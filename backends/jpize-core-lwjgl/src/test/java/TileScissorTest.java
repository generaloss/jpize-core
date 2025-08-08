import jpize.context.Jpize;
import jpize.context.JpizeApplication;
import jpize.context.input.MouseBtn;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.opengl.gl.GL;
import jpize.opengl.tesselation.GLScissor;
import jpize.opengl.texture.Texture2D;
import jpize.util.font.Font;
import jpize.util.mesh.TextureBatch;
import jpize.util.camera.OrthographicCameraCentered;
import generaloss.spatialmath.Mathc;
import generaloss.spatialmath.Maths;
import generaloss.spatialmath.vector.Vec2f;
import jpize.util.pixmap.PixmapRGBA;

public class TileScissorTest extends JpizeApplication {

    private final TextureBatch batch;
    private final OrthographicCameraCentered camera;
    private final Texture2D texture;
    private final Font font;
    private final GLScissor<String> scissor;

    private float scale = 0.05F;
    private final Vec2f prev = new Vec2f();
    private final Vec2f position = new Vec2f();
    private final Vec2f cinematic_position = new Vec2f();

    public TileScissorTest() {
        this.batch = new TextureBatch();
        this.camera = new OrthographicCameraCentered();
        this.camera.setScale(scale);
        this.texture = new Texture2D(
            new PixmapRGBA(21, 21).fillRGB(0, 0, 20, 20, 0xFFFFFF).fillRGB(0, 0, 17, 17, 0x666666)
        );
        this.font = new Font().loadFNT("/font.fnt", false);//.loadDefault();
        this.scissor = new GLScissor<String>()
            .put("scissor_1",  100, 100, camera.getWidth() - 200, camera.getHeight() - 200);
    }

    @Override
    public void update() {
        scale *= Mathc.pow(1.15F, Jpize.getScroll());
        camera.setScale(camera.getScale() + (scale - camera.getScale()) * 0.1F);

        if(MouseBtn.LEFT.down())
            Jpize.input.getCursorPos(prev);
        if(MouseBtn.LEFT.pressed())
            cinematic_position.sub(Jpize.input.getCursorPos(position).copy().sub(prev).div(camera.getScale()));
        prev.set(position);
        camera.position().add(cinematic_position.copy().sub(camera.position()).div(10));

        camera.update();
    }

    @Override
    public void render() {
        GL.clearColorBuffer();
        batch.setup(camera);
        scissor.apply();

        for(int i = Maths.floor((camera.getX() - camera.getScaledHalfWidth()) / 50); i <= (camera.getX() + camera.getScaledHalfWidth()) / 50; i++)
            for(int j = Maths.floor((camera.getY() - camera.getScaledHalfHeight()) / 50); j <= (camera.getY() + camera.getScaledHalfHeight()) / 50; j++)
                batch.draw(texture, i * 50, j * 50, 50, 50);

        batch.render();
        scissor.disable();

        batch.draw(texture, -25, -25, 50, 50);
        batch.render();

        font.drawText("FPS: " + Jpize.getFPS(), 100, 100);
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
        GlfwContextBuilder.create(1280, 720, "Tile Test")
            .icon("/icon2.png")
            .build().setApp(new TileScissorTest());
        GlfwContextManager.run();
    }

}
