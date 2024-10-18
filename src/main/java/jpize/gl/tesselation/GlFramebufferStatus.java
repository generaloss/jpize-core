package jpize.gl.tesselation;

import jpize.util.Utils;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL32.*;

public enum GlFramebufferStatus {

    UNDEFINED                     (GL_FRAMEBUFFER_UNDEFINED                    ), // 33305 // the specified framebuffer is the default read or draw framebuffer, but the default framebuffer does not exist.
    COMPLETE                      (GL_FRAMEBUFFER_COMPLETE                     ), // 36053 // the specified framebuffer is complete. Otherwise, the return value is determined as follows:
    INCOMPLETE_ATTACHMENT         (GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT        ), // 36054 // any of the framebuffer attachment points are framebuffer incomplete.
    INCOMPLETE_MISSING_ATTACHMENT (GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT), // 36055 // the framebuffer does not have at least one image attached to it.
    INCOMPLETE_DRAW_BUFFER        (GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER       ), // 36059 // the value of GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE is GL_NONE for any color attachment point(s) named by GL_DRAW_BUFFERi.
    INCOMPLETE_READ_BUFFER        (GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER       ), // 36060 // GL_READ_BUFFER is not GL_NONE and the value of GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE is GL_NONE for the color attachment point named by GL_READ_BUFFER.
    UNSUPPORTED                   (GL_FRAMEBUFFER_UNSUPPORTED                  ), // 36061 // the combination of internal formats of the attached images violates an implementation-dependent set of restrictions.
    INCOMPLETE_MULTISAMPLE        (GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE       ), // 36182 // the value of GL_RENDERBUFFER_SAMPLES is not the same for all attached renderbuffers; if the value of GL_TEXTURE_SAMPLES is the not same for all attached textures; or, if the attached images are a mix of renderbuffers and textures, the value of GL_RENDERBUFFER_SAMPLES does not match the value of GL_TEXTURE_SAMPLES. is also returned if the value of GL_TEXTURE_FIXED_SAMPLE_LOCATIONS is not the same for all attached textures; or, if the attached images are a mix of renderbuffers and textures, the value of GL_TEXTURE_FIXED_SAMPLE_LOCATIONS is not GL_TRUE for all attached textures.
    INCOMPLETE_LAYER_TARGETS      (GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS     ); // 36264 // any framebuffer attachment is layered, and any populated attachment is not layered, or if all populated color attachments are not from textures of the same target.

    public final int value;

    GlFramebufferStatus(int value) {
        this.value = value;
    }

    public boolean isComplete() {
        return (this == COMPLETE);
    }


    public static GlFramebufferStatus byValue(int value) {
        return BY_VALUE.get(value);
    }

    private static final Map<Integer, GlFramebufferStatus> BY_VALUE = Utils.make(new HashMap<>(), map -> {
        for(GlFramebufferStatus e: values())
            map.put(e.value, e);
    });

}
