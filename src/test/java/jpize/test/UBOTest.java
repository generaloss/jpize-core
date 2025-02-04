package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.gl.buffer.GlUniformBuffer;
import jpize.gl.shader.Shader;
import jpize.gl.texture.Texture2D;
import jpize.glfw.input.Key;
import jpize.util.postprocess.RenderQuad;
import jpize.util.res.Resource;

import java.io.IOException;

public class UBOTest extends JpizeApplication {

    private final Texture2D texture;
    private final Shader shader;
    private final GlUniformBuffer texturesBuf;

    public UBOTest() {
        this.texture = new Texture2D("/bg.png");
        new Texture2D().dispose();

        this.shader = new Shader(
            Resource.internal("/vert.glsl"),
            Resource.internal("/frag.glsl")
        );

        this.texturesBuf = new GlUniformBuffer();
        texturesBuf.setData(texture.getID());

        shader.bind();
        shader.uniform("Textures", texturesBuf);
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();

        if(Key.C.down())
            new Texture2D().dispose();

        shader.bind();
        RenderQuad.instance().render();
    }

    @Override
    public void dispose() {
        shader.dispose();
        texture.dispose();
        texturesBuf.dispose();
    }

    public static void main(String[] args) throws IOException {
        Jpize.create(1080, 720, "Uniform Buffer Test")
            .samples(8)
            .build().setApp(new UBOTest());

        Jpize.run();
    }

}