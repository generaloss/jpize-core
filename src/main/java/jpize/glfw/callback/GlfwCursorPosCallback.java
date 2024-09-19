package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwCursorPosCallback {

    void invoke(GlfwWindow window, float x, float y);

}
