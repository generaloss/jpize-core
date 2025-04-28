package jpize.opengl.framebuffer.attachment;

import jpize.opengl.framebuffer.GlFramebuffer;
import jpize.opengl.texture.GlInternalFormat;
import jpize.opengl.type.GlType;

public abstract class FramebufferAttachment {

    protected final GlAttachment attachment;

    public FramebufferAttachment(GlAttachment attachment) {
        this.attachment = attachment;
    }

    public GlAttachment getAttachment() {
        return attachment;
    }

    abstract public void attach(GlFramebuffer framebuffer);

    abstract public void resize(int width, int height);


    public static FramebufferTexture color(int index, GlInternalFormat format, GlType type) {
        return new FramebufferTexture(GlAttachment.color(index), format, type);
    }

    public static FramebufferTexture color(int index) {
        return color(index, GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE);
    }


    public static FramebufferTexture depth(GlInternalFormat format, GlType type) {
        return new FramebufferTexture(GlAttachment.DEPTH, format, type);
    }

    public static FramebufferTexture depth() {
        return depth(GlInternalFormat.DEPTH_COMPONENT24, GlType.FLOAT);
    }


    public static FramebufferRenderbuffer depthRenderbuffer(GlInternalFormat format) {
        return new FramebufferRenderbuffer(GlAttachment.DEPTH, format);
    }

    public static FramebufferRenderbuffer depthRenderbuffer() {
        return depthRenderbuffer(GlInternalFormat.DEPTH_COMPONENT24);
    }


    public static FramebufferRenderbuffer stencilRenderbuffer(GlInternalFormat format) {
        return new FramebufferRenderbuffer(GlAttachment.STENCIL, format);
    }

    public static FramebufferRenderbuffer stencilRenderbuffer() {
        return stencilRenderbuffer(GlInternalFormat.STENCIL_INDEX8);
    }


    public static FramebufferRenderbuffer depthStencilRenderbuffer(GlInternalFormat format) {
        return new FramebufferRenderbuffer(GlAttachment.DEPTH_STENCIL, format);
    }

    public static FramebufferRenderbuffer depthStencilRenderbuffer() {
        return depthStencilRenderbuffer(GlInternalFormat.DEPTH24_STENCIL8);
    }

}
