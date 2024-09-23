package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.util.font.Font;
import jpize.util.font.FontLoader;
import jpize.gl.texture.GlTexture2D;
import jpize.util.TextureBatch;

public class LoadingWindowTest {

    public static void main(String[] args) {
        Glfw.glfwInitHintPlatform(GlfwPlatform.X11);
        Jpize.create(720, 480, "Loading...")
            .decorated(false).resizable(false).build()
            .skipRender(true).setApp(new LoadingWindow());
        Jpize.run();
    }

    private static class LoadingWindow extends JpizeApplication {
        TextureBatch batch = new TextureBatch();
        GlTexture2D bg = new GlTexture2D("/bg.png");
        Font font = FontLoader.loadDefault();
        public void init() {
            batch.begin();
            batch.draw(bg, 0, 0, Jpize.getWidth(), Jpize.getHeight());
            font.drawText(batch, "Loading...", 50, 50);
            batch.end();

            Jpize.syncExecutor().execLater(2F, () -> {
                Jpize.create(1280, 720, "Content Window")
                    .icon("/icon.png").build()
                    .setApp(new ContextWindow());
                Jpize.exitOthers();
            });
        }
        public void dispose() {
            batch.dispose();
            bg.dispose();
            font.dispose();
        }
    }

    private static class ContextWindow extends JpizeApplication { }

}
