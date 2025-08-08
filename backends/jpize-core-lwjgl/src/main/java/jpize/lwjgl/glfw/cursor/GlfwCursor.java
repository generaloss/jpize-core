package jpize.lwjgl.glfw.cursor;

import jpize.lwjgl.glfw.GlfwImage;
import jpize.lwjgl.glfw.GlfwObject;
import resourceflow.Disposable;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwCursor extends GlfwObject implements Disposable {

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
