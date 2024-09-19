package jpize.gl.texture;

import static org.lwjgl.opengl.GL40.*;

public enum GlTexParamTarget {

    TEXTURE_1D                   (GL_TEXTURE_1D                  ),
    TEXTURE_1D_ARRAY             (GL_TEXTURE_1D_ARRAY            ),
    TEXTURE_2D                   (GL_TEXTURE_2D                  ),
    TEXTURE_2D_ARRAY             (GL_TEXTURE_2D_ARRAY            ),
    TEXTURE_2D_MULTISAMPLE       (GL_TEXTURE_2D_MULTISAMPLE      ),
    TEXTURE_2D_MULTISAMPLE_ARRAY (GL_TEXTURE_2D_MULTISAMPLE_ARRAY),
    TEXTURE_3D                   (GL_TEXTURE_3D                  ),
    TEXTURE_CUBE_MAP             (GL_TEXTURE_CUBE_MAP            ),
    TEXTURE_CUBE_MAP_ARRAY       (GL_TEXTURE_CUBE_MAP_ARRAY      ),
    TEXTURE_RECTANGLE            (GL_TEXTURE_RECTANGLE           );

    public final int value;

    GlTexParamTarget(int value) {
        this.value = value;
    }

}
