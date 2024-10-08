package jpize.util;

import jpize.app.Jpize;
import jpize.util.pixmap.PixmapRGBA;
import jpize.gl.texture.Texture2D;
import jpize.util.postprocess.ScreenQuad;
import jpize.util.postprocess.ScreenQuadShader;

public class Canvas extends PixmapRGBA {

    private final Texture2D frame;

    public Canvas() {
        super(Jpize.getWidth(), Jpize.getHeight());
        this.frame = new Texture2D(this);
    }

    public void render() {
        frame.setImage(this);
        ScreenQuadShader.use(frame);
        ScreenQuad.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        frame.dispose();
    }

}
