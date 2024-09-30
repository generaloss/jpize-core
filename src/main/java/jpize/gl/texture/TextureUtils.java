package jpize.gl.texture;

import jpize.util.pixmap.PixmapRGBA;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class TextureUtils {

    private static final Map<Long, Texture2D> W_T_BY_CONTEXT = new HashMap<>();

    public static Texture2D whiteTexture() {
        final long context = GLFW.glfwGetCurrentContext();
        if(!W_T_BY_CONTEXT.containsKey(context)) {
            final PixmapRGBA pixmap = new PixmapRGBA(1, 1);
            pixmap.setPixel(0, 0, 1D, 1D, 1D, 1D);
            W_T_BY_CONTEXT.put(context, new Texture2D(pixmap));
        }
        return W_T_BY_CONTEXT.get(context);
    }

    private static void dispose() { // calls from ContextManager
        for(Texture2D texture: W_T_BY_CONTEXT.values())
            texture.dispose();
    }

}
