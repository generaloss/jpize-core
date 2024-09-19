package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwCursorEnterCallback {

    void invoke(GlfwWindow window, boolean entered);

}
