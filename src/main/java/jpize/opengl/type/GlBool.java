package jpize.opengl.type;

import static jpize.opengl.gl.GL11I.GL_TRUE;
import static jpize.opengl.gl.GL11I.GL_FALSE;

public class GlBool {

    public static boolean of(int value) {
        return (value == GL_TRUE);
    }

    public static int by(boolean b) {
        return (b ? GL_TRUE : GL_FALSE);
    }

}
