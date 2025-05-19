package jpize.opengl.buffer;

import static jpize.opengl.gl.GL44I.*;

public enum GLBufferParam {

    IMMUTABLE_STORAGE (GL_BUFFER_IMMUTABLE_STORAGE), // 33311
    STORAGE_FLAGS     (GL_BUFFER_STORAGE_FLAGS    ), // 33312
    SIZE              (GL_BUFFER_SIZE             ), // 34660
    USAGE             (GL_BUFFER_USAGE            ), // 34661
    ACCESS            (GL_BUFFER_ACCESS           ), // 35003
    MAPPED            (GL_BUFFER_MAPPED           ), // 35004
    ACCESS_FLAGS      (GL_BUFFER_ACCESS_FLAGS     ), // 37151
    MAP_LENGTH        (GL_BUFFER_MAP_LENGTH       ), // 37152
    MAP_OFFSET        (GL_BUFFER_MAP_OFFSET       ); // 37153

    public final int value;

    GLBufferParam(int value) {
        this.value = value;
    }

}
