package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwCursorEnterCallback {

    void invoke(GlfwWindow window, boolean entered);

}
