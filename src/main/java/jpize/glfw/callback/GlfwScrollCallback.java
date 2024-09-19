package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwScrollCallback {

    void invoke(GlfwWindow window, float offsetX, float offsetY);

}
