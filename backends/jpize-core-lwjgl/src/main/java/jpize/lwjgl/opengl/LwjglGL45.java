package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL45I;
import org.lwjgl.opengl.GL45;
import java.nio.*;

public class LwjglGL45 extends LwjglGL44 implements GL45I {

    @Override
    public void glClipControl(int origin, int depth) {
        GL45.glClipControl(origin, depth);
    }

    @Override
    public void glCreateTransformFeedbacks(IntBuffer ids) {
        GL45.glCreateTransformFeedbacks(ids);
    }

    @Override
    public int glCreateTransformFeedbacks() {
        return GL45.glCreateTransformFeedbacks();
    }

    @Override
    public void glTransformFeedbackBufferBase(int xfb, int index, int buffer) {
        GL45.glTransformFeedbackBufferBase(xfb, index, buffer);
    }

    @Override
    public void glTransformFeedbackBufferRange(int xfb, int index, int buffer, long offset, long size) {
        GL45.glTransformFeedbackBufferRange(xfb, index, buffer, offset, size);
    }

    @Override
    public void glGetTransformFeedbackiv(int xfb, int pname, IntBuffer param) {
        GL45.glGetTransformFeedbackiv(xfb, pname, param);
    }

    @Override
    public int glGetTransformFeedbacki(int xfb, int pname) {
        return GL45.glGetTransformFeedbacki(xfb, pname);
    }

    @Override
    public void glGetTransformFeedbacki_v(int xfb, int pname, int index, IntBuffer param) {
        GL45.glGetTransformFeedbacki_v(xfb, pname, index, param);
    }

    @Override
    public int glGetTransformFeedbacki(int xfb, int pname, int index) {
        return GL45.glGetTransformFeedbacki(xfb, pname, index);
    }

    @Override
    public void glGetTransformFeedbacki64_v(int xfb, int pname, int index, LongBuffer param) {
        GL45.glGetTransformFeedbacki64_v(xfb, pname, index, param);
    }

    @Override
    public long glGetTransformFeedbacki64(int xfb, int pname, int index) {
        return GL45.glGetTransformFeedbacki64(xfb, pname, index);
    }

    @Override
    public void glCreateBuffers(IntBuffer buffers) {
        GL45.glCreateBuffers(buffers);
    }

    @Override
    public int glCreateBuffers() {
        return GL45.glCreateBuffers();
    }

    @Override
    public void glNamedBufferStorage(int buffer, long size, int flags) {
        GL45.glNamedBufferStorage(buffer, size, flags);
    }

    @Override
    public void glNamedBufferStorage(int buffer, ByteBuffer data, int flags) {
        GL45.glNamedBufferStorage(buffer, data, flags);
    }

    @Override
    public void glNamedBufferStorage(int buffer, ShortBuffer data, int flags) {
        GL45.glNamedBufferStorage(buffer, data, flags);
    }

    @Override
    public void glNamedBufferStorage(int buffer, IntBuffer data, int flags) {
        GL45.glNamedBufferStorage(buffer, data, flags);
    }

    @Override
    public void glNamedBufferStorage(int buffer, FloatBuffer data, int flags) {
        GL45.glNamedBufferStorage(buffer, data, flags);
    }

    @Override
    public void glNamedBufferStorage(int buffer, DoubleBuffer data, int flags) {
        GL45.glNamedBufferStorage(buffer, data, flags);
    }

    @Override
    public void glNamedBufferData(int buffer, long size, int usage) {
        GL45.glNamedBufferData(buffer, size, usage);
    }

    @Override
    public void glNamedBufferData(int buffer, ByteBuffer data, int usage) {
        GL45.glNamedBufferData(buffer, data, usage);
    }

    @Override
    public void glNamedBufferData(int buffer, ShortBuffer data, int usage) {
        GL45.glNamedBufferData(buffer, data, usage);
    }

    @Override
    public void glNamedBufferData(int buffer, IntBuffer data, int usage) {
        GL45.glNamedBufferData(buffer, data, usage);
    }

    @Override
    public void glNamedBufferData(int buffer, LongBuffer data, int usage) {
        GL45.glNamedBufferData(buffer, data, usage);
    }

    @Override
    public void glNamedBufferData(int buffer, FloatBuffer data, int usage) {
        GL45.glNamedBufferData(buffer, data, usage);
    }

    @Override
    public void glNamedBufferData(int buffer, DoubleBuffer data, int usage) {
        GL45.glNamedBufferData(buffer, data, usage);
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, ByteBuffer data) {
        GL45.glNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, ShortBuffer data) {
        GL45.glNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, IntBuffer data) {
        GL45.glNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, LongBuffer data) {
        GL45.glNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, FloatBuffer data) {
        GL45.glNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, DoubleBuffer data) {
        GL45.glNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glCopyNamedBufferSubData(int readBuffer, int writeBuffer, long readOffset, long writeOffset, long size) {
        GL45.glCopyNamedBufferSubData(readBuffer, writeBuffer, readOffset, writeOffset, size);
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, ByteBuffer data) {
        GL45.glClearNamedBufferData(buffer, internalformat, format, type, data);
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, ShortBuffer data) {
        GL45.glClearNamedBufferData(buffer, internalformat, format, type, data);
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, IntBuffer data) {
        GL45.glClearNamedBufferData(buffer, internalformat, format, type, data);
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, FloatBuffer data) {
        GL45.glClearNamedBufferData(buffer, internalformat, format, type, data);
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, ByteBuffer data) {
        GL45.glClearNamedBufferSubData(buffer, internalformat, offset, size, format, type, data);
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, ShortBuffer data) {
        GL45.glClearNamedBufferSubData(buffer, internalformat, offset, size, format, type, data);
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, IntBuffer data) {
        GL45.glClearNamedBufferSubData(buffer, internalformat, offset, size, format, type, data);
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, FloatBuffer data) {
        GL45.glClearNamedBufferSubData(buffer, internalformat, offset, size, format, type, data);
    }

    @Override
    public ByteBuffer glMapNamedBuffer(int buffer, int access) {
        return GL45.glMapNamedBuffer(buffer, access);
    }

    @Override
    public ByteBuffer glMapNamedBuffer(int buffer, int access, ByteBuffer old_buffer) {
        return GL45.glMapNamedBuffer(buffer, access, old_buffer);
    }

    @Override
    public ByteBuffer glMapNamedBuffer(int buffer, int access, long length, ByteBuffer old_buffer) {
        return GL45.glMapNamedBuffer(buffer, access, length, old_buffer);
    }

    @Override
    public ByteBuffer glMapNamedBufferRange(int buffer, long offset, long length, int access) {
        return GL45.glMapNamedBufferRange(buffer, offset, length, access);
    }

    @Override
    public ByteBuffer glMapNamedBufferRange(int buffer, long offset, long length, int access, ByteBuffer old_buffer) {
        return GL45.glMapNamedBufferRange(buffer, offset, length, access, old_buffer);
    }

    @Override
    public boolean glUnmapNamedBuffer(int buffer) {
        return GL45.glUnmapNamedBuffer(buffer);
    }

    @Override
    public void glFlushMappedNamedBufferRange(int buffer, long offset, long length) {
        GL45.glFlushMappedNamedBufferRange(buffer, offset, length);
    }

    @Override
    public void glGetNamedBufferParameteriv(int buffer, int pname, IntBuffer params) {
        GL45.glGetNamedBufferParameteriv(buffer, pname, params);
    }

    @Override
    public int glGetNamedBufferParameteri(int buffer, int pname) {
        return GL45.glGetNamedBufferParameteri(buffer, pname);
    }

    @Override
    public void glGetNamedBufferParameteri64v(int buffer, int pname, LongBuffer params) {
        GL45.glGetNamedBufferParameteri64v(buffer, pname, params);
    }

    @Override
    public long glGetNamedBufferParameteri64(int buffer, int pname) {
        return GL45.glGetNamedBufferParameteri64(buffer, pname);
    }

    @Override
    public long glGetNamedBufferPointer(int buffer, int pname) {
        return GL45.glGetNamedBufferPointer(buffer, pname);
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, ByteBuffer data) {
        GL45.glGetNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, ShortBuffer data) {
        GL45.glGetNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, IntBuffer data) {
        GL45.glGetNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, LongBuffer data) {
        GL45.glGetNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, FloatBuffer data) {
        GL45.glGetNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, DoubleBuffer data) {
        GL45.glGetNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glCreateFramebuffers(IntBuffer framebuffers) {
        GL45.glCreateFramebuffers(framebuffers);
    }

    @Override
    public int glCreateFramebuffers() {
        return GL45.glCreateFramebuffers();
    }

    @Override
    public void glNamedFramebufferRenderbuffer(int framebuffer, int attachment, int renderbuffertarget, int renderbuffer) {
        GL45.glNamedFramebufferRenderbuffer(framebuffer, attachment, renderbuffertarget, renderbuffer);
    }

    @Override
    public void glNamedFramebufferParameteri(int framebuffer, int pname, int param) {
        GL45.glNamedFramebufferParameteri(framebuffer, pname, param);
    }

    @Override
    public void glNamedFramebufferTexture(int framebuffer, int attachment, int texture, int level) {
        GL45.glNamedFramebufferTexture(framebuffer, attachment, texture, level);
    }

    @Override
    public void glNamedFramebufferTextureLayer(int framebuffer, int attachment, int texture, int level, int layer) {
        GL45.glNamedFramebufferTextureLayer(framebuffer, attachment, texture, level, layer);
    }

    @Override
    public void glNamedFramebufferDrawBuffer(int framebuffer, int buf) {
        GL45.glNamedFramebufferDrawBuffer(framebuffer, buf);
    }

    @Override
    public void glNamedFramebufferDrawBuffers(int framebuffer, IntBuffer bufs) {
        GL45.glNamedFramebufferDrawBuffers(framebuffer, bufs);
    }

    @Override
    public void glNamedFramebufferDrawBuffers(int framebuffer, int buf) {
        GL45.glNamedFramebufferDrawBuffers(framebuffer, buf);
    }

    @Override
    public void glNamedFramebufferReadBuffer(int framebuffer, int src) {
        GL45.glNamedFramebufferReadBuffer(framebuffer, src);
    }

    @Override
    public void glInvalidateNamedFramebufferData(int framebuffer, IntBuffer attachments) {
        GL45.glInvalidateNamedFramebufferData(framebuffer, attachments);
    }

    @Override
    public void glInvalidateNamedFramebufferData(int framebuffer, int attachment) {
        GL45.glInvalidateNamedFramebufferData(framebuffer, attachment);
    }

    @Override
    public void glInvalidateNamedFramebufferSubData(int framebuffer, IntBuffer attachments, int x, int y, int width, int height) {
        GL45.glInvalidateNamedFramebufferSubData(framebuffer, attachments, x, y, width, height);
    }

    @Override
    public void glInvalidateNamedFramebufferSubData(int framebuffer, int attachment, int x, int y, int width, int height) {
        GL45.glInvalidateNamedFramebufferSubData(framebuffer, attachment, x, y, width, height);
    }

    @Override
    public void glClearNamedFramebufferiv(int framebuffer, int buffer, int drawbuffer, IntBuffer value) {
        GL45.glClearNamedFramebufferiv(framebuffer, buffer, drawbuffer, value);
    }

    @Override
    public void glClearNamedFramebufferuiv(int framebuffer, int buffer, int drawbuffer, IntBuffer value) {
        GL45.glClearNamedFramebufferuiv(framebuffer, buffer, drawbuffer, value);
    }

    @Override
    public void glClearNamedFramebufferfv(int framebuffer, int buffer, int drawbuffer, FloatBuffer value) {
        GL45.glClearNamedFramebufferfv(framebuffer, buffer, drawbuffer, value);
    }

    @Override
    public void glClearNamedFramebufferfi(int framebuffer, int buffer, int drawbuffer, float depth, int stencil) {
        GL45.glClearNamedFramebufferfi(framebuffer, buffer, drawbuffer, depth, stencil);
    }

    @Override
    public void glBlitNamedFramebuffer(int readFramebuffer, int drawFramebuffer, int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter) {
        GL45.glBlitNamedFramebuffer(readFramebuffer, drawFramebuffer, srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask, filter);
    }

    @Override
    public int glCheckNamedFramebufferStatus(int framebuffer, int target) {
        return GL45.glCheckNamedFramebufferStatus(framebuffer, target);
    }

    @Override
    public void glGetNamedFramebufferParameteriv(int framebuffer, int pname, IntBuffer params) {
        GL45.glGetNamedFramebufferParameteriv(framebuffer, pname, params);
    }

    @Override
    public int glGetNamedFramebufferParameteri(int framebuffer, int pname) {
        return GL45.glGetNamedFramebufferParameteri(framebuffer, pname);
    }

    @Override
    public void glGetNamedFramebufferAttachmentParameteriv(int framebuffer, int attachment, int pname, IntBuffer params) {
        GL45.glGetNamedFramebufferAttachmentParameteriv(framebuffer, attachment, pname, params);
    }

    @Override
    public int glGetNamedFramebufferAttachmentParameteri(int framebuffer, int attachment, int pname) {
        return GL45.glGetNamedFramebufferAttachmentParameteri(framebuffer, attachment, pname);
    }

    @Override
    public void glCreateRenderbuffers(IntBuffer renderbuffers) {
        GL45.glCreateRenderbuffers(renderbuffers);
    }

    @Override
    public int glCreateRenderbuffers() {
        return GL45.glCreateRenderbuffers();
    }

    @Override
    public void glNamedRenderbufferStorage(int renderbuffer, int internalformat, int width, int height) {
        GL45.glNamedRenderbufferStorage(renderbuffer, internalformat, width, height);
    }

    @Override
    public void glNamedRenderbufferStorageMultisample(int renderbuffer, int samples, int internalformat, int width, int height) {
        GL45.glNamedRenderbufferStorageMultisample(renderbuffer, samples, internalformat, width, height);
    }

    @Override
    public void glGetNamedRenderbufferParameteriv(int renderbuffer, int pname, IntBuffer params) {
        GL45.glGetNamedRenderbufferParameteriv(renderbuffer, pname, params);
    }

    @Override
    public int glGetNamedRenderbufferParameteri(int renderbuffer, int pname) {
        return GL45.glGetNamedRenderbufferParameteri(renderbuffer, pname);
    }

    @Override
    public void glCreateTextures(int target, IntBuffer textures) {
        GL45.glCreateTextures(target, textures);
    }

    @Override
    public int glCreateTextures(int target) {
        return GL45.glCreateTextures(target);
    }

    @Override
    public void glTextureBuffer(int texture, int internalformat, int buffer) {
        GL45.glTextureBuffer(texture, internalformat, buffer);
    }

    @Override
    public void glTextureBufferRange(int texture, int internalformat, int buffer, long offset, long size) {
        GL45.glTextureBufferRange(texture, internalformat, buffer, offset, size);
    }

    @Override
    public void glTextureStorage1D(int texture, int levels, int internalformat, int width) {
        GL45.glTextureStorage1D(texture, levels, internalformat, width);
    }

    @Override
    public void glTextureStorage2D(int texture, int levels, int internalformat, int width, int height) {
        GL45.glTextureStorage2D(texture, levels, internalformat, width, height);
    }

    @Override
    public void glTextureStorage3D(int texture, int levels, int internalformat, int width, int height, int depth) {
        GL45.glTextureStorage3D(texture, levels, internalformat, width, height, depth);
    }

    @Override
    public void glTextureStorage2DMultisample(int texture, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) {
        GL45.glTextureStorage2DMultisample(texture, samples, internalformat, width, height, fixedsamplelocations);
    }

    @Override
    public void glTextureStorage3DMultisample(int texture, int samples, int internalformat, int width, int height, int depth, boolean fixedsamplelocations) {
        GL45.glTextureStorage3DMultisample(texture, samples, internalformat, width, height, depth, fixedsamplelocations);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) {
        GL45.glTextureSubImage1D(texture, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, long pixels) {
        GL45.glTextureSubImage1D(texture, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) {
        GL45.glTextureSubImage1D(texture, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, IntBuffer pixels) {
        GL45.glTextureSubImage1D(texture, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, FloatBuffer pixels) {
        GL45.glTextureSubImage1D(texture, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, DoubleBuffer pixels) {
        GL45.glTextureSubImage1D(texture, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) {
        GL45.glTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, long pixels) {
        GL45.glTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) {
        GL45.glTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) {
        GL45.glTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, FloatBuffer pixels) {
        GL45.glTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, DoubleBuffer pixels) {
        GL45.glTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
        GL45.glTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels) {
        GL45.glTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
        GL45.glTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
        GL45.glTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) {
        GL45.glTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer pixels) {
        GL45.glTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glCompressedTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int imageSize, long data) {
        GL45.glCompressedTextureSubImage1D(texture, level, xoffset, width, format, imageSize, data);
    }

    @Override
    public void glCompressedTextureSubImage1D(int texture, int level, int xoffset, int width, int format, ByteBuffer data) {
        GL45.glCompressedTextureSubImage1D(texture, level, xoffset, width, format, data);
    }

    @Override
    public void glCompressedTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, long data) {
        GL45.glCompressedTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, imageSize, data);
    }

    @Override
    public void glCompressedTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer data) {
        GL45.glCompressedTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, data);
    }

    @Override
    public void glCompressedTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, long data) {
        GL45.glCompressedTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data);
    }

    @Override
    public void glCompressedTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer data) {
        GL45.glCompressedTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, data);
    }

    @Override
    public void glCopyTextureSubImage1D(int texture, int level, int xoffset, int x, int y, int width) {
        GL45.glCopyTextureSubImage1D(texture, level, xoffset, x, y, width);
    }

    @Override
    public void glCopyTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
        GL45.glCopyTextureSubImage2D(texture, level, xoffset, yoffset, x, y, width, height);
    }

    @Override
    public void glCopyTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) {
        GL45.glCopyTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, x, y, width, height);
    }

    @Override
    public void glTextureParameterf(int texture, int pname, float param) {
        GL45.glTextureParameterf(texture, pname, param);
    }

    @Override
    public void glTextureParameterfv(int texture, int pname, FloatBuffer params) {
        GL45.glTextureParameterfv(texture, pname, params);
    }

    @Override
    public void glTextureParameteri(int texture, int pname, int param) {
        GL45.glTextureParameteri(texture, pname, param);
    }

    @Override
    public void glTextureParameterIiv(int texture, int pname, IntBuffer params) {
        GL45.glTextureParameterIiv(texture, pname, params);
    }

    @Override
    public void glTextureParameterIi(int texture, int pname, int param) {
        GL45.glTextureParameterIi(texture, pname, param);
    }

    @Override
    public void glTextureParameterIuiv(int texture, int pname, IntBuffer params) {
        GL45.glTextureParameterIuiv(texture, pname, params);
    }

    @Override
    public void glTextureParameterIui(int texture, int pname, int param) {
        GL45.glTextureParameterIui(texture, pname, param);
    }

    @Override
    public void glTextureParameteriv(int texture, int pname, IntBuffer params) {
        GL45.glTextureParameteriv(texture, pname, params);
    }

    @Override
    public void glGenerateTextureMipmap(int texture) {
        GL45.glGenerateTextureMipmap(texture);
    }

    @Override
    public void glBindTextureUnit(int unit, int texture) {
        GL45.glBindTextureUnit(unit, texture);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, int bufSize, long pixels) {
        GL45.glGetTextureImage(texture, level, format, type, bufSize, pixels);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, ByteBuffer pixels) {
        GL45.glGetTextureImage(texture, level, format, type, pixels);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, ShortBuffer pixels) {
        GL45.glGetTextureImage(texture, level, format, type, pixels);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, IntBuffer pixels) {
        GL45.glGetTextureImage(texture, level, format, type, pixels);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, FloatBuffer pixels) {
        GL45.glGetTextureImage(texture, level, format, type, pixels);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, DoubleBuffer pixels) {
        GL45.glGetTextureImage(texture, level, format, type, pixels);
    }

    @Override
    public void glGetCompressedTextureImage(int texture, int level, int bufSize, long pixels) {
        GL45.glGetCompressedTextureImage(texture, level, bufSize, pixels);
    }

    @Override
    public void glGetCompressedTextureImage(int texture, int level, ByteBuffer pixels) {
        GL45.glGetCompressedTextureImage(texture, level, pixels);
    }

    @Override
    public void glGetTextureLevelParameterfv(int texture, int level, int pname, FloatBuffer params) {
        GL45.glGetTextureLevelParameterfv(texture, level, pname, params);
    }

    @Override
    public float glGetTextureLevelParameterf(int texture, int level, int pname) {
        return GL45.glGetTextureLevelParameterf(texture, level, pname);
    }

    @Override
    public void glGetTextureLevelParameteriv(int texture, int level, int pname, IntBuffer params) {
        GL45.glGetTextureLevelParameteriv(texture, level, pname, params);
    }

    @Override
    public int glGetTextureLevelParameteri(int texture, int level, int pname) {
        return GL45.glGetTextureLevelParameteri(texture, level, pname);
    }

    @Override
    public void glGetTextureParameterfv(int texture, int pname, FloatBuffer params) {
        GL45.glGetTextureParameterfv(texture, pname, params);
    }

    @Override
    public float glGetTextureParameterf(int texture, int pname) {
        return GL45.glGetTextureParameterf(texture, pname);
    }

    @Override
    public void glGetTextureParameterIiv(int texture, int pname, IntBuffer params) {
        GL45.glGetTextureParameterIiv(texture, pname, params);
    }

    @Override
    public int glGetTextureParameterIi(int texture, int pname) {
        return GL45.glGetTextureParameterIi(texture, pname);
    }

    @Override
    public void glGetTextureParameterIuiv(int texture, int pname, IntBuffer params) {
        GL45.glGetTextureParameterIuiv(texture, pname, params);
    }

    @Override
    public int glGetTextureParameterIui(int texture, int pname) {
        return GL45.glGetTextureParameterIui(texture, pname);
    }

    @Override
    public void glGetTextureParameteriv(int texture, int pname, IntBuffer params) {
        GL45.glGetTextureParameteriv(texture, pname, params);
    }

    @Override
    public int glGetTextureParameteri(int texture, int pname) {
        return GL45.glGetTextureParameteri(texture, pname);
    }

    @Override
    public void glCreateVertexArrays(IntBuffer arrays) {
        GL45.glCreateVertexArrays(arrays);
    }

    @Override
    public int glCreateVertexArrays() {
        return GL45.glCreateVertexArrays();
    }

    @Override
    public void glDisableVertexArrayAttrib(int vaobj, int index) {
        GL45.glDisableVertexArrayAttrib(vaobj, index);
    }

    @Override
    public void glEnableVertexArrayAttrib(int vaobj, int index) {
        GL45.glEnableVertexArrayAttrib(vaobj, index);
    }

    @Override
    public void glVertexArrayElementBuffer(int vaobj, int buffer) {
        GL45.glVertexArrayElementBuffer(vaobj, buffer);
    }

    @Override
    public void glVertexArrayVertexBuffer(int vaobj, int bindingindex, int buffer, long offset, int stride) {
        GL45.glVertexArrayVertexBuffer(vaobj, bindingindex, buffer, offset, stride);
    }

    @Override
    public void glVertexArrayAttribFormat(int vaobj, int attribindex, int size, int type, boolean normalized, int relativeoffset) {
        GL45.glVertexArrayAttribFormat(vaobj, attribindex, size, type, normalized, relativeoffset);
    }

    @Override
    public void glVertexArrayAttribIFormat(int vaobj, int attribindex, int size, int type, int relativeoffset) {
        GL45.glVertexArrayAttribIFormat(vaobj, attribindex, size, type, relativeoffset);
    }

    @Override
    public void glVertexArrayAttribLFormat(int vaobj, int attribindex, int size, int type, int relativeoffset) {
        GL45.glVertexArrayAttribLFormat(vaobj, attribindex, size, type, relativeoffset);
    }

    @Override
    public void glVertexArrayAttribBinding(int vaobj, int attribindex, int bindingindex) {
        GL45.glVertexArrayAttribBinding(vaobj, attribindex, bindingindex);
    }

    @Override
    public void glVertexArrayBindingDivisor(int vaobj, int bindingindex, int divisor) {
        GL45.glVertexArrayBindingDivisor(vaobj, bindingindex, divisor);
    }

    @Override
    public void glGetVertexArrayiv(int vaobj, int pname, IntBuffer param) {
        GL45.glGetVertexArrayiv(vaobj, pname, param);
    }

    @Override
    public int glGetVertexArrayi(int vaobj, int pname) {
        return GL45.glGetVertexArrayi(vaobj, pname);
    }

    @Override
    public void glGetVertexArrayIndexediv(int vaobj, int index, int pname, IntBuffer param) {
        GL45.glGetVertexArrayIndexediv(vaobj, index, pname, param);
    }

    @Override
    public int glGetVertexArrayIndexedi(int vaobj, int index, int pname) {
        return GL45.glGetVertexArrayIndexedi(vaobj, index, pname);
    }

    @Override
    public void glGetVertexArrayIndexed64iv(int vaobj, int index, int pname, LongBuffer param) {
        GL45.glGetVertexArrayIndexed64iv(vaobj, index, pname, param);
    }

    @Override
    public long glGetVertexArrayIndexed64i(int vaobj, int index, int pname) {
        return GL45.glGetVertexArrayIndexed64i(vaobj, index, pname);
    }

    @Override
    public void glCreateSamplers(IntBuffer samplers) {
        GL45.glCreateSamplers(samplers);
    }

    @Override
    public int glCreateSamplers() {
        return GL45.glCreateSamplers();
    }

    @Override
    public void glCreateProgramPipelines(IntBuffer pipelines) {
        GL45.glCreateProgramPipelines(pipelines);
    }

    @Override
    public int glCreateProgramPipelines() {
        return GL45.glCreateProgramPipelines();
    }

    @Override
    public void glCreateQueries(int target, IntBuffer ids) {
        GL45.glCreateQueries(target, ids);
    }

    @Override
    public int glCreateQueries(int target) {
        return GL45.glCreateQueries(target);
    }

    @Override
    public void glGetQueryBufferObjectiv(int id, int buffer, int pname, long offset) {
        GL45.glGetQueryBufferObjectiv(id, buffer, pname, offset);
    }

    @Override
    public void glGetQueryBufferObjectuiv(int id, int buffer, int pname, long offset) {
        GL45.glGetQueryBufferObjectuiv(id, buffer, pname, offset);
    }

    @Override
    public void glGetQueryBufferObjecti64v(int id, int buffer, int pname, long offset) {
        GL45.glGetQueryBufferObjecti64v(id, buffer, pname, offset);
    }

    @Override
    public void glGetQueryBufferObjectui64v(int id, int buffer, int pname, long offset) {
        GL45.glGetQueryBufferObjectui64v(id, buffer, pname, offset);
    }

    @Override
    public void glMemoryBarrierByRegion(int barriers) {
        GL45.glMemoryBarrierByRegion(barriers);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int bufSize, long pixels) {
        GL45.glGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, bufSize, pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
        GL45.glGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
        GL45.glGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
        GL45.glGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) {
        GL45.glGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer pixels) {
        GL45.glGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int bufSize, long pixels) {
        GL45.glGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, bufSize, pixels);
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, ByteBuffer pixels) {
        GL45.glGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, pixels);
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, ShortBuffer pixels) {
        GL45.glGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, pixels);
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, IntBuffer pixels) {
        GL45.glGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, pixels);
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, FloatBuffer pixels) {
        GL45.glGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, pixels);
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, DoubleBuffer pixels) {
        GL45.glGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, pixels);
    }

    @Override
    public void glTextureBarrier() {
        GL45.glTextureBarrier();
    }

    @Override
    public int glGetGraphicsResetStatus() {
        return GL45.glGetGraphicsResetStatus();
    }

    @Override
    public void glGetnMapdv(int target, int query, DoubleBuffer data) {
        GL45.glGetnMapdv(target, query, data);
    }

    @Override
    public double glGetnMapd(int target, int query) {
        return GL45.glGetnMapd(target, query);
    }

    @Override
    public void glGetnMapfv(int target, int query, FloatBuffer data) {
        GL45.glGetnMapfv(target, query, data);
    }

    @Override
    public float glGetnMapf(int target, int query) {
        return GL45.glGetnMapf(target, query);
    }

    @Override
    public void glGetnMapiv(int target, int query, IntBuffer data) {
        GL45.glGetnMapiv(target, query, data);
    }

    @Override
    public int glGetnMapi(int target, int query) {
        return GL45.glGetnMapi(target, query);
    }

    @Override
    public void glGetnPixelMapfv(int map, FloatBuffer data) {
        GL45.glGetnPixelMapfv(map, data);
    }

    @Override
    public void glGetnPixelMapuiv(int map, IntBuffer data) {
        GL45.glGetnPixelMapuiv(map, data);
    }

    @Override
    public void glGetnPixelMapusv(int map, ShortBuffer data) {
        GL45.glGetnPixelMapusv(map, data);
    }

    @Override
    public void glGetnPolygonStipple(int bufSize, long pattern) {
        GL45.glGetnPolygonStipple(bufSize, pattern);
    }

    @Override
    public void glGetnPolygonStipple(ByteBuffer pattern) {
        GL45.glGetnPolygonStipple(pattern);
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, int bufSize, long img) {
        GL45.glGetnTexImage(tex, level, format, type, bufSize, img);
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, ByteBuffer img) {
        GL45.glGetnTexImage(tex, level, format, type, img);
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, ShortBuffer img) {
        GL45.glGetnTexImage(tex, level, format, type, img);
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, IntBuffer img) {
        GL45.glGetnTexImage(tex, level, format, type, img);
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, FloatBuffer img) {
        GL45.glGetnTexImage(tex, level, format, type, img);
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, DoubleBuffer img) {
        GL45.glGetnTexImage(tex, level, format, type, img);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, int bufSize, long pixels) {
        GL45.glReadnPixels(x, y, width, height, format, type, bufSize, pixels);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, ByteBuffer pixels) {
        GL45.glReadnPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, ShortBuffer pixels) {
        GL45.glReadnPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, IntBuffer pixels) {
        GL45.glReadnPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, FloatBuffer pixels) {
        GL45.glReadnPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, int bufSize, long table) {
        GL45.glGetnColorTable(target, format, type, bufSize, table);
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, ByteBuffer table) {
        GL45.glGetnColorTable(target, format, type, table);
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, ShortBuffer table) {
        GL45.glGetnColorTable(target, format, type, table);
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, IntBuffer table) {
        GL45.glGetnColorTable(target, format, type, table);
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, FloatBuffer table) {
        GL45.glGetnColorTable(target, format, type, table);
    }

    @Override
    public void glGetnConvolutionFilter(int target, int format, int type, int bufSize, long image) {
        GL45.glGetnConvolutionFilter(target, format, type, bufSize, image);
    }

    @Override
    public void glGetnConvolutionFilter(int target, int format, int type, ByteBuffer image) {
        GL45.glGetnConvolutionFilter(target, format, type, image);
    }

    @Override
    public void glGetnSeparableFilter(int target, int format, int type, int rowBufSize, long row, int columnBufSize, long column, ByteBuffer span) {
        GL45.glGetnSeparableFilter(target, format, type, rowBufSize, row, columnBufSize, column, span);
    }

    @Override
    public void glGetnSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, ByteBuffer span) {
        GL45.glGetnSeparableFilter(target, format, type, row, column, span);
    }

    @Override
    public void glGetnHistogram(int target, boolean reset, int format, int type, int bufSize, long values) {
        GL45.glGetnHistogram(target, reset, format, type, bufSize, values);
    }

    @Override
    public void glGetnHistogram(int target, boolean reset, int format, int type, ByteBuffer values) {
        GL45.glGetnHistogram(target, reset, format, type, values);
    }

    @Override
    public void glGetnMinmax(int target, boolean reset, int format, int type, int bufSize, long values) {
        GL45.glGetnMinmax(target, reset, format, type, bufSize, values);
    }

    @Override
    public void glGetnMinmax(int target, boolean reset, int format, int type, ByteBuffer values) {
        GL45.glGetnMinmax(target, reset, format, type, values);
    }

    @Override
    public void glGetnCompressedTexImage(int target, int level, int bufSize, long img) {
        GL45.glGetnCompressedTexImage(target, level, bufSize, img);
    }

    @Override
    public void glGetnCompressedTexImage(int target, int level, ByteBuffer img) {
        GL45.glGetnCompressedTexImage(target, level, img);
    }

    @Override
    public void glGetnUniformfv(int program, int location, FloatBuffer params) {
        GL45.glGetnUniformfv(program, location, params);
    }

    @Override
    public float glGetnUniformf(int program, int location) {
        return GL45.glGetnUniformf(program, location);
    }

    @Override
    public void glGetnUniformdv(int program, int location, DoubleBuffer params) {
        GL45.glGetnUniformdv(program, location, params);
    }

    @Override
    public double glGetnUniformd(int program, int location) {
        return GL45.glGetnUniformd(program, location);
    }

    @Override
    public void glGetnUniformiv(int program, int location, IntBuffer params) {
        GL45.glGetnUniformiv(program, location, params);
    }

    @Override
    public int glGetnUniformi(int program, int location) {
        return GL45.glGetnUniformi(program, location);
    }

    @Override
    public void glGetnUniformuiv(int program, int location, IntBuffer params) {
        GL45.glGetnUniformuiv(program, location, params);
    }

    @Override
    public int glGetnUniformui(int program, int location) {
        return GL45.glGetnUniformui(program, location);
    }

    @Override
    public void glCreateTransformFeedbacks(int[] ids) {
        GL45.glCreateTransformFeedbacks(ids);
    }

    @Override
    public void glGetTransformFeedbackiv(int xfb, int pname, int[] param) {
        GL45.glGetTransformFeedbackiv(xfb, pname, param);
    }

    @Override
    public void glGetTransformFeedbacki_v(int xfb, int pname, int index, int[] param) {
        GL45.glGetTransformFeedbacki_v(xfb, pname, index, param);
    }

    @Override
    public void glGetTransformFeedbacki64_v(int xfb, int pname, int index, long[] param) {
        GL45.glGetTransformFeedbacki64_v(xfb, pname, index, param);
    }

    @Override
    public void glCreateBuffers(int[] buffers) {
        GL45.glCreateBuffers(buffers);
    }

    @Override
    public void glNamedBufferStorage(int buffer, short[] data, int flags) {
        GL45.glNamedBufferStorage(buffer, data, flags);
    }

    @Override
    public void glNamedBufferStorage(int buffer, int[] data, int flags) {
        GL45.glNamedBufferStorage(buffer, data, flags);
    }

    @Override
    public void glNamedBufferStorage(int buffer, float[] data, int flags) {
        GL45.glNamedBufferStorage(buffer, data, flags);
    }

    @Override
    public void glNamedBufferStorage(int buffer, double[] data, int flags) {
        GL45.glNamedBufferStorage(buffer, data, flags);
    }

    @Override
    public void glNamedBufferData(int buffer, short[] data, int usage) {
        GL45.glNamedBufferData(buffer, data, usage);
    }

    @Override
    public void glNamedBufferData(int buffer, int[] data, int usage) {
        GL45.glNamedBufferData(buffer, data, usage);
    }

    @Override
    public void glNamedBufferData(int buffer, long[] data, int usage) {
        GL45.glNamedBufferData(buffer, data, usage);
    }

    @Override
    public void glNamedBufferData(int buffer, float[] data, int usage) {
        GL45.glNamedBufferData(buffer, data, usage);
    }

    @Override
    public void glNamedBufferData(int buffer, double[] data, int usage) {
        GL45.glNamedBufferData(buffer, data, usage);
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, short[] data) {
        GL45.glNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, int[] data) {
        GL45.glNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, long[] data) {
        GL45.glNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, float[] data) {
        GL45.glNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glNamedBufferSubData(int buffer, long offset, double[] data) {
        GL45.glNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, short[] data) {
        GL45.glClearNamedBufferData(buffer, internalformat, format, type, data);
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, int[] data) {
        GL45.glClearNamedBufferData(buffer, internalformat, format, type, data);
    }

    @Override
    public void glClearNamedBufferData(int buffer, int internalformat, int format, int type, float[] data) {
        GL45.glClearNamedBufferData(buffer, internalformat, format, type, data);
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, short[] data) {
        GL45.glClearNamedBufferSubData(buffer, internalformat, offset, size, format, type, data);
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, int[] data) {
        GL45.glClearNamedBufferSubData(buffer, internalformat, offset, size, format, type, data);
    }

    @Override
    public void glClearNamedBufferSubData(int buffer, int internalformat, long offset, long size, int format, int type, float[] data) {
        GL45.glClearNamedBufferSubData(buffer, internalformat, offset, size, format, type, data);
    }

    @Override
    public void glGetNamedBufferParameteriv(int buffer, int pname, int[] params) {
        GL45.glGetNamedBufferParameteriv(buffer, pname, params);
    }

    @Override
    public void glGetNamedBufferParameteri64v(int buffer, int pname, long[] params) {
        GL45.glGetNamedBufferParameteri64v(buffer, pname, params);
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, short[] data) {
        GL45.glGetNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, int[] data) {
        GL45.glGetNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, long[] data) {
        GL45.glGetNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, float[] data) {
        GL45.glGetNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glGetNamedBufferSubData(int buffer, long offset, double[] data) {
        GL45.glGetNamedBufferSubData(buffer, offset, data);
    }

    @Override
    public void glCreateFramebuffers(int[] framebuffers) {
        GL45.glCreateFramebuffers(framebuffers);
    }

    @Override
    public void glNamedFramebufferDrawBuffers(int framebuffer, int[] bufs) {
        GL45.glNamedFramebufferDrawBuffers(framebuffer, bufs);
    }

    @Override
    public void glInvalidateNamedFramebufferData(int framebuffer, int[] attachments) {
        GL45.glInvalidateNamedFramebufferData(framebuffer, attachments);
    }

    @Override
    public void glInvalidateNamedFramebufferSubData(int framebuffer, int[] attachments, int x, int y, int width, int height) {
        GL45.glInvalidateNamedFramebufferSubData(framebuffer, attachments, x, y, width, height);
    }

    @Override
    public void glClearNamedFramebufferiv(int framebuffer, int buffer, int drawbuffer, int[] value) {
        GL45.glClearNamedFramebufferiv(framebuffer, buffer, drawbuffer, value);
    }

    @Override
    public void glClearNamedFramebufferuiv(int framebuffer, int buffer, int drawbuffer, int[] value) {
        GL45.glClearNamedFramebufferuiv(framebuffer, buffer, drawbuffer, value);
    }

    @Override
    public void glClearNamedFramebufferfv(int framebuffer, int buffer, int drawbuffer, float[] value) {
        GL45.glClearNamedFramebufferfv(framebuffer, buffer, drawbuffer, value);
    }

    @Override
    public void glGetNamedFramebufferParameteriv(int framebuffer, int pname, int[] params) {
        GL45.glGetNamedFramebufferParameteriv(framebuffer, pname, params);
    }

    @Override
    public void glGetNamedFramebufferAttachmentParameteriv(int framebuffer, int attachment, int pname, int[] params) {
        GL45.glGetNamedFramebufferAttachmentParameteriv(framebuffer, attachment, pname, params);
    }

    @Override
    public void glCreateRenderbuffers(int[] renderbuffers) {
        GL45.glCreateRenderbuffers(renderbuffers);
    }

    @Override
    public void glGetNamedRenderbufferParameteriv(int renderbuffer, int pname, int[] params) {
        GL45.glGetNamedRenderbufferParameteriv(renderbuffer, pname, params);
    }

    @Override
    public void glCreateTextures(int target, int[] textures) {
        GL45.glCreateTextures(target, textures);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, short[] pixels) {
        GL45.glTextureSubImage1D(texture, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, int[] pixels) {
        GL45.glTextureSubImage1D(texture, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, float[] pixels) {
        GL45.glTextureSubImage1D(texture, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTextureSubImage1D(int texture, int level, int xoffset, int width, int format, int type, double[] pixels) {
        GL45.glTextureSubImage1D(texture, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, short[] pixels) {
        GL45.glTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, int[] pixels) {
        GL45.glTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, float[] pixels) {
        GL45.glTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage2D(int texture, int level, int xoffset, int yoffset, int width, int height, int format, int type, double[] pixels) {
        GL45.glTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, short[] pixels) {
        GL45.glTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int[] pixels) {
        GL45.glTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, float[] pixels) {
        GL45.glTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTextureSubImage3D(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, double[] pixels) {
        GL45.glTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTextureParameterfv(int texture, int pname, float[] params) {
        GL45.glTextureParameterfv(texture, pname, params);
    }

    @Override
    public void glTextureParameterIiv(int texture, int pname, int[] params) {
        GL45.glTextureParameterIiv(texture, pname, params);
    }

    @Override
    public void glTextureParameterIuiv(int texture, int pname, int[] params) {
        GL45.glTextureParameterIuiv(texture, pname, params);
    }

    @Override
    public void glTextureParameteriv(int texture, int pname, int[] params) {
        GL45.glTextureParameteriv(texture, pname, params);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, short[] pixels) {
        GL45.glGetTextureImage(texture, level, format, type, pixels);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, int[] pixels) {
        GL45.glGetTextureImage(texture, level, format, type, pixels);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, float[] pixels) {
        GL45.glGetTextureImage(texture, level, format, type, pixels);
    }

    @Override
    public void glGetTextureImage(int texture, int level, int format, int type, double[] pixels) {
        GL45.glGetTextureImage(texture, level, format, type, pixels);
    }

    @Override
    public void glGetTextureLevelParameterfv(int texture, int level, int pname, float[] params) {
        GL45.glGetTextureLevelParameterfv(texture, level, pname, params);
    }

    @Override
    public void glGetTextureLevelParameteriv(int texture, int level, int pname, int[] params) {
        GL45.glGetTextureLevelParameteriv(texture, level, pname, params);
    }

    @Override
    public void glGetTextureParameterfv(int texture, int pname, float[] params) {
        GL45.glGetTextureParameterfv(texture, pname, params);
    }

    @Override
    public void glGetTextureParameterIiv(int texture, int pname, int[] params) {
        GL45.glGetTextureParameterIiv(texture, pname, params);
    }

    @Override
    public void glGetTextureParameterIuiv(int texture, int pname, int[] params) {
        GL45.glGetTextureParameterIuiv(texture, pname, params);
    }

    @Override
    public void glGetTextureParameteriv(int texture, int pname, int[] params) {
        GL45.glGetTextureParameteriv(texture, pname, params);
    }

    @Override
    public void glCreateVertexArrays(int[] arrays) {
        GL45.glCreateVertexArrays(arrays);
    }

    @Override
    public void glGetVertexArrayiv(int vaobj, int pname, int[] param) {
        GL45.glGetVertexArrayiv(vaobj, pname, param);
    }

    @Override
    public void glGetVertexArrayIndexediv(int vaobj, int index, int pname, int[] param) {
        GL45.glGetVertexArrayIndexediv(vaobj, index, pname, param);
    }

    @Override
    public void glGetVertexArrayIndexed64iv(int vaobj, int index, int pname, long[] param) {
        GL45.glGetVertexArrayIndexed64iv(vaobj, index, pname, param);
    }

    @Override
    public void glCreateSamplers(int[] samplers) {
        GL45.glCreateSamplers(samplers);
    }

    @Override
    public void glCreateProgramPipelines(int[] pipelines) {
        GL45.glCreateProgramPipelines(pipelines);
    }

    @Override
    public void glCreateQueries(int target, int[] ids) {
        GL45.glCreateQueries(target, ids);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, short[] pixels) {
        GL45.glGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int[] pixels) {
        GL45.glGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, float[] pixels) {
        GL45.glGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glGetTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, double[] pixels) {
        GL45.glGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, short[] pixels) {
        GL45.glGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, pixels);
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int[] pixels) {
        GL45.glGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, pixels);
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, float[] pixels) {
        GL45.glGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, pixels);
    }

    @Override
    public void glGetCompressedTextureSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, double[] pixels) {
        GL45.glGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, pixels);
    }

    @Override
    public void glGetnMapdv(int target, int query, double[] data) {
        GL45.glGetnMapdv(target, query, data);
    }

    @Override
    public void glGetnMapfv(int target, int query, float[] data) {
        GL45.glGetnMapfv(target, query, data);
    }

    @Override
    public void glGetnMapiv(int target, int query, int[] data) {
        GL45.glGetnMapiv(target, query, data);
    }

    @Override
    public void glGetnPixelMapfv(int map, float[] data) {
        GL45.glGetnPixelMapfv(map, data);
    }

    @Override
    public void glGetnPixelMapuiv(int map, int[] data) {
        GL45.glGetnPixelMapuiv(map, data);
    }

    @Override
    public void glGetnPixelMapusv(int map, short[] data) {
        GL45.glGetnPixelMapusv(map, data);
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, short[] img) {
        GL45.glGetnTexImage(tex, level, format, type, img);
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, int[] img) {
        GL45.glGetnTexImage(tex, level, format, type, img);
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, float[] img) {
        GL45.glGetnTexImage(tex, level, format, type, img);
    }

    @Override
    public void glGetnTexImage(int tex, int level, int format, int type, double[] img) {
        GL45.glGetnTexImage(tex, level, format, type, img);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, short[] pixels) {
        GL45.glReadnPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, int[] pixels) {
        GL45.glReadnPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glReadnPixels(int x, int y, int width, int height, int format, int type, float[] pixels) {
        GL45.glReadnPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, short[] table) {
        GL45.glGetnColorTable(target, format, type, table);
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, int[] table) {
        GL45.glGetnColorTable(target, format, type, table);
    }

    @Override
    public void glGetnColorTable(int target, int format, int type, float[] table) {
        GL45.glGetnColorTable(target, format, type, table);
    }

    @Override
    public void glGetnUniformfv(int program, int location, float[] params) {
        GL45.glGetnUniformfv(program, location, params);
    }

    @Override
    public void glGetnUniformdv(int program, int location, double[] params) {
        GL45.glGetnUniformdv(program, location, params);
    }

    @Override
    public void glGetnUniformiv(int program, int location, int[] params) {
        GL45.glGetnUniformiv(program, location, params);
    }

    @Override
    public void glGetnUniformuiv(int program, int location, int[] params) {
        GL45.glGetnUniformuiv(program, location, params);
    }

}