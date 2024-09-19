package jpize.glfw.window;

import static org.lwjgl.glfw.GLFW.*;

public enum GlfwAttribMutable {

    DECORATED         (GLFW_DECORATED        ),
    RESIZABLE         (GLFW_RESIZABLE        ),
    FLOATING          (GLFW_FLOATING         ),
    AUTO_ICONIFY      (GLFW_AUTO_ICONIFY     ),
    FOCUS_ON_SHOW     (GLFW_FOCUS_ON_SHOW    ),
    MOUSE_PASSTHROUGH (GLFW_MOUSE_PASSTHROUGH);

    public final int value;

    GlfwAttribMutable(int value) {
        this.value = value;
    }

}
