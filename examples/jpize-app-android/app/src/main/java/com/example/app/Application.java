package com.example.app;

import generaloss.spatialmath.vector.Vec2f;
import jpize.context.Jpize;
import jpize.context.JpizeApplication;
import jpize.context.input.Key;
import jpize.opengl.texture.Texture2D;
import jpize.util.camera.Camera;
import jpize.util.camera.OrthographicCamera;
import jpize.util.font.Font;
import jpize.util.mesh.TextureBatch;

public class Application extends JpizeApplication {

    private final Camera camera;
    private final TextureBatch batch;
    private final Texture2D image;
    private final Font font;

    public Application() {
        this.camera = new OrthographicCamera();
        this.batch = new TextureBatch();
        this.image = new Texture2D();
        this.font = new Font();
    }

    @Override
    public void init() {
        image.setImage("/background.png");
        font.loadDefault(96);
    }

    @Override
    public void update() {
        if(Key.ESCAPE.down())
            Jpize.exit();

        camera.update();
    }

    @Override
    public void render() {
        batch.setup(camera);

        batch.draw(image, 0F, 0F, Jpize.getWidth(), Jpize.getHeight());
        this.drawTextInCenter("Hello, World!");

        batch.render();
    }

    private void drawTextInCenter(String text) {
        final Vec2f bounds = font.getTextBounds(text);

        final float drawX = (Jpize.getWidth() - bounds.x) / 2F;
        final float drawY = (Jpize.getHeight() - bounds.y) / 2F;

        font.drawText(batch, text, drawX, drawY);
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        font.dispose();
    }

}
