package jpize.util.postprocess;

import jpize.gl.type.GlType;
import jpize.util.mesh.IndexedMesh;
import jpize.gl.vertex.GlVertAttr;

public class ScreenQuad {

    private static ScreenQuad instance;
    private final IndexedMesh mesh;

    private ScreenQuad() {
        this.mesh = new IndexedMesh(new GlVertAttr(2, GlType.FLOAT), new GlVertAttr(2, GlType.FLOAT)); // pos, uv
        this.mesh.getBuffer().setData(new float[]{
            -1, +1, 0, 1, // 0
            -1, -1, 0, 0, // 1
            +1, -1, 1, 0, // 2
            +1, +1, 1, 1, // 3
        });
        this.mesh.getIndexBuffer().setData(0, 1, 2, 2, 3, 0);
    }

    public static void render() {
        if(instance == null)
            instance = new ScreenQuad();
        instance.mesh.render();
    }

    private static void dispose() { // calls from ContextManager
        if(instance != null)
            instance.mesh.dispose();
    }

}
