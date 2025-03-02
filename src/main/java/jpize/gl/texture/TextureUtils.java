package jpize.gl.texture;

import jpize.util.pixmap.PixmapRGBA;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class TextureUtils {

    private static final Map<Long, Texture2D> WHITE_TEXTURE_BY_CONTEXT = new HashMap<>();

    public static Texture2D whiteTexture() {
        final long context = GLFW.glfwGetCurrentContext();
        if(!WHITE_TEXTURE_BY_CONTEXT.containsKey(context)) {
            final PixmapRGBA pixmap = new PixmapRGBA(1, 1);
            pixmap.clearRGB(0xFFFFFF);
            final Texture2D texture = new Texture2D(pixmap);
            pixmap.dispose();
            WHITE_TEXTURE_BY_CONTEXT.put(context, texture);
        }
        return WHITE_TEXTURE_BY_CONTEXT.get(context);
    }

    private static void _dispose() { // calls from ContextManager (112)
        for(Texture2D texture: WHITE_TEXTURE_BY_CONTEXT.values())
            texture.dispose();
    }

}
