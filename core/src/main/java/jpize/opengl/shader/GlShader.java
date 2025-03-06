package jpize.opengl.shader;

import jpize.app.Jpize;
import jpize.opengl.GlObject;
import jpize.opengl.gl.GLI20;

public class GlShader extends GlObject {

    public GlShader(GlShaderType type) {
        super(Jpize.GL20.glCreateShader(type.value));
    }

    public GlShaderType getType() {
        return GlShaderType.byValue(Jpize.GL20.glGetShaderi(ID, GLI20.GL_SHADER_TYPE));
    }


    public int getSourceLength() {
        return Jpize.GL20.glGetShaderi(ID, GLI20.GL_SHADER_SOURCE_LENGTH);
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

    // public void setSource(PointerBuffer strings, int[] lenghts) {
    //     Jpize.GL20.glShaderSource(ID, strings, lenghts);
    // }

    // public void setSource(PointerBuffer strings, IntBuffer lenghts) {
    //     Jpize.GL20.glShaderSource(ID, strings, lenghts);
    // }


    public int getInfoLogLength() {
        return Jpize.GL20.glGetShaderi(ID, GLI20.GL_INFO_LOG_LENGTH);
    }

    public String getInfoLog() {
        return Jpize.GL20.glGetShaderInfoLog(ID);
    }


    public boolean getCompileStatus() {
        return (Jpize.GL20.glGetShaderi(ID, GLI20.GL_COMPILE_STATUS) == GLI20.GL_TRUE);
    }

    public void compile() {
        Jpize.GL20.glCompileShader(ID);
    }

    public void compileAndCheckError() {
        this.compile();
        if(!this.getCompileStatus()) {
            final String log = this.getInfoLog();
            throw new RuntimeException("Failed to compile shader" + (log.isEmpty() ? "" : (":\n" + log)));
        }
    }


    public boolean getDeleteStatus() {
        return (Jpize.GL20.glGetShaderi(ID, GLI20.GL_DELETE_STATUS) == GLI20.GL_TRUE);
    }

    @Override
    public void dispose() {
        Jpize.GL20.glDeleteShader(ID);
        if(!getDeleteStatus())
            System.err.println("Failed to delete shader " + ID);
    }


    public static boolean isShader(int ID) {
        return Jpize.GL20.glIsShader(ID);
    }

}
