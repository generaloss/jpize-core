package jpize.android.opengl;

import android.opengl.GLES31;
import jpize.opengl.gl.GL42I;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public class AndroidGL42 extends AndroidGL41 implements GL42I {

    public static final AndroidGL42 INSTANCE = new AndroidGL42();

    protected AndroidGL42() { }


    @Override
    public void glGetActiveAtomicCounterBufferiv(int program, int bufferIndex, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetActiveAtomicCounterBufferi(int program, int bufferIndex, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexStorage1D(int target, int levels, int internalformat, int width) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexStorage2D(int target, int levels, int internalformat, int width, int height) {
        GLES31.glTexStorage2D(target, levels, internalformat, width, height);
    }

    @Override
    public void glTexStorage3D(int target, int levels, int internalformat, int width, int height, int depth) {
        GLES31.glTexStorage3D(target, levels, internalformat, width, height, depth);
    }

    @Override
    public void glDrawTransformFeedbackInstanced(int mode, int id, int primcount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawTransformFeedbackStreamInstanced(int mode, int id, int stream, int primcount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawArraysInstancedBaseInstance(int mode, int first, int count, int primcount, int baseinstance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawElementsInstancedBaseInstance(int mode, int count, int type, long indices, int primcount, int baseinstance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawElementsInstancedBaseInstance(int mode, int type, ByteBuffer indices, int primcount, int baseinstance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawElementsInstancedBaseInstance(int mode, ByteBuffer indices, int primcount, int baseinstance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawElementsInstancedBaseInstance(int mode, ShortBuffer indices, int primcount, int baseinstance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawElementsInstancedBaseInstance(int mode, IntBuffer indices, int primcount, int baseinstance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, int count, int type, long indices, int primcount, int basevertex, int baseinstance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, int type, ByteBuffer indices, int primcount, int basevertex, int baseinstance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, ByteBuffer indices, int primcount, int basevertex, int baseinstance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, ShortBuffer indices, int primcount, int basevertex, int baseinstance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, IntBuffer indices, int primcount, int basevertex, int baseinstance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glBindImageTexture(int unit, int texture, int level, boolean layered, int layer, int access, int format) {
        GLES31.glBindImageTexture(unit, texture, level, layered, layer, access, format);
    }

    @Override
    public void glMemoryBarrier(int barriers) {
        GLES31.glMemoryBarrier(barriers);
    }

    @Override
    public void glGetInternalformativ(int target, int internalformat, int pname, IntBuffer params) {
        GLES31.glGetInternalformativ(target, internalformat, pname, params.limit(), params);
    }

    @Override
    public int glGetInternalformati(int target, int internalformat, int pname) {
        GLES31.glGetInternalformativ(target, internalformat, pname, 1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glGetActiveAtomicCounterBufferiv(int program, int bufferIndex, int pname, int[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetInternalformativ(int target, int internalformat, int pname, int[] params) {
        GLES31.glGetInternalformativ(target, internalformat, pname, params.length, params, 0);
    }

}