package jpize.android.opengl;

import android.opengl.*;
import jpize.opengl.gl.GL30I;
import java.nio.*;

public class AndroidGL30 extends AndroidGL21 implements GL30I {

    public static final AndroidGL30 INSTANCE = new AndroidGL30();

    protected AndroidGL30() { }


    @Override
    public String glGetStringi(int name, int index) {
        return GLES30.glGetStringi(name, index);
    }

    @Override
    public void glClearBufferiv(int buffer, int drawbuffer, IntBuffer value) {
        GLES30.glClearBufferiv(buffer, drawbuffer, value);
    }

    @Override
    public void glClearBufferuiv(int buffer, int drawbuffer, IntBuffer value) {
        GLES30.glClearBufferuiv(buffer, drawbuffer, value);
    }

    @Override
    public void glClearBufferfv(int buffer, int drawbuffer, FloatBuffer value) {
        GLES30.glClearBufferfv(buffer, drawbuffer, value);
    }

    @Override
    public void glClearBufferfi(int buffer, int drawbuffer, float depth, int stencil) {
        GLES30.glClearBufferfi(buffer, drawbuffer, depth, stencil);
    }

    @Override
    public void glVertexAttribI1i(int index, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI2i(int index, int x, int y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI3i(int index, int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI4i(int index, int x, int y, int z, int w) {
        GLES30.glVertexAttribI4i(index, x, y, z, w);
    }

    @Override
    public void glVertexAttribI1ui(int index, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI2ui(int index, int x, int y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI3ui(int index, int x, int y, int z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI4ui(int index, int x, int y, int z, int w) {
        GLES30.glVertexAttribI4ui(index, x, y, z, w);
    }

    @Override
    public void glVertexAttribI1iv(int index, IntBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI2iv(int index, IntBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI3iv(int index, IntBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI4iv(int index, IntBuffer v) {
        GLES30.glVertexAttribI4iv(index, v);
    }

    @Override
    public void glVertexAttribI1uiv(int index, IntBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI2uiv(int index, IntBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI3uiv(int index, IntBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI4uiv(int index, IntBuffer v) {
        GLES30.glVertexAttribI4uiv(index, v);
    }

    @Override
    public void glVertexAttribI4bv(int index, ByteBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI4sv(int index, ShortBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI4ubv(int index, ByteBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI4usv(int index, ShortBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribIPointer(int index, int size, int type, int stride, ByteBuffer pointer) {
        GLES30.glVertexAttribIPointer(index, size, type, stride, pointer);
    }

    @Override
    public void glVertexAttribIPointer(int index, int size, int type, int stride, long pointer) {
        GLES30.glVertexAttribIPointer(index, size, type, stride, (int) pointer);
    }

    @Override
    public void glVertexAttribIPointer(int index, int size, int type, int stride, ShortBuffer pointer) {
        GLES30.glVertexAttribIPointer(index, size, type, stride, pointer);
    }

    @Override
    public void glVertexAttribIPointer(int index, int size, int type, int stride, IntBuffer pointer) {
        GLES30.glVertexAttribIPointer(index, size, type, stride, pointer);
    }

    @Override
    public void glGetVertexAttribIiv(int index, int pname, IntBuffer params) {
        GLES30.glGetVertexAttribIiv(index, pname, params);
    }

    @Override
    public int glGetVertexAttribIi(int index, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetVertexAttribIuiv(int index, int pname, IntBuffer params) {
        GLES30.glGetVertexAttribIuiv(index, pname, params);
    }

    @Override
    public int glGetVertexAttribIui(int index, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glUniform1ui(int location, int v0) {
        GLES30.glUniform1ui(location, v0);
    }

    @Override
    public void glUniform2ui(int location, int v0, int v1) {
        GLES30.glUniform2ui(location, v0, v1);
    }

    @Override
    public void glUniform3ui(int location, int v0, int v1, int v2) {
        GLES30.glUniform3ui(location, v0, v1, v2);
    }

    @Override
    public void glUniform4ui(int location, int v0, int v1, int v2, int v3) {
        GLES30.glUniform4ui(location, v0, v1, v2, v3);
    }

    @Override
    public void glUniform1uiv(int location, IntBuffer value) {
        GLES30.glUniform1uiv(location, value.limit(), value);
    }

    @Override
    public void glUniform2uiv(int location, IntBuffer value) {
        GLES30.glUniform2uiv(location, value.limit(), value);
    }

    @Override
    public void glUniform3uiv(int location, IntBuffer value) {
        GLES30.glUniform3uiv(location, value.limit(), value);
    }

    @Override
    public void glUniform4uiv(int location, IntBuffer value) {
        GLES30.glUniform4uiv(location, value.limit(), value);
    }

    @Override
    public void glGetUniformuiv(int program, int location, IntBuffer params) {
        GLES30.glGetUniformuiv(program, location, params);
    }

    @Override
    public int glGetUniformui(int program, int location) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glBindFragDataLocation(int program, int colorNumber, ByteBuffer name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glBindFragDataLocation(int program, int colorNumber, CharSequence name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetFragDataLocation(int program, ByteBuffer name) {
        return GLES30.glGetFragDataLocation(program, createString(name));
    }

    @Override
    public int glGetFragDataLocation(int program, CharSequence name) {
        return GLES30.glGetFragDataLocation(program, name.toString());
    }

    @Override
    public void glBeginConditionalRender(int id, int mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glEndConditionalRender() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ByteBuffer glMapBufferRange(int target, long offset, long length, int access) {
        return (ByteBuffer) GLES30.glMapBufferRange(target, (int) offset, (int) length, access);
    }

    @Override
    public ByteBuffer glMapBufferRange(int target, long offset, long length, int access, ByteBuffer old_buffer) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glFlushMappedBufferRange(int target, long offset, long length) {
        GLES30.glFlushMappedBufferRange(target, (int) offset, (int) length);
    }

    @Override
    public void glClampColor(int target, int clamp) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean glIsRenderbuffer(int renderbuffer) {
        return GLES20.glIsRenderbuffer(renderbuffer);
    }

    @Override
    public void glBindRenderbuffer(int target, int renderbuffer) {
        GLES20.glBindRenderbuffer(target, renderbuffer);
    }

    @Override
    public void glDeleteRenderbuffers(IntBuffer renderbuffers) {
        GLES20.glDeleteRenderbuffers(renderbuffers.limit(), renderbuffers);
    }

    @Override
    public void glDeleteRenderbuffers(int renderbuffer) {
        tmp_int[0] = renderbuffer;
        GLES20.glDeleteRenderbuffers(1, tmp_int, 0);
    }

    @Override
    public void glGenRenderbuffers(IntBuffer renderbuffers) {
        GLES20.glGenRenderbuffers(renderbuffers.limit(), renderbuffers);
    }

    @Override
    public int glGenRenderbuffers() {
        GLES20.glGenRenderbuffers(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glRenderbufferStorage(int target, int internalformat, int width, int height) {
        GLES20.glRenderbufferStorage(target, internalformat, width, height);
    }

    @Override
    public void glRenderbufferStorageMultisample(int target, int samples, int internalformat, int width, int height) {
        GLES30.glRenderbufferStorageMultisample(target, samples, internalformat, width, height);
    }

    @Override
    public void glGetRenderbufferParameteriv(int target, int pname, IntBuffer params) {
        GLES20.glGetRenderbufferParameteriv(target, pname, params);
    }

    @Override
    public int glGetRenderbufferParameteri(int target, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean glIsFramebuffer(int framebuffer) {
        return GLES20.glIsFramebuffer(framebuffer);
    }

    @Override
    public void glBindFramebuffer(int target, int framebuffer) {
        GLES20.glBindFramebuffer(target, framebuffer);
    }

    @Override
    public void glDeleteFramebuffers(IntBuffer framebuffers) {
        GLES20.glDeleteFramebuffers(framebuffers.limit(), framebuffers);
    }

    @Override
    public void glDeleteFramebuffers(int framebuffer) {
        tmp_int[0] = framebuffer;
        GLES20.glDeleteFramebuffers(1, tmp_int, 0);
    }

    @Override
    public void glGenFramebuffers(IntBuffer framebuffers) {
        GLES20.glGenFramebuffers(framebuffers.limit(), framebuffers);
    }

    @Override
    public int glGenFramebuffers() {
        GLES20.glGenFramebuffers(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public int glCheckFramebufferStatus(int target) {
        return GLES20.glCheckFramebufferStatus(target);
    }

    @Override
    public void glFramebufferTexture1D(int target, int attachment, int textarget, int texture, int level) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {
        GLES20.glFramebufferTexture2D(target, attachment, textarget, texture, level);
    }

    @Override
    public void glFramebufferTexture3D(int target, int attachment, int textarget, int texture, int level, int layer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glFramebufferTextureLayer(int target, int attachment, int texture, int level, int layer) {
        GLES30.glFramebufferTextureLayer(target, attachment, texture, level, layer);
    }

    @Override
    public void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {
        GLES20.glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer);
    }

    @Override
    public void glGetFramebufferAttachmentParameteriv(int target, int attachment, int pname, IntBuffer params) {
        GLES20.glGetFramebufferAttachmentParameteriv(target, attachment, pname, params);
    }

    @Override
    public int glGetFramebufferAttachmentParameteri(int target, int attachment, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glBlitFramebuffer(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter) {
        GLES30.glBlitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask, filter);
    }

    @Override
    public void glGenerateMipmap(int target) {
        GLES20.glGenerateMipmap(target);
    }

    @Override
    public void glTexParameterIiv(int target, int pname, IntBuffer params) {
        GLES32.glTexParameterIiv(target, pname, params);
    }

    @Override
    public void glTexParameterIi(int target, int pname, int param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexParameterIuiv(int target, int pname, IntBuffer params) {
        GLES32.glTexParameterIuiv(target, pname, params);
    }

    @Override
    public void glTexParameterIui(int target, int pname, int param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTexParameterIiv(int target, int pname, IntBuffer params) {
        GLES32.glGetTexParameterIiv(target, pname, params);
    }

    @Override
    public int glGetTexParameterIi(int target, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTexParameterIuiv(int target, int pname, IntBuffer params) {
        GLES32.glGetTexParameterIuiv(target, pname, params);
    }

    @Override
    public int glGetTexParameterIui(int target, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glColorMaski(int buf, boolean r, boolean g, boolean b, boolean a) {
        GLES32.glColorMaski(buf, r, g, b, a);
    }

    @Override
    public void glGetBooleani_v(int target, int index, ByteBuffer data) {
        GLES31.glGetBooleani_v(target, index, data.asIntBuffer());
    }

    @Override
    public boolean glGetBooleani(int target, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetIntegeri_v(int target, int index, IntBuffer data) {
        GLES30.glGetIntegeri_v(target, index, data);
    }

    @Override
    public int glGetIntegeri(int target, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glEnablei(int cap, int index) {
        GLES32.glEnablei(cap, index);
    }

    @Override
    public void glDisablei(int target, int index) {
        GLES32.glDisablei(target, index);
    }

    @Override
    public boolean glIsEnabledi(int target, int index) {
        return GLES32.glIsEnabledi(target, index);
    }

    @Override
    public void glBindBufferRange(int target, int index, int buffer, long offset, long size) {
        GLES30.glBindBufferRange(target, index, buffer, (int) offset, (int) size);
    }

    @Override
    public void glBindBufferBase(int target, int index, int buffer) {
        GLES30.glBindBufferBase(target, index, buffer);
    }

    @Override
    public void glBeginTransformFeedback(int primitiveMode) {
        GLES30.glBeginTransformFeedback(primitiveMode);
    }

    @Override
    public void glEndTransformFeedback() {
        GLES30.glEndTransformFeedback();
    }

    @Override
    public void glTransformFeedbackVaryings(int program, CharSequence[] varyings, int bufferMode) {
        GLES30.glTransformFeedbackVaryings(program, createStringArray(varyings), bufferMode);
    }

    @Override
    public void glTransformFeedbackVaryings(int program, CharSequence varying, int bufferMode) {
        GLES30.glTransformFeedbackVaryings(program, createStringArray(varying), bufferMode);
    }

    @Override
    public void glGetTransformFeedbackVarying(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
        GLES30.glGetTransformFeedbackVarying(program, index, length.limit(), length, size, type, name);
    }

    @Override
    public String glGetTransformFeedbackVarying(int program, int index, int bufSize, IntBuffer size, IntBuffer type) {
        return GLES30.glGetTransformFeedbackVarying(program, index, size, type);
    }

    @Override
    public String glGetTransformFeedbackVarying(int program, int index, IntBuffer size, IntBuffer type) {
        return GLES30.glGetTransformFeedbackVarying(program, index, size, type);
    }

    @Override
    public void glBindVertexArray(int array) {
        GLES30.glBindVertexArray(array);
    }

    @Override
    public void glDeleteVertexArrays(IntBuffer arrays) {
        GLES30.glDeleteVertexArrays(arrays.limit(), arrays);
    }

    @Override
    public void glDeleteVertexArrays(int array) {
        tmp_int[0] = array;
        GLES30.glDeleteVertexArrays(1, tmp_int, 0);
    }

    @Override
    public void glGenVertexArrays(IntBuffer arrays) {
        GLES30.glGenVertexArrays(arrays.limit(), arrays);
    }

    @Override
    public int glGenVertexArrays() {
        GLES30.glGenVertexArrays(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public boolean glIsVertexArray(int array) {
        return GLES30.glIsVertexArray(array);
    }

    @Override
    public void glClearBufferiv(int buffer, int drawbuffer, int[] value) {
        GLES30.glClearBufferiv(buffer, drawbuffer, value, 0);
    }

    @Override
    public void glClearBufferuiv(int buffer, int drawbuffer, int[] value) {
        GLES30.glClearBufferuiv(buffer, drawbuffer, value, 0);
    }

    @Override
    public void glClearBufferfv(int buffer, int drawbuffer, float[] value) {
        GLES30.glClearBufferfv(buffer, drawbuffer, value, 0);
    }

    @Override
    public void glVertexAttribI1iv(int index, int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI2iv(int index, int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI3iv(int index, int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI4iv(int index, int[] v) {
        GLES30.glVertexAttribI4iv(index, v, 0);
    }

    @Override
    public void glVertexAttribI1uiv(int index, int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI2uiv(int index, int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI3uiv(int index, int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI4uiv(int index, int[] v) {
        GLES30.glVertexAttribI4uiv(index, v, 0);
    }

    @Override
    public void glVertexAttribI4sv(int index, short[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribI4usv(int index, short[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetVertexAttribIiv(int index, int pname, int[] params) {
        GLES30.glGetVertexAttribIiv(index, pname, params, 0);
    }

    @Override
    public void glGetVertexAttribIuiv(int index, int pname, int[] params) {
        GLES30.glGetVertexAttribIuiv(index, pname, params, 0);
    }

    @Override
    public void glUniform1uiv(int location, int[] value) {
        GLES30.glUniform1uiv(location, value.length, value, 0);
    }

    @Override
    public void glUniform2uiv(int location, int[] value) {
        GLES30.glUniform2uiv(location, value.length, value, 0);
    }

    @Override
    public void glUniform3uiv(int location, int[] value) {
        GLES30.glUniform3uiv(location, value.length, value, 0);
    }

    @Override
    public void glUniform4uiv(int location, int[] value) {
        GLES30.glUniform4uiv(location, value.length, value, 0);
    }

    @Override
    public void glGetUniformuiv(int program, int location, int[] params) {
        GLES30.glGetUniformuiv(program, location, params, 0);
    }

    @Override
    public void glDeleteRenderbuffers(int[] renderbuffers) {
        GLES20.glDeleteRenderbuffers(renderbuffers.length, renderbuffers, 0);
    }

    @Override
    public void glGenRenderbuffers(int[] renderbuffers) {
        GLES20.glGenRenderbuffers(renderbuffers.length, renderbuffers, 0);
    }

    @Override
    public void glGetRenderbufferParameteriv(int target, int pname, int[] params) {
        GLES20.glGetRenderbufferParameteriv(target, pname, params, 0);
    }

    @Override
    public void glDeleteFramebuffers(int[] framebuffers) {
        GLES20.glDeleteFramebuffers(framebuffers.length, framebuffers, 0);
    }

    @Override
    public void glGenFramebuffers(int[] framebuffers) {
        GLES20.glGenFramebuffers(framebuffers.length, framebuffers, 0);
    }

    @Override
    public void glGetFramebufferAttachmentParameteriv(int target, int attachment, int pname, int[] params) {
        GLES20.glGetFramebufferAttachmentParameteriv(target, attachment, pname, params, 0);
    }

    @Override
    public void glTexParameterIiv(int target, int pname, int[] params) {
        GLES32.glTexParameterIiv(target, pname, params, 0);
    }

    @Override
    public void glTexParameterIuiv(int target, int pname, int[] params) {
        GLES32.glTexParameterIuiv(target, pname, params, 0);
    }

    @Override
    public void glGetTexParameterIiv(int target, int pname, int[] params) {
        GLES32.glGetTexParameterIiv(target, pname, params, 0);
    }

    @Override
    public void glGetTexParameterIuiv(int target, int pname, int[] params) {
        GLES32.glGetTexParameterIuiv(target, pname, params, 0);
    }

    @Override
    public void glGetIntegeri_v(int target, int index, int[] data) {
        GLES30.glGetIntegeri_v(target, index, data, 0);
    }

    @Override
    public void glGetTransformFeedbackVarying(int program, int index, int[] length, int[] size, int[] type, ByteBuffer name) {
        GLES30.glGetTransformFeedbackVarying(program, index, length.length, createBuffer(length), createBuffer(size), createBuffer(type), name);
    }

    @Override
    public void glDeleteVertexArrays(int[] arrays) {
        GLES30.glDeleteVertexArrays(arrays.length, arrays, 0);
    }

    @Override
    public void glGenVertexArrays(int[] arrays) {
        GLES30.glGenVertexArrays(arrays.length, arrays, 0);
    }

}