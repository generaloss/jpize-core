package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwWindowFocusCallback {

    void invoke(GlfwWindow window, boolean focused);

}
