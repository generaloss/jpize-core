package jpize.opengl.tesselation;

import static jpize.opengl.gl.GL11I.*;

public enum GLFace {

    FRONT          (GL_FRONT         ), // 1028
    BACK           (GL_BACK          ), // 1029
    FRONT_AND_BACK (GL_FRONT_AND_BACK); // 1032

    public final int value;

    GLFace(int value) {
        this.value = value;
    }

}
