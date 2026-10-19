package jpize.opengl.vertex;

import jpize.context.Jpize;
import jpize.opengl.GLObject;
import jpize.opengl.buffer.GLIndexBuffer;
import jpize.opengl.tesselation.GLPrimitive;
import jpize.opengl.type.GLIndexType;

public class GLVertexArray extends GLObject {

    public GLVertexArray() {
        super(Jpize.GL30.glGenVertexArrays());
    }


    public void drawArrays(int verticesCount, GLPrimitive mode) {
        this.bind();
        Jpize.GL11.glDrawArrays(mode.value, 0, verticesCount);
        unbind();
    }

    public void drawArrays(int verticesCount) {
        this.drawArrays(verticesCount, GLPrimitive.TRIANGLES);
    }


    public void drawElements(long offsetBytes, int indicesCount, GLPrimitive mode, GLIndexType indexType) {
        this.bind();
        Jpize.GL11.glDrawElements(mode.value, indicesCount, indexType.type.value, offsetBytes); // 0L ?
        unbind();
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