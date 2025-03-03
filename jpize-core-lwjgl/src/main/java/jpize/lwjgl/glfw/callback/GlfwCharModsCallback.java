package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.input.GlfwMods;
import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwCharModsCallback {

    void invoke(GlfwWindow window, char codepoint, GlfwMods mods);

}
