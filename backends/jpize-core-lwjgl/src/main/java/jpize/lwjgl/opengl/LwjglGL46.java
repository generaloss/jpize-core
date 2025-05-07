package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL46I;
import org.lwjgl.opengl.GL46;
import java.nio.*;

public class LwjglGL46 extends LwjglGL45 implements GL46I {

    public static final LwjglGL46 INSTANCE = new LwjglGL46();

    protected LwjglGL46() { }


    @Override
    public void glMultiDrawArraysIndirectCount(int mode, ByteBuffer indirect, long drawcount, int maxdrawcount, int stride) {
        GL46.glMultiDrawArraysIndirectCount(mode, indirect, drawcount, maxdrawcount, stride);
    }

    @Override
    public void glMultiDrawArraysIndirectCount(int mode, long indirect, long drawcount, int maxdrawcount, int stride) {
        GL46.glMultiDrawArraysIndirectCount(mode, indirect, drawcount, maxdrawcount, stride);
    }

    @Override
    public void glMultiDrawArraysIndirectCount(int mode, IntBuffer indirect, long drawcount, int maxdrawcount, int stride) {
        GL46.glMultiDrawArraysIndirectCount(mode, indirect, drawcount, maxdrawcount, stride);
    }

    @Override
    public void glMultiDrawElementsIndirectCount(int mode, int type, ByteBuffer indirect, long drawcount, int maxdrawcount, int stride) {
        GL46.glMultiDrawElementsIndirectCount(mode, type, indirect, drawcount, maxdrawcount, stride);
    }

    @Override
    public void glMultiDrawElementsIndirectCount(int mode, int type, long indirect, long drawcount, int maxdrawcount, int stride) {
        GL46.glMultiDrawElementsIndirectCount(mode, type, indirect, drawcount, maxdrawcount, stride);
    }

    @Override
    public void glMultiDrawElementsIndirectCount(int mode, int type, IntBuffer indirect, long drawcount, int maxdrawcount, int stride) {
        GL46.glMultiDrawElementsIndirectCount(mode, type, indirect, drawcount, maxdrawcount, stride);
    }

    @Override
    public void glPolygonOffsetClamp(float factor, float units, float clamp) {
        GL46.glPolygonOffsetClamp(factor, units, clamp);
    }

    @Override
    public void glSpecializeShader(int shader, ByteBuffer pEntryPoint, IntBuffer pConstantIndex, IntBuffer pConstantValue) {
        GL46.glSpecializeShader(shader, pEntryPoint, pConstantIndex, pConstantValue);
    }

    @Override
    public void glSpecializeShader(int shader, CharSequence pEntryPoint, IntBuffer pConstantIndex, IntBuffer pConstantValue) {
        GL46.glSpecializeShader(shader, pEntryPoint, pConstantIndex, pConstantValue);
    }

    @Override
    public void glMultiDrawArraysIndirectCount(int mode, int[] indirect, long drawcount, int maxdrawcount, int stride) {
        GL46.glMultiDrawArraysIndirectCount(mode, indirect, drawcount, maxdrawcount, stride);
    }

    @Override
    public void glMultiDrawElementsIndirectCount(int mode, int type, int[] indirect, long drawcount, int maxdrawcount, int stride) {
        GL46.glMultiDrawElementsIndirectCount(mode, type, indirect, drawcount, maxdrawcount, stride);
    }

    @Override
    public void glSpecializeShader(int shader, ByteBuffer pEntryPoint, int[] pConstantIndex, int[] pConstantValue) {
        GL46.glSpecializeShader(shader, pEntryPoint, pConstantIndex, pConstantValue);
    }

    @Override
    public void glSpecializeShader(int shader, CharSequence pEntryPoint, int[] pConstantIndex, int[] pConstantValue) {
        GL46.glSpecializeShader(shader, pEntryPoint, pConstantIndex, pConstantValue);
    }

}