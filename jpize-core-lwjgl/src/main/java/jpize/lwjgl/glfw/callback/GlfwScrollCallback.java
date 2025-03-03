package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwScrollCallback {

    void invoke(GlfwWindow window, float offsetX, float offsetY);

}
