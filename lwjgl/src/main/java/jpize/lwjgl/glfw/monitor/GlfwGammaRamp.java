package jpize.lwjgl.glfw.monitor;

import org.lwjgl.glfw.GLFWGammaRamp;

public class GlfwGammaRamp {

    private final GLFWGammaRamp ramp;

    public GlfwGammaRamp(GLFWGammaRamp ramp) {
        this.ramp = ramp;
    }

    public GlfwGammaRamp() {
        this(GLFWGammaRamp.calloc());
    }

    public GLFWGammaRamp getGLFWGammaRamp() {
        return ramp;
    }


    public GlfwGammaRamp set(/* ... */) {
        // ramp.size();
        return this;
    }

    public void free() {
        ramp.free();
    }

}
