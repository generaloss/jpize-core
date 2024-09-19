package jpize.glfw.callback;

import jpize.glfw.input.GlfwMods;
import jpize.glfw.window.GlfwWindow;

public interface GlfwCharModsCallback {

    void invoke(GlfwWindow window, char codepoint, GlfwMods mods);

}
