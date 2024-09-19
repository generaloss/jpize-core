package jpize.glfw.callback;

import jpize.glfw.monitor.GlfwMonitor;
import jpize.glfw.GlfwEvent;

public interface GlfwMonitorCallback {

    void invoke(GlfwMonitor monitor, GlfwEvent event);

}
