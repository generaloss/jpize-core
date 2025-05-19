package jpize.opengl.buffer;

import static jpize.opengl.gl.GL46I.*;

public enum GLBufferTarget {

    ARRAY              (GL_ARRAY_BUFFER             ), // implemented
    ELEMENT            (GL_ELEMENT_ARRAY_BUFFER     ), // implemented
    QUERY              (GL_QUERY_BUFFER             ),
    TEXTURE            (GL_TEXTURE_BUFFER           ),
    UNIFORM            (GL_UNIFORM_BUFFER           ), // implemented
    DRAW_INDIRECT      (GL_DRAW_INDIRECT_BUFFER     ),
    DISPATCH_INDIRECT  (GL_DISPATCH_INDIRECT_BUFFER ),
    TRANSFORM_FEEDBACK (GL_TRANSFORM_FEEDBACK_BUFFER),
    ATOMIC_COUNTER     (GL_ATOMIC_COUNTER_BUFFER    ),
    SHADER_STORAGE     (GL_SHADER_STORAGE_BUFFER    ),
    PIXEL_UNPACK       (GL_PIXEL_UNPACK_BUFFER      ),
    PIXEL_PACK         (GL_PIXEL_PACK_BUFFER        ),
    COPY_READ          (GL_COPY_READ_BUFFER         ),
    COPY_WRITE         (GL_COPY_WRITE_BUFFER        );


    public final int value;

    GLBufferTarget(int value) {
        this.value = value;
    }

}