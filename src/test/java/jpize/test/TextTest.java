package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.util.font.Font;
import jpize.util.font.FontLoader;
import jpize.util.font.FontRenderOptions;
import jpize.util.mesh.TextureBatch;

public class TextTest extends JpizeApplication {

    private final TextureBatch batch;
    private final Font font;
    private final FontRenderOptions options;

    public TextTest() {
        this.batch = new TextureBatch();
        this.font = FontLoader.loadDefault();
        this.options = font.getRenderOptions();

        options.setInvLineWrap(true);
        options.scale().set(1F, 1F);
        options.advanceFactor().set(1F, 1F);
        options.color().set(1F, 0.8F, 1F);
        options.setRotation(0F);
        options.rotationOrigin().set(0.5F, 0.5F);
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();
        batch.setup();
        font.drawText(batch, "public static void main(String[] args) {\n    System.out.println(\"pizza\");\n}", 100, 400);
        batch.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }


    public static void main(String[] args) {
        if(System.getProperty("os.name").equals("Linux"))
            Glfw.glfwInitHintPlatform(GlfwPlatform.X11);

        Jpize.create(1080, 720, "Cloth Simulation")
            .samples(8)
            .build().setApp(new TextTest());

        Jpize.run();
    }

}
