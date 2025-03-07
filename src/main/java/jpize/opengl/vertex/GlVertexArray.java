package jpize.opengl.vertex;

import jpize.context.Jpize;
import jpize.opengl.GlObject;
import jpize.opengl.tesselation.GlPrimitive;
import jpize.opengl.type.GlIndexType;

public class GlVertexArray extends GlObject {

    public GlVertexArray() {
        super(Jpize.GL30.glGenVertexArrays());
        this.bind();
    }


    public void drawArrays(int verticesNum, GlPrimitive mode) {
        this.bind();
        Jpize.GL11.glDrawArrays(mode.value, 0, verticesNum);
    }

    public void drawArrays(int verticesNum) {
        this.drawArrays(verticesNum, GlPrimitive.TRIANGLES);
    }


    public void drawElements(int indicesNum, GlPrimitive mode, GlIndexType indexType) {
        this.bind();
        Jpize.GL11.glDrawElements(mode.value, indicesNum, indexType.value, 0L);
    }

    public void drawElements(int indicesNum, GlPrimitive mode) {
        this.drawElements(indicesNum, mode, GlIndexType.UNSIGNED_INT);
    }

    public void drawElements(int indicesNum) {
        this.drawElements(indicesNum, GlPrimitive.TRIANGLES);
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