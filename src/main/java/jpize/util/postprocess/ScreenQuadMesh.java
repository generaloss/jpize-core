package jpize.util.postprocess;

import jpize.app.Context;
import jpize.app.Jpize;
import jpize.gl.type.GlType;
import jpize.util.mesh.IndexedMesh;
import jpize.gl.vertex.GlVertAttr;

import java.util.HashMap;
import java.util.Map;

public class ScreenQuadMesh {

    private final IndexedMesh mesh;

    private ScreenQuadMesh() {
        this.mesh = new IndexedMesh(
            new GlVertAttr(2, GlType.FLOAT), // position
            new GlVertAttr(2, GlType.FLOAT)  // texcoord
        );
        this.mesh.vertices().setData(
            -1F,  1F,  0F, 0F, // 0
            -1F, -1F,  0F, 1F, // 1
             1F, -1F,  1F, 1F, // 2
             1F,  1F,  1F, 0F  // 3
        );
        this.mesh.indices().setData(
            0, 1, 2,
            2, 3, 0
        );
    }


    private static final Map<Context, ScreenQuadMesh> BY_CONTEXT = new HashMap<>();

    public static void render() {
        final Context context = Jpize.context();

        if(!BY_CONTEXT.containsKey(context))
            BY_CONTEXT.put(context, new ScreenQuadMesh());

        BY_CONTEXT.get(context).mesh.render();
    }

    private static void dispose() {
        for(ScreenQuadMesh quad: BY_CONTEXT.values())
            quad.mesh.dispose();
    }

}
