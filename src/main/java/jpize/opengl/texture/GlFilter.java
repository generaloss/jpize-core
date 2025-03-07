package jpize.opengl.texture;

import jpize.util.Utils;

import java.util.HashMap;
import java.util.Map;

import static jpize.opengl.gl.GLI11.*;

public enum GlFilter {

    NEAREST                (GL_NEAREST               ), // 972_8
    LINEAR                 (GL_LINEAR                ), // 972_9

    NEAREST_MIPMAP_NEAREST (GL_NEAREST_MIPMAP_NEAREST), // 998_4
    LINEAR_MIPMAP_NEAREST  (GL_LINEAR_MIPMAP_NEAREST ), // 998_5
    NEAREST_MIPMAP_LINEAR  (GL_NEAREST_MIPMAP_LINEAR ), // 998_6
    LINEAR_MIPMAP_LINEAR   (GL_LINEAR_MIPMAP_LINEAR  ); // 998_7

    public final int value;

    GlFilter(int value) {
        this.value = value;
    }


    public static GlFilter byValue(int value) {
        return BY_VALUE.get(value);
    }

    private static final Map<Integer, GlFilter> BY_VALUE = Utils.make(new HashMap<>(), map -> {
        for(GlFilter e: values())
            map.put(e.value, e);
    });

}
