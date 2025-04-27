package jpize.opengl.buffer;

import jpize.context.Jpize;
import jpize.opengl.type.GlType;
import jpize.opengl.vertex.GlVertAttr;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public class GlVertexBuffer extends GlBuffer {

    private int vertexSize;
    private int vertexBytes;
    
    public GlVertexBuffer() {
        super(GlBufTarget.ARRAY_BUFFER);
    }


    public void vertexAttribPointer(int index, int size, GlType type, boolean normalized, int stride, long pointer) {
        Jpize.GL20.glVertexAttribPointer(index, size, type.value, normalized, stride, pointer);
    }

    public void vertexAttribPointer(int index, int size, GlType type, boolean normalized, int stride, IntBuffer pointer) {
        Jpize.GL20.glVertexAttribPointer(index, size, type.value, normalized, stride, pointer);
    }

    public void vertexAttribPointer(int index, int size, GlType type, boolean normalized, int stride, ByteBuffer pointer) {
        Jpize.GL20.glVertexAttribPointer(index, size, type.value, normalized, stride, pointer);
    }

    public void vertexAttribPointer(int index, int size, GlType type, boolean normalized, int stride, FloatBuffer pointer) {
        Jpize.GL20.glVertexAttribPointer(index, size, type.value, normalized, stride, pointer);
    }

    public void vertexAttribPointer(int index, int size, GlType type, boolean normalized, int stride, ShortBuffer pointer) {
        Jpize.GL20.glVertexAttribPointer(index, size, type.value, normalized, stride, pointer);
    }


    public void enableVertexAttribArray(int index) {
        Jpize.GL20.glEnableVertexAttribArray(index);
    }


    public void enableAttributes(GlVertAttr... attributes) {
        if(vertexSize != 0)
            throw new IllegalStateException("VertexBuffer.enableAttributes() must be called once");

        if(attributes.length == 0)
            throw new IllegalArgumentException("Attributes must not be empty");

        for(GlVertAttr attribute: attributes){
            vertexSize += attribute.getCount();
            vertexBytes += (attribute.getCount() * attribute.getType().bytes);
        }

        int pointer = 0;
        for(byte i = 0; i < attributes.length; i++){
            final GlVertAttr attribute = attributes[i];

            final int count = attribute.getCount();
            if(count < 1)
                throw new IllegalArgumentException("Attribute 'count' must be at least 1");

            final GlType type = attribute.getType();

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
        return (super.getSizeBytes() / vertexBytes);
    }

}
