package jpize.util.font;

import jpize.app.Jpize;
import jpize.gl.shader.Shader;
import jpize.gl.tesselation.GlPrimitive;
import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertAttr;
import jpize.util.camera.Camera3D;
import jpize.util.math.vector.Vec3f;
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

public class TextRenderer {

    public static void render(Font font, TextureBatch batch, String text, float x, float y) {
        if(text == null || text.isBlank())
            return;
        
        // init
        final FontRenderOptions options = font.getRenderOptions();
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


    private record Renderer(Mesh mesh, Shader shader, Matrix4f matrixCombined) { }

    private static final Map<Long, Renderer> RENDERER_BY_CONTEXT = new HashMap<>();

    public static void render(Font font, String text, float x, float y) {
        if(text == null || text.isBlank())
            return;

        // context-local instance
        if(!RENDERER_BY_CONTEXT.containsKey(GLFW.glfwGetCurrentContext())){
            final Renderer renderer = new Renderer(
                new Mesh(new GlVertAttr(3, GlType.FLOAT), new GlVertAttr(2, GlType.FLOAT), new GlVertAttr(4, GlType.FLOAT)),
                new Shader(Resource.internal("/shader/text_renderer/vert.glsl"), Resource.internal("/shader/text_renderer/frag.glsl")),
                new Matrix4f()
            );
            renderer.mesh.setMode(GlPrimitive.QUADS);
            RENDERER_BY_CONTEXT.put(GLFW.glfwGetCurrentContext(), renderer);
        }
        final Renderer renderer = RENDERER_BY_CONTEXT.get(GLFW.glfwGetCurrentContext());

        // init
        final FontRenderOptions options = font.getRenderOptions();
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

            final float r = color.getRed();
            final float g = color.getGreen();
            final float b = color.getBlue();
            final float a = color.getAlpha();

            final Vec2f vertex1 = new Vec2f(0F,    height).mulMat3(transformMatrix).add(renderPos);
            final Vec2f vertex2 = new Vec2f(0F,    0F    ).mulMat3(transformMatrix).add(renderPos);
            final Vec2f vertex3 = new Vec2f(width, 0F    ).mulMat3(transformMatrix).add(renderPos);
            final Vec2f vertex4 = new Vec2f(width, height).mulMat3(transformMatrix).add(renderPos);

            vertices.add(
                vertex1.x, vertex1.y, 0F,  u1, v1,  r, g, b, a,
                vertex2.x, vertex2.y, 0F,  u1, v2,  r, g, b, a,
                vertex3.x, vertex3.y, 0F,  u2, v2,  r, g, b, a,
                vertex4.x, vertex4.y, 0F,  u2, v1,  r, g, b, a
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

    public static void render(Font font, Camera3D camera, String text, float x, float y, float z) {
        if(text == null || text.isBlank())
            return;

        // context-local instance
        if(!RENDERER_BY_CONTEXT.containsKey(GLFW.glfwGetCurrentContext())){
            final Renderer renderer = new Renderer(
                    new Mesh(new GlVertAttr(3, GlType.FLOAT), new GlVertAttr(2, GlType.FLOAT), new GlVertAttr(4, GlType.FLOAT)),
                    new Shader(Resource.internal("/shader/text_renderer/vert.glsl"), Resource.internal("/shader/text_renderer/frag.glsl")),
                    new Matrix4f()
            );
            renderer.mesh.setMode(GlPrimitive.QUADS);
            RENDERER_BY_CONTEXT.put(GLFW.glfwGetCurrentContext(), renderer);
        }
        final Renderer renderer = RENDERER_BY_CONTEXT.get(GLFW.glfwGetCurrentContext());

        // init
        final FontRenderOptions options = font.getRenderOptions();
        final Color color = options.color();

        final Matrix3f shearMatrix = new Matrix3f()
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
            final Vec3f renderPos = new Vec3f(sprite.getX(), sprite.getY());
            renderPos.y -= font.getDescentScaled();
            renderPos
                    .sub(centerPos)
                    .mulMat4(options.matrix())
                    .mulMat3(shearMatrix)
                    .add(centerPos)
                    .add(x, y, z);
            renderPos.y += font.getDescentScaled();

            final Texture2D page = sprite.getPage();
            final float width = sprite.getWidth();
            final float height = sprite.getHeight();
            final Region region = sprite.getRegion();

            final float u1 = region.u1();
            final float u2 = region.u2();
            final float v1 = region.v1();
            final float v2 = region.v2();

            final float r = color.getRed();
            final float g = color.getGreen();
            final float b = color.getBlue();
            final float a = color.getAlpha();

            final Vec3f vertex1 = new Vec3f(0F,    height).mulMat4(options.matrix()).mulMat3(shearMatrix).add(renderPos);
            final Vec3f vertex2 = new Vec3f(0F,    0F    ).mulMat4(options.matrix()).mulMat3(shearMatrix).add(renderPos);
            final Vec3f vertex3 = new Vec3f(width, 0F    ).mulMat4(options.matrix()).mulMat3(shearMatrix).add(renderPos);
            final Vec3f vertex4 = new Vec3f(width, height).mulMat4(options.matrix()).mulMat3(shearMatrix).add(renderPos);

            vertices.add(
                    vertex1.x, vertex1.y, vertex1.z,  u1, v1,  r, g, b, a,
                    vertex2.x, vertex2.y, vertex2.z,  u1, v2,  r, g, b, a,
                    vertex3.x, vertex3.y, vertex3.z,  u2, v2,  r, g, b, a,
                    vertex4.x, vertex4.y, vertex4.z,  u2, v1,  r, g, b, a
            );

            // render mesh
            if(lastTexture == null)
                lastTexture = page;

            if(lastTexture != page){
                lastTexture = page;

                renderer.mesh.vertices().setData(vertices.array());
                vertices.clear();

                renderer.shader.bind();
                renderer.shader.uniform("u_combined", camera.getCombined());
                renderer.shader.uniform("u_texture", lastTexture);
                renderer.mesh.render();
            }
        }

        // render mesh
        if(lastTexture == null || vertices.isEmpty())
            return;

        renderer.mesh.vertices().setData(vertices.copyOf());
        renderer.shader.bind();
        renderer.shader.uniform("u_combined", camera.getCombined());
        renderer.shader.uniform("u_texture", lastTexture);
        renderer.mesh.render();
    }

}
