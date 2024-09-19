package jpize.glfw.callback;

import jpize.glfw.GlfwError;

public interface GlfwErrorCallback {

    void invoke(GlfwError error, String message);

}
