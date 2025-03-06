package jpize.lwjgl.glfw.window;

import static org.lwjgl.glfw.GLFW.GLFW_NO_ROBUSTNESS;
import static org.lwjgl.glfw.GLFW.GLFW_NO_RESET_NOTIFICATION;
import static org.lwjgl.glfw.GLFW.GLFW_LOSE_CONTEXT_ON_RESET;

public enum GlfwContextRobustness {

    NO_ROBUSTNESS         (GLFW_NO_ROBUSTNESS        ),
    NO_RESET_NOTIFICATION (GLFW_NO_RESET_NOTIFICATION),
    LOSE_CONTEXT_ON_RESET (GLFW_LOSE_CONTEXT_ON_RESET);

    public final int value;

    GlfwContextRobustness(int value) {
        this.value = value;
    }

}
