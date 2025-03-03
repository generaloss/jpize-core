package jpize.lwjgl.glfw.window;

import static org.lwjgl.glfw.GLFW.*;

public enum GlfwAttrib {

    FOCUSED                  (GLFW_FOCUSED                 ),  // 1310_xx // indicates whether the specified window has input focus. See Window input focus for details.
    ICONIFIED                (GLFW_ICONIFIED               ),  // 1310_xx // indicates whether the specified window is iconified. See Window iconification for details.
    MAXIMIZED                (GLFW_MAXIMIZED               ),  // 1310_80 // indicates whether the specified window is maximized. See Window maximization for details.
    HOVERED                  (GLFW_HOVERED                 ),  // 1310_xx // indicates whether the cursor is currently directly over the content area of the window, with no other windows between. See Cursor enter/leave events for details.
    VISIBLE                  (GLFW_VISIBLE                 ),  // 1310_xx // indicates whether the specified window is visible. See Window visibility for details.
    RESIZABLE                (GLFW_RESIZABLE               ),  // 1310_xx // indicates whether the specified window is resizable by the user. This can be set before creation with the GLFW_RESIZABLE window hint or after with glfwSetWindowAttrib.
    DECORATED                (GLFW_DECORATED               ),  // 1310_77 // indicates whether the specified window has decorations such as a border, a close widget, etc. This can be set before creation with the GLFW_DECORATED window hint or after with glfwSetWindowAttrib.
    AUTO_ICONIFY             (GLFW_AUTO_ICONIFY            ),  // 1310_78 // indicates whether the specified full screen window is iconified on focus loss, a close widget, etc. This can be set before creation with the GLFW_AUTO_ICONIFY window hint or after with glfwSetWindowAttrib.
    FLOATING                 (GLFW_FLOATING                ),  // 1310_79 // indicates whether the specified window is floating, also called topmost or always-on-top. This can be set before creation with the GLFW_FLOATING window hint or after with glfwSetWindowAttrib.
    TRANSPARENT_FRAMEBUFFER  (GLFW_TRANSPARENT_FRAMEBUFFER ),  // 1310_xx // indicates whether the specified window has a transparent framebuffer, i.e. the window contents is composited with the background using the window framebuffer alpha channel. See Window transparency for details.
    FOCUS_ON_SHOW            (GLFW_FOCUS_ON_SHOW           ),  // 1310_xx // specifies whether the window will be given input focus when glfwShowWindow is called. This can be set before creation with the GLFW_FOCUS_ON_SHOW window hint or after with glfwSetWindowAttrib.
    MOUSE_PASSTHROUGH        (GLFW_MOUSE_PASSTHROUGH       ),  // 1310_xx // specifies whether the window is transparent to mouse input, letting any mouse events pass through to whatever window is behind it. This can be set before creation with the GLFW_MOUSE_PASSTHROUGH window hint or after with glfwSetWindowAttrib. This is only supported for undecorated windows. Decorated windows with this enabled will behave differently between platforms.Context related attributes
    CLIENT_API               (GLFW_CLIENT_API              ),  // 1310_xx // indicates the client API provided by the window's context; either GLFW_OPENGL_API, GLFW_OPENGL_ES_API or GLFW_NO_API.
    CONTEXT_CREATION_API     (GLFW_CONTEXT_CREATION_API    ),  // 1310_xx // indicates the context creation API used to create the window's context; either GLFW_NATIVE_CONTEXT_API, GLFW_EGL_CONTEXT_API or GLFW_OSMESA_CONTEXT_API.
    CONTEXT_VERSION_MAJOR    (GLFW_CONTEXT_VERSION_MAJOR   ),  // 1310_xx // indicate the client API version of the window's context.
    CONTEXT_VERSION_MINOR    (GLFW_CONTEXT_VERSION_MINOR   ),  // 1310_xx // indicate the client API version of the window's context.
    CONTEXT_REVISION         (GLFW_CONTEXT_REVISION        ),  // 1310_xx // indicate the client API version of the window's context.
    OPENGL_FORWARD_COMPAT    (GLFW_OPENGL_FORWARD_COMPAT   ),  // 1310_xx // is GLFW_TRUE if the window's context is an OpenGL forward-compatible one, or GLFW_FALSE otherwise.
    CONTEXT_DEBUG            (GLFW_CONTEXT_DEBUG           ),  // 1310_xx // is GLFW_TRUE if the window's context is in debug mode, or GLFW_FALSE otherwise.This is the new name, introduced in GLFW 3.4. The older GLFW_OPENGL_DEBUG_CONTEXT name is also available for compatibility.
    OPENGL_PROFILE           (GLFW_OPENGL_PROFILE          ),  // 1310_xx // indicates the OpenGL profile used by the context. This is GLFW_OPENGL_CORE_PROFILE or GLFW_OPENGL_COMPAT_PROFILE if the context uses a known profile, or GLFW_OPENGL_ANY_PROFILE if the OpenGL profile is unknown or the context is an OpenGL ES context. Note that the returned profile may not match the profile bits of the context flags, as GLFW will try other means of detecting the profile when no bits are set.
    CONTEXT_RELEASE_BEHAVIOR (GLFW_CONTEXT_RELEASE_BEHAVIOR),  // 1310_xx // indicates the release used by the context. Possible values are one of GLFW_ANY_RELEASE_BEHAVIOR, GLFW_RELEASE_BEHAVIOR_FLUSH or GLFW_RELEASE_BEHAVIOR_NONE. If the behavior is GLFW_ANY_RELEASE_BEHAVIOR, the default behavior of the context creation API will be used. If the behavior is GLFW_RELEASE_BEHAVIOR_FLUSH, the pipeline will be flushed whenever the context is released from being the current one. If the behavior is GLFW_RELEASE_BEHAVIOR_NONE, the pipeline will not be flushed on release.
    CONTEXT_NO_ERROR         (GLFW_CONTEXT_NO_ERROR        ),  // 1310_xx // indicates whether errors are generated by the context. Possible values are GLFW_TRUE and GLFW_FALSE. If enabled, situations that would have generated errors instead cause undefined behavior.
    CONTEXT_ROBUSTNESS       (GLFW_CONTEXT_ROBUSTNESS      ),  // 1310_xx // indicates the robustness strategy used by the context. This is GLFW_LOSE_CONTEXT_ON_RESET or GLFW_NO_RESET_NOTIFICATION if the window's context supports robustness, or GLFW_NO_ROBUSTNESS otherwise.
    DOUBLEBUFFER             (GLFW_DOUBLEBUFFER            );  // 1310_xx // indicates the robustness strategy used by the context. This is GLFW_LOSE_CONTEXT_ON_RESET or GLFW_NO_RESET_NOTIFICATION if the window's context supports robustness, or GLFW_NO_ROBUSTNESS otherwise.

    public final int value;

    GlfwAttrib(int value) {
        this.value = value;
    }

}
