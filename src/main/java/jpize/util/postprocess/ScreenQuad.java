package jpize.util.postprocess;

import jpize.gl.type.GlType;
import jpize.util.mesh.IndexedMesh;
import jpize.gl.vertex.GlVertAttr;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class ScreenQuad {

    private final IndexedMesh mesh;

    private ScreenQuad() {
        this.mesh = new IndexedMesh(new GlVertAttr(2, GlType.FLOAT), new GlVertAttr(2, GlType.FLOAT)); // pos2, uv2
        this.mesh.getBuffer().setData(new float[]{
            -1, +1, 0, 1, // 0
            -1, -1, 0, 0, // 1
            +1, -1, 1, 0, // 2
            +1, +1, 1, 1, // 3
        });
        this.mesh.getIndexBuffer().setData(0, 1, 2, 2, 3, 0);
    }


    private static final Map<Long, ScreenQuad> BY_CONTEXT = new HashMap<>();

    public static void render() {
        final long context = GLFW.glfwGetCurrentContext();
        if(!BY_CONTEXT.containsKey(context))
            BY_CONTEXT.put(context, new ScreenQuad());

        BY_CONTEXT.get(context).mesh.render();
    }

    private static void dispose() { // calls from ContextManager
        for(ScreenQuad quad: BY_CONTEXT.values())
            quad.mesh.dispose();
    }

}
