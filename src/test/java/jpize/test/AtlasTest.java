package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.util.atlas.TextureAtlas;
import jpize.util.mesh.TextureBatch;

public class AtlasTest extends JpizeApplication {

    private TextureBatch batch;
    private TextureAtlas<String> atlas;

    public void init() {
        Gl.clearColor(0.3, 0.4, 0.7);
        this.batch = new TextureBatch();
        this.atlas = new TextureAtlas<>();
        atlas.put("a", "/icon.png");
        atlas.build(512, 512);
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
