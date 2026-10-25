package coretests;

import generaloss.resourceflow.resource.Resource;
import jpize.context.Jpize;
import jpize.context.JpizeApplication;
import jpize.context.input.Key;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.opengl.gl.GL;
import jpize.opengl.glenum.GLTarget;
import jpize.opengl.shader.Shader;
import jpize.opengl.tesselation.GLPrimitive;
import jpize.opengl.texture.Texture2D;
import jpize.opengl.type.GLType;
import jpize.opengl.vertex.GLVertAttr;
import jpize.util.camera.Camera;
import jpize.util.camera.OrthographicCamera;
import jpize.util.mesh.IndexedMesh;

public class IndexedMeshTest extends JpizeApplication {

    private final Camera camera;
    private final IndexedMesh mesh;
    private final Shader shader;
    private final Texture2D texture;

    public IndexedMeshTest() {
        GL.clearColor(0.5F, 0.6F, 0.7F);
        GL.disable(GLTarget.CULL_FACE);

        this.camera = new OrthographicCamera();

        this.mesh = new IndexedMesh(
            new GLVertAttr(2, GLType.FLOAT), // position
            new GLVertAttr(2, GLType.FLOAT), // uv
            new GLVertAttr(4, GLType.FLOAT)  // color
        );
        texture = new Texture2D("/background.png");
        this.mesh.setMode(GLPrimitive.TRIANGLES);
        this.mesh.vertices().setData(
            0F,    500F,   0F, 0F,  1F, 1F, 1F, 1F,
            0F,    0F,     0F, 1F,  1F, 1F, 1F, 1F,
            800F,  0F,     1F, 1F,  1F, 1F, 1F, 1F,
            800F,  500F,   1F, 0F,  1F, 1F, 1F, 1F
        );
        mesh.indices().setData(
            0, 1, 2,
            2, 3, 0
        );

        this.shader = new Shader(
            Resource.internal("/shader/texture_batch/vert.glsl"),
            Resource.internal("/shader/texture_batch/frag.glsl")
        );
    }


    @Override
    public void update() {
        camera.update();

        if(Key.ESCAPE.down())
            Jpize.exit();
    }

    @Override
    public void render() {
        GL.clearColorDepthBuffers();

        shader.bind();
        shader.uniform("u_combined", camera.getCombined());
        shader.uniform("u_texture", texture);
        mesh.render();
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(width, height);
    }

    @Override
    public void dispose() {
        shader.dispose();
        mesh.dispose();
        texture.dispose();
    }


    public static void main(String[] args) {
        GlfwContextBuilder.create(1280, 720, "Indexed Mesh Test")
            .icon("/icon.png")
            .build()
            .setApp(new IndexedMeshTest());

        GlfwContextManager.run();
    }

}