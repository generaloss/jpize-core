package jpize.lwjgl.glfw.init;

import static org.lwjgl.glfw.GLFW.GLFW_WAYLAND_PREFER_LIBDECOR;
import static org.lwjgl.glfw.GLFW.GLFW_WAYLAND_DISABLE_LIBDECOR;

public enum GlfwWaylandLibdecor {

    PREFER_LIBDECOR  (GLFW_WAYLAND_PREFER_LIBDECOR ),
    DISABLE_LIBDECOR (GLFW_WAYLAND_DISABLE_LIBDECOR);

    public final int value;

    GlfwWaylandLibdecor(int value) {
        this.value = value;
    }

}
