package jpize.util;

import jpize.app.Jpize;
import jpize.util.camera.OrthographicCamera;
import jpize.gl.Gl;
import jpize.gl.glenum.GlTarget;
import jpize.util.pixmap.PixmapRGBA;
import jpize.gl.texture.Texture2D;
import jpize.gl.texture.TextureBatch;

public class Canvas extends PixmapRGBA {

    private final TextureBatch batch;
    private final OrthographicCamera camera;
    private final Texture2D frameTexture;

    public Canvas(int width, int height) {
        super(width, height);

        this.batch = new TextureBatch();
        this.camera = new OrthographicCamera();
        this.batch.flip(false, true);
        this.frameTexture = new Texture2D(this);
    }

    public Canvas() {
        this(Jpize.getWidth(), Jpize.getHeight());
    }


    public void render() {
        boolean cullFace = Gl.isEnabled(GlTarget.CULL_FACE);
        if(cullFace)
            Gl.disable(GlTarget.CULL_FACE);

        frameTexture.setImage(this);
        camera.update();
        batch.setup(camera);
        batch.draw(frameTexture, 0, 0, Jpize.getWidth(), Jpize.getHeight());
        batch.render();

        if(cullFace)
            Gl.enable(GlTarget.CULL_FACE);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        frameTexture.setDefaultImage(width, height);
        camera.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        frameTexture.dispose();
    }

}
