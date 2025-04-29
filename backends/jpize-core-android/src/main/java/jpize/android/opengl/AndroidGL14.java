package jpize.android.opengl;

import android.opengl.*;
import jpize.opengl.gl.GL14I;
import java.nio.*;

public class AndroidGL14 extends AndroidGL13 implements GL14I {

    @Override
    public void glBlendColor(float red, float green, float blue, float alpha) {
        GLES20.glBlendColor(red, green, blue, alpha);
    }

    @Override
    public void glBlendEquation(int mode) {
        GLES20.glBlendEquation(mode);
    }

    @Override
    public void glFogCoordf(float var0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glFogCoordd(double var0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glFogCoordfv(FloatBuffer coord) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glFogCoorddv(DoubleBuffer coord) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glFogCoordPointer(int type, int stride, ByteBuffer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glFogCoordPointer(int type, int stride, long pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glFogCoordPointer(int type, int stride, ShortBuffer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glFogCoordPointer(int type, int stride, FloatBuffer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiDrawArrays(int mode, IntBuffer first, IntBuffer count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glPointParameterf(int pname, float param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glPointParameteri(int pname, int param) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glPointParameterfv(int pname, FloatBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glPointParameteriv(int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3b(byte var0, byte var1, byte var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3s(short var0, short var1, short var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3i(int var0, int var1, int var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3f(float var0, float var1, float var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3d(double var0, double var2, double var4) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3ub(byte var0, byte var1, byte var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3us(short var0, short var1, short var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3ui(int var0, int var1, int var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3bv(ByteBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3sv(ShortBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3iv(IntBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3fv(FloatBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3dv(DoubleBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3ubv(ByteBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3usv(ShortBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3uiv(IntBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColorPointer(int size, int type, int stride, ByteBuffer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColorPointer(int size, int type, int stride, long pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColorPointer(int size, int type, int stride, ShortBuffer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColorPointer(int size, int type, int stride, IntBuffer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColorPointer(int size, int type, int stride, FloatBuffer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) {
        GLES20.glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
    }

    @Override
    public void glWindowPos2i(int var0, int var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos2s(short var0, short var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos2f(float var0, float var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos2d(double var0, double var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos2iv(IntBuffer p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos2sv(ShortBuffer p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos2fv(FloatBuffer p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos2dv(DoubleBuffer p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos3i(int var0, int var1, int var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos3s(short var0, short var1, short var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos3f(float var0, float var1, float var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos3d(double var0, double var2, double var4) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos3iv(IntBuffer p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos3sv(ShortBuffer p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos3fv(FloatBuffer p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos3dv(DoubleBuffer p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glFogCoordfv(float[] coord) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glFogCoorddv(double[] coord) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiDrawArrays(int mode, int[] first, int[] count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glPointParameterfv(int pname, float[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glPointParameteriv(int pname, int[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3sv(short[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3iv(int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3fv(float[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3dv(double[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3usv(short[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSecondaryColor3uiv(int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos2iv(int[] p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos2sv(short[] p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos2fv(float[] p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos2dv(double[] p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos3iv(int[] p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos3sv(short[] p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos3fv(float[] p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glWindowPos3dv(double[] p) {
        throw new UnsupportedOperationException();
    }

}