package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwWindowSizeCallback {

    void invoke(GlfwWindow window, int width, int height);

}
