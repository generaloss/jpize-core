package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL20I;
import org.lwjgl.opengl.GL20;
import java.nio.*;

public class LwjglGL20 extends LwjglGL15 implements GL20I {

    public static final LwjglGL20 INSTANCE = new LwjglGL20();

    protected LwjglGL20() { }


    @Override
    public int glCreateProgram() {
        return GL20.glCreateProgram();
    }

    @Override
    public void glDeleteProgram(int program) {
        GL20.glDeleteProgram(program);
    }

    @Override
    public boolean glIsProgram(int program) {
        return GL20.glIsProgram(program);
    }

    @Override
    public int glCreateShader(int type) {
        return GL20.glCreateShader(type);
    }

    @Override
    public void glDeleteShader(int shader) {
        GL20.glDeleteShader(shader);
    }

    @Override
    public boolean glIsShader(int shader) {
        return GL20.glIsShader(shader);
    }

    @Override
    public void glAttachShader(int program, int shader) {
        GL20.glAttachShader(program, shader);
    }

    @Override
    public void glDetachShader(int program, int shader) {
        GL20.glDetachShader(program, shader);
    }

    @Override
    public void glShaderSource(int shader, CharSequence... strings) {
        GL20.glShaderSource(shader, strings);
    }

    @Override
    public void glShaderSource(int shader, CharSequence string) {
        GL20.glShaderSource(shader, string);
    }

    @Override
    public void glCompileShader(int shader) {
        GL20.glCompileShader(shader);
    }

    @Override
    public void glLinkProgram(int program) {
        GL20.glLinkProgram(program);
    }

    @Override
    public void glUseProgram(int program) {
        GL20.glUseProgram(program);
    }

    @Override
    public void glValidateProgram(int program) {
        GL20.glValidateProgram(program);
    }

    @Override
    public void glUniform1f(int location, float v0) {
        GL20.glUniform1f(location, v0);
    }

    @Override
    public void glUniform2f(int location, float v0, float v1) {
        GL20.glUniform2f(location, v0, v1);
    }

    @Override
    public void glUniform3f(int location, float v0, float v1, float v2) {
        GL20.glUniform3f(location, v0, v1, v2);
    }

    @Override
    public void glUniform4f(int location, float v0, float v1, float v2, float v3) {
        GL20.glUniform4f(location, v0, v1, v2, v3);
    }

    @Override
    public void glUniform1i(int location, int v0) {
        GL20.glUniform1i(location, v0);
    }

    @Override
    public void glUniform2i(int location, int v0, int v1) {
        GL20.glUniform2i(location, v0, v1);
    }

    @Override
    public void glUniform3i(int location, int v0, int v1, int v2) {
        GL20.glUniform3i(location, v0, v1, v2);
    }

    @Override
    public void glUniform4i(int location, int v0, int v1, int v2, int v3) {
        GL20.glUniform4i(location, v0, v1, v2, v3);
    }

    @Override
    public void glUniform1fv(int location, FloatBuffer value) {
        GL20.glUniform1fv(location, value);
    }

    @Override
    public void glUniform2fv(int location, FloatBuffer value) {
        GL20.glUniform2fv(location, value);
    }

    @Override
    public void glUniform3fv(int location, FloatBuffer value) {
        GL20.glUniform3fv(location, value);
    }

    @Override
    public void glUniform4fv(int location, FloatBuffer value) {
        GL20.glUniform4fv(location, value);
    }

    @Override
    public void glUniform1iv(int location, IntBuffer value) {
        GL20.glUniform1iv(location, value);
    }

    @Override
    public void glUniform2iv(int location, IntBuffer value) {
        GL20.glUniform2iv(location, value);
    }

    @Override
    public void glUniform3iv(int location, IntBuffer value) {
        GL20.glUniform3iv(location, value);
    }

    @Override
    public void glUniform4iv(int location, IntBuffer value) {
        GL20.glUniform4iv(location, value);
    }

    @Override
    public void glUniformMatrix2fv(int location, boolean transpose, FloatBuffer value) {
        GL20.glUniformMatrix2fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix3fv(int location, boolean transpose, FloatBuffer value) {
        GL20.glUniformMatrix3fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix4fv(int location, boolean transpose, FloatBuffer value) {
        GL20.glUniformMatrix4fv(location, transpose, value);
    }

    @Override
    public void glGetShaderiv(int shader, int pname, IntBuffer params) {
        GL20.glGetShaderiv(shader, pname, params);
    }

    @Override
    public int glGetShaderi(int shader, int pname) {
        return GL20.glGetShaderi(shader, pname);
    }

    @Override
    public void glGetProgramiv(int program, int pname, IntBuffer params) {
        GL20.glGetProgramiv(program, pname, params);
    }

    @Override
    public int glGetProgrami(int program, int pname) {
        return GL20.glGetProgrami(program, pname);
    }

    @Override
    public void glGetShaderInfoLog(int shader, IntBuffer length, ByteBuffer infoLog) {
        GL20.glGetShaderInfoLog(shader, length, infoLog);
    }

    @Override
    public String glGetShaderInfoLog(int shader, int maxLength) {
        return GL20.glGetShaderInfoLog(shader, maxLength);
    }

    @Override
    public String glGetShaderInfoLog(int shader) {
        return GL20.glGetShaderInfoLog(shader);
    }

    @Override
    public void glGetProgramInfoLog(int program, IntBuffer length, ByteBuffer infoLog) {
        GL20.glGetProgramInfoLog(program, length, infoLog);
    }

    @Override
    public String glGetProgramInfoLog(int program, int maxLength) {
        return GL20.glGetProgramInfoLog(program, maxLength);
    }

    @Override
    public String glGetProgramInfoLog(int program) {
        return GL20.glGetProgramInfoLog(program);
    }

    @Override
    public void glGetAttachedShaders(int program, IntBuffer count, IntBuffer shaders) {
        GL20.glGetAttachedShaders(program, count, shaders);
    }

    @Override
    public int glGetUniformLocation(int program, ByteBuffer name) {
        return GL20.glGetUniformLocation(program, name);
    }

    @Override
    public int glGetUniformLocation(int program, CharSequence name) {
        return GL20.glGetUniformLocation(program, name);
    }

    @Override
    public void glGetActiveUniform(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
        GL20.glGetActiveUniform(program, index, length, size, type, name);
    }

    @Override
    public String glGetActiveUniform(int program, int index, int maxLength, IntBuffer size, IntBuffer type) {
        return GL20.glGetActiveUniform(program, index, maxLength, size, type);
    }

    @Override
    public String glGetActiveUniform(int program, int index, IntBuffer size, IntBuffer type) {
        return GL20.glGetActiveUniform(program, index, size, type);
    }

    @Override
    public void glGetUniformfv(int program, int location, FloatBuffer params) {
        GL20.glGetUniformfv(program, location, params);
    }

    @Override
    public float glGetUniformf(int program, int location) {
        return GL20.glGetUniformf(program, location);
    }

    @Override
    public void glGetUniformiv(int program, int location, IntBuffer params) {
        GL20.glGetUniformiv(program, location, params);
    }

    @Override
    public int glGetUniformi(int program, int location) {
        return GL20.glGetUniformi(program, location);
    }

    @Override
    public void glGetShaderSource(int shader, IntBuffer length, ByteBuffer source) {
        GL20.glGetShaderSource(shader, length, source);
    }

    @Override
    public String glGetShaderSource(int shader, int maxLength) {
        return GL20.glGetShaderSource(shader, maxLength);
    }

    @Override
    public String glGetShaderSource(int shader) {
        return GL20.glGetShaderSource(shader);
    }

    @Override
    public void glVertexAttrib1f(int index, float v0) {
        GL20.glVertexAttrib1f(index, v0);
    }

    @Override
    public void glVertexAttrib1s(int index, short v0) {
        GL20.glVertexAttrib1s(index, v0);
    }

    @Override
    public void glVertexAttrib1d(int index, double v0) {
        GL20.glVertexAttrib1d(index, v0);
    }

    @Override
    public void glVertexAttrib2f(int index, float v0, float v1) {
        GL20.glVertexAttrib2f(index, v0, v1);
    }

    @Override
    public void glVertexAttrib2s(int index, short v0, short v1) {
        GL20.glVertexAttrib2s(index, v0, v1);
    }

    @Override
    public void glVertexAttrib2d(int index, double v0, double v1) {
        GL20.glVertexAttrib2d(index, v0, v1);
    }

    @Override
    public void glVertexAttrib3f(int index, float v0, float v1, float v2) {
        GL20.glVertexAttrib3f(index, v0, v1, v2);
    }

    @Override
    public void glVertexAttrib3s(int index, short v0, short v1, short v2) {
        GL20.glVertexAttrib3s(index, v0, v1, v2);
    }

    @Override
    public void glVertexAttrib3d(int index, double v0, double v1, double v2) {
        GL20.glVertexAttrib3d(index, v0, v1, v2);
    }

    @Override
    public void glVertexAttrib4f(int index, float v0, float v1, float v2, float v3) {
        GL20.glVertexAttrib4f(index, v0, v1, v2, v3);
    }

    @Override
    public void glVertexAttrib4s(int index, short v0, short v1, short v2, short v3) {
        GL20.glVertexAttrib4s(index, v0, v1, v2, v3);
    }

    @Override
    public void glVertexAttrib4d(int index, double v0, double v1, double v2, double v3) {
        GL20.glVertexAttrib4d(index, v0, v1, v2, v3);
    }

    @Override
    public void glVertexAttrib4Nub(int index, byte x, byte y, byte z, byte w) {
        GL20.glVertexAttrib4Nub(index, x, y, z, w);
    }

    @Override
    public void glVertexAttrib1fv(int index, FloatBuffer v) {
        GL20.glVertexAttrib1fv(index, v);
    }

    @Override
    public void glVertexAttrib1sv(int index, ShortBuffer v) {
        GL20.glVertexAttrib1sv(index, v);
    }

    @Override
    public void glVertexAttrib1dv(int index, DoubleBuffer v) {
        GL20.glVertexAttrib1dv(index, v);
    }

    @Override
    public void glVertexAttrib2fv(int index, FloatBuffer v) {
        GL20.glVertexAttrib2fv(index, v);
    }

    @Override
    public void glVertexAttrib2sv(int index, ShortBuffer v) {
        GL20.glVertexAttrib2sv(index, v);
    }

    @Override
    public void glVertexAttrib2dv(int index, DoubleBuffer v) {
        GL20.glVertexAttrib2dv(index, v);
    }

    @Override
    public void glVertexAttrib3fv(int index, FloatBuffer v) {
        GL20.glVertexAttrib3fv(index, v);
    }

    @Override
    public void glVertexAttrib3sv(int index, ShortBuffer v) {
        GL20.glVertexAttrib3sv(index, v);
    }

    @Override
    public void glVertexAttrib3dv(int index, DoubleBuffer v) {
        GL20.glVertexAttrib3dv(index, v);
    }

    @Override
    public void glVertexAttrib4fv(int index, FloatBuffer v) {
        GL20.glVertexAttrib4fv(index, v);
    }

    @Override
    public void glVertexAttrib4sv(int index, ShortBuffer v) {
        GL20.glVertexAttrib4sv(index, v);
    }

    @Override
    public void glVertexAttrib4dv(int index, DoubleBuffer v) {
        GL20.glVertexAttrib4dv(index, v);
    }

    @Override
    public void glVertexAttrib4iv(int index, IntBuffer v) {
        GL20.glVertexAttrib4iv(index, v);
    }

    @Override
    public void glVertexAttrib4bv(int index, ByteBuffer v) {
        GL20.glVertexAttrib4bv(index, v);
    }

    @Override
    public void glVertexAttrib4ubv(int index, ByteBuffer v) {
        GL20.glVertexAttrib4ubv(index, v);
    }

    @Override
    public void glVertexAttrib4usv(int index, ShortBuffer v) {
        GL20.glVertexAttrib4usv(index, v);
    }

    @Override
    public void glVertexAttrib4uiv(int index, IntBuffer v) {
        GL20.glVertexAttrib4uiv(index, v);
    }

    @Override
    public void glVertexAttrib4Nbv(int index, ByteBuffer v) {
        GL20.glVertexAttrib4Nbv(index, v);
    }

    @Override
    public void glVertexAttrib4Nsv(int index, ShortBuffer v) {
        GL20.glVertexAttrib4Nsv(index, v);
    }

    @Override
    public void glVertexAttrib4Niv(int index, IntBuffer v) {
        GL20.glVertexAttrib4Niv(index, v);
    }

    @Override
    public void glVertexAttrib4Nubv(int index, ByteBuffer v) {
        GL20.glVertexAttrib4Nubv(index, v);
    }

    @Override
    public void glVertexAttrib4Nusv(int index, ShortBuffer v) {
        GL20.glVertexAttrib4Nusv(index, v);
    }

    @Override
    public void glVertexAttrib4Nuiv(int index, IntBuffer v) {
        GL20.glVertexAttrib4Nuiv(index, v);
    }

    @Override
    public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, ByteBuffer pointer) {
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
    }

    @Override
    public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer) {
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
    }

    @Override
    public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, ShortBuffer pointer) {
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
    }

    @Override
    public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, IntBuffer pointer) {
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
    }

    @Override
    public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, FloatBuffer pointer) {
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
    }

    @Override
    public void glEnableVertexAttribArray(int index) {
        GL20.glEnableVertexAttribArray(index);
    }

    @Override
    public void glDisableVertexAttribArray(int index) {
        GL20.glDisableVertexAttribArray(index);
    }

    @Override
    public void glBindAttribLocation(int program, int index, ByteBuffer name) {
        GL20.glBindAttribLocation(program, index, name);
    }

    @Override
    public void glBindAttribLocation(int program, int index, CharSequence name) {
        GL20.glBindAttribLocation(program, index, name);
    }

    @Override
    public void glGetActiveAttrib(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
        GL20.glGetActiveAttrib(program, index, length, size, type, name);
    }

    @Override
    public String glGetActiveAttrib(int program, int index, int maxLength, IntBuffer size, IntBuffer type) {
        return GL20.glGetActiveAttrib(program, index, maxLength, size, type);
    }

    @Override
    public String glGetActiveAttrib(int program, int index, IntBuffer size, IntBuffer type) {
        return GL20.glGetActiveAttrib(program, index, size, type);
    }

    @Override
    public int glGetAttribLocation(int program, ByteBuffer name) {
        return GL20.glGetAttribLocation(program, name);
    }

    @Override
    public int glGetAttribLocation(int program, CharSequence name) {
        return GL20.glGetAttribLocation(program, name);
    }

    @Override
    public void glGetVertexAttribiv(int index, int pname, IntBuffer params) {
        GL20.glGetVertexAttribiv(index, pname, params);
    }

    @Override
    public int glGetVertexAttribi(int index, int pname) {
        return GL20.glGetVertexAttribi(index, pname);
    }

    @Override
    public void glGetVertexAttribfv(int index, int pname, FloatBuffer params) {
        GL20.glGetVertexAttribfv(index, pname, params);
    }

    @Override
    public void glGetVertexAttribdv(int index, int pname, DoubleBuffer params) {
        GL20.glGetVertexAttribdv(index, pname, params);
    }

    @Override
    public long glGetVertexAttribPointer(int index, int pname) {
        return GL20.glGetVertexAttribPointer(index, pname);
    }

    @Override
    public void glDrawBuffers(IntBuffer bufs) {
        GL20.glDrawBuffers(bufs);
    }

    @Override
    public void glDrawBuffers(int buf) {
        GL20.glDrawBuffers(buf);
    }

    @Override
    public void glBlendEquationSeparate(int modeRGB, int modeAlpha) {
        GL20.glBlendEquationSeparate(modeRGB, modeAlpha);
    }

    @Override
    public void glStencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
        GL20.glStencilOpSeparate(face, sfail, dpfail, dppass);
    }

    @Override
    public void glStencilFuncSeparate(int face, int func, int ref, int mask) {
        GL20.glStencilFuncSeparate(face, func, ref, mask);
    }

    @Override
    public void glStencilMaskSeparate(int face, int mask) {
        GL20.glStencilMaskSeparate(face, mask);
    }

    @Override
    public void glUniform1fv(int location, float[] value) {
        GL20.glUniform1fv(location, value);
    }

    @Override
    public void glUniform2fv(int location, float[] value) {
        GL20.glUniform2fv(location, value);
    }

    @Override
    public void glUniform3fv(int location, float[] value) {
        GL20.glUniform3fv(location, value);
    }

    @Override
    public void glUniform4fv(int location, float[] value) {
        GL20.glUniform4fv(location, value);
    }

    @Override
    public void glUniform1iv(int location, int[] value) {
        GL20.glUniform1iv(location, value);
    }

    @Override
    public void glUniform2iv(int location, int[] value) {
        GL20.glUniform2iv(location, value);
    }

    @Override
    public void glUniform3iv(int location, int[] value) {
        GL20.glUniform3iv(location, value);
    }

    @Override
    public void glUniform4iv(int location, int[] value) {
        GL20.glUniform4iv(location, value);
    }

    @Override
    public void glUniformMatrix2fv(int location, boolean transpose, float[] value) {
        GL20.glUniformMatrix2fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix3fv(int location, boolean transpose, float[] value) {
        GL20.glUniformMatrix3fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix4fv(int location, boolean transpose, float[] value) {
        GL20.glUniformMatrix4fv(location, transpose, value);
    }

    @Override
    public void glGetShaderiv(int shader, int pname, int[] params) {
        GL20.glGetShaderiv(shader, pname, params);
    }

    @Override
    public void glGetProgramiv(int program, int pname, int[] params) {
        GL20.glGetProgramiv(program, pname, params);
    }

    @Override
    public void glGetShaderInfoLog(int shader, int[] length, ByteBuffer infoLog) {
        GL20.glGetShaderInfoLog(shader, length, infoLog);
    }

    @Override
    public void glGetProgramInfoLog(int program, int[] length, ByteBuffer infoLog) {
        GL20.glGetProgramInfoLog(program, length, infoLog);
    }

    @Override
    public void glGetAttachedShaders(int program, int[] count, int[] shaders) {
        GL20.glGetAttachedShaders(program, count, shaders);
    }

    @Override
    public void glGetActiveUniform(int program, int index, int[] length, int[] size, int[] type, ByteBuffer name) {
        GL20.glGetActiveUniform(program, index, length, size, type, name);
    }

    @Override
    public void glGetUniformfv(int program, int location, float[] params) {
        GL20.glGetUniformfv(program, location, params);
    }

    @Override
    public void glGetUniformiv(int program, int location, int[] params) {
        GL20.glGetUniformiv(program, location, params);
    }

    @Override
    public void glGetShaderSource(int shader, int[] length, ByteBuffer source) {
        GL20.glGetShaderSource(shader, length, source);
    }

    @Override
    public void glVertexAttrib1fv(int index, float[] v) {
        GL20.glVertexAttrib1fv(index, v);
    }

    @Override
    public void glVertexAttrib1sv(int index, short[] v) {
        GL20.glVertexAttrib1sv(index, v);
    }

    @Override
    public void glVertexAttrib1dv(int index, double[] v) {
        GL20.glVertexAttrib1dv(index, v);
    }

    @Override
    public void glVertexAttrib2fv(int index, float[] v) {
        GL20.glVertexAttrib2fv(index, v);
    }

    @Override
    public void glVertexAttrib2sv(int index, short[] v) {
        GL20.glVertexAttrib2sv(index, v);
    }

    @Override
    public void glVertexAttrib2dv(int index, double[] v) {
        GL20.glVertexAttrib2dv(index, v);
    }

    @Override
    public void glVertexAttrib3fv(int index, float[] v) {
        GL20.glVertexAttrib3fv(index, v);
    }

    @Override
    public void glVertexAttrib3sv(int index, short[] v) {
        GL20.glVertexAttrib3sv(index, v);
    }

    @Override
    public void glVertexAttrib3dv(int index, double[] v) {
        GL20.glVertexAttrib3dv(index, v);
    }

    @Override
    public void glVertexAttrib4fv(int index, float[] v) {
        GL20.glVertexAttrib4fv(index, v);
    }

    @Override
    public void glVertexAttrib4sv(int index, short[] v) {
        GL20.glVertexAttrib4sv(index, v);
    }

    @Override
    public void glVertexAttrib4dv(int index, double[] v) {
        GL20.glVertexAttrib4dv(index, v);
    }

    @Override
    public void glVertexAttrib4iv(int index, int[] v) {
        GL20.glVertexAttrib4iv(index, v);
    }

    @Override
    public void glVertexAttrib4usv(int index, short[] v) {
        GL20.glVertexAttrib4usv(index, v);
    }

    @Override
    public void glVertexAttrib4uiv(int index, int[] v) {
        GL20.glVertexAttrib4uiv(index, v);
    }

    @Override
    public void glVertexAttrib4Nsv(int index, short[] v) {
        GL20.glVertexAttrib4Nsv(index, v);
    }

    @Override
    public void glVertexAttrib4Niv(int index, int[] v) {
        GL20.glVertexAttrib4Niv(index, v);
    }

    @Override
    public void glVertexAttrib4Nusv(int index, short[] v) {
        GL20.glVertexAttrib4Nusv(index, v);
    }

    @Override
    public void glVertexAttrib4Nuiv(int index, int[] v) {
        GL20.glVertexAttrib4Nuiv(index, v);
    }

    @Override
    public void glGetActiveAttrib(int program, int index, int[] length, int[] size, int[] type, ByteBuffer name) {
        GL20.glGetActiveAttrib(program, index, length, size, type, name);
    }

    @Override
    public void glGetVertexAttribiv(int index, int pname, int[] params) {
        GL20.glGetVertexAttribiv(index, pname, params);
    }

    @Override
    public void glGetVertexAttribfv(int index, int pname, float[] params) {
        GL20.glGetVertexAttribfv(index, pname, params);
    }

    @Override
    public void glGetVertexAttribdv(int index, int pname, double[] params) {
        GL20.glGetVertexAttribdv(index, pname, params);
    }

    @Override
    public void glDrawBuffers(int[] bufs) {
        GL20.glDrawBuffers(bufs);
    }

}