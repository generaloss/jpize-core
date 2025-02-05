package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.gl.buffer.GlUniformBuffer;
import jpize.gl.shader.Shader;
import jpize.gl.texture.Texture2D;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.postprocess.RenderQuad;
import jpize.util.res.Resource;

import java.io.IOException;

public class UBOTest extends JpizeApplication {

    private final Texture2D texture;
    private final Shader shader;
    private final Matrix4f mat;
    private final GlUniformBuffer statesBuf;

    public UBOTest() {
        this.texture = new Texture2D("/bg.png");

        this.shader = new Shader(
            Resource.internal("/vert.glsl"),
            Resource.internal("/frag.glsl")
        );

        this.mat = new Matrix4f();

        this.statesBuf = new GlUniformBuffer();
        statesBuf.allocateData(16 * 4);
        statesBuf.setSubData(0L, mat.val);

        shader.bind();
        shader.uniform("State", statesBuf);
    }

    @Override
    public void render() {
        mat.identity().translate(Jpize.getX() / Jpize.getWidth(), Jpize.getY() / Jpize.getHeight());
        statesBuf.setSubData(0L, mat.val);

        Gl.clearColorBuffer();
        shader.bind();
        shader.uniform("u_texture", texture);
        RenderQuad.instance().render();
    }

    @Override
    public void dispose() {
        shader.dispose();
        texture.dispose();
        statesBuf.dispose();
    }

    public static void main(String[] args) throws IOException {
        Jpize.create(1080, 720, "Uniform Buffer Test")
            .samples(8)
            .build().setApp(new UBOTest());

        Jpize.run();
    }

}