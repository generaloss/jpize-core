package jpize.lwjgl.glfw.callback;

import jpize.io.input.Action;
import jpize.lwjgl.glfw.input.GlfwMods;
import jpize.lwjgl.glfw.input.GlfwKey;
import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwKeyCallback {

    void invoke(GlfwWindow window, GlfwKey key, int scancode, Action action, GlfwMods mods);

}
