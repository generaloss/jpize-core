package jpize.opengl.texture;

import jpize.context.Jpize;
import jpize.opengl.gl.*;
import jpize.opengl.GLObject;
import jpize.opengl.glenum.GLCompareFunc;
import jpize.opengl.type.GLBool;
import jpize.opengl.type.GLType;
import jpize.util.pixmap.Pixmap;

import java.nio.*;

public abstract class GLTexture extends GLObject {

    public static void unbindAll() {
        for(GLTexParamTarget target: GLTexParamTarget.values())
            Jpize.GL15.glBindTexture(target.value, 0);
    }


    public GLTexture(int ID) {
        super(ID);
    }

    public GLTexture() {
        this(Jpize.GL15.glGenTextures());
    }


    @Override
    public void dispose() {
        Jpize.GL15.glDeleteTextures(ID);
    }

    protected void glBind(GLTexParamTarget target) {
        Jpize.GL15.glBindTexture(target.value, ID);
    }

    protected static void glUnbind(GLTexParamTarget target) {
        Jpize.GL15.glBindTexture(target.value, 0);
    }


    public void glGetSubImage(int level, int x, int y, int z, int width, int height, int depth, GLBaseFormat format, GLType type, ByteBuffer pixels) {
        Jpize.GL45.glGetTextureSubImage(ID, level, x, y, z, width, height, depth, format.value, type.value, pixels);
    }

    public void glGetSubImage(int level, int x, int y, int width, int height, Pixmap pixmap) {
        pixmap.resize(width, height);
        this.glGetSubImage(level, x, y, 0, width, height, 1, pixmap.getFormat().base, GLType.UNSIGNED_BYTE, pixmap.buffer());
    }


    public static void glActiveTexture(int active) {
        Jpize.GL15.glActiveTexture(GL13I.GL_TEXTURE0 + active);
    }


    public static void glGenerateMipmap(GLTextureTarget target) {
        Jpize.GL30.glGenerateMipmap(target.value);
    }



    public static void glSetImage1D(GLTexImage1DTarget target, int level, int width, GLInternalFormat format, GLType type, ByteBuffer pixels) {
        Jpize.GL15.glTexImage1D(target.value, level, format.value, width, 0, format.base.value, type.value, pixels);
    }


    public static void glSetImage2D(GLTexImage2DTarget target, int level, int width, int height, GLInternalFormat format, GLType type, ByteBuffer pixels) {
        Jpize.GL15.glTexImage2D(target.value, level, format.value, width, height, 0, format.base.value, type.value, pixels);
    }

    public static void glSetImage2D(GLTexImage2DTarget target, int level, Pixmap pixmap) {
        glSetImage2D(target, level, pixmap.getWidth(), pixmap.getHeight(), pixmap.getFormat(), GLType.UNSIGNED_BYTE, pixmap.buffer());
    }


    public static void glSetImage3D(GLTexImage3DTarget target, int level, int width, int height, int depth, GLInternalFormat format, GLType type, ByteBuffer pixels) {
        Jpize.GL15.glTexImage3D(target.value, level, format.value, width, height, depth, 0, format.base.value, type.value, pixels);
    }


    public static void glSetSubImage1D(GLTexImage1DTarget target, int level, int width, int height, int offsetX, GLBaseFormat format, GLType type, ByteBuffer pixels) {
        Jpize.GL15.glTexSubImage1D(target.value, level, offsetX, width, format.value, type.value, pixels);
    }


    public static void glSetSubImage2D(GLTexImage2DTarget target, int level, int width, int height, int offsetX, int offsetY, GLBaseFormat format, GLType type, ByteBuffer pixels) {
        Jpize.GL15.glTexSubImage2D(target.value, level, offsetX, offsetY, width, height, format.value, type.value, pixels);
    }


    public static void glSetSubImage3D(GLTexImage3DTarget target, int level, int width, int height, int depth, int offsetX, int offsetY, int offsetZ, GLBaseFormat format, GLType type, ByteBuffer pixels) {
        Jpize.GL15.glTexSubImage3D(target.value, level, offsetX, offsetY, offsetZ, width, height, depth, format.value, type.value, pixels);
    }

    public static void glSetSubImage3D(GLTexImage3DTarget target, int level, int width, int height, int depth, int offsetX, int offsetY, int offsetZ, Pixmap pixmap) {
        final ByteBuffer pixels = (pixmap == null ? null : pixmap.buffer());
        final GLBaseFormat format = (pixmap == null ? GLBaseFormat.RGBA : pixmap.getFormat().base);
        glSetSubImage3D(target, level, width, height, depth, offsetX, offsetY, offsetZ, format, GLType.UNSIGNED_BYTE, pixels);
    }


    public static void glGetImage(GLTextureTarget target, int level, GLBaseFormat format, GLType type, ByteBuffer pixels) {
        Jpize.GL15.glGetTexImage(target.value, level, format.value, type.value, pixels);
    }

    public static void glGetImage(GLTextureTarget target, int level, int width, int height, Pixmap pixmap) {
        pixmap.resize(width, height);
        glGetImage(target, level, pixmap.getFormat().base, GLType.UNSIGNED_BYTE, pixmap.buffer());
    }


    public static int glGetWidth(GLTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_WIDTH);
    }

    public static int glGetHeight(GLTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_HEIGHT);
    }

    public static int glGetDepth(GLTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL12I.GL_TEXTURE_DEPTH);
    }


    public static GLInternalFormat glGetInternalFormat(GLTexLevelTarget target, int level) {
        return GLInternalFormat.byValue(Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_INTERNAL_FORMAT));
    }


    public static int glGetRedSize(GLTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_RED_SIZE);
    }

    public static int glGetGreenSize(GLTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_GREEN_SIZE);
    }

    public static int glGetBlueSize(GLTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_BLUE_SIZE);
    }

    public static int glGetAlphaSize(GLTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_ALPHA_SIZE);
    }


    public static int glGetDepthSize(GLTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL14I.GL_TEXTURE_DEPTH_SIZE);
    }

    public static int glGetCompressedImageSize(GLTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL13I.GL_TEXTURE_COMPRESSED_IMAGE_SIZE);
    }

    public static boolean glIsCompressed(GLTexLevelTarget target, int level) {
        return GLBool.of(Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL13I.GL_TEXTURE_COMPRESSED));
    }

    public static int glGetBufferOffset(GLTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL43I.GL_TEXTURE_BUFFER_OFFSET);
    }



    public static void glSetDepthStencilTextureMode(GLTexParamTarget target, GLDepthStencilMode mode) {
        Jpize.GL15.glTexParameteri(target.value, GL43I.GL_DEPTH_STENCIL_TEXTURE_MODE, mode.value);
    }

    public static GLDepthStencilMode glGetDepthStencilTextureMode(GLTexParamTarget target) {
        return GLDepthStencilMode.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL43I.GL_DEPTH_STENCIL_TEXTURE_MODE));
    }


    public static void glSetBaseLevel(GLTexParamTarget target, int level) {
        Jpize.GL15.glTexParameteri(target.value, GL12I.GL_TEXTURE_BASE_LEVEL, level);
    }

    public static int glGetBaseLevel(GLTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL12I.GL_TEXTURE_BASE_LEVEL);
    }


    public static void glSetCompareFunc(GLTexParamTarget target, GLCompareFunc value) {
        Jpize.GL15.glTexParameteri(target.value, GL14I.GL_TEXTURE_COMPARE_FUNC, value.value);
    }

    public static GLCompareFunc glGetCompareFunc(GLTexParamTarget target) {
        return GLCompareFunc.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL14I.GL_TEXTURE_COMPARE_FUNC));
    }


    public static void glSetCompareMode(GLTexParamTarget target, GLCompareMode value) {
        Jpize.GL15.glTexParameteri(target.value, GL14I.GL_TEXTURE_COMPARE_MODE, value.value);
    }

    public static GLCompareMode glGetCompareMode(GLTexParamTarget target) {
        return GLCompareMode.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL14I.GL_TEXTURE_COMPARE_MODE));
    }


    public static void glSetLodBias(GLTexParamTarget target, float lodBias) {
        lodBias = Math.min(lodBias, GL.getMaxTextureLodBias());
        Jpize.GL15.glTexParameterf(target.value, GL14I.GL_TEXTURE_LOD_BIAS, lodBias);
    }

    public static float glGetLodBias(GLTexParamTarget target) {
        return Jpize.GL15.glGetTexParameterf(target.value, GL14I.GL_TEXTURE_LOD_BIAS);
    }


    public static void glSetMinFilter(GLTexParamTarget target, GLFilter filter) {
        Jpize.GL15.glTexParameteri(target.value, GL11I.GL_TEXTURE_MIN_FILTER, filter.value);
    }

    public static void glSetMagFilter(GLTexParamTarget target, GLFilter filter) {
        Jpize.GL15.glTexParameteri(target.value, GL11I.GL_TEXTURE_MAG_FILTER, filter.value);
    }

    public static GLFilter glGetMinFilter(GLTexParamTarget target) {
        return GLFilter.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL11I.GL_TEXTURE_MIN_FILTER));
    }

    public static GLFilter glGetMagFilter(GLTexParamTarget target) {
        return GLFilter.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL11I.GL_TEXTURE_MAG_FILTER));
    }


    public static void glSetMinLod(GLTexParamTarget target, int value) {
        Jpize.GL15.glTexParameteri(target.value, GL12I.GL_TEXTURE_MIN_LOD, value);
    }

    public static int glGetMinLod(GLTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL12I.GL_TEXTURE_MIN_LOD);
    }


    public static void glSetMaxLod(GLTexParamTarget target, int value) {
        Jpize.GL15.glTexParameteri(target.value, GL12I.GL_TEXTURE_MAX_LOD, value);
    }

    public static int glGetMaxLod(GLTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL12I.GL_TEXTURE_MAX_LOD);
    }


    public static void glSetMaxLevel(GLTexParamTarget target, int level) {
        Jpize.GL15.glTexParameteri(target.value, GL12I.GL_TEXTURE_MAX_LEVEL, level);
    }

    public static int glGetMaxLevel(GLTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL12I.GL_TEXTURE_MAX_LEVEL);
    }


    public static void glSetSqizzleR(GLTexParamTarget target, int value) {
        Jpize.GL15.glTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_R, value);
    }

    public static int glGetSqizzleR(GLTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_R);
    }


    public static void glSetSqizzleG(GLTexParamTarget target, int value) {
        Jpize.GL15.glTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_G, value);
    }

    public static int glGetSqizzleG(GLTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_G);
    }


    public static void glSetSqizzleB(GLTexParamTarget target, int value) {
        Jpize.GL15.glTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_B, value);
    }

    public static int glGetSqizzleB(GLTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_B);
    }


    public static void glSetSqizzleA(GLTexParamTarget target, int value) {
        Jpize.GL15.glTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_A, value);
    }

    public static int glGetSqizzleA(GLTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_A);
    }


    public static void glSetWrapS(GLTexParamTarget target, GLWrap wrap) {
        Jpize.GL15.glTexParameteri(target.value, GL11I.GL_TEXTURE_WRAP_S, wrap.value);
    }

    public static void glSetWrapT(GLTexParamTarget target, GLWrap wrap) {
        Jpize.GL15.glTexParameteri(target.value, GL11I.GL_TEXTURE_WRAP_T, wrap.value);
    }

    public static void glSetWrapR(GLTexParamTarget target, GLWrap wrap) {
        Jpize.GL15.glTexParameteri(target.value, GL12I.GL_TEXTURE_WRAP_R, wrap.value);
    }

    public static GLWrap glGetWrapS(GLTexParamTarget target) {
        return GLWrap.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL11I.GL_TEXTURE_WRAP_S));
    }

    public static GLWrap glGetWrapT(GLTexParamTarget target) {
        return GLWrap.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL11I.GL_TEXTURE_WRAP_T));
    }

    public static GLWrap glGetWrapR(GLTexParamTarget target) {
        return GLWrap.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL12I.GL_TEXTURE_WRAP_R));
    }


    public static void glSetBorderColor(GLTexParamTarget target, float... color) {
        Jpize.GL15.glTexParameterfv(target.value, GL11I.GL_TEXTURE_BORDER_COLOR, color);
    }

    public static float[] glGetBorderColor(GLTexParamTarget target) {
        final float[] value = new float[4];
        Jpize.GL15.glGetTexParameterfv(target.value, GL11I.GL_TEXTURE_BORDER_COLOR, value);
        return value;
    }


    public static void glSetSwizzle(GLTexParamTarget target, float... color) {
        Jpize.GL15.glTexParameterfv(target.value, GL33I.GL_TEXTURE_SWIZZLE_RGBA, color);
    }

    public static float[] glGetSwizzle(GLTexParamTarget target) {
        final float[] value = new float[4];
        Jpize.GL15.glGetTexParameterfv(target.value, GL33I.GL_TEXTURE_SWIZZLE_RGBA, value);
        return value;
    }


    public static void glSetMaxAnisotropy(GLTexParamTarget target, float levels) {
        levels = Math.min(levels, GL.getMaxAnisotropy());
        Jpize.GL15.glTexParameterf(target.value, GL46I.GL_TEXTURE_MAX_ANISOTROPY, levels);
    }

    public static float glGetMaxAnisotropy(GLTexParamTarget target) {
        return Jpize.GL15.glGetTexParameterf(target.value, GL46I.GL_TEXTURE_MAX_ANISOTROPY);
    }

}
