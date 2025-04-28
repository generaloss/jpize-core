package jpize.opengl.framebuffer;

import jpize.context.Jpize;
import jpize.opengl.gl.Gl;
import jpize.opengl.framebuffer.attachment.FramebufferAttachment;
import jpize.opengl.framebuffer.attachment.FramebufferTexture;
import jpize.opengl.texture.Texture2D;

public class Framebuffer3D extends GlFramebuffer {

    private final FramebufferTexture colorAttachment;
    private final FramebufferTexture depthAttachment;

    public Framebuffer3D(int width, int height) {
        super(width, height);
        this.colorAttachment = FramebufferAttachment.color(0);
        this.depthAttachment = FramebufferAttachment.depth();
        super.attach(colorAttachment);
        super.attach(depthAttachment);
        super.checkCompletionError();
    }

    public Framebuffer3D() {
        this(Jpize.getWidth(), Jpize.getHeight());
    }

    public Texture2D getColorTexture() {
        return colorAttachment.getTexture();
    }

    public Texture2D getDepthTexture() {
        return depthAttachment.getTexture();
    }


    public void begin() {
        super.bind();
        Gl.clearColorDepthBuffers();
    }

    public void end() {
        super.unbind();
    }


    @Override
    public void dispose() {
        colorAttachment.dispose();
        depthAttachment.dispose();
    }

}