package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwPreeditCandidateCallback {

    void invoke(GlfwWindow window, int candidatesCount, int selectedIndex, int pageStart, int pageSize);

}
