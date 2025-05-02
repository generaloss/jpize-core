package jpize.opengl.framebuffer;

import static jpize.opengl.gl.GL30I.*;

public enum GLFramebufferTarget {

    FRAMEBUFFER      (GL_FRAMEBUFFER),
    READ_FRAMEBUFFER (GL_READ_FRAMEBUFFER),
    DRAW_FRAMEBUFFER (GL_DRAW_FRAMEBUFFER);

    public final int value;

    GLFramebufferTarget(int value) {
        this.value = value;
    }

}
