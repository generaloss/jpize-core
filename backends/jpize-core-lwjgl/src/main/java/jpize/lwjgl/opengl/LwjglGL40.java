package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL40I;
import org.lwjgl.opengl.GL40;
import java.nio.*;

public class LwjglGL40 extends LwjglGL33 implements GL40I {

    public static final LwjglGL40 INSTANCE = new LwjglGL40();

    protected LwjglGL40() { }


    @Override
    public void glBlendEquationi(int buf, int mode) {
        GL40.glBlendEquationi(buf, mode);
    }

    @Override
    public void glBlendEquationSeparatei(int buf, int modeRGB, int modeAlpha) {
        GL40.glBlendEquationSeparatei(buf, modeRGB, modeAlpha);
    }

    @Override
    public void glBlendFunci(int buf, int sfactor, int dfactor) {
        GL40.glBlendFunci(buf, sfactor, dfactor);
    }

    @Override
    public void glBlendFuncSeparatei(int buf, int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
        GL40.glBlendFuncSeparatei(buf, srcRGB, dstRGB, srcAlpha, dstAlpha);
    }

    @Override
    public void glDrawArraysIndirect(int mode, ByteBuffer indirect) {
        GL40.glDrawArraysIndirect(mode, indirect);
    }

    @Override
    public void glDrawArraysIndirect(int mode, long indirect) {
        GL40.glDrawArraysIndirect(mode, indirect);
    }

    @Override
    public void glDrawArraysIndirect(int mode, IntBuffer indirect) {
        GL40.glDrawArraysIndirect(mode, indirect);
    }

    @Override
    public void glDrawElementsIndirect(int mode, int type, ByteBuffer indirect) {
        GL40.glDrawElementsIndirect(mode, type, indirect);
    }

    @Override
    public void glDrawElementsIndirect(int mode, int type, long indirect) {
        GL40.glDrawElementsIndirect(mode, type, indirect);
    }

    @Override
    public void glDrawElementsIndirect(int mode, int type, IntBuffer indirect) {
        GL40.glDrawElementsIndirect(mode, type, indirect);
    }

    @Override
    public void glUniform1d(int location, double x) {
        GL40.glUniform1d(location, x);
    }

    @Override
    public void glUniform2d(int location, double x, double y) {
        GL40.glUniform2d(location, x, y);
    }

    @Override
    public void glUniform3d(int location, double x, double y, double z) {
        GL40.glUniform3d(location, x, y, z);
    }

    @Override
    public void glUniform4d(int location, double x, double y, double z, double w) {
        GL40.glUniform4d(location, x, y, z, w);
    }

    @Override
    public void glUniform1dv(int location, DoubleBuffer value) {
        GL40.glUniform1dv(location, value);
    }

    @Override
    public void glUniform2dv(int location, DoubleBuffer value) {
        GL40.glUniform2dv(location, value);
    }

    @Override
    public void glUniform3dv(int location, DoubleBuffer value) {
        GL40.glUniform3dv(location, value);
    }

    @Override
    public void glUniform4dv(int location, DoubleBuffer value) {
        GL40.glUniform4dv(location, value);
    }

    @Override
    public void glUniformMatrix2dv(int location, boolean transpose, DoubleBuffer value) {
        GL40.glUniformMatrix2dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix3dv(int location, boolean transpose, DoubleBuffer value) {
        GL40.glUniformMatrix3dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix4dv(int location, boolean transpose, DoubleBuffer value) {
        GL40.glUniformMatrix4dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix2x3dv(int location, boolean transpose, DoubleBuffer value) {
        GL40.glUniformMatrix2x3dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix2x4dv(int location, boolean transpose, DoubleBuffer value) {
        GL40.glUniformMatrix2x4dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix3x2dv(int location, boolean transpose, DoubleBuffer value) {
        GL40.glUniformMatrix3x2dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix3x4dv(int location, boolean transpose, DoubleBuffer value) {
        GL40.glUniformMatrix3x4dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix4x2dv(int location, boolean transpose, DoubleBuffer value) {
        GL40.glUniformMatrix4x2dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix4x3dv(int location, boolean transpose, DoubleBuffer value) {
        GL40.glUniformMatrix4x3dv(location, transpose, value);
    }

    @Override
    public void glGetUniformdv(int program, int location, DoubleBuffer params) {
        GL40.glGetUniformdv(program, location, params);
    }

    @Override
    public double glGetUniformd(int program, int location) {
        return GL40.glGetUniformd(program, location);
    }

    @Override
    public void glMinSampleShading(float value) {
        GL40.glMinSampleShading(value);
    }

    @Override
    public int glGetSubroutineUniformLocation(int program, int shadertype, ByteBuffer name) {
        return GL40.glGetSubroutineUniformLocation(program, shadertype, name);
    }

    @Override
    public int glGetSubroutineUniformLocation(int program, int shadertype, CharSequence name) {
        return GL40.glGetSubroutineUniformLocation(program, shadertype, name);
    }

    @Override
    public int glGetSubroutineIndex(int program, int shadertype, ByteBuffer name) {
        return GL40.glGetSubroutineIndex(program, shadertype, name);
    }

    @Override
    public int glGetSubroutineIndex(int program, int shadertype, CharSequence name) {
        return GL40.glGetSubroutineIndex(program, shadertype, name);
    }

    @Override
    public void glGetActiveSubroutineUniformiv(int program, int shadertype, int index, int pname, IntBuffer values) {
        GL40.glGetActiveSubroutineUniformiv(program, shadertype, index, pname, values);
    }

    @Override
    public int glGetActiveSubroutineUniformi(int program, int shadertype, int index, int pname) {
        return GL40.glGetActiveSubroutineUniformi(program, shadertype, index, pname);
    }

    @Override
    public void glGetActiveSubroutineUniformName(int program, int shadertype, int index, IntBuffer length, ByteBuffer name) {
        GL40.glGetActiveSubroutineUniformName(program, shadertype, index, length, name);
    }

    @Override
    public String glGetActiveSubroutineUniformName(int program, int shadertype, int index, int bufsize) {
        return GL40.glGetActiveSubroutineUniformName(program, shadertype, index, bufsize);
    }

    @Override
    public String glGetActiveSubroutineUniformName(int program, int shadertype, int index) {
        return GL40.glGetActiveSubroutineUniformName(program, shadertype, index);
    }

    @Override
    public void glGetActiveSubroutineName(int program, int shadertype, int index, IntBuffer length, ByteBuffer name) {
        GL40.glGetActiveSubroutineName(program, shadertype, index, length, name);
    }

    @Override
    public String glGetActiveSubroutineName(int program, int shadertype, int index, int bufsize) {
        return GL40.glGetActiveSubroutineName(program, shadertype, index, bufsize);
    }

    @Override
    public String glGetActiveSubroutineName(int program, int shadertype, int index) {
        return GL40.glGetActiveSubroutineName(program, shadertype, index);
    }

    @Override
    public void glUniformSubroutinesuiv(int shadertype, IntBuffer indices) {
        GL40.glUniformSubroutinesuiv(shadertype, indices);
    }

    @Override
    public void glUniformSubroutinesui(int shadertype, int index) {
        GL40.glUniformSubroutinesui(shadertype, index);
    }

    @Override
    public void glGetUniformSubroutineuiv(int shadertype, int location, IntBuffer params) {
        GL40.glGetUniformSubroutineuiv(shadertype, location, params);
    }

    @Override
    public int glGetUniformSubroutineui(int shadertype, int location) {
        return GL40.glGetUniformSubroutineui(shadertype, location);
    }

    @Override
    public void glGetProgramStageiv(int program, int shadertype, int pname, IntBuffer values) {
        GL40.glGetProgramStageiv(program, shadertype, pname, values);
    }

    @Override
    public int glGetProgramStagei(int program, int shadertype, int pname) {
        return GL40.glGetProgramStagei(program, shadertype, pname);
    }

    @Override
    public void glPatchParameteri(int pname, int value) {
        GL40.glPatchParameteri(pname, value);
    }

    @Override
    public void glPatchParameterfv(int pname, FloatBuffer values) {
        GL40.glPatchParameterfv(pname, values);
    }

    @Override
    public void glBindTransformFeedback(int target, int id) {
        GL40.glBindTransformFeedback(target, id);
    }

    @Override
    public void glDeleteTransformFeedbacks(IntBuffer ids) {
        GL40.glDeleteTransformFeedbacks(ids);
    }

    @Override
    public void glDeleteTransformFeedbacks(int id) {
        GL40.glDeleteTransformFeedbacks(id);
    }

    @Override
    public void glGenTransformFeedbacks(IntBuffer ids) {
        GL40.glGenTransformFeedbacks(ids);
    }

    @Override
    public int glGenTransformFeedbacks() {
        return GL40.glGenTransformFeedbacks();
    }

    @Override
    public boolean glIsTransformFeedback(int id) {
        return GL40.glIsTransformFeedback(id);
    }

    @Override
    public void glPauseTransformFeedback() {
        GL40.glPauseTransformFeedback();
    }

    @Override
    public void glResumeTransformFeedback() {
        GL40.glResumeTransformFeedback();
    }

    @Override
    public void glDrawTransformFeedback(int mode, int id) {
        GL40.glDrawTransformFeedback(mode, id);
    }

    @Override
    public void glDrawTransformFeedbackStream(int mode, int id, int stream) {
        GL40.glDrawTransformFeedbackStream(mode, id, stream);
    }

    @Override
    public void glBeginQueryIndexed(int target, int index, int id) {
        GL40.glBeginQueryIndexed(target, index, id);
    }

    @Override
    public void glEndQueryIndexed(int target, int index) {
        GL40.glEndQueryIndexed(target, index);
    }

    @Override
    public void glGetQueryIndexediv(int target, int index, int pname, IntBuffer params) {
        GL40.glGetQueryIndexediv(target, index, pname, params);
    }

    @Override
    public int glGetQueryIndexedi(int target, int index, int pname) {
        return GL40.glGetQueryIndexedi(target, index, pname);
    }

    @Override
    public void glDrawArraysIndirect(int mode, int[] indirect) {
        GL40.glDrawArraysIndirect(mode, indirect);
    }

    @Override
    public void glDrawElementsIndirect(int mode, int type, int[] indirect) {
        GL40.glDrawElementsIndirect(mode, type, indirect);
    }

    @Override
    public void glUniform1dv(int location, double[] value) {
        GL40.glUniform1dv(location, value);
    }

    @Override
    public void glUniform2dv(int location, double[] value) {
        GL40.glUniform2dv(location, value);
    }

    @Override
    public void glUniform3dv(int location, double[] value) {
        GL40.glUniform3dv(location, value);
    }

    @Override
    public void glUniform4dv(int location, double[] value) {
        GL40.glUniform4dv(location, value);
    }

    @Override
    public void glUniformMatrix2dv(int location, boolean transpose, double[] value) {
        GL40.glUniformMatrix2dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix3dv(int location, boolean transpose, double[] value) {
        GL40.glUniformMatrix3dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix4dv(int location, boolean transpose, double[] value) {
        GL40.glUniformMatrix4dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix2x3dv(int location, boolean transpose, double[] value) {
        GL40.glUniformMatrix2x3dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix2x4dv(int location, boolean transpose, double[] value) {
        GL40.glUniformMatrix2x4dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix3x2dv(int location, boolean transpose, double[] value) {
        GL40.glUniformMatrix3x2dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix3x4dv(int location, boolean transpose, double[] value) {
        GL40.glUniformMatrix3x4dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix4x2dv(int location, boolean transpose, double[] value) {
        GL40.glUniformMatrix4x2dv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix4x3dv(int location, boolean transpose, double[] value) {
        GL40.glUniformMatrix4x3dv(location, transpose, value);
    }

    @Override
    public void glGetUniformdv(int program, int location, double[] params) {
        GL40.glGetUniformdv(program, location, params);
    }

    @Override
    public void glGetActiveSubroutineUniformiv(int program, int shadertype, int index, int pname, int[] values) {
        GL40.glGetActiveSubroutineUniformiv(program, shadertype, index, pname, values);
    }

    @Override
    public void glGetActiveSubroutineUniformName(int program, int shadertype, int index, int[] length, ByteBuffer name) {
        GL40.glGetActiveSubroutineUniformName(program, shadertype, index, length, name);
    }

    @Override
    public void glGetActiveSubroutineName(int program, int shadertype, int index, int[] length, ByteBuffer name) {
        GL40.glGetActiveSubroutineName(program, shadertype, index, length, name);
    }

    @Override
    public void glUniformSubroutinesuiv(int shadertype, int[] indices) {
        GL40.glUniformSubroutinesuiv(shadertype, indices);
    }

    @Override
    public void glGetUniformSubroutineuiv(int shadertype, int location, int[] params) {
        GL40.glGetUniformSubroutineuiv(shadertype, location, params);
    }

    @Override
    public void glGetProgramStageiv(int program, int shadertype, int pname, int[] values) {
        GL40.glGetProgramStageiv(program, shadertype, pname, values);
    }

    @Override
    public void glPatchParameterfv(int pname, float[] values) {
        GL40.glPatchParameterfv(pname, values);
    }

    @Override
    public void glDeleteTransformFeedbacks(int[] ids) {
        GL40.glDeleteTransformFeedbacks(ids);
    }

    @Override
    public void glGenTransformFeedbacks(int[] ids) {
        GL40.glGenTransformFeedbacks(ids);
    }

    @Override
    public void glGetQueryIndexediv(int target, int index, int pname, int[] params) {
        GL40.glGetQueryIndexediv(target, index, pname, params);
    }

}