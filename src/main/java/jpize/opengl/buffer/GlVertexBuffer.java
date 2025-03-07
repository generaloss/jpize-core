package jpize.opengl.buffer;

import jpize.context.Jpize;
import jpize.opengl.type.GlType;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public class GlVertexBuffer extends GlBuffer {

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

}
