package jpize.opengl.framebuffer;

import jpize.opengl.gl.Gl;
import jpize.opengl.framebuffer.attachment.FramebufferAttachment;
import jpize.opengl.framebuffer.attachment.FramebufferTexture;
import jpize.opengl.texture.Texture2D;

public class Framebuffer2D extends GlFramebuffer {

    private final FramebufferTexture colorAttachment;

    public Framebuffer2D() {
        this.colorAttachment = FramebufferAttachment.color(0);
        super.attach(colorAttachment);
        super.checkCompletionError();
    }

    public Texture2D getColorTexture() {
        return colorAttachment.getTexture();
    }


    public void begin() {
        super.bind();
        Gl.clearColorBuffer();
    }

    public void end() {
        super.unbind();
    }


    @Override
    public void dispose() {
        colorAttachment.dispose();
    }

}
