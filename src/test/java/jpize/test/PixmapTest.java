package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.util.pixmap.Canvas;
import jpize.util.pixmap.PixmapIO;
import jpize.util.pixmap.PixmapRGBA;

public class PixmapTest extends JpizeApplication {

    private PixmapRGBA texture;
    private Canvas canvas;

    public void init() {
        this.texture = PixmapIO.load("/accented.png");
        this.canvas = new Canvas();
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();
        canvas.clear();
        canvas.drawPixmap(texture, 0, 0, (float) canvas.getWidth() / texture.getWidth(), (float) canvas.getHeight() / texture.getHeight());
        final int x = (int) Jpize.getX();
        final int y = (int) Jpize.input().getCursorNativeY();
        canvas.drawLineRGBA(Jpize.getWidth() / 2, Jpize.getHeight() / 2, x, y, 0xFFFFFFFF);
        canvas.colorize(1F, 0F, 0F);
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

        Jpize.create(720, 480, "Window").build().setApp(new PixmapTest());

        Jpize.run();
    }

}