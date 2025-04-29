package jpize.lwjgl.glfw;

import jpize.context.input.ConnectEvent;

import static org.lwjgl.glfw.GLFW.GLFW_CONNECTED;
import static org.lwjgl.glfw.GLFW.GLFW_DISCONNECTED;

public class GlfwConnectEvent {

    public static ConnectEvent byGlfwValue(int value) {
        return switch(value) {
            case GLFW_CONNECTED -> ConnectEvent.CONNECTED;
            case GLFW_DISCONNECTED -> ConnectEvent.DISCONNECTED;
            default -> throw new IllegalArgumentException("Unknown GlfwConnectionEvent value: " + value);
        };
    }

}
