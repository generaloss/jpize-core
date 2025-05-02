package jpize.opengl.tesselation;

import static jpize.opengl.gl.GL33I.*;

public enum GLPolygonMode {

    POINT (GL_POINT), // 691_2
    LINE  (GL_LINE ), // 691_3
    FILL  (GL_FILL ); // 691_4

    public final int value;

    GLPolygonMode(int value) {
        this.value = value;
    }

}
