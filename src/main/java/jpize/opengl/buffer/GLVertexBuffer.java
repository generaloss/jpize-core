package jpize.opengl.buffer;

import jpize.context.Jpize;
import jpize.opengl.type.GLType;
import jpize.opengl.vertex.GLVertAttr;

import java.nio.*;

public class GLVertexBuffer extends GLBuffer {

    private int vertexSize;
    private int vertexBytes;
    
    public GLVertexBuffer() {
        super(GLBufferTarget.ARRAY);
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


    public void vertexAttribIPointer(int index, int size, GLType type, int stride, long pointer) {
        Jpize.GL30.glVertexAttribIPointer(index, size, type.value, stride, pointer);
    }

    public void vertexAttribIPointer(int index, int size, GLType type, int stride, IntBuffer pointer) {
        Jpize.GL30.glVertexAttribIPointer(index, size, type.value, stride, pointer);
    }

    public void vertexAttribIPointer(int index, int size, GLType type, int stride, ByteBuffer pointer) {
        Jpize.GL30.glVertexAttribIPointer(index, size, type.value, stride, pointer);
    }

    public void vertexAttribIPointer(int index, int size, GLType type, int stride, ShortBuffer pointer) {
        Jpize.GL30.glVertexAttribIPointer(index, size, type.value, stride, pointer);
    }


    public void vertexAttribLPointer(int index, int size, GLType type, int stride, long pointer) {
        Jpize.GL45.glVertexAttribLPointer(index, size, type.value, stride, pointer);
    }

    public void vertexAttribLPointer(int index, int size, GLType type, int stride, ByteBuffer pointer) {
        Jpize.GL41.glVertexAttribLPointer(index, size, type.value, stride, pointer);
    }

    public void vertexAttribLPointer(int index, int size, int stride, DoubleBuffer pointer) {
        Jpize.GL41.glVertexAttribLPointer(index, size, stride, pointer);
    }


    public void getVertexAttribPointer(int index, int pname) {
        Jpize.GL41.glGetVertexAttribPointer(index, pname);
    }


    public void enableVertexAttribArray(int index) {
        Jpize.GL20.glEnableVertexAttribArray(index);
    }


    public void enableAttributes(GLVertAttr... attributes) {
        if(vertexSize != 0)
            throw new IllegalStateException("VertexBuffer.enableAttributes() must be called once");
        if(attributes == null)
            throw new IllegalArgumentException("Argument 'attributes' cannot be null");
        if(attributes.length == 0)
            throw new IllegalArgumentException("Attributes must not be empty");

        for(GLVertAttr attribute: attributes){
            vertexSize += attribute.getCount();
            vertexBytes += (attribute.getCount() * attribute.getType().bytes);
        }

        long pointer = 0;
        for(int i = 0; i < attributes.length; i++){
            final GLVertAttr attribute = attributes[i];

            final int count = attribute.getCount();
            if(count < 1)
                throw new IllegalArgumentException("Attribute 'count' must be at least 1");

            final GLType type = attribute.getType();

            if (type.isInteger() && !attribute.isNormalized()) {
                this.vertexAttribIPointer(i, count, type, vertexBytes, pointer);
            } else if (type == GLType.DOUBLE) {
                this.vertexAttribLPointer(i, count, type, vertexBytes, pointer);
            } else {
                this.vertexAttribPointer(i, count, type, attribute.isNormalized(), vertexBytes, pointer);
            }

            this.enableVertexAttribArray(i);

            pointer += ((long) count * type.bytes);
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
