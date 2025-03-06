package jpize.opengl.glenum;

import static jpize.opengl.gl.GLI11.*;

public enum GlMode {

    DONT_CARE (GL_DONT_CARE), // 4352
    FASTEST   (GL_FASTEST  ), // 4353
    NICEST    (GL_NICEST   ); // 4354

    public final int value;

    GlMode(int value) {
        this.value = value;
    }

}
