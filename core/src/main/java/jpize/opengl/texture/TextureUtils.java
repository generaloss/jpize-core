package jpize.opengl.texture;

import jpize.util.pixmap.PixmapRGBA;
import java.util.HashMap;
import java.util.Map;

public class TextureUtils {

    private static final Map<Long, Texture2D> WHITE_TEXTURE_BY_THREAD = new HashMap<>();

    public static Texture2D whiteTexture() {
        final long threadID = Thread.currentThread().getId();
        if(!WHITE_TEXTURE_BY_THREAD.containsKey(threadID)) {
            final PixmapRGBA pixmap = new PixmapRGBA(1, 1);
            pixmap.clearRGB(0xFFFFFF);
            final Texture2D texture = new Texture2D(pixmap);
            pixmap.dispose();
            WHITE_TEXTURE_BY_THREAD.put(threadID, texture);
        }
        return WHITE_TEXTURE_BY_THREAD.get(threadID);
    }

    private static void _dispose() { // calls from ContextManager (112)
        for(Texture2D texture: WHITE_TEXTURE_BY_THREAD.values())
            texture.dispose();
    }

}
