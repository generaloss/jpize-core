package jpize.util.mesh;

import jpize.app.Jpize;
import jpize.gl.shader.Shader;
import jpize.gl.tesselation.GlPrimitive;
import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertAttr;
import jpize.util.Disposable;
import jpize.util.camera.Camera;
import jpize.util.color.Color;
import jpize.util.color.ImmutableColor;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2f;
import jpize.util.res.Resource;

public class VertexBatch implements Disposable {

    private final Mesh mesh;
    private final Shader shader;
    private final Color color;
    private Matrix4f combinedMat;
    private Shader customShader;
    // data
    private final int maxSize, vertexBytes;
    private int size, vertexBufferOffset;
    public final float[] tmpVertexData;
    // transform
    private final Vec2f position;

    public VertexBatch(int maxSize, GlPrimitive mode) {
        this.maxSize = maxSize;
        this.color = new Color();

        // shader
        this.shader = new Shader(
            Resource.internal("/shader/vertex_batch/vert.glsl"),
            Resource.internal("/shader/vertex_batch/frag.glsl")
        );

        // mesh
        this.mesh = new Mesh(
            new GlVertAttr(2, GlType.FLOAT), // position
            new GlVertAttr(4, GlType.FLOAT)  // color
        );
        this.mesh.setMode(mode);

        // get vertex size
        final int vertexSize = mesh.vertices().getVertexSize();
        this.vertexBytes = mesh.vertices().getVertexBytes();

        // allocate buffers
        this.tmpVertexData = new float[vertexSize];
        this.mesh.vertices().allocateData(maxSize * vertexBytes);

        // matrices
        this.position = new Vec2f();
    }

    public VertexBatch(GlPrimitive mode) {
        this(2048, mode);
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
        if(size == 0 || combinedMat == null)
            return;

        // shader
        final Shader currentShader = (customShader != null) ? customShader : shader;
        currentShader.bind();
        currentShader.uniform("u_combined", combinedMat);

        // render
        mesh.render(size);

        // reset
        size = 0;
        vertexBufferOffset = 0;
    }


    public void addVertex(float x, float y, float r, float g, float b, float a) {
        if(size == maxSize)
            this.render();

        tmpVertexData[0] = (x + position.x);
        tmpVertexData[1] = (y + position.y);

        tmpVertexData[2] = r;
        tmpVertexData[3] = g;
        tmpVertexData[4] = b;
        tmpVertexData[5] = a;

        mesh.vertices().setSubData(vertexBufferOffset, tmpVertexData);
        vertexBufferOffset += vertexBytes;
        size++;
    }

    public void addVertex(float x, float y, Color color) {
        this.addVertex(x, y, color.r, color.g, color.b, color.a);
    }

    public void addVertex(float x, float y, ImmutableColor color) {
        this.addVertex(x, y, color.r, color.g, color.b, color.a);
    }

    public void addVertex(Vec2f position, float r, float g, float b, float a) {
        this.addVertex(position.x, position.y, r, g, b, a);
    }

    public void addVertex(Vec2f position, Color color) {
        this.addVertex(position.x, position.y, color.r, color.g, color.b, color.a);
    }

    public void addVertex(Vec2f position, ImmutableColor color) {
        this.addVertex(position.x, position.y, color.r, color.g, color.b, color.a);
    }


    public Mesh mesh() {
        return mesh;
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


    public Vec2f position() {
        return position;
    }


    @Override
    public void dispose() {
        shader.dispose();
        mesh.dispose();
    }

}
