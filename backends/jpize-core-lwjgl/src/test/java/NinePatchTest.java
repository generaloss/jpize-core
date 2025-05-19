import jpize.context.Jpize;
import jpize.context.JpizeApplication;
import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.opengl.gl.GL;
import jpize.util.ninepatch.NinePatch;
import jpize.util.mesh.RectBatch;
import jpize.util.ninepatch.StretchMode;

public class NinePatchTest extends JpizeApplication {

    private RectBatch batch;
    private NinePatch ninePatch;

    public void init() {
        GL.clearColor(0.3, 0.6, 0.9);
        this.batch = new RectBatch();
        this.batch.position().add(50);
        this.batch.setRoundVertices(true);
        this.ninePatch = new NinePatch()
            .load("/button_l.9.png")
            .setStretchMode(StretchMode.STRETCH)
            .setScale(10)
            .setDrawCenter(true);
    }

    @Override
    public void render() {
        GL.clearColorBuffer();
        batch.setup();

        final float width  = (Jpize.getX() - batch.position().x);
        final float height = (Jpize.getY() - batch.position().y);

        batch.drawRectRGBA(0, 0, ninePatch.getMinWidth(), ninePatch.getMinHeight(), 0xFF000055);
        // batch.drawRect(-1, -1, width + 2, height + 2, 1.0F, 0.3F, 0.3F);
        ninePatch.draw(batch, 0, 0, width, height);

        final float contentX = ninePatch.getContentX(width);
        final float contentY = ninePatch.getContentY(height);
        final float contentWidth = ninePatch.getContentWidth(width);
        final float contentHeight = ninePatch.getContentHeight(height);
        batch.drawRectRGBA(contentX, contentY, contentWidth, contentHeight, 0x00FF0055);

        batch.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        ninePatch.dispose();
    }

    public static void main(String[] args) {
        GlfwContextBuilder.create(1280, 720, "Window")
            .icon("/icon2.png")
            .build().setApp(new NinePatchTest());
        GlfwContextManager.run();
    }

}