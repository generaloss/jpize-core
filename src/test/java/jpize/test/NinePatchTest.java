package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.util.ninepatch.NinePatch;
import jpize.util.mesh.TextureBatch;
import jpize.util.ninepatch.StretchMode;

public class NinePatchTest extends JpizeApplication {

    private TextureBatch batch;
    private NinePatch ninePatch;

    public void init() {
        Gl.clearColor(0.3, 0.6, 0.9);
        this.batch = new TextureBatch();
        this.ninePatch = new NinePatch()
            .load("/button.9.png")
            .setStretchMode(StretchMode.STRETCH)
            .setScale(15F);
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();
        batch.setup();
        ninePatch.draw(batch, 50, 50, Jpize.getX() - 50, Jpize.getY() - 50);
        batch.drawRect(
            50 + ninePatch.getContentX(Jpize.getX() - 50), 50 + ninePatch.getContentY(Jpize.getY() - 50),
            ninePatch.getContentWidth(Jpize.getX() - 50), ninePatch.getContentHeight(Jpize.getY() - 50),
            0.5
        );
        batch.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        ninePatch.dispose();
    }

    public static void main(String[] args) {
        if(System.getProperty("os.name").equals("Linux"))
            Glfw.glfwInitHintPlatform(GlfwPlatform.X11); // waiting for fixes in lwjgl 3.3.6

        Jpize.create(1280, 720, "Window").build().setApp(new NinePatchTest());
        Jpize.run();
    }

}