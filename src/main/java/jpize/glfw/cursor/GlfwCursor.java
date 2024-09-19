package jpize.glfw.cursor;

import jpize.glfw.GlfwImage;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwCursor {

    private final long ID;

    public GlfwCursor(GlfwImage image, int hotspotX, int hotspotY) {
        this.ID = glfwCreateCursor(image.getGLFWImage(), hotspotX, hotspotY);
    }

    public GlfwCursor(GlfwCursorShape shape) {
        this.ID = glfwCreateStandardCursor(shape.value);
    }

    public long getID() {
        return ID;
    }

    public void destroy() {
        glfwDestroyCursor(ID);
    }

}
