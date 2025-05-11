package jpize.lwjgl.glfw;

import jpize.lwjgl.glfw.callback.GlfwErrorCallback;
import jpize.lwjgl.glfw.init.GlfwAnglePlatformType;
import jpize.lwjgl.glfw.init.GlfwInitHint;
import jpize.lwjgl.glfw.init.GlfwPlatform;
import jpize.lwjgl.glfw.init.GlfwWaylandLibdecor;
import jpize.lwjgl.glfw.window.*;
import jpize.util.math.vector.Vec3i;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWAllocator;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

public class Glfw {

    public static final int TRUE = GLFW.GLFW_TRUE;
    public static final int FALSE = GLFW.GLFW_FALSE;
    public static final int DONT_CARE = GLFW.GLFW_DONT_CARE;
    public static final int ANY_POSITION = GLFW.GLFW_ANY_POSITION;


    public static void setTime(double time) {
        GLFW.glfwSetTime(time);
    }

    public static float getTime() {
        return (float) GLFW.glfwGetTime();
    }

    public static long getTimerFrequency() {
        return GLFW.glfwGetTimerFrequency();
    }

    public static long getTimerValue() {
        return GLFW.glfwGetTimerValue();
    }


    public static GlfwPlatform getPlatform() {
        return GlfwPlatform.byValue(GLFW.glfwGetPlatform());
    }

    public static boolean platformSupported(GlfwPlatform platform) {
        return GLFW.glfwPlatformSupported(platform.value);
    }


    public static GlfwError getError(PointerBuffer description) {
        return GlfwError.byValue(GLFW.glfwGetError(description));
    }

    public static void checkError(){
        final PointerBuffer description = MemoryUtil.memAllocPointer(1);
        if(getError(description) != GlfwError.NO_ERROR)
            System.err.println("[GLFW] Error: " + MemoryUtil.memUTF8(description.get()));
        MemoryUtil.memFree(description);
    }

    public static GLFWErrorCallback setErrorCallback(GlfwErrorCallback callback) {
        return GLFW.glfwSetErrorCallback((int code, long description) -> {
            final GlfwError error = GlfwError.byValue(code);
            final String message = MemoryUtil.memUTF8(description);
            callback.invoke(error, message);
        });
    }


    public static void initAllocator(GLFWAllocator allocator) {
        GLFW.glfwInitAllocator(allocator);
    }


    public static void defaultWindowHints() {
        GLFW.glfwDefaultWindowHints();
    }


    public static void glfwInitHint(GlfwInitHint hint, int value) {
        GLFW.glfwInitHint(hint.value, value);
    }

    public static void glfwInitHintPlatform(GlfwPlatform value) {
        glfwInitHint(GlfwInitHint.PLATFORM, value.value);
    }

    public static void glfwInitHintJoystickHatButtons(boolean value) {
        glfwInitHint(GlfwInitHint.JOYSTICK_HAT_BUTTONS, value ? TRUE : FALSE);
    }

    public static void glfwInitHintAnglePlatformType(GlfwAnglePlatformType value) {
        glfwInitHint(GlfwInitHint.ANGLE_PLATFORM_TYPE, value.value);
    }

    public static void glfwInitHintCocoaChdirResources(boolean value) {
        glfwInitHint(GlfwInitHint.COCOA_CHDIR_RESOURCES, value ? TRUE : FALSE);
    }

    public static void glfwInitHintCocoaMenubar(boolean value) {
        glfwInitHint(GlfwInitHint.COCOA_MENUBAR, value ? TRUE : FALSE);
    }

    public static void glfwInitHintWaylandLibdecor(GlfwWaylandLibdecor value) {
        glfwInitHint(GlfwInitHint.WAYLAND_LIBDECOR, value.value);
    }

    public static void glfwInitHintX11XcbVulkanSurface(boolean value) {
        glfwInitHint(GlfwInitHint.X11_XCB_VULKAN_SURFACE, value ? TRUE : FALSE);
    }


    public static void windowHint(GlfwWindowHint hint, int value) {
        GLFW.glfwWindowHint(hint.value, value);
    }

    public static void windowHint(GlfwWindowHint hint, boolean value) {
        GLFW.glfwWindowHint(hint.value, value ? TRUE : FALSE);
    }

    public static void windowHint(GlfwWindowHint hint, GlfwClientAPI value) {
        GLFW.glfwWindowHint(hint.value, value.value);
    }

    public static void windowHint(GlfwWindowHint hint, GlfwContextCreationAPI value) {
        GLFW.glfwWindowHint(hint.value, value.value);
    }

    public static void windowHint(GlfwWindowHint hint, GlfwContextRobustness value) {
        GLFW.glfwWindowHint(hint.value, value.value);
    }

    public static void windowHint(GlfwWindowHint hint, GlfwContextReleaseBehavior value) {
        GLFW.glfwWindowHint(hint.value, value.value);
    }

    public static void windowHint(GlfwWindowHint hint, GlfwOpenGLProfile value) {
        GLFW.glfwWindowHint(hint.value, value.value);
    }

    public static void windowHint(GlfwWindowHint hint, CharSequence value) {
        GLFW.glfwWindowHintString(hint.value, value);
    }


    public static Vec3i getVersion() {
        final IntBuffer major = MemoryUtil.memAllocInt(1);
        final IntBuffer minor = MemoryUtil.memAllocInt(1);
        final IntBuffer rev = MemoryUtil.memAllocInt(1);

        GLFW.glfwGetVersion(major, minor, rev);
        final Vec3i version = new Vec3i(major.get(), minor.get(), rev.get());

        MemoryUtil.memFree(major);
        MemoryUtil.memFree(minor);
        MemoryUtil.memFree(rev);
        return version;
    }

    public static String getVersionString() {
        return GLFW.glfwGetVersionString();
    }


    public static boolean extensionSupported(CharSequence extension) {
        return GLFW.glfwExtensionSupported(extension);
    }


    public static long getProcAddress(CharSequence procname) {
        return GLFW.glfwGetProcAddress(procname);
    }


    public static void pollEvents() {
        GLFW.glfwPollEvents();
    }

    public static void postEmptyEvent() {
        GLFW.glfwPostEmptyEvent();
    }

    public static void waitEvents() {
        GLFW.glfwWaitEvents();
    }

    public static void waitEventsTimeout(double timeout) {
        GLFW.glfwWaitEventsTimeout(timeout);
    }


    public static GlfwWindow getCurrentContext() {
        return GlfwWindowRegistry.getCurrent();
    }


    private static boolean vsync;

    public static void swapInterval(int interval) {
        GLFW.glfwSwapInterval(interval);
        vsync = (interval == 1);
    }

    public static boolean isVSyncEnabled() {
        return vsync;
    }

    public static void enableVSync(boolean enable) {
        swapInterval(enable ? 1 : 0);
    }

    public static void toggleVSync() {
        enableVSync(!isVSyncEnabled());
    }

}
