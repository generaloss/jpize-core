package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL14I;
import org.lwjgl.opengl.GL14;

import java.nio.*;

public class LwjglGL14 extends LwjglGL13 implements GL14I {

    public static final LwjglGL14 INSTANCE = new LwjglGL14();

    protected LwjglGL14() { }


    @Override
    public void glBlendColor(float red, float green, float blue, float alpha) {
        GL14.glBlendColor(red, green, blue, alpha);
    }

    @Override
    public void glBlendEquation(int mode) {
        GL14.glBlendEquation(mode);
    }

    @Override
    public void glFogCoordf(float var0) {
        GL14.glFogCoordf(var0);
    }

    @Override
    public void glFogCoordd(double var0) {
        GL14.glFogCoordd(var0);
    }

    @Override
    public void glFogCoordfv(FloatBuffer coord) {
        GL14.glFogCoordfv(coord);
    }

    @Override
    public void glFogCoorddv(DoubleBuffer coord) {
        GL14.glFogCoorddv(coord);
    }

    @Override
    public void glFogCoordPointer(int type, int stride, ByteBuffer pointer) {
        GL14.glFogCoordPointer(type, stride, pointer);
    }

    @Override
    public void glFogCoordPointer(int type, int stride, long pointer) {
        GL14.glFogCoordPointer(type, stride, pointer);
    }

    @Override
    public void glFogCoordPointer(int type, int stride, ShortBuffer pointer) {
        GL14.glFogCoordPointer(type, stride, pointer);
    }

    @Override
    public void glFogCoordPointer(int type, int stride, FloatBuffer pointer) {
        GL14.glFogCoordPointer(type, stride, pointer);
    }

    @Override
    public void glMultiDrawArrays(int mode, IntBuffer first, IntBuffer count) {
        GL14.glMultiDrawArrays(mode, first, count);
    }

    @Override
    public void glPointParameterf(int pname, float param) {
        GL14.glPointParameterf(pname, param);
    }

    @Override
    public void glPointParameteri(int pname, int param) {
        GL14.glPointParameteri(pname, param);
    }

    @Override
    public void glPointParameterfv(int pname, FloatBuffer params) {
        GL14.glPointParameterfv(pname, params);
    }

    @Override
    public void glPointParameteriv(int pname, IntBuffer params) {
        GL14.glPointParameteriv(pname, params);
    }

    @Override
    public void glSecondaryColor3b(byte var0, byte var1, byte var2) {
        GL14.glSecondaryColor3b(var0, var1, var2);
    }

    @Override
    public void glSecondaryColor3s(short var0, short var1, short var2) {
        GL14.glSecondaryColor3s(var0, var1, var2);
    }

    @Override
    public void glSecondaryColor3i(int var0, int var1, int var2) {
        GL14.glSecondaryColor3i(var0, var1, var2);
    }

    @Override
    public void glSecondaryColor3f(float var0, float var1, float var2) {
        GL14.glSecondaryColor3f(var0, var1, var2);
    }

    @Override
    public void glSecondaryColor3d(double var0, double var2, double var4) {
        GL14.glSecondaryColor3d(var0, var2, var4);
    }

    @Override
    public void glSecondaryColor3ub(byte var0, byte var1, byte var2) {
        GL14.glSecondaryColor3ub(var0, var1, var2);
    }

    @Override
    public void glSecondaryColor3us(short var0, short var1, short var2) {
        GL14.glSecondaryColor3us(var0, var1, var2);
    }

    @Override
    public void glSecondaryColor3ui(int var0, int var1, int var2) {
        GL14.glSecondaryColor3ui(var0, var1, var2);
    }

    @Override
    public void glSecondaryColor3bv(ByteBuffer v) {
        GL14.glSecondaryColor3bv(v);
    }

    @Override
    public void glSecondaryColor3sv(ShortBuffer v) {
        GL14.glSecondaryColor3sv(v);
    }

    @Override
    public void glSecondaryColor3iv(IntBuffer v) {
        GL14.glSecondaryColor3iv(v);
    }

    @Override
    public void glSecondaryColor3fv(FloatBuffer v) {
        GL14.glSecondaryColor3fv(v);
    }

    @Override
    public void glSecondaryColor3dv(DoubleBuffer v) {
        GL14.glSecondaryColor3dv(v);
    }

    @Override
    public void glSecondaryColor3ubv(ByteBuffer v) {
        GL14.glSecondaryColor3ubv(v);
    }

    @Override
    public void glSecondaryColor3usv(ShortBuffer v) {
        GL14.glSecondaryColor3usv(v);
    }

    @Override
    public void glSecondaryColor3uiv(IntBuffer v) {
        GL14.glSecondaryColor3uiv(v);
    }

    @Override
    public void glSecondaryColorPointer(int size, int type, int stride, ByteBuffer pointer) {
        GL14.glSecondaryColorPointer(size, type, stride, pointer);
    }

    @Override
    public void glSecondaryColorPointer(int size, int type, int stride, long pointer) {
        GL14.glSecondaryColorPointer(size, type, stride, pointer);
    }

    @Override
    public void glSecondaryColorPointer(int size, int type, int stride, ShortBuffer pointer) {
        GL14.glSecondaryColorPointer(size, type, stride, pointer);
    }

    @Override
    public void glSecondaryColorPointer(int size, int type, int stride, IntBuffer pointer) {
        GL14.glSecondaryColorPointer(size, type, stride, pointer);
    }

    @Override
    public void glSecondaryColorPointer(int size, int type, int stride, FloatBuffer pointer) {
        GL14.glSecondaryColorPointer(size, type, stride, pointer);
    }

    @Override
    public void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) {
        GL14.glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
    }

    @Override
    public void glWindowPos2i(int var0, int var1) {
        GL14.glWindowPos2i(var0, var1);
    }

    @Override
    public void glWindowPos2s(short var0, short var1) {
        GL14.glWindowPos2s(var0, var1);
    }

    @Override
    public void glWindowPos2f(float var0, float var1) {
        GL14.glWindowPos2f(var0, var1);
    }

    @Override
    public void glWindowPos2d(double var0, double var2) {
        GL14.glWindowPos2d(var0, var2);
    }

    @Override
    public void glWindowPos2iv(IntBuffer p) {
        GL14.glWindowPos2iv(p);
    }

    @Override
    public void glWindowPos2sv(ShortBuffer p) {
        GL14.glWindowPos2sv(p);
    }

    @Override
    public void glWindowPos2fv(FloatBuffer p) {
        GL14.glWindowPos2fv(p);
    }

    @Override
    public void glWindowPos2dv(DoubleBuffer p) {
        GL14.glWindowPos2dv(p);
    }

    @Override
    public void glWindowPos3i(int var0, int var1, int var2) {
        GL14.glWindowPos3i(var0, var1, var2);
    }

    @Override
    public void glWindowPos3s(short var0, short var1, short var2) {
        GL14.glWindowPos3s(var0, var1, var2);
    }

    @Override
    public void glWindowPos3f(float var0, float var1, float var2) {
        GL14.glWindowPos3f(var0, var1, var2);
    }

    @Override
    public void glWindowPos3d(double var0, double var2, double var4) {
        GL14.glWindowPos3d(var0, var2, var4);
    }

    @Override
    public void glWindowPos3iv(IntBuffer p) {
        GL14.glWindowPos3iv(p);
    }

    @Override
    public void glWindowPos3sv(ShortBuffer p) {
        GL14.glWindowPos3sv(p);
    }

    @Override
    public void glWindowPos3fv(FloatBuffer p) {
        GL14.glWindowPos3fv(p);
    }

    @Override
    public void glWindowPos3dv(DoubleBuffer p) {
        GL14.glWindowPos3dv(p);
    }

    @Override
    public void glFogCoordfv(float[] coord) {
        GL14.glFogCoordfv(coord);
    }

    @Override
    public void glFogCoorddv(double[] coord) {
        GL14.glFogCoorddv(coord);
    }

    @Override
    public void glMultiDrawArrays(int mode, int[] first, int[] count) {
        GL14.glMultiDrawArrays(mode, first, count);
    }

    @Override
    public void glPointParameterfv(int pname, float[] params) {
        GL14.glPointParameterfv(pname, params);
    }

    @Override
    public void glPointParameteriv(int pname, int[] params) {
        GL14.glPointParameteriv(pname, params);
    }

    @Override
    public void glSecondaryColor3sv(short[] v) {
        GL14.glSecondaryColor3sv(v);
    }

    @Override
    public void glSecondaryColor3iv(int[] v) {
        GL14.glSecondaryColor3iv(v);
    }

    @Override
    public void glSecondaryColor3fv(float[] v) {
        GL14.glSecondaryColor3fv(v);
    }

    @Override
    public void glSecondaryColor3dv(double[] v) {
        GL14.glSecondaryColor3dv(v);
    }

    @Override
    public void glSecondaryColor3usv(short[] v) {
        GL14.glSecondaryColor3usv(v);
    }

    @Override
    public void glSecondaryColor3uiv(int[] v) {
        GL14.glSecondaryColor3uiv(v);
    }

    @Override
    public void glWindowPos2iv(int[] p) {
        GL14.glWindowPos2iv(p);
    }

    @Override
    public void glWindowPos2sv(short[] p) {
        GL14.glWindowPos2sv(p);
    }

    @Override
    public void glWindowPos2fv(float[] p) {
        GL14.glWindowPos2fv(p);
    }

    @Override
    public void glWindowPos2dv(double[] p) {
        GL14.glWindowPos2dv(p);
    }

    @Override
    public void glWindowPos3iv(int[] p) {
        GL14.glWindowPos3iv(p);
    }

    @Override
    public void glWindowPos3sv(short[] p) {
        GL14.glWindowPos3sv(p);
    }

    @Override
    public void glWindowPos3fv(float[] p) {
        GL14.glWindowPos3fv(p);
    }

    @Override
    public void glWindowPos3dv(double[] p) {
        GL14.glWindowPos3dv(p);
    }

}