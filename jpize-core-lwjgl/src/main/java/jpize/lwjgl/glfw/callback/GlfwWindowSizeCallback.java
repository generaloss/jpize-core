package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwWindowSizeCallback {

    void invoke(GlfwWindow window, int width, int height);

}
