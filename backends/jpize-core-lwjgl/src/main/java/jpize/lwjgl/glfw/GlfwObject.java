package jpize.lwjgl.glfw;

import java.util.Objects;

public class GlfwObject {

    protected final long ID;

    public GlfwObject(long ID) {
        this.ID = ID;
        if(this.ID == 0L)
            throw new IllegalStateException("Failed to create the GLFW object.");
    }

    public long getID() {
        return ID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ID);
    }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(object == null || getClass() != object.getClass())
            return false;
        final GlfwObject glfwObject = (GlfwObject) object;
        return (ID == glfwObject.ID);
    }


    public static long getID(GlfwObject object) {
        if(object == null)
            return 0L;
        return object.ID;
    }

}
