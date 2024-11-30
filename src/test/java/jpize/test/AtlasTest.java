package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.util.atlas.TextureAtlas;
import jpize.util.mesh.TextureBatch;
import jpize.util.res.Resource;

public class AtlasTest extends JpizeApplication {

    private TextureBatch batch;
    private TextureAtlas<String> atlas;

    public void init() {
        Gl.clearColor(0.3, 0.4, 0.7);
        this.batch = new TextureBatch();
        this.atlas = new TextureAtlas<>();
        for(Resource s: Resource.external("src/test/resources/blocks").listResources())
            atlas.put(s.simpleName(), "/blocks/" + s.name());

        atlas.setPadding(5, 3, 5, 3);

        atlas.setFillPaddings(true);

        atlas.build(128, 128);
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();
        batch.setup();
        batch.draw(atlas.getTexture(), 0F, 0F, Jpize.getWidth(), Jpize.getHeight());
        batch.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        atlas.dispose();
    }


    public static void main(String[] args) {
        if(System.getProperty("os.name").equals("Linux"))
            Glfw.glfwInitHintPlatform(GlfwPlatform.X11); // waiting for fixes in lwjgl 3.3.5

        Jpize.create(720, 480, "Window").build().setApp(new AtlasTest());

        Jpize.run();
    }

}
