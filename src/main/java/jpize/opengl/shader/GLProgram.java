package jpize.opengl.shader;

import jpize.context.Jpize;
import jpize.opengl.*;
import jpize.opengl.gl.GL20I;
import jpize.opengl.gl.GL41I;
import jpize.opengl.type.GLBool;

import java.nio.IntBuffer;
import java.util.Arrays;

public class GLProgram extends GLObject {

    public GLProgram() {
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

    public void attach(GLShader shader) {
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

    public void detach(GLShader shader) {
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
        return GLBool.of(Jpize.GL20.glGetProgrami(ID, GL20I.GL_LINK_STATUS));
    }

    public void checkLinkError() {
        if(!this.getLinkStatus())
            this.logError("Linking shader error");
    }


    public void validate() {
        Jpize.GL20.glValidateProgram(ID);
    }

    public boolean getValidateStatus() {
        return GLBool.of(Jpize.GL20.glGetProgrami(ID, GL20I.GL_VALIDATE_STATUS));
    }

    public void checkValidateError() {
        if(!this.getValidateStatus())
            this.logError("Validating shader error");
    }


    public int getActiveUniforms() {
        return Jpize.GL20.glGetProgrami(ID, GL20I.GL_ACTIVE_UNIFORMS);
    }

    public String getActiveUniformName(int index) {
        return Jpize.GL31.glGetActiveUniformName(ID, index);
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
        Jpize.GL41.glProgramParameteri(ID, GL41I.GL_PROGRAM_BINARY_RETRIEVABLE_HINT, GLBool.by(value));
    }

    public int getBinaryLength() {
        return Jpize.GL20.glGetProgrami(ID, GL41I.GL_PROGRAM_BINARY_LENGTH);
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



    public static void uniformFloat(int location, float x) {
        Jpize.GL20.glUniform1f(location, x);
    }

    public static void uniformDouble(int location, double x) {
        Jpize.GL40.glUniform1d(location, x);
    }

    public static void uniformInt(int location, int x) {
        Jpize.GL20.glUniform1i(location, x);
    }

    public static void uniformUint(int location, int x) {
        Jpize.GL30.glUniform1ui(location, x);
    }

    public static void uniformVec2f(int location, float x, float y) {
        Jpize.GL20.glUniform2f(location, x, y);
    }

    public static void uniformVec2d(int location, double x, double y) {
        Jpize.GL40.glUniform2d(location, x, y);
    }

    public static void uniformVec2i(int location, int x, int y) {
        Jpize.GL20.glUniform2i(location, x, y);
    }

    public static void uniformVec2ui(int location, int x, int y) {
        Jpize.GL30.glUniform2ui(location, x, y);
    }

    public static void uniformVec3f(int location, float x, float y, float z) {
        Jpize.GL20.glUniform3f(location, x, y, z);
    }

    public static void uniformVec3d(int location, double x, double y, double z) {
        Jpize.GL40.glUniform3d(location, x, y, z);
    }

    public static void uniformVec3i(int location, int x, int y, int z) {
        Jpize.GL20.glUniform3i(location, x, y, z);
    }

    public static void uniformVec3ui(int location, int x, int y, int z) {
        Jpize.GL30.glUniform3ui(location, x, y, z);
    }

    public static void uniformVec4f(int location, float x, float y, float z, float w) {
        Jpize.GL20.glUniform4f(location, x, y, z, w);
    }

    public static void uniformVec4d(int location, double x, double y, double z, double w) {
        Jpize.GL40.glUniform4d(location, x, y, z, w);
    }

    public static void uniformVec4i(int location, int x, int y, int z, int w) {
        Jpize.GL20.glUniform4i(location, x, y, z, w);
    }

    public static void uniformVec4ui(int location, int x, int y, int z, int w) {
        Jpize.GL30.glUniform4ui(location, x, y, z, w);
    }


    public static void uniformFloats(int location, float[] values) {
        Jpize.GL20.glUniform1fv(location, values);
    }

    public static void uniformDoubles(int location, double[] values) {
        Jpize.GL40.glUniform1dv(location, values);
    }

    public static void uniformInts(int location, int[] values) {
        Jpize.GL20.glUniform1iv(location, values);
    }

    public static void uniformUints(int location, int[] values) {
        Jpize.GL30.glUniform1uiv(location, values);
    }

    public static void uniformVecs2f(int location, float[] values) {
        Jpize.GL20.glUniform2fv(location, values);
    }

    public static void uniformVecs2d(int location, double[] values) {
        Jpize.GL40.glUniform2dv(location, values);
    }

    public static void uniformVecs2i(int location, int[] values) {
        Jpize.GL20.glUniform2iv(location, values);
    }

    public static void uniformVecs2ui(int location, int[] values) {
        Jpize.GL30.glUniform2uiv(location, values);
    }

    public static void uniformVecs3f(int location, float[] values) {
        Jpize.GL20.glUniform3fv(location, values);
    }

    public static void uniformVecs3d(int location, double[] values) {
        Jpize.GL40.glUniform3dv(location, values);
    }

    public static void uniformVecs3i(int location, int[] values) {
        Jpize.GL20.glUniform3iv(location, values);
    }

    public static void uniformVecs3ui(int location, int[] values) {
        Jpize.GL30.glUniform3uiv(location, values);
    }

    public static void uniformVecs4f(int location, float[] values) {
        Jpize.GL20.glUniform4fv(location, values);
    }

    public static void uniformVecs4d(int location, double[] values) {
        Jpize.GL40.glUniform4dv(location, values);
    }

    public static void uniformVecs4i(int location, int[] values) {
        Jpize.GL20.glUniform4iv(location, values);
    }

    public static void uniformVecs4ui(int location, int[] values) {
        Jpize.GL30.glUniform4uiv(location, values);
    }


    public static void uniformMat2f(int location, boolean transpose, float[] values) {
        Jpize.GL20.glUniformMatrix2fv(location, transpose, values);
    }

    public static void uniformMat2d(int location, boolean transpose, double[] values) {
        Jpize.GL40.glUniformMatrix2dv(location, transpose, values);
    }

    public static void uniformMat2x3f(int location, boolean transpose, float[] values) {
        Jpize.GL21.glUniformMatrix2x3fv(location, transpose, values);
    }

    public static void uniformMat2x3d(int location, boolean transpose, double[] values) {
        Jpize.GL40.glUniformMatrix2x3dv(location, transpose, values);
    }

    public static void uniformMat2x4f(int location, boolean transpose, float[] values) {
        Jpize.GL21.glUniformMatrix2x4fv(location, transpose, values);
    }

    public static void uniformMat2x4d(int location, boolean transpose, double[] values) {
        Jpize.GL40.glUniformMatrix2x4dv(location, transpose, values);
    }


    public static void uniformMat3x2f(int location, boolean transpose, float[] values) {
        Jpize.GL21.glUniformMatrix3x2fv(location, transpose, values);
    }

    public static void uniformMat3x2d(int location, boolean transpose, double[] values) {
        Jpize.GL40.glUniformMatrix3x2dv(location, transpose, values);
    }

    public static void uniformMat3f(int location, boolean transpose, float[] values) {
        Jpize.GL20.glUniformMatrix3fv(location, transpose, values);
    }

    public static void uniformMat3d(int location, boolean transpose, double[] values) {
        Jpize.GL40.glUniformMatrix3dv(location, transpose, values);
    }

    public static void uniformMat3x4f(int location, boolean transpose, float[] values) {
        Jpize.GL21.glUniformMatrix3x4fv(location, transpose, values);
    }

    public static void uniformMat3x4d(int location, boolean transpose, double[] values) {
        Jpize.GL40.glUniformMatrix3x4dv(location, transpose, values);
    }


    public static void uniformMat4x2f(int location, boolean transpose, float[] values) {
        Jpize.GL21.glUniformMatrix4x2fv(location, transpose, values);
    }

    public static void uniformMat4x2d(int location, boolean transpose, double[] values) {
        Jpize.GL40.glUniformMatrix4x2dv(location, transpose, values);
    }

    public static void uniformMat4x3f(int location, boolean transpose, float[] values) {
        Jpize.GL21.glUniformMatrix4x3fv(location, transpose, values);
    }

    public static void uniformMat4x3d(int location, boolean transpose, double[] values) {
        Jpize.GL40.glUniformMatrix4x3dv(location, transpose, values);
    }

    public static void uniformMat4f(int location, boolean transpose, float[] values) {
        Jpize.GL20.glUniformMatrix4fv(location, transpose, values);
    }

    public static void uniformMat4d(int location, boolean transpose, double[] values) {
        Jpize.GL40.glUniformMatrix4dv(location, transpose, values);
    }

}
