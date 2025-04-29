package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL21I;
import org.lwjgl.opengl.GL21;
import java.nio.*;

public class LwjglGL21 extends LwjglGL20 implements GL21I {

    @Override
    public void glUniformMatrix2x3fv(int location, boolean transpose, FloatBuffer value) {
        GL21.glUniformMatrix2x3fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix3x2fv(int location, boolean transpose, FloatBuffer value) {
        GL21.glUniformMatrix3x2fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix2x4fv(int location, boolean transpose, FloatBuffer value) {
        GL21.glUniformMatrix2x4fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix4x2fv(int location, boolean transpose, FloatBuffer value) {
        GL21.glUniformMatrix4x2fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix3x4fv(int location, boolean transpose, FloatBuffer value) {
        GL21.glUniformMatrix3x4fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix4x3fv(int location, boolean transpose, FloatBuffer value) {
        GL21.glUniformMatrix4x3fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix2x3fv(int location, boolean transpose, float[] value) {
        GL21.glUniformMatrix2x3fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix3x2fv(int location, boolean transpose, float[] value) {
        GL21.glUniformMatrix3x2fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix2x4fv(int location, boolean transpose, float[] value) {
        GL21.glUniformMatrix2x4fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix4x2fv(int location, boolean transpose, float[] value) {
        GL21.glUniformMatrix4x2fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix3x4fv(int location, boolean transpose, float[] value) {
        GL21.glUniformMatrix3x4fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix4x3fv(int location, boolean transpose, float[] value) {
        GL21.glUniformMatrix4x3fv(location, transpose, value);
    }

}