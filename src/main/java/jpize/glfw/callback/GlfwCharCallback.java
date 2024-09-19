package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwCharCallback {

    void invoke(GlfwWindow window, char codepoint);

}
