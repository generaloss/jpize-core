import jpize.context.JpizeApplication;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.opengl.gl.GL;
import jpize.util.font.Font;
import jpize.util.font.FontRenderOptions;
import jpize.util.font.GlyphIterator;
import jpize.util.mesh.TextureBatch;
import generaloss.resourceflow.resource.Resource;

import java.io.IOException;
import java.util.zip.ZipFile;

public class TextTest extends JpizeApplication {

    private final TextureBatch batch;
    private final Font font;

    public TextTest() throws IOException {
        this.batch = new TextureBatch();
        final ZipFile zip = new ZipFile("./backends/jpize-core-lwjgl/src/test/resources/font/font.zip");
        this.font = new Font().loadFNT(Resource.zip(zip, zip.getEntry("font.fnt")), false);
        final FontRenderOptions options = font.getOptions();

        options.setInvLineWrap(false);
        options.scale().set(4F);
        options.advanceFactor().set(1F, 1F);
        options.color().set(1F, 0.8F, 1F);
        options.setRotation(0F);
        options.rotationOrigin().set(0.5F, 0.5F);

        GlyphIterator a = font.drawText("I ", 0, 0);
        System.out.println(a.nextAdvanceX() + ", " + a.nextAdvanceY());
        GlyphIterator b = font.drawText("Want ", 0, 0);
        System.out.println(b.nextAdvanceX() + ", " + b.nextAdvanceY());
        GlyphIterator c = font.drawText("Pizza", 0, 0);
        System.out.println(c.nextAdvanceX() + ", " + c.nextAdvanceY());
        GlyphIterator d = font.drawText("\n", 0, 0);
        System.out.println(d.nextAdvanceX() + ", " + d.nextAdvanceY());
        GlyphIterator e = font.drawText("Bold text", 0, 0);
        System.out.println(e.nextAdvanceX() + ", " + e.nextAdvanceY());
    }

    @Override
    public void render() {
        GL.clearColorBuffer();
        batch.setup();
        final GlyphIterator state = font.drawText(batch, "public static void main(String[] args) {\n    System.out.println(\"pizza\");\n}", 100, 400);
        batch.drawRect(100, 400, state.bounds().x * state.options().scale().x, state.bounds().y * state.options().scale().y, 1F, 0.8F, 1F, 0.25F);
        batch.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }


    public static void main(String[] args) throws IOException {
        GlfwContextBuilder.create(1080, 720, "Cloth Simulation")
            .icon("/icon2.png")
            .samples(8)
            .build().setApp(new TextTest());

        GlfwContextManager.run();
    }

}
