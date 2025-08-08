package jpize.android.context;

import android.app.Activity;
import android.view.Window;
import jpize.context.IWindow;
import jpize.util.Insetsi;
import spatialmath.vector.Vec2f;
import spatialmath.vector.Vec2i;
import jpize.util.pixmap.PixmapRGBA;
import resourceflow.resource.Resource;

public class AndroidWindow implements IWindow {

    private final Activity activity;
    private final Window window;

    public AndroidWindow(Activity activity) {
        this.activity = activity;
        this.window = activity.getWindow();
    }

    @Override
    public long getID() {
        return 1L;
    }

    @Override
    public void makeContextCurrent() { }

    @Override
    public void show() { }

    @Override
    public void hide() { }

    @Override
    public void focus() { }

    @Override
    public void inconify() { }

    @Override
    public void restore() { }

    @Override
    public void maximize() { }

    @Override
    public void requestAttention() { }

    @Override
    public void swapBuffers() { }

    @Override
    public boolean shouldClose() {
        return activity.isFinishing();
    }

    @Override
    public void setShouldClose(boolean value) {
        if(value) {
            activity.finishActivity(0);
        }
    }

    @Override
    public String getTitle() {
        return activity.getApplicationInfo()
            .loadLabel(activity.getPackageManager())
            .toString();
    }

    @Override
    public float getOpacity() {
        return 1F;
    }

    @Override
    public void setOpacity(double opacity) { }

    @Override
    public void setIcon(PixmapRGBA... pixmap) { }

    @Override
    public void setIcon(Resource... resource) { }

    @Override
    public void setIcon(String... filepaths) { }

    @Override
    public float getAspectRatio() {
        return ((float) this.getWidth() / this.getHeight());
    }

    @Override
    public Vec2i getFramebufferSize() {
        return new Vec2i(this.getWidth(), this.getHeight());
    }

    @Override
    public int getFramebufferWidth() {
        return this.getWidth();
    }

    @Override
    public int getFramebufferHeight() {
        return this.getHeight();
    }

    @Override
    public Vec2f getContentScale() {
        return new Vec2f(1F);
    }

    @Override
    public float getContentScaleX() {
        return 1F;
    }

    @Override
    public float getContentScaleY() {
        return 1F;
    }

    @Override
    public Insetsi getFrameSize() {
        return new Insetsi(0, 0, 0, 0);
    }

    @Override
    public void setPos(int x, int y) { }

    @Override
    public Vec2i getPos() {
        return new Vec2i();
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setSizeLimits(int minWidth, int minHeight, int maxWidth, int maxHeight) { }

    @Override
    public void setSize(int width, int height) { }

    @Override
    public Vec2i getSize() {
        return new Vec2i(this.getWidth(), this.getHeight());
    }

    @Override
    public int getWidth() {
        return window.getDecorView().getWidth();
    }

    @Override
    public int getHeight() {
        return window.getDecorView().getHeight();
    }

    @Override
    public void setFullscreen() { }

    @Override
    public void setWindowed() { }

    @Override
    public void setFullscreen(boolean fullscreen) { }

    @Override
    public boolean isFullscreen() {
        return false;
    }

    @Override
    public void toggleFullscreen() { }

    @Override
    public void dispose() { }

}
