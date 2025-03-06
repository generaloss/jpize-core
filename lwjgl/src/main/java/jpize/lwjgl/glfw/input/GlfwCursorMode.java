package jpize.lwjgl.glfw.input;

import jpize.io.input.CursorMode;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwCursorMode {

    private static final int[] CURSOR_GLFW_MODES = {
        GLFW_CURSOR_NORMAL  , // 21299_3
        GLFW_CURSOR_HIDDEN  , // 21299_4
        GLFW_CURSOR_DISABLED, // 21299_5
        GLFW_CURSOR_CAPTURED, // 21299_6
    };

    public static int getGlfwValue(CursorMode mode) {
        return CURSOR_GLFW_MODES[mode.ordinal()];
    }

    public static CursorMode byGlfwValue(int value) {
        return switch(value) {
            case GLFW_CURSOR_NORMAL   -> CursorMode.NORMAL;
            case GLFW_CURSOR_HIDDEN   -> CursorMode.HIDDEN;
            case GLFW_CURSOR_DISABLED -> CursorMode.DISABLED;
            case GLFW_CURSOR_CAPTURED -> CursorMode.CAPTURED;
            default -> null;
        };
    }

}
