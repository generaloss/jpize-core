package jpize.opengl.texture;

import static jpize.opengl.gl.GL11I.*;

public enum GLTexImage1DTarget {

    TEXTURE_1D       (GL_TEXTURE_1D      ), // 3552
    PROXY_TEXTURE_1D (GL_PROXY_TEXTURE_1D); // 32867

    public final int value;

    GLTexImage1DTarget(int value) {
        this.value = value;
    }

}
