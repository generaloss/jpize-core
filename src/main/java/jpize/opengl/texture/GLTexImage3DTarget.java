package jpize.opengl.texture;

import static jpize.opengl.gl.GL30I.*;

public enum GLTexImage3DTarget {

    TEXTURE_2D_ARRAY       (GL_TEXTURE_2D_ARRAY      ), // 35866
    PROXY_TEXTURE_2D_ARRAY (GL_PROXY_TEXTURE_2D_ARRAY), // 35867
    TEXTURE_3D             (GL_TEXTURE_3D            ), // 32879
    PROXY_TEXTURE_3D       (GL_PROXY_TEXTURE_3D      ); // 32880

    public final int value;

    GLTexImage3DTarget(int value) {
        this.value = value;
    }

}
