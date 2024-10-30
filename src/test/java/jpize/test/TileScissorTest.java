package jpize.test;

import jpize.app.Jpize;
import jpize.app.JpizeApplication;
import jpize.gl.Gl;
import jpize.gl.tesselation.GlScissor;
import jpize.gl.texture.Texture2D;
import jpize.gl.texture.TextureBatch;
import jpize.glfw.Glfw;
import jpize.glfw.init.GlfwPlatform;
import jpize.glfw.input.MouseBtn;
import jpize.util.camera.OrthographicCameraCentered;
import jpize.util.math.Mathc;
import jpize.util.math.Maths;
import jpize.util.math.vector.Vec2f;
import jpize.util.pixmap.PixmapRGBA;

public class TileScissorTest extends JpizeApplication {

    private final TextureBatch batch;
    private final OrthographicCameraCentered camera;
    private final Texture2D texture;
    private float scale = 1F;
    private final Vec2f prev = new Vec2f();
    private final Vec2f position = new Vec2f();
    private final Vec2f cinematic_position = new Vec2f();
    private final GlScissor<String> scissor;

    public TileScissorTest() {
        this.batch = new TextureBatch();
        this.camera = new OrthographicCameraCentered();
        this.texture = new Texture2D(
            new PixmapRGBA(21, 21)
            .fill(0, 0, 20, 20,  1D, 1D, 1D, 1D)
            .fill(0, 0, 17, 17,  0.4D, 0.4D, 0.4D, 1D)
        );
        this.scissor = new GlScissor<String>()
            .put("scissor_1",  100, 100, camera.getWidth() - 200, camera.getHeight() - 200);
    }

    @Override
    public void update() {
        scale *= Mathc.pow(1.15F, Jpize.getScroll());
        camera.setScale(camera.getScale() + (scale - camera.getScale()) * 0.1F);

        if(MouseBtn.LEFT.down())
            Jpize.input().getCursorPos(prev);
        if(MouseBtn.LEFT.pressed())
            cinematic_position.sub(Jpize.input().getCursorPos(position).copy().sub(prev).div(camera.getScale()));
        prev.set(position);
        camera.position().add(cinematic_position.copy().sub(camera.position()).div(10));

        camera.update();
    }

    @Override
    public void render() {
        Gl.clearColorBuffer();
        batch.setup(camera);
        scissor.apply();

        for(int i = Maths.floor((camera.getX() - camera.getScaledHalfWidth()) / 50); i <= (camera.getX() + camera.getScaledHalfWidth()) / 50; i++)
            for(int j = Maths.floor((camera.getY() - camera.getScaledHalfHeight()) / 50); j <= (camera.getY() + camera.getScaledHalfHeight()) / 50; j++)
                batch.draw(texture, i * 50, j * 50, 50, 50);

        batch.render();
        scissor.disable();

        batch.draw(texture, -25, -25, 50, 50);
        batch.render();
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }


    public static void main(String[] args) {
        if(System.getProperty("os.name").equals("Linux"))
            Glfw.glfwInitHintPlatform(GlfwPlatform.X11);

        Jpize.create(1280, 720, "Tile Test")
            .build().setApp(new TileScissorTest());

        Jpize.run();
    }

}
