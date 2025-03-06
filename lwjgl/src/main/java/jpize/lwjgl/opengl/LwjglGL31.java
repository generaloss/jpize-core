package jpize.lwjgl.opengl;

import jpize.opengl.gl.GLI31;
import org.lwjgl.opengl.GL31;
import java.nio.*;

public class LwjglGL31 extends LwjglGL30 implements GLI31 {

    @Override
    public void glDrawArraysInstanced(int mode, int first, int count, int primcount) {
        GL31.glDrawArraysInstanced(mode, first, count, primcount);
    }

    @Override
    public void nglDrawElementsInstanced(int mode, int count, int type, long indices, int primcount) {
        GL31.nglDrawElementsInstanced(mode, count, type, indices, primcount);
    }

    @Override
    public void glDrawElementsInstanced(int mode, int count, int type, long indices, int primcount) {
        GL31.glDrawElementsInstanced(mode, count, type, indices, primcount);
    }

    @Override
    public void glDrawElementsInstanced(int mode, int type, ByteBuffer indices, int primcount) {
        GL31.glDrawElementsInstanced(mode, type, indices, primcount);
    }

    @Override
    public void glDrawElementsInstanced(int mode, ByteBuffer indices, int primcount) {
        GL31.glDrawElementsInstanced(mode, indices, primcount);
    }

    @Override
    public void glDrawElementsInstanced(int mode, ShortBuffer indices, int primcount) {
        GL31.glDrawElementsInstanced(mode, indices, primcount);
    }

    @Override
    public void glDrawElementsInstanced(int mode, IntBuffer indices, int primcount) {
        GL31.glDrawElementsInstanced(mode, indices, primcount);
    }

    @Override
    public void glCopyBufferSubData(int readTarget, int writeTarget, long readOffset, long writeOffset, long size) {
        GL31.glCopyBufferSubData(readTarget, writeTarget, readOffset, writeOffset, size);
    }

    @Override
    public void glPrimitiveRestartIndex(int index) {
        GL31.glPrimitiveRestartIndex(index);
    }

    @Override
    public void glTexBuffer(int target, int internalformat, int buffer) {
        GL31.glTexBuffer(target, internalformat, buffer);
    }

    @Override
    public void nglGetUniformIndices(int program, int uniformCount, long uniformNames, long uniformIndices) {
        GL31.nglGetUniformIndices(program, uniformCount, uniformNames, uniformIndices);
    }

    @Override
    public void glGetUniformIndices(int program, CharSequence[] uniformNames, IntBuffer uniformIndices) {
        GL31.glGetUniformIndices(program, uniformNames, uniformIndices);
    }

    @Override
    public int glGetUniformIndices(int program, CharSequence uniformName) {
        return GL31.glGetUniformIndices(program, uniformName);
    }

    @Override
    public void nglGetActiveUniformsiv(int program, int uniformCount, long uniformIndices, int pname, long params) {
        GL31.nglGetActiveUniformsiv(program, uniformCount, uniformIndices, pname, params);
    }

    @Override
    public void glGetActiveUniformsiv(int program, IntBuffer uniformIndices, int pname, IntBuffer params) {
        GL31.glGetActiveUniformsiv(program, uniformIndices, pname, params);
    }

    @Override
    public int glGetActiveUniformsi(int program, int uniformIndex, int pname) {
        return GL31.glGetActiveUniformsi(program, uniformIndex, pname);
    }

    @Override
    public void nglGetActiveUniformName(int program, int uniformIndex, int bufSize, long length, long uniformName) {
        GL31.nglGetActiveUniformName(program, uniformIndex, bufSize, length, uniformName);
    }

    @Override
    public void glGetActiveUniformName(int program, int uniformIndex, IntBuffer length, ByteBuffer uniformName) {
        GL31.glGetActiveUniformName(program, uniformIndex, length, uniformName);
    }

    @Override
    public String glGetActiveUniformName(int program, int uniformIndex, int bufSize) {
        return GL31.glGetActiveUniformName(program, uniformIndex, bufSize);
    }

    @Override
    public String glGetActiveUniformName(int program, int uniformIndex) {
        return GL31.glGetActiveUniformName(program, uniformIndex);
    }

    @Override
    public int nglGetUniformBlockIndex(int program, long uniformBlockName) {
        return GL31.nglGetUniformBlockIndex(program, uniformBlockName);
    }

    @Override
    public int glGetUniformBlockIndex(int program, ByteBuffer uniformBlockName) {
        return GL31.glGetUniformBlockIndex(program, uniformBlockName);
    }

    @Override
    public int glGetUniformBlockIndex(int program, CharSequence uniformBlockName) {
        return GL31.glGetUniformBlockIndex(program, uniformBlockName);
    }

    @Override
    public void nglGetActiveUniformBlockiv(int program, int uniformBlockIndex, int pname, long params) {
        GL31.nglGetActiveUniformBlockiv(program, uniformBlockIndex, pname, params);
    }

    @Override
    public void glGetActiveUniformBlockiv(int program, int uniformBlockIndex, int pname, IntBuffer params) {
        GL31.glGetActiveUniformBlockiv(program, uniformBlockIndex, pname, params);
    }

    @Override
    public int glGetActiveUniformBlocki(int program, int uniformBlockIndex, int pname) {
        return GL31.glGetActiveUniformBlocki(program, uniformBlockIndex, pname);
    }

    @Override
    public void nglGetActiveUniformBlockName(int program, int uniformBlockIndex, int bufSize, long length, long uniformBlockName) {
        GL31.nglGetActiveUniformBlockName(program, uniformBlockIndex, bufSize, length, uniformBlockName);
    }

    @Override
    public void glGetActiveUniformBlockName(int program, int uniformBlockIndex, IntBuffer length, ByteBuffer uniformBlockName) {
        GL31.glGetActiveUniformBlockName(program, uniformBlockIndex, length, uniformBlockName);
    }

    @Override
    public String glGetActiveUniformBlockName(int program, int uniformBlockIndex, int bufSize) {
        return GL31.glGetActiveUniformBlockName(program, uniformBlockIndex, bufSize);
    }

    @Override
    public String glGetActiveUniformBlockName(int program, int uniformBlockIndex) {
        return GL31.glGetActiveUniformBlockName(program, uniformBlockIndex);
    }

    @Override
    public void glUniformBlockBinding(int program, int uniformBlockIndex, int uniformBlockBinding) {
        GL31.glUniformBlockBinding(program, uniformBlockIndex, uniformBlockBinding);
    }

    @Override
    public void glGetActiveUniformsiv(int program, int[] uniformIndices, int pname, int[] params) {
        GL31.glGetActiveUniformsiv(program, uniformIndices, pname, params);
    }

    @Override
    public void glGetActiveUniformName(int program, int uniformIndex, int[] length, ByteBuffer uniformName) {
        GL31.glGetActiveUniformName(program, uniformIndex, length, uniformName);
    }

    @Override
    public void glGetActiveUniformBlockiv(int program, int uniformBlockIndex, int pname, int[] params) {
        GL31.glGetActiveUniformBlockiv(program, uniformBlockIndex, pname, params);
    }

    @Override
    public void glGetActiveUniformBlockName(int program, int uniformBlockIndex, int[] length, ByteBuffer uniformBlockName) {
        GL31.glGetActiveUniformBlockName(program, uniformBlockIndex, length, uniformBlockName);
    }

}