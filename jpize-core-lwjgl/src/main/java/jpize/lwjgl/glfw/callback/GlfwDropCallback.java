package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwDropCallback {

    void invoke(GlfwWindow window, String[] files);

}
