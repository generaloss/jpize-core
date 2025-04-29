package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL33I;
import org.lwjgl.opengl.GL33;
import java.nio.*;

public class LwjglGL33 extends LwjglGL32 implements GL33I {

    @Override
    public void glBindFragDataLocationIndexed(int program, int colorNumber, int index, ByteBuffer name) {
        GL33.glBindFragDataLocationIndexed(program, colorNumber, index, name);
    }

    @Override
    public void glBindFragDataLocationIndexed(int program, int colorNumber, int index, CharSequence name) {
        GL33.glBindFragDataLocationIndexed(program, colorNumber, index, name);
    }

    @Override
    public int glGetFragDataIndex(int program, ByteBuffer name) {
        return GL33.glGetFragDataIndex(program, name);
    }

    @Override
    public int glGetFragDataIndex(int program, CharSequence name) {
        return GL33.glGetFragDataIndex(program, name);
    }

    @Override
    public void glGenSamplers(IntBuffer samplers) {
        GL33.glGenSamplers(samplers);
    }

    @Override
    public int glGenSamplers() {
        return GL33.glGenSamplers();
    }

    @Override
    public void glDeleteSamplers(IntBuffer samplers) {
        GL33.glDeleteSamplers(samplers);
    }

    @Override
    public void glDeleteSamplers(int sampler) {
        GL33.glDeleteSamplers(sampler);
    }

    @Override
    public boolean glIsSampler(int sampler) {
        return GL33.glIsSampler(sampler);
    }

    @Override
    public void glBindSampler(int unit, int sampler) {
        GL33.glBindSampler(unit, sampler);
    }

    @Override
    public void glSamplerParameteri(int sampler, int pname, int param) {
        GL33.glSamplerParameteri(sampler, pname, param);
    }

    @Override
    public void glSamplerParameterf(int sampler, int pname, float param) {
        GL33.glSamplerParameterf(sampler, pname, param);
    }

    @Override
    public void glSamplerParameteriv(int sampler, int pname, IntBuffer params) {
        GL33.glSamplerParameteriv(sampler, pname, params);
    }

    @Override
    public void glSamplerParameterfv(int sampler, int pname, FloatBuffer params) {
        GL33.glSamplerParameterfv(sampler, pname, params);
    }

    @Override
    public void glSamplerParameterIiv(int sampler, int pname, IntBuffer params) {
        GL33.glSamplerParameterIiv(sampler, pname, params);
    }

    @Override
    public void glSamplerParameterIuiv(int sampler, int pname, IntBuffer params) {
        GL33.glSamplerParameterIuiv(sampler, pname, params);
    }

    @Override
    public void glGetSamplerParameteriv(int sampler, int pname, IntBuffer params) {
        GL33.glGetSamplerParameteriv(sampler, pname, params);
    }

    @Override
    public int glGetSamplerParameteri(int sampler, int pname) {
        return GL33.glGetSamplerParameteri(sampler, pname);
    }

    @Override
    public void glGetSamplerParameterfv(int sampler, int pname, FloatBuffer params) {
        GL33.glGetSamplerParameterfv(sampler, pname, params);
    }

    @Override
    public float glGetSamplerParameterf(int sampler, int pname) {
        return GL33.glGetSamplerParameterf(sampler, pname);
    }

    @Override
    public void glGetSamplerParameterIiv(int sampler, int pname, IntBuffer params) {
        GL33.glGetSamplerParameterIiv(sampler, pname, params);
    }

    @Override
    public int glGetSamplerParameterIi(int sampler, int pname) {
        return GL33.glGetSamplerParameterIi(sampler, pname);
    }

    @Override
    public void glGetSamplerParameterIuiv(int sampler, int pname, IntBuffer params) {
        GL33.glGetSamplerParameterIuiv(sampler, pname, params);
    }

    @Override
    public int glGetSamplerParameterIui(int sampler, int pname) {
        return GL33.glGetSamplerParameterIui(sampler, pname);
    }

    @Override
    public void glQueryCounter(int id, int target) {
        GL33.glQueryCounter(id, target);
    }

    @Override
    public void glGetQueryObjecti64v(int id, int pname, LongBuffer params) {
        GL33.glGetQueryObjecti64v(id, pname, params);
    }

    @Override
    public void glGetQueryObjecti64v(int id, int pname, long params) {
        GL33.glGetQueryObjecti64v(id, pname, params);
    }

    @Override
    public long glGetQueryObjecti64(int id, int pname) {
        return GL33.glGetQueryObjecti64(id, pname);
    }

    @Override
    public void glGetQueryObjectui64v(int id, int pname, LongBuffer params) {
        GL33.glGetQueryObjectui64v(id, pname, params);
    }

    @Override
    public void glGetQueryObjectui64v(int id, int pname, long params) {
        GL33.glGetQueryObjectui64v(id, pname, params);
    }

    @Override
    public long glGetQueryObjectui64(int id, int pname) {
        return GL33.glGetQueryObjectui64(id, pname);
    }

    @Override
    public void glVertexAttribDivisor(int index, int divisor) {
        GL33.glVertexAttribDivisor(index, divisor);
    }

    @Override
    public void glVertexP2ui(int var0, int var1) {
        GL33.glVertexP2ui(var0, var1);
    }

    @Override
    public void glVertexP3ui(int var0, int var1) {
        GL33.glVertexP3ui(var0, var1);
    }

    @Override
    public void glVertexP4ui(int var0, int var1) {
        GL33.glVertexP4ui(var0, var1);
    }

    @Override
    public void glVertexP2uiv(int type, IntBuffer value) {
        GL33.glVertexP2uiv(type, value);
    }

    @Override
    public void glVertexP3uiv(int type, IntBuffer value) {
        GL33.glVertexP3uiv(type, value);
    }

    @Override
    public void glVertexP4uiv(int type, IntBuffer value) {
        GL33.glVertexP4uiv(type, value);
    }

    @Override
    public void glTexCoordP1ui(int var0, int var1) {
        GL33.glTexCoordP1ui(var0, var1);
    }

    @Override
    public void glTexCoordP2ui(int var0, int var1) {
        GL33.glTexCoordP2ui(var0, var1);
    }

    @Override
    public void glTexCoordP3ui(int var0, int var1) {
        GL33.glTexCoordP3ui(var0, var1);
    }

    @Override
    public void glTexCoordP4ui(int var0, int var1) {
        GL33.glTexCoordP4ui(var0, var1);
    }

    @Override
    public void glTexCoordP1uiv(int type, IntBuffer coords) {
        GL33.glTexCoordP1uiv(type, coords);
    }

    @Override
    public void glTexCoordP2uiv(int type, IntBuffer coords) {
        GL33.glTexCoordP2uiv(type, coords);
    }

    @Override
    public void glTexCoordP3uiv(int type, IntBuffer coords) {
        GL33.glTexCoordP3uiv(type, coords);
    }

    @Override
    public void glTexCoordP4uiv(int type, IntBuffer coords) {
        GL33.glTexCoordP4uiv(type, coords);
    }

    @Override
    public void glMultiTexCoordP1ui(int var0, int var1, int var2) {
        GL33.glMultiTexCoordP1ui(var0, var1, var2);
    }

    @Override
    public void glMultiTexCoordP2ui(int var0, int var1, int var2) {
        GL33.glMultiTexCoordP2ui(var0, var1, var2);
    }

    @Override
    public void glMultiTexCoordP3ui(int var0, int var1, int var2) {
        GL33.glMultiTexCoordP3ui(var0, var1, var2);
    }

    @Override
    public void glMultiTexCoordP4ui(int var0, int var1, int var2) {
        GL33.glMultiTexCoordP4ui(var0, var1, var2);
    }

    @Override
    public void glMultiTexCoordP1uiv(int texture, int type, IntBuffer coords) {
        GL33.glMultiTexCoordP1uiv(texture, type, coords);
    }

    @Override
    public void glMultiTexCoordP2uiv(int texture, int type, IntBuffer coords) {
        GL33.glMultiTexCoordP2uiv(texture, type, coords);
    }

    @Override
    public void glMultiTexCoordP3uiv(int texture, int type, IntBuffer coords) {
        GL33.glMultiTexCoordP3uiv(texture, type, coords);
    }

    @Override
    public void glMultiTexCoordP4uiv(int texture, int type, IntBuffer coords) {
        GL33.glMultiTexCoordP4uiv(texture, type, coords);
    }

    @Override
    public void glNormalP3ui(int var0, int var1) {
        GL33.glNormalP3ui(var0, var1);
    }

    @Override
    public void glNormalP3uiv(int type, IntBuffer coords) {
        GL33.glNormalP3uiv(type, coords);
    }

    @Override
    public void glColorP3ui(int var0, int var1) {
        GL33.glColorP3ui(var0, var1);
    }

    @Override
    public void glColorP4ui(int var0, int var1) {
        GL33.glColorP4ui(var0, var1);
    }

    @Override
    public void glColorP3uiv(int type, IntBuffer color) {
        GL33.glColorP3uiv(type, color);
    }

    @Override
    public void glColorP4uiv(int type, IntBuffer color) {
        GL33.glColorP4uiv(type, color);
    }

    @Override
    public void glSecondaryColorP3ui(int var0, int var1) {
        GL33.glSecondaryColorP3ui(var0, var1);
    }

    @Override
    public void glSecondaryColorP3uiv(int type, IntBuffer color) {
        GL33.glSecondaryColorP3uiv(type, color);
    }

    @Override
    public void glVertexAttribP1ui(int index, int type, boolean normalized, int value) {
        GL33.glVertexAttribP1ui(index, type, normalized, value);
    }

    @Override
    public void glVertexAttribP2ui(int index, int type, boolean normalized, int value) {
        GL33.glVertexAttribP2ui(index, type, normalized, value);
    }

    @Override
    public void glVertexAttribP3ui(int index, int type, boolean normalized, int value) {
        GL33.glVertexAttribP3ui(index, type, normalized, value);
    }

    @Override
    public void glVertexAttribP4ui(int index, int type, boolean normalized, int value) {
        GL33.glVertexAttribP4ui(index, type, normalized, value);
    }

    @Override
    public void glVertexAttribP1uiv(int index, int type, boolean normalized, IntBuffer value) {
        GL33.glVertexAttribP1uiv(index, type, normalized, value);
    }

    @Override
    public void glVertexAttribP2uiv(int index, int type, boolean normalized, IntBuffer value) {
        GL33.glVertexAttribP2uiv(index, type, normalized, value);
    }

    @Override
    public void glVertexAttribP3uiv(int index, int type, boolean normalized, IntBuffer value) {
        GL33.glVertexAttribP3uiv(index, type, normalized, value);
    }

    @Override
    public void glVertexAttribP4uiv(int index, int type, boolean normalized, IntBuffer value) {
        GL33.glVertexAttribP4uiv(index, type, normalized, value);
    }

    @Override
    public void glGenSamplers(int[] samplers) {
        GL33.glGenSamplers(samplers);
    }

    @Override
    public void glDeleteSamplers(int[] samplers) {
        GL33.glDeleteSamplers(samplers);
    }

    @Override
    public void glSamplerParameteriv(int sampler, int pname, int[] params) {
        GL33.glSamplerParameteriv(sampler, pname, params);
    }

    @Override
    public void glSamplerParameterfv(int sampler, int pname, float[] params) {
        GL33.glSamplerParameterfv(sampler, pname, params);
    }

    @Override
    public void glSamplerParameterIiv(int sampler, int pname, int[] params) {
        GL33.glSamplerParameterIiv(sampler, pname, params);
    }

    @Override
    public void glSamplerParameterIuiv(int sampler, int pname, int[] params) {
        GL33.glSamplerParameterIuiv(sampler, pname, params);
    }

    @Override
    public void glGetSamplerParameteriv(int sampler, int pname, int[] params) {
        GL33.glGetSamplerParameteriv(sampler, pname, params);
    }

    @Override
    public void glGetSamplerParameterfv(int sampler, int pname, float[] params) {
        GL33.glGetSamplerParameterfv(sampler, pname, params);
    }

    @Override
    public void glGetSamplerParameterIiv(int sampler, int pname, int[] params) {
        GL33.glGetSamplerParameterIiv(sampler, pname, params);
    }

    @Override
    public void glGetSamplerParameterIuiv(int sampler, int pname, int[] params) {
        GL33.glGetSamplerParameterIuiv(sampler, pname, params);
    }

    @Override
    public void glGetQueryObjecti64v(int id, int pname, long[] params) {
        GL33.glGetQueryObjecti64v(id, pname, params);
    }

    @Override
    public void glGetQueryObjectui64v(int id, int pname, long[] params) {
        GL33.glGetQueryObjectui64v(id, pname, params);
    }

    @Override
    public void glVertexP2uiv(int type, int[] value) {
        GL33.glVertexP2uiv(type, value);
    }

    @Override
    public void glVertexP3uiv(int type, int[] value) {
        GL33.glVertexP3uiv(type, value);
    }

    @Override
    public void glVertexP4uiv(int type, int[] value) {
        GL33.glVertexP4uiv(type, value);
    }

    @Override
    public void glTexCoordP1uiv(int type, int[] coords) {
        GL33.glTexCoordP1uiv(type, coords);
    }

    @Override
    public void glTexCoordP2uiv(int type, int[] coords) {
        GL33.glTexCoordP2uiv(type, coords);
    }

    @Override
    public void glTexCoordP3uiv(int type, int[] coords) {
        GL33.glTexCoordP3uiv(type, coords);
    }

    @Override
    public void glTexCoordP4uiv(int type, int[] coords) {
        GL33.glTexCoordP4uiv(type, coords);
    }

    @Override
    public void glMultiTexCoordP1uiv(int texture, int type, int[] coords) {
        GL33.glMultiTexCoordP1uiv(texture, type, coords);
    }

    @Override
    public void glMultiTexCoordP2uiv(int texture, int type, int[] coords) {
        GL33.glMultiTexCoordP2uiv(texture, type, coords);
    }

    @Override
    public void glMultiTexCoordP3uiv(int texture, int type, int[] coords) {
        GL33.glMultiTexCoordP3uiv(texture, type, coords);
    }

    @Override
    public void glMultiTexCoordP4uiv(int texture, int type, int[] coords) {
        GL33.glMultiTexCoordP4uiv(texture, type, coords);
    }

    @Override
    public void glNormalP3uiv(int type, int[] coords) {
        GL33.glNormalP3uiv(type, coords);
    }

    @Override
    public void glColorP3uiv(int type, int[] color) {
        GL33.glColorP3uiv(type, color);
    }

    @Override
    public void glColorP4uiv(int type, int[] color) {
        GL33.glColorP4uiv(type, color);
    }

    @Override
    public void glSecondaryColorP3uiv(int type, int[] color) {
        GL33.glSecondaryColorP3uiv(type, color);
    }

    @Override
    public void glVertexAttribP1uiv(int index, int type, boolean normalized, int[] value) {
        GL33.glVertexAttribP1uiv(index, type, normalized, value);
    }

    @Override
    public void glVertexAttribP2uiv(int index, int type, boolean normalized, int[] value) {
        GL33.glVertexAttribP2uiv(index, type, normalized, value);
    }

    @Override
    public void glVertexAttribP3uiv(int index, int type, boolean normalized, int[] value) {
        GL33.glVertexAttribP3uiv(index, type, normalized, value);
    }

    @Override
    public void glVertexAttribP4uiv(int index, int type, boolean normalized, int[] value) {
        GL33.glVertexAttribP4uiv(index, type, normalized, value);
    }

}