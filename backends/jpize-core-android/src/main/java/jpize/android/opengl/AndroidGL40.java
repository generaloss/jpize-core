package jpize.android.opengl;

import android.opengl.GLES31;
import android.opengl.GLES32;
import jpize.opengl.gl.GL40I;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class AndroidGL40 extends AndroidGL33 implements GL40I {

    public static final AndroidGL40 INSTANCE = new AndroidGL40();

    protected AndroidGL40() { }


    @Override
    public void glBlendEquationi(int buf, int mode) {
        GLES32.glBlendEquationi(buf, mode);
    }

    @Override
    public void glBlendEquationSeparatei(int buf, int modeRGB, int modeAlpha) {
        GLES32.glBlendEquationSeparatei(buf, modeRGB, modeAlpha);
    }

    @Override
    public void glBlendFunci(int buf, int sfactor, int dfactor) {
        GLES32.glBlendFunci(buf, sfactor, dfactor);
    }

    @Override
    public void glBlendFuncSeparatei(int buf, int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
        GLES32.glBlendFuncSeparatei(buf, srcRGB, dstRGB, srcAlpha, dstAlpha);
    }

    @Override
    public void glDrawArraysIndirect(int mode, ByteBuffer indirect) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawArraysIndirect(int mode, long indirect) {
        GLES31.glDrawArraysIndirect(mode, indirect);
    }

    @Override
    public void glDrawArraysIndirect(int mode, IntBuffer indirect) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawElementsIndirect(int mode, int type, ByteBuffer indirect) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawElementsIndirect(int mode, int type, long indirect) {
        GLES31.glDrawElementsIndirect(mode, type, indirect);
    }

    @Override
    public void glDrawElementsIndirect(int mode, int type, IntBuffer indirect) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glUniform1d(int location, double x) {
        GLES31.glUniform1f(location, (float) x);
    }

    @Override
    public void glUniform2d(int location, double x, double y) {
        GLES31.glUniform2f(location, (float) x, (float) y);
    }

    @Override
    public void glUniform3d(int location, double x, double y, double z) {
        GLES31.glUniform3f(location, (float) x, (float) y, (float) z);
    }

    @Override
    public void glUniform4d(int location, double x, double y, double z, double w) {
        GLES31.glUniform4f(location, (float) x, (float) y, (float) z, (float) w);
    }

    @Override
    public void glUniform1dv(int location, DoubleBuffer value) {
        final float[] valueArr =  new float[value.limit()];
        GLES31.glUniform1fv(location, value.limit(), valueArr, 0);
        writeToBuffer(valueArr, value);
    }

    @Override
    public void glUniform2dv(int location, DoubleBuffer value) {
        final float[] valueArr =  new float[value.limit()];
        GLES31.glUniform2fv(location, value.limit(), valueArr, 0);
        writeToBuffer(valueArr, value);
    }

    @Override
    public void glUniform3dv(int location, DoubleBuffer value) {
        final float[] valueArr =  new float[value.limit()];
        GLES31.glUniform3fv(location, value.limit(), valueArr, 0);
        writeToBuffer(valueArr, value);
    }

    @Override
    public void glUniform4dv(int location, DoubleBuffer value) {
        final float[] valueArr =  new float[value.limit()];
        GLES31.glUniform4fv(location, value.limit(), valueArr, 0);
        writeToBuffer(valueArr, value);
    }

    @Override
    public void glUniformMatrix2dv(int location, boolean transpose, DoubleBuffer value) {
        final float[] valueArr =  new float[value.limit()];
        GLES31.glUniformMatrix2fv(location, value.limit(), transpose, valueArr, 0);
        writeToBuffer(valueArr, value);
    }

    @Override
    public void glUniformMatrix3dv(int location, boolean transpose, DoubleBuffer value) {
        final float[] valueArr =  new float[value.limit()];
        GLES31.glUniformMatrix3fv(location, value.limit(), transpose, valueArr, 0);
        writeToBuffer(valueArr, value);
    }

    @Override
    public void glUniformMatrix4dv(int location, boolean transpose, DoubleBuffer value) {
        final float[] valueArr =  new float[value.limit()];
        GLES31.glUniformMatrix4fv(location, value.limit(), transpose, valueArr, 0);
        writeToBuffer(valueArr, value);
    }

    @Override
    public void glUniformMatrix2x3dv(int location, boolean transpose, DoubleBuffer value) {
        final float[] valueArr =  new float[value.limit()];
        GLES31.glUniformMatrix2x3fv(location, value.limit(), transpose, valueArr, 0);
        writeToBuffer(valueArr, value);
    }

    @Override
    public void glUniformMatrix2x4dv(int location, boolean transpose, DoubleBuffer value) {
        final float[] valueArr =  new float[value.limit()];
        GLES31.glUniformMatrix2x4fv(location, value.limit(), transpose, valueArr, 0);
        writeToBuffer(valueArr, value);
    }

    @Override
    public void glUniformMatrix3x2dv(int location, boolean transpose, DoubleBuffer value) {
        final float[] valueArr =  new float[value.limit()];
        GLES31.glUniformMatrix3x2fv(location, value.limit(), transpose, valueArr, 0);
        writeToBuffer(valueArr, value);
    }

    @Override
    public void glUniformMatrix3x4dv(int location, boolean transpose, DoubleBuffer value) {
        final float[] valueArr =  new float[value.limit()];
        GLES31.glUniformMatrix3x4fv(location, value.limit(), transpose, valueArr, 0);
        writeToBuffer(valueArr, value);
    }

    @Override
    public void glUniformMatrix4x2dv(int location, boolean transpose, DoubleBuffer value) {
        final float[] valueArr =  new float[value.limit()];
        GLES31.glUniformMatrix4x2fv(location, value.limit(), transpose, valueArr, 0);
        writeToBuffer(valueArr, value);
    }

    @Override
    public void glUniformMatrix4x3dv(int location, boolean transpose, DoubleBuffer value) {
        final float[] valueArr =  new float[value.limit()];
        GLES31.glUniformMatrix4x3fv(location, value.limit(), transpose, valueArr, 0);
        writeToBuffer(valueArr, value);
    }

    @Override
    public void glGetUniformdv(int program, int location, DoubleBuffer params) {
        final float[] paramsArr = new float[params.limit()];
        GLES31.glGetUniformfv(program, location, paramsArr, 0);
        writeToBuffer(paramsArr, params);
    }

    @Override
    public double glGetUniformd(int program, int location) {
        GLES31.glGetUniformfv(program, 1, tmp_float, 0);
        return tmp_float[0];
    }

    @Override
    public void glMinSampleShading(float value) {
        GLES32.glMinSampleShading(value);
    }

    @Override
    public int glGetSubroutineUniformLocation(int program, int shadertype, ByteBuffer name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetSubroutineUniformLocation(int program, int shadertype, CharSequence name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetSubroutineIndex(int program, int shadertype, ByteBuffer name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetSubroutineIndex(int program, int shadertype, CharSequence name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetActiveSubroutineUniformiv(int program, int shadertype, int index, int pname, IntBuffer values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetActiveSubroutineUniformi(int program, int shadertype, int index, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetActiveSubroutineUniformName(int program, int shadertype, int index, IntBuffer length, ByteBuffer name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String glGetActiveSubroutineUniformName(int program, int shadertype, int index, int bufsize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String glGetActiveSubroutineUniformName(int program, int shadertype, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetActiveSubroutineName(int program, int shadertype, int index, IntBuffer length, ByteBuffer name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String glGetActiveSubroutineName(int program, int shadertype, int index, int bufsize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String glGetActiveSubroutineName(int program, int shadertype, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glUniformSubroutinesuiv(int shadertype, IntBuffer indices) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glUniformSubroutinesui(int shadertype, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetUniformSubroutineuiv(int shadertype, int location, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetUniformSubroutineui(int shadertype, int location) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetProgramStageiv(int program, int shadertype, int pname, IntBuffer values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetProgramStagei(int program, int shadertype, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glPatchParameteri(int pname, int value) {
        GLES32.glPatchParameteri(pname, value);
    }

    @Override
    public void glPatchParameterfv(int pname, FloatBuffer values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glBindTransformFeedback(int target, int id) {
        GLES31.glBindTransformFeedback(target, id);
    }

    @Override
    public void glDeleteTransformFeedbacks(IntBuffer ids) {
        GLES31.glDeleteTransformFeedbacks(ids.limit(), ids);
    }

    @Override
    public void glDeleteTransformFeedbacks(int id) {
        tmp_int[0] = id;
        GLES31.glDeleteTransformFeedbacks(1, tmp_int, 0);
    }

    @Override
    public void glGenTransformFeedbacks(IntBuffer ids) {
        GLES31.glGenTransformFeedbacks(ids.limit(), ids);
    }

    @Override
    public int glGenTransformFeedbacks() {
        GLES31.glGenTransformFeedbacks(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public boolean glIsTransformFeedback(int id) {
        return GLES31.glIsTransformFeedback(id);
    }

    @Override
    public void glPauseTransformFeedback() {
        GLES31.glPauseTransformFeedback();
    }

    @Override
    public void glResumeTransformFeedback() {
        GLES31.glResumeTransformFeedback();
    }

    @Override
    public void glDrawTransformFeedback(int mode, int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawTransformFeedbackStream(int mode, int id, int stream) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glBeginQueryIndexed(int target, int index, int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glEndQueryIndexed(int target, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetQueryIndexediv(int target, int index, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetQueryIndexedi(int target, int index, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawArraysIndirect(int mode, int[] indirect) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDrawElementsIndirect(int mode, int type, int[] indirect) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glUniform1dv(int location, double[] value) {
        GLES31.glUniform1fv(location, value.length, toFloatArray(value), 0);
    }

    @Override
    public void glUniform2dv(int location, double[] value) {
        GLES31.glUniform2fv(location, value.length, toFloatArray(value), 0);
    }

    @Override
    public void glUniform3dv(int location, double[] value) {
        GLES31.glUniform3fv(location, value.length, toFloatArray(value), 0);
    }

    @Override
    public void glUniform4dv(int location, double[] value) {
        GLES31.glUniform4fv(location, value.length, toFloatArray(value), 0);
    }

    @Override
    public void glUniformMatrix2dv(int location, boolean transpose, double[] value) {
        GLES31.glUniformMatrix2fv(location, value.length, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glUniformMatrix3dv(int location, boolean transpose, double[] value) {
        GLES31.glUniformMatrix3fv(location, value.length, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glUniformMatrix4dv(int location, boolean transpose, double[] value) {
        GLES31.glUniformMatrix4fv(location, value.length, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glUniformMatrix2x3dv(int location, boolean transpose, double[] value) {
        GLES31.glUniformMatrix2x3fv(location, value.length, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glUniformMatrix2x4dv(int location, boolean transpose, double[] value) {
        GLES31.glUniformMatrix2x4fv(location, value.length, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glUniformMatrix3x2dv(int location, boolean transpose, double[] value) {
        GLES31.glUniformMatrix3x2fv(location, value.length, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glUniformMatrix3x4dv(int location, boolean transpose, double[] value) {
        GLES31.glUniformMatrix3x4fv(location, value.length, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glUniformMatrix4x2dv(int location, boolean transpose, double[] value) {
        GLES31.glUniformMatrix4x2fv(location, value.length, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glUniformMatrix4x3dv(int location, boolean transpose, double[] value) {
        GLES31.glUniformMatrix4x3fv(location, value.length, transpose, toFloatArray(value), 0);
    }

    @Override
    public void glGetUniformdv(int program, int location, double[] params) {
        GLES31.glGetUniformfv(program, location, toFloatArray(params), 0);
    }

    @Override
    public void glGetActiveSubroutineUniformiv(int program, int shadertype, int index, int pname, int[] values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetActiveSubroutineUniformName(int program, int shadertype, int index, int[] length, ByteBuffer name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetActiveSubroutineName(int program, int shadertype, int index, int[] length, ByteBuffer name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glUniformSubroutinesuiv(int shadertype, int[] indices) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetUniformSubroutineuiv(int shadertype, int location, int[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetProgramStageiv(int program, int shadertype, int pname, int[] values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glPatchParameterfv(int pname, float[] values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glDeleteTransformFeedbacks(int[] ids) {
        GLES31.glDeleteTransformFeedbacks(ids.length, ids, 0);
    }

    @Override
    public void glGenTransformFeedbacks(int[] ids) {
        GLES31.glGenTransformFeedbacks(ids.length, ids, 0);
    }

    @Override
    public void glGetQueryIndexediv(int target, int index, int pname, int[] params) {
        throw new UnsupportedOperationException();
    }

}