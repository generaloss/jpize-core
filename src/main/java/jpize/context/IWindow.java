package jpize.context;

import jpize.util.Disposable;
import jpize.util.Insetsi;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec2i;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.res.Resource;

public interface IWindow extends Disposable {

    long getID();


    void makeContextCurrent();

    void show();

    void hide();

    void focus();

    void inconify();

    void restore();

    void maximize();

    void requestAttention();

    void swapBuffers();


    boolean shouldClose();

    void setShouldClose(boolean value);


    String getTitle();


    float getOpacity();

    void setOpacity(double opacity);


    void setIcon(PixmapRGBA... pixmap);

    void setIcon(Resource... resource);

    void setIcon(String... filepaths);


    float getAspectRatio();


    Vec2i getFramebufferSize();

    int getFramebufferWidth();

    int getFramebufferHeight();


    Vec2f getContentScale();

    float getContentScaleX();

    float getContentScaleY();


    Insetsi getFrameSize();


    void setPos(int x, int y);

    Vec2i getPos();

    int getX();

    int getY();


    void setSizeLimits(int minWidth, int minHeight, int maxWidth, int maxHeight);

    void setSize(int width, int height);

    Vec2i getSize();

    int getWidth();

    int getHeight();


    void setFullscreen();

    void setWindowed();

    void setFullscreen(boolean fullscreen);

    boolean isFullscreen();

    default void toggleFullscreen() {
        this.setFullscreen(!this.isFullscreen());
    }

}
