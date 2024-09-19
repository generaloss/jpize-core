package jpize.glfw.callback;

import jpize.glfw.input.GlfwAction;
import jpize.glfw.input.GlfwMods;
import jpize.glfw.input.MouseBtn;
import jpize.glfw.window.GlfwWindow;

public interface GlfwMouseButtonCallback {

    void invoke(GlfwWindow window, MouseBtn button, GlfwAction action, GlfwMods mods);

}
