package jpize.android.opengl;

import android.opengl.GLES32;
import jpize.opengl.gl.GL43I;

import java.nio.*;

public class AndroidGL43 extends AndroidGL42 implements GL43I {

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, ByteBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, ShortBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, IntBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, FloatBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, ByteBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, ShortBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, IntBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, FloatBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDispatchCompute(int num_groups_x, int num_groups_y, int num_groups_z) {
        GLES32.glDispatchCompute(num_groups_x, num_groups_y, num_groups_z);
    }

    @Override
    public void glDispatchComputeIndirect(long indirect) {
        GLES32.glDispatchComputeIndirect(indirect);
    }

    @Override
    public void glCopyImageSubData(int srcName, int srcTarget, int srcLevel, int srcX, int srcY, int srcZ, int dstName, int dstTarget, int dstLevel, int dstX, int dstY, int dstZ, int srcWidth, int srcHeight, int srcDepth) {
        GLES32.glCopyImageSubData(srcName, srcTarget, srcLevel, srcX, srcY, srcZ, dstName, dstTarget, dstLevel, dstX, dstY, dstZ, srcWidth, srcHeight, srcDepth);
    }

    @Override
    public void glDebugMessageControl(int source, int type, int severity, IntBuffer ids, boolean enabled) {
        GLES32.glDebugMessageControl(source, type, severity, ids.limit(), ids, enabled);
    }

    @Override
    public void glDebugMessageControl(int source, int type, int severity, int id, boolean enabled) {
        tmp_int[0] = id;
        GLES32.glDebugMessageControl(source, type, severity, 1, tmp_int, 0, enabled);
    }

    @Override
    public void glDebugMessageInsert(int source, int type, int id, int severity, ByteBuffer message) {
        GLES32.glDebugMessageInsert(source, type, id, severity, message.limit(), createString(message));
    }

    @Override
    public void glDebugMessageInsert(int source, int type, int id, int severity, CharSequence message) {
        GLES32.glDebugMessageInsert(source, type, id, severity, message.length(), message.toString());
    }

    @Override
    public void glDebugMessageCallback(GLDebugMessageCallback callback, long userParam) {
        GLES32.glDebugMessageCallback((int source, int type, int id, int severity, String message) ->
            callback.invoke(source, type, id, severity, message.length(), message, 0L)
        );
    }

    @Override
    public int glGetDebugMessageLog(int count, IntBuffer sources, IntBuffer types, IntBuffer ids, IntBuffer severities, IntBuffer lengths, ByteBuffer messageLog) {
        return GLES32.glGetDebugMessageLog(count, sources, types, ids, severities, lengths, messageLog);
    }

    @Override
    public void glPushDebugGroup(int source, int id, ByteBuffer message) {
        final String messageStr = createString(message);
        GLES32.glPushDebugGroup(source, id, messageStr.length(), messageStr);
    }

    @Override
    public void glPushDebugGroup(int source, int id, CharSequence message) {
        GLES32.glPushDebugGroup(source, id, message.length(), message.toString());
    }

    @Override
    public void glPopDebugGroup() {
        GLES32.glPopDebugGroup();
    }

    @Override
    public void glObjectLabel(int identifier, int name, ByteBuffer label) {
        final String labelStr = createString(label);
        GLES32.glObjectLabel(identifier, name, labelStr.length(), labelStr);
    }

    @Override
    public void glObjectLabel(int identifier, int name, CharSequence label) {
        GLES32.glObjectLabel(identifier, name, label.length(), label.toString());
    }

    @Override
    public void glGetObjectLabel(int identifier, int name, IntBuffer length, ByteBuffer label) {
        final String labelStr = GLES32.glGetObjectLabel(identifier, name);
        setBufferString(labelStr, length, label);
    }

    @Override
    public String glGetObjectLabel(int identifier, int name, int bufSize) { //! bufSize ignored
        return GLES32.glGetObjectLabel(identifier, name);
    }

    @Override
    public String glGetObjectLabel(int identifier, int name) {
        return GLES32.glGetObjectLabel(identifier, name);
    }

    @Override
    public void glObjectPtrLabel(long ptr, ByteBuffer label) {
        GLES32.glObjectPtrLabel(ptr, createString(label));
    }

    @Override
    public void glObjectPtrLabel(long ptr, CharSequence label) {
        GLES32.glObjectPtrLabel(ptr, label.toString());
    }

    @Override
    public void glGetObjectPtrLabel(long ptr, IntBuffer length, ByteBuffer label) {
        final String labelStr = GLES32.glGetObjectPtrLabel(ptr);
        setBufferString(labelStr, length, label);
    }

    @Override
    public String glGetObjectPtrLabel(long ptr, int bufSize) { //! bufSize ignored
        return GLES32.glGetObjectPtrLabel(ptr);
    }

    @Override
    public String glGetObjectPtrLabel(long ptr) {
        return GLES32.glGetObjectPtrLabel(ptr);
    }

    @Override
    public void glFramebufferParameteri(int target, int pname, int param) {
        GLES32.glFramebufferParameteri(target, pname, param);
    }

    @Override
    public void glGetFramebufferParameteriv(int target, int pname, IntBuffer params) {
        GLES32.glGetFramebufferParameteriv(target, pname, params);
    }

    @Override
    public int glGetFramebufferParameteri(int target, int pname) {
        GLES32.glGetFramebufferParameteriv(target, pname, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glGetInternalformati64v(int target, int internalformat, int pname, LongBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long glGetInternalformati64(int target, int internalformat, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glInvalidateTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glInvalidateTexImage(int texture, int level) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glInvalidateBufferSubData(int buffer, long offset, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glInvalidateBufferData(int buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glInvalidateFramebuffer(int target, IntBuffer attachments) {
        GLES32.glInvalidateFramebuffer(target, attachments.limit(), attachments);
    }

    @Override
    public void glInvalidateFramebuffer(int target, int attachment) {
        tmp_int[0] = attachment;
        GLES32.glInvalidateFramebuffer(target, 1, tmp_int, 0);
    }

    @Override
    public void glInvalidateSubFramebuffer(int target, IntBuffer attachments, int x, int y, int width, int height) {
        GLES32.glInvalidateSubFramebuffer(target, attachments.limit(), attachments, x, y, width, height);
    }

    @Override
    public void glInvalidateSubFramebuffer(int target, int attachment, int x, int y, int width, int height) {
        tmp_int[0] = attachment;
        GLES32.glInvalidateSubFramebuffer(target, 1, tmp_int, 0, x, y, width, height);
    }

    @Override
    public void glMultiDrawArraysIndirect(int mode, ByteBuffer indirect, int drawcount, int stride) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiDrawArraysIndirect(int mode, long indirect, int drawcount, int stride) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiDrawArraysIndirect(int mode, IntBuffer indirect, int drawcount, int stride) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiDrawElementsIndirect(int mode, int type, ByteBuffer indirect, int drawcount, int stride) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiDrawElementsIndirect(int mode, int type, long indirect, int drawcount, int stride) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiDrawElementsIndirect(int mode, int type, IntBuffer indirect, int drawcount, int stride) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetProgramInterfaceiv(int program, int programInterface, int pname, IntBuffer params) {
        GLES32.glGetProgramInterfaceiv(program, programInterface, pname, params);
    }

    @Override
    public int glGetProgramInterfacei(int program, int programInterface, int pname) {
        GLES32.glGetProgramInterfaceiv(program, programInterface, pname, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public int glGetProgramResourceIndex(int program, int programInterface, ByteBuffer name) {
        return GLES32.glGetProgramResourceIndex(program, programInterface, createString(name));
    }

    @Override
    public int glGetProgramResourceIndex(int program, int programInterface, CharSequence name) {
        return GLES32.glGetProgramResourceIndex(program, programInterface, name.toString());
    }

    @Override
    public void glGetProgramResourceName(int program, int programInterface, int index, IntBuffer length, ByteBuffer name) {
        final String nameStr = GLES32.glGetProgramResourceName(program, programInterface, index);
        setBufferString(nameStr, length, name);
    }

    @Override
    public String glGetProgramResourceName(int program, int programInterface, int index, int bufSize) { //! bufSize ignored
        return GLES32.glGetProgramResourceName(program, programInterface, index);
    }

    @Override
    public String glGetProgramResourceName(int program, int programInterface, int index) {
        return GLES32.glGetProgramResourceName(program, programInterface, index);
    }

    @Override
    public void glGetProgramResourceiv(int program, int programInterface, int index, IntBuffer props, IntBuffer length, IntBuffer params) {
        GLES32.glGetProgramResourceiv(program, programInterface, index, props.limit(), props, length.limit(), length, params);
    }

    @Override
    public int glGetProgramResourceLocation(int program, int programInterface, ByteBuffer name) {
        return GLES32.glGetProgramResourceLocation(program, programInterface, createString(name));
    }

    @Override
    public int glGetProgramResourceLocation(int program, int programInterface, CharSequence name) {
        return GLES32.glGetProgramResourceLocation(program, programInterface, name.toString());
    }

    @Override
    public int glGetProgramResourceLocationIndex(int program, int programInterface, ByteBuffer name) {
        return GLES32.glGetProgramResourceLocation(program, programInterface, createString(name));
    }

    @Override
    public int glGetProgramResourceLocationIndex(int program, int programInterface, CharSequence name) {
        return GLES32.glGetProgramResourceLocation(program, programInterface, name.toString());
    }

    @Override
    public void glShaderStorageBlockBinding(int program, int storageBlockIndex, int storageBlockBinding) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexBufferRange(int target, int internalformat, int buffer, long offset, long size) {
        GLES32.glTexBufferRange(target, internalformat, buffer, (int) offset, (int) size);
    }

    @Override
    public void glTexStorage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) {
        GLES32.glTexStorage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations);
    }

    @Override
    public void glTexStorage3DMultisample(int target, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) {
        GLES32.glTexStorage3DMultisample(target, samples, internalformat, width, height, depth, fixedsamplelocations);
    }

    @Override
    public void glTextureView(int texture, int target, int origtexture, int internalformat, int minlevel, int numlevels, int minlayer, int numlayers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glBindVertexBuffer(int bindingindex, int buffer, long offset, int stride) {
        GLES32.glBindVertexBuffer(bindingindex, buffer, offset, stride);
    }

    @Override
    public void glVertexAttribFormat(int attribindex, int size, int type, boolean normalized, int relativeoffset) {
        GLES32.glVertexAttribFormat(attribindex, size, type, normalized, relativeoffset);
    }

    @Override
    public void glVertexAttribIFormat(int attribindex, int size, int type, int relativeoffset) {
        GLES32.glVertexAttribIFormat(attribindex, size, type, relativeoffset);
    }

    @Override
    public void glVertexAttribLFormat(int attribindex, int size, int type, int relativeoffset) {
        GLES32.glVertexAttribIFormat(attribindex, size, type, relativeoffset);
    }

    @Override
    public void glVertexAttribBinding(int attribindex, int bindingindex) {
        GLES32.glVertexAttribBinding(attribindex, bindingindex);
    }

    @Override
    public void glVertexBindingDivisor(int bindingindex, int divisor) {
        GLES32.glVertexBindingDivisor(bindingindex, divisor);
    }

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, short[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, short[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDebugMessageControl(int source, int type, int severity, int[] ids, boolean enabled) {
        GLES32.glDebugMessageControl(source, type, severity, ids.length, ids, 0, enabled);
    }

    @Override
    public int glGetDebugMessageLog(int count, int[] sources, int[] types, int[] ids, int[] severities, int[] lengths, ByteBuffer messageLog) {
        final String[] messageLogStr = GLES32.glGetDebugMessageLog(count, sources, 0, types, 0, ids, 0, severities, 0);
        return setBufferStrings(count, messageLogStr, lengths, messageLog);
    }

    @Override
    public void glGetObjectLabel(int identifier, int name, int[] length, ByteBuffer label) {
        final String labelStr = GLES32.glGetObjectLabel(identifier, name);
        setBufferString(labelStr, length, label);
    }

    @Override
    public void glGetObjectPtrLabel(long ptr, int[] length, ByteBuffer label) {
        final String labelStr = GLES32.glGetObjectPtrLabel(ptr);
        setBufferString(labelStr, length, label);
    }

    @Override
    public void glGetFramebufferParameteriv(int target, int pname, int[] params) {
        GLES32.glGetFramebufferParameteriv(target, pname, params, 0);
    }

    @Override
    public void glGetInternalformati64v(int target, int internalformat, int pname, long[] params) {
        final int[] paramsArr = new int[params.length];
        GLES32.glGetInternalformativ(target, internalformat, pname, params.length, paramsArr, 0);
        writeToArray(paramsArr, params);
    }

    @Override
    public void glInvalidateFramebuffer(int target, int[] attachments) {
        GLES32.glInvalidateFramebuffer(target, attachments.length, attachments, 0);
    }

    @Override
    public void glInvalidateSubFramebuffer(int target, int[] attachments, int x, int y, int width, int height) {
        GLES32.glInvalidateSubFramebuffer(target, attachments.length, attachments, 0, x, y, width, height);
    }

    @Override
    public void glMultiDrawArraysIndirect(int mode, int[] indirect, int drawcount, int stride) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiDrawElementsIndirect(int mode, int type, int[] indirect, int drawcount, int stride) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetProgramInterfaceiv(int program, int programInterface, int pname, int[] params) {
        GLES32.glGetProgramInterfaceiv(program, programInterface, pname, params, 0);
    }

    @Override
    public void glGetProgramResourceName(int program, int programInterface, int index, int[] length, ByteBuffer name) {
        final String nameStr = GLES32.glGetProgramResourceName(program, programInterface, index);
        setBufferString(nameStr, length, name);
    }

    @Override
    public void glGetProgramResourceiv(int program, int programInterface, int index, int[] props, int[] length, int[] params) {
        GLES32.glGetProgramResourceiv(program, programInterface, index, props.length, props, 0, params.length, length, 0, params, 0);
    }

}