package jpize.util.postprocess;

import jpize.gl.Gl;
import jpize.util.Disposable;
import jpize.gl.texture.GlTexture2D;

public class Framebuffer2D implements Disposable { // For the laziest :))))

    private final FrameBufferObject fbo;

    public Framebuffer2D() {
        this.fbo = new FrameBufferObject();
        this.fbo.create();
    }


    public void begin() {
        fbo.bind();
        Gl.clearColorBuffer();
    }

    public void end() {
        fbo.unbind();
    }

    public GlTexture2D getFrameTexture() {
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
