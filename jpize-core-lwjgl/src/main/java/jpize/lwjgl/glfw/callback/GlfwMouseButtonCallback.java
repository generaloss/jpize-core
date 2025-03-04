package jpize.lwjgl.glfw.callback;

import jpize.io.input.Action;
import jpize.lwjgl.glfw.input.GlfwMods;
import jpize.lwjgl.glfw.input.GlfwMouseBtn;
import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwMouseButtonCallback {

    void invoke(GlfwWindow window, GlfwMouseBtn button, Action action, GlfwMods mods);

}
