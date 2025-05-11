package jpize.android.opengl;

import android.opengl.*;
import jpize.opengl.gl.GL21I;
import java.nio.*;

public class AndroidGL21 extends AndroidGL20 implements GL21I {

    public static final AndroidGL21 INSTANCE = new AndroidGL21();

    protected AndroidGL21() { }


    @Override
    public void glUniformMatrix2x3fv(int location, boolean transpose, FloatBuffer value) {
        GLES30.glUniformMatrix2x3fv(location, value.limit(), transpose, value);
    }

    @Override
    public void glUniformMatrix3x2fv(int location, boolean transpose, FloatBuffer value) {
        GLES30.glUniformMatrix3x2fv(location, value.limit(), transpose, value);
    }

    @Override
    public void glUniformMatrix2x4fv(int location, boolean transpose, FloatBuffer value) {
        GLES30.glUniformMatrix2x4fv(location, value.limit(), transpose, value);
    }

    @Override
    public void glUniformMatrix4x2fv(int location, boolean transpose, FloatBuffer value) {
        GLES30.glUniformMatrix4x2fv(location, value.limit(), transpose, value);
    }

    @Override
    public void glUniformMatrix3x4fv(int location, boolean transpose, FloatBuffer value) {
        GLES30.glUniformMatrix3x4fv(location, value.limit(), transpose, value);
    }

    @Override
    public void glUniformMatrix4x3fv(int location, boolean transpose, FloatBuffer value) {
        GLES30.glUniformMatrix4x3fv(location, value.limit(), transpose, value);
    }

    @Override
    public void glUniformMatrix2x3fv(int location, boolean transpose, float[] value) {
        GLES30.glUniformMatrix2x3fv(location, value.length, transpose, value, 0);
    }

    @Override
    public void glUniformMatrix3x2fv(int location, boolean transpose, float[] value) {
        GLES30.glUniformMatrix3x2fv(location, value.length, transpose, value, 0);
    }

    @Override
    public void glUniformMatrix2x4fv(int location, boolean transpose, float[] value) {
        GLES30.glUniformMatrix2x4fv(location, value.length, transpose, value, 0);
    }

    @Override
    public void glUniformMatrix4x2fv(int location, boolean transpose, float[] value) {
        GLES30.glUniformMatrix4x2fv(location, value.length, transpose, value, 0);
    }

    @Override
    public void glUniformMatrix3x4fv(int location, boolean transpose, float[] value) {
        GLES30.glUniformMatrix3x4fv(location, value.length, transpose, value, 0);
    }

    @Override
    public void glUniformMatrix4x3fv(int location, boolean transpose, float[] value) {
        GLES30.glUniformMatrix4x3fv(location, value.length, transpose, value, 0);
    }

}