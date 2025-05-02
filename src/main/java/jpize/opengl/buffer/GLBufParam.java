package jpize.opengl.buffer;

import static jpize.opengl.gl.GL44I.*;

public enum GLBufParam {

    BUFFER_IMMUTABLE_STORAGE (GL_BUFFER_IMMUTABLE_STORAGE), // 33311
    BUFFER_STORAGE_FLAGS     (GL_BUFFER_STORAGE_FLAGS    ), // 33312
    BUFFER_SIZE              (GL_BUFFER_SIZE             ), // 34660
    BUFFER_USAGE             (GL_BUFFER_USAGE            ), // 34661
    BUFFER_ACCESS            (GL_BUFFER_ACCESS           ), // 35003
    BUFFER_MAPPED            (GL_BUFFER_MAPPED           ), // 35004
    BUFFER_ACCESS_FLAGS      (GL_BUFFER_ACCESS_FLAGS     ), // 37151
    BUFFER_MAP_LENGTH        (GL_BUFFER_MAP_LENGTH       ), // 37152
    BUFFER_MAP_OFFSET        (GL_BUFFER_MAP_OFFSET       ); // 37153

    public final int value;

    GLBufParam(int value) {
        this.value = value;
    }

}
