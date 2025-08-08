package jpize.android.opengl;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLES31;
import jpize.opengl.gl.GL41I;
import spatialmath.geometry.Recti;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class AndroidGL41 extends AndroidGL40 implements GL41I {

    public static final AndroidGL41 INSTANCE = new AndroidGL41();

    protected AndroidGL41() { }


    @Override
    public void glReleaseShaderCompiler() {
        GLES20.glReleaseShaderCompiler();
    }

    @Override
    public void glShaderBinary(IntBuffer shaders, int binaryformat, ByteBuffer binary) {
        GLES20.glShaderBinary(shaders.limit(), shaders, binaryformat, binary, binary.limit());
    }

    @Override
    public void glGetShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range, IntBuffer precision) {
        GLES20.glGetShaderPrecisionFormat(shadertype, precisiontype, range, precision);
    }

    @Override
    public int glGetShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range) {
        GLES20.glGetShaderPrecisionFormat(shadertype, precisiontype, range, tmp_int_buf);
        return tmp_int_buf.get(0);
    }

    @Override
    public void glDepthRangef(float zNear, float zFar) {
        GLES20.glDepthRangef(zNear, zFar);
    }

    @Override
    public void glClearDepthf(float depth) {
        GLES20.glClearDepthf(depth);
    }

    @Override
    public void glGetProgramBinary(int program, IntBuffer length, IntBuffer binaryFormat, ByteBuffer binary) {
        GLES30.glGetProgramBinary(program, binary.limit(), length, binaryFormat, binary);
    }

    @Override
    public void glProgramBinary(int program, int binaryFormat, ByteBuffer binary) {
        GLES30.glProgramBinary(program, binaryFormat, binary, binary.limit());
    }

    @Override
    public void glProgramParameteri(int program, int pname, int value) {
        GLES30.glProgramParameteri(program, pname, value);
    }

    @Override
    public void glUseProgramStages(int pipeline, int stages, int program) {
        GLES31.glUseProgramStages(pipeline, stages, program);
    }

    @Override
    public void glActiveShaderProgram(int pipeline, int program) {
        GLES31.glActiveShaderProgram(pipeline, program);
    }

    @Override
    public int glCreateShaderProgramv(int type, CharSequence... strings) {
        return GLES31.glCreateShaderProgramv(type, createStringArray(strings));
    }

    @Override
    public int glCreateShaderProgramv(int type, CharSequence string) {
        return GLES31.glCreateShaderProgramv(type, createStringArray(string));
    }

    @Override
    public void glBindProgramPipeline(int pipeline) {
        GLES31.glBindProgramPipeline(pipeline);
    }

    @Override
    public void glDeleteProgramPipelines(IntBuffer pipelines) {
        GLES31.glDeleteProgramPipelines(pipelines.limit(), pipelines);
    }

    @Override
    public void glDeleteProgramPipelines(int pipeline) {
        tmp_int[0] = pipeline;
        GLES31.glDeleteProgramPipelines(1, tmp_int, 0);
    }

    @Override
    public void glGenProgramPipelines(IntBuffer pipelines) {
        GLES31.glGenProgramPipelines(pipelines.limit(), pipelines);
    }

    @Override
    public int glGenProgramPipelines() {
        GLES31.glGenProgramPipelines(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public boolean glIsProgramPipeline(int pipeline) {
        return GLES31.glIsProgramPipeline(pipeline);
    }

    @Override
    public void glGetProgramPipelineiv(int pipeline, int pname, IntBuffer params) {
        GLES31.glGetProgramPipelineiv(pipeline, pname, params);
    }

    @Override
    public int glGetProgramPipelinei(int pipeline, int pname) {
        GLES31.glGetProgramPipelineiv(pipeline, pname, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glProgramUniform1i(int program, int location, int x) {
        GLES31.glProgramUniform1i(program, location, x);
    }

    @Override
    public void glProgramUniform2i(int program, int location, int x, int y) {
        GLES31.glProgramUniform2i(program, location, x, y);
    }

    @Override
    public void glProgramUniform3i(int program, int location, int x, int y, int z) {
        GLES31.glProgramUniform3i(program, location, x, y, z);
    }

    @Override
    public void glProgramUniform4i(int program, int location, int x, int y, int z, int w) {
        GLES31.glProgramUniform4i(program, location, x, y, z, w);
    }

    @Override
    public void glProgramUniform1ui(int program, int location, int x) {
        GLES31.glProgramUniform1ui(program, location, x);
    }

    @Override
    public void glProgramUniform2ui(int program, int location, int x, int y) {
        GLES31.glProgramUniform2ui(program, location, x, y);
    }

    @Override
    public void glProgramUniform3ui(int program, int location, int x, int y, int z) {
        GLES31.glProgramUniform3ui(program, location, x, y, z);
    }

    @Override
    public void glProgramUniform4ui(int program, int location, int x, int y, int z, int w) {
        GLES31.glProgramUniform4ui(program, location, x, y, z, w);
    }

    @Override
    public void glProgramUniform1f(int program, int location, float x) {
        GLES31.glProgramUniform1f(program, location, x);
    }

    @Override
    public void glProgramUniform2f(int program, int location, float x, float y) {
        GLES31.glProgramUniform2f(program, location, x, y);
    }

    @Override
    public void glProgramUniform3f(int program, int location, float x, float y, float z) {
        GLES31.glProgramUniform3f(program, location, x, y, z);
    }

    @Override
    public void glProgramUniform4f(int program, int location, float x, float y, float z, float w) {
        GLES31.glProgramUniform4f(program, location, x, y, z, w);
    }

    @Override
    public void glProgramUniform1d(int program, int location, double x) {
        GLES31.glProgramUniform1f(program, location, (float) x);
    }

    @Override
    public void glProgramUniform2d(int program, int location, double x, double y) {
        GLES31.glProgramUniform2f(program, location, (float) x, (float) y);
    }

    @Override
    public void glProgramUniform3d(int program, int location, double x, double y, double z) {
        GLES31.glProgramUniform3f(program, location, (float) x, (float) y, (float) z);
    }

    @Override
    public void glProgramUniform4d(int program, int location, double x, double y, double z, double w) {
        GLES31.glProgramUniform4f(program, location, (float) x, (float) y, (float) z, (float) w);
    }

    @Override
    public void glProgramUniform1iv(int program, int location, IntBuffer value) {
        GLES31.glProgramUniform1iv(program, location, value.limit(), value);
    }

    @Override
    public void glProgramUniform2iv(int program, int location, IntBuffer value) {
        GLES31.glProgramUniform2iv(program, location, value.limit() / 2, value);
    }

    @Override
    public void glProgramUniform3iv(int program, int location, IntBuffer value) {
        GLES31.glProgramUniform3iv(program, location, value.limit() / 3, value);
    }

    @Override
    public void glProgramUniform4iv(int program, int location, IntBuffer value) {
        GLES31.glProgramUniform4iv(program, location, value.limit() / 4, value);
    }

    @Override
    public void glProgramUniform1uiv(int program, int location, IntBuffer value) {
        GLES31.glProgramUniform1uiv(program, location, value.limit(), value);
    }

    @Override
    public void glProgramUniform2uiv(int program, int location, IntBuffer value) {
        GLES31.glProgramUniform2uiv(program, location, value.limit() / 2, value);
    }

    @Override
    public void glProgramUniform3uiv(int program, int location, IntBuffer value) {
        GLES31.glProgramUniform3uiv(program, location, value.limit() / 3, value);
    }

    @Override
    public void glProgramUniform4uiv(int program, int location, IntBuffer value) {
        GLES31.glProgramUniform4uiv(program, location, value.limit() / 4, value);
    }

    @Override
    public void glProgramUniform1fv(int program, int location, FloatBuffer value) {
        GLES31.glProgramUniform1fv(program, location, value.limit(), value);
    }

    @Override
    public void glProgramUniform2fv(int program, int location, FloatBuffer value) {
        GLES31.glProgramUniform2fv(program, location, value.limit() / 2, value);
    }

    @Override
    public void glProgramUniform3fv(int program, int location, FloatBuffer value) {
        GLES31.glProgramUniform3fv(program, location, value.limit() / 3, value);
    }

    @Override
    public void glProgramUniform4fv(int program, int location, FloatBuffer value) {
        GLES31.glProgramUniform4fv(program, location, value.limit() / 4, value);
    }

    @Override
    public void glProgramUniform1dv(int program, int location, DoubleBuffer value) {
        GLES31.glProgramUniform1fv(program, location, value.limit(), toFloatBuffer(value));
    }

    @Override
    public void glProgramUniform2dv(int program, int location, DoubleBuffer value) {
        GLES31.glProgramUniform2fv(program, location, value.limit() / 2, toFloatBuffer(value));
    }

    @Override
    public void glProgramUniform3dv(int program, int location, DoubleBuffer value) {
        GLES31.glProgramUniform3fv(program, location, value.limit() / 3, toFloatBuffer(value));
    }

    @Override
    public void glProgramUniform4dv(int program, int location, DoubleBuffer value) {
        GLES31.glProgramUniform4fv(program, location, value.limit() / 4, toFloatBuffer(value));
    }

    @Override
    public void glProgramUniformMatrix2fv(int program, int location, boolean transpose, FloatBuffer value) {
        GLES31.glProgramUniformMatrix2fv(program, location, value.limit() / 2, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3fv(int program, int location, boolean transpose, FloatBuffer value) {
        GLES31.glProgramUniformMatrix3fv(program, location, value.limit() / 3, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4fv(int program, int location, boolean transpose, FloatBuffer value) {
        GLES31.glProgramUniformMatrix4fv(program, location, value.limit() / 4, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix2dv(int program, int location, boolean transpose, DoubleBuffer value) {
        GLES31.glProgramUniformMatrix2fv(program, location, value.limit() / 2, transpose, toFloatBuffer(value));
    }

    @Override
    public void glProgramUniformMatrix3dv(int program, int location, boolean transpose, DoubleBuffer value) {
        GLES31.glProgramUniformMatrix3fv(program, location, value.limit() / 3, transpose, toFloatBuffer(value));
    }

    @Override
    public void glProgramUniformMatrix4dv(int program, int location, boolean transpose, DoubleBuffer value) {
        GLES31.glProgramUniformMatrix4fv(program, location, value.limit() / 4, transpose, toFloatBuffer(value));
    }

    @Override
    public void glProgramUniformMatrix2x3fv(int program, int location, boolean transpose, FloatBuffer value) {
        if(value.limit() % 6 != 0)
            throw new IllegalArgumentException("FloatBuffer size must be a multiple of 6 for mat2x3");
        GLES31.glProgramUniformMatrix2x3fv(program, location, value.limit() / 6, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3x2fv(int program, int location, boolean transpose, FloatBuffer value) {
        if(value.limit() % 6 != 0)
            throw new IllegalArgumentException("FloatBuffer size must be a multiple of 6 for mat3x2");
        GLES31.glProgramUniformMatrix3x2fv(program, location, value.limit() / 6, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix2x4fv(int program, int location, boolean transpose, FloatBuffer value) {
        if(value.limit() % 8 != 0)
            throw new IllegalArgumentException("FloatBuffer size must be a multiple of 8 for mat2x4");
        GLES31.glProgramUniformMatrix2x4fv(program, location, value.limit() / 8, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4x2fv(int program, int location, boolean transpose, FloatBuffer value) {
        if(value.limit() % 8 != 0)
            throw new IllegalArgumentException("FloatBuffer size must be a multiple of 8 for mat4x2");
        GLES31.glProgramUniformMatrix4x2fv(program, location, value.limit() / 8, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix3x4fv(int program, int location, boolean transpose, FloatBuffer value) {
        if(value.limit() % 12 != 0)
            throw new IllegalArgumentException("FloatBuffer size must be a multiple of 12 for mat3x4");
        GLES31.glProgramUniformMatrix3x4fv(program, location, value.limit() / 12, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix4x3fv(int program, int location, boolean transpose, FloatBuffer value) {
        if(value.limit() % 12 != 0)
            throw new IllegalArgumentException("FloatBuffer size must be a multiple of 12 for mat4x3");
        GLES31.glProgramUniformMatrix4x3fv(program, location, value.limit() / 12, transpose, value);
    }

    @Override
    public void glProgramUniformMatrix2x3dv(int program, int location, boolean transpose, DoubleBuffer value) {
        if(value.limit() % 6 != 0)
            throw new IllegalArgumentException("DoubleBuffer size must be a multiple of 6 for mat2x3");
        GLES31.glProgramUniformMatrix2x3fv(program, location, value.limit() / 6, transpose, toFloatBuffer(value));
    }

    @Override
    public void glProgramUniformMatrix3x2dv(int program, int location, boolean transpose, DoubleBuffer value) {
        if(value.limit() % 6 != 0)
            throw new IllegalArgumentException("DoubleBuffer size must be a multiple of 6 for mat3x2");
        GLES31.glProgramUniformMatrix3x2fv(program, location, value.limit() / 6, transpose, toFloatBuffer(value));
    }

    @Override
    public void glProgramUniformMatrix2x4dv(int program, int location, boolean transpose, DoubleBuffer value) {
        if(value.limit() % 8 != 0)
            throw new IllegalArgumentException("DoubleBuffer size must be a multiple of 8 for mat2x4");
        GLES31.glProgramUniformMatrix2x4fv(program, location, value.limit() / 8, transpose, toFloatBuffer(value));
    }

    @Override
    public void glProgramUniformMatrix4x2dv(int program, int location, boolean transpose, DoubleBuffer value) {
        if(value.limit() % 8 != 0)
            throw new IllegalArgumentException("DoubleBuffer size must be a multiple of 8 for mat4x2");
        GLES31.glProgramUniformMatrix4x2fv(program, location, value.limit() / 8, transpose, toFloatBuffer(value));
    }

    @Override
    public void glProgramUniformMatrix3x4dv(int program, int location, boolean transpose, DoubleBuffer value) {
        if(value.limit() % 12 != 0)
            throw new IllegalArgumentException("DoubleBuffer size must be a multiple of 12 for mat3x4");
        GLES31.glProgramUniformMatrix3x4fv(program, location, value.limit() / 12, transpose, toFloatBuffer(value));
    }

    @Override
    public void glProgramUniformMatrix4x3dv(int program, int location, boolean transpose, DoubleBuffer value) {
        if(value.limit() % 12 != 0)
            throw new IllegalArgumentException("DoubleBuffer size must be a multiple of 12 for mat4x3");
        GLES31.glProgramUniformMatrix4x3fv(program, location, value.limit() / 12, transpose, toFloatBuffer(value));
    }

    @Override
    public void glValidateProgramPipeline(int pipeline) {
        GLES31.glValidateProgramPipeline(pipeline);
    }

    @Override
    public void glGetProgramPipelineInfoLog(int pipeline, IntBuffer length, ByteBuffer infoLog) {
        final String log = GLES31.glGetProgramPipelineInfoLog(pipeline);
        setBufferString(log, length, infoLog);
    }

    @Override
    public String glGetProgramPipelineInfoLog(int pipeline, int bufSize) {
        String log = GLES31.glGetProgramPipelineInfoLog(pipeline);
        if(log == null || log.isEmpty())
            return "";

        final int maxLength = Math.max(0, bufSize - 1);
        if(log.length() > maxLength)
            log = log.substring(0, maxLength);
        return log;
    }

    @Override
    public String glGetProgramPipelineInfoLog(int pipeline) {
        return GLES31.glGetProgramPipelineInfoLog(pipeline);
    }

    @Override
    public void glVertexAttribL1d(int index, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribL2d(int index, double x, double y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribL3d(int index, double x, double y, double z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribL4d(int index, double x, double y, double z, double w) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribL1dv(int index, DoubleBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribL2dv(int index, DoubleBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribL3dv(int index, DoubleBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribL4dv(int index, DoubleBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribLPointer(int index, int size, int type, int stride, ByteBuffer pointer) {
        GLES30.glVertexAttribIPointer(index, size, type, stride, pointer);
    }

    @Override
    public void glVertexAttribLPointer(int index, int size, int type, int stride, long pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribLPointer(int index, int size, int stride, DoubleBuffer pointer) {
        GLES30.glVertexAttribIPointer(index, size, GLES20.GL_FLOAT, stride, toFloatBuffer(pointer));
    }

    @Override
    public void glGetVertexAttribLdv(int index, int pname, DoubleBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glViewportArrayv(int first, FloatBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glViewportIndexedf(int index, float x, float y, float w, float h) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glViewportIndexedfv(int index, FloatBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glScissorArrayv(int first, IntBuffer v) {
        final int limit = v.limit();
        if(limit % 4 != 0)
            throw new IllegalArgumentException("Scissor buffer limit must be a multiple of 4");

        int i = 0;

        final Recti scissor = tmp_rect.set(
            v.get(i++),
            v.get(i++),
            v.get(i++),
            v.get(i++)
        );

        while(i < limit) {
            final int x = v.get(i++);
            final int y = v.get(i++);
            final int width = v.get(i++);
            final int height = v.get(i++);
            final int x2 = (x + width);
            final int y2 = (y + height);

            final int scissorX = scissor.x;
            final int scissorY = scissor.y;
            final int scissorX2 = (scissorX + scissor.width);
            final int scissorY2 = (scissorY + scissor.height);

            scissor.x = Math.max(Math.min(x, scissorX2), scissorX);
            scissor.y = Math.max(Math.min(y, scissorY2), scissorY);
            scissor.width = Math.max(0, Math.min(Math.min(width, x2 - scissorX), Math.min(scissorX2, x2) - x));
            scissor.height = Math.max(0, Math.min(Math.min(height, y2 - scissorY), Math.min(scissorY2, y2) - y));
        }

        super.glScissor(scissor.x, scissor.y, scissor.width, scissor.height);
    }

    @Override
    public void glScissorIndexed(int index, int left, int bottom, int width, int height) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glScissorIndexedv(int index, IntBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDepthRangeArrayv(int first, DoubleBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDepthRangeIndexed(int index, double zNear, double zFar) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetFloati_v(int target, int index, FloatBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float glGetFloati(int target, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetDoublei_v(int target, int index, DoubleBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double glGetDoublei(int target, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glShaderBinary(int[] shaders, int binaryformat, ByteBuffer binary) {
        GLES30.glShaderBinary(shaders.length, shaders, 0, binaryformat, binary, binary.limit());
    }

    @Override
    public void glGetShaderPrecisionFormat(int shadertype, int precisiontype, int[] range, int[] precision) {
        GLES30.glGetShaderPrecisionFormat(shadertype, precisiontype, range, 0, precision, 0);
    }

    @Override
    public void glGetProgramBinary(int program, int[] length, int[] binaryFormat, ByteBuffer binary) {
        GLES30.glGetProgramBinary(program, binary.limit(), length, 0, binaryFormat, 0, binary);
    }

    @Override
    public void glDeleteProgramPipelines(int[] pipelines) {
        GLES31.glDeleteProgramPipelines(pipelines.length, pipelines, 0);
    }

    @Override
    public void glGenProgramPipelines(int[] pipelines) {
        GLES31.glGenProgramPipelines(pipelines.length, pipelines, 0);
    }

    @Override
    public void glGetProgramPipelineiv(int pipeline, int pname, int[] params) {
        GLES31.glGetProgramPipelineiv(pipeline, pname, params, 0);
    }

    @Override
    public void glProgramUniform1iv(int program, int location, int[] value) {
        GLES31.glProgramUniform1iv(program, location, value.length, value, 0);
    }

    @Override
    public void glProgramUniform2iv(int program, int location, int[] value) {
        GLES31.glProgramUniform2iv(program, location, value.length / 2, value, 0);
    }

    @Override
    public void glProgramUniform3iv(int program, int location, int[] value) {
        GLES31.glProgramUniform3iv(program, location, value.length / 3, value, 0);
    }

    @Override
    public void glProgramUniform4iv(int program, int location, int[] value) {
        GLES31.glProgramUniform4iv(program, location, value.length / 4, value, 0);
    }

    @Override
    public void glProgramUniform1uiv(int program, int location, int[] value) {
        GLES31.glProgramUniform1uiv(program, location, value.length, value, 0);
    }

    @Override
    public void glProgramUniform2uiv(int program, int location, int[] value) {
        GLES31.glProgramUniform2uiv(program, location, value.length / 2, value, 0);
    }

    @Override
    public void glProgramUniform3uiv(int program, int location, int[] value) {
        GLES31.glProgramUniform3uiv(program, location, value.length / 3, value, 0);
    }

    @Override
    public void glProgramUniform4uiv(int program, int location, int[] value) {
        GLES31.glProgramUniform4uiv(program, location, value.length / 4, value, 0);
    }

    @Override
    public void glProgramUniform1fv(int program, int location, float[] value) {
        GLES31.glProgramUniform1fv(program, location, value.length, value, 0);
    }

    @Override
    public void glProgramUniform2fv(int program, int location, float[] value) {
        GLES31.glProgramUniform2fv(program, location, value.length / 2, value, 0);
    }

    @Override
    public void glProgramUniform3fv(int program, int location, float[] value) {
        GLES31.glProgramUniform3fv(program, location, value.length / 3, value, 0);
    }

    @Override
    public void glProgramUniform4fv(int program, int location, float[] value) {
        GLES31.glProgramUniform4fv(program, location, value.length / 4, value, 0);
    }

    @Override
    public void glProgramUniform1dv(int program, int location, double[] value) {
        GLES31.glProgramUniform1fv(program, location, value.length, toFloatArray(value), 0);
    }

    @Override
    public void glProgramUniform2dv(int program, int location, double[] value) {
        GLES31.glProgramUniform2fv(program, location, value.length / 2, toFloatArray(value), 0);
    }

    @Override
    public void glProgramUniform3dv(int program, int location, double[] value) {
        GLES31.glProgramUniform3fv(program, location, value.length / 3, toFloatArray(value), 0);
    }

    @Override
    public void glProgramUniform4dv(int program, int location, double[] value) {
        GLES31.glProgramUniform4fv(program, location, value.length / 4, toFloatArray(value), 0);
    }

    @Override
    public void glProgramUniformMatrix2fv(int program, int location, boolean transpose, float[] value) {
        if(value.length % 4 != 0)
            throw new IllegalArgumentException("Float array size must be a multiple of 4 for mat2");
        GLES31.glProgramUniformMatrix2fv(program, location, value.length / 4, transpose, value, 0);
    }

    @Override
    public void glProgramUniformMatrix3fv(int program, int location, boolean transpose, float[] value) {
        if(value.length % 9 != 0)
            throw new IllegalArgumentException("Float array size must be a multiple of 9 for mat3");
        GLES31.glProgramUniformMatrix3fv(program, location, value.length / 9, transpose, value, 0);
    }

    @Override
    public void glProgramUniformMatrix4fv(int program, int location, boolean transpose, float[] value) {
        if(value.length % 16 != 0)
            throw new IllegalArgumentException("Float array size must be a multiple of 16 for mat4");
        GLES31.glProgramUniformMatrix4fv(program, location, value.length / 16, transpose, value, 0);
    }

    @Override
    public void glProgramUniformMatrix2dv(int program, int location, boolean transpose, double[] value) {
        if(value.length % 4 != 0)
            throw new IllegalArgumentException("Double array size must be a multiple of 4 for mat2");
        GLES31.glProgramUniformMatrix2fv(program, location, value.length / 4, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glProgramUniformMatrix3dv(int program, int location, boolean transpose, double[] value) {
        if(value.length % 9 != 0)
            throw new IllegalArgumentException("Double array size must be a multiple of 9 for mat3");
        GLES31.glProgramUniformMatrix3fv(program, location, value.length / 9, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glProgramUniformMatrix4dv(int program, int location, boolean transpose, double[] value) {
        if(value.length % 16 != 0)
            throw new IllegalArgumentException("Double array size must be a multiple of 16 for mat4");
        GLES31.glProgramUniformMatrix4fv(program, location, value.length / 16, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glProgramUniformMatrix2x3fv(int program, int location, boolean transpose, float[] value) {
        if(value.length % 6 != 0)
            throw new IllegalArgumentException("Float array size must be a multiple of 6 for mat2x3");
        GLES31.glProgramUniformMatrix2x3fv(program, location, value.length / 6, transpose, value, 0);
    }

    @Override
    public void glProgramUniformMatrix3x2fv(int program, int location, boolean transpose, float[] value) {
        if(value.length % 6 != 0)
            throw new IllegalArgumentException("Float array size must be a multiple of 6 for mat3x2");
        GLES31.glProgramUniformMatrix3x2fv(program, location, value.length / 6, transpose, value, 0);
    }

    @Override
    public void glProgramUniformMatrix2x4fv(int program, int location, boolean transpose, float[] value) {
        if(value.length % 8 != 0)
            throw new IllegalArgumentException("Float array size must be a multiple of 8 for mat2x4");
        GLES31.glProgramUniformMatrix2x4fv(program, location, value.length / 8, transpose, value, 0);
    }

    @Override
    public void glProgramUniformMatrix4x2fv(int program, int location, boolean transpose, float[] value) {
        if(value.length % 8 != 0)
            throw new IllegalArgumentException("Float array size must be a multiple of 8 for mat4x2");
        GLES31.glProgramUniformMatrix4x2fv(program, location, value.length / 8, transpose, value, 0);
    }

    @Override
    public void glProgramUniformMatrix3x4fv(int program, int location, boolean transpose, float[] value) {
        if(value.length % 12 != 0)
            throw new IllegalArgumentException("Float array size must be a multiple of 12 for mat3x4");
        GLES31.glProgramUniformMatrix3x4fv(program, location, value.length / 12, transpose, value, 0);
    }

    @Override
    public void glProgramUniformMatrix4x3fv(int program, int location, boolean transpose, float[] value) {
        if(value.length % 12 != 0)
            throw new IllegalArgumentException("Float array size must be a multiple of 12 for mat4x3");
        GLES31.glProgramUniformMatrix4x3fv(program, location, value.length / 12, transpose, value, 0);
    }

    @Override
    public void glProgramUniformMatrix2x3dv(int program, int location, boolean transpose, double[] value) {
        if(value.length % 6 != 0)
            throw new IllegalArgumentException("Double array size must be a multiple of 6 for mat2x3");
        GLES31.glProgramUniformMatrix2x3fv(program, location, value.length / 6, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glProgramUniformMatrix3x2dv(int program, int location, boolean transpose, double[] value) {
        if(value.length % 6 != 0)
            throw new IllegalArgumentException("Double array size must be a multiple of 6 for mat3x2");
        GLES31.glProgramUniformMatrix3x2fv(program, location, value.length / 6, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glProgramUniformMatrix2x4dv(int program, int location, boolean transpose, double[] value) {
        if(value.length % 8 != 0)
            throw new IllegalArgumentException("Double array size must be a multiple of 8 for mat2x4");
        GLES31.glProgramUniformMatrix2x4fv(program, location, value.length / 8, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glProgramUniformMatrix4x2dv(int program, int location, boolean transpose, double[] value) {
        if(value.length % 8 != 0)
            throw new IllegalArgumentException("Double array size must be a multiple of 8 for mat4x2");
        GLES31.glProgramUniformMatrix4x2fv(program, location, value.length / 8, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glProgramUniformMatrix3x4dv(int program, int location, boolean transpose, double[] value) {
        if(value.length % 12 != 0)
            throw new IllegalArgumentException("Double array size must be a multiple of 12 for mat3x4");
        GLES31.glProgramUniformMatrix3x4fv(program, location, value.length / 12, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glProgramUniformMatrix4x3dv(int program, int location, boolean transpose, double[] value) {
        if(value.length % 12 != 0)
            throw new IllegalArgumentException("Double array size must be a multiple of 12 for mat4x3");
        GLES31.glProgramUniformMatrix4x3fv(program, location, value.length / 12, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glGetProgramPipelineInfoLog(int pipeline, int[] length, ByteBuffer infoLog) {
        final String log = GLES31.glGetProgramPipelineInfoLog(pipeline);
        setBufferString(log, length, infoLog);
    }

    @Override
    public void glVertexAttribL1dv(int index, double[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribL2dv(int index, double[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribL3dv(int index, double[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribL4dv(int index, double[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetVertexAttribLdv(int index, int pname, double[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glViewportArrayv(int first, float[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glViewportIndexedfv(int index, float[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glScissorArrayv(int first, int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glScissorIndexedv(int index, int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDepthRangeArrayv(int first, double[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetFloati_v(int target, int index, float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetDoublei_v(int target, int index, double[] data) {
        throw new UnsupportedOperationException();
    }

}