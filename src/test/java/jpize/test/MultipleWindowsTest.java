package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.glfw.input.Key;
import jpize.util.ctrl.TextInput;
import jpize.util.font.Font;
import jpize.util.font.FontLoader;
import jpize.gl.texture.GlTexture2D;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.TextureBatch;

public class MultipleWindowsTest {

    public static void main(String[] args) {
        if(System.getProperty("os.name").equals("Linux")) Glfw.glfwInitHintPlatform(GlfwPlatform.X11);
        Jpize.create("Window 1", 800, 600)
            .icon("/icon.png").build()
            .setApp(new Window1());
        Jpize.create("Window 2", 800, 600)
            .icon(new PixmapRGBA(16, 16).fill(0, 0, 15, 15, 1, 0, 1, 1F)).build()
            .setApp(new Window2());

        System.out.println("platform: " + Glfw.getPlatform());
        Jpize.run();
    }

    public static class Window1 extends JpizeApplication {
        final TextureBatch batch = new TextureBatch();
        final GlTexture2D texture = new GlTexture2D("/icon.png");
        public void init() {
            Gl.clearColor(1, 1, 1, 1F);
        }
        public void update() {
            if(Key.ESCAPE.down()) Jpize.exitOthers();
            if(Key.E.down()) Jpize.exit();
        }
        int angle = 0;
        public void render() {
            Gl.clearColorBuffer();
            batch.rotate(angle++);
            batch.begin();
            batch.draw(texture, 200 * 0.3F, 200 * 0.3F, 400, 400);
            batch.end();
        }
        public void dispose() {
            batch.dispose();
            texture.dispose();
        }
    }

    public static class Window2 extends JpizeApplication {
        final Font font = FontLoader.loadDefaultBold();
        final TextInput textProc = new TextInput().enable();
        public void init() {
            textProc.insert("I want pizza");
            Gl.clearColor(0.02, 0.05, 0.12, 1F);
        }
        public void update() {
            if(Key.ESCAPE.down()) Jpize.exitOthers();
            if(Key.E.down()) Jpize.exit();
        }
        public void render() {
            Gl.clearColorBuffer();
            font.drawText(textProc.makeString(true), 100, 100);
        }
        public void dispose() {
            Jpize.window().hide();
            font.dispose();
        }
    }

}
