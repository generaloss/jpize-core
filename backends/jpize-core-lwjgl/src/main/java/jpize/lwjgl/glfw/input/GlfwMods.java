package jpize.lwjgl.glfw.input;

import jpize.context.input.Mods;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwMods extends Mods {

    public GlfwMods(int bits) {
        super(bits);
    }

    @Override
    public boolean hasShift() {
        return (bits & GLFW_MOD_SHIFT) == GLFW_MOD_SHIFT;
    }

    @Override
    public boolean hasCtrl() {
        return (bits & GLFW_MOD_CONTROL) == GLFW_MOD_CONTROL;
    }

    @Override
    public boolean hasAlt() {
        return (bits & GLFW_MOD_ALT) == GLFW_MOD_ALT;
    }

    @Override
    public boolean hasSuper() {
        return (bits & GLFW_MOD_SUPER) == GLFW_MOD_SUPER;
    }

    @Override
    public boolean hasCapsLock() {
        return (bits & GLFW_MOD_CAPS_LOCK) == GLFW_MOD_CAPS_LOCK;
    }

    @Override
    public boolean hasNumLock() {
        return (bits & GLFW_MOD_NUM_LOCK) == GLFW_MOD_NUM_LOCK;
    }

}
