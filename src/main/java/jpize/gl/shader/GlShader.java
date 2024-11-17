package jpize.gl.shader;

import jpize.gl.GlObject;

import static org.lwjgl.opengl.GL20.*;

public class GlShader extends GlObject {

    public GlShader(String code, GlShaderType type) {
        super(glCreateShader(type.value));
        glShaderSource(ID, code);
        glCompileShader(ID);

        if(glGetShaderi(ID, GL_COMPILE_STATUS) == GL_FALSE)
            this.logError();
    }

    private void logError() {
        final String log = glGetShaderInfoLog(ID);
        throw new RuntimeException("Compiling shader error" + (log.isEmpty() ? "" : (":\n" + log)));
    }

    public GlShaderType getType() {
        return GlShaderType.byValue(glGetShaderi(ID, GL_SHADER_TYPE));
    }

    public String getSource() {
        return glGetShaderSource(ID);
    }

    @Override
    public void dispose() {
        glDeleteShader(ID);
    }

}
