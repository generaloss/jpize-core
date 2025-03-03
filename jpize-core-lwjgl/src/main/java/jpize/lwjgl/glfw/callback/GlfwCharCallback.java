package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwCharCallback {

    void invoke(GlfwWindow window, char codepoint);

}
