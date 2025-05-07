package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL30I;
import org.lwjgl.opengl.GL30;
import java.nio.*;

public class LwjglGL30 extends LwjglGL21 implements GL30I {

    public static final LwjglGL30 INSTANCE = new LwjglGL30();

    protected LwjglGL30() { }


    @Override
    public String glGetStringi(int name, int index) {
        return GL30.glGetStringi(name, index);
    }

    @Override
    public void glClearBufferiv(int buffer, int drawbuffer, IntBuffer value) {
        GL30.glClearBufferiv(buffer, drawbuffer, value);
    }

    @Override
    public void glClearBufferuiv(int buffer, int drawbuffer, IntBuffer value) {
        GL30.glClearBufferuiv(buffer, drawbuffer, value);
    }

    @Override
    public void glClearBufferfv(int buffer, int drawbuffer, FloatBuffer value) {
        GL30.glClearBufferfv(buffer, drawbuffer, value);
    }

    @Override
    public void glClearBufferfi(int buffer, int drawbuffer, float depth, int stencil) {
        GL30.glClearBufferfi(buffer, drawbuffer, depth, stencil);
    }

    @Override
    public void glVertexAttribI1i(int index, int x) {
        GL30.glVertexAttribI1i(index, x);
    }

    @Override
    public void glVertexAttribI2i(int index, int x, int y) {
        GL30.glVertexAttribI2i(index, x, y);
    }

    @Override
    public void glVertexAttribI3i(int index, int x, int y, int z) {
        GL30.glVertexAttribI3i(index, x, y, z);
    }

    @Override
    public void glVertexAttribI4i(int index, int x, int y, int z, int w) {
        GL30.glVertexAttribI4i(index, x, y, z, w);
    }

    @Override
    public void glVertexAttribI1ui(int index, int x) {
        GL30.glVertexAttribI1ui(index, x);
    }

    @Override
    public void glVertexAttribI2ui(int index, int x, int y) {
        GL30.glVertexAttribI2ui(index, x, y);
    }

    @Override
    public void glVertexAttribI3ui(int index, int x, int y, int z) {
        GL30.glVertexAttribI3ui(index, x, y, z);
    }

    @Override
    public void glVertexAttribI4ui(int index, int x, int y, int z, int w) {
        GL30.glVertexAttribI4ui(index, x, y, z, w);
    }

    @Override
    public void glVertexAttribI1iv(int index, IntBuffer v) {
        GL30.glVertexAttribI1iv(index, v);
    }

    @Override
    public void glVertexAttribI2iv(int index, IntBuffer v) {
        GL30.glVertexAttribI2iv(index, v);
    }

    @Override
    public void glVertexAttribI3iv(int index, IntBuffer v) {
        GL30.glVertexAttribI3iv(index, v);
    }

    @Override
    public void glVertexAttribI4iv(int index, IntBuffer v) {
        GL30.glVertexAttribI4iv(index, v);
    }

    @Override
    public void glVertexAttribI1uiv(int index, IntBuffer v) {
        GL30.glVertexAttribI1uiv(index, v);
    }

    @Override
    public void glVertexAttribI2uiv(int index, IntBuffer v) {
        GL30.glVertexAttribI2uiv(index, v);
    }

    @Override
    public void glVertexAttribI3uiv(int index, IntBuffer v) {
        GL30.glVertexAttribI3uiv(index, v);
    }

    @Override
    public void glVertexAttribI4uiv(int index, IntBuffer v) {
        GL30.glVertexAttribI4uiv(index, v);
    }

    @Override
    public void glVertexAttribI4bv(int index, ByteBuffer v) {
        GL30.glVertexAttribI4bv(index, v);
    }

    @Override
    public void glVertexAttribI4sv(int index, ShortBuffer v) {
        GL30.glVertexAttribI4sv(index, v);
    }

    @Override
    public void glVertexAttribI4ubv(int index, ByteBuffer v) {
        GL30.glVertexAttribI4ubv(index, v);
    }

    @Override
    public void glVertexAttribI4usv(int index, ShortBuffer v) {
        GL30.glVertexAttribI4usv(index, v);
    }

    @Override
    public void glVertexAttribIPointer(int index, int size, int type, int stride, ByteBuffer pointer) {
        GL30.glVertexAttribIPointer(index, size, type, stride, pointer);
    }

    @Override
    public void glVertexAttribIPointer(int index, int size, int type, int stride, long pointer) {
        GL30.glVertexAttribIPointer(index, size, type, stride, pointer);
    }

    @Override
    public void glVertexAttribIPointer(int index, int size, int type, int stride, ShortBuffer pointer) {
        GL30.glVertexAttribIPointer(index, size, type, stride, pointer);
    }

    @Override
    public void glVertexAttribIPointer(int index, int size, int type, int stride, IntBuffer pointer) {
        GL30.glVertexAttribIPointer(index, size, type, stride, pointer);
    }

    @Override
    public void glGetVertexAttribIiv(int index, int pname, IntBuffer params) {
        GL30.glGetVertexAttribIiv(index, pname, params);
    }

    @Override
    public int glGetVertexAttribIi(int index, int pname) {
        return GL30.glGetVertexAttribIi(index, pname);
    }

    @Override
    public void glGetVertexAttribIuiv(int index, int pname, IntBuffer params) {
        GL30.glGetVertexAttribIuiv(index, pname, params);
    }

    @Override
    public int glGetVertexAttribIui(int index, int pname) {
        return GL30.glGetVertexAttribIui(index, pname);
    }

    @Override
    public void glUniform1ui(int location, int v0) {
        GL30.glUniform1ui(location, v0);
    }

    @Override
    public void glUniform2ui(int location, int v0, int v1) {
        GL30.glUniform2ui(location, v0, v1);
    }

    @Override
    public void glUniform3ui(int location, int v0, int v1, int v2) {
        GL30.glUniform3ui(location, v0, v1, v2);
    }

    @Override
    public void glUniform4ui(int location, int v0, int v1, int v2, int v3) {
        GL30.glUniform4ui(location, v0, v1, v2, v3);
    }

    @Override
    public void glUniform1uiv(int location, IntBuffer value) {
        GL30.glUniform1uiv(location, value);
    }

    @Override
    public void glUniform2uiv(int location, IntBuffer value) {
        GL30.glUniform2uiv(location, value);
    }

    @Override
    public void glUniform3uiv(int location, IntBuffer value) {
        GL30.glUniform3uiv(location, value);
    }

    @Override
    public void glUniform4uiv(int location, IntBuffer value) {
        GL30.glUniform4uiv(location, value);
    }

    @Override
    public void glGetUniformuiv(int program, int location, IntBuffer params) {
        GL30.glGetUniformuiv(program, location, params);
    }

    @Override
    public int glGetUniformui(int program, int location) {
        return GL30.glGetUniformui(program, location);
    }

    @Override
    public void glBindFragDataLocation(int program, int colorNumber, ByteBuffer name) {
        GL30.glBindFragDataLocation(program, colorNumber, name);
    }

    @Override
    public void glBindFragDataLocation(int program, int colorNumber, CharSequence name) {
        GL30.glBindFragDataLocation(program, colorNumber, name);
    }

    @Override
    public int glGetFragDataLocation(int program, ByteBuffer name) {
        return GL30.glGetFragDataLocation(program, name);
    }

    @Override
    public int glGetFragDataLocation(int program, CharSequence name) {
        return GL30.glGetFragDataLocation(program, name);
    }

    @Override
    public void glBeginConditionalRender(int id, int mode) {
        GL30.glBeginConditionalRender(id, mode);
    }

    @Override
    public void glEndConditionalRender() {
        GL30.glEndConditionalRender();
    }

    @Override
    public ByteBuffer glMapBufferRange(int target, long offset, long length, int access) {
        return GL30.glMapBufferRange(target, offset, length, access);
    }

    @Override
    public ByteBuffer glMapBufferRange(int target, long offset, long length, int access, ByteBuffer old_buffer) {
        return GL30.glMapBufferRange(target, offset, length, access, old_buffer);
    }

    @Override
    public void glFlushMappedBufferRange(int target, long offset, long length) {
        GL30.glFlushMappedBufferRange(target, offset, length);
    }

    @Override
    public void glClampColor(int target, int clamp) {
        GL30.glClampColor(target, clamp);
    }

    @Override
    public boolean glIsRenderbuffer(int renderbuffer) {
        return GL30.glIsRenderbuffer(renderbuffer);
    }

    @Override
    public void glBindRenderbuffer(int target, int renderbuffer) {
        GL30.glBindRenderbuffer(target, renderbuffer);
    }

    @Override
    public void glDeleteRenderbuffers(IntBuffer renderbuffers) {
        GL30.glDeleteRenderbuffers(renderbuffers);
    }

    @Override
    public void glDeleteRenderbuffers(int renderbuffer) {
        GL30.glDeleteRenderbuffers(renderbuffer);
    }

    @Override
    public void glGenRenderbuffers(IntBuffer renderbuffers) {
        GL30.glGenRenderbuffers(renderbuffers);
    }

    @Override
    public int glGenRenderbuffers() {
        return GL30.glGenRenderbuffers();
    }

    @Override
    public void glRenderbufferStorage(int target, int internalformat, int width, int height) {
        GL30.glRenderbufferStorage(target, internalformat, width, height);
    }

    @Override
    public void glRenderbufferStorageMultisample(int target, int samples, int internalformat, int width, int height) {
        GL30.glRenderbufferStorageMultisample(target, samples, internalformat, width, height);
    }

    @Override
    public void glGetRenderbufferParameteriv(int target, int pname, IntBuffer params) {
        GL30.glGetRenderbufferParameteriv(target, pname, params);
    }

    @Override
    public int glGetRenderbufferParameteri(int target, int pname) {
        return GL30.glGetRenderbufferParameteri(target, pname);
    }

    @Override
    public boolean glIsFramebuffer(int framebuffer) {
        return GL30.glIsFramebuffer(framebuffer);
    }

    @Override
    public void glBindFramebuffer(int target, int framebuffer) {
        GL30.glBindFramebuffer(target, framebuffer);
    }

    @Override
    public void glDeleteFramebuffers(IntBuffer framebuffers) {
        GL30.glDeleteFramebuffers(framebuffers);
    }

    @Override
    public void glDeleteFramebuffers(int framebuffer) {
        GL30.glDeleteFramebuffers(framebuffer);
    }

    @Override
    public void glGenFramebuffers(IntBuffer framebuffers) {
        GL30.glGenFramebuffers(framebuffers);
    }

    @Override
    public int glGenFramebuffers() {
        return GL30.glGenFramebuffers();
    }

    @Override
    public int glCheckFramebufferStatus(int target) {
        return GL30.glCheckFramebufferStatus(target);
    }

    @Override
    public void glFramebufferTexture1D(int target, int attachment, int textarget, int texture, int level) {
        GL30.glFramebufferTexture1D(target, attachment, textarget, texture, level);
    }

    @Override
    public void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {
        GL30.glFramebufferTexture2D(target, attachment, textarget, texture, level);
    }

    @Override
    public void glFramebufferTexture3D(int target, int attachment, int textarget, int texture, int level, int layer) {
        GL30.glFramebufferTexture3D(target, attachment, textarget, texture, level, layer);
    }

    @Override
    public void glFramebufferTextureLayer(int target, int attachment, int texture, int level, int layer) {
        GL30.glFramebufferTextureLayer(target, attachment, texture, level, layer);
    }

    @Override
    public void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {
        GL30.glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer);
    }

    @Override
    public void glGetFramebufferAttachmentParameteriv(int target, int attachment, int pname, IntBuffer params) {
        GL30.glGetFramebufferAttachmentParameteriv(target, attachment, pname, params);
    }

    @Override
    public int glGetFramebufferAttachmentParameteri(int target, int attachment, int pname) {
        return GL30.glGetFramebufferAttachmentParameteri(target, attachment, pname);
    }

    @Override
    public void glBlitFramebuffer(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter) {
        GL30.glBlitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask, filter);
    }

    @Override
    public void glGenerateMipmap(int target) {
        GL30.glGenerateMipmap(target);
    }

    @Override
    public void glTexParameterIiv(int target, int pname, IntBuffer params) {
        GL30.glTexParameterIiv(target, pname, params);
    }

    @Override
    public void glTexParameterIi(int target, int pname, int param) {
        GL30.glTexParameterIi(target, pname, param);
    }

    @Override
    public void glTexParameterIuiv(int target, int pname, IntBuffer params) {
        GL30.glTexParameterIuiv(target, pname, params);
    }

    @Override
    public void glTexParameterIui(int target, int pname, int param) {
        GL30.glTexParameterIui(target, pname, param);
    }

    @Override
    public void glGetTexParameterIiv(int target, int pname, IntBuffer params) {
        GL30.glGetTexParameterIiv(target, pname, params);
    }

    @Override
    public int glGetTexParameterIi(int target, int pname) {
        return GL30.glGetTexParameterIi(target, pname);
    }

    @Override
    public void glGetTexParameterIuiv(int target, int pname, IntBuffer params) {
        GL30.glGetTexParameterIuiv(target, pname, params);
    }

    @Override
    public int glGetTexParameterIui(int target, int pname) {
        return GL30.glGetTexParameterIui(target, pname);
    }

    @Override
    public void glColorMaski(int buf, boolean r, boolean g, boolean b, boolean a) {
        GL30.glColorMaski(buf, r, g, b, a);
    }

    @Override
    public void glGetBooleani_v(int target, int index, ByteBuffer data) {
        GL30.glGetBooleani_v(target, index, data);
    }

    @Override
    public boolean glGetBooleani(int target, int index) {
        return GL30.glGetBooleani(target, index);
    }

    @Override
    public void glGetIntegeri_v(int target, int index, IntBuffer data) {
        GL30.glGetIntegeri_v(target, index, data);
    }

    @Override
    public int glGetIntegeri(int target, int index) {
        return GL30.glGetIntegeri(target, index);
    }

    @Override
    public void glEnablei(int cap, int index) {
        GL30.glEnablei(cap, index);
    }

    @Override
    public void glDisablei(int target, int index) {
        GL30.glDisablei(target, index);
    }

    @Override
    public boolean glIsEnabledi(int target, int index) {
        return GL30.glIsEnabledi(target, index);
    }

    @Override
    public void glBindBufferRange(int target, int index, int buffer, long offset, long size) {
        GL30.glBindBufferRange(target, index, buffer, offset, size);
    }

    @Override
    public void glBindBufferBase(int target, int index, int buffer) {
        GL30.glBindBufferBase(target, index, buffer);
    }

    @Override
    public void glBeginTransformFeedback(int primitiveMode) {
        GL30.glBeginTransformFeedback(primitiveMode);
    }

    @Override
    public void glEndTransformFeedback() {
        GL30.glEndTransformFeedback();
    }

    @Override
    public void glTransformFeedbackVaryings(int program, CharSequence[] varyings, int bufferMode) {
        GL30.glTransformFeedbackVaryings(program, varyings, bufferMode);
    }

    @Override
    public void glTransformFeedbackVaryings(int program, CharSequence varying, int bufferMode) {
        GL30.glTransformFeedbackVaryings(program, varying, bufferMode);
    }

    @Override
    public void glGetTransformFeedbackVarying(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
        GL30.glGetTransformFeedbackVarying(program, index, length, size, type, name);
    }

    @Override
    public String glGetTransformFeedbackVarying(int program, int index, int bufSize, IntBuffer size, IntBuffer type) {
        return GL30.glGetTransformFeedbackVarying(program, index, bufSize, size, type);
    }

    @Override
    public String glGetTransformFeedbackVarying(int program, int index, IntBuffer size, IntBuffer type) {
        return GL30.glGetTransformFeedbackVarying(program, index, size, type);
    }

    @Override
    public void glBindVertexArray(int array) {
        GL30.glBindVertexArray(array);
    }

    @Override
    public void glDeleteVertexArrays(IntBuffer arrays) {
        GL30.glDeleteVertexArrays(arrays);
    }

    @Override
    public void glDeleteVertexArrays(int array) {
        GL30.glDeleteVertexArrays(array);
    }

    @Override
    public void glGenVertexArrays(IntBuffer arrays) {
        GL30.glGenVertexArrays(arrays);
    }

    @Override
    public int glGenVertexArrays() {
        return GL30.glGenVertexArrays();
    }

    @Override
    public boolean glIsVertexArray(int array) {
        return GL30.glIsVertexArray(array);
    }

    @Override
    public void glClearBufferiv(int buffer, int drawbuffer, int[] value) {
        GL30.glClearBufferiv(buffer, drawbuffer, value);
    }

    @Override
    public void glClearBufferuiv(int buffer, int drawbuffer, int[] value) {
        GL30.glClearBufferuiv(buffer, drawbuffer, value);
    }

    @Override
    public void glClearBufferfv(int buffer, int drawbuffer, float[] value) {
        GL30.glClearBufferfv(buffer, drawbuffer, value);
    }

    @Override
    public void glVertexAttribI1iv(int index, int[] v) {
        GL30.glVertexAttribI1iv(index, v);
    }

    @Override
    public void glVertexAttribI2iv(int index, int[] v) {
        GL30.glVertexAttribI2iv(index, v);
    }

    @Override
    public void glVertexAttribI3iv(int index, int[] v) {
        GL30.glVertexAttribI3iv(index, v);
    }

    @Override
    public void glVertexAttribI4iv(int index, int[] v) {
        GL30.glVertexAttribI4iv(index, v);
    }

    @Override
    public void glVertexAttribI1uiv(int index, int[] v) {
        GL30.glVertexAttribI1uiv(index, v);
    }

    @Override
    public void glVertexAttribI2uiv(int index, int[] v) {
        GL30.glVertexAttribI2uiv(index, v);
    }

    @Override
    public void glVertexAttribI3uiv(int index, int[] v) {
        GL30.glVertexAttribI3uiv(index, v);
    }

    @Override
    public void glVertexAttribI4uiv(int index, int[] v) {
        GL30.glVertexAttribI4uiv(index, v);
    }

    @Override
    public void glVertexAttribI4sv(int index, short[] v) {
        GL30.glVertexAttribI4sv(index, v);
    }

    @Override
    public void glVertexAttribI4usv(int index, short[] v) {
        GL30.glVertexAttribI4usv(index, v);
    }

    @Override
    public void glGetVertexAttribIiv(int index, int pname, int[] params) {
        GL30.glGetVertexAttribIiv(index, pname, params);
    }

    @Override
    public void glGetVertexAttribIuiv(int index, int pname, int[] params) {
        GL30.glGetVertexAttribIuiv(index, pname, params);
    }

    @Override
    public void glUniform1uiv(int location, int[] value) {
        GL30.glUniform1uiv(location, value);
    }

    @Override
    public void glUniform2uiv(int location, int[] value) {
        GL30.glUniform2uiv(location, value);
    }

    @Override
    public void glUniform3uiv(int location, int[] value) {
        GL30.glUniform3uiv(location, value);
    }

    @Override
    public void glUniform4uiv(int location, int[] value) {
        GL30.glUniform4uiv(location, value);
    }

    @Override
    public void glGetUniformuiv(int program, int location, int[] params) {
        GL30.glGetUniformuiv(program, location, params);
    }

    @Override
    public void glDeleteRenderbuffers(int[] renderbuffers) {
        GL30.glDeleteRenderbuffers(renderbuffers);
    }

    @Override
    public void glGenRenderbuffers(int[] renderbuffers) {
        GL30.glGenRenderbuffers(renderbuffers);
    }

    @Override
    public void glGetRenderbufferParameteriv(int target, int pname, int[] params) {
        GL30.glGetRenderbufferParameteriv(target, pname, params);
    }

    @Override
    public void glDeleteFramebuffers(int[] framebuffers) {
        GL30.glDeleteFramebuffers(framebuffers);
    }

    @Override
    public void glGenFramebuffers(int[] framebuffers) {
        GL30.glGenFramebuffers(framebuffers);
    }

    @Override
    public void glGetFramebufferAttachmentParameteriv(int target, int attachment, int pname, int[] params) {
        GL30.glGetFramebufferAttachmentParameteriv(target, attachment, pname, params);
    }

    @Override
    public void glTexParameterIiv(int target, int pname, int[] params) {
        GL30.glTexParameterIiv(target, pname, params);
    }

    @Override
    public void glTexParameterIuiv(int target, int pname, int[] params) {
        GL30.glTexParameterIuiv(target, pname, params);
    }

    @Override
    public void glGetTexParameterIiv(int target, int pname, int[] params) {
        GL30.glGetTexParameterIiv(target, pname, params);
    }

    @Override
    public void glGetTexParameterIuiv(int target, int pname, int[] params) {
        GL30.glGetTexParameterIuiv(target, pname, params);
    }

    @Override
    public void glGetIntegeri_v(int target, int index, int[] data) {
        GL30.glGetIntegeri_v(target, index, data);
    }

    @Override
    public void glGetTransformFeedbackVarying(int program, int index, int[] length, int[] size, int[] type, ByteBuffer name) {
        GL30.glGetTransformFeedbackVarying(program, index, length, size, type, name);
    }

    @Override
    public void glDeleteVertexArrays(int[] arrays) {
        GL30.glDeleteVertexArrays(arrays);
    }

    @Override
    public void glGenVertexArrays(int[] arrays) {
        GL30.glGenVertexArrays(arrays);
    }

}