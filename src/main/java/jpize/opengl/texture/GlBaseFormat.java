package jpize.opengl.texture;

import jpize.util.Utils;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL33.*;

public enum GlBaseFormat {

    ALPHA           (GL_ALPHA          , 1), // 6406

    RED             (GL_RED            , 1), // 6403
    RED_INTEGER     (GL_RED_INTEGER    , 1), // 36244

    RG              (GL_RG             , 2), // 33319
    RG_INTEGER      (GL_RG_INTEGER     , 2), // 33320

    RGB             (GL_RGB            , 3), // 6407
    RGB_INTEGER     (GL_RGB_INTEGER    , 3), // 36248
    BGR             (GL_BGR            , 3), // 32992
    BGR_INTEGER     (GL_BGR_INTEGER    , 3), // 36250

    RGBA            (GL_RGBA           , 4), // 6408
    RGBA_INTEGER    (GL_RGBA_INTEGER   , 4), // 36249
    BGRA            (GL_BGRA           , 4), // 32993
    BGRA_INTEGER    (GL_BGRA_INTEGER   , 4), // 36251
    
    STENCIL_INDEX   (GL_STENCIL_INDEX  , 1), // 6401
    DEPTH_COMPONENT (GL_DEPTH_COMPONENT, 1), // 6402
    DEPTH_STENCIL   (GL_DEPTH_STENCIL  , 1); // 34041


    public final int value;
    public final int channels;

    GlBaseFormat(int value, int channels) {
        this.value = value;
        this.channels = channels;
    }


    private static final Map<Integer, GlBaseFormat> BY_VALUE = Utils.make(new HashMap<>(), map -> {
        for(GlBaseFormat e: values())
            map.put(e.value, e);
    });

    public static GlBaseFormat byValue(int value) {
        return BY_VALUE.get(value);
    }

}
