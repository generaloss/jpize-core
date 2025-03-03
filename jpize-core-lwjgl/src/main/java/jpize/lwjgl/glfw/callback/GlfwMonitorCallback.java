package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.monitor.GlfwMonitor;
import jpize.lwjgl.glfw.GlfwEvent;

public interface GlfwMonitorCallback {

    void invoke(GlfwMonitor monitor, GlfwEvent event);

}
