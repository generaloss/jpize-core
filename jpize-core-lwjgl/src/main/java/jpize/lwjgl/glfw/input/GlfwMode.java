package jpize.lwjgl.glfw.input;

import static org.lwjgl.glfw.GLFW.*;

public enum GlfwMode {

    CURSOR               (GLFW_CURSOR              ),
    STICKY_KEYS          (GLFW_STICKY_KEYS         ),
    STICKY_MOUSE_BUTTONS (GLFW_STICKY_MOUSE_BUTTONS),
    LOCK_KEY_MODS        (GLFW_LOCK_KEY_MODS       ),
    RAW_MOUSE_MOTION     (GLFW_RAW_MOUSE_MOTION    );

    public final int value;

    GlfwMode(int value) {
        this.value = value;
    }

}
