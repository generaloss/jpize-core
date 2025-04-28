package jpize.opengl.framebuffer.attachment;

import jpize.opengl.framebuffer.GlFramebuffer;
import jpize.opengl.texture.GlInternalFormat;
import jpize.opengl.type.GlType;
import jpize.util.Disposable;

public abstract class FramebufferAttachment implements Disposable {

    protected final GlAttachment attachment;

    public FramebufferAttachment(GlAttachment attachment) {
        this.attachment = attachment;
    }

    public GlAttachment getAttachment() {
        return attachment;
    }

    abstract public void attach(GlFramebuffer framebuffer);

    abstract public void resize(int width, int height);


    // factory methods

    public static FramebufferTexture color(int index, GlInternalFormat format, GlType type) {
        return new FramebufferTexture(GlAttachment.color(index), format, type);
    }

    public static FramebufferTexture color(int index) {
        return color(index, GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE);
    }

    public static FramebufferRenderbuffer colorRenderbuffer(int index, GlInternalFormat format) {
        return new FramebufferRenderbuffer(GlAttachment.color(index), format);
    }

    public static FramebufferRenderbuffer colorRenderbuffer(int index) {
        return colorRenderbuffer(index, GlInternalFormat.RGBA8);
    }

    public static FramebufferAttachment color(int index, GlInternalFormat format, GlType type, boolean multisample) {
        if(multisample) {
            return colorRenderbuffer(index, format);
        }else{
            return color(index, format, type);
        }
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

    public static FramebufferAttachment depth(GlInternalFormat format, GlType type, boolean multisample) {
        if(multisample) {
            return depthRenderbuffer(format);
        }else{
            return depth(format, type);
        }
    }


    public static FramebufferTexture stencil(GlInternalFormat format, GlType type) {
        return new FramebufferTexture(GlAttachment.STENCIL, format, type);
    }

    public static FramebufferTexture stencil() {
        return stencil(GlInternalFormat.STENCIL_INDEX8, GlType.FLOAT);
    }

    public static FramebufferRenderbuffer stencilRenderbuffer(GlInternalFormat format) {
        return new FramebufferRenderbuffer(GlAttachment.STENCIL, format);
    }

    public static FramebufferRenderbuffer stencilRenderbuffer() {
        return stencilRenderbuffer(GlInternalFormat.STENCIL_INDEX8);
    }

    public static FramebufferAttachment stencil(GlInternalFormat format, GlType type, boolean multisample) {
        if(multisample) {
            return stencilRenderbuffer(format);
        }else{
            return stencil(format, type);
        }
    }


    public static FramebufferTexture depthStencil(GlInternalFormat format, GlType type) {
        return new FramebufferTexture(GlAttachment.DEPTH_STENCIL, format, type);
    }

    public static FramebufferTexture depthStencil() {
        return depthStencil(GlInternalFormat.DEPTH24_STENCIL8, GlType.FLOAT);
    }

    public static FramebufferRenderbuffer depthStencilRenderbuffer(GlInternalFormat format) {
        return new FramebufferRenderbuffer(GlAttachment.DEPTH_STENCIL, format);
    }

    public static FramebufferRenderbuffer depthStencilRenderbuffer() {
        return depthStencilRenderbuffer(GlInternalFormat.DEPTH24_STENCIL8);
    }

    public static FramebufferAttachment depthStencil(GlInternalFormat format, GlType type, boolean multisample) {
        if(multisample) {
            return depthStencilRenderbuffer(format);
        }else{
            return depthStencil(format, type);
        }
    }

}