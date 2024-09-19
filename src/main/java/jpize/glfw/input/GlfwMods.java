package jpize.glfw.input;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwMods {

    private final int bits;

    public GlfwMods(int bits) {
        this.bits = bits;
    }

    public int getBits() {
        return bits;
    }


    public boolean hasShift() {
        return (bits & GLFW_MOD_SHIFT) == GLFW_MOD_SHIFT;
    }

    public boolean hasCtrl() {
        return (bits & GLFW_MOD_CONTROL) == GLFW_MOD_CONTROL;
    }

    public boolean hasAlt() {
        return (bits & GLFW_MOD_ALT) == GLFW_MOD_ALT;
    }

    public boolean hasSuper() {
        return (bits & GLFW_MOD_SUPER) == GLFW_MOD_SUPER;
    }

    public boolean hasCapsLock() {
        return (bits & GLFW_MOD_CAPS_LOCK) == GLFW_MOD_CAPS_LOCK;
    }

    public boolean hasNumLock() {
        return (bits & GLFW_MOD_NUM_LOCK) == GLFW_MOD_NUM_LOCK;
    }

}
