package jpize.opengl.vertex;

import jpize.opengl.GlObject;
import jpize.opengl.tesselation.GlPrimitive;
import jpize.opengl.type.GlIndexType;

import static org.lwjgl.opengl.GL33.*;

public class GlVertexArray extends GlObject {

    public GlVertexArray() {
        super(glGenVertexArrays());
        bind();
    }


    public void drawArrays(int verticesNum, GlPrimitive mode) {
        bind();
        glDrawArrays(mode.value, 0, verticesNum);
    }

    public void drawArrays(int verticesNum) {
        drawArrays(verticesNum, GlPrimitive.TRIANGLES);
    }


    public void drawElements(int indicesNum, GlPrimitive mode, GlIndexType indexType) {
        bind();
        glDrawElements(mode.value, indicesNum, indexType.value, 0);
    }

    public void drawElements(int indicesNum, GlPrimitive mode) {
        drawElements(indicesNum, mode, GlIndexType.UNSIGNED_INT);
    }

    public void drawElements(int indicesNum) {
        drawElements(indicesNum, GlPrimitive.TRIANGLES);
    }

    public void bind() {
        glBindVertexArray(ID);
    }

    public static void unbind() {
        glBindVertexArray(0);
    }


    @Override
    public void dispose() {
        glDeleteVertexArrays(ID);
    }

}