package jpize.opengl.texture;

import static org.lwjgl.opengl.GL32.*;

public enum GlTexLevelTarget {

    TEXTURE_1D                         (GL_TEXTURE_1D                        ),
    TEXTURE_2D                         (GL_TEXTURE_2D                        ),
    TEXTURE_3D                         (GL_TEXTURE_3D                        ),
    TEXTURE_1D_ARRAY                   (GL_TEXTURE_1D_ARRAY                  ),
    TEXTURE_2D_ARRAY                   (GL_TEXTURE_2D_ARRAY                  ),
    TEXTURE_RECTANGLE                  (GL_TEXTURE_RECTANGLE                 ),
    TEXTURE_2D_MULTISAMPLE             (GL_TEXTURE_2D_MULTISAMPLE            ),
    TEXTURE_2D_MULTISAMPLE_ARRAY       (GL_TEXTURE_2D_MULTISAMPLE_ARRAY      ),
    TEXTURE_CUBE_MAP_POSITIVE_X        (GL_TEXTURE_CUBE_MAP_POSITIVE_X       ),
    TEXTURE_CUBE_MAP_NEGATIVE_X        (GL_TEXTURE_CUBE_MAP_NEGATIVE_X       ),
    TEXTURE_CUBE_MAP_POSITIVE_Y        (GL_TEXTURE_CUBE_MAP_POSITIVE_Y       ),
    TEXTURE_CUBE_MAP_NEGATIVE_Y        (GL_TEXTURE_CUBE_MAP_NEGATIVE_Y       ),
    TEXTURE_CUBE_MAP_POSITIVE_Z        (GL_TEXTURE_CUBE_MAP_POSITIVE_Z       ),
    TEXTURE_CUBE_MAP_NEGATIVE_Z        (GL_TEXTURE_CUBE_MAP_NEGATIVE_Z       ),
    PROXY_TEXTURE_1D                   (GL_PROXY_TEXTURE_1D                  ),
    PROXY_TEXTURE_2D                   (GL_PROXY_TEXTURE_2D                  ),
    PROXY_TEXTURE_3D                   (GL_PROXY_TEXTURE_3D                  ),
    PROXY_TEXTURE_1D_ARRAY             (GL_PROXY_TEXTURE_1D_ARRAY            ),
    PROXY_TEXTURE_2D_ARRAY             (GL_PROXY_TEXTURE_2D_ARRAY            ),
    PROXY_TEXTURE_RECTANGLE            (GL_PROXY_TEXTURE_RECTANGLE           ),
    PROXY_TEXTURE_2D_MULTISAMPLE       (GL_PROXY_TEXTURE_2D_MULTISAMPLE      ),
    PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY (GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY),
    PROXY_TEXTURE_CUBE_MAP             (GL_PROXY_TEXTURE_CUBE_MAP            ),
    TEXTURE_BUFFER                     (GL_TEXTURE_BUFFER                    );

    public final int value;

    GlTexLevelTarget(int value) {
        this.value = value;
    }

}
