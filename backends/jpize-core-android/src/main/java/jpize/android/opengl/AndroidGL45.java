package jpize.android.opengl;

import android.opengl.GLES20;
import android.opengl.GLES32;
import jpize.opengl.gl.GL45I;
import jpize.util.BufferUtils;

import java.nio.*;

public class AndroidGL45 extends AndroidGL44 implements GL45I {

    @Override
    public void glClipControl(int origin, int depth) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCreateTransformFeedbacks(IntBuffer ids) {
        GLES32.glGenTransformFeedbacks(ids.limit(), ids);
    }

    @Override
    public int glCreateTransformFeedbacks() {
        GLES32.glGenTransformFeedbacks(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glTransformFeedbackBufferBase(int xfb, int index, int buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTransformFeedbackBufferRange(int xfb, int index, int buffer, long offset, long size) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTransformFeedbackiv(int xfb, int pname, IntBuffer param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetTransformFeedbacki(int xfb, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTransformFeedbacki_v(int xfb, int pname, int index, IntBuffer param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetTransformFeedbacki(int xfb, int pname, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTransformFeedbacki64_v(int xfb, int pname, int index, LongBuffer param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long glGetTransformFeedbacki64(int xfb, int pname, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCreateBuffers(IntBuffer buffers) {
        GLES32.glGenBuffers(buffers.limit(), buffers);
    }

    @Override
    public int glCreateBuffers() {
        GLES32.glGenBuffers(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glNamedBufferStorage(int buffer, long size, int flags) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferStorage(int buffer, ByteBuffer data, int flags) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferStorage(int buffer, ShortBuffer data, int flags) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferStorage(int buffer, IntBuffer data, int flags) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferStorage(int buffer, FloatBuffer data, int flags) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferStorage(int buffer, DoubleBuffer data, int flags) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferData(int buffer, long size, int usage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferData(int buffer, ByteBuffer data, int usage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferData(int buffer, ShortBuffer data, int usage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferData(int buffer, IntBuffer data, int usage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferData(int buffer, LongBuffer data, int usage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferData(int buffer, FloatBuffer data, int usage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferData(int buffer, DoubleBuffer data, int usage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, ByteBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, ShortBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, IntBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, LongBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, FloatBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, DoubleBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCopyNamedBufferSubData(int readBuffer, int writeBuffer, long readOffset, long writeOffset, long size) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, ByteBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, ShortBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, IntBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, FloatBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, ByteBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, ShortBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, IntBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, FloatBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ByteBuffer glMapNamedBuffer(int buffer, int access) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ByteBuffer glMapNamedBuffer(int buffer, int access, ByteBuffer old_buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ByteBuffer glMapNamedBuffer(int buffer, int access, long length, ByteBuffer old_buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ByteBuffer glMapNamedBufferRange(int buffer, long offset, long length, int access) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ByteBuffer glMapNamedBufferRange(int buffer, long offset, long length, int access, ByteBuffer old_buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean glUnmapNamedBuffer(int buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glFlushMappedNamedBufferRange(int buffer, long offset, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferParameteriv(int buffer, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetNamedBufferParameteri(int buffer, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferParameteri64v(int buffer, int pname, LongBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long glGetNamedBufferParameteri64(int buffer, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long glGetNamedBufferPointer(int buffer, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, ByteBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, ShortBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, IntBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, LongBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, FloatBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, DoubleBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCreateFramebuffers(IntBuffer framebuffers) {
        GLES32.glGenFramebuffers(framebuffers.limit(), framebuffers);
    }

    @Override
    public int glCreateFramebuffers() {
        GLES32.glGenFramebuffers(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glNamedFramebufferRenderbuffer(int framebuffer, int attachment, int renderbuffertarget, int renderbuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedFramebufferParameteri(int framebuffer, int pname, int param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedFramebufferTexture(int framebuffer, int attachment, int texture, int level) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedFramebufferTextureLayer(int framebuffer, int attachment, int texture, int level, int layer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedFramebufferDrawBuffer(int framebuffer, int buf) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedFramebufferDrawBuffers(int framebuffer, IntBuffer bufs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedFramebufferDrawBuffers(int framebuffer, int buf) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedFramebufferReadBuffer(int framebuffer, int src) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glInvalidateNamedFramebufferData(int framebuffer, IntBuffer attachments) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glInvalidateNamedFramebufferData(int framebuffer, int attachment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glInvalidateNamedFramebufferSubData(int framebuffer, IntBuffer attachments, int x, int y, int width, int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glInvalidateNamedFramebufferSubData(int framebuffer, int attachment, int x, int y, int width, int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedFramebufferiv(int framebuffer, int buffer, int drawbuffer, IntBuffer value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedFramebufferuiv(int framebuffer, int buffer, int drawbuffer, IntBuffer value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedFramebufferfv(int framebuffer, int buffer, int drawbuffer, FloatBuffer value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedFramebufferfi(int framebuffer, int buffer, int drawbuffer, float depth, int stencil) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glBlitNamedFramebuffer(int readFramebuffer, int drawFramebuffer, int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glCheckNamedFramebufferStatus(int framebuffer, int target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedFramebufferParameteriv(int framebuffer, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetNamedFramebufferParameteri(int framebuffer, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedFramebufferAttachmentParameteriv(int framebuffer, int attachment, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetNamedFramebufferAttachmentParameteri(int framebuffer, int attachment, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCreateRenderbuffers(IntBuffer renderbuffers) {
        GLES32.glGenRenderbuffers(renderbuffers.limit(), renderbuffers);
    }

    @Override
    public int glCreateRenderbuffers() {
        GLES32.glGenRenderbuffers(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glNamedRenderbufferStorage(int renderbuffer, int internalformat, int width, int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedRenderbufferStorageMultisample(int renderbuffer, int samples, int internalformat, int width, int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedRenderbufferParameteriv(int renderbuffer, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetNamedRenderbufferParameteri(int renderbuffer, int pname) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param target is ignored
     */
    @Override
    public void glCreateTextures(int target, IntBuffer textures) {
        GLES32.glGenTextures(textures.limit(), textures);
    }

    /**
     * @param target is ignored
     */
    @Override
    public int glCreateTextures(int target) {
        GLES32.glGenTextures(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glTextureBuffer(int texture, int internalformat, int buffer) {
        GLES32.glTexBuffer(texture, internalformat, buffer);
    }

    @Override
    public void glTextureBufferRange(int texture, int internalformat, int buffer, long offset, long size) {
        GLES32.glTexBufferRange(texture, internalformat, buffer, (int) offset, (int) size);
    }

    @Override
    public void glTextureStorage1D(int texture, int levels, int internalformat, int width) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTextureStorage2D(int texture, int levels, int internalformat, int width, int height) {
        GLES32.glTexStorage2D(texture, levels, internalformat, width, height);
    }

    @Override
    public void glTextureStorage3D(int texture, int levels, int internalformat, int width, int height, int depth) {
        GLES32.glTexStorage3D(texture, levels, internalformat, width, height, depth);
    }

    @Override
    public void glTextureStorage2DMultisample(int texture, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) {
        GLES32.glTexStorage2DMultisample(texture, samples, internalformat, width, height, fixedsamplelocations);
    }

    @Override
    public void glTextureStorage3DMultisample(int texture, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) {
        GLES32.glTexStorage3DMultisample(texture, samples, internalformat, width, height, depth, fixedsamplelocations);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, long pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, IntBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, FloatBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, DoubleBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) {
        GLES32.glTexSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, long pixels) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) {
        GLES32.glTexSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) {
        GLES32.glTexSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, FloatBuffer pixels) {
        GLES32.glTexSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, DoubleBuffer pixels) {
        GLES32.glTexSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
        GLES32.glTexSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
        GLES32.glTexSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
        GLES32.glTexSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) {
        GLES32.glTexSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer pixels) {
        GLES32.glTexSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glCompressedTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int imageSize, long data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCompressedTextureSubImage1D(int texture, int level, int xoffset, int width, int format, ByteBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCompressedTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, long data) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glCompressedTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer data) {
        GLES32.glCompressedTexSubImage2D(texture, level, xoffset, yoffset, width, height, format, data.limit(), data); // + imageSize = data.limit()
    }

    @Override
    public void glCompressedTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, long data) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glCompressedTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer data) {
        GLES32.glCompressedTexSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, data.limit(), data); // + imageSize = data.limit()
    }

    @Override
    public void glCopyTextureSubImage1D(int texture, int level, int xoffset, int x, int y, int width) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCopyTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
        GLES32.glCopyTexSubImage2D(texture, level, xoffset, yoffset, x, y, width, height);
    }

    @Override
    public void glCopyTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) {
        GLES32.glCopyTexSubImage3D(texture, level, xoffset, yoffset, zoffset, x, y, width, height);
    }

    @Override
    public void glTextureParameterf(int texture, int pname, float param) {
        GLES32.glTexParameterf(texture, pname, param);
    }

    @Override
    public void glTextureParameterfv(int texture, int pname, FloatBuffer params) {
        GLES32.glTexParameterfv(texture, pname, params);
    }

    @Override
    public void glTextureParameteri(int texture, int pname, int param) {
        GLES32.glTexParameteri(texture, pname, param);
    }

    @Override
    public void glTextureParameterIiv(int texture, int pname, IntBuffer params) {
        GLES32.glTexParameterIiv(texture, pname, params);
    }

    @Override
    public void glTextureParameterIi(int texture, int pname, int param) {
        tmp_int[0] = param;
        GLES32.glTexParameterIiv(texture, pname, tmp_int, 0);
    }

    @Override
    public void glTextureParameterIuiv(int texture, int pname, IntBuffer params) {
        GLES32.glTexParameterIuiv(texture, pname, params);
    }

    @Override
    public void glTextureParameterIui(int texture, int pname, int param) {
        tmp_int[0] = param;
        GLES32.glTexParameterIuiv(texture, pname, tmp_int, 0);
    }

    @Override
    public void glTextureParameteriv(int texture, int pname, IntBuffer params) {
        GLES32.glTexParameteriv(texture, pname, params);
    }

    @Override
    public void glGenerateTextureMipmap(int texture) {
        GLES32.glGenerateMipmap(texture);
    }

    @Override
    public void glBindTextureUnit(int unit, int texture) {
        GLES32.glBindTexture(texture, unit);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, int bufSize, long pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, ByteBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, ShortBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, IntBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, FloatBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, DoubleBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetCompressedTextureImage(int texture, int level, int bufSize, long pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetCompressedTextureImage(int texture, int level, ByteBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTextureLevelParameterfv(int texture, int level, int pname, FloatBuffer params) {
        GLES32.glGetTexLevelParameterfv(texture, level, pname, params);
    }

    @Override
    public float glGetTextureLevelParameterf(int texture, int level, int pname) {
        GLES32.glGetTexLevelParameterfv(texture, level, pname, tmp_float, 0);
        return tmp_float[0];
    }

    @Override
    public void glGetTextureLevelParameteriv(int texture, int level, int pname, IntBuffer params) {
        GLES32.glGetTexLevelParameteriv(texture, level, pname, params);
    }

    @Override
    public int glGetTextureLevelParameteri(int texture, int level, int pname) {
        GLES32.glGetTexLevelParameteriv(texture, level, pname, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glGetTextureParameterfv(int texture, int pname, FloatBuffer params) {
        GLES32.glGetTexParameterfv(texture, pname, params);
    }

    @Override
    public float glGetTextureParameterf(int texture, int pname) {
        GLES32.glGetTexParameterfv(texture, pname, tmp_float, 0);
        return tmp_float[0];
    }

    @Override
    public void glGetTextureParameterIiv(int texture, int pname, IntBuffer params) {
        GLES32.glGetTexParameterIiv(texture, pname, params);
    }

    @Override
    public int glGetTextureParameterIi(int texture, int pname) {
        GLES32.glGetTexParameterIiv(texture, pname, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glGetTextureParameterIuiv(int texture, int pname, IntBuffer params) {
        GLES32.glGetTexParameterIuiv(texture, pname, params);
    }

    @Override
    public int glGetTextureParameterIui(int texture, int pname) {
        GLES32.glGetTexParameterIuiv(texture, pname, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glGetTextureParameteriv(int texture, int pname, IntBuffer params) {
        GLES32.glGetTexParameteriv(texture, pname, params);
    }

    @Override
    public int glGetTextureParameteri(int texture, int pname) {
        GLES32.glGetTexParameteriv(texture, pname, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glCreateVertexArrays(IntBuffer arrays) {
        GLES32.glGenVertexArrays(arrays.limit(), arrays);
    }

    @Override
    public int glCreateVertexArrays() {
        GLES32.glGenVertexArrays(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glDisableVertexArrayAttrib(int vaobj, int index) {
        GLES32.glGetIntegerv(GLES32.GL_VERTEX_ARRAY_BINDING, tmp_int, 0);
        GLES32.glBindVertexArray(vaobj);
        GLES32.glDisableVertexAttribArray(index);
        GLES32.glBindVertexArray(tmp_int[0]);
    }

    @Override
    public void glEnableVertexArrayAttrib(int vaobj, int index) {
        GLES32.glGetIntegerv(GLES32.GL_VERTEX_ARRAY_BINDING, tmp_int, 0);
        GLES32.glBindVertexArray(vaobj);
        GLES32.glEnableVertexAttribArray(index);
        GLES32.glBindVertexArray(tmp_int[0]);
    }

    @Override
    public void glVertexArrayElementBuffer(int vaobj, int buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexArrayVertexBuffer(int vaobj, int bindingindex, int buffer, long offset, int stride) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexArrayAttribFormat(int vaobj, int attribindex, int size, int type, boolean normalized, int relativeoffset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexArrayAttribIFormat(int vaobj, int attribindex, int size, int type, int relativeoffset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexArrayAttribLFormat(int vaobj, int attribindex, int size, int type, int relativeoffset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexArrayAttribBinding(int vaobj, int attribindex, int bindingindex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexArrayBindingDivisor(int vaobj, int bindingindex, int divisor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetVertexArrayiv(int vaobj, int pname, IntBuffer param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetVertexArrayi(int vaobj, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetVertexArrayIndexediv(int vaobj, int index, int pname, IntBuffer param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetVertexArrayIndexedi(int vaobj, int index, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetVertexArrayIndexed64iv(int vaobj, int index, int pname, LongBuffer param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long glGetVertexArrayIndexed64i(int vaobj, int index, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCreateSamplers(IntBuffer samplers) {
        GLES32.glGenSamplers(samplers.limit(), samplers);
    }

    @Override
    public int glCreateSamplers() {
        GLES32.glGenSamplers(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glCreateProgramPipelines(IntBuffer pipelines) {
        GLES32.glGenProgramPipelines(pipelines.limit(), pipelines);
    }

    @Override
    public int glCreateProgramPipelines() {
        GLES32.glGenProgramPipelines(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glCreateQueries(int target, IntBuffer ids) {
        GLES32.glGenQueries(target, ids);
    }

    @Override
    public int glCreateQueries(int target) { // target ignored
        GLES32.glGenQueries(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glGetQueryBufferObjectiv(int id, int buffer, int pname, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetQueryBufferObjectuiv(int id, int buffer, int pname, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetQueryBufferObjecti64v(int id, int buffer, int pname, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetQueryBufferObjectui64v(int id, int buffer, int pname, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMemoryBarrierByRegion(int barriers) {
        GLES32.glMemoryBarrierByRegion(barriers);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int bufSize, long pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
        if(depth != 1 || zoffset != 0)
            throw new UnsupportedOperationException("Only 2D texture layer supported on GLES");
        getTextureImage(GLES20.GL_TEXTURE_2D, texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
        if(depth != 1 || zoffset != 0)
            throw new UnsupportedOperationException("Only 2D texture layer supported on GLES");
        getTextureImage(GLES20.GL_TEXTURE_2D, texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
        if(depth != 1 || zoffset != 0)
            throw new UnsupportedOperationException("Only 2D texture layer supported on GLES");
        getTextureImage(GLES20.GL_TEXTURE_2D, texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) {
        if(depth != 1 || zoffset != 0)
            throw new UnsupportedOperationException("Only 2D texture layer supported on GLES");
        getTextureImage(GLES20.GL_TEXTURE_2D, texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer pixels) {
        if(depth != 1 || zoffset != 0)
            throw new UnsupportedOperationException("Only 2D texture layer supported on GLES");
        getTextureImage(GLES20.GL_TEXTURE_2D, texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int bufSize, long pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, ByteBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, ShortBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, IntBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, FloatBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, DoubleBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTextureBarrier() {
        throw new UnsupportedOperationException(); // glBlendBarrier() exists
    }

    @Override
    public int glGetGraphicsResetStatus() {
        return GLES32.glGetGraphicsResetStatus();
    }

    @Override
    public void glGetnMapdv(int target, int query, DoubleBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double glGetnMapd(int target, int query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnMapfv(int target, int query, FloatBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float glGetnMapf(int target, int query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnMapiv(int target, int query, IntBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetnMapi(int target, int query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnPixelMapfv(int map, FloatBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnPixelMapuiv(int map, IntBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnPixelMapusv(int map, ShortBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnPolygonStipple(int bufSize, long pattern) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnPolygonStipple(ByteBuffer pattern) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, int bufSize, long img) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, ByteBuffer img) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, ShortBuffer img) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, IntBuffer img) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, FloatBuffer img) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, DoubleBuffer img) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, int bufSize, long pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, ByteBuffer pixels) {
        GLES32.glReadnPixels(x, y, width, height, format, type, pixels.remaining(), pixels);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, ShortBuffer pixels) {
        GLES32.glReadnPixels(x, y, width, height, format, type, pixels.remaining() * Short.BYTES, pixels);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, IntBuffer pixels) {
        GLES32.glReadnPixels(x, y, width, height, format, type, pixels.remaining() * Integer.BYTES, pixels);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, FloatBuffer pixels) {
        GLES32.glReadnPixels(x, y, width, height, format, type, pixels.remaining() * Float.BYTES, pixels);
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, int bufSize, long table) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, ByteBuffer table) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, ShortBuffer table) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, IntBuffer table) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, FloatBuffer table) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnConvolutionFilter(int target, int format, int type, int bufSize, long image) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnConvolutionFilter(int target, int format, int type, ByteBuffer image) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnSeparableFilter(int target, int format, int type, int rowBufSize, long row, int columnBufSize, long column, ByteBuffer span) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, ByteBuffer span) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnHistogram(int target, boolean reset, int format, int type, int bufSize, long values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnHistogram(int target, boolean reset, int format, int type, ByteBuffer values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnMinmax(int target, boolean reset, int format, int type, int bufSize, long values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnMinmax(int target, boolean reset, int format, int type, ByteBuffer values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnCompressedTexImage(int target, int level, int bufSize, long img) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnCompressedTexImage(int target, int level, ByteBuffer img) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnUniformfv(int program, int location, FloatBuffer params) {
        GLES32.glGetnUniformfv(program, location, params.limit(), params);
    }

    @Override
    public float glGetnUniformf(int program, int location) {
        GLES32.glGetnUniformfv(program, location, 1, tmp_float, 0);
        return tmp_float[0];
    }

    @Override
    public void glGetnUniformdv(int program, int location, DoubleBuffer params) {
        final float[] paramsArr = new float[params.limit()];
        GLES32.glGetnUniformfv(program, location, paramsArr.length, paramsArr, 0);
        writeToBuffer(paramsArr, params);
    }

    @Override
    public double glGetnUniformd(int program, int location) {
        return this.glGetnUniformf(program, location);
    }

    @Override
    public void glGetnUniformiv(int program, int location, IntBuffer params) {
        GLES32.glGetnUniformiv(program, location, params.limit(), params);
    }

    @Override
    public int glGetnUniformi(int program, int location) {
        GLES32.glGetnUniformiv(program, location, Integer.BYTES, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glGetnUniformuiv(int program, int location, IntBuffer params) {
        GLES32.glGetnUniformuiv(program, location, params.remaining() * Integer.BYTES, params);
    }

    @Override
    public int glGetnUniformui(int program, int location) {
        GLES32.glGetnUniformuiv(program, location, Integer.BYTES, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glCreateTransformFeedbacks(int[] ids) {
        GLES32.glGenTransformFeedbacks(ids.length, ids, 0);
    }

    @Override
    public void glGetTransformFeedbackiv(int xfb, int pname, int[] param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTransformFeedbacki_v(int xfb, int pname, int index, int[] param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetTransformFeedbacki64_v(int xfb, int pname, int index, long[] param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCreateBuffers(int[] buffers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferStorage(int buffer, short[] data, int flags) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferStorage(int buffer, int[] data, int flags) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferStorage(int buffer, float[] data, int flags) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferStorage(int buffer, double[] data, int flags) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferData(int buffer, short[] data, int usage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferData(int buffer, int[] data, int usage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferData(int buffer, long[] data, int usage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferData(int buffer, float[] data, int usage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferData(int buffer, double[] data, int usage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, short[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, long[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, double[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, short[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, short[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferParameteriv(int buffer, int pname, int[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferParameteri64v(int buffer, int pname, long[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, short[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, long[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, double[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCreateFramebuffers(int[] framebuffers) {
        GLES32.glGenFramebuffers(framebuffers.length, framebuffers, 0);
    }

    @Override
    public void glNamedFramebufferDrawBuffers(int framebuffer, int[] bufs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glInvalidateNamedFramebufferData(int framebuffer, int[] attachments) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glInvalidateNamedFramebufferSubData(int framebuffer, int[] attachments, int x, int y, int width, int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedFramebufferiv(int framebuffer, int buffer, int drawbuffer, int[] value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedFramebufferuiv(int framebuffer, int buffer, int drawbuffer, int[] value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glClearNamedFramebufferfv(int framebuffer, int buffer, int drawbuffer, float[] value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedFramebufferParameteriv(int framebuffer, int pname, int[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetNamedFramebufferAttachmentParameteriv(int framebuffer, int attachment, int pname, int[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCreateRenderbuffers(int[] renderbuffers) {
        GLES32.glGenRenderbuffers(renderbuffers.length, renderbuffers, 0);
    }

    @Override
    public void glGetNamedRenderbufferParameteriv(int renderbuffer, int pname, int[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCreateTextures(int target, int[] textures) {
        GLES32.glGenTextures(textures.length, textures, 0);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, short[] pixels) {
        final ShortBuffer pixelsBuf = createBuffer(pixels);
        GLES32.glTexSubImage2D(texture, level, xoffset, 0, width, 1, format, type, pixelsBuf);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, int[] pixels) {
        final IntBuffer pixelsBuf = createBuffer(pixels);
        GLES32.glTexSubImage2D(texture, level, xoffset, 0, width, 1, format, type, pixelsBuf);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, float[] pixels) {
        final FloatBuffer pixelsBuf = createBuffer(pixels);
        GLES32.glTexSubImage2D(texture, level, xoffset, 0, width, 1, format, type, pixelsBuf);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, double[] pixels) {
        final DoubleBuffer pixelsBuf = createBuffer(pixels);
        GLES32.glTexSubImage2D(texture, level, xoffset, 0, width, 1, format, type, pixelsBuf);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, short[] pixels) {
        final ShortBuffer pixelsBuf = BufferUtils.createShortBuffer(pixels.length);
        GLES32.glTexSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, int[] pixels) {
        final IntBuffer pixelsBuf = BufferUtils.createIntBuffer(pixels.length);
        GLES32.glTexSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, float[] pixels) {
        final FloatBuffer pixelsBuf = BufferUtils.createFloatBuffer(pixels.length);
        GLES32.glTexSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, double[] pixels) {
        final DoubleBuffer pixelsBuf = BufferUtils.createDoubleBuffer(pixels.length);
        GLES32.glTexSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, short[] pixels) {
        final ShortBuffer pixelsBuf = BufferUtils.createShortBuffer(pixels.length);
        GLES32.glTexSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int[] pixels) {
        final IntBuffer pixelsBuf = BufferUtils.createIntBuffer(pixels.length);
        GLES32.glTexSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, float[] pixels) {
        final FloatBuffer pixelsBuf = BufferUtils.createFloatBuffer(pixels.length);
        GLES32.glTexSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, double[] pixels) {
        final DoubleBuffer pixelsBuf = BufferUtils.createDoubleBuffer(pixels.length);
        GLES32.glTexSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glTextureParameterfv(int texture, int pname, float[] params) {
        GLES32.glTexParameterfv(texture, pname, params, 0);
    }

    @Override
    public void glTextureParameterIiv(int texture, int pname, int[] params) {
        GLES32.glTexParameterIiv(texture, pname, params, 0);
    }

    @Override
    public void glTextureParameterIuiv(int texture, int pname, int[] params) {
        GLES32.glTexParameterIuiv(texture, pname, params, 0);
    }

    @Override
    public void glTextureParameteriv(int texture, int pname, int[] params) {
        GLES32.glTexParameteriv(texture, pname, params, 0);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, short[] pixels) {
        final ShortBuffer pixelsBuf = BufferUtils.createShortBuffer(pixels.length);
        getTexImage(texture, level, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, int[] pixels) {
        final IntBuffer pixelsBuf = BufferUtils.createIntBuffer(pixels.length);
        getTexImage(texture, level, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, float[] pixels) {
        final FloatBuffer pixelsBuf = BufferUtils.createFloatBuffer(pixels.length);
        getTexImage(texture, level, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, double[] pixels) {
        final DoubleBuffer pixelsBuf = BufferUtils.createDoubleBuffer(pixels.length);
        getTexImage(texture, level, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glGetTextureLevelParameterfv(int texture, int level, int pname, float[] params) {
        GLES32.glGetTexLevelParameterfv(texture, level, pname, params, 0);
    }

    @Override
    public void glGetTextureLevelParameteriv(int texture, int level, int pname, int[] params) {
        GLES32.glGetTexLevelParameteriv(texture, level, pname, params, 0);
    }

    @Override
    public void glGetTextureParameterfv(int texture, int pname, float[] params) {
        GLES32.glGetTexParameterfv(texture, pname, params, 0);
    }

    @Override
    public void glGetTextureParameterIiv(int texture, int pname, int[] params) {
        GLES32.glGetTexParameterIiv(texture, pname, params, 0);
    }

    @Override
    public void glGetTextureParameterIuiv(int texture, int pname, int[] params) {
        GLES32.glGetTexParameterIuiv(texture, pname, params, 0);
    }

    @Override
    public void glGetTextureParameteriv(int texture, int pname, int[] params) {
        GLES32.glGetTexParameteriv(texture, pname, params, 0);
    }

    @Override
    public void glCreateVertexArrays(int[] arrays) {
        GLES32.glGenVertexArrays(arrays.length, arrays, 0);
    }

    @Override
    public void glGetVertexArrayiv(int vaobj, int pname, int[] param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetVertexArrayIndexediv(int vaobj, int index, int pname, int[] param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetVertexArrayIndexed64iv(int vaobj, int index, int pname, long[] param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCreateSamplers(int[] samplers) {
        GLES32.glGenSamplers(samplers.length, samplers, 0);
    }

    @Override
    public void glCreateProgramPipelines(int[] pipelines) {
        GLES32.glGenProgramPipelines(pipelines.length, pipelines, 0);
    }

    @Override
    public void glCreateQueries(int target, int[] ids) { // target ignored
        GLES32.glGenQueries(ids.length, ids, 0);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, short[] pixels) {
        if(depth != 1 || zoffset != 0)
            throw new UnsupportedOperationException("Only 2D texture layer supported on GLES");
        final ShortBuffer pixelsBuf = BufferUtils.createShortBuffer(pixels.length);
        getTexImage(texture, level, xoffset, yoffset, width, height, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int[] pixels) {
        if(depth != 1 || zoffset != 0)
            throw new UnsupportedOperationException("Only 2D texture layer supported on GLES");
        final IntBuffer pixelsBuf = BufferUtils.createIntBuffer(pixels.length);
        getTexImage(texture, level, xoffset, yoffset, width, height, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, float[] pixels) {
        if(depth != 1 || zoffset != 0)
            throw new UnsupportedOperationException("Only 2D texture layer supported on GLES");
        final FloatBuffer pixelsBuf = BufferUtils.createFloatBuffer(pixels.length);
        getTexImage(texture, level, xoffset, yoffset, width, height, format, type, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, double[] pixels) {
        GLES32.glTexSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, createBuffer(pixels));
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, short[] pixels) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int[] pixels) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, float[] pixels) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, double[] pixels) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glGetnMapdv(int target, int query, double[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnMapfv(int target, int query, float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnMapiv(int target, int query, int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnPixelMapfv(int map, float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnPixelMapuiv(int map, int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnPixelMapusv(int map, short[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, short[] img) {
        final ShortBuffer imgBuf = BufferUtils.createShortBuffer(img.length);
        getTexImage(tex, level, format, type, imgBuf);
        imgBuf.get(img);
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, int[] img) {
        final IntBuffer imgBuf = BufferUtils.createIntBuffer(img.length);
        getTexImage(tex, level, format, type, imgBuf);
        imgBuf.get(img);
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, float[] img) {
        final FloatBuffer imgBuf = BufferUtils.createFloatBuffer(img.length);
        getTexImage(tex, level, format, type, imgBuf);
        imgBuf.get(img);
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, double[] img) {
        final DoubleBuffer imgBuf = BufferUtils.createDoubleBuffer(img.length);
        getTexImage(tex, level, format, type, imgBuf);
        imgBuf.get(img);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, short[] pixels) {
        final ShortBuffer pixelsBuf = BufferUtils.createShortBuffer(pixels.length);
        GLES32.glReadnPixels(x, y, width, height, format, type, pixels.length * Short.BYTES, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, int[] pixels) {
        final IntBuffer pixelsBuf = BufferUtils.createIntBuffer(pixels.length);
        GLES32.glReadnPixels(x, y, width, height, format, type, pixels.length * Integer.BYTES, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, float[] pixels) {
        final FloatBuffer pixelsBuf = BufferUtils.createFloatBuffer(pixels.length);
        GLES32.glReadnPixels(x, y, width, height, format, type, pixels.length * Float.BYTES, pixelsBuf);
        pixelsBuf.get(pixels);
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, short[] table) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, int[] table) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, float[] table) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetnUniformfv(int program, int location, float[] params) {
        GLES32.glGetnUniformfv(program, location, params.length * Float.BYTES, params, 0);
    }

    @Override
    public void glGetnUniformdv(int program, int location, double[] params) {
        final int length = params.length;
        final float[] paramsFloat = new float[length];

        GLES32.glGetnUniformfv(program, location, length * Float.BYTES, paramsFloat, 0);

        for(int i = 0; i < length; i++)
            params[i] = paramsFloat[i];
    }

    @Override
    public void glGetnUniformiv(int program, int location, int[] params) {
        GLES32.glGetnUniformiv(program, location, params.length * Integer.BYTES, params, 0);
    }

    @Override
    public void glGetnUniformuiv(int program, int location, int[] params) {
        GLES32.glGetnUniformuiv(program, location, params.length * Integer.BYTES, params, 0);
    }

}