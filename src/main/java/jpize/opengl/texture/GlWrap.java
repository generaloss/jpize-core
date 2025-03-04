package jpize.opengl.texture;

import jpize.util.Utils;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.ARBTextureMirrorClampToEdge.GL_MIRROR_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL33.*;

public enum GlWrap {

    REPEAT               (GL_REPEAT              ), // 10497
    CLAMP_TO_BORDER      (GL_CLAMP_TO_BORDER     ), // 33069
    CLAMP_TO_EDGE        (GL_CLAMP_TO_EDGE       ), // 33071
    MIRRORED_REPEAT      (GL_MIRRORED_REPEAT     ), // 33648
    MIRROR_CLAMP_TO_EDGE (GL_MIRROR_CLAMP_TO_EDGE); // 34627

    public final int value;

    GlWrap(int value) {
        this.value = value;
    }


    public static GlWrap byValue(int value) {
        return BY_VALUE.get(value);
    }

    private static final Map<Integer, GlWrap> BY_VALUE = Utils.make(new HashMap<>(), map -> {
        for(GlWrap e: values())
            map.put(e.value, e);
    });

}
