package jpize.util;

import jpize.util.pixmap.PixmapRGBA;
import jpize.gl.texture.GlTexture2D;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class TextureUtils {

    private static final Map<Long, GlTexture2D> W_T_BY_CONTEXT = new HashMap<>();

    public static GlTexture2D whiteTexture() {
        final long context = GLFW.glfwGetCurrentContext();
        if(!W_T_BY_CONTEXT.containsKey(context)) {
            final PixmapRGBA pixmap = new PixmapRGBA(1, 1);
            pixmap.setPixel(0, 0, 1D, 1D, 1D, 1D);
            W_T_BY_CONTEXT.put(context, new GlTexture2D(pixmap));
        }
        return W_T_BY_CONTEXT.get(context);
    }

    private static void dispose() { // calls from ContextManager
        for(GlTexture2D texture: W_T_BY_CONTEXT.values())
            texture.dispose();
    }

}
