package jpize.opengl.tesselation;

import jpize.opengl.Gl;
import jpize.util.Disposable;
import jpize.opengl.texture.Texture2D;

public class Framebuffer3D implements Disposable {

    private final GlFramebuffer fbo;
    private final GlRenderbuffer rbo;

    public Framebuffer3D() {
        this.fbo = new GlFramebuffer().create().bind();
        this.rbo = new GlRenderbuffer().create();
        this.fbo.unbind();
    }

    public GlFramebuffer getFBO() {
        return fbo;
    }

    public GlRenderbuffer getRBO() {
        return rbo;
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

    public Texture2D getFrameTexture() {
        return fbo.getTexture();
    }

    public Texture2D getDepthTexture() {
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
