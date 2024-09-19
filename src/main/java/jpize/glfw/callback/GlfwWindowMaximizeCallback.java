package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwWindowMaximizeCallback {

    void invoke(GlfwWindow window, boolean maximized);

}
