package jpize.util.postprocess;

import jpize.gl.texture.GlTexture;
import jpize.gl.Gl;
import jpize.util.Disposable;
import jpize.gl.texture.GlTexture2D;

public class Framebuffer3D implements Disposable {

    private final FrameBufferObject fbo;
    private final RenderBufferObject rbo;

    public Framebuffer3D() {
        this.fbo = new FrameBufferObject();
        this.fbo.create();
        this.fbo.bind();

        this.rbo = new RenderBufferObject();
        this.rbo.create();

        this.fbo.unbind();
    }


    public void begin() {
        fbo.bind();
        rbo.bind();
        Gl.clearColorDepthBuffers();
    }

    public void end() {
        fbo.unbind();
        rbo.unbind();
    }

    public GlTexture2D getFrameTexture() {
        return fbo.getTexture();
    }

    public GlTexture getDepthMap() {
        return rbo.getTexture();
    }


    public void resize(int width, int height) {
        fbo.resize(width, height);
        rbo.resize(width, height);
    }

    @Override
    public void dispose() {
        fbo.dispose();
        rbo.dispose();
    }

}
