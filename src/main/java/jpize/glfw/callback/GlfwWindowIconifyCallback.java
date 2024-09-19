package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwWindowIconifyCallback {

    void invoke(GlfwWindow window, boolean iconified);

}
