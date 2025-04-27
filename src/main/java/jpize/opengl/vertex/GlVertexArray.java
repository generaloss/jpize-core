package jpize.opengl.vertex;

import jpize.context.Jpize;
import jpize.opengl.GlObject;
import jpize.opengl.tesselation.GlPrimitive;
import jpize.opengl.type.GlIndexType;

public class GlVertexArray extends GlObject {

    public GlVertexArray() {
        super(Jpize.GL30.glGenVertexArrays());
    }


    public void drawArrays(int verticesCount, GlPrimitive mode) {
        this.bind();
        Jpize.GL11.glDrawArrays(mode.value, 0, verticesCount);
    }

    public void drawArrays(int verticesCount) {
        this.drawArrays(verticesCount, GlPrimitive.TRIANGLES);
    }


    public void drawElements(int indicesCount, GlPrimitive mode, GlIndexType indexType) {
        this.bind();
        Jpize.GL11.glDrawElements(mode.value, indicesCount, indexType.type.value, 0L);
    }

    public void drawElements(int indicesCount, GlPrimitive mode) {
        this.drawElements(indicesCount, mode, GlIndexType.UNSIGNED_INT);
    }

    public void drawElements(int indicesCount) {
        this.drawElements(indicesCount, GlPrimitive.TRIANGLES);
    }

    public void bind() {
        Jpize.GL30.glBindVertexArray(ID);
    }

    public static void unbind() {
        Jpize.GL30.glBindVertexArray(0);
    }


    @Override
    public void dispose() {
        Jpize.GL30.glDeleteVertexArrays(ID);
    }

}