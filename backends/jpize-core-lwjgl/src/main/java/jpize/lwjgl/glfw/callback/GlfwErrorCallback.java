package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.GlfwError;

public interface GlfwErrorCallback {

    void invoke(GlfwError error, String message);

}
