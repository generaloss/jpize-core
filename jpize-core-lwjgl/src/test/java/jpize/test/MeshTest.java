package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.opengl.gl.Gl;
import jpize.opengl.shader.Shader;
import jpize.opengl.tesselation.GlPrimitive;
import jpize.opengl.texture.Texture2D;
import jpize.opengl.type.GlType;
import jpize.opengl.vertex.GlVertAttr;
import jpize.util.camera.OrthographicCamera;
import jpize.util.mesh.Mesh;
import jpize.util.res.Resource;

public class MeshTest extends JpizeApplication {

    private Shader shader;
    private Texture2D texture;
    private Mesh mesh;
    private OrthographicCamera camera;

    @Override
    public void init() {
        Gl.clearColor(0.3, 0.4, 0.7);
        this.shader = new Shader(Resource.internal("/shader/render_quad/vert.glsl"), Resource.internal("/shader/render_quad/frag.glsl"));
        this.texture = new Texture2D("/icon2.png");
        this.mesh = new Mesh(
            new GlVertAttr(2, GlType.FLOAT), // position
            new GlVertAttr(2, GlType.FLOAT)  // texcoord
        );
        mesh.setMode(GlPrimitive.QUADS);

        mesh.vertices().setData(
            0F,   100F,  0F, 0F, // 0
            0F,   0F,    0F, 1F, // 1
            100F, 0F,    1F, 1F, // 2
            100F, 100F,  1F, 0F  // 3
        );

        camera = new OrthographicCamera();
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();

        camera.update();

        shader.bind();
        shader.uniform("u_texture", texture);
        shader.uniform("u_proj", camera.getProjection());
        mesh.render();
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
        shader.dispose();
        mesh.dispose();
    }


    public static void main(String[] args) {
        Jpize.create(720, 720, "Window").build().setApp(new MeshTest());
        Jpize.run();
    }

}
