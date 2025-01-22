package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.util.font.Font;
import jpize.util.font.FontRenderOptions;
import jpize.util.mesh.TextureBatch;
import jpize.util.res.Resource;

import java.io.IOException;
import java.util.zip.ZipFile;

public class TextTest extends JpizeApplication {

    private final TextureBatch batch;
    private final Font font;
    private final FontRenderOptions options;

    public TextTest() throws IOException {
        this.batch = new TextureBatch();
        final ZipFile zip = new ZipFile("src/test/resources/font.zip");
        this.font = new Font().loadFNT(Resource.zip(zip, zip.getEntry("font.fnt")), false);
        this.options = font.getRenderOptions();

        options.setInvLineWrap(true);
        options.scale().set(4F);
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


    public static void main(String[] args) throws IOException {
        Jpize.create(1080, 720, "Cloth Simulation")
            .samples(8)
            .build().setApp(new TextTest());

        Jpize.run();
    }

}
