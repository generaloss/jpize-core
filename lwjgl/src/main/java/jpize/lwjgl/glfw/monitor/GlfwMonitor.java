package jpize.lwjgl.glfw.monitor;

import jpize.io.input.ConnectEvent;
import jpize.lwjgl.glfw.GlfwConnectEvent;
import jpize.lwjgl.glfw.GlfwObjectLong;
import jpize.lwjgl.glfw.callback.GlfwMonitorCallback;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec2i;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;

import java.awt.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwMonitor extends GlfwObjectLong {

    private GlfwMonitor(long ID) {
        super(ID);
    }


    public String getName() {
        return glfwGetMonitorName(ID);
    }


    public GlfwGammaRamp getGammaRamp() {
        return new GlfwGammaRamp(glfwGetGammaRamp(ID));
    }

    public void setGammaRamp(GlfwGammaRamp ramp) {
        glfwSetGammaRamp(ID, ramp.getGLFWGammaRamp());
    }

    public void setGamma(float gamma) {
        glfwSetGamma(ID, gamma);
    }


    public GLFWVidMode getVideoMode() {
        return glfwGetVideoMode(ID);
    }

    public GLFWVidMode.Buffer getVideoModes() {
        return glfwGetVideoModes(ID);
    }


    public Vec2f getContentScale() {
        final FloatBuffer widthBuf = MemoryUtil.memCallocFloat(1);
        final FloatBuffer heightBuf = MemoryUtil.memCallocFloat(1);
        glfwGetMonitorContentScale(ID, widthBuf, heightBuf);
        final Vec2f value = new Vec2f(widthBuf.get(), heightBuf.get());
        MemoryUtil.memFree(widthBuf);
        MemoryUtil.memFree(heightBuf);
        return value;
    }

    public float getContentScaleX() {
        final FloatBuffer buffer = MemoryUtil.memCallocFloat(1);
        glfwGetMonitorContentScale(ID, buffer, null);
        final float value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    public float getContentScaleY() {
        final FloatBuffer buffer = MemoryUtil.memCallocFloat(1);
        glfwGetMonitorContentScale(ID, null, buffer);
        final float value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }


    public Vec2i getPhysicalSizeMM() {
        final IntBuffer widthBuf = MemoryUtil.memCallocInt(1);
        final IntBuffer heightBuf = MemoryUtil.memCallocInt(1);
        glfwGetMonitorPhysicalSize(ID, widthBuf, heightBuf);
        final Vec2i value = new Vec2i(widthBuf.get(), heightBuf.get());
        MemoryUtil.memFree(widthBuf);
        MemoryUtil.memFree(heightBuf);
        return value;
    }

    public int getPhysicalWidthMM() {
        final IntBuffer buffer = MemoryUtil.memCallocInt(1);
        glfwGetMonitorPhysicalSize(ID, buffer, null);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    public int getPhysicalHeightMM() {
        final IntBuffer buffer = MemoryUtil.memCallocInt(1);
        glfwGetMonitorPhysicalSize(ID, null, buffer);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }


    public Vec2i getSize() {
        final GLFWVidMode videoMode = this.getVideoMode();
        return new Vec2i(videoMode.width(), videoMode.height());
    }

    public int getWidth() {
        return getVideoMode().width();
    }

    public int getHeight() {
        return getVideoMode().height();
    }

    public float getAspect() {
        return Vec2f.aspect(this.getWidth(), this.getHeight());
    }


    public Vec2i getPosition() {
        final IntBuffer xBuf = MemoryUtil.memCallocInt(1);
        final IntBuffer yBuf = MemoryUtil.memCallocInt(1);
        glfwGetMonitorPos(ID, xBuf, yBuf);
        final Vec2i value = new Vec2i(xBuf.get(), yBuf.get());
        MemoryUtil.memFree(xBuf);
        MemoryUtil.memFree(yBuf);
        return value;
    }

    public int getX() {
        final IntBuffer buffer = MemoryUtil.memCallocInt(1);
        glfwGetMonitorPos(ID, buffer, null);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    public int getY() {
        final IntBuffer buffer = MemoryUtil.memCallocInt(1);
        glfwGetMonitorPos(ID, null, buffer);
        final int value = buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }


    public Rectangle getWorkarea() {
        final IntBuffer xBuf = MemoryUtil.memCallocInt(1);
        final IntBuffer yBuf = MemoryUtil.memCallocInt(1);
        final IntBuffer widthBuf = MemoryUtil.memCallocInt(1);
        final IntBuffer heightBuf = MemoryUtil.memCallocInt(1);
        glfwGetMonitorWorkarea(ID, xBuf, yBuf, widthBuf, heightBuf);
        final Rectangle value = new Rectangle(xBuf.get(), yBuf.get(), widthBuf.get(), heightBuf.get());
        MemoryUtil.memFree(xBuf);
        MemoryUtil.memFree(yBuf);
        MemoryUtil.memFree(widthBuf);
        MemoryUtil.memFree(heightBuf);
        return value;
    }


    public float getVerticalDPI() {
        final int pixels = this.getHeight();
        final int mm = this.getPhysicalHeightMM();
        return (float) pixels / mm * 25.4F;
    }

    public float getHorizontalDPI() {
        final int pixels = this.getWidth();
        final int mm = this.getPhysicalWidthMM();
        return (float) pixels / mm * 25.4F;
    }

    public float getDiagonalDPI() {
        final float pixels = this.getSize().len();
        final float mm = this.getPhysicalSizeMM().len();
        return pixels / mm * 25.4F;
    }


    private static final Map<Long, GlfwMonitor> MONITORS = new HashMap<>();

    public static GlfwMonitor getMonitor(long ID) {
        if(ID == 0L)
            return null;
        if(!MONITORS.containsKey(ID))
            MONITORS.put(ID, new GlfwMonitor(ID));
        return MONITORS.get(ID);
    }

    public static GlfwMonitor getPrimaryMonitor() {
        return getMonitor(glfwGetPrimaryMonitor());
    }

    public static GlfwMonitor[] getMonitors() {
        final PointerBuffer pointerBuf = glfwGetMonitors();
        final GlfwMonitor[] monitors = new GlfwMonitor[pointerBuf.limit()];
        for(int i = 0; i < monitors.length; i++){
            final long ID = MemoryUtil.memGetLong(pointerBuf.get(i));
            monitors[i] = getMonitor(ID);
        }
        return monitors;
    }


    private static final List<GlfwMonitorCallback> MONITOR_CALLBACKS = new ArrayList<>();
    private static Callback MONITOR_CALLBACK;

    public static void setMonitorCallback(GlfwMonitorCallback callback) {
        MONITOR_CALLBACKS.add(callback);
        if(MONITOR_CALLBACK != null)
            return;

        MONITOR_CALLBACK = glfwSetMonitorCallback((ID, eventCode) -> {
            final ConnectEvent event = GlfwConnectEvent.byGlfwValue(eventCode);
            final GlfwMonitor monitor;

            if(event.isConnected()){
                if(!MONITORS.containsKey(ID))
                    MONITORS.put(ID, new GlfwMonitor(ID));
                monitor = MONITORS.get(ID);
            }else{
                monitor = MONITORS.remove(ID);
            }
            MONITOR_CALLBACKS.forEach(c -> c.invoke(monitor, event));
        });
    }

}
