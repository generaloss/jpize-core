package jpize.glfw.cursor;

import jpize.glfw.GlfwImage;
import jpize.glfw.GlfwObjectLong;
import jpize.util.Disposable;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwCursor extends GlfwObjectLong implements Disposable {

    public GlfwCursor(GlfwImage image, int hotspotX, int hotspotY) {
        super(glfwCreateCursor(image.getGLFWImage(), hotspotX, hotspotY));
    }

    public GlfwCursor(GlfwCursorShape shape) {
        super(glfwCreateStandardCursor(shape.value));
    }

    @Override
    public void dispose() {
        glfwDestroyCursor(ID);
    }

}
