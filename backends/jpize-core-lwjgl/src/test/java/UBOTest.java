import jpize.context.Jpize;
import jpize.context.JpizeApplication;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.opengl.gl.GL;
import jpize.opengl.buffer.UniformBufferStd140;
import jpize.opengl.shader.Shader;
import jpize.opengl.texture.Texture2D;
import generaloss.spatialmath.matrix.Matrix4f;
import generaloss.spatialmath.vector.Vec2f;
import jpize.util.RenderQuad;
import generaloss.resourceflow.resource.Resource;

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

        GL.clearColorBuffer();
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
        GlfwContextBuilder.create(1080, 720, "Uniform Buffer Test")
            .icon("/icon2.png")
            .samples(8)
            .build().setApp(new UBOTest());

        GlfwContextManager.run();
    }

}