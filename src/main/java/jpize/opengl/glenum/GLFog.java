package jpize.opengl.glenum;

import static jpize.opengl.gl.GL15I.*;

public enum GLFog { // TODO: delete class

    INDEX     (GL_FOG_INDEX    ), // 29_13
    DENSITY   (GL_FOG_DENSITY  ), // 29_14
    START     (GL_FOG_START    ), // 29_15
    END       (GL_FOG_END      ), // 29_16
    MODE      (GL_FOG_MODE     ), // 29_17
    COLOR     (GL_FOG_COLOR    ), // 29_18
    COORD_SRC (GL_FOG_COORD_SRC); // 33872

    public final int value;
    
    GLFog(int value) {
        this.value = value;
    }
    
}
