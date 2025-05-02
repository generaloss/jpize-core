package jpize.util.mesh;

import jpize.context.Jpize;
import jpize.opengl.shader.Shader;
import jpize.opengl.tesselation.GLPrimitive;
import jpize.opengl.type.GLType;
import jpize.opengl.vertex.GLVertAttr;
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
    private final Shader defaultShader;
    private Shader currentShader;
    private final Color color;
    private Matrix4f combinedMat;
    // vertex
    private final Color vertexColor;
    // data
    private int size;
    private final FloatList vertexList;
    // transform
    private final Vec2f position;
    private boolean roundVertices;
    // tmp
    private Matrix4f tmp_combinedMat;
    private final Vec2f tmp_vertex;

    public VertexBatch(GLPrimitive mode) {
        // mesh
        this.mesh = new Mesh(
                new GLVertAttr(2, GLType.FLOAT), // position
                new GLVertAttr(4, GLType.FLOAT)  // color
        );
        this.mesh.setMode(mode);
        this.vertexList = new FloatList();
        // shader
        this.defaultShader = new Shader(
            Resource.internal("/shader/vertex_batch/vert.glsl"),
            Resource.internal("/shader/vertex_batch/frag.glsl")
        );
        this.setShader(defaultShader);
        // vertex
        this.vertexColor = new Color();
        // transform
        this.color = new Color();
        this.position = new Vec2f();
        // tmp
        this.tmp_vertex = new Vec2f();
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
        if(tmp_combinedMat == null)
            tmp_combinedMat = new Matrix4f();
        tmp_combinedMat.setOrthographic(0F, 0F, Jpize.getWidth(), Jpize.getHeight());
        this.setup(tmp_combinedMat);
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
        if(size == 0)
            return;
        if(combinedMat == null)
            throw new IllegalStateException("No matrix found. Call TextureBatch.setup() first");

        // render
        currentShader.bind();
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


    private void drawVertexColorless(float x, float y) {
        tmp_vertex.set(x, y).add(position);
        if(roundVertices)
            tmp_vertex.round();

        vertexList.add(
            tmp_vertex.x, tmp_vertex.y,
            vertexColor.red, vertexColor.green, vertexColor.blue, vertexColor.alpha
        );
        size++;
    }

    public void drawVertex(float x, float y, AbstractColor color) {
        vertexColor.set(color);
        this.drawVertexColorless(x, y);
    }

    public void drawVertex(float x, float y, double r, double g, double b, double a) {
        vertexColor.set(r, g, b, a);
        this.drawVertexColorless(x, y);
    }

    public void drawVertex(float x, float y, double r, double g, double b) {
        vertexColor.set(r, g, b);
        this.drawVertexColorless(x, y);
    }

    public void drawVertexRGBA(float x, float y, int color) {
        vertexColor.setRGBA(color);
        this.drawVertexColorless(x, y);
    }

    public void drawVertexRGB(float x, float y, int color) {
        vertexColor.setRGB(color);
        this.drawVertexColorless(x, y);
    }

    public void drawVertex(Vec2f position, AbstractColor color) {
        vertexColor.set(color);
        this.drawVertexColorless(position.x, position.y);
    }

    public void drawVertex(Vec2f position, double r, double g, double b, double a) {
        vertexColor.set(r, g, b, a);
        this.drawVertexColorless(position.x, position.y);
    }

    public void drawVertex(Vec2f position, double r, double g, double b) {
        vertexColor.set(r, g, b);
        this.drawVertexColorless(position.x, position.y);
    }

    public void drawVertexRGBA(Vec2f position, int color) {
        vertexColor.setRGBA(color);
        this.drawVertexColorless(position.x, position.y);
    }

    public void drawVertexRGB(Vec2f position, int color) {
        vertexColor.setRGB(color);
        this.drawVertexColorless(position.x, position.y);
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

    public void setColorRGB(int color) {
        this.color.setRGB(color);
    }

    public void setColorRGBA(int color) {
        this.color.setRGBA(color);
    }

    public void setAlpha(double alpha) {
        color.setAlpha(alpha);
    }


    public Vec2f position() {
        return position;
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
