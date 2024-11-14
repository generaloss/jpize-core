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
import jpize.glfw.monitor.GlfwMonitor;
import jpize.util.camera.PerspectiveCamera;
import jpize.util.input.MotionInput;
import jpize.util.input.RotationInput;
import jpize.util.math.EulerAngles;
import jpize.util.mesh.Mesh;
import jpize.util.res.Resource;

public class Camera3DTest extends JpizeApplication {

    private final PerspectiveCamera camera;
    private final EulerAngles rotation;
    private final RotationInput rotInput;
    private final MotionInput motionInput;
    private final Mesh mesh;
    private final Skybox skybox;
    private final Shader shader;

    public Camera3DTest() {
        Gl.clearColor(0.5F, 0.6F, 0.7F);
        Gl.disable(GlTarget.CULL_FACE);

        this.camera = new PerspectiveCamera(0.1F, 100F, 90F);

        this.rotation = new EulerAngles();

        this.rotInput = new RotationInput(rotation);
        this.rotInput.setClampPitch(false);
        this.rotInput.setSmoothness(0.01F);
        this.rotInput.lockNextFrame();

        this.motionInput = new MotionInput();

        this.mesh = new Mesh(
            new GlVertAttr(3, GlType.FLOAT),
            new GlVertAttr(2, GlType.FLOAT)
        );
        mesh.vertices().setData(
             20F, -10F,  20F,  1F, 1F,
             20F, -10F, -20F,  0F, 1F,
            -20F, -10F, -20F,  0F, 0F,
            -20F, -10F, -20F,  0F, 0F,
            -20F, -10F,  20F,  1F, 0F,
             20F, -10F,  20F,  1F, 1F
        );

        this.shader = new Shader(Resource.internal("/shader.vert"), Resource.internal("/shader.frag"));

        this.skybox = new Skybox(
            "/skybox_positive_x.png", "/skybox_negative_x.png",
            "/skybox_positive_y.png", "/skybox_negative_y.png",
            "/skybox_positive_z.png", "/skybox_negative_z.png");
    }

    @Override
    public void init() {
        Glfw.init();
        GlfwMonitor monitor = GlfwMonitor.getPrimaryMonitor();
    }

    private final Texture2D texture_floor = new Texture2D("/cube4.png");

    @Override
    public void update() {
        motionInput.update(rotation.yaw);
        camera.rotation().setRotation(rotation);
        camera.position().add(motionInput.getMotionDirected().mul(Jpize.getDeltaTime() * 10));
        camera.update();

        if(Key.F11.down()) {
            rotInput.lockNextFrame();
            Jpize.window().toggleFullscreen();
        }
        if(Key.ESCAPE.down()) Jpize.exit();
    }

    @Override
    public void render() {
        Gl.clearColorDepthBuffers();

        skybox.render(camera);
        shader.bind();
        shader.uniform("u_combined", camera.getCombined());
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
            Glfw.glfwInitHintPlatform(GlfwPlatform.WAYLAND);

        Jpize.create(1280, 720, "Quaternion Camera")
            .build().setApp(new Camera3DTest());

        Jpize.run();
    }

}
