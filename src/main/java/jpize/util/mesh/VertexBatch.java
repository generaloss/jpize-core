package jpize.util.mesh;

import jpize.app.Jpize;
import jpize.gl.shader.Shader;
import jpize.gl.tesselation.GlPrimitive;
import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertAttr;
import jpize.util.Disposable;
import jpize.util.array.FloatList;
import jpize.util.camera.Camera;
import jpize.util.color.Color;
import jpize.util.color.AbstractColor;
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
    private int size;
    private final FloatList vertexList;
    // transform
    private final Vec2f position;

    public VertexBatch(GlPrimitive mode) {
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
        this.vertexList = new FloatList();
        // state
        this.color = new Color();
        this.position = new Vec2f();
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

    public void render(boolean clearCache) {
        if(size == 0 || combinedMat == null)
            return;

        // shader
        final Shader currentShader = (customShader != null) ? customShader : shader;
        currentShader.bind();
        currentShader.uniform("u_combined", combinedMat);

        // render
        mesh.vertices().setData(vertexList.arrayTrimmed());
        mesh.render(size);

        // reset
        vertexList.clear();
        if(clearCache)
            vertexList.trim();
        size = 0;
    }

    public void render() {
        this.render(false);
    }


    public void addVertex(float x, float y, float r, float g, float b, float a) {
        vertexList.add(x + position.x, y + position.y,  r, g, b, a);
        size++;
    }

    public void addVertex(float x, float y, AbstractColor color) {
        this.addVertex(x, y, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public void addVertex(Vec2f position, float r, float g, float b, float a) {
        this.addVertex(position.x, position.y, r, g, b, a);
    }

    public void addVertex(Vec2f position, AbstractColor color) {
        this.addVertex(position.x, position.y,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
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

    public void setAlpha(double alpha) {
        color.setAlpha(alpha);
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
