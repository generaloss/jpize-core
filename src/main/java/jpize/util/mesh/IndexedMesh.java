package jpize.util.mesh;

import jpize.opengl.tesselation.GlPrimitive;
import jpize.opengl.vertex.GlVertexArray;
import jpize.opengl.vertex.GlVertAttr;
import jpize.opengl.buffer.IndexBuffer;
import jpize.opengl.buffer.VertexBuffer;

public class IndexedMesh implements IMesh {

    private final GlVertexArray vertexArray;
    private final VertexBuffer vertexBuffer;
    private final IndexBuffer indexBuffer;
    private GlPrimitive mode;

    public IndexedMesh(GlVertAttr... attributes) {
        this.mode = GlPrimitive.TRIANGLES;
        this.vertexArray = new GlVertexArray();

        this.vertexBuffer = new VertexBuffer();
        this.vertexBuffer.enableAttributes(attributes);

        this.indexBuffer = new IndexBuffer();
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
        this.render(indexBuffer.getIndexCount());
    }


    @Override
    public VertexBuffer vertices() {
        return vertexBuffer;
    }

    public IndexBuffer indices() {
        return indexBuffer;
    }

    @Override
    public void dispose() {
        vertexArray.dispose();
        vertexBuffer.dispose();
        indexBuffer.dispose();
    }

}
