package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.gl.glenum.GlTarget;
import jpize.gl.shader.Shader;
import jpize.gl.texture.Skybox;
import jpize.gl.texture.Texture2D;
import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertAttr;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.glfw.input.Key;
import jpize.util.camera.QuaternionCamera;
import jpize.util.ctrl.EulerRotCtrl;
import jpize.util.math.EulerAngles;
import jpize.util.mesh.Mesh;
import jpize.util.res.Resource;

public class Camera3DTest extends JpizeApplication {

    private final QuaternionCamera camera;
    private final EulerRotCtrl rotCtrl;
    private final Mesh mesh;
    private final Skybox skybox;
    private final Shader shader;

    public Camera3DTest() {
        Gl.clearColor(0.5F, 0.6F, 0.7F);
        Gl.disable(GlTarget.CULL_FACE);

        this.camera = new QuaternionCamera(0.1F, 100F, 90F);

        this.rotCtrl = new EulerRotCtrl(new EulerAngles());

        this.mesh = new Mesh(
            new GlVertAttr(3, GlType.FLOAT),
            new GlVertAttr(2, GlType.FLOAT)
        );
        mesh.vertices().setData(
            -100F, -10F,  100F,   1F, 0F,
            -100F, -10F, -100F,   0F, 0F,
             100F, -10F, -100F,   0F, 1F,
             100F, -10F, -100F,   0F, 1F,
             100F, -10F,  100F,   1F, 1F,
            -100F, -10F,  100F,   1F, 0F
        );

        this.shader = new Shader(Resource.internal("/shader.vert"), Resource.internal("/shader.frag"));

        this.skybox = new Skybox(
            "/skybox_positive_x.png", "/skybox_negative_x.png",
            "/skybox_positive_y.png", "/skybox_negative_y.png",
            "/skybox_positive_z.png", "/skybox_negative_z.png");
    }

    private final Texture2D texture_floor = new Texture2D("/floor.png");

    @Override
    public void update() {
        camera.quaternion().setRotation(rotCtrl.getTarget());
        camera.update();

        if(Key.F11.down()) {
            Jpize.window().toggleFullscreen();
            rotCtrl.lockNextFrame();
        }
        if(Key.ESCAPE.down()) Jpize.exit();
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();
        //
        skybox.render(camera);
        //
        shader.bind();
        shader.uniform("u_combined", camera.getCombined());
        //
        shader.uniform("u_texture", texture_floor);
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
        skybox.dispose();
    }


    public static void main(String[] args) {
        if(System.getProperty("os.name").equals("Linux"))
            Glfw.glfwInitHintPlatform(GlfwPlatform.X11);

        Jpize.create(1280, 720, "Quaternion Camera")
            .build().setApp(new Camera3DTest());

        Jpize.run();
    }

}
