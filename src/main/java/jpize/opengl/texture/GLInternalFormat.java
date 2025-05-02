package jpize.opengl.texture;

import jpize.util.Utils;

import java.util.HashMap;
import java.util.Map;

import static jpize.opengl.gl.GL42I.*;

public enum GLInternalFormat {

    // COLOR
    ALPHA4         (GL_ALPHA4        , GLBaseFormat.ALPHA),
    ALPHA8         (GL_ALPHA8        , GLBaseFormat.ALPHA),
    ALPHA12        (GL_ALPHA12       , GLBaseFormat.ALPHA),
    ALPHA16        (GL_ALPHA16       , GLBaseFormat.ALPHA),

    R8	           (GL_R8	         , GLBaseFormat.RED),
    R8_SNORM	   (GL_R8_SNORM	     , GLBaseFormat.RED),
    R16	           (GL_R16	         , GLBaseFormat.RED),
    R16_SNORM      (GL_R16_SNORM	 , GLBaseFormat.RED),
    R8I	           (GL_R8I	         , GLBaseFormat.RED),
    R8UI	       (GL_R8UI	         , GLBaseFormat.RED),
    R16I	       (GL_R16I	         , GLBaseFormat.RED),
    R16UI	       (GL_R16UI	     , GLBaseFormat.RED),
    R32I	       (GL_R32I	         , GLBaseFormat.RED),
    R32UI	       (GL_R32UI	     , GLBaseFormat.RED),
    R16F	       (GL_R16F	         , GLBaseFormat.RED),
    R32F	       (GL_R32F	         , GLBaseFormat.RED),

    RG8	           (GL_RG8	         , GLBaseFormat.RG),
    RG8_SNORM      (GL_RG8_SNORM	 , GLBaseFormat.RG),
    RG16	       (GL_RG16	         , GLBaseFormat.RG),
    RG16_SNORM     (GL_RG16_SNORM	 , GLBaseFormat.RG),
    RG16F	       (GL_RG16F	     , GLBaseFormat.RG),
    RG32F	       (GL_RG32F	     , GLBaseFormat.RG),
    RG8I	       (GL_RG8I	         , GLBaseFormat.RG),
    RG8UI	       (GL_RG8UI	     , GLBaseFormat.RG),
    RG16I	       (GL_RG16I	     , GLBaseFormat.RG),
    RG16UI	       (GL_RG16UI	     , GLBaseFormat.RG),
    RG32I	       (GL_RG32I	     , GLBaseFormat.RG),
    RG32UI	       (GL_RG32UI	     , GLBaseFormat.RG),

    R3_G3_B2	   (GL_R3_G3_B2	     , GLBaseFormat.RGB),
    RGB4	       (GL_RGB4	         , GLBaseFormat.RGB),
    RGB5	       (GL_RGB5	         , GLBaseFormat.RGB),
    RGB8	       (GL_RGB8	         , GLBaseFormat.RGB),
    RGB8_SNORM     (GL_RGB8_SNORM	 , GLBaseFormat.RGB),
    RGB10	       (GL_RGB10	     , GLBaseFormat.RGB),
    RGB12	       (GL_RGB12	     , GLBaseFormat.RGB),
    RGB16	       (GL_RGB16	     , GLBaseFormat.RGB),
    RGB16_SNORM    (GL_RGB16_SNORM	 , GLBaseFormat.RGB),
    SRGB8	       (GL_SRGB8	     , GLBaseFormat.RGB),
    RGB16F	       (GL_RGB16F	     , GLBaseFormat.RGB),
    RGB32F	       (GL_RGB32F	     , GLBaseFormat.RGB),
    R11F_G11F_B10F (GL_R11F_G11F_B10F, GLBaseFormat.RGB),
    RGB9_E5	       (GL_RGB9_E5	     , GLBaseFormat.RGB),
    RGB8I	       (GL_RGB8I	     , GLBaseFormat.RGB),
    RGB8UI	       (GL_RGB8UI	     , GLBaseFormat.RGB),
    RGB16I	       (GL_RGB16I	     , GLBaseFormat.RGB),
    RGB16UI	       (GL_RGB16UI	     , GLBaseFormat.RGB),
    RGB32I	       (GL_RGB32I	     , GLBaseFormat.RGB),
    RGB32UI	       (GL_RGB32UI	     , GLBaseFormat.RGB),
    RGB10_A2UI     (GL_RGB10_A2UI    , GLBaseFormat.RGB),

    RGBA2	       (GL_RGBA2	     , GLBaseFormat.RGBA),
    RGBA4	       (GL_RGBA4	     , GLBaseFormat.RGBA),
    RGB5_A1	       (GL_RGB5_A1	     , GLBaseFormat.RGBA),
    RGBA8	       (GL_RGBA8	     , GLBaseFormat.RGBA),
    RGBA8_SNORM    (GL_RGBA8_SNORM   , GLBaseFormat.RGBA),
    RGB10_A2       (GL_RGB10_A2 	 , GLBaseFormat.RGBA),
    RGBA12	       (GL_RGBA12	     , GLBaseFormat.RGBA),
    RGBA16	       (GL_RGBA16	     , GLBaseFormat.RGBA),
    RGBA16_SNORM   (GL_RGBA16_SNORM  , GLBaseFormat.RGBA),
    SRGB8_ALPHA8   (GL_SRGB8_ALPHA8  , GLBaseFormat.RGBA),
    RGBA16F	       (GL_RGBA16F	     , GLBaseFormat.RGBA),
    RGBA32F	       (GL_RGBA32F	     , GLBaseFormat.RGBA),
    RGBA8I	       (GL_RGBA8I	     , GLBaseFormat.RGBA),
    RGBA8UI	       (GL_RGBA8UI	     , GLBaseFormat.RGBA),
    RGBA16I	       (GL_RGBA16I	     , GLBaseFormat.RGBA),
    RGBA16UI	   (GL_RGBA16UI	     , GLBaseFormat.RGBA),
    RGBA32I	       (GL_RGBA32I	     , GLBaseFormat.RGBA),
    RGBA32UI	   (GL_RGBA32UI	     , GLBaseFormat.RGBA),

    // DEPTH
    DEPTH_COMPONENT16  (GL_DEPTH_COMPONENT16 , GLBaseFormat.DEPTH_COMPONENT),
    DEPTH_COMPONENT24  (GL_DEPTH_COMPONENT24 , GLBaseFormat.DEPTH_COMPONENT),
    DEPTH_COMPONENT32  (GL_DEPTH_COMPONENT32 , GLBaseFormat.DEPTH_COMPONENT),
    DEPTH_COMPONENT32F (GL_DEPTH_COMPONENT32F, GLBaseFormat.DEPTH_COMPONENT),

    // STENCIL
    STENCIL_INDEX1  (GL_STENCIL_INDEX1 , GLBaseFormat.STENCIL_INDEX),
    STENCIL_INDEX4  (GL_STENCIL_INDEX4 , GLBaseFormat.STENCIL_INDEX),
    STENCIL_INDEX8  (GL_STENCIL_INDEX8 , GLBaseFormat.STENCIL_INDEX),
    STENCIL_INDEX16 (GL_STENCIL_INDEX16, GLBaseFormat.STENCIL_INDEX),

    // DEPTH STENCIL
    DEPTH24_STENCIL8   (GL_DEPTH24_STENCIL8  , GLBaseFormat.DEPTH_STENCIL  ),
    DEPTH32F_STENCIL8  (GL_DEPTH32F_STENCIL8 , GLBaseFormat.DEPTH_STENCIL  ),

    // COMPRESSED
    COMPRESSED_RED	                  (GL_COMPRESSED_RED	                , GLBaseFormat.RED ),
    COMPRESSED_RED_RGTC1	          (GL_COMPRESSED_RED_RGTC1	            , GLBaseFormat.RED ),
    COMPRESSED_SIGNED_RED_RGTC1       (GL_COMPRESSED_SIGNED_RED_RGTC1       , GLBaseFormat.RED ),
    COMPRESSED_RG	                  (GL_COMPRESSED_RG	                    , GLBaseFormat.RG  ),
    COMPRESSED_RG_RGTC2	              (GL_COMPRESSED_RG_RGTC2	            , GLBaseFormat.RG  ),
    COMPRESSED_SIGNED_RG_RGTC2        (GL_COMPRESSED_SIGNED_RG_RGTC2        , GLBaseFormat.RG  ),
    COMPRESSED_RGB	                  (GL_COMPRESSED_RGB	                , GLBaseFormat.RGB ),
    COMPRESSED_RGB_BPTC_SIGNED_FLOAT  (GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT  , GLBaseFormat.RGB ),
    COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT(GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT, GLBaseFormat.RGB ),
    COMPRESSED_SRGB	                  (GL_COMPRESSED_SRGB	                , GLBaseFormat.RGB ),
    COMPRESSED_RGBA	                  (GL_COMPRESSED_RGBA	                , GLBaseFormat.RGBA),
    COMPRESSED_RGBA_BPTC_UNORM        (GL_COMPRESSED_RGBA_BPTC_UNORM        , GLBaseFormat.RGBA),
    COMPRESSED_SRGB_ALPHA             (GL_COMPRESSED_SRGB_ALPHA             , GLBaseFormat.RGBA),
    COMPRESSED_SRGB_ALPHA_BPTC_UNORM  (GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM  , GLBaseFormat.RGBA);


    public final int value;
    public final GLBaseFormat base;

    GLInternalFormat(int value, GLBaseFormat base) {
        this.value = value;
        this.base = base;
    }

    public int channels() {
        return base.channels;
    }


    public static GLInternalFormat byValue(int value) {
        return BY_VALUE.get(value);
    }

    private static final Map<Integer, GLInternalFormat> BY_VALUE = Utils.make(new HashMap<>(), map -> {
        for(GLInternalFormat e: values())
            map.put(e.value, e);
    });

}
