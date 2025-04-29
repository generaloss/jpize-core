package jpize.lwjgl.glfw.init;

import static org.lwjgl.glfw.GLFW.*;

public enum GlfwAnglePlatformType {

    NONE     (GLFW_ANGLE_PLATFORM_TYPE_NONE     ), // 2252_81
    OPENGL   (GLFW_ANGLE_PLATFORM_TYPE_OPENGL   ), // 2252_82
    OPENGLES (GLFW_ANGLE_PLATFORM_TYPE_OPENGLES ), // 2252_83
    D3D9     (GLFW_ANGLE_PLATFORM_TYPE_D3D9     ), // 2252_84
    D3D11    (GLFW_ANGLE_PLATFORM_TYPE_D3D11    ), // 2252_85
    VULKAN   (GLFW_ANGLE_PLATFORM_TYPE_VULKAN   ), // 2252_87
    METAL    (GLFW_ANGLE_PLATFORM_TYPE_METAL    ); // 2252_88

    public final int value;

    GlfwAnglePlatformType(int value) {
        this.value = value;
    }

}
