package jpize.glfw.callback;

import jpize.glfw.window.GlfwWindow;

public interface GlfwPreeditCallback {

    void invoke(GlfwWindow window, int preeditCount, long preeditStringPointer, int blockCount, long blockSizesPointer, int focusedBlock, int caret);

}
