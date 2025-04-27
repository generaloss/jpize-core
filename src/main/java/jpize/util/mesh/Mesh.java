package jpize.util.mesh;

import jpize.opengl.buffer.GlVertexBuffer;
import jpize.opengl.tesselation.GlPrimitive;
import jpize.opengl.vertex.GlVertexArray;
import jpize.opengl.vertex.GlVertAttr;

public class Mesh implements IMesh {

    private final GlVertexArray vertexArray;
    private final GlVertexBuffer vertexBuffer;
    private GlPrimitive mode;

    public Mesh(GlVertAttr... attributes) {
        this.vertexArray = new GlVertexArray();
        this.vertexArray.bind();

        this.vertexBuffer = new GlVertexBuffer();
        this.vertexBuffer.bind();
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
        this.render(vertexBuffer.getVerticesCount());
    }


    @Override
    public GlVertexBuffer vertices() {
        return vertexBuffer;
    }

    @Override
    public void dispose() {
        vertexBuffer.dispose();
        vertexArray.dispose();
    }

}
