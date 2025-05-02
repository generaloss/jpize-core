package jpize.opengl.framebuffer.attachment;

import jpize.opengl.framebuffer.GLFramebuffer;
import jpize.opengl.framebuffer.renderbuffer.GLRenderbuffer;
import jpize.opengl.texture.GLInternalFormat;

public class FramebufferRenderbuffer extends FramebufferAttachment {

    private final GLRenderbuffer renderbuffer;

    public FramebufferRenderbuffer(GLAttachment attachment, GLInternalFormat format) {
        super(attachment);
        this.renderbuffer = new GLRenderbuffer(format);
    }

    public GLRenderbuffer getRenderbuffer() {
        return renderbuffer;
    }


    private void setRenderbufferStorage(int width, int height) {
        renderbuffer.bind();
        renderbuffer.setStorage(width, height);
        renderbuffer.unbind();
    }

    @Override
    public void attach(GLFramebuffer framebuffer) {
        this.setRenderbufferStorage(framebuffer.getWidth(), framebuffer.getHeight());
        framebuffer.framebufferRenderbuffer(super.attachment, renderbuffer);
    }

    @Override
    public void resize(int width, int height) {
        this.setRenderbufferStorage(width, height);
    }

    @Override
    public void dispose() {
        renderbuffer.dispose();
    }

}