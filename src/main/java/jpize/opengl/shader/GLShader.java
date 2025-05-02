package jpize.opengl.shader;

import jpize.context.Jpize;
import jpize.opengl.GLObject;
import jpize.opengl.gl.GL20I;
import jpize.opengl.type.GLBool;

public class GLShader extends GLObject {

    public GLShader(GLShaderType type) {
        super(Jpize.GL20.glCreateShader(type.value));
    }


    public CharSequence getInfoLog() {
        return Jpize.GL20.glGetShaderInfoLog(ID);
    }

    public void logError(String error) {
        final CharSequence log = this.getInfoLog();
        if(!log.isEmpty()){
            throw new RuntimeException(error + ":\n" + log);
        }else{
            throw new RuntimeException(error);
        }
    }


    public String getSource() {
        return Jpize.GL20.glGetShaderSource(ID);
    }

    public void setSource(CharSequence string) {
        Jpize.GL20.glShaderSource(ID, string);
    }

    public void setSource(CharSequence... strings) {
        Jpize.GL20.glShaderSource(ID, strings);
    }

    /// LU: public void setSource(PointerBuffer strings, int[] lenghts) {
    /// LU:     Jpize.GL20.glShaderSource(ID, strings, lenghts);
    /// LU: }

    /// LU: public void setSource(PointerBuffer strings, IntBuffer lenghts) {
    /// LU:     Jpize.GL20.glShaderSource(ID, strings, lenghts);
    /// LU: }


    public void compile() {
        Jpize.GL20.glCompileShader(ID);
    }

    public void checkCompileError() {
        if(!this.getCompileStatus())
            this.logError("Compiling shader error");
    }


    public GLShaderType getType() {
        return GLShaderType.byValue(Jpize.GL20.glGetShaderi(ID, GL20I.GL_SHADER_TYPE));
    }

    public int getSourceLength() {
        return Jpize.GL20.glGetShaderi(ID, GL20I.GL_SHADER_SOURCE_LENGTH);
    }

    public int getInfoLogLength() {
        return Jpize.GL20.glGetShaderi(ID, GL20I.GL_INFO_LOG_LENGTH);
    }

    public boolean getCompileStatus() {
        return GLBool.of(Jpize.GL20.glGetShaderi(ID, GL20I.GL_COMPILE_STATUS));
    }

    public boolean getDeleteStatus() {
        return GLBool.of(Jpize.GL20.glGetShaderi(ID, GL20I.GL_DELETE_STATUS));
    }


    @Override
    public void dispose() {
        Jpize.GL20.glDeleteShader(ID);
        if(!this.getDeleteStatus())
            System.err.println("Failed to delete shader " + ID);
    }


    public static boolean isShader(int ID) {
        return Jpize.GL20.glIsShader(ID);
    }

}
