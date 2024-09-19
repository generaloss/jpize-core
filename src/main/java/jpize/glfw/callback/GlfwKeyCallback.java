package jpize.glfw.callback;

import jpize.glfw.input.GlfwAction;
import jpize.glfw.input.GlfwMods;
import jpize.glfw.input.Key;
import jpize.glfw.window.GlfwWindow;

public interface GlfwKeyCallback {

    void invoke(GlfwWindow window, Key key, int scancode, GlfwAction action, GlfwMods mods);

}
