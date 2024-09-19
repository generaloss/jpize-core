package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwFramebufferSizeCallback {

    void invoke(GlfwWindow window, int width, int height);

}
