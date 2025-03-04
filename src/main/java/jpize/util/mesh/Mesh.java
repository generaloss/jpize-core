package jpize.util.mesh;

import jpize.opengl.tesselation.GlPrimitive;
import jpize.opengl.vertex.GlVertexArray;
import jpize.opengl.vertex.GlVertAttr;
import jpize.opengl.buffer.VertexBuffer;

public class Mesh implements IMesh {

    private final GlVertexArray vertexArray;
    private final VertexBuffer vertexBuffer;
    private GlPrimitive mode;

    public Mesh(GlVertAttr... attributes) {
        this.vertexArray = new GlVertexArray();
        this.vertexBuffer = new VertexBuffer();
        this.vertexBuffer.enableAttributes(attributes);
        this.mode = GlPrimitive.TRIANGLES;
    }

    public GlPrimitive getMode() {
        return mode;
    }

    public void setMode(GlPrimitive mode) {
        this.mode = mode;
    }


    public void render(int vertexCount) {
        vertexArray.drawArrays(vertexCount, mode);
    }

    @Override
    public void render() {
        this.render(vertexBuffer.getVertexCount());
    }


    @Override
    public VertexBuffer vertices() {
        return vertexBuffer;
    }

    @Override
    public void dispose() {
        vertexBuffer.dispose();
        vertexArray.dispose();
    }

}
