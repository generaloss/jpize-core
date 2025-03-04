package jpize.opengl.buffer;

import static jpize.opengl.IGL15.*;

public enum GlBufAccess {

    READ_ONLY  (GL_READ_ONLY ), // 3500_0
    WRITE_ONLY (GL_WRITE_ONLY), // 3500_1
    READ_WRITE (GL_READ_WRITE); // 3500_2

    public final int value;

    GlBufAccess(int value) {
        this.value = value;
    }

}
