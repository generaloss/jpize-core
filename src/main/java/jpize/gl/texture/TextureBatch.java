package jpize.gl.texture;

import jpize.app.Jpize;
import jpize.gl.tesselation.GlPrimitive;
import jpize.util.Disposable;
import jpize.util.mesh.Mesh;
import jpize.util.color.ImmutableColor;
import jpize.util.region.Region;
import jpize.util.region.TextureRegion;
import jpize.util.res.Resource;
import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertAttr;
import jpize.util.camera.Camera;
import jpize.util.color.Color;
import jpize.util.math.matrix.Matrix3f;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2f;
import jpize.gl.shader.Shader;

import static jpize.gl.buffer.QuadIndexBuffer.QUAD_VERTICES;

public class TextureBatch implements Disposable {

    private final Mesh mesh;
    private final Shader shader;
    private final Color color;
    private Texture2D lastTexture;
    private Matrix4f combinedMat;
    private Shader customShader;
    // data
    private final int maxSize, vertexBytes;
    private int size, vertexBufferOffset;
    public final float[] tmp_vertexData;
    // transform
    private final Vec2f transformOrigin;
    private final Matrix3f transformMat, rotationMat, shearMat, scaleMat;
    private final Vec2f position;
    private boolean flipX, flipY;
    private final Vec2f tmp_origin, tmp_vertex1, tmp_vertex2, tmp_vertex3, tmp_vertex4;

    public TextureBatch(int maxSize) {
        this.maxSize = maxSize;
        this.color = new Color();
        this.transformOrigin = new Vec2f(0.5F);

        // shader
        this.shader = new Shader(
            Resource.internal("/shader/batch/batch.vert"),
            Resource.internal("/shader/batch/batch.frag")
        );

        // mesh
        this.mesh = new Mesh(
            new GlVertAttr(2, GlType.FLOAT),
            new GlVertAttr(2, GlType.FLOAT),
            new GlVertAttr(4, GlType.FLOAT)
        );
        this.mesh.setMode(GlPrimitive.QUADS);

        // get vertex size
        final int vertexSize = mesh.vertices().getVertexSize();
        this.vertexBytes = mesh.vertices().getVertexBytes();

        // allocate buffers
        this.tmp_vertexData = new float[vertexSize];
        this.mesh.vertices().allocateData(QUAD_VERTICES * maxSize * vertexBytes);

        // matrices
        this.transformMat = new Matrix3f();
        this.rotationMat = new Matrix3f();
        this.shearMat = new Matrix3f();
        this.scaleMat = new Matrix3f();
        this.position = new Vec2f();

        // tmp
        this.tmp_origin  = new Vec2f();
        this.tmp_vertex1 = new Vec2f();
        this.tmp_vertex2 = new Vec2f();
        this.tmp_vertex3 = new Vec2f();
        this.tmp_vertex4 = new Vec2f();
    }

    public TextureBatch() {
        this(2048);
    }


    private void addVertex(float x, float y, float s, float t, float r, float g, float b, float a) {
        tmp_vertexData[0] = x;
        tmp_vertexData[1] = y;

        tmp_vertexData[2] = s;
        tmp_vertexData[3] = t;

        tmp_vertexData[4] = r;
        tmp_vertexData[5] = g;
        tmp_vertexData[6] = b;
        tmp_vertexData[7] = a;

        mesh.vertices().setSubData(vertexBufferOffset, tmp_vertexData);
        vertexBufferOffset += vertexBytes;
    }

    private void addTexturedQuad(float x, float y, float width, float height, float u1, float v1, float u2, float v2, float r, float g, float b, float a) {
        tmp_origin.set(width * transformOrigin.x, height * transformOrigin.y);

        transformMat.set(rotationMat.getMul(scaleMat.getMul(shearMat)));

        tmp_vertex1.set(0F,    height).sub(tmp_origin).mulMat3(transformMat).add(tmp_origin).add(x, y).add(position);
        tmp_vertex2.set(0F,    0F    ).sub(tmp_origin).mulMat3(transformMat).add(tmp_origin).add(x, y).add(position);
        tmp_vertex3.set(width, 0F    ).sub(tmp_origin).mulMat3(transformMat).add(tmp_origin).add(x, y).add(position);
        tmp_vertex4.set(width, height).sub(tmp_origin).mulMat3(transformMat).add(tmp_origin).add(x, y).add(position);

        this.addVertex(tmp_vertex1.x, tmp_vertex1.y, (flipX ? u2 : u1), (flipY ? v2 : v1), r, g, b, a);
        this.addVertex(tmp_vertex2.x, tmp_vertex2.y, (flipX ? u2 : u1), (flipY ? v1 : v2), r, g, b, a);
        this.addVertex(tmp_vertex3.x, tmp_vertex3.y, (flipX ? u1 : u2), (flipY ? v1 : v2), r, g, b, a);
        this.addVertex(tmp_vertex4.x, tmp_vertex4.y, (flipX ? u1 : u2), (flipY ? v2 : v1), r, g, b, a);
    }


    public void setup(Matrix4f combined) {
        this.combinedMat = combined;
    }

    public void setup(Camera camera) {
        this.setup(camera.getCombined());
    }

    public void setup() {
        if(combinedMat == null)
            combinedMat = new Matrix4f();
        combinedMat.setOrthographic(0F, 0F, Jpize.getWidth(), Jpize.getHeight());
        this.setup(combinedMat);
    }

    public void setShader(Shader shader) {
        customShader = shader;
    }

    public void render() {
        if(lastTexture == null || size == 0 || combinedMat == null)
            return;

        // shader
        final Shader usedShader = (customShader != null) ? customShader : shader;

        usedShader.bind();
        usedShader.uniform("u_combined", combinedMat);
        usedShader.uniform("u_texture", lastTexture);

        // render
        mesh.render(size * QUAD_VERTICES);

        // reset
        size = 0;
        vertexBufferOffset = 0;
    }


    public void draw(Texture2D texture, float x, float y, float width, float height, float r, float g, float b, float a) {
        if(size == maxSize)
            this.render();

        if(texture != lastTexture){
            if(texture == null)
                return;
            this.render();
            lastTexture = texture;
        }

        this.addTexturedQuad(x, y, width, height, 0F, 0F, 1F, 1F, r, g, b, a);
        size++;
    }

    public void draw(Texture2D texture, float x, float y, float width, float height, Color color) {
        this.draw(texture, x, y, width, height, color.r, color.g, color.b, color.a);
    }

    public void draw(Texture2D texture, float x, float y, float width, float height) {
        this.draw(texture, x, y, width, height, color.r, color.g, color.b, color.a);
    }


    public void draw(TextureRegion textureRegion, float x, float y, float width, float height, float r, float g, float b, float a) {
        if(size == maxSize)
            this.render();

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
        this.draw(textureRegion, x, y, width, height, color.r, color.g, color.b, color.a);
    }

    public void draw(TextureRegion textureRegion, float x, float y, float width, float height) {
        this.draw(textureRegion, x, y, width, height, color.r, color.g, color.b, color.a);
    }


    public void draw(Texture2D texture, Region region, float x, float y, float width, float height, float r, float g, float b, float a) {
        if(size == maxSize)
            this.render();

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
        this.draw(texture, region, x, y, width, height, color.r, color.g, color.b, color.a);
    }

    public void draw(Texture2D texture, Region region, float x, float y, float width, float height){
        this.draw(texture, region, x, y, width, height, color.r, color.g, color.b, color.a);
    }


    public void draw(TextureRegion textureRegion, Region region, float x, float y, float width, float height, float r, float g, float b, float a) {
        final TextureRegion subregion = new TextureRegion(textureRegion).setSubregion(region);
        this.draw(subregion, x, y, width, height, r, g, b, a);
    }

    public void draw(TextureRegion textureRegion, Region region, float x, float y, float width, float height, Color color) {
        this.draw(textureRegion, region, x, y, width, height, color.r, color.g, color.b, color.a);
    }

    public void draw(TextureRegion textureRegion, Region region, float x, float y, float width, float height) {
        this.draw(textureRegion, region, x, y, width, height, color.r, color.g, color.b, color.a);
    }


    public void drawRect(float x, float y, float width, float height, double r, double g, double b, double a) {
        this.draw(TextureUtils.whiteTexture(), x, y, width, height, (float) r, (float) g, (float) b, (float) a);
    }

    public void drawRect(float x, float y, float width, float height, Color color) {
        this.drawRect(x, y, width, height, color.r, color.g, color.b, color.a);
    }

    public void drawRect(float x, float y, float width, float height, double r, double g, double b) {
        this.drawRect(x, y, width, height, r, g, b, 1D);
    }

    public void drawRect(float x, float y, float width, float height, double alpha) {
        this.drawRect(x, y, width, height, 0F, 0F, 0F, alpha);
    }


    public int size() {
        return size;
    }

    public Color color() {
        return color;
    }

    public void resetColor() {
        color.set(1F, 1F, 1F, 1F);
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void setColor(ImmutableColor color) {
        this.color.set(color);
    }

    public void setColor(double r, double g, double b, double a) {
        color.set(r, g, b, a);
    }

    public void setColor(double r, double g, double b) {
        color.set(r, g, b, 1F);
    }

    public void setAlpha(double a) {
        color.setA(a);
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


    @Override
    public void dispose() {
        shader.dispose();
        mesh.dispose();
    }

}
