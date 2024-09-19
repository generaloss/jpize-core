package jpize.glfw.window;

import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_API;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_ES_API;
import static org.lwjgl.glfw.GLFW.GLFW_NO_API;

public enum GlfwClientAPI {

    NO_API        (GLFW_NO_API        ), // 0;
    OPENGL_API    (GLFW_OPENGL_API    ), // 1966_09;
    OPENGL_ES_API (GLFW_OPENGL_ES_API ); // 1966_10;

    public final int value;

    GlfwClientAPI(int value) {
        this.value = value;
    }

}
