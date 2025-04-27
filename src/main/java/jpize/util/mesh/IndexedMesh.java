package jpize.util.mesh;

import jpize.opengl.buffer.GlIndexBuffer;
import jpize.opengl.tesselation.GlPrimitive;
import jpize.opengl.vertex.GlVertexArray;
import jpize.opengl.vertex.GlVertAttr;
import jpize.opengl.buffer.GlVertexBuffer;

public class IndexedMesh implements IMesh {

    private final GlVertexArray vertexArray;
    private final GlVertexBuffer vertexBuffer;
    private final GlIndexBuffer indexBuffer;
    private GlPrimitive mode;

    public IndexedMesh(GlVertAttr... attributes) {
        this.vertexArray = new GlVertexArray();
        this.vertexArray.bind();

        this.vertexBuffer = new GlVertexBuffer();
        this.vertexBuffer.bind();
        this.vertexBuffer.enableAttributes(attributes);

        this.indexBuffer = new GlIndexBuffer();

        this.mode = GlPrimitive.TRIANGLES;
    }

    public GlPrimitive getMode() {
        return mode;
    }

    public void setMode(GlPrimitive mode) {
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
    public GlVertexBuffer vertices() {
        return vertexBuffer;
    }

    public GlIndexBuffer indices() {
        return indexBuffer;
    }

    @Override
    public void dispose() {
        vertexArray.dispose();
        vertexBuffer.dispose();
        indexBuffer.dispose();
    }

}
