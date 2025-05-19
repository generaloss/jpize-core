package jpize.opengl.texture;

import static jpize.opengl.gl.GL40I.*;

public enum GLTextureTarget {

    TEXTURE_1D             (GL_TEXTURE_1D            ),
    TEXTURE_2D             (GL_TEXTURE_2D            ),
    TEXTURE_3D             (GL_TEXTURE_3D            ),
    TEXTURE_1D_ARRAY       (GL_TEXTURE_1D_ARRAY      ),
    TEXTURE_2D_ARRAY       (GL_TEXTURE_2D_ARRAY      ),
    TEXTURE_CUBE_MAP       (GL_TEXTURE_CUBE_MAP      ),
    TEXTURE_CUBE_MAP_ARRAY (GL_TEXTURE_CUBE_MAP_ARRAY);

    public final int value;

    GLTextureTarget(int value) {
        this.value = value;
    }

}
