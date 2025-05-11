package jpize.lwjgl.glfw.window;

import jpize.context.IWindow;
import jpize.lwjgl.glfw.Glfw;
import jpize.lwjgl.glfw.GlfwImage;
import jpize.lwjgl.glfw.GlfwObject;
import jpize.lwjgl.glfw.monitor.GlfwMonitor;
import jpize.util.Insetsi;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec2i;
import jpize.util.res.Resource;
import org.lwjgl.glfw.*;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwWindow extends GlfwObject implements IWindow {

    public GlfwWindow(int width, int height, String title, GlfwMonitor monitor, GlfwWindow share) {
        super(glfwCreateWindow(width, height, title, GlfwObject.getID(monitor), GlfwObject.getID(share)));
        GlfwWindowRegistry.registerContext(this);
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

    @Override
    public void makeContextCurrent() {
        glfwMakeContextCurrent(ID);
    }

    @Override
    public void show() {
        glfwShowWindow(ID);
    }

    @Override
    public void hide() {
        glfwHideWindow(ID);
    }

    @Override
    public void focus() {
        glfwFocusWindow(ID);
    }

    @Override
    public void inconify() {
        glfwIconifyWindow(ID);
    }

    @Override
    public void restore() {
        glfwRestoreWindow(ID);
    }

    @Override
    public void maximize() {
        glfwMaximizeWindow(ID);
    }

    @Override
    public void requestAttention() {
        glfwRequestWindowAttention(ID);
    }

    @Override
    public void swapBuffers() {
        glfwSwapBuffers(ID);
    }


    public GlfwMonitor getMonitor() {
        return GlfwMonitor.getMonitor(glfwGetWindowMonitor(ID));
    }

    public void setMonitor(GlfwMonitor monitor, int x, int y, int width, int height, int refreshRate) {
        glfwSetWindowMonitor(ID, GlfwObject.getID(monitor), x, y, width, height, refreshRate);
    }


    @Override
    public boolean shouldClose() {
        return glfwWindowShouldClose(ID);
    }

    @Override
    public void setShouldClose(boolean value) {
        glfwSetWindowShouldClose(ID, value);
    }


    @Override
    public String getTitle() {
        return glfwGetWindowTitle(ID);
    }

    public void setTitle(CharSequence title) {
        glfwSetWindowTitle(ID, title);
    }


    @Override
    public float getOpacity() {
        return glfwGetWindowOpacity(ID);
    }

    @Override
    public void setOpacity(double opacity) {
        glfwSetWindowOpacity(ID, (float) opacity);
    }


    @Override
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

    @Override
    public void setIcon(PixmapRGBA... pixmap) {
        setIcon(GlfwImage.makeArray(pixmap));
    }

    @Override
    public void setIcon(Resource... resource) {
        setIcon(GlfwImage.loadArray(resource));
    }

    @Override
    public void setIcon(String... filepaths) {
        setIcon(GlfwImage.loadArray(filepaths));
    }


    @Override
    public Vec2i getFramebufferSize() {
        final IntBuffer widthBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer heightBuf = MemoryUtil.memAllocInt(1);
        glfwGetFramebufferSize(ID, widthBuf, heightBuf);
        final Vec2i value = new Vec2i(widthBuf.get(), heightBuf.get());
        MemoryUtil.memFree(widthBuf);
        MemoryUtil.memFree(heightBuf);
        return value;
    }

    @Override
    public int getFramebufferWidth() {
        final IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetFramebufferSize(ID, buffer, null);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    @Override
    public int getFramebufferHeight() {
        final IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetFramebufferSize(ID, null, buffer);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }


    @Override
    public Vec2f getContentScale() {
        final FloatBuffer xBuf = MemoryUtil.memAllocFloat(1);
        final FloatBuffer yBuf = MemoryUtil.memAllocFloat(1);
        glfwGetWindowContentScale(ID, xBuf, yBuf);
        final Vec2f value = new Vec2f(xBuf.get(), yBuf.get());
        MemoryUtil.memFree(xBuf);
        MemoryUtil.memFree(yBuf);
        return value;
    }

    @Override
    public float getContentScaleX() {
        final FloatBuffer buffer = MemoryUtil.memAllocFloat(1);
        glfwGetWindowContentScale(ID, buffer, null);
        final float value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    @Override
    public float getContentScaleY() {
        final FloatBuffer buffer = MemoryUtil.memAllocFloat(1);
        glfwGetWindowContentScale(ID, null, buffer);
        final float value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }


    @Override
    public Insetsi getFrameSize() {
        final IntBuffer leftBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer topBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer rightBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer bottomBuf = MemoryUtil.memAllocInt(1);

        glfwGetWindowFrameSize(ID, leftBuf, topBuf, rightBuf, bottomBuf);
        final Insetsi value = new Insetsi(topBuf.get(), leftBuf.get(), bottomBuf.get(), rightBuf.get());

        MemoryUtil.memFree(leftBuf);
        MemoryUtil.memFree(topBuf);
        MemoryUtil.memFree(rightBuf);
        MemoryUtil.memFree(bottomBuf);
        return value;
    }


    @Override
    public void setPos(int x, int y) {
        glfwSetWindowPos(ID, x, y);
    }

    @Override
    public Vec2i getPos() {
        final IntBuffer xBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer yBuf = MemoryUtil.memAllocInt(1);
        glfwGetWindowPos(ID, xBuf, yBuf);
        final Vec2i value = new Vec2i(xBuf.get(), yBuf.get());
        MemoryUtil.memFree(xBuf);
        MemoryUtil.memFree(yBuf);
        return value;
    }

    @Override
    public int getX() {
        final IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetWindowPos(ID, buffer, null);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    @Override
    public int getY() {
        final IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetWindowPos(ID, null, buffer);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }


    @Override
    public void setSizeLimits(int minWidth, int minHeight, int maxWidth, int maxHeight) {
        glfwSetWindowSizeLimits(ID, minWidth, minHeight, maxWidth, maxHeight);
    }

    @Override
    public void setSize(int width, int height) {
        glfwSetWindowSize(ID, width, height);
    }

    @Override
    public Vec2i getSize() {
        final IntBuffer widthBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer heightBuf = MemoryUtil.memAllocInt(1);
        glfwGetWindowSize(ID, widthBuf, heightBuf);
        final Vec2i value = new Vec2i(widthBuf.get(), heightBuf.get());
        MemoryUtil.memFree(widthBuf);
        MemoryUtil.memFree(heightBuf);
        return value;
    }

    @Override
    public int getWidth() {
        final IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetWindowSize(ID, buffer, null);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    @Override
    public int getHeight() {
        final IntBuffer buffer = MemoryUtil.memAllocInt(1);
        glfwGetWindowSize(ID, null, buffer);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }


    private final Vec2i windowedPos = new Vec2i();
    private final Vec2i windowedSize = new Vec2i();

    public void setFullscreen(GlfwMonitor monitor) {
        windowedPos.set(getPos());
        windowedSize.set(getSize());
        final GLFWVidMode videoMode = monitor.getVideoMode();
        setMonitor(monitor, 0, 0, videoMode.width(), videoMode.height(), videoMode.refreshRate());
    }

    @Override
    public void setFullscreen() {
        this.setFullscreen(GlfwMonitor.getPrimaryMonitor());
    }

    @Override
    public void setWindowed() {
        setMonitor(null, windowedPos.x, windowedPos.y, windowedSize.x, windowedSize.y, 0);
    }

    @Override
    public void setFullscreen(boolean fullscreen) {
        if(fullscreen) this.setFullscreen();
        else this.setWindowed();
    }

    @Override
    public boolean isFullscreen() {
        return (this.getMonitor() != null);
    }


    @Override
    public void dispose() {
        GlfwWindowRegistry.unregisterContext(this);
        glfwDestroyWindow(ID);
    }

}
