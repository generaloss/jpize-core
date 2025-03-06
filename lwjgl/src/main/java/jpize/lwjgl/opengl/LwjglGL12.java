package jpize.lwjgl.opengl;

import jpize.opengl.gl.GLI12;
import org.lwjgl.opengl.GL12;
import java.nio.*;

public class LwjglGL12 extends LwjglGL11 implements GLI12 {

    @Override
    public void nglTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, long pixels) {
        GL12.nglTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) {
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, long pixels) {
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) {
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) {
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels) {
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, DoubleBuffer pixels) {
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void nglTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels) {
        GL12.nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) {
        GL12.glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height);
    }

    @Override
    public void nglDrawRangeElements(int mode, int start, int end, int count, int type, long indices) {
        GL12.nglDrawRangeElements(mode, start, end, count, type, indices);
    }

    @Override
    public void glDrawRangeElements(int mode, int start, int end, int count, int type, long indices) {
        GL12.glDrawRangeElements(mode, start, end, count, type, indices);
    }

    @Override
    public void glDrawRangeElements(int mode, int start, int end, int type, ByteBuffer indices) {
        GL12.glDrawRangeElements(mode, start, end, type, indices);
    }

    @Override
    public void glDrawRangeElements(int mode, int start, int end, ByteBuffer indices) {
        GL12.glDrawRangeElements(mode, start, end, indices);
    }

    @Override
    public void glDrawRangeElements(int mode, int start, int end, ShortBuffer indices) {
        GL12.glDrawRangeElements(mode, start, end, indices);
    }

    @Override
    public void glDrawRangeElements(int mode, int start, int end, IntBuffer indices) {
        GL12.glDrawRangeElements(mode, start, end, indices);
    }

    @Override
    public void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, short[] pixels) {
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, int[] pixels) {
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, float[] pixels) {
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, double[] pixels) {
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, short[] pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int[] pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, float[] pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, double[] pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

}