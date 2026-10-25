package coretests;

import jpize.context.Jpize;
import jpize.context.JpizeApplication;
import jpize.context.input.Key;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.opengl.gl.GL;
import jpize.opengl.glenum.GLTarget;
import jpize.util.Skybox;
import jpize.util.camera.PerspectiveCamera;
import jpize.util.font.Font;
import jpize.util.font.FontRenderOptions;
import jpize.util.input.MotionInput;
import jpize.util.input.RotationInput;
import generaloss.spatialmath.vector.Vec2f;
import generaloss.spatialmath.vector.Vec3f;

public class Camera3DTest extends JpizeApplication {

    private final PerspectiveCamera camera;
    private final RotationInput rotInput;
    private final MotionInput motionInput;
    private final Skybox skybox;
    private final Font font;

    public Camera3DTest() {
        GL.clearColor(0.5F, 0.6F, 0.7F);
        GL.disable(GLTarget.CULL_FACE);

        this.camera = new PerspectiveCamera(0.1F, 100F, 90F);

        this.rotInput = new RotationInput(camera.rotation());
        this.rotInput.setClampPitch(false);
        this.rotInput.setSpeed(0.1F);

        this.motionInput = new MotionInput();

        this.skybox = new Skybox(
            "/camera3d/skybox_positive_x.png", "/camera3d/skybox_negative_x.png",
            "/camera3d/skybox_positive_y.png", "/camera3d/skybox_negative_y.png",
            "/camera3d/skybox_positive_z.png", "/camera3d/skybox_negative_z.png"
        );

        // --- TEST SKYBOX setImage() ---

        // for(GLCubemapTarget target: GLCubemapTarget.values()) {
        //     final Resource resource = Resource.internal("/skybox_" + target.toString().toLowerCase() + ".png");
        //     final Pixmap pixmap = PixmapIO.load(resource);
        //     skybox.setImage(target, pixmap);
        //     pixmap.dispose();
        // }

        // for(GLCubemapTarget target: GLCubemapTarget.values()) {
        //     final Resource resource = Resource.internal("/skybox_" + target.toString().toLowerCase() + ".png");
        //     final Pixmap pixmap = PixmapIO.load(resource);
        //     skybox.setImage(target, pixmap);
        //     pixmap.dispose();
        // }

        // ------------------------------

        this.font = new Font().loadFNT("/fonts/font_2.fnt", false);
    }

    @Override
    public void update() {
        motionInput.update(camera.rotation().yaw);
        System.out.println("yaw: " + camera.rotation().yaw + ", dir: " + camera.rotation().getDirectionHorizontal(new Vec3f()) + ", pos: " + camera.position());
        camera.position().add(motionInput.getMotionDirected().mul(Jpize.getDeltaTime() * 10));
        camera.update();

        if(Key.F11.down()) {
            rotInput.lockInputs();
            Jpize.window.toggleFullscreen();
        }
        if(Key.ESCAPE.down())
            Jpize.exit();
    }

    @Override
    public void render() {
        GL.clearColorDepthBuffers();
        GL.enable(GLTarget.DEPTH_TEST);

        // skybox
        skybox.render(camera);

        // 3D text
        final FontRenderOptions options = font.getOptions();
        options.scale().set(0.2);

        options.matrix().identity();
        font.drawText(camera, "Static text\nPizza\npizza", 5F, 2F, 0F);

        // bloom.begin();

        float angleY = Vec2f.angle(camera.getX(), camera.getZ()) + 90;
        float angleX = Vec2f.angleBetween(Vec2f.len(camera.getX(), camera.getZ()), camera.getY(), 0, 1) - 90;
        float angleZ = camera.rotation().getYaw();
        options.matrix().setRotationXYZ(angleX, angleY, 0);
        font.drawText(camera, "Test text 3D\nPizza", -font.getTextWidth("Test text 3D\nPizza") * 0.5F, -font.getTextHeight("Test text 3D\nPizza") * 0.5F, 0F);

        // bloom.end();
        GL.disable(GLTarget.DEPTH_TEST);

        options.scale().set(5);
        font.drawText("Test text", 100, 100);
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(width, height);
    }

    @Override
    public void dispose() {
        skybox.dispose();
        font.dispose();
    }


    public static void main(String[] args) {
        GlfwContextBuilder.create(1280, 720, "Camera 3D Test")
            .icon("/icon.png")
            .build()
            .setApp(new Camera3DTest());

        GlfwContextManager.run();
    }

}
