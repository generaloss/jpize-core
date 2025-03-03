package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwWindowIconifyCallback {

    void invoke(GlfwWindow window, boolean iconified);

}
