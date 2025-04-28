package jpize.opengl.framebuffer.attachment;

import jpize.opengl.framebuffer.GlFramebuffer;
import jpize.opengl.framebuffer.renderbuffer.GlRenderbuffer;
import jpize.opengl.texture.GlInternalFormat;
import jpize.util.Disposable;

public class FramebufferRenderbuffer extends FramebufferAttachment implements Disposable {

    private final GlRenderbuffer renderbuffer;

    public FramebufferRenderbuffer(GlAttachment attachment, GlInternalFormat format) {
        super(attachment);
        this.renderbuffer = new GlRenderbuffer(format);
    }

    public GlRenderbuffer getRenderbuffer() {
        return renderbuffer;
    }


    private void setRenderbufferStorage(int width, int height) {
        renderbuffer.bind();
        renderbuffer.setStorage(width, height);
        renderbuffer.unbind();
    }

    @Override
    public void attach(GlFramebuffer framebuffer) {
        this.setRenderbufferStorage(framebuffer.getWidth(), framebuffer.getHeight());
        framebuffer.attachRenderbuffer(super.attachment, renderbuffer);
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