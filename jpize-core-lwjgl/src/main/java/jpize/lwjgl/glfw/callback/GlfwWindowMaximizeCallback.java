package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwWindowMaximizeCallback {

    void invoke(GlfwWindow window, boolean maximized);

}
