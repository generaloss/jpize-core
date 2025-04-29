package jpize.android.opengl;

import android.opengl.*;
import android.opengl.GLES20;
import jpize.opengl.gl.GL32I;

import java.nio.*;

public class AndroidGL32 extends AndroidGL31 implements GL32I {

    @Override
    public void glGetBufferParameteri64v(int target, int pname, LongBuffer params) {
        GLES30.glGetBufferParameteri64v(target, pname, params);
    }

    @Override
    public long glGetBufferParameteri64(int target, int pname) {
        GLES30.glGetBufferParameteri64v(target, pname, tmp_long, 0);
        return tmp_long[0];
    }

    @Override
    public void glDrawElementsBaseVertex(int mode, int count, int type, long indices, int basevertex) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glDrawElementsBaseVertex(int mode, int type, ByteBuffer indices, int basevertex) {

        GLES32.glDrawElementsBaseVertex(mode, bufferCount(indices, type), type, indices, basevertex);
    }

    @Override
    public void glDrawElementsBaseVertex(int mode, ByteBuffer indices, int basevertex) {
        GLES32.glDrawElementsBaseVertex(mode, indices.limit(), GLES10.GL_BYTE, indices, basevertex);
    }

    @Override
    public void glDrawElementsBaseVertex(int mode, ShortBuffer indices, int basevertex) {
        GLES32.glDrawElementsBaseVertex(mode, indices.limit(), GLES10.GL_SHORT, indices, basevertex);
    }

    @Override
    public void glDrawElementsBaseVertex(int mode, IntBuffer indices, int basevertex) {
        GLES32.glDrawElementsBaseVertex(mode, indices.limit(), GLES20.GL_INT, indices, basevertex);
    }

    @Override
    public void glDrawRangeElementsBaseVertex(int mode, int start, int end, int count, int type, long indices, int basevertex) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glDrawRangeElementsBaseVertex(int mode, int start, int end, int type, ByteBuffer indices, int basevertex) {
        GLES32.glDrawRangeElementsBaseVertex(mode, start, end, bufferCount(indices, type), type, indices, basevertex);
    }

    @Override
    public void glDrawRangeElementsBaseVertex(int mode, int start, int end, ByteBuffer indices, int basevertex) {
        GLES32.glDrawRangeElementsBaseVertex(mode, start, end, indices.limit(), GLES10.GL_BYTE, indices, basevertex);
    }

    @Override
    public void glDrawRangeElementsBaseVertex(int mode, int start, int end, ShortBuffer indices, int basevertex) {
        GLES32.glDrawRangeElementsBaseVertex(mode, start, end, indices.limit(), GLES10.GL_SHORT, indices, basevertex);
    }

    @Override
    public void glDrawRangeElementsBaseVertex(int mode, int start, int end, IntBuffer indices, int basevertex) {
        GLES32.glDrawRangeElementsBaseVertex(mode, start, end, indices.limit(), GLES20.GL_INT, indices, basevertex);
    }

    @Override
    public void glDrawElementsInstancedBaseVertex(int mode, int count, int type, long indices, int primcount, int basevertex) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glDrawElementsInstancedBaseVertex(int mode, int type, ByteBuffer indices, int primcount, int basevertex) {
        GLES32.glDrawElementsInstancedBaseVertex(mode, bufferCount(indices, type), type, indices, primcount, basevertex);
    }

    @Override
    public void glDrawElementsInstancedBaseVertex(int mode, ByteBuffer indices, int primcount, int basevertex) {
        GLES32.glDrawElementsInstancedBaseVertex(mode, indices.limit(), GLES10.GL_BYTE, indices, primcount, basevertex);
    }

    @Override
    public void glDrawElementsInstancedBaseVertex(int mode, ShortBuffer indices, int primcount, int basevertex) {
        GLES32.glDrawElementsInstancedBaseVertex(mode, indices.limit(), GLES10.GL_SHORT, indices, primcount, basevertex);
    }

    @Override
    public void glDrawElementsInstancedBaseVertex(int mode, IntBuffer indices, int primcount, int basevertex) {
        GLES32.glDrawElementsInstancedBaseVertex(mode, indices.limit(), GLES20.GL_INT, indices, primcount, basevertex);
    }

    @Override
    public void glProvokingVertex(int mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexImage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) {
        GLES32.glTexStorage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations);
    }

    @Override
    public void glTexImage3DMultisample(int target, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) {
        GLES32.glTexStorage3DMultisample(target, samples, internalformat, width, height, depth, fixedsamplelocations);
    }

    @Override
    public void glGetMultisamplefv(int pname, int index, FloatBuffer val) {
        GLES32.glGetMultisamplefv(pname, index, val);
    }

    @Override
    public float glGetMultisamplef(int pname, int index) {
        GLES32.glGetMultisamplefv(pname, index, tmp_float, 0);
        return tmp_float[0];
    }

    @Override
    public void glSampleMaski(int index, int mask) {
        GLES32.glSampleMaski(index, mask);
    }

    @Override
    public void glFramebufferTexture(int target, int attachment, int texture, int level) {
        GLES32.glFramebufferTexture(target, attachment, texture, level);
    }

    @Override
    public long glFenceSync(int condition, int flags) {
        return GLES30.glFenceSync(condition, flags);
    }

    @Override
    public boolean glIsSync(long sync) {
        return GLES30.glIsSync(sync);
    }

    @Override
    public void glDeleteSync(long sync) {
        GLES30.glDeleteSync(sync);
    }

    @Override
    public int glClientWaitSync(long sync, int flags, long timeout) {
        return GLES30.glClientWaitSync(sync, flags, timeout);
    }

    @Override
    public void glWaitSync(long sync, int flags, long timeout) {
        GLES30.glWaitSync(sync, flags, timeout);
    }

    @Override
    public void glGetInteger64v(int pname, LongBuffer params) {
        GLES30.glGetInteger64v(pname, params);
    }

    @Override
    public long glGetInteger64(int pname) {
        GLES30.glGetInteger64v(pname, tmp_long, 0);
        return tmp_long[0];
    }

    @Override
    public void glGetInteger64i_v(int pname, int index, LongBuffer params) {
        GLES30.glGetInteger64i_v(pname, index, params);
    }

    @Override
    public long glGetInteger64i(int pname, int index) {
        GLES30.glGetInteger64i_v(pname, index, tmp_long, 0);
        return tmp_long[0];
    }

    @Override
    public void glGetSynciv(long sync, int pname, IntBuffer length, IntBuffer values) {
        GLES30.glGetSynciv(sync, pname, values.limit(), length, values);
    }

    @Override
    public int glGetSynci(long sync, int pname, IntBuffer length) {
        final int[] lengthArr = new int[1];
        GLES30.glGetSynciv(sync, pname, 1, lengthArr, 0, tmp_int, 0);
        length.put(lengthArr[0]);
        length.position(0);
        return tmp_int[0];
    }

    @Override
    public void glGetBufferParameteri64v(int target, int pname, long[] params) {
        GLES30.glGetBufferParameteri64v(target, pname, params, 0);
    }

    @Override
    public void glGetMultisamplefv(int pname, int index, float[] val) {
        GLES32.glGetMultisamplefv(pname, index, val, 0);
    }

    @Override
    public void glGetInteger64v(int pname, long[] params) {
        GLES30.glGetInteger64v(pname, params, 0);
    }

    @Override
    public void glGetInteger64i_v(int pname, int index, long[] params) {
        GLES30.glGetInteger64i_v(pname, index, params, 0);
    }

    @Override
    public void glGetSynciv(long sync, int pname, int[] length, int[] values) {
        GLES30.glGetSynciv(sync, pname, values.length, length, 0, values, 0);
    }

}