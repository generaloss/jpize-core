package jpize.glfw.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_REPEAT;

public enum GlfwAction {

    RELEASE (GLFW_RELEASE), // 0
    PRESS   (GLFW_PRESS  ), // 1
    REPEAT  (GLFW_REPEAT ); // 2

    public final int value;

    GlfwAction(int value) {
        this.value = value;
    }


    public boolean isDown() {
        return (this == PRESS);
    }

    public boolean isPressed() {
        return (this != RELEASE);
    }

    public boolean isRelease() {
        return (this == RELEASE);
    }


    public static GlfwAction byValue(int value) {
        return values()[value];
    }

}
