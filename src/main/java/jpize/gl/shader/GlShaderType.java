package jpize.gl.shader;


import static org.lwjgl.opengl.GL32.*;

public enum GlShaderType {

    FRAGMENT (GL_FRAGMENT_SHADER), // 35632
    VERTEX   (GL_VERTEX_SHADER  ), // 35633
    GEOMETRY (GL_GEOMETRY_SHADER); // 36313

    public final int value;

    GlShaderType(int value) {
        this.value = value;
    }

}
