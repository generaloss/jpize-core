package jpize.opengl.texture;

import static jpize.opengl.gl.GL30I.GL_COMPARE_REF_TO_TEXTURE;
import static jpize.opengl.gl.GL11I.GL_NONE;

public enum GLCompareMode {

    COMPARE_REF_TO_TEXTURE (GL_COMPARE_REF_TO_TEXTURE), // 34894
    NONE                   (GL_NONE                  ); // 0

    public final int value;

    GLCompareMode(int value) {
        this.value = value;
    }


    public static GLCompareMode byValue(int value) {
        if(value == COMPARE_REF_TO_TEXTURE.value) return COMPARE_REF_TO_TEXTURE;
        return NONE;
    }

}
