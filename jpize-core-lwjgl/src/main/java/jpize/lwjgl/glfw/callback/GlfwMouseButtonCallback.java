package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.input.GlfwAction;
import jpize.lwjgl.glfw.input.GlfwMods;
import jpize.lwjgl.glfw.input.MouseBtn;
import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwMouseButtonCallback {

    void invoke(GlfwWindow window, MouseBtn button, GlfwAction action, GlfwMods mods);

}
