package jpize.glfw.cursor;

import static org.lwjgl.glfw.GLFW.*;

public enum GlfwCursorShape {

    ARROW         (GLFW_ARROW_CURSOR        ), // 2211_85
    IBEAM         (GLFW_IBEAM_CURSOR        ), // 2211_86
    CROSSHAIR     (GLFW_CROSSHAIR_CURSOR    ), // 2211_87
    POINTING_HAND (GLFW_POINTING_HAND_CURSOR), // 2211_88
    RESIZE_EW     (GLFW_RESIZE_EW_CURSOR    ), // 2211_89
    RESIZE_NS     (GLFW_RESIZE_NS_CURSOR    ), // 2211_90
    RESIZE_NWSE   (GLFW_RESIZE_NWSE_CURSOR  ), // 2211_91 // Maybe available in Wayland / X11.
    RESIZE_NESW   (GLFW_RESIZE_NESW_CURSOR  ), // 2211_92 // Maybe available in Wayland / X11.
    RESIZE_ALL    (GLFW_RESIZE_ALL_CURSOR   ), // 2211_93
    NOT_ALLOWED   (GLFW_NOT_ALLOWED_CURSOR  ); // 2211_94 // Maybe available in Wayland / X11.

    public final int value;

    GlfwCursorShape(int value) {
        this.value = value;
    }

}
