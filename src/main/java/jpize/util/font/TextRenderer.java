package jpize.util.font;

import jpize.app.Jpize;
import jpize.gl.shader.Shader;
import jpize.gl.tesselation.GlPrimitive;
import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertAttr;
import jpize.util.font.glyph.GlyphIterator;
import jpize.util.font.glyph.GlyphSprite;
import jpize.util.mesh.Mesh;
import jpize.util.region.Region;
import jpize.gl.texture.Texture2D;
import jpize.util.mesh.TextureBatch;
import jpize.util.array.FloatList;
import jpize.util.color.Color;
import jpize.util.math.matrix.Matrix3f;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2f;
import jpize.util.res.Resource;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class TextRenderer { //! optimize code

    public static void render(Font font, FontRenderOptions options, TextureBatch batch, String text, float x, float y) {
        if(text == null || text.isBlank())
            return;

        // init
        batch.setTransformOrigin(0F, 0F);
        batch.rotate(options.getRotation());
        batch.shear(font.isItalic() ? options.getItalicAngle() : 0F, 0F);

        final Vec2f centerPos = font.getTextBounds(text).mul(options.rotationOrigin());
        centerPos.y *= options.getLineWrapSign();

        // context-local instance
        final Iterable<GlyphSprite> iterable = font.iterable(text);
        final GlyphIterator iterator = (GlyphIterator) iterable.iterator();

        for(GlyphSprite sprite: iterable){
            // cull lines
            if(options.isCullLinesEnabled()) {
                final float lineBottomY = (iterator.getCursorY() * options.scale().y + y);
                final float lineTopY = (lineBottomY + font.getLineAdvanceScaled());
                if(lineTopY < options.getCullLinesBottomY() || lineBottomY > options.getCullLinesTopY()){
                    iterator.skipLine();
                    continue;
                }
            }

            if((char) sprite.getCode() == ' ' || !sprite.isRenderable())
                continue;

            final Vec2f renderPos = new Vec2f(sprite.getX(), sprite.getY());
            renderPos.y -= font.getDescentScaled();
            renderPos
                .sub(centerPos)
                .rotate(options.getRotation())
                .add(centerPos)
                .add(x, y);
            renderPos.y += font.getDescentScaled();
            batch.draw(sprite.getPage(), sprite.getRegion(), renderPos.x, renderPos.y, sprite.getWidth(), sprite.getHeight(), options.color());
        }
    }


    public static void render(Font font, FontRenderOptions options, String text, float x, float y) {
        if(text == null || text.isBlank())
            return;

        // context-local instance
        if(!RENDERERS_BY_CONTEXT.containsKey(GLFW.glfwGetCurrentContext())){
            final Renderer renderer = new Renderer(
                new Mesh(new GlVertAttr(2, GlType.FLOAT), new GlVertAttr(2, GlType.FLOAT), new GlVertAttr(4, GlType.FLOAT)),
                new Shader(Resource.internal("/shader/text_renderer/vert.glsl"), Resource.internal("/shader/text_renderer/frag.glsl")),
                new Matrix4f()
            );
            renderer.mesh.setMode(GlPrimitive.QUADS);
            RENDERERS_BY_CONTEXT.put(GLFW.glfwGetCurrentContext(), renderer);
        }
        final Renderer renderer = RENDERERS_BY_CONTEXT.get(GLFW.glfwGetCurrentContext());

        // init
        renderer.matrixCombined.setOrthographic(0F, 0F, Jpize.getWidth(), Jpize.getHeight());

        final Color color = options.color();

        final Matrix3f transformMatrix = new Matrix3f()
            .setRotation(options.getRotation())
            .shear(font.isItalic() ? options.getItalicAngle() : 0F, 0F);

        final Vec2f centerPos = font.getTextBounds(text).mul(options.rotationOrigin());
        centerPos.y *= options.getLineWrapSign();

        final FloatList vertices = new FloatList(text.length() * 4);
        Texture2D lastTexture = null;

        // iterate glyphs
        final Iterable<GlyphSprite> iterable = font.iterable(text);
        final GlyphIterator iterator = (GlyphIterator) iterable.iterator();

        for(GlyphSprite sprite: iterable){
            // cull lines
            if(options.isCullLinesEnabled()) {
                final float lineBottomY = (iterator.getCursorY() * options.scale().y + y);
                final float lineTopY = (lineBottomY + font.getLineAdvanceScaled());
                if(lineTopY < options.getCullLinesBottomY() || lineBottomY > options.getCullLinesTopY()){
                    iterator.skipLine();
                    continue;
                }
            }

            if((char) sprite.getCode() == ' ' || !sprite.isRenderable())
                continue;

            // add textured quad
            final Vec2f renderPos = new Vec2f(sprite.getX(), sprite.getY());
            renderPos.y -= font.getDescentScaled();
            renderPos
                .sub(centerPos)
                .mulMat3(transformMatrix)
                .add(centerPos)
                .add(x, y);
            renderPos.y += font.getDescentScaled();

            final Texture2D page = sprite.getPage();
            final float width = sprite.getWidth();
            final float height = sprite.getHeight();
            final Region region = sprite.getRegion();

            final float u1 = region.u1();
            final float u2 = region.u2();
            final float v1 = region.v1();
            final float v2 = region.v2();

            final float r = color.r;
            final float g = color.g;
            final float b = color.b;
            final float a = color.a;

            final Vec2f vertex1 = new Vec2f(0,     height).mulMat3(transformMatrix).add(renderPos);
            final Vec2f vertex2 = new Vec2f(0,     0     ).mulMat3(transformMatrix).add(renderPos);
            final Vec2f vertex3 = new Vec2f(width, 0     ).mulMat3(transformMatrix).add(renderPos);
            final Vec2f vertex4 = new Vec2f(width, height).mulMat3(transformMatrix).add(renderPos);

            vertices.add(
                vertex1.x, vertex1.y,  u1, v1,  r, g, b, a,
                vertex2.x, vertex2.y,  u1, v2,  r, g, b, a,
                vertex3.x, vertex3.y,  u2, v2,  r, g, b, a,
                vertex4.x, vertex4.y,  u2, v1,  r, g, b, a
            );

            // render mesh
            if(lastTexture == null)
                lastTexture = page;

            if(lastTexture != page){
                lastTexture = page;

                renderer.mesh.vertices().setData(vertices.array());
                vertices.clear();

                renderer.shader.bind();
                renderer.shader.uniform("u_combined", renderer.matrixCombined);
                renderer.shader.uniform("u_texture", lastTexture);
                renderer.mesh.render();
            }
        }

        // render mesh
        if(lastTexture == null || vertices.isEmpty())
            return;

        renderer.mesh.vertices().setData(vertices.copyOf());
        renderer.shader.bind();
        renderer.shader.uniform("u_combined", renderer.matrixCombined);
        renderer.shader.uniform("u_texture", lastTexture);
        renderer.mesh.render();
    }

    private static final Map<Long, Renderer> RENDERERS_BY_CONTEXT = new HashMap<>();

    private record Renderer(Mesh mesh, Shader shader, Matrix4f matrixCombined) { }

}
