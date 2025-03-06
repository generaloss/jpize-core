package jpize.opengl.buffer;

import static jpize.opengl.gl.GLI33.*;

public enum GlBufUsage {

    STREAM_DRAW  (GL_STREAM_DRAW ), // 35040
    STREAM_READ  (GL_STREAM_READ ), // 35041
    STREAM_COPY  (GL_STREAM_COPY ), // 35042

    STATIC_DRAW  (GL_STATIC_DRAW ), // 35044
    STATIC_READ  (GL_STATIC_READ ), // 35045
    STATIC_COPY  (GL_STATIC_COPY ), // 35046

    DYNAMIC_DRAW (GL_DYNAMIC_DRAW), // 35048
    DYNAMIC_READ (GL_DYNAMIC_READ), // 35049
    DYNAMIC_COPY (GL_DYNAMIC_COPY); // 35050

    public final int value;

    GlBufUsage(int value) {
        this.value = value;
    }

}
