package jpize.opengl.tesselation;

import jpize.opengl.gl.Gl;
import jpize.util.Disposable;
import jpize.opengl.texture.Texture2D;

public class Framebuffer2D implements Disposable {

    private final GlFramebuffer fbo;

    public Framebuffer2D() {
        this.fbo = new GlFramebuffer().create();
    }

    public GlFramebuffer getFBO() {
        return fbo;
    }


    public void begin() {
        fbo.bind();
        Gl.clearColorBuffer();
    }

    public void end() {
        fbo.unbind();
    }

    public Texture2D getFrameTexture() {
        return fbo.getTexture();
    }


    public void resize(int width, int height) {
        fbo.resize(width, height);
    }

    @Override
    public void dispose() {
        fbo.dispose();
    }

}
