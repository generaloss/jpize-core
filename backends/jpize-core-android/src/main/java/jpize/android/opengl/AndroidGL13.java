package jpize.android.opengl;

import android.opengl.*;
import jpize.opengl.gl.GL13I;
import java.nio.*;

public class AndroidGL13 extends AndroidGL12 implements GL13I {

    @Override
    public void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, long data) {
        GLES30.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, (int) data);
    }

    @Override
    public void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, ByteBuffer data) {
        GLES30.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, data.limit(), data);
    }

    @Override
    public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, long data) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, ByteBuffer data) {
        GLES10.glCompressedTexImage2D(target, level, internalformat, width, height, border, data.limit(), data);
    }

    @Override
    public void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, long data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, ByteBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, long data) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer data) {
        GLES30.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, data.limit(), data);
    }

    @Override
    public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, long data) {
        throw new UnsupportedOperationException(); // args
    }

    @Override
    public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer data) {
        GLES10.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, data.limit(), data);
    }

    @Override
    public void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, long data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, ByteBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetCompressedTexImage(int target, int level, ByteBuffer pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glGetCompressedTexImage(int target, int level, long pixels) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glSampleCoverage(float value, boolean invert) {
        GLES10.glSampleCoverage(value, invert);
    }

    @Override
    public void glActiveTexture(int texture) {
        GLES10.glActiveTexture(texture);
    }

    @Override
    public void glClientActiveTexture(int var0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord1f(int var0, float var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord1s(int var0, short var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord1i(int var0, int var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord1d(int var0, double var1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord1fv(int texture, FloatBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord1sv(int texture, ShortBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord1iv(int texture, IntBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord1dv(int texture, DoubleBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord2f(int var0, float var1, float var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord2s(int var0, short var1, short var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord2i(int var0, int var1, int var2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord2d(int var0, double var1, double var3) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord2fv(int texture, FloatBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord2sv(int texture, ShortBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord2iv(int texture, IntBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord2dv(int texture, DoubleBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord3f(int var0, float var1, float var2, float var3) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord3s(int var0, short var1, short var2, short var3) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord3i(int var0, int var1, int var2, int var3) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord3d(int var0, double var1, double var3, double var5) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord3fv(int texture, FloatBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord3sv(int texture, ShortBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord3iv(int texture, IntBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord3dv(int texture, DoubleBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord4f(int var0, float var1, float var2, float var3, float var4) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord4s(int var0, short var1, short var2, short var3, short var4) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord4i(int var0, int var1, int var2, int var3, int var4) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord4d(int var0, double var1, double var3, double var5, double var7) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord4fv(int texture, FloatBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord4sv(int texture, ShortBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord4iv(int texture, IntBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord4dv(int texture, DoubleBuffer v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glLoadTransposeMatrixf(FloatBuffer m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glLoadTransposeMatrixd(DoubleBuffer m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultTransposeMatrixf(FloatBuffer m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultTransposeMatrixd(DoubleBuffer m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord1fv(int texture, float[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord1sv(int texture, short[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord1iv(int texture, int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord1dv(int texture, double[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord2fv(int texture, float[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord2sv(int texture, short[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord2iv(int texture, int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord2dv(int texture, double[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord3fv(int texture, float[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord3sv(int texture, short[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord3iv(int texture, int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord3dv(int texture, double[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord4fv(int texture, float[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord4sv(int texture, short[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord4iv(int texture, int[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultiTexCoord4dv(int texture, double[] v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glLoadTransposeMatrixf(float[] m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glLoadTransposeMatrixd(double[] m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultTransposeMatrixf(float[] m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void glMultTransposeMatrixd(double[] m) {
        throw new UnsupportedOperationException();
    }

}