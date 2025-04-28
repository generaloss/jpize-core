package jpize.opengl.type;

import jpize.util.Utils;

import java.util.HashMap;
import java.util.Map;

import static jpize.opengl.gl.GL41I.*;

public enum GlType {

    BYTE                         (GL_BYTE                        , 1), // 5120
    UNSIGNED_BYTE                (GL_UNSIGNED_BYTE               , 1), // 5121
    SHORT                        (GL_SHORT                       , 2), // 5122
    UNSIGNED_SHORT               (GL_UNSIGNED_SHORT              , 2), // 5123
    INT                          (GL_INT                         , 4), // 5124
    UNSIGNED_INT                 (GL_UNSIGNED_INT                , 4), // 5125
    FLOAT                        (GL_FLOAT                       , 4), // 5126
    DOUBLE                       (GL_DOUBLE                      , 8), // 5130
    HALF_FLOAT                   (GL_HALF_FLOAT                  , 2), // 5131
    FIXED                        (GL_FIXED                       , 4), // 5132

    UNSIGNED_INT_2_10_10_10_REV  (GL_UNSIGNED_INT_2_10_10_10_REV , 4), // 33640
    BOOL                         (GL_BOOL                        , 1), // 35670
    UNSIGNED_INT_10F_11F_11F_REV (GL_UNSIGNED_INT_10F_11F_11F_REV, 4), // 35899
    INT_2_10_10_10_REV           (GL_INT_2_10_10_10_REV          , 4); // 36255

    public final int value;
    public final int bytes;

    GlType(int value, int bytes) {
        this.value = value;
        this.bytes = bytes;
    }


    public static GlType byValue(int value) {
        return BY_VALUE.get(value);
    }

    private static final Map<Integer, GlType> BY_VALUE = Utils.make(new HashMap<>(), map -> {
        for(GlType e: values())
            map.put(e.value, e);
    });

}
