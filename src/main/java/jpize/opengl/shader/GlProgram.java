package jpize.opengl.shader;

import jpize.context.Jpize;
import jpize.opengl.*;
import jpize.opengl.gl.GL20I;
import jpize.opengl.gl.GL41I;
import jpize.opengl.texture.Texture2D;
import jpize.opengl.texture.Texture2DArray;
import jpize.opengl.texture.TextureCubemap;
import jpize.opengl.type.GlBool;
import jpize.util.color.AbstractColor;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

public class GlProgram extends GlObject {

    public GlProgram() {
        super(Jpize.GL20.glCreateProgram());
    }


    public CharSequence getInfoLog() {
        return Jpize.GL20.glGetProgramInfoLog(ID);
    }

    public void logError(String error) {
        final CharSequence log = this.getInfoLog();
        if(!log.isEmpty()){
            throw new RuntimeException(error + ":\n" + log);
        }else{
            throw new RuntimeException(error);
        }
    }


    public void attach(int shaderID) {
        Jpize.GL20.glAttachShader(ID, shaderID);
    }

    public void attach(GlShader shader) {
        this.attach(shader.getID());
    }

    public int[] getAttached(int maxCount) {
        final int[] countBuf = new int[1];
        int[] shaderIdsBuf = new int[maxCount];
        Jpize.GL20.glGetAttachedShaders(ID, countBuf, shaderIdsBuf);
        return Arrays.copyOf(shaderIdsBuf, countBuf[0]);
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


    public void link() {
        Jpize.GL20.glLinkProgram(ID);
    }

    public boolean getLinkStatus() {
        return GlBool.of(Jpize.GL20.glGetProgrami(ID, GL20I.GL_LINK_STATUS));
    }

    public void checkLinkError() {
        if(!this.getLinkStatus())
            this.logError("Linking shader error");
    }


    public void validate() {
        Jpize.GL20.glValidateProgram(ID);
    }

    public boolean getValidateStatus() {
        return GlBool.of(Jpize.GL20.glGetProgrami(ID, GL20I.GL_VALIDATE_STATUS));
    }

    public void checkValidateError() {
        if(!this.getValidateStatus())
            this.logError("Validating shader error");
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


    public int getUniformIndices(CharSequence uniformName) {
        return Jpize.GL31.glGetUniformIndices(ID, uniformName);
    }

    public void getUniformIndices(CharSequence[] uniformNames, IntBuffer uniformIndices) {
        Jpize.GL31.glGetUniformIndices(ID, uniformNames, uniformIndices);
    }

    /// LU: public void getUniformIndices(PointerBuffer uniformNames, IntBuffer uniformIndices) {
    /// LU:     Jpize.GL31.glGetUniformIndices(ID, uniformNames, uniformIndices);
    /// LU: }

    /// LU: public void getUniformIndices(PointerBuffer uniformNames, int[] uniformIndices) {
    /// LU:     Jpize.GL31.glGetUniformIndices(ID, uniformNames, uniformIndices);
    /// LU: }


    public void bindAttributeLocation(int index, CharSequence name) {
        Jpize.GL20.glBindAttribLocation(ID, index, name);
    }

    public void bindFragDataLocation(int index, CharSequence name) {
        Jpize.GL30.glBindFragDataLocation(ID, index, name);
    }


    public int getUniformBlockIndex(CharSequence uniformBlockName) {
        return Jpize.GL31.glGetUniformBlockIndex(ID, uniformBlockName);
    }

    public void uniformBlockBinding(int blockIndex, int blockBindingPoint) {
        Jpize.GL31.glUniformBlockBinding(ID, blockIndex, blockBindingPoint);
    }


    public void setBinaryRetrievable(boolean value) {
        Jpize.GL41.glProgramParameteri(ID, GL41I.GL_PROGRAM_BINARY_RETRIEVABLE_HINT, GlBool.by(value));
    }

    public int getBinaryLength() {
        return Jpize.GL20.glGetProgrami(ID, GL41I.GL_PROGRAM_BINARY_LENGTH);
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


    public void uniform(int location, Texture2D sampler2D, int active) {
        sampler2D.active(active);
        this.uniform(location, active);
    }

    public void uniform(int location, Texture2DArray sampler2DArray, int active) {
        sampler2DArray.active(active);
        this.uniform(location, active);
    }

    public void uniform(int location, TextureCubemap samplerCube, int active) {
        samplerCube.active(active);
        this.uniform(location, active);
    }


    public void uniform(int location, AbstractColor color) {
        this.uniform(location, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
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
