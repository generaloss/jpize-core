package jpize.lwjgl.glfw.window;

import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_ANY_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_COMPAT_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;

public enum GlfwOpenGLProfile {

    ANY    (GLFW_OPENGL_ANY_PROFILE   ),
    COMPAT (GLFW_OPENGL_COMPAT_PROFILE),
    CORE   (GLFW_OPENGL_CORE_PROFILE  );

    public final int value;

    GlfwOpenGLProfile(int value) {
        this.value = value;
    }

}
