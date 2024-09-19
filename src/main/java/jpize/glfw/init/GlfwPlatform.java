package jpize.glfw.init;

import static org.lwjgl.glfw.GLFW.*;

public enum GlfwPlatform {

    ANY     (GLFW_ANY_PLATFORM    ), // 3932_16
    WIN32   (GLFW_PLATFORM_WIN32  ), // 3932_17
    COCOA   (GLFW_PLATFORM_COCOA  ), // 3932_18
    WAYLAND (GLFW_PLATFORM_WAYLAND), // 3932_19
    X11     (GLFW_PLATFORM_X11    ), // 3932_20
    NULL    (GLFW_PLATFORM_NULL   ); // 3932_21

    public final int value;

    GlfwPlatform (int value) {
        this.value = value;
    }

    public static GlfwPlatform byValue(int value) {
        final int index = (value - ANY.value);
        if(index > -1 && index < values().length)
            return values()[index];
        return NULL;
    }

}
