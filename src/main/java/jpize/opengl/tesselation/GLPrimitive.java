package jpize.opengl.tesselation;

import static jpize.opengl.gl.GL33I.*;

public enum GLPrimitive {

    POINTS         (GL_POINTS        ), // 0
    LINES          (GL_LINES         ), // 1
    LINE_LOOP      (GL_LINE_LOOP     ), // 2
    LINE_STRIP     (GL_LINE_STRIP    ), // 3
    TRIANGLES      (GL_TRIANGLES     ), // 4
    TRIANGLE_STRIP (GL_TRIANGLE_STRIP), // 5
    TRIANGLE_FAN   (GL_TRIANGLE_FAN  ), // 6
    QUADS          (GL_QUADS         ), // 7
    QUAD_STRIP     (GL_QUAD_STRIP    ), // 8
    POLYGON        (GL_POLYGON       ); // 9

    public final int value;

    GLPrimitive(int value) {
        this.value = value;
    }

}
