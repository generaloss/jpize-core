package jpize.opengl.shader;

import static jpize.opengl.gl.GL32I.*;

public enum GLShaderType {

    FRAGMENT (GL_FRAGMENT_SHADER), // 35632
    VERTEX   (GL_VERTEX_SHADER  ), // 35633
    GEOMETRY (GL_GEOMETRY_SHADER); // 36313

    public final int value;

    GLShaderType(int value) {
        this.value = value;
    }

    public static GLShaderType byValue(int value) {
        return switch(value) {
            case GL_FRAGMENT_SHADER -> FRAGMENT;
            case GL_VERTEX_SHADER -> VERTEX;
            case GL_GEOMETRY_SHADER -> GEOMETRY;
            default -> throw new IllegalArgumentException("Unknown GlShaderType value: " + value);
        };
    }

}
