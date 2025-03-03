package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwCursorPosCallback {

    void invoke(GlfwWindow window, float x, float y);

}
