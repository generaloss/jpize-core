package jpize.opengl.buffer;

import static jpize.opengl.gl.GL44I.*;

public enum GlBufMapFlag {

    READ              (GL_MAP_READ_BIT             ), // 1
    WRITE             (GL_MAP_WRITE_BIT            ), // 2
    INVALIDATE_RANGE  (GL_MAP_INVALIDATE_RANGE_BIT ), // 4
    INVALIDATE_BUFFER (GL_MAP_INVALIDATE_BUFFER_BIT), // 8
    EXPLICIT_FLUSH    (GL_MAP_FLUSH_EXPLICIT_BIT   ), // 16
    UNSYNCHRONIZED    (GL_MAP_UNSYNCHRONIZED_BIT   ), // 32
    PERSISTENT        (GL_MAP_PERSISTENT_BIT       ), // 64
    COHERENT          (GL_MAP_COHERENT_BIT         ); // 128

    public final int value;

    GlBufMapFlag(int value) {
        this.value = value;
    }

}
