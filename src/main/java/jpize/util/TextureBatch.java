package jpize.util;

import jpize.app.Jpize;
import jpize.gl.tesselation.GlPrimitive;
import jpize.util.mesh.Mesh;
import jpize.util.color.ImmutableColor;
import jpize.util.region.Region;
import jpize.util.region.TextureRegion;
import jpize.util.res.Resource;
import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertAttr;
import jpize.util.camera.Camera;
import jpize.gl.texture.GlTexture2D;
import jpize.util.color.Color;
import jpize.util.math.matrix.Matrix3f;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2f;
import jpize.util.scissor.Scissor;
import jpize.util.shader.Shader;

import static jpize.util.buffer.QuadIndexBuffer.QUAD_VERTICES;

public class TextureBatch implements Disposable {

    // Tesselation
    private final Mesh mesh;
    private final Shader shader;
    private final Color color;
    private GlTexture2D lastTexture;
    // Custom
    private Matrix4f projectionMat, viewMat;
    private Shader customShader;
    // Data
    private final int maxSize, vertexBytes;
    private int size, vertexBufferOffset;
    public final float[] vertexData;
    // Transform
    private float translateX, translateY;
    private final Vec2f transformOrigin;
    private final Matrix3f transformMat, rotationMat, shearMat, scaleMat, flipMat;
    private final Scissor scissor;

    public TextureBatch(int maxSize) {
        this.maxSize = maxSize;
        this.color = new Color();
        this.scissor = new Scissor(this);
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
        final int vertexSize = mesh.getBuffer().getVertexSize();
        this.vertexBytes = mesh.getBuffer().getVertexBytes();

        // allocate buffers
        this.vertexData = new float[vertexSize];
        this.mesh.getBuffer().allocateData(QUAD_VERTICES * maxSize * vertexBytes);

        // matrices
        this.transformMat = new Matrix3f();
        this.rotationMat = new Matrix3f();
        this.shearMat = new Matrix3f();
        this.scaleMat = new Matrix3f();
        this.flipMat = new Matrix3f();
    }

    public TextureBatch() {
        this(1024);
    }


    private void addVertex(float x, float y, float s, float t, float r, float g, float b, float a) {
        vertexData[0] = x;
        vertexData[1] = y;

        vertexData[2] = s;
        vertexData[3] = t;

        vertexData[4] = r;
        vertexData[5] = g;
        vertexData[6] = b;
        vertexData[7] = a;

        mesh.getBuffer().setSubData(vertexBufferOffset, vertexData);
        vertexBufferOffset += vertexBytes;
    }

    private void addTexturedQuad(float x, float y, float width, float height, float u1, float v1, float u2, float v2, float r, float g, float b, float a) {
        final Vec2f origin = new Vec2f(width * transformOrigin.x, height * transformOrigin.y);

        transformMat.set(rotationMat.getMul(scaleMat.getMul(shearMat.getMul(flipMat))));

        final Vec2f vertex1 = new Vec2f(0F,    height).sub(origin) .mulMat3(transformMat) .add(origin).add(x + translateX, y + translateY);
        final Vec2f vertex2 = new Vec2f(0F,    0F    ).sub(origin) .mulMat3(transformMat) .add(origin).add(x + translateX, y + translateY);
        final Vec2f vertex3 = new Vec2f(width, 0F    ).sub(origin) .mulMat3(transformMat) .add(origin).add(x + translateX, y + translateY);
        final Vec2f vertex4 = new Vec2f(width, height).sub(origin) .mulMat3(transformMat) .add(origin).add(x + translateX, y + translateY);

        addVertex(vertex1.x, vertex1.y, u1, v1, r, g, b, a);
        addVertex(vertex2.x, vertex2.y, u1, v2, r, g, b, a);
        addVertex(vertex3.x, vertex3.y, u2, v2, r, g, b, a);
        addVertex(vertex4.x, vertex4.y, u2, v1, r, g, b, a);
    }


    public void begin(Matrix4f projection, Matrix4f view) {
        this.projectionMat = projection;
        this.viewMat = view;
    }

    public void begin(Camera camera) {
        begin(camera.getProjection(), camera.getView());
    }

    public void begin() {
        if(viewMat == null) viewMat = new Matrix4f();
        if(projectionMat == null) projectionMat = new Matrix4f();

        begin(projectionMat.setOrthographic(0F, 0F, Jpize.getWidth(), Jpize.getHeight()), viewMat);
    }

    public void end() {
        flush();
    }

    private void flush() {
        if(lastTexture == null || size == 0 || projectionMat == null)
            return;

        // Shader
        final Shader usedShader = (customShader != null) ? customShader : shader;

        usedShader.bind();
        usedShader.uniform("u_projection", projectionMat);
        usedShader.uniform("u_view", viewMat);
        usedShader.uniform("u_texture", lastTexture);

        // Render
        mesh.render(size * QUAD_VERTICES);

        // Reset
        size = 0;
        vertexBufferOffset = 0;
    }

    public void useShader(Shader shader) {
        customShader = shader;
    }


    public void draw(GlTexture2D texture, float x, float y, float width, float height) {
        if(!scissor.intersects(x, y, width, height))
            return;
        if(size == maxSize)
            flush();

        if(texture != lastTexture){
            if(texture == null)
                return;
            flush();
            lastTexture = texture;
        }

        addTexturedQuad(x, y, width, height, 0F, 0F, 1F, 1F, color.r, color.g, color.b, color.a);
        size++;
    }

    public void draw(TextureRegion textureRegion, float x, float y, float width, float height) {
        if(!scissor.intersects(x, y, width, height))
            return;
        if(size == maxSize)
            flush();

        final GlTexture2D texture = textureRegion.getTexture();
        if(texture != lastTexture){
            if(texture == null)
                return;
            flush();
            lastTexture = texture;
        }

        addTexturedQuad(x, y, width, height,
            textureRegion.u1(), textureRegion.v1(), textureRegion.u2(), textureRegion.v2(),
            color.r, color.g, color.b, color.a);
        size++;
    }

    public void draw(GlTexture2D texture, float x, float y, float width, float height, Region region) {
        if(!scissor.intersects(x, y, width, height))
            return;
        if(size == maxSize)
            flush();

        if(texture != lastTexture){
            if(texture == null)
                return;
            flush();
            lastTexture = texture;
        }

        addTexturedQuad(x, y, width, height,
            region.u1(), region.v1(), region.u2(), region.v2(),
            color.r, color.g, color.b, color.a);
        size++;
    }

    public void draw(TextureRegion textureRegion, float x, float y, float width, float height, Region region) {
        if(!scissor.intersects(x, y, width, height))
            return;
        if(size == maxSize)
            flush();

        final GlTexture2D texture = textureRegion.getTexture();
        if(texture != lastTexture){
            if(texture == null)
                return;
            flush();
            lastTexture = texture;
        }

        final Region regionInRegion = Region.calcRegionInRegion(textureRegion, region);

        addTexturedQuad(x, y, width, height,
            regionInRegion.u1(), regionInRegion.v1(), regionInRegion.u2(), regionInRegion.v2(),
            color.r, color.g, color.b, color.a);
        size++;
    }

    public void draw(GlTexture2D texture, float x, float y, float width, float height, float r, float g, float b, float a) {
        if(!scissor.intersects(x, y, width, height))
            return;
        if(size == maxSize)
            flush();

        if(texture != lastTexture){
            if(texture == null)
                return;
            flush();
            lastTexture = texture;
        }

        addTexturedQuad(x, y, width, height, 0F, 0F, 1F, 1F, r, g, b, a);
        size++;
    }

    public void draw(GlTexture2D texture, float x, float y, float width, float height, Color color) {
        draw(texture, x, y, width, height, color.r, color.g, color.b, color.a);
    }

    public void draw(GlTexture2D texture, float x, float y, float width, float height, Region region, float r, float g, float b, float a) {
        if(!scissor.intersects(x, y, width, height))
            return;
        if(size == maxSize)
            flush();

        if(texture != lastTexture){
            if(texture == null)
                return;
            flush();
            lastTexture = texture;
        }

        addTexturedQuad(x, y, width, height,
            region.u1(), region.v1(), region.u2(), region.v2(),
            r, g, b, a);
        size++;
    }

    public void draw(GlTexture2D texture, float x, float y, float width, float height, Region region, Color color) {
        draw(texture, x, y, width, height, region, color.r, color.g, color.b, color.a);
    }

    public void draw(TextureRegion textureRegion, float x, float y, float width, float height, Region region, float r, float g, float b, float a) {
        if(!scissor.intersects(x, y, width, height))
            return;
        if(size == maxSize)
            flush();

        final GlTexture2D texture = textureRegion.getTexture();
        if(texture != lastTexture){
            if(texture == null)
                return;
            flush();
            lastTexture = texture;
        }

        final Region regionInRegion = Region.calcRegionInRegion(textureRegion, region);

        addTexturedQuad(x, y, width, height,
            regionInRegion.u1(), regionInRegion.v1(), regionInRegion.u2(), regionInRegion.v2(),
            r, g, b, a);
        size++;
    }

    public void draw(TextureRegion textureRegion, float x, float y, float width, float height, Region region, Color color) {
        draw(textureRegion, x, y, width, height, region, color.r, color.g, color.b, color.a);
    }

    public void drawRect(double r, double g, double b, double a, float x, float y, float width, float height) {
        draw(TextureUtils.whiteTexture(), x, y, width, height, (float) r, (float) g, (float) b, (float) a);
    }

    public void drawRect(Color color, float x, float y, float width, float height) {
        drawRect(color.r, color.g, color.b, color.a, x, y, width, height);
    }

    public void drawRect(double alpha, float x, float y, float width, float height) {
        drawRect(0F, 0F, 0F, alpha, x, y, width, height);
    }


    public int size() {
        return size;
    }


    public Scissor getScissor() {
        return scissor;
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

    public Color getColor() {
        return color;
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

    public void translate(float x, float y) {
        translateX = x;
        translateY = y;
    }

    public void rotate(double angle) {
        rotationMat.setRotation(angle);
    }

    public void shear(double angleX, double angleY) {
        shearMat.setShear(angleX, angleY);
    }

    public void scale(double scale) {
        scaleMat.setScale(scale);
    }

    public void scale(float x, float y) {
        scaleMat.setScale(x, y);
    }

    public void flip(boolean x, boolean y) {
        flipMat.val[Matrix3f.m00] = (y ? 1F : -1F);
        flipMat.val[Matrix3f.m11] = (x ? 1F : -1F);
    }


    @Override
    public void dispose() {
        shader.dispose();
        mesh.dispose();
    }

}
