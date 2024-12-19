package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.util.font.Font;
import jpize.gl.texture.Texture2D;
import jpize.util.mesh.TextureBatch;

public class LoadingWindowTest {

    private static class MainWindow extends JpizeApplication { /* ... */ }

    private static class LoadingWindow extends JpizeApplication {
        TextureBatch batch = new TextureBatch();
        Texture2D bg = new Texture2D("/bg.png");
        Font font = new Font().loadDefault();
        public void init() {
            // show window because usually the window shows after init()
            Jpize.window().show();

            // render
            batch.setup();
            batch.draw(bg, 0, 0, Jpize.getWidth(), Jpize.getHeight());
            font.drawText(batch, "Loading...", 50, 50);
            batch.render();

            // swap buffers to display the rendered content
            Jpize.window().swapBuffers();
            // disable render() to prevent epilepsy
            Jpize.context().disableRender(true);

            // create main window in 2 seconds
            Jpize.syncExecutor().execLater(2F, () -> {
                Jpize.create(1280, 720, "Content Window")
                    .icon("/icon.png").build()
                    .setApp(new MainWindow());
                // close loading window
                Jpize.exitOthers();
            });
        }
        public void dispose() {
            batch.dispose();
            bg.dispose();
            font.dispose();
        }
    }

    public static void main(String[] args) {
        if(System.getProperty("os.name").equals("Linux"))
            Glfw.glfwInitHintPlatform(GlfwPlatform.X11); // waiting for fixes in lwjgl 3.3.6

        Jpize.create(720, 480, "Loading...")
            .decorated(false).resizable(false)
            .build().setApp(new LoadingWindow());

        Jpize.run();
    }

}
