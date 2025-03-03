package jpize.lwjgl.glfw;

import static org.lwjgl.glfw.GLFW.GLFW_CONNECTED;
import static org.lwjgl.glfw.GLFW.GLFW_DISCONNECTED;

public enum GlfwEvent {

    CONNECTED    (GLFW_CONNECTED   ), // 26214_5
    DISCONNECTED (GLFW_DISCONNECTED); // 26214_6

    public final int value;

    GlfwEvent(int value) {
        this.value = value;
    }

    public boolean isConnected() {
        return this == CONNECTED;
    }


    public static GlfwEvent byValue(int value) {
        return values()[value - CONNECTED.value];
    }

}
