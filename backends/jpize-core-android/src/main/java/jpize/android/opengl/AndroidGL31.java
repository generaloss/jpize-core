package jpize.android.opengl;

import android.opengl.*;
import jpize.opengl.gl.GL31I;
import jpize.util.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public class AndroidGL31 extends AndroidGL30 implements GL31I {

    @Override
    public void glDrawArraysInstanced(int mode, int first, int count, int primcount) {
        GLES30.glDrawArraysInstanced(mode, first, count, primcount);
    }

    @Override
    public void glDrawElementsInstanced(int mode, int count, int type, long indices, int primcount) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glDrawElementsInstanced(int mode, int type, ByteBuffer indices, int primcount) {
        GLES30.glDrawElementsInstanced(mode, indices.limit(), type, indices, primcount);
    }

    @Override
    public void glDrawElementsInstanced(int mode, ByteBuffer indices, int primcount) {
        GLES30.glDrawElementsInstanced(mode, indices.limit(), GLES10.GL_BYTE, indices, primcount);
    }

    @Override
    public void glDrawElementsInstanced(int mode, ShortBuffer indices, int primcount) {
        GLES30.glDrawElementsInstanced(mode, indices.limit(), GLES10.GL_SHORT, indices, primcount);
    }

    @Override
    public void glDrawElementsInstanced(int mode, IntBuffer indices, int primcount) {
        GLES30.glDrawElementsInstanced(mode, indices.limit(), GLES20.GL_INT, indices, primcount);
    }

    @Override
    public void glCopyBufferSubData(int readTarget, int writeTarget, long readOffset, long writeOffset, long size) {
        GLES30.glCopyBufferSubData(readTarget, writeTarget, (int) readOffset, (int) writeOffset, (int) size);
    }

    @Override
    public void glPrimitiveRestartIndex(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexBuffer(int target, int internalformat, int buffer) {
        GLES32.glTexBuffer(target, internalformat, buffer);
    }

    @Override
    public void glGetUniformIndices(int program, CharSequence[] uniformNames, IntBuffer uniformIndices) {
        GLES30.glGetUniformIndices(program, createStringArray(uniformNames), uniformIndices);
    }

    @Override
    public int glGetUniformIndices(int program, CharSequence uniformName) {
        GLES30.glGetUniformIndices(program, createStringArray(uniformName), tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glGetActiveUniformsiv(int program, IntBuffer uniformIndices, int pname, IntBuffer params) {
        GLES30.glGetActiveUniformsiv(program, uniformIndices.limit(), uniformIndices, pname, params);
    }

    @Override
    public int glGetActiveUniformsi(int program, int uniformIndex, int pname) {
        tmp_int_arg[0] = uniformIndex;
        GLES30.glGetActiveUniformsiv(program, 1, tmp_int_arg, 0, pname, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glGetActiveUniformName(int program, int uniformIndex, IntBuffer length, ByteBuffer uniformName) {
        final int[] lengthArr = new int[length.limit()];
        final byte[] nameArr = new byte[uniformName.limit()];
        GLES20.glGetActiveUniform(program, uniformIndex, uniformName.limit(), lengthArr, 0, null, 0, null, 0, nameArr, 0);
        length.put(lengthArr);
        length.position(0);
        uniformName.put(nameArr);
        uniformName.position(0);
    }

    @Override
    public String glGetActiveUniformName(int program, int uniformIndex, int bufSize) {
        final byte[] nameArr = new byte[bufSize];
        GLES20.glGetActiveUniform(program, uniformIndex, bufSize, null, 0, null, 0, null, 0, nameArr, 0);
        return createString(nameArr);
    }

    @Override
    public String glGetActiveUniformName(int program, int uniformIndex) {
        return GLES20.glGetActiveUniform(program, uniformIndex, null, 0, null, 0);
    }

    @Override
    public int glGetUniformBlockIndex(int program, ByteBuffer uniformBlockName) {
        return GLES30.glGetUniformBlockIndex(program, createString(uniformBlockName));
    }

    @Override
    public int glGetUniformBlockIndex(int program, CharSequence uniformBlockName) {
        return GLES30.glGetUniformBlockIndex(program, uniformBlockName.toString());
    }

    @Override
    public void glGetActiveUniformBlockiv(int program, int uniformBlockIndex, int pname, IntBuffer params) {
        GLES30.glGetActiveUniformBlockiv(program, uniformBlockIndex, pname, params);
    }

    @Override
    public int glGetActiveUniformBlocki(int program, int uniformBlockIndex, int pname) {
        GLES30.glGetActiveUniformBlockiv(program, uniformBlockIndex, pname, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glGetActiveUniformBlockName(int program, int uniformBlockIndex, IntBuffer length, ByteBuffer uniformBlockName) {
        GLES30.glGetActiveUniformBlockName(program, uniformBlockIndex, length, uniformBlockName);
    }

    @Override
    public String glGetActiveUniformBlockName(int program, int uniformBlockIndex, int bufSize) {
        final byte[] nameArr = new byte[bufSize];
        GLES30.glGetActiveUniformBlockName(program, uniformBlockIndex, 1, null, 0, nameArr, 0);
        return createString(nameArr);
    }

    @Override
    public String glGetActiveUniformBlockName(int program, int uniformBlockIndex) {
        return GLES30.glGetActiveUniformBlockName(program, uniformBlockIndex);
    }

    @Override
    public void glUniformBlockBinding(int program, int uniformBlockIndex, int uniformBlockBinding) {
        GLES30.glUniformBlockBinding(program, uniformBlockIndex, uniformBlockBinding);
    }

    @Override
    public void glGetActiveUniformsiv(int program, int[] uniformIndices, int pname, int[] params) {
        GLES30.glGetActiveUniformsiv(program, uniformIndices.length, uniformIndices, 0, pname, params, 0);
    }

    @Override
    public void glGetActiveUniformName(int program, int uniformIndex, int[] length, ByteBuffer uniformName) {
        final byte[] nameArr = new byte[uniformName.limit()];
        GLES20.glGetActiveUniform(program, uniformIndex, uniformName.limit(), length, 0, null, 0, null, 0, nameArr, 0);
        uniformName.put(nameArr);
        uniformName.position(0);
    }

    @Override
    public void glGetActiveUniformBlockiv(int program, int uniformBlockIndex, int pname, int[] params) {
        final IntBuffer paramsBuf = BufferUtils.createIntBuffer(params.length);
        GLES30.glGetActiveUniformBlockiv(program, uniformBlockIndex, pname, paramsBuf);
        paramsBuf.get(params);
    }

    @Override
    public void glGetActiveUniformBlockName(int program, int uniformBlockIndex, int[] length, ByteBuffer uniformBlockName) {
        GLES30.glGetActiveUniformBlockName(program, uniformBlockIndex, createBuffer(length), uniformBlockName);
    }

}