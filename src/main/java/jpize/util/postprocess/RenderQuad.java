package jpize.util.postprocess;

import jpize.context.Jpize;
import jpize.opengl.shader.Shader;
import jpize.opengl.texture.Texture2D;
import jpize.opengl.type.GlType;
import jpize.opengl.vertex.GlVertAttr;
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


    private static final Map<Long, RenderQuad> BY_THREAD = new HashMap<>();

    public static RenderQuad instance() {
        final long threadID = Thread.currentThread().getId();
        if(!BY_THREAD.containsKey(threadID)) {
            final RenderQuad quad = new RenderQuad(new float[]{
                -1F,  1F,  0F, 0F, // 0
                -1F, -1F,  0F, 1F, // 1
                 1F, -1F,  1F, 1F, // 2
                 1F,  1F,  1F, 0F, // 3
            }, new int[]{
                0, 1, 2,
                2, 3, 0,
            });
            Jpize.callbacks.addCloseContext(quad::dispose);
            BY_THREAD.put(threadID, quad);
        }
        return BY_THREAD.get(threadID);
    }

}
