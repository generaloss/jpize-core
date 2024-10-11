package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.util.Canvas;
import jpize.util.math.Mathc;

public class CanvasTest extends JpizeApplication {

    private Canvas canvas;
    private float time;

    public void init() {
        this.canvas = new Canvas();
        new Thread(() -> System.out.println(Jpize.context())).start();
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();
        canvas.clear();

        time += Jpize.getDT();
        canvas.drawLine((int) (100 + (Mathc.sin(time) * 0.5 + 0.5) * 300), 100, 400, 400,  1, 1, 1, 1F);

        canvas.render();
    }

    @Override
    public void resize(int width, int height) {
        canvas.resize(width, height);
    }


    public static void main(String[] args) {
        if(System.getProperty("os.name").equals("Linux"))
            Glfw.glfwInitHintPlatform(GlfwPlatform.X11); // waiting for fixes in lwjgl 3.3.5

        Jpize.create(720, 480, "Window")
            .build().setApp(new CanvasTest());

        Jpize.run();
    }

}
