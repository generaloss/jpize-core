package jpize.android.opengl;

import android.opengl.GLES32;
import jpize.opengl.gl.GL44I;

import java.nio.*;

public class AndroidGL44 extends AndroidGL43 implements GL44I {

    @Override
    public void glBufferStorage(int target, long size, int flags) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glBufferStorage(int target, ByteBuffer data, int flags) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glBufferStorage(int target, ShortBuffer data, int flags) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glBufferStorage(int target, IntBuffer data, int flags) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glBufferStorage(int target, FloatBuffer data, int flags) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glBufferStorage(int target, DoubleBuffer data, int flags) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexImage(int texture, int level, int format, int type, ByteBuffer data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexImage(int texture, int level, int format, int type, ShortBuffer data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexImage(int texture, int level, int format, int type, IntBuffer data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexImage(int texture, int level, int format, int type, FloatBuffer data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexImage(int texture, int level, int format, int type, DoubleBuffer data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glBindBuffersBase(int target, int first, IntBuffer buffers) {
        for(int i = 0; i < buffers.remaining(); i++)
            GLES32.glBindBufferBase(target, first + i, buffers.get(i));
    }

    @Override
    public void glBindTextures(int first, IntBuffer textures) {
        for(int i = 0; i < textures.remaining(); i++)
            GLES32.glBindTexture(first + i, textures.get(i));
    }

    @Override
    public void glBindSamplers(int first, IntBuffer samplers) {
        for(int i = 0; i < samplers.remaining(); i++)
            GLES32.glBindSampler(first + i, samplers.get(i));
    }

    @Override
    public void glBindImageTextures(int first, IntBuffer textures) {
        throw new IllegalArgumentException();
        // implementation with fixed values: for(int i = 0; i < textures.remaining(); i++) GLES32.glBindImageTexture(first + i, textures.get(i), 0, false, 0, GLES32.GL_READ_WRITE, GLES32.GL_RGBA8);
    }

    @Override
    public void glBufferStorage(int target, short[] data, int flags) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glBufferStorage(int target, int[] data, int flags) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glBufferStorage(int target, float[] data, int flags) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glBufferStorage(int target, double[] data, int flags) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, short[] data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int[] data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, float[] data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, double[] data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexImage(int texture, int level, int format, int type, short[] data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexImage(int texture, int level, int format, int type, int[] data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexImage(int texture, int level, int format, int type, float[] data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glClearTexImage(int texture, int level, int format, int type, double[] data) {
        throw new IllegalArgumentException();
    }

    @Override
    public void glBindBuffersBase(int target, int first, int[] buffers) {
        for(int i = 0; i < buffers.length; i++)
            GLES32.glBindBufferBase(target, first + i, buffers[i]);
    }

    @Override
    public void glBindTextures(int first, int[] textures) {
        for(int i = 0; i < textures.length; i++)
            GLES32.glBindTexture(first + i, textures[i]);
    }

    @Override
    public void glBindSamplers(int first, int[] samplers) {
        for(int i = 0; i < samplers.length; i++)
            GLES32.glBindSampler(first + i, samplers[i]);
    }

    @Override
    public void glBindImageTextures(int first, int[] textures) {
        throw new IllegalArgumentException();
        // implementation with fixed values: for(int i = 0; i < textures.length; i++) GLES32.glBindImageTexture(first + i, textures[i], 0, false, 0, GLES32.GL_READ_WRITE, GLES32.GL_RGBA8);
    }

}