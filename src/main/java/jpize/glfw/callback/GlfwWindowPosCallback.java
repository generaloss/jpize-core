package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwWindowPosCallback {

    void invoke(GlfwWindow window, int x, int y);

}
