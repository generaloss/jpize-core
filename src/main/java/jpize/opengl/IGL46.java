package jpize.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public interface IGL46 extends IGL45 {

    int GL_PARAMETER_BUFFER = 33006;
    int GL_PARAMETER_BUFFER_BINDING = 33007;
    int GL_VERTICES_SUBMITTED = 33518;
    int GL_PRIMITIVES_SUBMITTED = 33519;
    int GL_VERTEX_SHADER_INVOCATIONS = 33520;
    int GL_TESS_CONTROL_SHADER_PATCHES = 33521;
    int GL_TESS_EVALUATION_SHADER_INVOCATIONS = 33522;
    int GL_GEOMETRY_SHADER_PRIMITIVES_EMITTED = 33523;
    int GL_FRAGMENT_SHADER_INVOCATIONS = 33524;
    int GL_COMPUTE_SHADER_INVOCATIONS = 33525;
    int GL_CLIPPING_INPUT_PRIMITIVES = 33526;
    int GL_CLIPPING_OUTPUT_PRIMITIVES = 33527;
    int GL_POLYGON_OFFSET_CLAMP = 36379;
    int GL_CONTEXT_FLAG_NO_ERROR_BIT = 8;
    int GL_SHADER_BINARY_FORMAT_SPIR_V = 38225;
    int GL_SPIR_V_BINARY = 38226;
    int GL_SPIR_V_EXTENSIONS = 38227;
    int GL_NUM_SPIR_V_EXTENSIONS = 38228;
    int GL_TEXTURE_MAX_ANISOTROPY = 34046;
    int GL_MAX_TEXTURE_MAX_ANISOTROPY = 34047;
    int GL_TRANSFORM_FEEDBACK_OVERFLOW = 33516;
    int GL_TRANSFORM_FEEDBACK_STREAM_OVERFLOW = 33517;

    void nglMultiDrawArraysIndirectCount(int mode, long indirect, long drawcount, int maxdrawcount, int stride);
    void glMultiDrawArraysIndirectCount(int mode, ByteBuffer indirect, long drawcount, int maxdrawcount, int stride);
    void glMultiDrawArraysIndirectCount(int mode, long indirect, long drawcount, int maxdrawcount, int stride);
    void glMultiDrawArraysIndirectCount(int mode, IntBuffer indirect, long drawcount, int maxdrawcount, int stride);
    void nglMultiDrawElementsIndirectCount(int mode, int type, long indirect, long drawcount, int maxdrawcount, int stride);
    void glMultiDrawElementsIndirectCount(int mode, int type, ByteBuffer indirect, long drawcount, int maxdrawcount, int stride);
    void glMultiDrawElementsIndirectCount(int mode, int type, long indirect, long drawcount, int maxdrawcount, int stride);
    void glMultiDrawElementsIndirectCount(int mode, int type, IntBuffer indirect, long drawcount, int maxdrawcount, int stride);
    void glPolygonOffsetClamp(float factor, float units, float clamp);
    void nglSpecializeShader(int shader, long pEntryPoint, int numSpecializationConstants, long pConstantIndex, long pConstantValue);
    void glSpecializeShader(int shader, ByteBuffer pEntryPoint, IntBuffer pConstantIndex, IntBuffer pConstantValue);
    void glSpecializeShader(int shader, CharSequence pEntryPoint, IntBuffer pConstantIndex, IntBuffer pConstantValue);
    void glMultiDrawArraysIndirectCount(int mode, int[] indirect, long drawcount, int maxdrawcount, int stride);
    void glMultiDrawElementsIndirectCount(int mode, int type, int[] indirect, long drawcount, int maxdrawcount, int stride);
    void glSpecializeShader(int shader, ByteBuffer pEntryPoint, int [] pConstantIndex, int [] pConstantValue);
    void glSpecializeShader(int shader, CharSequence pEntryPoint, int [] pConstantIndex, int [] pConstantValue);

}
