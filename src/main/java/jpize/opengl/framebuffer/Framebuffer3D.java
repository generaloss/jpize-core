package jpize.opengl.framebuffer;

import jpize.context.Jpize;
import jpize.opengl.gl.GL;
import jpize.opengl.framebuffer.attachment.FramebufferAttachment;
import jpize.opengl.framebuffer.attachment.FramebufferTexture;
import jpize.opengl.texture.Texture2D;

public class Framebuffer3D extends GlFramebuffer {

    private final FramebufferTexture colorAttachment;
    private final FramebufferTexture depthAttachment;

    public Framebuffer3D(GlFramebufferTarget target, int width, int height, boolean multisample) {
        super(target, width, height, multisample);

        this.colorAttachment = FramebufferAttachment.color(0);
        this.depthAttachment = FramebufferAttachment.depth();

        super.attach(colorAttachment, depthAttachment);
        super.checkCompletionError();
    }

    public Framebuffer3D(int width, int height, boolean multisample) {
        this(GlFramebufferTarget.FRAMEBUFFER, width, height, multisample);
    }

    public Framebuffer3D(int width, int height) {
        this(width, height, false);
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
        GL.clearColorDepthBuffers();
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

    public void renderToScreen() {
        super.renderToScreen(colorAttachment);
    }


    @Override
    public void dispose() {
        super.dispose();
        colorAttachment.dispose();
        depthAttachment.dispose();
    }

}