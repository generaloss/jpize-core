package jpize.opengl.framebuffer;

import static jpize.opengl.gl.GL30I.*;

public enum GlFramebufferTarget {

    FRAMEBUFFER      (GL_FRAMEBUFFER),
    READ_FRAMEBUFFER (GL_READ_FRAMEBUFFER),
    DRAW_FRAMEBUFFER (GL_DRAW_FRAMEBUFFER);

    public final int value;

    GlFramebufferTarget(int value) {
        this.value = value;
    }

}
