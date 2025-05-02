package jpize.opengl.glenum;

import static jpize.opengl.gl.GL11I.*;

public enum GLCompareFunc {

    NEVER    (GL_NEVER   ), // 51_2
    LESS     (GL_LESS    ), // 51_3
    EQUAL    (GL_EQUAL   ), // 51_4
    LEQUAL   (GL_LEQUAL  ), // 51_5
    GREATER  (GL_GREATER ), // 51_6
    NOTEQUAL (GL_NOTEQUAL), // 51_7
    GEQUAL   (GL_GEQUAL  ), // 51_8
    ALWAYS   (GL_ALWAYS  ); // 51_9

    public final int value;

    GLCompareFunc(int value) {
        this.value = value;
    }

    public static GLCompareFunc byValue(int value) {
        return values()[value - NEVER.value];
    }

}
