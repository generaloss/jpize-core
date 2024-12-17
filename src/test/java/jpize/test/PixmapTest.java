package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.gl.texture.Texture2D;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.util.pixmap.Canvas;

public class PixmapTest extends JpizeApplication {

    private Texture2D texture;
    private Canvas canvas;

    public void init() {
        this.canvas = new Canvas();
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();
        canvas.clearRGBA(0x44AAFFAA);
        canvas.fillRGBA(0, 0, (int) Jpize.getX(), (int) Jpize.input().getCursorNativeY(), 0xFF0000AA);
        canvas.render();
    }

    @Override
    public void resize(int width, int height) {
        canvas.resize(width, height);
    }

    @Override
    public void dispose() {
        canvas.dispose();
    }

    public static void main(String[] args) {
        if(System.getProperty("os.name").equals("Linux"))
            Glfw.glfwInitHintPlatform(GlfwPlatform.X11); // waiting for fixes in lwjgl 3.3.5

        Jpize.create(720, 480, "Window").build().setApp(new PixmapTest());

        Jpize.run();
    }

}