package jpize.opengl.buffer;

import jpize.context.Jpize;
import jpize.opengl.type.GLType;
import jpize.opengl.vertex.GLVertAttr;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public class GLVertexBuffer extends GLBuffer {

    private int vertexSize;
    private int vertexBytes;
    
    public GLVertexBuffer() {
        super(GLBufTarget.ARRAY_BUFFER);
    }


    public void vertexAttribPointer(int index, int size, GLType type, boolean normalized, int stride, long pointer) {
        Jpize.GL20.glVertexAttribPointer(index, size, type.value, normalized, stride, pointer);
    }

    public void vertexAttribPointer(int index, int size, GLType type, boolean normalized, int stride, IntBuffer pointer) {
        Jpize.GL20.glVertexAttribPointer(index, size, type.value, normalized, stride, pointer);
    }

    public void vertexAttribPointer(int index, int size, GLType type, boolean normalized, int stride, ByteBuffer pointer) {
        Jpize.GL20.glVertexAttribPointer(index, size, type.value, normalized, stride, pointer);
    }

    public void vertexAttribPointer(int index, int size, GLType type, boolean normalized, int stride, FloatBuffer pointer) {
        Jpize.GL20.glVertexAttribPointer(index, size, type.value, normalized, stride, pointer);
    }

    public void vertexAttribPointer(int index, int size, GLType type, boolean normalized, int stride, ShortBuffer pointer) {
        Jpize.GL20.glVertexAttribPointer(index, size, type.value, normalized, stride, pointer);
    }


    public void enableVertexAttribArray(int index) {
        Jpize.GL20.glEnableVertexAttribArray(index);
    }


    public void enableAttributes(GLVertAttr... attributes) {
        if(vertexSize != 0)
            throw new IllegalStateException("VertexBuffer.enableAttributes() must be called once");

        if(attributes.length == 0)
            throw new IllegalArgumentException("Attributes must not be empty");

        for(GLVertAttr attribute: attributes){
            vertexSize += attribute.getCount();
            vertexBytes += (attribute.getCount() * attribute.getType().bytes);
        }

        int pointer = 0;
        for(byte i = 0; i < attributes.length; i++){
            final GLVertAttr attribute = attributes[i];

            final int count = attribute.getCount();
            if(count < 1)
                throw new IllegalArgumentException("Attribute 'count' must be at least 1");

            final GLType type = attribute.getType();

            this.vertexAttribPointer(i, count, type, attribute.isNormalized(), vertexBytes, pointer);
            this.enableVertexAttribArray(i);

            pointer += (count * type.bytes);
        }
    }


    public int getVertexSize() {
        return vertexSize;
    }

    public int getVertexBytes() {
        return vertexBytes;
    }

    public int getVerticesCount() {
        if(vertexBytes == 0)
            return 0;
        return (super.getSizeBytes() / vertexBytes);
    }

}
