package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwFramebufferSizeCallback {

    void invoke(GlfwWindow window, int width, int height);

}
