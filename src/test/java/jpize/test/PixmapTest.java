package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.util.color.Color;
import jpize.util.math.vector.Vec2i;
import jpize.util.pixmap.Canvas;
import jpize.util.pixmap.PixmapIO;
import jpize.util.pixmap.PixmapRGBA;

public class PixmapTest extends JpizeApplication {

    private PixmapRGBA texture;
    private Canvas canvas;

    public void init() {
        this.texture = PixmapIO.load("/blocks/oak_leaves.png");
        this.canvas = new Canvas();
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();
        canvas.clear();
        canvas.drawPixmap(texture,
                (double) canvas.getWidth() / texture.getWidth(),
                (double) canvas.getHeight() / texture.getHeight());
        final int x = (int) Jpize.getX();
        final int y = (int) Jpize.input().getCursorNativeY();
        canvas.colorize(0.9F, 0.6F, 0.0F);
        canvas.enableBlending();
        canvas.drawCircle(Jpize.getWidth() / 2, Jpize.getHeight() / 2, (int) Vec2i.dst(Jpize.getWidth() / 2, Jpize.getHeight() / 2, x, y), new Color(1, 0, 0));
        canvas.fillRGBA(Jpize.getWidth() / 2, Jpize.getHeight() / 2, x, y, 0xFFFFFF99);
        canvas.disableBlending();

        canvas.render();
    }

    @Override
    public void resize(int width, int height) {
        canvas.resize(width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
        canvas.dispose();
    }

    public static void main(String[] args) {
        if(System.getProperty("os.name").equals("Linux"))
            Glfw.glfwInitHintPlatform(GlfwPlatform.X11); // waiting for fixes in lwjgl 3.3.5

        Jpize.create(1080, 1080, "Window").build().setApp(new PixmapTest());

        Jpize.run();
    }

}