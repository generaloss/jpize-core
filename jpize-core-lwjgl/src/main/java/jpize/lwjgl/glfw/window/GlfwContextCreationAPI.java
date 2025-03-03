package jpize.lwjgl.glfw.window;

import static org.lwjgl.glfw.GLFW.GLFW_NATIVE_CONTEXT_API;
import static org.lwjgl.glfw.GLFW.GLFW_EGL_CONTEXT_API;
import static org.lwjgl.glfw.GLFW.GLFW_OSMESA_CONTEXT_API;

public enum GlfwContextCreationAPI {

    NATIVE (GLFW_NATIVE_CONTEXT_API),
    EGL    (GLFW_EGL_CONTEXT_API   ),
    OSMESA (GLFW_OSMESA_CONTEXT_API);

    public final int value;

    GlfwContextCreationAPI(int value) {
        this.value = value;
    }

}
