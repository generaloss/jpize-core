package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.input.GlfwAction;
import jpize.lwjgl.glfw.input.GlfwMods;
import jpize.lwjgl.glfw.input.Key;
import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwKeyCallback {

    void invoke(GlfwWindow window, Key key, int scancode, GlfwAction action, GlfwMods mods);

}
