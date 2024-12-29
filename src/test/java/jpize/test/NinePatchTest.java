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
            .load("/button_ll.9.png")
            .setStretchMode(StretchMode.STRETCH)
            .setScale(20.5);
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();
        batch.setup();

        float width  = (Jpize.getX() - 50);
        float height = (Jpize.getY() - 50);

        batch.drawRect(49, 49, width + 2, height + 2, 1.0F, 0.3F, 0.3F);
        ninePatch.draw(batch, 50, 50, width, height);

        float contentX = 50 + ninePatch.getContentX(width);
        float contentY = 50 + ninePatch.getContentY(height);
        float contentWidth = ninePatch.getContentWidth(width);
        float contentHeight = ninePatch.getContentHeight(height);
        batch.drawRect(contentX, contentY, contentWidth, contentHeight, 0.3F, 1.0F, 0.3F, 0.25F);

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