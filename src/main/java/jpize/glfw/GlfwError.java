package jpize.glfw;

import org.lwjgl.glfw.GLFW;

public enum GlfwError {

    NO_ERROR              (GLFW.GLFW_NO_ERROR              ), // 0
    GLFW_NOT_INITIALIZED  (GLFW.GLFW_NOT_INITIALIZED       ), // 655_37
    NO_CURRENT_CONTEXT    (GLFW.GLFW_NO_CURRENT_CONTEXT    ), // 655_38
    INVALID_ENUM          (GLFW.GLFW_INVALID_ENUM          ), // 655_39
    INVALID_VALUE         (GLFW.GLFW_INVALID_VALUE         ), // 655_40
    OUT_OF_MEMORY         (GLFW.GLFW_OUT_OF_MEMORY         ), // 655_41
    API_UNAVAILABLE       (GLFW.GLFW_API_UNAVAILABLE       ), // 655_42
    VERSION_UNAVAILABLE   (GLFW.GLFW_VERSION_UNAVAILABLE   ), // 655_43
    PLATFORM_ERROR        (GLFW.GLFW_PLATFORM_ERROR        ), // 655_44
    FORMAT_UNAVAILABLE    (GLFW.GLFW_FORMAT_UNAVAILABLE    ), // 655_45
    NO_WINDOW_CONTEXT     (GLFW.GLFW_NO_WINDOW_CONTEXT     ), // 655_46
    CURSOR_UNAVAILABLE    (GLFW.GLFW_CURSOR_UNAVAILABLE    ), // 655_47
    FEATURE_UNAVAILABLE   (GLFW.GLFW_FEATURE_UNAVAILABLE   ), // 655_48
    FEATURE_UNIMPLEMENTED (GLFW.GLFW_FEATURE_UNIMPLEMENTED ), // 655_49
    PLATFORM_UNAVAILABLE  (GLFW.GLFW_PLATFORM_UNAVAILABLE  ); // 655_50

    public final int value;

    GlfwError(int value) {
        this.value = value;
    }

    public static GlfwError byValue(int value) {
        if(value == 0)
            return NO_ERROR;

        final int index = (value - GLFW_NOT_INITIALIZED.value + 1);
        if(index > 0 && index < values().length)
            return values()[index];

        return NO_ERROR;
    }

}
