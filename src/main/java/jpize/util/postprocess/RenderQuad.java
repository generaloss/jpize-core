package jpize.util.postprocess;

import jpize.app.Context;
import jpize.app.Jpize;
import jpize.gl.shader.Shader;
import jpize.gl.texture.Texture2D;
import jpize.gl.type.GlType;
import jpize.gl.vertex.GlVertAttr;
import jpize.util.Disposable;
import jpize.util.mesh.IndexedMesh;
import jpize.util.res.Resource;

import java.util.HashMap;
import java.util.Map;

public class RenderQuad implements Disposable {

    private final IndexedMesh mesh;
    private final Shader shader;

    public RenderQuad(float[] vertices, int[] indices) {
        this.mesh = new IndexedMesh(
                new GlVertAttr(2, GlType.FLOAT), // position
                new GlVertAttr(2, GlType.FLOAT)  // texcoord
        );
        this.mesh.vertices().setData(vertices);
        this.mesh.indices().setData(indices);
        this.shader = new Shader(
                Resource.internal("/shader/render_quad/vert.glsl"),
                Resource.internal("/shader/render_quad/frag.glsl")
        );
    }

    public void render() {
        mesh.render();
    }

    public void render(Shader shader, Texture2D texture) {
        shader.bind();
        shader.uniform("u_texture", texture);
        this.render();
    }

    public void render(Texture2D texture) {
        this.render(shader, texture);
    }

    @Override
    public void dispose() {
        mesh.dispose();
        shader.dispose();
    }


    private static final Map<Context, RenderQuad> BY_CONTEXT = new HashMap<>();

    public static RenderQuad instance() {
        final Context context = Jpize.context();

        if(!BY_CONTEXT.containsKey(context))
            BY_CONTEXT.put(context, new RenderQuad(
                new float[] {
                    -1F,  1F,  0F, 0F, // 0
                    -1F, -1F,  0F, 1F, // 1
                     1F, -1F,  1F, 1F, // 2
                     1F,  1F,  1F, 0F, // 3
                },
                new int[] {
                    0, 1, 2,
                    2, 3, 0,
                }
            )
        );

        return BY_CONTEXT.get(context);
    }

    private static void _dispose() { // calls from ContextManager (113)
        for(RenderQuad quad: BY_CONTEXT.values())
            quad.dispose();
    }

}
