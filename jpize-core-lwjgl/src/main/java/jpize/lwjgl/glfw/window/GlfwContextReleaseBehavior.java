package jpize.lwjgl.glfw.window;

import static org.lwjgl.glfw.GLFW.GLFW_ANY_RELEASE_BEHAVIOR;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE_BEHAVIOR_FLUSH;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE_BEHAVIOR_NONE;

public enum GlfwContextReleaseBehavior {

    ANY   (GLFW_ANY_RELEASE_BEHAVIOR  ),
    FLUSH (GLFW_RELEASE_BEHAVIOR_FLUSH),
    NONE  (GLFW_RELEASE_BEHAVIOR_NONE );

    public final int value;

    GlfwContextReleaseBehavior(int value) {
        this.value = value;
    }

}
