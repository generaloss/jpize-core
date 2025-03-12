package jpize.util.mesh;

import jpize.context.Jpize;
import jpize.opengl.tesselation.GlPrimitive;
import jpize.opengl.texture.Texture2D;
import jpize.opengl.texture.TextureUtils;
import jpize.util.Disposable;
import jpize.util.array.FloatList;
import jpize.util.color.Color;
import jpize.util.color.AbstractColor;
import jpize.util.math.geometry.Rect;
import jpize.util.region.Region;
import jpize.util.region.TextureRegion;
import jpize.util.res.Resource;
import jpize.opengl.type.GlType;
import jpize.opengl.vertex.GlVertAttr;
import jpize.util.camera.Camera;
import jpize.util.math.matrix.Matrix3f;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2f;
import jpize.opengl.shader.Shader;

public class TextureBatch implements Disposable {

    private final Mesh mesh;
    private final Shader defaultShader;
    private Shader currentShader;
    private Matrix4f combinedMat;
    private final Color color;
    // quad
    private Texture2D quadTexture;
    private final Region quadRegion;
    private final Rect quadRect;
    private final Color quadColor;
    // data
    private int size;
    private final FloatList vertexList;
    // transform
    private final Matrix3f transformMat, rotationMat, shearMat;
    private final Vec2f transformOrigin;
    private final Vec2f position;
    private final Vec2f scale;
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
        // quad
        this.quadRegion = new TextureRegion();
        this.quadRect = new Rect();
        this.quadColor = new Color();
        // transform
        this.transformMat = new Matrix3f();
        this.rotationMat = new Matrix3f();
        this.shearMat = new Matrix3f();
        this.color = new Color();
        this.position = new Vec2f();
        this.transformOrigin = new Vec2f(0.5F);
        this.scale = new Vec2f(1F);
        this.roundVertices = true;
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


    public Shader getCurrentShader() {
        return currentShader;
    }

    public void setShader(Shader shader) {
        if(shader == null){
            currentShader = defaultShader;
        }else{
            currentShader = shader;
        }
    }


    public void render(boolean clearCache) {
        if(size == 0 || quadTexture == null)
            return;
        if(combinedMat == null)
            throw new IllegalStateException("No matrix found. Call TextureBatch.setup() first");

        // render
        currentShader.bind();
        currentShader.uniform("u_texture", quadTexture);
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


    private void drawQuad() {
        final float x = quadRect.x;
        final float y = quadRect.y;
        final float width = quadRect.width;
        final float height = quadRect.height;

        tmp_origin.set(width * transformOrigin.x, height * transformOrigin.y);

        transformMat.set(rotationMat.getMul(shearMat));

        tmp_vertex1.set(0F,    height).sub(tmp_origin).mul(scale).mulMat3(transformMat).add(tmp_origin).add(x, y).add(position);
        tmp_vertex2.set(0F,    0F    ).sub(tmp_origin).mul(scale).mulMat3(transformMat).add(tmp_origin).add(x, y).add(position);
        tmp_vertex3.set(width, 0F    ).sub(tmp_origin).mul(scale).mulMat3(transformMat).add(tmp_origin).add(x, y).add(position);
        tmp_vertex4.set(width, height).sub(tmp_origin).mul(scale).mulMat3(transformMat).add(tmp_origin).add(x, y).add(position);

        if(roundVertices) {
            tmp_vertex1.round();
            tmp_vertex2.round();
            tmp_vertex3.round();
            tmp_vertex4.round();
        }

        final float u1 = (flipX ? quadRegion.u1 : quadRegion.u2);
        final float v1 = (flipY ? quadRegion.v1 : quadRegion.v2);
        final float u2 = (flipX ? quadRegion.u2 : quadRegion.u1);
        final float v2 = (flipY ? quadRegion.v2 : quadRegion.v1);

        final float r = quadColor.red;
        final float g = quadColor.green;
        final float b = quadColor.blue;
        final float a = quadColor.alpha;

        vertexList.add(tmp_vertex1.x, tmp_vertex1.y, u2, v2, r, g, b, a);
        vertexList.add(tmp_vertex2.x, tmp_vertex2.y, u2, v1, r, g, b, a);
        vertexList.add(tmp_vertex3.x, tmp_vertex3.y, u1, v1, r, g, b, a);
        vertexList.add(tmp_vertex4.x, tmp_vertex4.y, u1, v2, r, g, b, a);

        size++;
    }


    private boolean applyTexture(Texture2D texture) {
        if(texture != quadTexture){
            if(texture == null)
                return false;
            this.render();
            quadTexture = texture;
        }
        return true;
    }

    private boolean applyTextureRegion(TextureRegion textureRegion) {
        if(textureRegion == null)
            return false;

        final Texture2D texture = textureRegion.getTexture();
        if(!this.applyTexture(texture))
            return false;

        quadRegion.set(textureRegion);
        return true;
    }


    private void drawColorless(Texture2D texture, float x, float y, float width, float height) {
        if(!this.applyTexture(texture)) return;
        quadRegion.set(0F, 0F, 1F, 1F);
        quadRect.set(x, y, width, height);
        this.drawQuad();
    }

    public void draw(Texture2D texture, float x, float y, float width, float height) {
        quadColor.set(color);
        this.drawColorless(texture, x, y, width, height);
    }

    public void draw(Texture2D texture, float x, float y, float width, float height, AbstractColor color) {
        quadColor.set(color);
        this.drawColorless(texture, x, y, width, height);
    }

    public void draw(Texture2D texture, float x, float y, float width, float height, double r, double g, double b, double a) {
        quadColor.set(r, g, b, a);
        this.drawColorless(texture, x, y, width, height);
    }

    public void draw(Texture2D texture, float x, float y, float width, float height, double r, double g, double b) {
        quadColor.set(r, g, b);
        this.drawColorless(texture, x, y, width, height);
    }

    public void drawRGBA(Texture2D texture, float x, float y, float width, float height, int color) {
        quadColor.setRGBA(color);
        this.drawColorless(texture, x, y, width, height);
    }

    public void drawRGB(Texture2D texture, float x, float y, float width, float height, int color) {
        quadColor.setRGB(color);
        this.drawColorless(texture, x, y, width, height);
    }


    private void drawColorless(TextureRegion textureRegion, float x, float y, float width, float height) {
        if(!this.applyTextureRegion(textureRegion)) return;
        quadRect.set(x, y, width, height);
        this.drawQuad();
    }

    public void draw(TextureRegion textureRegion, float x, float y, float width, float height) {
        quadColor.set(color);
        this.drawColorless(textureRegion, x, y, width, height);
    }

    public void draw(TextureRegion textureRegion, float x, float y, float width, float height, AbstractColor color) {
        quadColor.set(color);
        this.drawColorless(textureRegion, x, y, width, height);
    }

    public void draw(TextureRegion textureRegion, float x, float y, float width, float height, double r, double g, double b, double a) {
        quadColor.set(r, g, b, a);
        this.drawColorless(textureRegion, x, y, width, height);
    }

    public void draw(TextureRegion textureRegion, float x, float y, float width, float height, double r, double g, double b) {
        quadColor.set(r, g, b);
        this.drawColorless(textureRegion, x, y, width, height);
    }

    public void drawRGBA(TextureRegion textureRegion, float x, float y, float width, float height, int color) {
        quadColor.setRGBA(color);
        this.drawColorless(textureRegion, x, y, width, height);
    }

    public void drawRGB(TextureRegion textureRegion, float x, float y, float width, float height, int color) {
        quadColor.setRGB(color);
        this.drawColorless(textureRegion, x, y, width, height);
    }


    private void drawColorless(Texture2D texture, Region region, float x, float y, float width, float height) {
        if(!this.applyTexture(texture)) return;
        quadRegion.set(region);
        quadRect.set(x, y, width, height);
        this.drawQuad();
    }

    public void draw(Texture2D texture, Region region, float x, float y, float width, float height){
        quadColor.set(color);
        this.drawColorless(texture, region, x, y, width, height);
    }

    public void draw(Texture2D texture, Region region, float x, float y, float width, float height, AbstractColor color) {
        quadColor.set(color);
        this.drawColorless(texture, region, x, y, width, height);
    }

    public void draw(Texture2D texture, Region region, float x, float y, float width, float height, double r, double g, double b, double a) {
        quadColor.set(r, g, b, a);
        this.drawColorless(texture, region, x, y, width, height);
    }

    public void draw(Texture2D texture, Region region, float x, float y, float width, float height, double r, double g, double b) {
        quadColor.set(r, g, b);
        this.drawColorless(texture, region, x, y, width, height);
    }

    public void drawRGBA(Texture2D texture, Region region, float x, float y, float width, float height, int color) {
        quadColor.setRGBA(color);
        this.drawColorless(texture, region, x, y, width, height);
    }

    public void drawRGB(Texture2D texture, Region region, float x, float y, float width, float height, int color) {
        quadColor.setRGB(color);
        this.drawColorless(texture, region, x, y, width, height);
    }


    public void draw(TextureRegion textureRegion, Region region, float x, float y, float width, float height) {
        quadColor.set(color);
        this.drawColorless(new TextureRegion(textureRegion).setSubregion(region), x, y, width, height);
    }

    public void draw(TextureRegion textureRegion, Region region, float x, float y, float width, float height, AbstractColor color) {
        quadColor.set(color);
        this.drawColorless(new TextureRegion(textureRegion).setSubregion(region), x, y, width, height);
    }

    public void draw(TextureRegion textureRegion, Region region, float x, float y, float width, float height, double r, double g, double b, double a) {
        quadColor.set(r, g, b, a);
        this.drawColorless(new TextureRegion(textureRegion).setSubregion(region), x, y, width, height);
    }

    public void draw(TextureRegion textureRegion, Region region, float x, float y, float width, float height, double r, double g, double b) {
        quadColor.set(r, g, b);
        this.drawColorless(new TextureRegion(textureRegion).setSubregion(region), x, y, width, height);
    }

    public void drawRGBA(TextureRegion textureRegion, Region region, float x, float y, float width, float height, int color) {
        quadColor.setRGBA(color);
        this.drawColorless(new TextureRegion(textureRegion).setSubregion(region), x, y, width, height);
    }

    public void drawRGB(TextureRegion textureRegion, Region region, float x, float y, float width, float height, int color) {
        quadColor.setRGB(color);
        this.drawColorless(new TextureRegion(textureRegion).setSubregion(region), x, y, width, height);
    }


    public void drawRect(float x, float y, float width, float height, AbstractColor color) {
        this.draw(TextureUtils.whiteTexture(), x, y, width, height, color);
    }

    public void drawRect(float x, float y, float width, float height, double r, double g, double b, double a) {
        this.draw(TextureUtils.whiteTexture(), x, y, width, height, r, g, b, a);
    }

    public void drawRect(float x, float y, float width, float height, double r, double g, double b) {
        this.draw(TextureUtils.whiteTexture(), x, y, width, height, r, g, b);
    }

    public void drawRectRGBA(float x, float y, float width, float height, int color) {
        this.drawRGBA(TextureUtils.whiteTexture(), x, y, width, height, color);
    }

    public void drawRectRGB(float x, float y, float width, float height, int color) {
        this.drawRGB(TextureUtils.whiteTexture(), x, y, width, height, color);
    }

    public void drawRectBlack(float x, float y, float width, float height, double alpha) {
        this.draw(TextureUtils.whiteTexture(), x, y, width, height, 0D, 0D, 0D, alpha);
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

    public Vec2f scale() {
        return scale;
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
