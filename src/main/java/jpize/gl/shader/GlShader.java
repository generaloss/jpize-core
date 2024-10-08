package jpize.gl.shader;

import jpize.gl.GlObject;

import static org.lwjgl.opengl.GL20.*;

public class GlShader extends GlObject {

    public GlShader(String code, GlShaderType type) {
        super(glCreateShader(type.value));

        glShaderSource(ID, code);
        glCompileShader(ID);

        if(glGetShaderi(ID, GL_COMPILE_STATUS) == GL_FALSE)
            logError();
    }

    private void logError() {
        final String log = glGetShaderInfoLog(ID);
        if(!log.isEmpty())
            throw new RuntimeException("Compiling shader error" + ":\n" + log);
        else
            throw new RuntimeException("Compiling shader error");
    }

    @Override
    public void dispose() {
        glDeleteShader(ID);
    }

}
