package jpize.util.mesh;

import jpize.app.Jpize;
import jpize.gl.tesselation.GlPrimitive;
import jpize.gl.texture.Texture2D;
import jpize.gl.texture.TextureUtils;
import jpize.util.Disposable;
import jpize.util.array.FloatList;
import jpize.util.color.Color;
import jpize.util.color.AbstractColor;
import jpize.util.region.Region;
import jpize.util.region.TextureRegion;
import jpize.util.res.Resource;
import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertAttr;
import jpize.util.camera.Camera;
import jpize.util.math.matrix.Matrix3f;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2f;
import jpize.gl.shader.Shader;

public class TextureBatch implements Disposable {

    private final Mesh mesh;
    private final Shader defaultShader;
    private Shader currentShader;
    private Matrix4f combinedMat;
    private final Color color;
    private Texture2D lastTexture;
    // data
    private int size;
    private final FloatList vertexList;
    // transform
    private final Matrix3f transformMat, rotationMat, shearMat, scaleMat;
    private final Vec2f transformOrigin;
    private final Vec2f position;
    private boolean flipX, flipY;
    private boolean roundVertices;
    // tmp
    private final Vec2f tmp_origin, tmp_vertex1, tmp_vertex2, tmp_vertex3, tmp_vertex4;
    private Matrix4f tmp_projectionMat;


    public TextureBatch() {
        // mesh
        this.mesh = new Mesh(
            new GlVertAttr(2, GlType.FLOAT), // position
            new GlVertAttr(2, GlType.FLOAT), // uv
            new GlVertAttr(4, GlType.FLOAT)  // color
        );
        this.mesh.setMode(GlPrimitive.QUADS);
        this.vertexList = new FloatList();
        // shader
        this.defaultShader = new Shader(
            Resource.internal("/shader/texture_batch/vert.glsl"),
            Resource.internal("/shader/texture_batch/frag.glsl")
        );
        this.setShader(defaultShader);
        // transform
        this.transformMat = new Matrix3f();
        this.rotationMat = new Matrix3f();
        this.shearMat = new Matrix3f();
        this.scaleMat = new Matrix3f();
        this.color = new Color();
        this.position = new Vec2f();
        this.transformOrigin = new Vec2f(0.5F);
        this.setRoundVertices(true);
        // tmp
        this.tmp_origin = new Vec2f();
        this.tmp_vertex1 = new Vec2f();
        this.tmp_vertex2 = new Vec2f();
        this.tmp_vertex3 = new Vec2f();
        this.tmp_vertex4 = new Vec2f();
    }


    public void setup(Matrix4f combined) {
        currentShader.bind();
        currentShader.uniform("u_combined", combined);
        combinedMat = combined;
    }

    public void setup(Camera camera) {
        this.setup(camera.getCombined());
    }

    public void setup() {
        if(tmp_projectionMat == null)
            tmp_projectionMat = new Matrix4f();
        tmp_projectionMat.setOrthographic(0F, 0F, Jpize.getWidth(), Jpize.getHeight());
        this.setup(tmp_projectionMat);
    }


    public void setShader(Shader shader) {
        if(shader == null){
            currentShader = defaultShader;
        }else{
            currentShader = shader;
        }
    }


    public void render(boolean clearCache) {
        if(size == 0 || lastTexture == null)
            return;
        if(combinedMat == null)
            throw new IllegalStateException("No matrix found. Call TextureBatch.setup() first");

        // render
        currentShader.bind();
        currentShader.uniform("u_texture", lastTexture);
        mesh.vertices().setData(vertexList.arrayTrimmed());
        mesh.render();

        // reset
        vertexList.clear();
        if(clearCache)
            vertexList.trim();
        size = 0;
    }

    public void render() {
        this.render(false);
    }


    private void addTexturedQuad(float x, float y, float width, float height, float u1, float v1, float u2, float v2, float r, float g, float b, float a) {
        tmp_origin.set(width * transformOrigin.x, height * transformOrigin.y);

        transformMat.set(rotationMat.getMul(scaleMat.getMul(shearMat)));

        tmp_vertex1.set(0F,    height).sub(tmp_origin).mulMat3(transformMat).add(tmp_origin).add(x, y).add(position);
        tmp_vertex2.set(0F,    0F    ).sub(tmp_origin).mulMat3(transformMat).add(tmp_origin).add(x, y).add(position);
        tmp_vertex3.set(width, 0F    ).sub(tmp_origin).mulMat3(transformMat).add(tmp_origin).add(x, y).add(position);
        tmp_vertex4.set(width, height).sub(tmp_origin).mulMat3(transformMat).add(tmp_origin).add(x, y).add(position);

        if(roundVertices) {
            tmp_vertex1.round();
            tmp_vertex2.round();
            tmp_vertex3.round();
            tmp_vertex4.round();
        }

        vertexList.add(tmp_vertex1.x, tmp_vertex1.y, (flipX ? u2 : u1), (flipY ? v2 : v1), r, g, b, a);
        vertexList.add(tmp_vertex2.x, tmp_vertex2.y, (flipX ? u2 : u1), (flipY ? v1 : v2), r, g, b, a);
        vertexList.add(tmp_vertex3.x, tmp_vertex3.y, (flipX ? u1 : u2), (flipY ? v1 : v2), r, g, b, a);
        vertexList.add(tmp_vertex4.x, tmp_vertex4.y, (flipX ? u1 : u2), (flipY ? v2 : v1), r, g, b, a);
    }


    public void draw(Texture2D texture, float x, float y, float width, float height, float r, float g, float b, float a) {
        if(texture != lastTexture){
            if(texture == null)
                return;
            this.render();
            lastTexture = texture;
        }

        this.addTexturedQuad(x, y, width, height, 0F, 0F, 1F, 1F, r, g, b, a);
        size++;
    }

    public void draw(Texture2D texture, float x, float y, float width, float height, AbstractColor color) {
        this.draw(texture, x, y, width, height,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public void draw(Texture2D texture, float x, float y, float width, float height) {
        this.draw(texture, x, y, width, height,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }


    public void draw(TextureRegion textureRegion, float x, float y, float width, float height, float r, float g, float b, float a) {
        final Texture2D texture = textureRegion.getTexture();
        if(texture != lastTexture){
            if(texture == null)
                return;
            this.render();
            lastTexture = texture;
        }

        this.addTexturedQuad(x, y, width, height,
            textureRegion.u1(), textureRegion.v1(), textureRegion.u2(), textureRegion.v2(),
            r, g, b, a);
        size++;
    }

    public void draw(TextureRegion textureRegion, float x, float y, float width, float height, Color color) {
        this.draw(textureRegion, x, y, width, height,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public void draw(TextureRegion textureRegion, float x, float y, float width, float height) {
        this.draw(textureRegion, x, y, width, height,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }


    public void draw(Texture2D texture, Region region, float x, float y, float width, float height, float r, float g, float b, float a) {
        if(texture != lastTexture){
            if(texture == null)
                return;
            this.render();
            lastTexture = texture;
        }

        this.addTexturedQuad(x, y, width, height,
            region.u1(), region.v1(), region.u2(), region.v2(),
            r, g, b, a);
        size++;
    }

    public void draw(Texture2D texture, Region region, float x, float y, float width, float height, Color color) {
        this.draw(texture, region, x, y, width, height,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public void draw(Texture2D texture, Region region, float x, float y, float width, float height){
        this.draw(texture, region, x, y, width, height,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }


    public void draw(TextureRegion textureRegion, Region region, float x, float y, float width, float height, float r, float g, float b, float a) {
        final TextureRegion subregion = new TextureRegion(textureRegion).setSubregion(region);
        this.draw(subregion, x, y, width, height, r, g, b, a);
    }

    public void draw(TextureRegion textureRegion, Region region, float x, float y, float width, float height, Color color) {
        this.draw(textureRegion, region, x, y, width, height,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public void draw(TextureRegion textureRegion, Region region, float x, float y, float width, float height) {
        this.draw(textureRegion, region, x, y, width, height,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }


    public void drawRect(float x, float y, float width, float height, double r, double g, double b, double a) {
        this.draw(TextureUtils.whiteTexture(), x, y, width, height,
                (float) r, (float) g, (float) b, (float) a);
    }

    public void drawRect(float x, float y, float width, float height, Color color) {
        this.drawRect(x, y, width, height, color.getRed(),
                color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public void drawRect(float x, float y, float width, float height, double r, double g, double b) {
        this.drawRect(x, y, width, height, r, g, b, 1D);
    }

    public void drawBlackRect(float x, float y, float width, float height, double alpha) {
        this.drawRect(x, y, width, height, 0F, 0F, 0F, alpha);
    }


    public int size() {
        return size;
    }


    public Color color() {
        return color;
    }

    public void resetColor() {
        color.reset();
    }

    public void setColor(AbstractColor color) {
        this.color.set(color);
    }

    public void setColor(double red, double green, double blue, double alpha) {
        color.set(red, green, blue, alpha);
    }

    public void setColori(int red, int green, int blue, int alpha) {
        color.seti(red, green, blue, alpha);
    }

    public void setColor(double red, double green, double blue) {
        color.set(red, green, blue);
    }

    public void setColori(int red, int green, int blue) {
        color.seti(red, green, blue);
    }

    public void setColor(int color) {
        this.color.set(color);
    }

    public void setColorRGB(int color) {
        this.color.setRGB(color);
    }

    public void setColorRGBA(int color) {
        this.color.setRGBA(color);
    }

    public void setAlpha(double alpha) {
        color.setAlpha(alpha);
    }


    public Vec2f getTransformOrigin() {
        return transformOrigin;
    }

    public void setTransformOrigin(double x, double y) {
        transformOrigin.set(x, y);
    }

    public Vec2f position() {
        return position;
    }

    public void rotate(double angle) {
        rotationMat.setRotation(angle);
    }

    public void shear(double angleX, double angleY) {
        shearMat.setShear(angleX, angleY);
    }

    public void setScale(double scale) {
        scaleMat.setScale(scale);
    }

    public void setScale(double x, double y) {
        scaleMat.setScale(x, y);
    }

    public void scale(double scale) {
        scaleMat.scale(scale);
    }

    public void scale(double x, double y) {
        scaleMat.scale(x, y);
    }

    public void flipX(boolean flip) {
        flipX = flip;
    }

    public void flipY(boolean flip) {
        flipY = flip;
    }

    public void flip(boolean x, boolean y) {
        this.flipX(x);
        this.flipY(y);
    }


    public boolean isRoundVertices() {
        return roundVertices;
    }

    public void setRoundVertices(boolean roundVertices) {
        this.roundVertices = roundVertices;
    }


    @Override
    public void dispose() {
        defaultShader.dispose();
        mesh.dispose();
    }

}
