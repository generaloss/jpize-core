package jpize.opengl.buffer;

import static jpize.opengl.gl.GL44I.*;

public enum GLBufferStorageFlag {

    READ           (GL_MAP_READ_BIT      ), // 1
    WRITE          (GL_MAP_WRITE_BIT     ), // 2
    PERSISTENT     (GL_MAP_PERSISTENT_BIT), // 64
    COHERENT       (GL_MAP_COHERENT_BIT  ), // 128
    CLIENT_STORAGE (GL_CLIENT_STORAGE_BIT); // 512

    public final int value;

    GLBufferStorageFlag(int value) {
        this.value = value;
    }

}
