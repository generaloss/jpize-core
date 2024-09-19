package jpize.gl.tesselation;

import static org.lwjgl.opengl.GL33.*;

public enum GlFace {

    FRONT          (GL_FRONT         ), // 1028
    BACK           (GL_BACK          ), // 1029
    FRONT_AND_BACK (GL_FRONT_AND_BACK); // 1032

    public final int value;

    GlFace(int value) {
        this.value = value;
    }

}
