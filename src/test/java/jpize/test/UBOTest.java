package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.opengl.Gl;
import jpize.opengl.buffer.UniformBufferStd140;
import jpize.opengl.shader.Shader;
import jpize.opengl.texture.Texture2D;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2f;
import jpize.util.postprocess.RenderQuad;
import jpize.util.res.Resource;

import java.io.IOException;

public class UBOTest extends JpizeApplication {

    private final Texture2D texture;
    private final Shader shader;
    private final Matrix4f matrix;
    private float brightness;
    private final UniformBufferStd140 statesBuf;

    public UBOTest() {
        this.texture = new Texture2D("/bg.png");
        this.shader = new Shader(
            Resource.internal("/vert.glsl"),
            Resource.internal("/frag.glsl")
        );
        this.matrix = new Matrix4f();

        this.statesBuf = new UniformBufferStd140();
        statesBuf.allocateData(64 + 16 + 16);
        this.updateUBO();

        shader.bind();
        shader.uniform("State", statesBuf);
    }

    private void updateUBO() {
        statesBuf.reset();
        statesBuf.put(matrix);
        statesBuf.put(0.5F, brightness);
    }

    @Override
    public void render() {
        matrix.identity().translate(Jpize.getX() / Jpize.getWidth() - 0.5F, Jpize.getY() / Jpize.getHeight() - 0.5F);
        brightness = Vec2f.dst(Jpize.getHalfWidth(), Jpize.getHalfHeight(), Jpize.getX(), Jpize.getY()) / 500F;
        this.updateUBO();

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