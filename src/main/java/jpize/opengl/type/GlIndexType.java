package jpize.opengl.type;

import static org.lwjgl.opengl.GL11.*;

public enum GlIndexType{

    UNSIGNED_BYTE  (GL_UNSIGNED_BYTE , 1), // 5121
    UNSIGNED_SHORT (GL_UNSIGNED_SHORT, 2), // 5123
    UNSIGNED_INT   (GL_UNSIGNED_INT  , 4); // 5125

    public final int value;
    public final int size;

    GlIndexType(int value, int size){
        this.value = value;
        this.size = size;
    }

}