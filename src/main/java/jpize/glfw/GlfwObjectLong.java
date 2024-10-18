package jpize.glfw;

import java.util.Objects;

public class GlfwObjectLong {

    protected final long ID;

    public GlfwObjectLong(long ID) {
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
        GlfwObjectLong glfwObject = (GlfwObjectLong) object;
        return ID == glfwObject.ID;
    }

}
