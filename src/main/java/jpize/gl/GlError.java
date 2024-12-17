package jpize.gl;

import org.lwjgl.opengl.GL45;

public enum GlError {

    NO_ERROR                      (GL45.GL_NO_ERROR                     ), // 0
    INVALID_ENUM                  (GL45.GL_INVALID_ENUM                 ), // 1280
    INVALID_VALUE                 (GL45.GL_INVALID_VALUE                ), // 1281
    INVALID_OPERATION             (GL45.GL_INVALID_OPERATION            ), // 1282
    STACK_OVERFLOW                (GL45.GL_STACK_OVERFLOW               ), // 1283
    STACK_UNDERFLOW               (GL45.GL_STACK_UNDERFLOW              ), // 1284
    OUT_OF_MEMORY                 (GL45.GL_OUT_OF_MEMORY                ), // 1285
    INVALID_FRAMEBUFFER_OPERATION (GL45.GL_INVALID_FRAMEBUFFER_OPERATION), // 1286
    CONTEXT_LOST                  (GL45.GL_CONTEXT_LOST                 ); // 1287

    public final int value;
    public final String description;

    GlError(int value) {
        this.value = value;
        this.description = this.name().replace('_', ' ').toLowerCase();
    }

    public static GlError byValue(int value) {
        if(value == 0)
            return NO_ERROR;

        final int index = (value - INVALID_ENUM.value + 1);
        if(index > 0 && index < values().length)
            return values()[index];

        return NO_ERROR;
    }

}
