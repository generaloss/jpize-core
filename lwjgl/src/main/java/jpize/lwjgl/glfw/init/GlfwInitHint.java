package jpize.lwjgl.glfw.init;

import static org.lwjgl.glfw.GLFW.*;

public enum GlfwInitHint {

    PLATFORM               (GLFW_PLATFORM              ),
    JOYSTICK_HAT_BUTTONS   (GLFW_JOYSTICK_HAT_BUTTONS  ),
    ANGLE_PLATFORM_TYPE    (GLFW_ANGLE_PLATFORM_TYPE   ),
    COCOA_CHDIR_RESOURCES  (GLFW_COCOA_CHDIR_RESOURCES ),
    COCOA_MENUBAR          (GLFW_COCOA_MENUBAR         ),
    WAYLAND_LIBDECOR       (GLFW_WAYLAND_LIBDECOR      ),
    X11_XCB_VULKAN_SURFACE (GLFW_X11_XCB_VULKAN_SURFACE);

    public final int value;

    GlfwInitHint(int value) {
        this.value = value;
    }

}
