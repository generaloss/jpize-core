package jpize.util.shader;

import jpize.gl.shader.Shader;
import jpize.gl.texture.Texture2D;
import jpize.util.res.Resource;
import jpize.util.camera.Camera;
import jpize.util.color.Color;
import jpize.util.math.matrix.Matrix4f;

public class BaseShader extends Shader {
    
    private BaseShader(String path) {
        super(Resource.internal("/shader/base/" + path + ".vert"), Resource.internal("/shader/base/" + path + ".frag"));
    }


    public void setMatrices(Matrix4f combined) {
        super.uniform("u_combined", combined);
    }

    public void setMatrices(Matrix4f projection, Matrix4f view) {
        super.uniformMat4("u_combined", projection.getMul(view));
    }

    public void setMatrices(Camera camera) {
        setMatrices(camera.getCombined());
    }
    
    
    public void setColor(Color color) {
        super.uniform("u_color", color);
    }
    
    public void setColor(float r, float g, float b, float a) {
        super.uniform("u_color", r, g, b, a);
    }
    
    public void setColor(float r, float g, float b) {
        setColor(r, g, b, 1F);
    }


    public void setTexture(Texture2D texture) {
        super.uniform("u_texture", texture);
    }
    
    
    private static BaseShader pos2Color;
    
    /** Attributes: vec2 POSITION, vec4 COLOR */
    public static BaseShader getPos2Color() {
        if(pos2Color == null)
            pos2Color = new BaseShader("pos2-color");
        return pos2Color;
    }

    private static BaseShader pos2UvColor;

    /** Attributes: vec2 POSITION, vec4 COLOR */
    public static BaseShader getPos2UvColor() {
        if(pos2UvColor == null)
            pos2UvColor = new BaseShader("pos2-uv-color");
        return pos2UvColor;
    }
    
    private static BaseShader pos3Color;
    
    /** Attributes: vec3 POSITION, vec4 COLOR */
    public static BaseShader getPos3Color() {
        if(pos3Color == null)
            pos3Color = new BaseShader("pos3-color");
        return pos3Color;
    }
    
    private static BaseShader pos3UColor;
    
    /** Attributes: vec3 POSITION; Uniforms: vec4 COLOR */
    public static BaseShader getPos3UColor() {
        if(pos3UColor == null)
            pos3UColor = new BaseShader("pos3-ucolor");
        return pos3UColor;
    }
    
    
    private static void disposeShaders() { // calls from ContextManager
        if(pos2Color != null) pos2Color.dispose();
        if(pos2UvColor != null) pos2UvColor.dispose();
        if(pos3Color != null) pos3Color.dispose();
        if(pos3UColor != null) pos3UColor.dispose();
    }
    
}
