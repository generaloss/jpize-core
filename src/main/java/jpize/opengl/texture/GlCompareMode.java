package jpize.opengl.texture;

import static jpize.opengl.gl.GLI30.GL_COMPARE_REF_TO_TEXTURE;
import static jpize.opengl.gl.GLI11.GL_NONE;

public enum GlCompareMode {

    COMPARE_REF_TO_TEXTURE (GL_COMPARE_REF_TO_TEXTURE), // 34894
    NONE                   (GL_NONE                  ); // 0

    public final int value;

    GlCompareMode(int value) {
        this.value = value;
    }


    public static GlCompareMode byValue(int value) {
        if(value == COMPARE_REF_TO_TEXTURE.value) return COMPARE_REF_TO_TEXTURE;
        return NONE;
    }

}
