import jpize.context.Jpize;
import jpize.context.JpizeApplication;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.opengl.gl.GL;
import jpize.util.color.Color;
import generaloss.spatialmath.vector.Vec2i;
import jpize.util.pixmap.Canvas;
import jpize.util.pixmap.PixmapIO;
import jpize.util.pixmap.PixmapRGBA;

public class CanvasTest extends JpizeApplication {

    private PixmapRGBA texture;
    private Canvas canvas;

    public void init() {
        this.texture = PixmapIO.load("/blocks/oak_leaves.png");
        this.canvas = new Canvas();
    }

    @Override
    public void render() {
        GL.clearColorBuffer();
        canvas.clear();
        canvas.drawPixmap(texture,
            (double) canvas.getWidth() / texture.getWidth(),
            (double) canvas.getHeight() / texture.getHeight()
        );
        final int x = (int) Jpize.getX();
        final int y = (int) Jpize.getNativeY();
        canvas.colorize(0.9F, 0.6F, 0.0F);
        canvas.enableBlending();
        canvas.drawCircle(Jpize.getWidth() / 2, Jpize.getHeight() / 2, (int) Vec2i.dst(Jpize.getWidth() / 2, Jpize.getHeight() / 2, x, y), new Color(1, 0, 0));
        canvas.fillRGBA(Jpize.getWidth() / 2, Jpize.getHeight() / 2, x, y, 0xFFFFFF99);
        canvas.disableBlending();
        canvas.drawCircleRGB(x, y, 6, 0xFFFFFF);

        canvas.render();
    }

    @Override
    public void resize(int width, int height) {
        canvas.resize(width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
        canvas.dispose();
    }

    public static void main(String[] args) {
        GlfwContextBuilder.create(1080, 1080, "Window")
            .icon("/icon2.png")
            .build().setApp(new CanvasTest());
        GlfwContextManager.run();
    }

}