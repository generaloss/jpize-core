package jpize.opengl.framebuffer.attachment;

import jpize.opengl.framebuffer.GLFramebuffer;
import jpize.opengl.texture.GLInternalFormat;
import jpize.opengl.type.GLType;
import jpize.util.Disposable;

public abstract class FramebufferAttachment implements Disposable {

    protected final GLAttachment attachment;

    public FramebufferAttachment(GLAttachment attachment) {
        this.attachment = attachment;
    }

    public GLAttachment getAttachment() {
        return attachment;
    }

    abstract public void attach(GLFramebuffer framebuffer);

    abstract public void resize(int width, int height);


    // factory methods

    public static FramebufferTexture color(int index, GLInternalFormat format, GLType type) {
        return new FramebufferTexture(GLAttachment.color(index), format, type);
    }

    public static FramebufferTexture color(int index) {
        return color(index, GLInternalFormat.RGBA8, GLType.UNSIGNED_BYTE);
    }

    public static FramebufferRenderbuffer colorRenderbuffer(int index, GLInternalFormat format) {
        return new FramebufferRenderbuffer(GLAttachment.color(index), format);
    }

    public static FramebufferRenderbuffer colorRenderbuffer(int index) {
        return colorRenderbuffer(index, GLInternalFormat.RGBA8);
    }

    public static FramebufferAttachment color(int index, GLInternalFormat format, GLType type, boolean multisample) {
        if(multisample) {
            return colorRenderbuffer(index, format);
        }else{
            return color(index, format, type);
        }
    }


    public static FramebufferTexture depth(GLInternalFormat format, GLType type) {
        return new FramebufferTexture(GLAttachment.DEPTH, format, type);
    }

    public static FramebufferTexture depth() {
        return depth(GLInternalFormat.DEPTH_COMPONENT24, GLType.FLOAT);
    }

    public static FramebufferRenderbuffer depthRenderbuffer(GLInternalFormat format) {
        return new FramebufferRenderbuffer(GLAttachment.DEPTH, format);
    }

    public static FramebufferRenderbuffer depthRenderbuffer() {
        return depthRenderbuffer(GLInternalFormat.DEPTH_COMPONENT24);
    }

    public static FramebufferAttachment depth(GLInternalFormat format, GLType type, boolean multisample) {
        if(multisample) {
            return depthRenderbuffer(format);
        }else{
            return depth(format, type);
        }
    }


    public static FramebufferTexture stencil(GLInternalFormat format, GLType type) {
        return new FramebufferTexture(GLAttachment.STENCIL, format, type);
    }

    public static FramebufferTexture stencil() {
        return stencil(GLInternalFormat.STENCIL_INDEX8, GLType.FLOAT);
    }

    public static FramebufferRenderbuffer stencilRenderbuffer(GLInternalFormat format) {
        return new FramebufferRenderbuffer(GLAttachment.STENCIL, format);
    }

    public static FramebufferRenderbuffer stencilRenderbuffer() {
        return stencilRenderbuffer(GLInternalFormat.STENCIL_INDEX8);
    }

    public static FramebufferAttachment stencil(GLInternalFormat format, GLType type, boolean multisample) {
        if(multisample) {
            return stencilRenderbuffer(format);
        }else{
            return stencil(format, type);
        }
    }


    public static FramebufferTexture depthStencil(GLInternalFormat format, GLType type) {
        return new FramebufferTexture(GLAttachment.DEPTH_STENCIL, format, type);
    }

    public static FramebufferTexture depthStencil() {
        return depthStencil(GLInternalFormat.DEPTH24_STENCIL8, GLType.FLOAT);
    }

    public static FramebufferRenderbuffer depthStencilRenderbuffer(GLInternalFormat format) {
        return new FramebufferRenderbuffer(GLAttachment.DEPTH_STENCIL, format);
    }

    public static FramebufferRenderbuffer depthStencilRenderbuffer() {
        return depthStencilRenderbuffer(GLInternalFormat.DEPTH24_STENCIL8);
    }

    public static FramebufferAttachment depthStencil(GLInternalFormat format, GLType type, boolean multisample) {
        if(multisample) {
            return depthStencilRenderbuffer(format);
        }else{
            return depthStencil(format, type);
        }
    }

}