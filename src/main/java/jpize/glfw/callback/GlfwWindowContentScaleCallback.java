package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwWindowContentScaleCallback {

    void invoke(GlfwWindow window, float scaleX, float scaleY);

}
