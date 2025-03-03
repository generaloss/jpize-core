package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwWindowContentScaleCallback {

    void invoke(GlfwWindow window, float scaleX, float scaleY);

}
