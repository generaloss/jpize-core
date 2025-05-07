package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL13I;
import org.lwjgl.opengl.GL13;
import java.nio.*;

public class LwjglGL13 extends LwjglGL12 implements GL13I {

    public static final LwjglGL13 INSTANCE = new LwjglGL13();

    protected LwjglGL13() { }


    @Override
    public void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, long data) {
        GL13.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imageSize, data);
    }

    @Override
    public void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, ByteBuffer data) {
        GL13.glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, data);
    }

    @Override
    public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, long data) {
        GL13.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
    }

    @Override
    public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, ByteBuffer data) {
        GL13.glCompressedTexImage2D(target, level, internalformat, width, height, border, data);
    }

    @Override
    public void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, long data) {
        GL13.glCompressedTexImage1D(target, level, internalformat, width, border, imageSize, data);
    }

    @Override
    public void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, ByteBuffer data) {
        GL13.glCompressedTexImage1D(target, level, internalformat, width, border, data);
    }

    @Override
    public void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, long data) {
        GL13.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data);
    }

    @Override
    public void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer data) {
        GL13.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, data);
    }

    @Override
    public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, long data) {
        GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
    }

    @Override
    public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer data) {
        GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, data);
    }

    @Override
    public void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, long data) {
        GL13.glCompressedTexSubImage1D(target, level, xoffset, width, format, imageSize, data);
    }

    @Override
    public void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, ByteBuffer data) {
        GL13.glCompressedTexSubImage1D(target, level, xoffset, width, format, data);
    }

    @Override
    public void glGetCompressedTexImage(int target, int level, ByteBuffer pixels) {
        GL13.glGetCompressedTexImage(target, level, pixels);
    }

    @Override
    public void glGetCompressedTexImage(int target, int level, long pixels) {
        GL13.glGetCompressedTexImage(target, level, pixels);
    }

    @Override
    public void glSampleCoverage(float value, boolean invert) {
        GL13.glSampleCoverage(value, invert);
    }

    @Override
    public void glActiveTexture(int texture) {
        GL13.glActiveTexture(texture);
    }

    @Override
    public void glClientActiveTexture(int var0) {
        GL13.glClientActiveTexture(var0);
    }

    @Override
    public void glMultiTexCoord1f(int var0, float var1) {
        GL13.glMultiTexCoord1f(var0, var1);
    }

    @Override
    public void glMultiTexCoord1s(int var0, short var1) {
        GL13.glMultiTexCoord1s(var0, var1);
    }

    @Override
    public void glMultiTexCoord1i(int var0, int var1) {
        GL13.glMultiTexCoord1i(var0, var1);
    }

    @Override
    public void glMultiTexCoord1d(int var0, double var1) {
        GL13.glMultiTexCoord1d(var0, var1);
    }

    @Override
    public void glMultiTexCoord1fv(int texture, FloatBuffer v) {
        GL13.glMultiTexCoord1fv(texture, v);
    }

    @Override
    public void glMultiTexCoord1sv(int texture, ShortBuffer v) {
        GL13.glMultiTexCoord1sv(texture, v);
    }

    @Override
    public void glMultiTexCoord1iv(int texture, IntBuffer v) {
        GL13.glMultiTexCoord1iv(texture, v);
    }

    @Override
    public void glMultiTexCoord1dv(int texture, DoubleBuffer v) {
        GL13.glMultiTexCoord1dv(texture, v);
    }

    @Override
    public void glMultiTexCoord2f(int var0, float var1, float var2) {
        GL13.glMultiTexCoord2f(var0, var1, var2);
    }

    @Override
    public void glMultiTexCoord2s(int var0, short var1, short var2) {
        GL13.glMultiTexCoord2s(var0, var1, var2);
    }

    @Override
    public void glMultiTexCoord2i(int var0, int var1, int var2) {
        GL13.glMultiTexCoord2i(var0, var1, var2);
    }

    @Override
    public void glMultiTexCoord2d(int var0, double var1, double var3) {
        GL13.glMultiTexCoord2d(var0, var1, var3);
    }

    @Override
    public void glMultiTexCoord2fv(int texture, FloatBuffer v) {
        GL13.glMultiTexCoord2fv(texture, v);
    }

    @Override
    public void glMultiTexCoord2sv(int texture, ShortBuffer v) {
        GL13.glMultiTexCoord2sv(texture, v);
    }

    @Override
    public void glMultiTexCoord2iv(int texture, IntBuffer v) {
        GL13.glMultiTexCoord2iv(texture, v);
    }

    @Override
    public void glMultiTexCoord2dv(int texture, DoubleBuffer v) {
        GL13.glMultiTexCoord2dv(texture, v);
    }

    @Override
    public void glMultiTexCoord3f(int var0, float var1, float var2, float var3) {
        GL13.glMultiTexCoord3f(var0, var1, var2, var3);
    }

    @Override
    public void glMultiTexCoord3s(int var0, short var1, short var2, short var3) {
        GL13.glMultiTexCoord3s(var0, var1, var2, var3);
    }

    @Override
    public void glMultiTexCoord3i(int var0, int var1, int var2, int var3) {
        GL13.glMultiTexCoord3i(var0, var1, var2, var3);
    }

    @Override
    public void glMultiTexCoord3d(int var0, double var1, double var3, double var5) {
        GL13.glMultiTexCoord3d(var0, var1, var3, var5);
    }

    @Override
    public void glMultiTexCoord3fv(int texture, FloatBuffer v) {
        GL13.glMultiTexCoord3fv(texture, v);
    }

    @Override
    public void glMultiTexCoord3sv(int texture, ShortBuffer v) {
        GL13.glMultiTexCoord3sv(texture, v);
    }

    @Override
    public void glMultiTexCoord3iv(int texture, IntBuffer v) {
        GL13.glMultiTexCoord3iv(texture, v);
    }

    @Override
    public void glMultiTexCoord3dv(int texture, DoubleBuffer v) {
        GL13.glMultiTexCoord3dv(texture, v);
    }

    @Override
    public void glMultiTexCoord4f(int var0, float var1, float var2, float var3, float var4) {
        GL13.glMultiTexCoord4f(var0, var1, var2, var3, var4);
    }

    @Override
    public void glMultiTexCoord4s(int var0, short var1, short var2, short var3, short var4) {
        GL13.glMultiTexCoord4s(var0, var1, var2, var3, var4);
    }

    @Override
    public void glMultiTexCoord4i(int var0, int var1, int var2, int var3, int var4) {
        GL13.glMultiTexCoord4i(var0, var1, var2, var3, var4);
    }

    @Override
    public void glMultiTexCoord4d(int var0, double var1, double var3, double var5, double var7) {
        GL13.glMultiTexCoord4d(var0, var1, var3, var5, var7);
    }

    @Override
    public void glMultiTexCoord4fv(int texture, FloatBuffer v) {
        GL13.glMultiTexCoord4fv(texture, v);
    }

    @Override
    public void glMultiTexCoord4sv(int texture, ShortBuffer v) {
        GL13.glMultiTexCoord4sv(texture, v);
    }

    @Override
    public void glMultiTexCoord4iv(int texture, IntBuffer v) {
        GL13.glMultiTexCoord4iv(texture, v);
    }

    @Override
    public void glMultiTexCoord4dv(int texture, DoubleBuffer v) {
        GL13.glMultiTexCoord4dv(texture, v);
    }

    @Override
    public void glLoadTransposeMatrixf(FloatBuffer m) {
        GL13.glLoadTransposeMatrixf(m);
    }

    @Override
    public void glLoadTransposeMatrixd(DoubleBuffer m) {
        GL13.glLoadTransposeMatrixd(m);
    }

    @Override
    public void glMultTransposeMatrixf(FloatBuffer m) {
        GL13.glMultTransposeMatrixf(m);
    }

    @Override
    public void glMultTransposeMatrixd(DoubleBuffer m) {
        GL13.glMultTransposeMatrixd(m);
    }

    @Override
    public void glMultiTexCoord1fv(int texture, float[] v) {
        GL13.glMultiTexCoord1fv(texture, v);
    }

    @Override
    public void glMultiTexCoord1sv(int texture, short[] v) {
        GL13.glMultiTexCoord1sv(texture, v);
    }

    @Override
    public void glMultiTexCoord1iv(int texture, int[] v) {
        GL13.glMultiTexCoord1iv(texture, v);
    }

    @Override
    public void glMultiTexCoord1dv(int texture, double[] v) {
        GL13.glMultiTexCoord1dv(texture, v);
    }

    @Override
    public void glMultiTexCoord2fv(int texture, float[] v) {
        GL13.glMultiTexCoord2fv(texture, v);
    }

    @Override
    public void glMultiTexCoord2sv(int texture, short[] v) {
        GL13.glMultiTexCoord2sv(texture, v);
    }

    @Override
    public void glMultiTexCoord2iv(int texture, int[] v) {
        GL13.glMultiTexCoord2iv(texture, v);
    }

    @Override
    public void glMultiTexCoord2dv(int texture, double[] v) {
        GL13.glMultiTexCoord2dv(texture, v);
    }

    @Override
    public void glMultiTexCoord3fv(int texture, float[] v) {
        GL13.glMultiTexCoord3fv(texture, v);
    }

    @Override
    public void glMultiTexCoord3sv(int texture, short[] v) {
        GL13.glMultiTexCoord3sv(texture, v);
    }

    @Override
    public void glMultiTexCoord3iv(int texture, int[] v) {
        GL13.glMultiTexCoord3iv(texture, v);
    }

    @Override
    public void glMultiTexCoord3dv(int texture, double[] v) {
        GL13.glMultiTexCoord3dv(texture, v);
    }

    @Override
    public void glMultiTexCoord4fv(int texture, float[] v) {
        GL13.glMultiTexCoord4fv(texture, v);
    }

    @Override
    public void glMultiTexCoord4sv(int texture, short[] v) {
        GL13.glMultiTexCoord4sv(texture, v);
    }

    @Override
    public void glMultiTexCoord4iv(int texture, int[] v) {
        GL13.glMultiTexCoord4iv(texture, v);
    }

    @Override
    public void glMultiTexCoord4dv(int texture, double[] v) {
        GL13.glMultiTexCoord4dv(texture, v);
    }

    @Override
    public void glLoadTransposeMatrixf(float[] m) {
        GL13.glLoadTransposeMatrixf(m);
    }

    @Override
    public void glLoadTransposeMatrixd(double[] m) {
        GL13.glLoadTransposeMatrixd(m);
    }

    @Override
    public void glMultTransposeMatrixf(float[] m) {
        GL13.glMultTransposeMatrixf(m);
    }

    @Override
    public void glMultTransposeMatrixd(double[] m) {
        GL13.glMultTransposeMatrixd(m);
    }

}