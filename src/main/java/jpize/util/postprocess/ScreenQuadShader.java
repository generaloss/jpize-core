package jpize.util.postprocess;

import jpize.util.res.Resource;
import jpize.gl.texture.GlTexture2D;
import jpize.util.shader.Shader;

public class ScreenQuadShader {

    private static ScreenQuadShader instance;
    private final Shader shader;

    public ScreenQuadShader() {
        this.shader = new Shader(Resource.internal("/shader/screen/screen.vert"), Resource.internal("/shader/screen/screen.frag"));
    }

    public static void use(GlTexture2D texture) {
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
