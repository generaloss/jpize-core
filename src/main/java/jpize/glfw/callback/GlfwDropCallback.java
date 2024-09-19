package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwDropCallback {

    void invoke(GlfwWindow window, String[] files);

}
