package jpize.util.pixmap;

import jpize.app.Jpize;
import jpize.gl.texture.Texture2D;
import jpize.util.postprocess.RenderQuad;

public class Canvas extends PixmapRGBA {

    private final Texture2D frame;
    private final RenderQuad renderQuad;

    public Canvas() {
        super(Jpize.getWidth(), Jpize.getHeight());
        super.enableBlending();
        this.frame = new Texture2D(this);

        this.renderQuad = new RenderQuad(
            new float[] {
                -1F,  1F,  0F, 1F, // 0
                -1F, -1F,  0F, 0F, // 1
                 1F, -1F,  1F, 0F, // 2
                 1F,  1F,  1F, 1F, // 3
            },
            new int[] {
                0, 1, 2,
                2, 3, 0,
            }
        );
    }

    public void render() {
        frame.setImage(this);
        renderQuad.render(frame);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        frame.dispose();
        renderQuad.dispose();
    }

}
