package jpize.util;

import jpize.util.pixmap.PixmapRGBA;
import jpize.gl.texture.GlTexture2D;

public class TextureUtils {

    private static GlTexture2D whitePixel;

    public static GlTexture2D quadTexture() {
        if(whitePixel == null){
            final PixmapRGBA pixmap = new PixmapRGBA(1, 1);
            pixmap.setPixel(0, 0, 1D, 1D, 1D, 1D);
            whitePixel = new GlTexture2D(pixmap);
        }
        return whitePixel;
    }

    private static void dispose() { // calls from ContextManager
        if(whitePixel != null)
            whitePixel.dispose();
    }

}
