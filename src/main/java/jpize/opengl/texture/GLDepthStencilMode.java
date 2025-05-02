package jpize.opengl.texture;

import static jpize.opengl.gl.GL11I.GL_DEPTH_COMPONENT;
import static jpize.opengl.gl.GL11I.GL_STENCIL_INDEX;

public enum GLDepthStencilMode {

    STENCIL_INDEX   (GL_STENCIL_INDEX  ), // 640_1
    DEPTH_COMPONENT (GL_DEPTH_COMPONENT); // 640_2

    public final int value;

    GLDepthStencilMode(int value) {
        this.value = value;
    }

    public static GLDepthStencilMode byValue(int value) {
        return values()[value - STENCIL_INDEX.value];
    }

}
