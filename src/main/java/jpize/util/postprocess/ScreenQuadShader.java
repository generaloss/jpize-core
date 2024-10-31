package jpize.util.postprocess;

import jpize.app.Context;
import jpize.app.Jpize;
import jpize.gl.shader.Shader;
import jpize.gl.texture.Texture2D;
import jpize.util.res.Resource;

import java.util.HashMap;
import java.util.Map;

public class ScreenQuadShader {

    private final Shader shader;

    private ScreenQuadShader() {
        this.shader = new Shader(
            Resource.internal("/shader/screen/screen.vert"),
            Resource.internal("/shader/screen/screen.frag")
        );
    }


    private static final Map<Context, ScreenQuadShader> BY_CONTEXT = new HashMap<>();

    public static void bind(Texture2D texture) {
        final Context context = Jpize.context();

        if(!BY_CONTEXT.containsKey(context))
            BY_CONTEXT.put(context, new ScreenQuadShader());

        final Shader shader = BY_CONTEXT.get(context).shader;
        shader.bind();
        shader.uniform("u_texture", texture);
    }

    public static void dispose() {
        for(ScreenQuadShader quad: BY_CONTEXT.values())
            quad.shader.dispose();
    }

}
