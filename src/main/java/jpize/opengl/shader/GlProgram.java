package jpize.opengl.shader;

import jpize.app.Jpize;
import jpize.opengl.*;
import jpize.opengl.gl.GLI11;
import jpize.opengl.gl.GLI20;
import jpize.opengl.gl.GLI41;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

public class GlProgram extends GlObject {

    public GlProgram() {
        super(Jpize.GL20.glCreateProgram());
    }


    public void attach(int shaderID) {
        Jpize.GL20.glAttachShader(ID, shaderID);
    }

    public void attach(GlShader shader) {
        this.attach(shader.getID());
    }

    public int[] getAttached(int maxCount) {
        final int[] countBuf = new int[1];
        int[] shaderIDsBuf = new int[maxCount];
        Jpize.GL20.glGetAttachedShaders(ID, countBuf, shaderIDsBuf);
        return Arrays.copyOf(shaderIDsBuf, countBuf[0]);
    }

    public int[] getAttached() {
        return this.getAttached(3);
    }

    public void detach(int shaderID) {
        Jpize.GL20.glDetachShader(ID, shaderID);
    }

    public void detach(GlShader shader) {
        this.detach(shader.getID());
    }

    public void detachAll(int maxCount) {
        final int[] attached = this.getAttached(maxCount);
        for(int attachedID: attached)
            this.detach(attachedID);
    }

    public void detachAll() {
        this.detachAll(3);
    }


    public void link() {
        Jpize.GL20.glLinkProgram(ID);
        if(Jpize.GL20.glGetProgrami(ID, GLI20.GL_LINK_STATUS) == GLI11.GL_FALSE)
            this.logError("Linking shader error");

        Jpize.GL20.glValidateProgram(ID);
        if(Jpize.GL20.glGetProgrami(ID, GLI20.GL_VALIDATE_STATUS) == GLI11.GL_FALSE)
            this.logError("Validating shader error");
    }

    private void logError(String error) {
        final CharSequence log = Jpize.GL20.glGetProgramInfoLog(ID);
        if(!log.isEmpty()){
            throw new RuntimeException(error + ":\n" + log);
        }else{
            throw new RuntimeException(error);
        }
    }


    public void uniform(int location, float x) {
        Jpize.GL20.glUniform1f(location, x);
    }

    public void uniform(int location, int x) {
        Jpize.GL20.glUniform1i(location, x);
    }

    public void uniform(int location, float x, float y) {
        Jpize.GL20.glUniform2f(location, x, y);
    }

    public void uniform(int location, int x, int y) {
        Jpize.GL20.glUniform2i(location, x, y);
    }

    public void uniform(int location, float x, float y, float z) {
        Jpize.GL20.glUniform3f(location, x, y, z);
    }

    public void uniform(int location, int x, int y, int z) {
        Jpize.GL20.glUniform3i(location, x, y, z);
    }

    public void uniform(int location, float x, float y, float z, float w) {
        Jpize.GL20.glUniform4f(location, x, y, z, w);
    }

    public void uniform(int location, int x, int y, int z, int w) {
        Jpize.GL20.glUniform4i(location, x, y, z, w);
    }


    public void uniformMat4(int location, boolean transpose, float[] values) {
        Jpize.GL20.glUniformMatrix4fv(location, transpose, values);
    }

    public void uniformMat4x3(int location, boolean transpose, float[] values) {
        Jpize.GL21.glUniformMatrix4x3fv(location, transpose, values);
    }

    public void uniformMat4x2(int location, boolean transpose, float[] values) {
        Jpize.GL21.glUniformMatrix4x2fv(location, transpose, values);
    }


    public void uniformMat3x4(int location, boolean transpose, float[] values) {
        Jpize.GL21.glUniformMatrix3x4fv(location, transpose, values);
    }

    public void uniformMat3(int location, boolean transpose, float[] values) {
        Jpize.GL20.glUniformMatrix3fv(location, transpose, values);
    }

    public void uniformMat3x2(int location, boolean transpose, float[] values) {
        Jpize.GL21.glUniformMatrix3x2fv(location, transpose, values);
    }


    public void uniformMat2x4(int location, boolean transpose, float[] values) {
        Jpize.GL21.glUniformMatrix2x4fv(location, transpose, values);
    }

    public void uniformMat2x3(int location, boolean transpose, float[] values) {
        Jpize.GL21.glUniformMatrix2x3fv(location, transpose, values);
    }

    public void uniformMat2(int location, boolean transpose, float[] values) {
        Jpize.GL20.glUniformMatrix2fv(location, transpose, values);
    }


    public void uniform(int location, float[] array) {
        Jpize.GL20.glUniform1fv(location, array);
    }

    public void uniform(int location, int[] array) {
        Jpize.GL20.glUniform1iv(location, array);
    }

    public void uniform(int location, FloatBuffer buffer) {
        Jpize.GL20.glUniform1fv(location, buffer);
    }

    public void uniform(int location, IntBuffer buffer) {
        Jpize.GL20.glUniform1iv(location, buffer);
    }


    public int getUniformLocation(CharSequence uniform) {
        return Jpize.GL20.glGetUniformLocation(ID, uniform);
    }

    public float getUniformFloat(int location) {
        return Jpize.GL20.glGetUniformf(ID, location);
    }

    public int getUniformInt(int location) {
        return Jpize.GL20.glGetUniformi(ID, location);
    }

    public void bindAttributeLocation(int index, CharSequence name) {
        Jpize.GL20.glBindAttribLocation(ID, index, name);
    }

    public void bindFragDataLocation(int index, CharSequence name) {
        Jpize.GL30.glBindFragDataLocation(ID, index, name);
    }


    public int getUniformIndices(CharSequence uniformName) {
        return Jpize.GL31.glGetUniformIndices(ID, uniformName);
    }

    public void getUniformIndices(CharSequence[] uniformNames, IntBuffer uniformIndices) {
        Jpize.GL31.glGetUniformIndices(ID, uniformNames, uniformIndices);
    }

    // public void getUniformIndices(PointerBuffer uniformNames, IntBuffer uniformIndices) {
    //     Jpize.GL31.glGetUniformIndices(ID, uniformNames, uniformIndices);
    // }

    // public void getUniformIndices(PointerBuffer uniformNames, int[] uniformIndices) {
    //     Jpize.GL31.glGetUniformIndices(ID, uniformNames, uniformIndices);
    // }


    public int getUniformBlockIndex(CharSequence uniformBlockName) {
        return Jpize.GL31.glGetUniformBlockIndex(ID, uniformBlockName);
    }

    public void uniformBlockBinding(int blockIndex, int blockBindingPoint) {
        Jpize.GL31.glUniformBlockBinding(ID, blockIndex, blockBindingPoint);
    }


    public void setBinaryRetrievable(boolean value) {
        Jpize.GL41.glProgramParameteri(ID, GLI41.GL_PROGRAM_BINARY_RETRIEVABLE_HINT, value ? 1 : 0);
    }

    public int getBinaryLength() {
        return Jpize.GL20.glGetProgrami(ID, GLI41.GL_PROGRAM_BINARY_LENGTH);
    }


    public void bind() {
        Jpize.GL20.glUseProgram(ID);
    }

    public static void unbind() {
        Jpize.GL20.glUseProgram(0);
    }


    @Override
    public void dispose() {
        Jpize.GL20.glDeleteProgram(ID);
    }

}
