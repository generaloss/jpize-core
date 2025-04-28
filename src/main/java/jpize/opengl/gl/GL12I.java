package jpize.opengl.gl;

import java.nio.*;

public interface GL12I extends GL11I {

    int GL_ALIASED_POINT_SIZE_RANGE = 33901;
    int GL_ALIASED_LINE_WIDTH_RANGE = 33902;
    int GL_SMOOTH_POINT_SIZE_RANGE = 2834;
    int GL_SMOOTH_POINT_SIZE_GRANULARITY = 2835;
    int GL_SMOOTH_LINE_WIDTH_RANGE = 2850;
    int GL_SMOOTH_LINE_WIDTH_GRANULARITY = 2851;
    int GL_TEXTURE_BINDING_3D = 32874;
    int GL_PACK_SKIP_IMAGES = 32875;
    int GL_PACK_IMAGE_HEIGHT = 32876;
    int GL_UNPACK_SKIP_IMAGES = 32877;
    int GL_UNPACK_IMAGE_HEIGHT = 32878;
    int GL_TEXTURE_3D = 32879;
    int GL_PROXY_TEXTURE_3D = 32880;
    int GL_TEXTURE_DEPTH = 32881;
    int GL_TEXTURE_WRAP_R = 32882;
    int GL_MAX_3D_TEXTURE_SIZE = 32883;
    int GL_BGR = 32992;
    int GL_BGRA = 32993;
    int GL_UNSIGNED_BYTE_3_3_2 = 32818;
    int GL_UNSIGNED_BYTE_2_3_3_REV = 33634;
    int GL_UNSIGNED_SHORT_5_6_5 = 33635;
    int GL_UNSIGNED_SHORT_5_6_5_REV = 33636;
    int GL_UNSIGNED_SHORT_4_4_4_4 = 32819;
    int GL_UNSIGNED_SHORT_4_4_4_4_REV = 33637;
    int GL_UNSIGNED_SHORT_5_5_5_1 = 32820;
    int GL_UNSIGNED_SHORT_1_5_5_5_REV = 33638;
    int GL_UNSIGNED_INT_8_8_8_8 = 32821;
    int GL_UNSIGNED_INT_8_8_8_8_REV = 33639;
    int GL_UNSIGNED_INT_10_10_10_2 = 32822;
    int GL_UNSIGNED_INT_2_10_10_10_REV = 33640;
    int GL_RESCALE_NORMAL = 32826;
    int GL_LIGHT_MODEL_COLOR_CONTROL = 33272;
    int GL_SINGLE_COLOR = 33273;
    int GL_SEPARATE_SPECULAR_COLOR = 33274;
    int GL_CLAMP_TO_EDGE = 33071;
    int GL_TEXTURE_MIN_LOD = 33082;
    int GL_TEXTURE_MAX_LOD = 33083;
    int GL_TEXTURE_BASE_LEVEL = 33084;
    int GL_TEXTURE_MAX_LEVEL = 33085;
    int GL_MAX_ELEMENTS_VERTICES = 33000;
    int GL_MAX_ELEMENTS_INDICES = 33001;

    void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels);
    void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, long pixels);
    void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels);
    void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels);
    void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels);
    void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, DoubleBuffer pixels);
    void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels);
    void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels);
    void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels);
    void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels);
    void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels);
    void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer pixels);
    void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height);
    void glDrawRangeElements(int mode, int start, int end, int count, int type, long indices);
    void glDrawRangeElements(int mode, int start, int end, int type, ByteBuffer indices);
    void glDrawRangeElements(int mode, int start, int end, ByteBuffer indices);
    void glDrawRangeElements(int mode, int start, int end, ShortBuffer indices);
    void glDrawRangeElements(int mode, int start, int end, IntBuffer indices);
    void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, short[] pixels);
    void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, int[] pixels);
    void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, float[] pixels);
    void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, double[] pixels);
    void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, short[] pixels);
    void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int[] pixels);
    void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, float[] pixels);
    void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, double[] pixels);

}
