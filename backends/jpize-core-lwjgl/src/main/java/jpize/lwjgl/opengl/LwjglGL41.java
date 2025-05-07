package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL41I;
import org.lwjgl.opengl.GL41;
import java.nio.*;

public class LwjglGL41 extends LwjglGL40 implements GL41I {

    public static final LwjglGL41 INSTANCE = new LwjglGL41();

    protected LwjglGL41() { }


    @Override
    public void glReleaseShaderCompiler() {
        GL41.glReleaseShaderCompiler();
    }

    @Override
    public void glShaderBinary(IntBuffer shaders, int binaryformat, ByteBuffer binary) {
        GL41.glShaderBinary(shaders, binaryformat, binary);
    }

    @Override
    public void glGetShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range, IntBuffer precision) {
        GL41.glGetShaderPrecisionFormat(shadertype, precisiontype, range, precision);
    }

    @Override
    public int glGetShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range) {
        return GL41.glGetShaderPrecisionFormat(shadertype, precisiontype, range);
    }

    @Override
    public void glDepthRangef(float zNear, float zFar) {
        GL41.glDepthRangef(zNear, zFar);
    }

    @Override
    public void glClearDepthf(float depth) {
        GL41.glClearDepthf(depth);
    }

    @Override
    public void glGetProgramBinary(int program, IntBuffer length, IntBuffer binaryFormat, ByteBuffer binary) {
        GL41.glGetProgramBinary(program, length, binaryFormat, binary);
    }

    @Override
    public void glProgramBinary(int program, int binaryFormat, ByteBuffer binary) {
        GL41.glProgramBinary(program, binaryFormat, binary);
    }

    @Override
    public void glProgramParameteri(int program, int pname, int value) {
        GL41.glProgramParameteri(program, pname, value);
    }

    @Override
    public void glUseProgramStages(int pipeline, int stages, int program) {
        GL41.glUseProgramStages(pipeline, stages, program);
    }

    @Override
    public void glActiveShaderProgram(int pipeline, int program) {
        GL41.glActiveShaderProgram(pipeline, program);
    }

    @Override
    public int glCreateShaderProgramv(int type, CharSequence... strings) {
        return GL41.glCreateShaderProgramv(type, strings);
    }

    @Override
    public int glCreateShaderProgramv(int type, CharSequence string) {
        return GL41.glCreateShaderProgramv(type, string);
    }

    @Override
    public void glBindProgramPipeline(int pipeline) {
        GL41.glBindProgramPipeline(pipeline);
    }

    @Override
    public void glDeleteProgramPipelines(IntBuffer pipelines) {
        GL41.glDeleteProgramPipelines(pipelines);
    }

    @Override
    public void glDeleteProgramPipelines(int pipeline) {
        GL41.glDeleteProgramPipelines(pipeline);
    }

    @Override
    public void glGenProgramPipelines(IntBuffer pipelines) {
        GL41.glGenProgramPipelines(pipelines);
    }

    @Override
    public int glGenProgramPipelines() {
        return GL41.glGenProgramPipelines();
    }

    @Override
    public boolean glIsProgramPipeline(int pipeline) {
        return GL41.glIsProgramPipeline(pipeline);
    }

    @Override
    public void glGetProgramPipelineiv(int pipeline, int pname, IntBuffer params) {
        GL41.glGetProgramPipelineiv(pipeline, pname, params);
    }

    @Override
    public int glGetProgramPipelinei(int pipeline, int pname) {
        return GL41.glGetProgramPipelinei(pipeline, pname);
    }

    @Override
    public void glProgramUniform1i(int program, int location, int x) {
        GL41.glProgramUniform1i(program, location, x);
    }

    @Override
    public void glProgramUniform2i(int program, int location, int x, int y) {
        GL41.glProgramUniform2i(program, location, x, y);
    }

    @Override
    public void glProgramUniform3i(int program, int location, int x, int y, int z) {
        GL41.glProgramUniform3i(program, location, x, y, z);
    }

    @Override
    public void glProgramUniform4i(int program, int location, int x, int y, int z, int w) {
        GL41.glProgramUniform4i(program, location, x, y, z, w);
    }

    @Override
    public void glProgramUniform1ui(int program, int location, int x) {
        GL41.glProgramUniform1ui(program, location, x);
    }

    @Override
    public void glProgramUniform2ui(int program, int location, int x, int y) {
        GL41.glProgramUniform2ui(program, location, x, y);
    }

    @Override
    public void glProgramUniform3ui(int program, int location, int x, int y, int z) {
        GL41.glProgramUniform3ui(program, location, x, y, z);
    }

    @Override
    public void glProgramUniform4ui(int program, int location, int x, int y, int z, int w) {
        GL41.glProgramUniform4ui(program, location, x, y, z, w);
    }

    @Override
    public void glProgramUniform1f(int program, int location, float x) {
        GL41.glProgramUniform1f(program, location, x);
    }

    @Override
    public void glProgramUniform2f(int program, int location, float x, float y) {
        GL41.glProgramUniform2f(program, location, x, y);
    }

    @Override
    public void glProgramUniform3f(int program, int location, float x, float y, float z) {
        GL41.glProgramUniform3f(program, location, x, y, z);
    }

    @Override
    public void glProgramUniform4f(int program, int location, float x, float y, float z, float w) {
        GL41.glProgramUniform4f(program, location, x, y, z, w);
    }

    @Override
    public void glProgramUniform1d(int program, int location, double x) {
        GL41.glProgramUniform1d(program, location, x);
    }

    @Override
    public void glProgramUniform2d(int program, int location, double x, double y) {
        GL41.glProgramUniform2d(program, location, x, y);
    }

    @Override
    public void glProgramUniform3d(int program, int location, double x, double y, double z) {
        GL41.glProgramUniform3d(program, location, x, y, z);
    }

    @Override
    public void glProgramUniform4d(int program, int location, double x, double y, double z, double w) {
        GL41.glProgramUniform4d(program, location, x, y, z, w);
    }

    @Override
    public void glProgramUniform1iv(int program, int location, IntBuffer value) {
        GL41.glProgramUniform1iv(program, location, value);
    }

    @Override
    public void glProgramUniform2iv(int program, int location, IntBuffer value) {
        GL41.glProgramUniform2iv(program, location, value);
    }

    @Override
    public void glProgramUniform3iv(int program, int location, IntBuffer value) {
        GL41.glProgramUniform3iv(program, location, value);
    }

    @Override
    public void glProgramUniform4iv(int program, int location, IntBuffer value) {
        GL41.glProgramUniform4iv(program, location, value);
    }

    @Override
    public void glProgramUniform1uiv(int program, int location, IntBuffer value) {
        GL41.glProgramUniform1uiv(program, location, value);
    }

    @Override
    public void glProgramUniform2uiv(int program, int location, IntBuffer value) {
        GL41.glProgramUniform2uiv(program, location, value);
    }

    @Override
    public void glProgramUniform3uiv(int program, int location, IntBuffer value) {
        GL41.glProgramUniform3uiv(program, location, value);
    }

    @Override
    public void glProgramUniform4uiv(int program, int location, IntBuffer value) {
        GL41.glProgramUniform4uiv(program, location, value);
    }

    @Override
    public void glProgramUniform1fv(int program, int location, FloatBuffer value) {
        GL41.glProgramUniform1fv(program, location, value);
    }

    @Override
    public void glProgramUniform2fv(int program, int location, FloatBuffer value) {
        GL41.glProgramUniform2fv(program, location, value);
    }

    @Override
    public void glProgramUniform3fv(int program, int location, FloatBuffer value) {
        GL41.glProgramUniform3fv(program, location, value);
    }

    @Override
    public void glProgramUniform4fv(int program, int location, FloatBuffer value) {
        GL41.glProgramUniform4fv(program, location, value);
    }

    @Override
    public void glProgramUniform1dv(int program, int location, DoubleBuffer value) {
        GL41.glProgramUniform1dv(program, location, value);
    }

    @Override
    public void glProgramUniform2dv(int program, int location, DoubleBuffer value) {
        GL41.glProgramUniform2dv(program, location, value);
    }

    @Override
    public void glProgramUniform3dv(int program, int location, DoubleBuffer value) {
        GL41.glProgramUniform3dv(program, location, value);
    }

    @Override
    public void glProgramUniform4dv(int program, int location, DoubleBuffer value) {
        GL41.glProgramUniform4dv(program, location, value);
    }

    @Override
    public void glProgramUniformMatrix2fv(int program, int location, boolean transpose, FloatBuffer value) {
        GL41.glProgramUniformMatrix2fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3fv(int program, int location, boolean transpose, FloatBuffer value) {
        GL41.glProgramUniformMatrix3fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4fv(int program, int location, boolean transpose, FloatBuffer value) {
        GL41.glProgramUniformMatrix4fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix2dv(int program, int location, boolean transpose, DoubleBuffer value) {
        GL41.glProgramUniformMatrix2dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3dv(int program, int location, boolean transpose, DoubleBuffer value) {
        GL41.glProgramUniformMatrix3dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4dv(int program, int location, boolean transpose, DoubleBuffer value) {
        GL41.glProgramUniformMatrix4dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix2x3fv(int program, int location, boolean transpose, FloatBuffer value) {
        GL41.glProgramUniformMatrix2x3fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3x2fv(int program, int location, boolean transpose, FloatBuffer value) {
        GL41.glProgramUniformMatrix3x2fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix2x4fv(int program, int location, boolean transpose, FloatBuffer value) {
        GL41.glProgramUniformMatrix2x4fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4x2fv(int program, int location, boolean transpose, FloatBuffer value) {
        GL41.glProgramUniformMatrix4x2fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3x4fv(int program, int location, boolean transpose, FloatBuffer value) {
        GL41.glProgramUniformMatrix3x4fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4x3fv(int program, int location, boolean transpose, FloatBuffer value) {
        GL41.glProgramUniformMatrix4x3fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix2x3dv(int program, int location, boolean transpose, DoubleBuffer value) {
        GL41.glProgramUniformMatrix2x3dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3x2dv(int program, int location, boolean transpose, DoubleBuffer value) {
        GL41.glProgramUniformMatrix3x2dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix2x4dv(int program, int location, boolean transpose, DoubleBuffer value) {
        GL41.glProgramUniformMatrix2x4dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4x2dv(int program, int location, boolean transpose, DoubleBuffer value) {
        GL41.glProgramUniformMatrix4x2dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3x4dv(int program, int location, boolean transpose, DoubleBuffer value) {
        GL41.glProgramUniformMatrix3x4dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4x3dv(int program, int location, boolean transpose, DoubleBuffer value) {
        GL41.glProgramUniformMatrix4x3dv(program, location, transpose, value);
    }

    @Override
    public void glValidateProgramPipeline(int pipeline) {
        GL41.glValidateProgramPipeline(pipeline);
    }

    @Override
    public void glGetProgramPipelineInfoLog(int pipeline, IntBuffer length, ByteBuffer infoLog) {
        GL41.glGetProgramPipelineInfoLog(pipeline, length, infoLog);
    }

    @Override
    public String glGetProgramPipelineInfoLog(int pipeline, int bufSize) {
        return GL41.glGetProgramPipelineInfoLog(pipeline, bufSize);
    }

    @Override
    public String glGetProgramPipelineInfoLog(int pipeline) {
        return GL41.glGetProgramPipelineInfoLog(pipeline);
    }

    @Override
    public void glVertexAttribL1d(int index, double x) {
        GL41.glVertexAttribL1d(index, x);
    }

    @Override
    public void glVertexAttribL2d(int index, double x, double y) {
        GL41.glVertexAttribL2d(index, x, y);
    }

    @Override
    public void glVertexAttribL3d(int index, double x, double y, double z) {
        GL41.glVertexAttribL3d(index, x, y, z);
    }

    @Override
    public void glVertexAttribL4d(int index, double x, double y, double z, double w) {
        GL41.glVertexAttribL4d(index, x, y, z, w);
    }

    @Override
    public void glVertexAttribL1dv(int index, DoubleBuffer v) {
        GL41.glVertexAttribL1dv(index, v);
    }

    @Override
    public void glVertexAttribL2dv(int index, DoubleBuffer v) {
        GL41.glVertexAttribL2dv(index, v);
    }

    @Override
    public void glVertexAttribL3dv(int index, DoubleBuffer v) {
        GL41.glVertexAttribL3dv(index, v);
    }

    @Override
    public void glVertexAttribL4dv(int index, DoubleBuffer v) {
        GL41.glVertexAttribL4dv(index, v);
    }

    @Override
    public void glVertexAttribLPointer(int index, int size, int type, int stride, ByteBuffer pointer) {
        GL41.glVertexAttribLPointer(index, size, type, stride, pointer);
    }

    @Override
    public void glVertexAttribLPointer(int index, int size, int type, int stride, long pointer) {
        GL41.glVertexAttribLPointer(index, size, type, stride, pointer);
    }

    @Override
    public void glVertexAttribLPointer(int index, int size, int stride, DoubleBuffer pointer) {
        GL41.glVertexAttribLPointer(index, size, stride, pointer);
    }

    @Override
    public void glGetVertexAttribLdv(int index, int pname, DoubleBuffer params) {
        GL41.glGetVertexAttribLdv(index, pname, params);
    }

    @Override
    public void glViewportArrayv(int first, FloatBuffer v) {
        GL41.glViewportArrayv(first, v);
    }

    @Override
    public void glViewportIndexedf(int index, float x, float y, float w, float h) {
        GL41.glViewportIndexedf(index, x, y, w, h);
    }

    @Override
    public void glViewportIndexedfv(int index, FloatBuffer v) {
        GL41.glViewportIndexedfv(index, v);
    }

    @Override
    public void glScissorArrayv(int first, IntBuffer v) {
        GL41.glScissorArrayv(first, v);
    }

    @Override
    public void glScissorIndexed(int index, int left, int bottom, int width, int height) {
        GL41.glScissorIndexed(index, left, bottom, width, height);
    }

    @Override
    public void glScissorIndexedv(int index, IntBuffer v) {
        GL41.glScissorIndexedv(index, v);
    }

    @Override
    public void glDepthRangeArrayv(int first, DoubleBuffer v) {
        GL41.glDepthRangeArrayv(first, v);
    }

    @Override
    public void glDepthRangeIndexed(int index, double zNear, double zFar) {
        GL41.glDepthRangeIndexed(index, zNear, zFar);
    }

    @Override
    public void glGetFloati_v(int target, int index, FloatBuffer data) {
        GL41.glGetFloati_v(target, index, data);
    }

    @Override
    public float glGetFloati(int target, int index) {
        return GL41.glGetFloati(target, index);
    }

    @Override
    public void glGetDoublei_v(int target, int index, DoubleBuffer data) {
        GL41.glGetDoublei_v(target, index, data);
    }

    @Override
    public double glGetDoublei(int target, int index) {
        return GL41.glGetDoublei(target, index);
    }

    @Override
    public void glShaderBinary(int[] shaders, int binaryformat, ByteBuffer binary) {
        GL41.glShaderBinary(shaders, binaryformat, binary);
    }

    @Override
    public void glGetShaderPrecisionFormat(int shadertype, int precisiontype, int[] range, int[] precision) {
        GL41.glGetShaderPrecisionFormat(shadertype, precisiontype, range, precision);
    }

    @Override
    public void glGetProgramBinary(int program, int[] length, int[] binaryFormat, ByteBuffer binary) {
        GL41.glGetProgramBinary(program, length, binaryFormat, binary);
    }

    @Override
    public void glDeleteProgramPipelines(int[] pipelines) {
        GL41.glDeleteProgramPipelines(pipelines);
    }

    @Override
    public void glGenProgramPipelines(int[] pipelines) {
        GL41.glGenProgramPipelines(pipelines);
    }

    @Override
    public void glGetProgramPipelineiv(int pipeline, int pname, int[] params) {
        GL41.glGetProgramPipelineiv(pipeline, pname, params);
    }

    @Override
    public void glProgramUniform1iv(int program, int location, int[] value) {
        GL41.glProgramUniform1iv(program, location, value);
    }

    @Override
    public void glProgramUniform2iv(int program, int location, int[] value) {
        GL41.glProgramUniform2iv(program, location, value);
    }

    @Override
    public void glProgramUniform3iv(int program, int location, int[] value) {
        GL41.glProgramUniform3iv(program, location, value);
    }

    @Override
    public void glProgramUniform4iv(int program, int location, int[] value) {
        GL41.glProgramUniform4iv(program, location, value);
    }

    @Override
    public void glProgramUniform1uiv(int program, int location, int[] value) {
        GL41.glProgramUniform1uiv(program, location, value);
    }

    @Override
    public void glProgramUniform2uiv(int program, int location, int[] value) {
        GL41.glProgramUniform2uiv(program, location, value);
    }

    @Override
    public void glProgramUniform3uiv(int program, int location, int[] value) {
        GL41.glProgramUniform3uiv(program, location, value);
    }

    @Override
    public void glProgramUniform4uiv(int program, int location, int[] value) {
        GL41.glProgramUniform4uiv(program, location, value);
    }

    @Override
    public void glProgramUniform1fv(int program, int location, float[] value) {
        GL41.glProgramUniform1fv(program, location, value);
    }

    @Override
    public void glProgramUniform2fv(int program, int location, float[] value) {
        GL41.glProgramUniform2fv(program, location, value);
    }

    @Override
    public void glProgramUniform3fv(int program, int location, float[] value) {
        GL41.glProgramUniform3fv(program, location, value);
    }

    @Override
    public void glProgramUniform4fv(int program, int location, float[] value) {
        GL41.glProgramUniform4fv(program, location, value);
    }

    @Override
    public void glProgramUniform1dv(int program, int location, double[] value) {
        GL41.glProgramUniform1dv(program, location, value);
    }

    @Override
    public void glProgramUniform2dv(int program, int location, double[] value) {
        GL41.glProgramUniform2dv(program, location, value);
    }

    @Override
    public void glProgramUniform3dv(int program, int location, double[] value) {
        GL41.glProgramUniform3dv(program, location, value);
    }

    @Override
    public void glProgramUniform4dv(int program, int location, double[] value) {
        GL41.glProgramUniform4dv(program, location, value);
    }

    @Override
    public void glProgramUniformMatrix2fv(int program, int location, boolean transpose, float[] value) {
        GL41.glProgramUniformMatrix2fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3fv(int program, int location, boolean transpose, float[] value) {
        GL41.glProgramUniformMatrix3fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4fv(int program, int location, boolean transpose, float[] value) {
        GL41.glProgramUniformMatrix4fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix2dv(int program, int location, boolean transpose, double[] value) {
        GL41.glProgramUniformMatrix2dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3dv(int program, int location, boolean transpose, double[] value) {
        GL41.glProgramUniformMatrix3dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4dv(int program, int location, boolean transpose, double[] value) {
        GL41.glProgramUniformMatrix4dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix2x3fv(int program, int location, boolean transpose, float[] value) {
        GL41.glProgramUniformMatrix2x3fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3x2fv(int program, int location, boolean transpose, float[] value) {
        GL41.glProgramUniformMatrix3x2fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix2x4fv(int program, int location, boolean transpose, float[] value) {
        GL41.glProgramUniformMatrix2x4fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4x2fv(int program, int location, boolean transpose, float[] value) {
        GL41.glProgramUniformMatrix4x2fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3x4fv(int program, int location, boolean transpose, float[] value) {
        GL41.glProgramUniformMatrix3x4fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4x3fv(int program, int location, boolean transpose, float[] value) {
        GL41.glProgramUniformMatrix4x3fv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix2x3dv(int program, int location, boolean transpose, double[] value) {
        GL41.glProgramUniformMatrix2x3dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3x2dv(int program, int location, boolean transpose, double[] value) {
        GL41.glProgramUniformMatrix3x2dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix2x4dv(int program, int location, boolean transpose, double[] value) {
        GL41.glProgramUniformMatrix2x4dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4x2dv(int program, int location, boolean transpose, double[] value) {
        GL41.glProgramUniformMatrix4x2dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3x4dv(int program, int location, boolean transpose, double[] value) {
        GL41.glProgramUniformMatrix3x4dv(program, location, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4x3dv(int program, int location, boolean transpose, double[] value) {
        GL41.glProgramUniformMatrix4x3dv(program, location, transpose, value);
    }

    @Override
    public void glGetProgramPipelineInfoLog(int pipeline, int[] length, ByteBuffer infoLog) {
        GL41.glGetProgramPipelineInfoLog(pipeline, length, infoLog);
    }

    @Override
    public void glVertexAttribL1dv(int index, double[] v) {
        GL41.glVertexAttribL1dv(index, v);
    }

    @Override
    public void glVertexAttribL2dv(int index, double[] v) {
        GL41.glVertexAttribL2dv(index, v);
    }

    @Override
    public void glVertexAttribL3dv(int index, double[] v) {
        GL41.glVertexAttribL3dv(index, v);
    }

    @Override
    public void glVertexAttribL4dv(int index, double[] v) {
        GL41.glVertexAttribL4dv(index, v);
    }

    @Override
    public void glGetVertexAttribLdv(int index, int pname, double[] params) {
        GL41.glGetVertexAttribLdv(index, pname, params);
    }

    @Override
    public void glViewportArrayv(int first, float[] v) {
        GL41.glViewportArrayv(first, v);
    }

    @Override
    public void glViewportIndexedfv(int index, float[] v) {
        GL41.glViewportIndexedfv(index, v);
    }

    @Override
    public void glScissorArrayv(int first, int[] v) {
        GL41.glScissorArrayv(first, v);
    }

    @Override
    public void glScissorIndexedv(int index, int[] v) {
        GL41.glScissorIndexedv(index, v);
    }

    @Override
    public void glDepthRangeArrayv(int first, double[] v) {
        GL41.glDepthRangeArrayv(first, v);
    }

    @Override
    public void glGetFloati_v(int target, int index, float[] data) {
        GL41.glGetFloati_v(target, index, data);
    }

    @Override
    public void glGetDoublei_v(int target, int index, double[] data) {
        GL41.glGetDoublei_v(target, index, data);
    }

}