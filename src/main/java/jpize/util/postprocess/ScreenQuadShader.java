package jpize.util.postprocess;

import jpize.util.res.Resource;
import jpize.gl.texture.Texture2D;
import jpize.gl.shader.Shader;

public class ScreenQuadShader {

    private static ScreenQuadShader instance;
    private final Shader shader;

    private ScreenQuadShader() {
        this.shader = new Shader(Resource.internal("/shader/screen/screen.vert"), Resource.internal("/shader/screen/screen.frag"));
    }

    public static void use(Texture2D texture) {
        if(instance == null)
            instance = new ScreenQuadShader();

        instance.shader.bind();
        instance.shader.uniform("u_texture", texture);
    }

    private static void dispose() { // calls from ContextManager
        if(instance != null)
            instance.shader.dispose();
    }

}
