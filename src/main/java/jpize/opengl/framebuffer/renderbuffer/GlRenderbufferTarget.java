package jpize.opengl.framebuffer.renderbuffer;

import static jpize.opengl.gl.GL30I.GL_RENDERBUFFER;

public enum GlRenderbufferTarget {

    RENDERBUFFER (GL_RENDERBUFFER);

    public final int value;

    GlRenderbufferTarget(int value) {
        this.value = value;
    }

}
