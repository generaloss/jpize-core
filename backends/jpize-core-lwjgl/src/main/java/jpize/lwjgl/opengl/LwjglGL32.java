package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL32I;
import org.lwjgl.opengl.GL32;
import java.nio.*;

public class LwjglGL32 extends LwjglGL31 implements GL32I {

    public static final LwjglGL32 INSTANCE = new LwjglGL32();

    protected LwjglGL32() { }


    @Override
    public void glGetBufferParameteri64v(int target, int pname, LongBuffer params) {
        GL32.glGetBufferParameteri64v(target, pname, params);
    }

    @Override
    public long glGetBufferParameteri64(int target, int pname) {
        return GL32.glGetBufferParameteri64(target, pname);
    }

    @Override
    public void glDrawElementsBaseVertex(int mode, int count, int type, long indices, int basevertex) {
        GL32.glDrawElementsBaseVertex(mode, count, type, indices, basevertex);
    }

    @Override
    public void glDrawElementsBaseVertex(int mode, int type, ByteBuffer indices, int basevertex) {
        GL32.glDrawElementsBaseVertex(mode, type, indices, basevertex);
    }

    @Override
    public void glDrawElementsBaseVertex(int mode, ByteBuffer indices, int basevertex) {
        GL32.glDrawElementsBaseVertex(mode, indices, basevertex);
    }

    @Override
    public void glDrawElementsBaseVertex(int mode, ShortBuffer indices, int basevertex) {
        GL32.glDrawElementsBaseVertex(mode, indices, basevertex);
    }

    @Override
    public void glDrawElementsBaseVertex(int mode, IntBuffer indices, int basevertex) {
        GL32.glDrawElementsBaseVertex(mode, indices, basevertex);
    }

    @Override
    public void glDrawRangeElementsBaseVertex(int mode, int start, int end, int count, int type, long indices, int basevertex) {
        GL32.glDrawRangeElementsBaseVertex(mode, start, end, count, type, indices, basevertex);
    }

    @Override
    public void glDrawRangeElementsBaseVertex(int mode, int start, int end, int type, ByteBuffer indices, int basevertex) {
        GL32.glDrawRangeElementsBaseVertex(mode, start, end, type, indices, basevertex);
    }

    @Override
    public void glDrawRangeElementsBaseVertex(int mode, int start, int end, ByteBuffer indices, int basevertex) {
        GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices, basevertex);
    }

    @Override
    public void glDrawRangeElementsBaseVertex(int mode, int start, int end, ShortBuffer indices, int basevertex) {
        GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices, basevertex);
    }

    @Override
    public void glDrawRangeElementsBaseVertex(int mode, int start, int end, IntBuffer indices, int basevertex) {
        GL32.glDrawRangeElementsBaseVertex(mode, start, end, indices, basevertex);
    }

    @Override
    public void glDrawElementsInstancedBaseVertex(int mode, int count, int type, long indices, int primcount, int basevertex) {
        GL32.glDrawElementsInstancedBaseVertex(mode, count, type, indices, primcount, basevertex);
    }

    @Override
    public void glDrawElementsInstancedBaseVertex(int mode, int type, ByteBuffer indices, int primcount, int basevertex) {
        GL32.glDrawElementsInstancedBaseVertex(mode, type, indices, primcount, basevertex);
    }

    @Override
    public void glDrawElementsInstancedBaseVertex(int mode, ByteBuffer indices, int primcount, int basevertex) {
        GL32.glDrawElementsInstancedBaseVertex(mode, indices, primcount, basevertex);
    }

    @Override
    public void glDrawElementsInstancedBaseVertex(int mode, ShortBuffer indices, int primcount, int basevertex) {
        GL32.glDrawElementsInstancedBaseVertex(mode, indices, primcount, basevertex);
    }

    @Override
    public void glDrawElementsInstancedBaseVertex(int mode, IntBuffer indices, int primcount, int basevertex) {
        GL32.glDrawElementsInstancedBaseVertex(mode, indices, primcount, basevertex);
    }

    @Override
    public void glProvokingVertex(int mode) {
        GL32.glProvokingVertex(mode);
    }

    @Override
    public void glTexImage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) {
        GL32.glTexImage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations);
    }

    @Override
    public void glTexImage3DMultisample(int target, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) {
        GL32.glTexImage3DMultisample(target, samples, internalformat, width, height, depth, fixedsamplelocations);
    }

    @Override
    public void glGetMultisamplefv(int pname, int index, FloatBuffer val) {
        GL32.glGetMultisamplefv(pname, index, val);
    }

    @Override
    public float glGetMultisamplef(int pname, int index) {
        return GL32.glGetMultisamplef(pname, index);
    }

    @Override
    public void glSampleMaski(int index, int mask) {
        GL32.glSampleMaski(index, mask);
    }

    @Override
    public void glFramebufferTexture(int target, int attachment, int texture, int level) {
        GL32.glFramebufferTexture(target, attachment, texture, level);
    }

    @Override
    public long glFenceSync(int condition, int flags) {
        return GL32.glFenceSync(condition, flags);
    }

    @Override
    public boolean glIsSync(long sync) {
        return GL32.glIsSync(sync);
    }

    @Override
    public void glDeleteSync(long sync) {
        GL32.glDeleteSync(sync);
    }

    @Override
    public int glClientWaitSync(long sync, int flags, long timeout) {
        return GL32.glClientWaitSync(sync, flags, timeout);
    }

    @Override
    public void glWaitSync(long sync, int flags, long timeout) {
        GL32.glWaitSync(sync, flags, timeout);
    }

    @Override
    public void glGetInteger64v(int pname, LongBuffer params) {
        GL32.glGetInteger64v(pname, params);
    }

    @Override
    public long glGetInteger64(int pname) {
        return GL32.glGetInteger64(pname);
    }

    @Override
    public void glGetInteger64i_v(int pname, int index, LongBuffer params) {
        GL32.glGetInteger64i_v(pname, index, params);
    }

    @Override
    public long glGetInteger64i(int pname, int index) {
        return GL32.glGetInteger64i(pname, index);
    }

    @Override
    public void glGetSynciv(long sync, int pname, IntBuffer length, IntBuffer values) {
        GL32.glGetSynciv(sync, pname, length, values);
    }

    @Override
    public int glGetSynci(long sync, int pname, IntBuffer length) {
        return GL32.glGetSynci(sync, pname, length);
    }

    @Override
    public void glGetBufferParameteri64v(int target, int pname, long[] params) {
        GL32.glGetBufferParameteri64v(target, pname, params);
    }

    @Override
    public void glGetMultisamplefv(int pname, int index, float[] val) {
        GL32.glGetMultisamplefv(pname, index, val);
    }

    @Override
    public void glGetInteger64v(int pname, long[] params) {
        GL32.glGetInteger64v(pname, params);
    }

    @Override
    public void glGetInteger64i_v(int pname, int index, long[] params) {
        GL32.glGetInteger64i_v(pname, index, params);
    }

    @Override
    public void glGetSynciv(long sync, int pname, int[] length, int[] values) {
        GL32.glGetSynciv(sync, pname, length, values);
    }

}