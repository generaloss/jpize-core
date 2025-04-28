package jpize.opengl.gl;

import java.nio.FloatBuffer;

public interface GL21I extends GL20I {

    int GL_CURRENT_RASTER_SECONDARY_COLOR = 33887;
    int GL_FLOAT_MAT2x3 = 35685;
    int GL_FLOAT_MAT2x4 = 35686;
    int GL_FLOAT_MAT3x2 = 35687;
    int GL_FLOAT_MAT3x4 = 35688;
    int GL_FLOAT_MAT4x2 = 35689;
    int GL_FLOAT_MAT4x3 = 35690;
    int GL_PIXEL_PACK_BUFFER = 35051;
    int GL_PIXEL_UNPACK_BUFFER = 35052;
    int GL_PIXEL_PACK_BUFFER_BINDING = 35053;
    int GL_PIXEL_UNPACK_BUFFER_BINDING = 35055;
    int GL_SRGB = 35904;
    int GL_SRGB8 = 35905;
    int GL_SRGB_ALPHA = 35906;
    int GL_SRGB8_ALPHA8 = 35907;
    int GL_SLUMINANCE_ALPHA = 35908;
    int GL_SLUMINANCE8_ALPHA8 = 35909;
    int GL_SLUMINANCE = 35910;
    int GL_SLUMINANCE8 = 35911;
    int GL_COMPRESSED_SRGB = 35912;
    int GL_COMPRESSED_SRGB_ALPHA = 35913;
    int GL_COMPRESSED_SLUMINANCE = 35914;
    int GL_COMPRESSED_SLUMINANCE_ALPHA = 35915;

    void glUniformMatrix2x3fv(int location, boolean transpose, FloatBuffer value);
    void glUniformMatrix3x2fv(int location, boolean transpose, FloatBuffer value);
    void glUniformMatrix2x4fv(int location, boolean transpose, FloatBuffer value);
    void glUniformMatrix4x2fv(int location, boolean transpose, FloatBuffer value);
    void glUniformMatrix3x4fv(int location, boolean transpose, FloatBuffer value);
    void glUniformMatrix4x3fv(int location, boolean transpose, FloatBuffer value);
    void glUniformMatrix2x3fv(int location, boolean transpose, float[] value);
    void glUniformMatrix3x2fv(int location, boolean transpose, float[] value);
    void glUniformMatrix2x4fv(int location, boolean transpose, float[] value);
    void glUniformMatrix4x2fv(int location, boolean transpose, float[] value);
    void glUniformMatrix3x4fv(int location, boolean transpose, float[] value);
    void glUniformMatrix4x3fv(int location, boolean transpose, float[] value);
}
