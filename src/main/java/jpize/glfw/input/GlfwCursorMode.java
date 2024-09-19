package jpize.glfw.input;

import static org.lwjgl.glfw.GLFW.*;

public enum GlfwCursorMode {

    NORMAL   (GLFW_CURSOR_NORMAL  ), // 21299_3 // Makes the cursor visible and behaving normally.
    HIDDEN   (GLFW_CURSOR_HIDDEN  ), // 21299_4 // Makes the cursor invisible when it is over the content area of the window but does not restrict the cursor from leaving.
    DISABLED (GLFW_CURSOR_DISABLED), // 21299_5 // Hides and grabs the cursor, providing virtual and unlimited cursor movement. This is useful for implementing for example 3D camera controls.
    CAPTURED (GLFW_CURSOR_CAPTURED); // 21299_6 // Makes the cursor visible and confines it to the content area of the window.

    public final int value;

    GlfwCursorMode(int value) {
        this.value = value;
    }

    public static GlfwCursorMode valueOf(int value) {
        return values()[value - NORMAL.value];
    }

}
