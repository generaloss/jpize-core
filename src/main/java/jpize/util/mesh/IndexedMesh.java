package jpize.util.mesh;

import jpize.opengl.buffer.GLIndexBuffer;
import jpize.opengl.tesselation.GLPrimitive;
import jpize.opengl.vertex.GLVertexArray;
import jpize.opengl.vertex.GLVertAttr;
import jpize.opengl.buffer.GLVertexBuffer;

public class IndexedMesh implements IMesh {

    private final GLVertexArray vertexArray;
    private final GLVertexBuffer vertexBuffer;
    private final GLIndexBuffer indexBuffer;
    private GLPrimitive mode;

    public IndexedMesh(GLVertAttr... attributes) {
        this.vertexArray = new GLVertexArray();
        this.vertexArray.bind();

        this.vertexBuffer = new GLVertexBuffer();
        this.vertexBuffer.bind();
        this.vertexBuffer.enableAttributes(attributes);

        this.indexBuffer = new GLIndexBuffer();

        this.mode = GLPrimitive.TRIANGLES;
    }

    public GLPrimitive getMode() {
        return mode;
    }

    public void setMode(GLPrimitive mode) {
        this.mode = mode;
    }


    public void render(int indices) {
        vertexArray.drawElements(indices, mode);
    }

    @Override
    public void render() {
        this.render(indexBuffer.getSizeElements());
    }


    @Override
    public GLVertexBuffer vertices() {
        return vertexBuffer;
    }

    public GLIndexBuffer indices() {
        return indexBuffer;
    }

    @Override
    public void dispose() {
        vertexArray.dispose();
        vertexBuffer.dispose();
        indexBuffer.dispose();
    }

}
