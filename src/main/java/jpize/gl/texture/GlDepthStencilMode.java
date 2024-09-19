package jpize.gl.texture;

import static org.lwjgl.opengl.GL11.GL_DEPTH_COMPONENT;
import static org.lwjgl.opengl.GL11.GL_STENCIL_INDEX;

public enum GlDepthStencilMode {

    STENCIL_INDEX   (GL_STENCIL_INDEX  ), // 640_1
    DEPTH_COMPONENT (GL_DEPTH_COMPONENT); // 640_2

    public final int value;

    GlDepthStencilMode(int value) {
        this.value = value;
    }

    public static GlDepthStencilMode byValue(int value) {
        return values()[value - STENCIL_INDEX.value];
    }

}
