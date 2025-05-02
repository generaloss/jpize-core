package jpize.opengl.framebuffer.renderbuffer;

import static jpize.opengl.gl.GL30I.GL_RENDERBUFFER;

public enum GLRenderbufferTarget {

    RENDERBUFFER (GL_RENDERBUFFER);

    public final int value;

    GLRenderbufferTarget(int value) {
        this.value = value;
    }

}
