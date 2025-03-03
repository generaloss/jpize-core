package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwWindowPosCallback {

    void invoke(GlfwWindow window, int x, int y);

}
