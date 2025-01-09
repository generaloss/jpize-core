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
        this.batch.position().add(50);
        this.ninePatch = new NinePatch()
            .load("/dialog.9.png")
            .setStretchMode(StretchMode.STRETCH)
            .setScale(1)
            .setDrawCenter(true);
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();
        batch.setup();

        final float width  = (Jpize.getX() - batch.position().x);
        final float height = (Jpize.getY() - batch.position().y);

        // batch.drawRect(-1, -1, width + 2, height + 2, 1.0F, 0.3F, 0.3F);
        ninePatch.draw(batch, 0, 0, width, height);

        final float contentX = ninePatch.getContentX(width);
        final float contentY = ninePatch.getContentY(height);
        final float contentWidth = ninePatch.getContentWidth(width);
        final float contentHeight = ninePatch.getContentHeight(height);
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