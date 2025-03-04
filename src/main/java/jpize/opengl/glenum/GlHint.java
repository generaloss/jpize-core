package jpize.opengl.glenum;

import static org.lwjgl.opengl.GL33.*;

public enum GlHint {

    PERSPECTIVE_CORRECTION_HINT (GL_PERSPECTIVE_CORRECTION_HINT), // 315_2
    POINT_SMOOTH_HINT           (GL_POINT_SMOOTH_HINT          ), // 315_3
    LINE_SMOOTH_HINT            (GL_LINE_SMOOTH_HINT           ), // 315_4
    POLYGON_SMOOTH_HINT         (GL_POLYGON_SMOOTH_HINT        ), // 315_5
    FOG_HINT                    (GL_FOG_HINT                   ), // 315_6
    GENERATE_MIPMAP_HINT        (GL_GENERATE_MIPMAP_HINT       ), // 33170
    TEXTURE_COMPRESSION_HINT    (GL_TEXTURE_COMPRESSION_HINT   ); // 34031

    public final int value;
    
    GlHint(int value) {
        this.value = value;
    }
    
}