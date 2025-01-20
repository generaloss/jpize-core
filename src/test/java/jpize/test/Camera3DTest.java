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
import jpize.glfw.input.Key;
import jpize.util.camera.PerspectiveCamera;
import jpize.util.font.Font;
import jpize.util.font.FontRenderOptions;
import jpize.util.input.MotionInput;
import jpize.util.input.RotationInput;
import jpize.util.math.EulerAngles;
import jpize.util.math.vector.Vec2f;
import jpize.util.mesh.Mesh;
import jpize.util.res.Resource;

public class Camera3DTest extends JpizeApplication {

    private final PerspectiveCamera camera;
    private final RotationInput rotInput;
    private final MotionInput motionInput;
    private final Mesh mesh;
    private final Skybox skybox;
    private final Shader shader;
    private final Font font;

    public Camera3DTest() {
        Gl.clearColor(0.5F, 0.6F, 0.7F);
        Gl.disable(GlTarget.CULL_FACE);

        this.camera = new PerspectiveCamera(0.1F, 100F, 90F);

        this.rotInput = new RotationInput(new EulerAngles());
        this.rotInput.setClampPitch(false);
        this.rotInput.setSpeed(0.1F);

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

        this.font = new Font().loadFNT("/font.fnt", false);
    }

    private final Texture2D texture_floor = new Texture2D("/cube4.png");

    @Override
    public void update() {
        camera.rotation().setRotation(rotInput.getTarget());
        motionInput.update(camera.rotation().getYaw());
        camera.position().add(motionInput.getMotionDirected().mul(Jpize.getDeltaTime() * 10));
        camera.update();

        if(Key.F11.down()) {
            rotInput.lockInputs();
            Jpize.window().toggleFullscreen();
        }
        if(Key.ESCAPE.down()) Jpize.exit();
    }

    @Override
    public void render() {
        Gl.clearColorDepthBuffers();

        Gl.enable(GlTarget.DEPTH_TEST);

        // skybox
        skybox.render(camera);
        // quad
        shader.bind();
        shader.uniform("u_combined", camera.getCombined());
        shader.uniform("u_texture", texture_floor);
        //mesh.render();
        // 3D text
        final FontRenderOptions options = font.getRenderOptions();
        options.scale().set(0.2);

        float angleY = Vec2f.angle(camera.getX(), camera.getZ()) + 90;
        float angleX = Vec2f.angleBetween(Vec2f.len(camera.getX(), camera.getZ()), camera.getY(), 0, 1) - 90;
        float angleZ = camera.rotation().getYaw();

        options.matrix().setRotationXYZ(angleX, angleY, 0);

        font.drawText(camera, "Test text 3D\nPizza", -font.getTextWidth("Test text 3D\nPizza") * 0.5F, -font.getTextHeight("Test text 3D\nPizza") * 0.5F, 0F);

        Gl.disable(GlTarget.DEPTH_TEST);

        options.scale().set(4);
        font.drawText("Test text", 100, 100);
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
        font.dispose();
    }


    public static void main(String[] args) {
        // if(System.getProperty("os.name").equals("Linux"))
        //     Glfw.glfwInitHintPlatform(GlfwPlatform.X11);

        Jpize.create(1280, 720, "Quaternion Camera")
            .build().setApp(new Camera3DTest());

        Jpize.run();
    }

}
