package jpize.opengl.texture;

import static org.lwjgl.opengl.GL11.*;

public enum GlTexImg1DTarget {

    TEXTURE_1D       (GL_TEXTURE_1D      ), // 3552
    PROXY_TEXTURE_1D (GL_PROXY_TEXTURE_1D); // 32867

    public final int value;

    GlTexImg1DTarget(int value) {
        this.value = value;
    }

}
