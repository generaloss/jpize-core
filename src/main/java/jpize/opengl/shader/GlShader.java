package jpize.opengl.shader;

import jpize.opengl.GlObject;
import org.lwjgl.PointerBuffer;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;

public class GlShader extends GlObject {

    public GlShader(GlShaderType type) {
        super(glCreateShader(type.value));
    }

    public GlShaderType getType() {
        return GlShaderType.byValue(glGetShaderi(ID, GL_SHADER_TYPE));
    }


    public int getSourceLength() {
        return glGetShaderi(ID, GL_SHADER_SOURCE_LENGTH);
    }

    public String getSource() {
        return glGetShaderSource(ID);
    }

    public void setSource(CharSequence string) {
        glShaderSource(ID, string);
    }

    public void setSource(CharSequence... strings) {
        glShaderSource(ID, strings);
    }

    public void setSource(PointerBuffer strings, int[] lenghts) {
        glShaderSource(ID, strings, lenghts);
    }

    public void setSource(PointerBuffer strings, IntBuffer lenghts) {
        glShaderSource(ID, strings, lenghts);
    }


    public int getInfoLogLength() {
        return glGetShaderi(ID, GL_INFO_LOG_LENGTH);
    }

    public String getInfoLog() {
        return glGetShaderInfoLog(ID);
    }


    public boolean getCompileStatus() {
        return (glGetShaderi(ID, GL_COMPILE_STATUS) == GL_TRUE);
    }

    public void compile() {
        glCompileShader(ID);
    }

    public void compileAndCheckError() {
        this.compile();
        if(!this.getCompileStatus()) {
            final String log = this.getInfoLog();
            throw new RuntimeException("Failed to compile shader" + (log.isEmpty() ? "" : (":\n" + log)));
        }
    }


    public boolean getDeleteStatus() {
        return (glGetShaderi(ID, GL_DELETE_STATUS) == GL_TRUE);
    }

    @Override
    public void dispose() {
        glDeleteShader(ID);
        if(!getDeleteStatus())
            System.err.println("Failed to delete shader " + ID);
    }


    public static boolean isShader(int ID) {
        return glIsShader(ID);
    }

}
