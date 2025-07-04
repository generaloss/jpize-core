import jpize.context.Jpize;
import jpize.context.JpizeApplication;
import jpize.context.input.Key;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.opengl.gl.GL;
import jpize.util.input.TextInput;
import jpize.util.font.Font;
import jpize.opengl.texture.Texture2D;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.mesh.TextureBatch;

public class MultipleWindowsTest {

    public static class Window1 extends JpizeApplication {
        final TextureBatch batch = new TextureBatch();
        final Texture2D texture = new Texture2D("/icon.png");
        public void init() {
            GL.clearColor(1, 1, 1, 1F);
        }
        public void update() {
            if(Key.E.down()) GlfwContextManager.closeAllOtherContexts();
            if(Key.ESCAPE.down()) Jpize.exit();
        }
        int angle = 0;
        public void render() {
            GL.clearColorBuffer();
            batch.rotate(angle++);
            batch.setup();
            batch.draw(texture, 200 * 0.3F, 200 * 0.3F, 400, 400);
            batch.render();
        }
        public void dispose() {
            batch.dispose();
            texture.dispose();
        }
    }

    public static class Window2 extends JpizeApplication {
        final Font font = new Font().loadFNT("/font.fnt", false);//.loadDefaultBold();
        final TextInput textProc = new TextInput().enable();
        public void init() {
            font.getOptions().scale().set(3F);
            textProc.insert("I want pizza");
            GL.clearColor(0.02, 0.05, 0.12, 1F);
        }
        public void update() {
            if(Key.E.down()) GlfwContextManager.closeAllOtherContexts();
            if(Key.ESCAPE.down()) Jpize.exit();
        }
        public void render() {
            GL.clearColorBuffer();
            font.drawText(textProc.makeString(true), 100, 100);
        }
        public void dispose() {
            Jpize.window.hide();
            font.dispose();
        }
    }

    public static void main(String[] args) {
        GlfwContextBuilder.create("Window 1", 800, 600)
            .icon("/icon2.png")
            .build().setApp(new Window1());

        GlfwContextBuilder.create("Window 2", 800, 600)
            .icon(new PixmapRGBA(16, 16).fill(0, 0, 15, 15, 1F, 0F, 1F, 1F))
            .build().setApp(new Window2());

        GlfwContextManager.run();
    }

}
