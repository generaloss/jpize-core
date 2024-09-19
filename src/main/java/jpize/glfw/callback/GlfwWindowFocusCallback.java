package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwWindowFocusCallback {

    void invoke(GlfwWindow window, boolean focused);

}
