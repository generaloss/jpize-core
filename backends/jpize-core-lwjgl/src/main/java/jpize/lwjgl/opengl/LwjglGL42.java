package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL42I;
import org.lwjgl.opengl.GL42;
import java.nio.*;

public class LwjglGL42 extends LwjglGL41 implements GL42I {

    public static final LwjglGL42 INSTANCE = new LwjglGL42();

    protected LwjglGL42() { }


    @Override
    public void glGetActiveAtomicCounterBufferiv(int program, int bufferIndex, int pname, IntBuffer params) {
        GL42.glGetActiveAtomicCounterBufferiv(program, bufferIndex, pname, params);
    }

    @Override
    public int glGetActiveAtomicCounterBufferi(int program, int bufferIndex, int pname) {
        return GL42.glGetActiveAtomicCounterBufferi(program, bufferIndex, pname);
    }

    @Override
    public void glTexStorage1D(int target, int levels, int internalformat, int width) {
        GL42.glTexStorage1D(target, levels, internalformat, width);
    }

    @Override
    public void glTexStorage2D(int target, int levels, int internalformat, int width, int height) {
        GL42.glTexStorage2D(target, levels, internalformat, width, height);
    }

    @Override
    public void glTexStorage3D(int target, int levels, int internalformat, int width, int height, int depth) {
        GL42.glTexStorage3D(target, levels, internalformat, width, height, depth);
    }

    @Override
    public void glDrawTransformFeedbackInstanced(int mode, int id, int primcount) {
        GL42.glDrawTransformFeedbackInstanced(mode, id, primcount);
    }

    @Override
    public void glDrawTransformFeedbackStreamInstanced(int mode, int id, int stream, int primcount) {
        GL42.glDrawTransformFeedbackStreamInstanced(mode, id, stream, primcount);
    }

    @Override
    public void glDrawArraysInstancedBaseInstance(int mode, int first, int count, int primcount, int baseinstance) {
        GL42.glDrawArraysInstancedBaseInstance(mode, first, count, primcount, baseinstance);
    }

    @Override
    public void glDrawElementsInstancedBaseInstance(int mode, int count, int type, long indices, int primcount, int baseinstance) {
        GL42.glDrawElementsInstancedBaseInstance(mode, count, type, indices, primcount, baseinstance);
    }

    @Override
    public void glDrawElementsInstancedBaseInstance(int mode, int type, ByteBuffer indices, int primcount, int baseinstance) {
        GL42.glDrawElementsInstancedBaseInstance(mode, type, indices, primcount, baseinstance);
    }

    @Override
    public void glDrawElementsInstancedBaseInstance(int mode, ByteBuffer indices, int primcount, int baseinstance) {
        GL42.glDrawElementsInstancedBaseInstance(mode, indices, primcount, baseinstance);
    }

    @Override
    public void glDrawElementsInstancedBaseInstance(int mode, ShortBuffer indices, int primcount, int baseinstance) {
        GL42.glDrawElementsInstancedBaseInstance(mode, indices, primcount, baseinstance);
    }

    @Override
    public void glDrawElementsInstancedBaseInstance(int mode, IntBuffer indices, int primcount, int baseinstance) {
        GL42.glDrawElementsInstancedBaseInstance(mode, indices, primcount, baseinstance);
    }

    @Override
    public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, int count, int type, long indices, int primcount, int basevertex, int baseinstance) {
        GL42.glDrawElementsInstancedBaseVertexBaseInstance(mode, count, type, indices, primcount, basevertex, baseinstance);
    }

    @Override
    public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, int type, ByteBuffer indices, int primcount, int basevertex, int baseinstance) {
        GL42.glDrawElementsInstancedBaseVertexBaseInstance(mode, type, indices, primcount, basevertex, baseinstance);
    }

    @Override
    public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, ByteBuffer indices, int primcount, int basevertex, int baseinstance) {
        GL42.glDrawElementsInstancedBaseVertexBaseInstance(mode, indices, primcount, basevertex, baseinstance);
    }

    @Override
    public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, ShortBuffer indices, int primcount, int basevertex, int baseinstance) {
        GL42.glDrawElementsInstancedBaseVertexBaseInstance(mode, indices, primcount, basevertex, baseinstance);
    }

    @Override
    public void glDrawElementsInstancedBaseVertexBaseInstance(int mode, IntBuffer indices, int primcount, int basevertex, int baseinstance) {
        GL42.glDrawElementsInstancedBaseVertexBaseInstance(mode, indices, primcount, basevertex, baseinstance);
    }

    @Override
    public void glBindImageTexture(int unit, int texture, int level, boolean layered, int layer, int access, int format) {
        GL42.glBindImageTexture(unit, texture, level, layered, layer, access, format);
    }

    @Override
    public void glMemoryBarrier(int barriers) {
        GL42.glMemoryBarrier(barriers);
    }

    @Override
    public void glGetInternalformativ(int target, int internalformat, int pname, IntBuffer params) {
        GL42.glGetInternalformativ(target, internalformat, pname, params);
    }

    @Override
    public int glGetInternalformati(int target, int internalformat, int pname) {
        return GL42.glGetInternalformati(target, internalformat, pname);
    }

    @Override
    public void glGetActiveAtomicCounterBufferiv(int program, int bufferIndex, int pname, int[] params) {
        GL42.glGetActiveAtomicCounterBufferiv(program, bufferIndex, pname, params);
    }

    @Override
    public void glGetInternalformativ(int target, int internalformat, int pname, int[] params) {
        GL42.glGetInternalformativ(target, internalformat, pname, params);
    }

}