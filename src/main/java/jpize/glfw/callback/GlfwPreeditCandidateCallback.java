package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwPreeditCandidateCallback {

    void invoke(GlfwWindow window, int candidatesCount, int selectedIndex, int pageStart, int pageSize);

}
