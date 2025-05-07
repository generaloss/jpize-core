import jpize.context.JpizeApplication;
import jpize.context.input.Key;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.opengl.gl.GL;
import jpize.opengl.texture.Texture2D;
import jpize.util.font.Font;
import jpize.util.mesh.TextureBatch;
import jpize.util.screen.AbstractScreen;
import jpize.util.screen.ScreenManager;

public class ScreensTest extends JpizeApplication {

    private static class TestScreen extends AbstractScreen<String> {

        private final TextureBatch batch;
        private final Texture2D texture;
        private final Font font;

        public TestScreen() {
            this.batch = new TextureBatch();
            this.texture = new Texture2D("/background.png");
            this.font = new Font().loadDefault();
        }

        @Override
        public void init() {
            System.out.println("init " + super.getID());
        }

        @Override
        public void show() {
            System.out.println("show " + super.getID());
        }

        @Override
        public void hide() {
            System.out.println("hide " + super.getID());
        }

        private int width;
        private int height;

        @Override
        public void render() {
            batch.setup();
            batch.draw(texture, 0, 0, width, height);
            font.drawText(batch, "Screen: '" + super.getID() + "'", 100, 100);
            batch.render();
        }

        @Override
        public void resize(int width, int height) {
            System.out.println("resize_" + width + "x" + height + " " + super.getID());
            this.width = width;
            this.height = height;
        }

        @Override
        public void dispose() {
            System.out.println("dispose " + super.getID());
            batch.dispose();
            texture.dispose();
            font.dispose();
        }
    }


    private final ScreenManager<String> screens;

    public ScreensTest() {
        this.screens = new ScreenManager<>();
        this.screens.register("session", new TestScreen());
        this.screens.register("title", new TestScreen()).setCurrent();
    }

    @Override
    public void update() {
        screens.update();

        if(Key.T.down()){
            if(screens.get("title").isCurrent()) {
                screens.setCurrent("session");
            }else{
                screens.setCurrent("title");
            }
        }
    }

    @Override
    public void render() {
        GL.clearColorBuffer();
        screens.render();
    }

    @Override
    public void resize(int width, int height) {
        screens.resize(width, height);
    }

    @Override
    public void dispose() {
        screens.dispose();
    }


    public static void main(String[] args) {
        GlfwContextBuilder.create(1280, 720, "My App")
            .icon("/icon2.png")
            .build().setApp(new ScreensTest());
        GlfwContextManager.run();
    }

}