package jpize.opengl.glenum;

import static jpize.opengl.gl.GL11I.*;

public enum GLMode {

    DONT_CARE (GL_DONT_CARE), // 4352
    FASTEST   (GL_FASTEST  ), // 4353
    NICEST    (GL_NICEST   ); // 4354

    public final int value;

    GLMode(int value) {
        this.value = value;
    }

}
