package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.window.GlfwWindow;

public interface GlfwPreeditCallback {

    void invoke(GlfwWindow window, int preeditCount, long preeditStringPointer, int blockCount, long blockSizesPointer, int focusedBlock, int caret);

}
