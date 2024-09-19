package jpize.gl.texture;

import static org.lwjgl.opengl.GL40.*;

public enum GlTexTarget {

    TEXTURE_1D             (GL_TEXTURE_1D            ),
    TEXTURE_2D             (GL_TEXTURE_2D            ),
    TEXTURE_3D             (GL_TEXTURE_3D            ),
    TEXTURE_1D_ARRAY       (GL_TEXTURE_1D_ARRAY      ),
    TEXTURE_2D_ARRAY       (GL_TEXTURE_2D_ARRAY      ),
    TEXTURE_CUBE_MAP       (GL_TEXTURE_CUBE_MAP      ),
    TEXTURE_CUBE_MAP_ARRAY (GL_TEXTURE_CUBE_MAP_ARRAY);

    public final int value;

    GlTexTarget(int value) {
        this.value = value;
    }

}
