package jpize.opengl.texture;

import jpize.util.Utils;

import java.util.HashMap;
import java.util.Map;

import static jpize.opengl.gl.GL42I.*;

public enum GlInternalFormat {

    // COLOR
    ALPHA4         (GL_ALPHA4        , GlBaseFormat.ALPHA),
    ALPHA8         (GL_ALPHA8        , GlBaseFormat.ALPHA),
    ALPHA12        (GL_ALPHA12       , GlBaseFormat.ALPHA),
    ALPHA16        (GL_ALPHA16       , GlBaseFormat.ALPHA),

    R8	           (GL_R8	         , GlBaseFormat.RED),
    R8_SNORM	   (GL_R8_SNORM	     , GlBaseFormat.RED),
    R16	           (GL_R16	         , GlBaseFormat.RED),
    R16_SNORM      (GL_R16_SNORM	 , GlBaseFormat.RED),
    R8I	           (GL_R8I	         , GlBaseFormat.RED),
    R8UI	       (GL_R8UI	         , GlBaseFormat.RED),
    R16I	       (GL_R16I	         , GlBaseFormat.RED),
    R16UI	       (GL_R16UI	     , GlBaseFormat.RED),
    R32I	       (GL_R32I	         , GlBaseFormat.RED),
    R32UI	       (GL_R32UI	     , GlBaseFormat.RED),
    R16F	       (GL_R16F	         , GlBaseFormat.RED),
    R32F	       (GL_R32F	         , GlBaseFormat.RED),

    RG8	           (GL_RG8	         , GlBaseFormat.RG),
    RG8_SNORM      (GL_RG8_SNORM	 , GlBaseFormat.RG),
    RG16	       (GL_RG16	         , GlBaseFormat.RG),
    RG16_SNORM     (GL_RG16_SNORM	 , GlBaseFormat.RG),
    RG16F	       (GL_RG16F	     , GlBaseFormat.RG),
    RG32F	       (GL_RG32F	     , GlBaseFormat.RG),
    RG8I	       (GL_RG8I	         , GlBaseFormat.RG),
    RG8UI	       (GL_RG8UI	     , GlBaseFormat.RG),
    RG16I	       (GL_RG16I	     , GlBaseFormat.RG),
    RG16UI	       (GL_RG16UI	     , GlBaseFormat.RG),
    RG32I	       (GL_RG32I	     , GlBaseFormat.RG),
    RG32UI	       (GL_RG32UI	     , GlBaseFormat.RG),

    R3_G3_B2	   (GL_R3_G3_B2	     , GlBaseFormat.RGB),
    RGB4	       (GL_RGB4	         , GlBaseFormat.RGB),
    RGB5	       (GL_RGB5	         , GlBaseFormat.RGB),
    RGB8	       (GL_RGB8	         , GlBaseFormat.RGB),
    RGB8_SNORM     (GL_RGB8_SNORM	 , GlBaseFormat.RGB),
    RGB10	       (GL_RGB10	     , GlBaseFormat.RGB),
    RGB12	       (GL_RGB12	     , GlBaseFormat.RGB),
    RGB16	       (GL_RGB16	     , GlBaseFormat.RGB),
    RGB16_SNORM    (GL_RGB16_SNORM	 , GlBaseFormat.RGB),
    SRGB8	       (GL_SRGB8	     , GlBaseFormat.RGB),
    RGB16F	       (GL_RGB16F	     , GlBaseFormat.RGB),
    RGB32F	       (GL_RGB32F	     , GlBaseFormat.RGB),
    R11F_G11F_B10F (GL_R11F_G11F_B10F, GlBaseFormat.RGB),
    RGB9_E5	       (GL_RGB9_E5	     , GlBaseFormat.RGB),
    RGB8I	       (GL_RGB8I	     , GlBaseFormat.RGB),
    RGB8UI	       (GL_RGB8UI	     , GlBaseFormat.RGB),
    RGB16I	       (GL_RGB16I	     , GlBaseFormat.RGB),
    RGB16UI	       (GL_RGB16UI	     , GlBaseFormat.RGB),
    RGB32I	       (GL_RGB32I	     , GlBaseFormat.RGB),
    RGB32UI	       (GL_RGB32UI	     , GlBaseFormat.RGB),
    RGB10_A2UI     (GL_RGB10_A2UI    , GlBaseFormat.RGB),

    RGBA2	       (GL_RGBA2	     , GlBaseFormat.RGBA),
    RGBA4	       (GL_RGBA4	     , GlBaseFormat.RGBA),
    RGB5_A1	       (GL_RGB5_A1	     , GlBaseFormat.RGBA),
    RGBA8	       (GL_RGBA8	     , GlBaseFormat.RGBA),
    RGBA8_SNORM    (GL_RGBA8_SNORM   , GlBaseFormat.RGBA),
    RGB10_A2       (GL_RGB10_A2 	 , GlBaseFormat.RGBA),
    RGBA12	       (GL_RGBA12	     , GlBaseFormat.RGBA),
    RGBA16	       (GL_RGBA16	     , GlBaseFormat.RGBA),
    RGBA16_SNORM   (GL_RGBA16_SNORM  , GlBaseFormat.RGBA),
    SRGB8_ALPHA8   (GL_SRGB8_ALPHA8  , GlBaseFormat.RGBA),
    RGBA16F	       (GL_RGBA16F	     , GlBaseFormat.RGBA),
    RGBA32F	       (GL_RGBA32F	     , GlBaseFormat.RGBA),
    RGBA8I	       (GL_RGBA8I	     , GlBaseFormat.RGBA),
    RGBA8UI	       (GL_RGBA8UI	     , GlBaseFormat.RGBA),
    RGBA16I	       (GL_RGBA16I	     , GlBaseFormat.RGBA),
    RGBA16UI	   (GL_RGBA16UI	     , GlBaseFormat.RGBA),
    RGBA32I	       (GL_RGBA32I	     , GlBaseFormat.RGBA),
    RGBA32UI	   (GL_RGBA32UI	     , GlBaseFormat.RGBA),

    // DEPTH
    DEPTH_COMPONENT16  (GL_DEPTH_COMPONENT16 , GlBaseFormat.DEPTH_COMPONENT),
    DEPTH_COMPONENT24  (GL_DEPTH_COMPONENT24 , GlBaseFormat.DEPTH_COMPONENT),
    DEPTH_COMPONENT32  (GL_DEPTH_COMPONENT32 , GlBaseFormat.DEPTH_COMPONENT),
    DEPTH_COMPONENT32F (GL_DEPTH_COMPONENT32F, GlBaseFormat.DEPTH_COMPONENT),

    // STENCIL
    STENCIL_INDEX1  (GL_STENCIL_INDEX1 , GlBaseFormat.STENCIL_INDEX),
    STENCIL_INDEX4  (GL_STENCIL_INDEX4 , GlBaseFormat.STENCIL_INDEX),
    STENCIL_INDEX8  (GL_STENCIL_INDEX8 , GlBaseFormat.STENCIL_INDEX),
    STENCIL_INDEX16 (GL_STENCIL_INDEX16, GlBaseFormat.STENCIL_INDEX),

    // DEPTH STENCIL
    DEPTH24_STENCIL8   (GL_DEPTH24_STENCIL8  , GlBaseFormat.DEPTH_STENCIL  ),
    DEPTH32F_STENCIL8  (GL_DEPTH32F_STENCIL8 , GlBaseFormat.DEPTH_STENCIL  ),

    // COMPRESSED
    COMPRESSED_RED	                  (GL_COMPRESSED_RED	                , GlBaseFormat.RED ),
    COMPRESSED_RED_RGTC1	          (GL_COMPRESSED_RED_RGTC1	            , GlBaseFormat.RED ),
    COMPRESSED_SIGNED_RED_RGTC1       (GL_COMPRESSED_SIGNED_RED_RGTC1       , GlBaseFormat.RED ),
    COMPRESSED_RG	                  (GL_COMPRESSED_RG	                    , GlBaseFormat.RG  ),
    COMPRESSED_RG_RGTC2	              (GL_COMPRESSED_RG_RGTC2	            , GlBaseFormat.RG  ),
    COMPRESSED_SIGNED_RG_RGTC2        (GL_COMPRESSED_SIGNED_RG_RGTC2        , GlBaseFormat.RG  ),
    COMPRESSED_RGB	                  (GL_COMPRESSED_RGB	                , GlBaseFormat.RGB ),
    COMPRESSED_RGB_BPTC_SIGNED_FLOAT  (GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT  , GlBaseFormat.RGB ),
    COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT(GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT, GlBaseFormat.RGB ),
    COMPRESSED_SRGB	                  (GL_COMPRESSED_SRGB	                , GlBaseFormat.RGB ),
    COMPRESSED_RGBA	                  (GL_COMPRESSED_RGBA	                , GlBaseFormat.RGBA),
    COMPRESSED_RGBA_BPTC_UNORM        (GL_COMPRESSED_RGBA_BPTC_UNORM        , GlBaseFormat.RGBA),
    COMPRESSED_SRGB_ALPHA             (GL_COMPRESSED_SRGB_ALPHA             , GlBaseFormat.RGBA),
    COMPRESSED_SRGB_ALPHA_BPTC_UNORM  (GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM  , GlBaseFormat.RGBA);


    public final int value;
    public final GlBaseFormat base;

    GlInternalFormat(int value, GlBaseFormat base) {
        this.value = value;
        this.base = base;
    }

    public int channels() {
        return base.channels;
    }


    public static GlInternalFormat byValue(int value) {
        return BY_VALUE.get(value);
    }

    private static final Map<Integer, GlInternalFormat> BY_VALUE = Utils.make(new HashMap<>(), map -> {
        for(GlInternalFormat e: values())
            map.put(e.value, e);
    });

}
