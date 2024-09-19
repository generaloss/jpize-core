package jpize.glfw.window;

import jpize.glfw.Glfw;
import jpize.glfw.GlfwImage;
import jpize.glfw.callback.GlfwCallbacks;
import jpize.glfw.input.GlfwInput;
import jpize.glfw.monitor.GlfwMonitor;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec2i;
import jpize.util.res.Resource;
import org.lwjgl.glfw.*;
import org.lwjgl.system.MemoryUtil;

import java.awt.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwWindow {

    private final long ID;
    private final GlfwInput input;
    private final GlfwCallbacks callbacks;

    public GlfwWindow(int width, int height, String title, GlfwMonitor monitor, GlfwWindow share) {
        final long shareID = (share == null) ? 0L : share.getID();
        final long monitorID = (monitor == null) ? 0L : monitor.getID();

        this.ID = glfwCreateWindow(width, height, title, monitorID, shareID);
        if(ID == 0L)
            throw new RuntimeException("Failed to create the GLFW window.");

        this.callbacks = new GlfwCallbacks(this);
        this.input = new GlfwInput(this);
        registerContext(this);
    }

    public GlfwWindow(String title, int width, int height, GlfwMonitor monitor, GlfwWindow share) {
        this(width, height, title, monitor, share);
    }

    public GlfwWindow(int width, int height, String title, GlfwMonitor monitor) {
        this(width, height, title, monitor, null);
    }

    public GlfwWindow(String title, int width, int height, GlfwMonitor monitor) {
        this(title, width, height, monitor, null);
    }

    public GlfwWindow(int width, int height, String title) {
        this(width, height, title, null);
    }

    public GlfwWindow(String title, int width, int height) {
        this(title, width, height, null);
    }

    public long getID() {
        return ID;
    }

    public GlfwInput getInput() {
        return input;
    }

    public GlfwCallbacks getCallbacks() {
        return callbacks;
    }


    public void destroy() {
        unregisterContext(this);
        glfwDestroyWindow(ID);
    }

    public void makeContextCurrent() {
        glfwMakeContextCurrent(ID);
    }

    public void show() {
        glfwShowWindow(ID);
    }

    public void hide() {
        glfwHideWindow(ID);
    }

    public void focus() {
        glfwFocusWindow(ID);
    }

    public void inconify() {
        glfwIconifyWindow(ID);
    }

    public void restore() {
        glfwRestoreWindow(ID);
    }

    public void maximize() {
        glfwMaximizeWindow(ID);
    }

    public void requestAttention() {
        glfwRequestWindowAttention(ID);
    }

    public void swapBuffers() {
        glfwSwapBuffers(ID);
    }


    public GlfwMonitor getMonitor() {
        return GlfwMonitor.getMonitor(glfwGetWindowMonitor(ID));
    }

    public void setMonitor(GlfwMonitor monitor, int x, int y, int width, int height, int refreshRate) {
        glfwSetWindowMonitor(ID, monitor.getID(), x, y, width, height, refreshRate);
    }


    public boolean shouldClose() {
        return glfwWindowShouldClose(ID);
    }

    public void setShouldClose(boolean value) {
        glfwSetWindowShouldClose(ID, value);
    }


    //!LWJGL3.3.4 public String getTitle() {
    //!LWJGL3.3.4     return glfwGetWindowTitle(ID);
    //!LWJGL3.3.4 }

    public void setTitle(CharSequence title) {
        glfwSetWindowTitle(ID, title);
    }


    public float getOpacity() {
        return glfwGetWindowOpacity(ID);
    }

    public void setOpacity(double opacity) {
        glfwSetWindowOpacity(ID, (float) opacity);
    }


    public float getAspectRatio() {
        return Vec2i.aspect(getWidth(), getHeight());
    }

    public void setAspectRatio(int numerator, int denominator) {
        glfwSetWindowAspectRatio(ID, numerator, denominator);
    }


    public void setAttrib(GlfwAttribMutable attrib, boolean value) {
        glfwSetWindowAttrib(ID, attrib.value, (value ? Glfw.TRUE : Glfw.FALSE));
    }

    public boolean getAttrib(GlfwAttrib attrib) {
        return glfwGetWindowAttrib(ID, attrib.value) == Glfw.TRUE;
    }


    public void setIcon(GlfwImage... images) {
        glfwSetWindowIcon(ID, GlfwImage.makeBuffer(images));
    }

    public void setIcon(PixmapRGBA... pixmap) {
        setIcon(GlfwImage.makeArray(pixmap));
    }

    public void setIcon(Resource... resource) {
        setIcon(GlfwImage.loadArray(resource));
    }

    public void setIcon(String... filepaths) {
        setIcon(GlfwImage.loadArray(filepaths));
    }


    public Vec2i getFramebufferSize() {
        final IntBuffer widthBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer heightBuf = MemoryUtil.memAllocInt(1);
        glfwGetFramebufferSize(ID, widthBuf, heightBuf);
        final Vec2i value = new Vec2i(widthBuf.get(), heightBuf.get());
        MemoryUtil.memFree(widthBuf);
        MemoryUtil.memFree(heightBuf);
        return value;
    }

    public int getFramebufferWidth() {
        final IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetFramebufferSize(ID, buffer, null);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    public int getFramebufferHeight() {
        final IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetFramebufferSize(ID, null, buffer);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }


    public Vec2f getContentScale() {
        final FloatBuffer xBuf = MemoryUtil.memAllocFloat(1);
        final FloatBuffer yBuf = MemoryUtil.memAllocFloat(1);
        glfwGetWindowContentScale(ID, xBuf, yBuf);
        final Vec2f value = new Vec2f(xBuf.get(), yBuf.get());
        MemoryUtil.memFree(xBuf);
        MemoryUtil.memFree(yBuf);
        return value;
    }

    public float getContentScaleX() {
        final FloatBuffer buffer = MemoryUtil.memAllocFloat(1);
        glfwGetWindowContentScale(ID, buffer, null);
        final float value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    public float getContentScaleY() {
        final FloatBuffer buffer = MemoryUtil.memAllocFloat(1);
        glfwGetWindowContentScale(ID, null, buffer);
        final float value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }


    public Insets getFrameSize() {
        final IntBuffer leftBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer topBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer rightBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer bottomBuf = MemoryUtil.memAllocInt(1);

        glfwGetWindowFrameSize(ID, leftBuf, topBuf, rightBuf, bottomBuf);
        final Insets value = new Insets(topBuf.get(), leftBuf.get(), bottomBuf.get(), rightBuf.get());

        MemoryUtil.memFree(leftBuf);
        MemoryUtil.memFree(topBuf);
        MemoryUtil.memFree(rightBuf);
        MemoryUtil.memFree(bottomBuf);
        return value;
    }


    public void setPos(int x, int y) {
        glfwSetWindowPos(ID, x, y);
    }

    public Vec2i getPos() {
        final IntBuffer xBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer yBuf = MemoryUtil.memAllocInt(1);
        glfwGetWindowPos(ID, xBuf, yBuf);
        final Vec2i value = new Vec2i(xBuf.get(), yBuf.get());
        MemoryUtil.memFree(xBuf);
        MemoryUtil.memFree(yBuf);
        return value;
    }

    public int getX() {
        final IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetWindowPos(ID, buffer, null);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    public int getY() {
        final IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetWindowPos(ID, null, buffer);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }


    public void setSizeLimits(int minWidth, int minHeight, int maxWidth, int maxHeight) {
        glfwSetWindowSizeLimits(ID, minWidth, minHeight, maxWidth, maxHeight);
    }

    public void setSize(int width, int height) {
        glfwSetWindowSize(ID, width, height);
    }

    public Vec2i getSize() {
        final IntBuffer widthBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer heightBuf = MemoryUtil.memAllocInt(1);
        glfwGetWindowSize(ID, widthBuf, heightBuf);
        final Vec2i value = new Vec2i(widthBuf.get(), heightBuf.get());
        MemoryUtil.memFree(widthBuf);
        MemoryUtil.memFree(heightBuf);
        return value;
    }

    public int getWidth() {
        final IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetWindowSize(ID, buffer, null);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    public int getHeight() {
        final IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetWindowSize(ID, null, buffer);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }


    private final Vec2i windowedPos = new Vec2i();
    private final Vec2i windowedSize = new Vec2i();

    public void setFullscreen() {
        windowedPos.set(getPos());
        windowedSize.set(getSize());
        final GlfwMonitor monitor = GlfwMonitor.getPrimaryMonitor();
        final GLFWVidMode videoMode = monitor.getVideoMode();
        setMonitor(monitor, 0, 0, videoMode.width(), videoMode.height(), videoMode.refreshRate());
    }

    public void setWindowed() {
        setMonitor(null, windowedPos.x, windowedPos.y, windowedSize.x, windowedSize.y, 0);
    }

    public void setFullscreen(boolean fullscreen) {
        if(fullscreen) setWindowed();
        else setFullscreen();
    }

    public boolean isFullscreen() {
        return getMonitor() != null;
    }

    public void toggleFullscreen() {
        setFullscreen(!isFullscreen());
    }


    private static final Map<Long, GlfwWindow> WINDOWS = new HashMap<>();

    private static void registerContext(GlfwWindow window) {
        WINDOWS.put(window.getID(), window);
    }

    private static void unregisterContext(GlfwWindow window) {
        WINDOWS.remove(window.getID());
    }

    public static Collection<GlfwWindow> getContexts() {
        return WINDOWS.values();
    }

    public static GlfwWindow getCurrentContext() {
        final long windowID = GLFW.glfwGetCurrentContext();
        return WINDOWS.get(windowID);
    }

}
