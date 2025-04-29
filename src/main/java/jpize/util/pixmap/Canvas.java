package jpize.util.pixmap;

import jpize.context.Jpize;
import jpize.opengl.texture.Texture2D;
import jpize.util.RenderQuad;

public class Canvas extends PixmapRGBA {

    private final Texture2D frameTexture;

    public Canvas() {
        super(Jpize.getWidth(), Jpize.getHeight());
        super.enableBlending();
        this.frameTexture = new Texture2D(this);
    }

    public void render() {
        frameTexture.setImage(this);
        RenderQuad.instance().render(frameTexture);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        frameTexture.dispose();
    }

}
