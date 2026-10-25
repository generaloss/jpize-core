package coretests;

import jpize.context.JpizeApplication;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.opengl.gl.GL;
import jpize.util.atlas.TextureAtlas;
import jpize.util.RenderQuad;
import generaloss.resourceflow.resource.Resource;

public class AtlasTest extends JpizeApplication {

    private TextureAtlas<String> atlas;

    public void init() {
        GL.clearColor(0.3, 0.4, 0.7);
        this.atlas = new TextureAtlas<>();
        for(Resource s: Resource.file("./examples/jpize-core-tests/src/main/resources/atlas/").listResources())
            atlas.put(s.simpleName(), "/atlas/" + s.name());

        atlas.setPadding(5, 3, 5, 3);
        atlas.enablePaddingFilling(true);
        atlas.build(128, 128);
    }

    @Override
    public void render() {
        GL.clearColorBuffer();
        RenderQuad.instance().render(atlas.getTexture());
    }

    @Override
    public void dispose() {
        atlas.dispose();
    }


    public static void main(String[] args) {
        GlfwContextBuilder.create(720, 720, "Atlas Test")
            .icon("/icon.png")
            .build()
            .setApp(new AtlasTest());

        GlfwContextManager.run();
    }

}
