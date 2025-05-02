package jpize.util.mesh;

import jpize.opengl.buffer.GLVertexBuffer;
import jpize.opengl.tesselation.GLPrimitive;
import jpize.opengl.vertex.GLVertexArray;
import jpize.opengl.vertex.GLVertAttr;

public class Mesh implements IMesh {

    private final GLVertexArray vertexArray;
    private final GLVertexBuffer vertexBuffer;
    private GLPrimitive mode;

    public Mesh(GLVertAttr... attributes) {
        this.vertexArray = new GLVertexArray();
        this.vertexArray.bind();

        this.vertexBuffer = new GLVertexBuffer();
        this.vertexBuffer.bind();
        this.vertexBuffer.enableAttributes(attributes);

        this.mode = GLPrimitive.TRIANGLES;
    }

    public GLPrimitive getMode() {
        return mode;
    }

    public void setMode(GLPrimitive mode) {
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
    public GLVertexBuffer vertices() {
        return vertexBuffer;
    }

    @Override
    public void dispose() {
        vertexBuffer.dispose();
        vertexArray.dispose();
    }

}
