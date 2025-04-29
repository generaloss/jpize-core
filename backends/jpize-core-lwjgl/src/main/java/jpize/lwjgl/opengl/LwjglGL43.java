package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL43I;
import org.lwjgl.opengl.GL43;
import org.lwjgl.system.MemoryUtil;

import java.nio.*;

public class LwjglGL43 extends LwjglGL42 implements GL43I {

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, ByteBuffer data) {
        GL43.glClearBufferData(target, internalformat, format, type, data);
    }

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, ShortBuffer data) {
        GL43.glClearBufferData(target, internalformat, format, type, data);
    }

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, IntBuffer data) {
        GL43.glClearBufferData(target, internalformat, format, type, data);
    }

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, FloatBuffer data) {
        GL43.glClearBufferData(target, internalformat, format, type, data);
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, ByteBuffer data) {
        GL43.glClearBufferSubData(target, internalformat, offset, size, format, type, data);
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, ShortBuffer data) {
        GL43.glClearBufferSubData(target, internalformat, offset, size, format, type, data);
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, IntBuffer data) {
        GL43.glClearBufferSubData(target, internalformat, offset, size, format, type, data);
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, FloatBuffer data) {
        GL43.glClearBufferSubData(target, internalformat, offset, size, format, type, data);
    }

    @Override
    public void glDispatchCompute(int num_groups_x, int num_groups_y, int num_groups_z) {
        GL43.glDispatchCompute(num_groups_x, num_groups_y, num_groups_z);
    }

    @Override
    public void glDispatchComputeIndirect(long indirect) {
        GL43.glDispatchComputeIndirect(indirect);
    }

    @Override
    public void glCopyImageSubData(int srcName, int srcTarget, int srcLevel, int srcX, int srcY, int srcZ, int dstName, int dstTarget, int dstLevel, int dstX, int dstY, int dstZ, int srcWidth, int srcHeight, int srcDepth) {
        GL43.glCopyImageSubData(srcName, srcTarget, srcLevel, srcX, srcY, srcZ, dstName, dstTarget, dstLevel, dstX, dstY, dstZ, srcWidth, srcHeight, srcDepth);
    }

    @Override
    public void glDebugMessageControl(int source, int type, int severity, IntBuffer ids, boolean enabled) {
        GL43.glDebugMessageControl(source, type, severity, ids, enabled);
    }

    @Override
    public void glDebugMessageControl(int source, int type, int severity, int id, boolean enabled) {
        GL43.glDebugMessageControl(source, type, severity, id, enabled);
    }

    @Override
    public void glDebugMessageInsert(int source, int type, int id, int severity, ByteBuffer message) {
        GL43.glDebugMessageInsert(source, type, id, severity, message);
    }

    @Override
    public void glDebugMessageInsert(int source, int type, int id, int severity, CharSequence message) {
        GL43.glDebugMessageInsert(source, type, id, severity, message);
    }

    @Override
    public void glDebugMessageCallback(GLDebugMessageCallback callback, long userParam) {
        GL43.glDebugMessageCallback((source, type, id, severity, length, messagePoniter, c_userParam) -> {
            final String message = MemoryUtil.memUTF8(messagePoniter);
            callback.invoke(source, type, id, severity, length, message, c_userParam);
        }, userParam);
    }

    @Override
    public int glGetDebugMessageLog(int count, IntBuffer sources, IntBuffer types, IntBuffer ids, IntBuffer severities, IntBuffer lengths, ByteBuffer messageLog) {
        return GL43.glGetDebugMessageLog(count, sources, types, ids, severities, lengths, messageLog);
    }

    @Override
    public void glPushDebugGroup(int source, int id, ByteBuffer message) {
        GL43.glPushDebugGroup(source, id, message);
    }

    @Override
    public void glPushDebugGroup(int source, int id, CharSequence message) {
        GL43.glPushDebugGroup(source, id, message);
    }

    @Override
    public void glPopDebugGroup() {
        GL43.glPopDebugGroup();
    }

    @Override
    public void glObjectLabel(int identifier, int name, ByteBuffer label) {
        GL43.glObjectLabel(identifier, name, label);
    }

    @Override
    public void glObjectLabel(int identifier, int name, CharSequence label) {
        GL43.glObjectLabel(identifier, name, label);
    }

    @Override
    public void glGetObjectLabel(int identifier, int name, IntBuffer length, ByteBuffer label) {
        GL43.glGetObjectLabel(identifier, name, length, label);
    }

    @Override
    public String glGetObjectLabel(int identifier, int name, int bufSize) {
        return GL43.glGetObjectLabel(identifier, name, bufSize);
    }

    @Override
    public String glGetObjectLabel(int identifier, int name) {
        return GL43.glGetObjectLabel(identifier, name);
    }

    @Override
    public void glObjectPtrLabel(long ptr, ByteBuffer label) {
        GL43.glObjectPtrLabel(ptr, label);
    }

    @Override
    public void glObjectPtrLabel(long ptr, CharSequence label) {
        GL43.glObjectPtrLabel(ptr, label);
    }

    @Override
    public void glGetObjectPtrLabel(long ptr, IntBuffer length, ByteBuffer label) {
        GL43.glGetObjectPtrLabel(ptr, length, label);
    }

    @Override
    public String glGetObjectPtrLabel(long ptr, int bufSize) {
        return GL43.glGetObjectPtrLabel(ptr, bufSize);
    }

    @Override
    public String glGetObjectPtrLabel(long ptr) {
        return GL43.glGetObjectPtrLabel(ptr);
    }

    @Override
    public void glFramebufferParameteri(int target, int pname, int param) {
        GL43.glFramebufferParameteri(target, pname, param);
    }

    @Override
    public void glGetFramebufferParameteriv(int target, int pname, IntBuffer params) {
        GL43.glGetFramebufferParameteriv(target, pname, params);
    }

    @Override
    public int glGetFramebufferParameteri(int target, int pname) {
        return GL43.glGetFramebufferParameteri(target, pname);
    }

    @Override
    public void glGetInternalformati64v(int target, int internalformat, int pname, LongBuffer params) {
        GL43.glGetInternalformati64v(target, internalformat, pname, params);
    }

    @Override
    public long glGetInternalformati64(int target, int internalformat, int pname) {
        return GL43.glGetInternalformati64(target, internalformat, pname);
    }

    @Override
    public void glInvalidateTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth) {
        GL43.glInvalidateTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth);
    }

    @Override
    public void glInvalidateTexImage(int texture, int level) {
        GL43.glInvalidateTexImage(texture, level);
    }

    @Override
    public void glInvalidateBufferSubData(int buffer, long offset, long length) {
        GL43.glInvalidateBufferSubData(buffer, offset, length);
    }

    @Override
    public void glInvalidateBufferData(int buffer) {
        GL43.glInvalidateBufferData(buffer);
    }

    @Override
    public void glInvalidateFramebuffer(int target, IntBuffer attachments) {
        GL43.glInvalidateFramebuffer(target, attachments);
    }

    @Override
    public void glInvalidateFramebuffer(int target, int attachment) {
        GL43.glInvalidateFramebuffer(target, attachment);
    }

    @Override
    public void glInvalidateSubFramebuffer(int target, IntBuffer attachments, int x, int y, int width, int height) {
        GL43.glInvalidateSubFramebuffer(target, attachments, x, y, width, height);
    }

    @Override
    public void glInvalidateSubFramebuffer(int target, int attachment, int x, int y, int width, int height) {
        GL43.glInvalidateSubFramebuffer(target, attachment, x, y, width, height);
    }

    @Override
    public void glMultiDrawArraysIndirect(int mode, ByteBuffer indirect, int drawcount, int stride) {
        GL43.glMultiDrawArraysIndirect(mode, indirect, drawcount, stride);
    }

    @Override
    public void glMultiDrawArraysIndirect(int mode, long indirect, int drawcount, int stride) {
        GL43.glMultiDrawArraysIndirect(mode, indirect, drawcount, stride);
    }

    @Override
    public void glMultiDrawArraysIndirect(int mode, IntBuffer indirect, int drawcount, int stride) {
        GL43.glMultiDrawArraysIndirect(mode, indirect, drawcount, stride);
    }

    @Override
    public void glMultiDrawElementsIndirect(int mode, int type, ByteBuffer indirect, int drawcount, int stride) {
        GL43.glMultiDrawElementsIndirect(mode, type, indirect, drawcount, stride);
    }

    @Override
    public void glMultiDrawElementsIndirect(int mode, int type, long indirect, int drawcount, int stride) {
        GL43.glMultiDrawElementsIndirect(mode, type, indirect, drawcount, stride);
    }

    @Override
    public void glMultiDrawElementsIndirect(int mode, int type, IntBuffer indirect, int drawcount, int stride) {
        GL43.glMultiDrawElementsIndirect(mode, type, indirect, drawcount, stride);
    }

    @Override
    public void glGetProgramInterfaceiv(int program, int programInterface, int pname, IntBuffer params) {
        GL43.glGetProgramInterfaceiv(program, programInterface, pname, params);
    }

    @Override
    public int glGetProgramInterfacei(int program, int programInterface, int pname) {
        return GL43.glGetProgramInterfacei(program, programInterface, pname);
    }

    @Override
    public int glGetProgramResourceIndex(int program, int programInterface, ByteBuffer name) {
        return GL43.glGetProgramResourceIndex(program, programInterface, name);
    }

    @Override
    public int glGetProgramResourceIndex(int program, int programInterface, CharSequence name) {
        return GL43.glGetProgramResourceIndex(program, programInterface, name);
    }

    @Override
    public void glGetProgramResourceName(int program, int programInterface, int index, IntBuffer length, ByteBuffer name) {
        GL43.glGetProgramResourceName(program, programInterface, index, length, name);
    }

    @Override
    public String glGetProgramResourceName(int program, int programInterface, int index, int bufSize) {
        return GL43.glGetProgramResourceName(program, programInterface, index, bufSize);
    }

    @Override
    public String glGetProgramResourceName(int program, int programInterface, int index) {
        return GL43.glGetProgramResourceName(program, programInterface, index);
    }

    @Override
    public void glGetProgramResourceiv(int program, int programInterface, int index, IntBuffer props, IntBuffer length, IntBuffer params) {
        GL43.glGetProgramResourceiv(program, programInterface, index, props, length, params);
    }

    @Override
    public int glGetProgramResourceLocation(int program, int programInterface, ByteBuffer name) {
        return GL43.glGetProgramResourceLocation(program, programInterface, name);
    }

    @Override
    public int glGetProgramResourceLocation(int program, int programInterface, CharSequence name) {
        return GL43.glGetProgramResourceLocation(program, programInterface, name);
    }

    @Override
    public int glGetProgramResourceLocationIndex(int program, int programInterface, ByteBuffer name) {
        return GL43.glGetProgramResourceLocationIndex(program, programInterface, name);
    }

    @Override
    public int glGetProgramResourceLocationIndex(int program, int programInterface, CharSequence name) {
        return GL43.glGetProgramResourceLocationIndex(program, programInterface, name);
    }

    @Override
    public void glShaderStorageBlockBinding(int program, int storageBlockIndex, int storageBlockBinding) {
        GL43.glShaderStorageBlockBinding(program, storageBlockIndex, storageBlockBinding);
    }

    @Override
    public void glTexBufferRange(int target, int internalformat, int buffer, long offset, long size) {
        GL43.glTexBufferRange(target, internalformat, buffer, offset, size);
    }

    @Override
    public void glTexStorage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) {
        GL43.glTexStorage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations);
    }

    @Override
    public void glTexStorage3DMultisample(int target, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) {
        GL43.glTexStorage3DMultisample(target, samples, internalformat, width, height, depth, fixedsamplelocations);
    }

    @Override
    public void glTextureView(int texture, int target, int origtexture, int internalformat, int minlevel, int numlevels, int minlayer, int numlayers) {
        GL43.glTextureView(texture, target, origtexture, internalformat, minlevel, numlevels, minlayer, numlayers);
    }

    @Override
    public void glBindVertexBuffer(int bindingindex, int buffer, long offset, int stride) {
        GL43.glBindVertexBuffer(bindingindex, buffer, offset, stride);
    }

    @Override
    public void glVertexAttribFormat(int attribindex, int size, int type, boolean normalized, int relativeoffset) {
        GL43.glVertexAttribFormat(attribindex, size, type, normalized, relativeoffset);
    }

    @Override
    public void glVertexAttribIFormat(int attribindex, int size, int type, int relativeoffset) {
        GL43.glVertexAttribIFormat(attribindex, size, type, relativeoffset);
    }

    @Override
    public void glVertexAttribLFormat(int attribindex, int size, int type, int relativeoffset) {
        GL43.glVertexAttribLFormat(attribindex, size, type, relativeoffset);
    }

    @Override
    public void glVertexAttribBinding(int attribindex, int bindingindex) {
        GL43.glVertexAttribBinding(attribindex, bindingindex);
    }

    @Override
    public void glVertexBindingDivisor(int bindingindex, int divisor) {
        GL43.glVertexBindingDivisor(bindingindex, divisor);
    }

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, short[] data) {
        GL43.glClearBufferData(target, internalformat, format, type, data);
    }

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, int[] data) {
        GL43.glClearBufferData(target, internalformat, format, type, data);
    }

    @Override
    public void glClearBufferData(int target, int internalformat, int format, int type, float[] data) {
        GL43.glClearBufferData(target, internalformat, format, type, data);
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, short[] data) {
        GL43.glClearBufferSubData(target, internalformat, offset, size, format, type, data);
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, int[] data) {
        GL43.glClearBufferSubData(target, internalformat, offset, size, format, type, data);
    }

    @Override
    public void glClearBufferSubData(int target, int internalformat, long offset, long size, int format, int type, float[] data) {
        GL43.glClearBufferSubData(target, internalformat, offset, size, format, type, data);
    }

    @Override
    public void glDebugMessageControl(int source, int type, int severity, int[] ids, boolean enabled) {
        GL43.glDebugMessageControl(source, type, severity, ids, enabled);
    }

    @Override
    public int glGetDebugMessageLog(int count, int[] sources, int[] types, int[] ids, int[] severities, int[] lengths, ByteBuffer messageLog) {
        return GL43.glGetDebugMessageLog(count, sources, types, ids, severities, lengths, messageLog);
    }

    @Override
    public void glGetObjectLabel(int identifier, int name, int[] length, ByteBuffer label) {
        GL43.glGetObjectLabel(identifier, name, length, label);
    }

    @Override
    public void glGetObjectPtrLabel(long ptr, int[] length, ByteBuffer label) {
        GL43.glGetObjectPtrLabel(ptr, length, label);
    }

    @Override
    public void glGetFramebufferParameteriv(int target, int pname, int[] params) {
        GL43.glGetFramebufferParameteriv(target, pname, params);
    }

    @Override
    public void glGetInternalformati64v(int target, int internalformat, int pname, long[] params) {
        GL43.glGetInternalformati64v(target, internalformat, pname, params);
    }

    @Override
    public void glInvalidateFramebuffer(int target, int[] attachments) {
        GL43.glInvalidateFramebuffer(target, attachments);
    }

    @Override
    public void glInvalidateSubFramebuffer(int target, int[] attachments, int x, int y, int width, int height) {
        GL43.glInvalidateSubFramebuffer(target, attachments, x, y, width, height);
    }

    @Override
    public void glMultiDrawArraysIndirect(int mode, int[] indirect, int drawcount, int stride) {
        GL43.glMultiDrawArraysIndirect(mode, indirect, drawcount, stride);
    }

    @Override
    public void glMultiDrawElementsIndirect(int mode, int type, int[] indirect, int drawcount, int stride) {
        GL43.glMultiDrawElementsIndirect(mode, type, indirect, drawcount, stride);
    }

    @Override
    public void glGetProgramInterfaceiv(int program, int programInterface, int pname, int[] params) {
        GL43.glGetProgramInterfaceiv(program, programInterface, pname, params);
    }

    @Override
    public void glGetProgramResourceName(int program, int programInterface, int index, int[] length, ByteBuffer name) {
        GL43.glGetProgramResourceName(program, programInterface, index, length, name);
    }

    @Override
    public void glGetProgramResourceiv(int program, int programInterface, int index, int[] props, int[] length, int[] params) {
        GL43.glGetProgramResourceiv(program, programInterface, index, props, length, params);
    }

}