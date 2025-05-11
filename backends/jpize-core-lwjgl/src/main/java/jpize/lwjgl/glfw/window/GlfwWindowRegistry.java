package jpize.lwjgl.glfw.window;

import org.lwjgl.glfw.GLFW;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GlfwWindowRegistry {

    private static final Map<Long, GlfwWindow> WINDOWS = new HashMap<>();

    protected static void registerContext(GlfwWindow window) {
        WINDOWS.put(window.getID(), window);
    }

    protected static void unregisterContext(GlfwWindow window) {
        WINDOWS.remove(window.getID());
    }

    public static Collection<GlfwWindow> getWindows() {
        return WINDOWS.values();
    }

    public static GlfwWindow getCurrent() {
        final long windowID = GLFW.glfwGetCurrentContext();
        return WINDOWS.get(windowID);
    }

}
