package jpize.glfw.window;

import static org.lwjgl.glfw.GLFW.*;

public enum GlfwWindowHint {

    RESIZABLE                (GLFW_RESIZABLE               ),
    VISIBLE                  (GLFW_VISIBLE                 ),
    DECORATED                (GLFW_DECORATED               ),
    FOCUSED                  (GLFW_FOCUSED                 ),
    AUTO_ICONIFY             (GLFW_AUTO_ICONIFY            ),
    FLOATING                 (GLFW_FLOATING                ),
    MAXIMIZED                (GLFW_MAXIMIZED               ),
    CENTER_CURSOR            (GLFW_CENTER_CURSOR           ),
    TRANSPARENT_FRAMEBUFFER  (GLFW_TRANSPARENT_FRAMEBUFFER ),
    FOCUS_ON_SHOW            (GLFW_FOCUS_ON_SHOW           ),
    SCALE_TO_MONITOR         (GLFW_SCALE_TO_MONITOR        ),
    //!LWJGL3.3.4 SCALE_FRAMEBUFFER        (GLFW_SCALE_FRAMEBUFFER       ),
    MOUSE_PASSTHROUGH        (GLFW_MOUSE_PASSTHROUGH       ),
    POSITION_X               (GLFW_POSITION_X              ),
    POSITION_Y               (GLFW_POSITION_Y              ),
    RED_BITS                 (GLFW_RED_BITS                ),
    GREEN_BITS               (GLFW_GREEN_BITS              ),
    BLUE_BITS                (GLFW_BLUE_BITS               ),
    ALPHA_BITS               (GLFW_ALPHA_BITS              ),
    DEPTH_BITS               (GLFW_DEPTH_BITS              ),
    STENCIL_BITS             (GLFW_STENCIL_BITS            ),
    ACCUM_RED_BITS           (GLFW_ACCUM_RED_BITS          ),
    ACCUM_GREEN_BITS         (GLFW_ACCUM_GREEN_BITS        ),
    ACCUM_BLUE_BITS          (GLFW_ACCUM_BLUE_BITS         ),
    ACCUM_ALPHA_BITS         (GLFW_ACCUM_ALPHA_BITS        ),
    AUX_BUFFERS              (GLFW_AUX_BUFFERS             ),
    SAMPLES                  (GLFW_SAMPLES                 ),
    REFRESH_RATE             (GLFW_REFRESH_RATE            ),
    STEREO                   (GLFW_STEREO                  ),
    SRGB_CAPABLE             (GLFW_SRGB_CAPABLE            ),
    DOUBLEBUFFER             (GLFW_DOUBLEBUFFER            ),
    CLIENT_API               (GLFW_CLIENT_API              ),
    CONTEXT_CREATION_API     (GLFW_CONTEXT_CREATION_API    ),
    CONTEXT_VERSION_MAJOR    (GLFW_CONTEXT_VERSION_MAJOR   ),
    CONTEXT_VERSION_MINOR    (GLFW_CONTEXT_VERSION_MINOR   ),
    CONTEXT_ROBUSTNESS       (GLFW_CONTEXT_ROBUSTNESS      ),
    CONTEXT_RELEASE_BEHAVIOR (GLFW_CONTEXT_RELEASE_BEHAVIOR),
    OPENGL_FORWARD_COMPAT    (GLFW_OPENGL_FORWARD_COMPAT   ),
    CONTEXT_DEBUG            (GLFW_CONTEXT_DEBUG           ),
    OPENGL_PROFILE           (GLFW_OPENGL_PROFILE          ),
    WIN32_KEYBOARD_MENU      (GLFW_WIN32_KEYBOARD_MENU     ),
    //!LWJGL3.3.4 WIN32_SHOWDEFAULT        (GLFW_WIN32_SHOWDEFAULT       ),
    COCOA_FRAME_NAME         (GLFW_COCOA_FRAME_NAME        ),
    COCOA_GRAPHICS_SWITCHING (GLFW_COCOA_GRAPHICS_SWITCHING),
    WAYLAND_APP_ID           (GLFW_WAYLAND_APP_ID          ),
    X11_CLASS_NAME           (GLFW_X11_CLASS_NAME          ),
    X11_INSTANCE_NAME        (GLFW_X11_INSTANCE_NAME       );

    public final int value;

    GlfwWindowHint(int value) {
        this.value = value;
    }

}
