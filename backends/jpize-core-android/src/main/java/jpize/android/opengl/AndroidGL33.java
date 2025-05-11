package jpize.android.opengl;

import android.opengl.GLES30;
import android.opengl.GLES32;
import jpize.opengl.gl.GL33I;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public class AndroidGL33 extends AndroidGL32 implements GL33I {

    public static final AndroidGL33 INSTANCE = new AndroidGL33();

    protected AndroidGL33() { }


    @Override
    public void glBindFragDataLocationIndexed(int program, int colorNumber, int index, ByteBuffer name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glBindFragDataLocationIndexed(int program, int colorNumber, int index, CharSequence name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetFragDataIndex(int program, ByteBuffer name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int glGetFragDataIndex(int program, CharSequence name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGenSamplers(IntBuffer samplers) {
        GLES30.glGenSamplers(samplers.limit(), samplers);
    }

    @Override
    public int glGenSamplers() {
        GLES30.glGenSamplers(1, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glDeleteSamplers(IntBuffer samplers) {
        GLES30.glDeleteSamplers(samplers.limit(), samplers);
    }

    @Override
    public void glDeleteSamplers(int sampler) {
        tmp_int[0] = sampler;
        GLES30.glDeleteSamplers(1, tmp_int, 0);
    }

    @Override
    public boolean glIsSampler(int sampler) {
        return GLES30.glIsSampler(sampler);
    }

    @Override
    public void glBindSampler(int unit, int sampler) {
        GLES30.glBindSampler(unit, sampler);
    }

    @Override
    public void glSamplerParameteri(int sampler, int pname, int param) {
        GLES30.glSamplerParameteri(sampler, pname, param);
    }

    @Override
    public void glSamplerParameterf(int sampler, int pname, float param) {
        GLES30.glSamplerParameterf(sampler, pname, param);
    }

    @Override
    public void glSamplerParameteriv(int sampler, int pname, IntBuffer params) {
        GLES30.glSamplerParameteriv(sampler, pname, params);
    }

    @Override
    public void glSamplerParameterfv(int sampler, int pname, FloatBuffer params) {
        GLES30.glSamplerParameterfv(sampler, pname, params);
    }

    @Override
    public void glSamplerParameterIiv(int sampler, int pname, IntBuffer params) {
        GLES32.glSamplerParameterIiv(sampler, pname, params);
    }

    @Override
    public void glSamplerParameterIuiv(int sampler, int pname, IntBuffer params) {
        GLES32.glSamplerParameterIuiv(sampler, pname, params);
    }

    @Override
    public void glGetSamplerParameteriv(int sampler, int pname, IntBuffer params) {
        GLES30.glGetSamplerParameteriv(sampler, pname, params);
    }

    @Override
    public int glGetSamplerParameteri(int sampler, int pname) {
        GLES30.glGetSamplerParameteriv(sampler, pname, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glGetSamplerParameterfv(int sampler, int pname, FloatBuffer params) {
        GLES30.glGetSamplerParameterfv(sampler, pname, params);
    }

    @Override
    public float glGetSamplerParameterf(int sampler, int pname) {
        GLES30.glGetSamplerParameterfv(sampler, pname, tmp_float, 0);
        return tmp_float[0];
    }

    @Override
    public void glGetSamplerParameterIiv(int sampler, int pname, IntBuffer params) {
        GLES32.glGetSamplerParameterIiv(sampler, pname, params);
    }

    @Override
    public int glGetSamplerParameterIi(int sampler, int pname) {
        GLES32.glGetSamplerParameterIiv(sampler, pname, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glGetSamplerParameterIuiv(int sampler, int pname, IntBuffer params) {
        GLES32.glGetSamplerParameterIuiv(sampler, pname, params);
    }

    @Override
    public int glGetSamplerParameterIui(int sampler, int pname) {
        GLES32.glGetSamplerParameterIuiv(sampler, pname, tmp_int, 0);
        return tmp_int[0];
    }

    @Override
    public void glQueryCounter(int id, int target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetQueryObjecti64v(int id, int pname, LongBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetQueryObjecti64v(int id, int pname, long params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long glGetQueryObjecti64(int id, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetQueryObjectui64v(int id, int pname, LongBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetQueryObjectui64v(int id, int pname, long params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long glGetQueryObjectui64(int id, int pname) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribDivisor(int index, int divisor) {
        GLES30.glVertexAttribDivisor(index, divisor);
    }

    @Override
    public void glVertexP2ui(int var0, int var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexP3ui(int var0, int var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexP4ui(int var0, int var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexP2uiv(int type, IntBuffer value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexP3uiv(int type, IntBuffer value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexP4uiv(int type, IntBuffer value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexCoordP1ui(int var0, int var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexCoordP2ui(int var0, int var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexCoordP3ui(int var0, int var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexCoordP4ui(int var0, int var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexCoordP1uiv(int type, IntBuffer coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexCoordP2uiv(int type, IntBuffer coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexCoordP3uiv(int type, IntBuffer coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexCoordP4uiv(int type, IntBuffer coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoordP1ui(int var0, int var1, int var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoordP2ui(int var0, int var1, int var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoordP3ui(int var0, int var1, int var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoordP4ui(int var0, int var1, int var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoordP1uiv(int texture, int type, IntBuffer coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoordP2uiv(int texture, int type, IntBuffer coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoordP3uiv(int texture, int type, IntBuffer coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoordP4uiv(int texture, int type, IntBuffer coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNormalP3ui(int var0, int var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNormalP3uiv(int type, IntBuffer coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glColorP3ui(int var0, int var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glColorP4ui(int var0, int var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glColorP3uiv(int type, IntBuffer color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glColorP4uiv(int type, IntBuffer color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColorP3ui(int var0, int var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColorP3uiv(int type, IntBuffer color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribP1ui(int index, int type, boolean normalized, int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribP2ui(int index, int type, boolean normalized, int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribP3ui(int index, int type, boolean normalized, int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribP4ui(int index, int type, boolean normalized, int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribP1uiv(int index, int type, boolean normalized, IntBuffer value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribP2uiv(int index, int type, boolean normalized, IntBuffer value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribP3uiv(int index, int type, boolean normalized, IntBuffer value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribP4uiv(int index, int type, boolean normalized, IntBuffer value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGenSamplers(int[] samplers) {
        GLES30.glGenSamplers(samplers.length, samplers, 0);
    }

    @Override
    public void glDeleteSamplers(int[] samplers) {
        GLES30.glDeleteSamplers(samplers.length, samplers, 0);
    }

    @Override
    public void glSamplerParameteriv(int sampler, int pname, int[] params) {
        GLES30.glSamplerParameteriv(sampler, pname, params, 0);
    }

    @Override
    public void glSamplerParameterfv(int sampler, int pname, float[] params) {
        GLES30.glSamplerParameterfv(sampler, pname, params, 0);
    }

    @Override
    public void glSamplerParameterIiv(int sampler, int pname, int[] params) {
        GLES32.glSamplerParameterIiv(sampler, pname, params, 0);
    }

    @Override
    public void glSamplerParameterIuiv(int sampler, int pname, int[] params) {
        GLES32.glSamplerParameterIuiv(sampler, pname, params, 0);
    }

    @Override
    public void glGetSamplerParameteriv(int sampler, int pname, int[] params) {
        GLES30.glGetSamplerParameteriv(sampler, pname, params, 0);
    }

    @Override
    public void glGetSamplerParameterfv(int sampler, int pname, float[] params) {
        GLES30.glGetSamplerParameterfv(sampler, pname, params, 0);
    }

    @Override
    public void glGetSamplerParameterIiv(int sampler, int pname, int[] params) {
        GLES32.glGetSamplerParameterIiv(sampler, pname, params, 0);
    }

    @Override
    public void glGetSamplerParameterIuiv(int sampler, int pname, int[] params) {
        GLES32.glGetSamplerParameterIuiv(sampler, pname, params, 0);
    }

    @Override
    public void glGetQueryObjecti64v(int id, int pname, long[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetQueryObjectui64v(int id, int pname, long[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexP2uiv(int type, int[] value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexP3uiv(int type, int[] value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexP4uiv(int type, int[] value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexCoordP1uiv(int type, int[] coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexCoordP2uiv(int type, int[] coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexCoordP3uiv(int type, int[] coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glTexCoordP4uiv(int type, int[] coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoordP1uiv(int texture, int type, int[] coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoordP2uiv(int texture, int type, int[] coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoordP3uiv(int texture, int type, int[] coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoordP4uiv(int texture, int type, int[] coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glNormalP3uiv(int type, int[] coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glColorP3uiv(int type, int[] color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glColorP4uiv(int type, int[] color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColorP3uiv(int type, int[] color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribP1uiv(int index, int type, boolean normalized, int[] value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribP2uiv(int index, int type, boolean normalized, int[] value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribP3uiv(int index, int type, boolean normalized, int[] value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glVertexAttribP4uiv(int index, int type, boolean normalized, int[] value) {
        throw new UnsupportedOperationException();
    }

}