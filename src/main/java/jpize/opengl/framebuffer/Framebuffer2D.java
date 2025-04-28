package jpize.opengl.framebuffer;

import jpize.context.Jpize;
import jpize.opengl.gl.Gl;
import jpize.opengl.framebuffer.attachment.FramebufferAttachment;
import jpize.opengl.framebuffer.attachment.FramebufferTexture;
import jpize.opengl.texture.Texture2D;

public class Framebuffer2D extends GlFramebuffer {

    private final FramebufferTexture colorAttachment;

    public Framebuffer2D(GlFramebufferTarget target, int width, int height, boolean multisample) {
        super(target, width, height, multisample);
        this.colorAttachment = FramebufferAttachment.color(0);
        super.attach(colorAttachment);
        super.checkCompletionError();
    }

    public Framebuffer2D(int width, int height, boolean multisample) {
        this(GlFramebufferTarget.FRAMEBUFFER, width, height, multisample);
    }

    public Framebuffer2D(int width, int height) {
        this(width, height, false);
    }

    public Framebuffer2D() {
        this(Jpize.getWidth(), Jpize.getHeight());
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

    public void beginEndAction(Runnable action) {
        this.begin();
        try {
            action.run();
        }finally{
            this.end();
        }
    }


    @Override
    public void dispose() {
        super.dispose();
        colorAttachment.dispose();
    }

}
