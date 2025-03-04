package jpize.opengl.buffer;

import static jpize.opengl.IGL44.*;

public enum GlBufStorageFlag {

    READ           (GL_MAP_READ_BIT      ), // 1
    WRITE          (GL_MAP_WRITE_BIT     ), // 2
    PERSISTENT     (GL_MAP_PERSISTENT_BIT), // 64
    COHERENT       (GL_MAP_COHERENT_BIT  ), // 128
    CLIENT_STORAGE (GL_CLIENT_STORAGE_BIT); // 512

    public final int value;

    GlBufStorageFlag(int value) {
        this.value = value;
    }

}
