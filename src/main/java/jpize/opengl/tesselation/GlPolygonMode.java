package jpize.opengl.tesselation;

import static org.lwjgl.opengl.GL33.*;

public enum GlPolygonMode{

    POINT (GL_POINT), // 691_2
    LINE  (GL_LINE ), // 691_3
    FILL  (GL_FILL ); // 691_4

    public final int value;

    GlPolygonMode(int value) {
        this.value = value;
    }

}
