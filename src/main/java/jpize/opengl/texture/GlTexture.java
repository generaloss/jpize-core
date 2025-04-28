package jpize.opengl.texture;

import jpize.context.Jpize;
import jpize.opengl.gl.*;
import jpize.opengl.GlObject;
import jpize.opengl.glenum.GlCompareFunc;
import jpize.opengl.type.GlBool;
import jpize.opengl.type.GlType;
import jpize.util.pixmap.Pixmap;

import java.nio.*;

public abstract class GlTexture extends GlObject {

    public static void unbindAll() {
        for(GlTexParamTarget target: GlTexParamTarget.values())
            Jpize.GL15.glBindTexture(target.value, 0);
    }


    public GlTexture(int ID) {
        super(ID);
    }

    public GlTexture() {
        this(Jpize.GL15.glGenTextures());
    }


    @Override
    public void dispose() {
        Jpize.GL15.glDeleteTextures(ID);
    }

    protected void glBind(GlTexParamTarget target) {
        Jpize.GL15.glBindTexture(target.value, ID);
    }

    protected static void glUnbind(GlTexParamTarget target) {
        Jpize.GL15.glBindTexture(target.value, 0);
    }



    protected void glActiveTexture(int active) {
        Jpize.GL15.glActiveTexture(GL13I.GL_TEXTURE0 + active);
    }


    protected void glGenerateMipmap(GlTexTarget target) {
        Jpize.GL30.glGenerateMipmap(target.value);
    }



    protected void glSetImage1D(GlTexImg1DTarget target, int level, int width, GlInternalFormat format, GlType type, ByteBuffer pixels) {
        Jpize.GL15.glTexImage1D(target.value, level, format.value, width, 0, format.base.value, type.value, pixels);
    }


    protected void glSetImage2D(GlTexImg2DTarget target, int level, int width, int height, GlInternalFormat format, GlType type, ByteBuffer pixels) {
        Jpize.GL15.glTexImage2D(target.value, level, format.value, width, height, 0, format.base.value, type.value, pixels);
    }

    protected void glSetImage2D(GlTexImg2DTarget target, int level, Pixmap pixmap) {
        this.glSetImage2D(target, level, pixmap.getWidth(), pixmap.getHeight(), pixmap.getFormat(), GlType.UNSIGNED_BYTE, pixmap.buffer());
    }


    protected void glSetImage3D(GlTexImg3DTarget target, int level, int width, int height, int depth, GlInternalFormat format, GlType type, ByteBuffer pixels) {
        Jpize.GL15.glTexImage3D(target.value, level, format.value, width, height, depth, 0, format.base.value, type.value, pixels);
    }


    protected void glSetSubImage1D(GlTexImg1DTarget target, int level, int width, int height, int offsetX, GlBaseFormat format, GlType type, ByteBuffer pixels) {
        Jpize.GL15.glTexSubImage1D(target.value, level, offsetX, width, format.value, type.value, pixels);
    }


    protected void glSetSubImage2D(GlTexImg2DTarget target, int level, int width, int height, int offsetX, int offsetY, GlBaseFormat format, GlType type, ByteBuffer pixels) {
        Jpize.GL15.glTexSubImage2D(target.value, level, offsetX, offsetY, width, height, format.value, type.value, pixels);
    }


    protected void glSetSubImage3D(GlTexImg3DTarget target, int level, int width, int height, int depth, int offsetX, int offsetY, int offsetZ, GlBaseFormat format, GlType type, ByteBuffer pixels) {
        Jpize.GL15.glTexSubImage3D(target.value, level, offsetX, offsetY, offsetZ, width, height, depth, format.value, type.value, pixels);
    }

    protected void glSetSubImage3D(GlTexImg3DTarget target, int level, int width, int height, int depth, int offsetX, int offsetY, int offsetZ, Pixmap pixmap) {
        final ByteBuffer pixels = (pixmap == null ? null : pixmap.buffer());
        final GlBaseFormat format = (pixmap == null ? GlBaseFormat.RGBA : pixmap.getFormat().base);
        this.glSetSubImage3D(target, level, width, height, depth, offsetX, offsetY, offsetZ, format, GlType.UNSIGNED_BYTE, pixels);
    }


    protected void glGetImage(GlTexTarget target, int level, GlBaseFormat format, GlType type, ByteBuffer pixels) {
        Jpize.GL15.glGetTexImage(target.value, level, format.value, type.value, pixels);
    }

    protected void glGetImage(GlTexTarget target, int level, int width, int height, Pixmap pixmap) {
        pixmap.resize(width, height);
        this.glGetImage(target, level, pixmap.getFormat().base, GlType.UNSIGNED_BYTE, pixmap.buffer());
    }


    protected void glGetSubImage(int level, int x, int y, int z, int width, int height, int depth, GlBaseFormat format, GlType type, ByteBuffer pixels) {
        Jpize.GL45.glGetTextureSubImage(ID, level, x, y, z, width, height, depth, format.value, type.value, pixels);
    }

    protected void glGetSubImage(int level, int x, int y, int width, int height, Pixmap pixmap) {
        pixmap.resize(width, height);
        this.glGetSubImage(level, x, y, 0, width, height, 1, pixmap.getFormat().base, GlType.UNSIGNED_BYTE, pixmap.buffer());
    }


    protected int glGetWidth(GlTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_WIDTH);
    }

    protected int glGetHeight(GlTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_HEIGHT);
    }

    protected int glGetDepth(GlTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL12I.GL_TEXTURE_DEPTH);
    }


    protected GlInternalFormat glGetInternalFormat(GlTexLevelTarget target, int level) {
        return GlInternalFormat.byValue(Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_INTERNAL_FORMAT));
    }


    protected int glGetRedSize(GlTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_RED_SIZE);
    }

    protected int glGetGreenSize(GlTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_GREEN_SIZE);
    }

    protected int glGetBlueSize(GlTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_BLUE_SIZE);
    }

    protected int glGetAlphaSize(GlTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL11I.GL_TEXTURE_ALPHA_SIZE);
    }


    protected int glGetDepthSize(GlTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL14I.GL_TEXTURE_DEPTH_SIZE);
    }

    protected int glGetCompressedImageSize(GlTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL13I.GL_TEXTURE_COMPRESSED_IMAGE_SIZE);
    }

    protected boolean glIsCompressed(GlTexLevelTarget target, int level) {
        return GlBool.of(Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL13I.GL_TEXTURE_COMPRESSED));
    }

    protected int glGetBufferOffset(GlTexLevelTarget target, int level) {
        return Jpize.GL15.glGetTexLevelParameteri(target.value, level, GL43I.GL_TEXTURE_BUFFER_OFFSET);
    }



    protected void glSetDepthStencilTextureMode(GlTexParamTarget target, GlDepthStencilMode mode) {
        Jpize.GL15.glTexParameteri(target.value, GL43I.GL_DEPTH_STENCIL_TEXTURE_MODE, mode.value);
    }

    protected GlDepthStencilMode glGetDepthStencilTextureMode(GlTexParamTarget target) {
        return GlDepthStencilMode.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL43I.GL_DEPTH_STENCIL_TEXTURE_MODE));
    }


    protected void glSetBaseLevel(GlTexParamTarget target, int level) {
        Jpize.GL15.glTexParameteri(target.value, GL12I.GL_TEXTURE_BASE_LEVEL, level);
    }

    protected int glGetBaseLevel(GlTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL12I.GL_TEXTURE_BASE_LEVEL);
    }


    protected void glSetCompareFunc(GlTexParamTarget target, GlCompareFunc value) {
        Jpize.GL15.glTexParameteri(target.value, GL14I.GL_TEXTURE_COMPARE_FUNC, value.value);
    }

    protected GlCompareFunc glGetCompareFunc(GlTexParamTarget target) {
        return GlCompareFunc.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL14I.GL_TEXTURE_COMPARE_FUNC));
    }


    protected void glSetCompareMode(GlTexParamTarget target, GlCompareMode value) {
        Jpize.GL15.glTexParameteri(target.value, GL14I.GL_TEXTURE_COMPARE_MODE, value.value);
    }

    protected GlCompareMode glGetCompareMode(GlTexParamTarget target) {
        return GlCompareMode.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL14I.GL_TEXTURE_COMPARE_MODE));
    }


    protected void glSetLodBias(GlTexParamTarget target, float lodBias) {
        lodBias = Math.min(lodBias, Gl.getMaxTextureLodBias());
        Jpize.GL15.glTexParameterf(target.value, GL14I.GL_TEXTURE_LOD_BIAS, lodBias);
    }

    protected float glGetLodBias(GlTexParamTarget target) {
        return Jpize.GL15.glGetTexParameterf(target.value, GL14I.GL_TEXTURE_LOD_BIAS);
    }


    protected void glSetMinFilter(GlTexParamTarget target, GlFilter filter) {
        Jpize.GL15.glTexParameteri(target.value, GL11I.GL_TEXTURE_MIN_FILTER, filter.value);
    }

    protected void glSetMagFilter(GlTexParamTarget target, GlFilter filter) {
        Jpize.GL15.glTexParameteri(target.value, GL11I.GL_TEXTURE_MAG_FILTER, filter.value);
    }

    protected GlFilter glGetMinFilter(GlTexParamTarget target) {
        return GlFilter.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL11I.GL_TEXTURE_MIN_FILTER));
    }

    protected GlFilter glGetMagFilter(GlTexParamTarget target) {
        return GlFilter.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL11I.GL_TEXTURE_MAG_FILTER));
    }


    protected void glSetMinLod(GlTexParamTarget target, int value) {
        Jpize.GL15.glTexParameteri(target.value, GL12I.GL_TEXTURE_MIN_LOD, value);
    }

    protected int glGetMinLod(GlTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL12I.GL_TEXTURE_MIN_LOD);
    }


    protected void glSetMaxLod(GlTexParamTarget target, int value) {
        Jpize.GL15.glTexParameteri(target.value, GL12I.GL_TEXTURE_MAX_LOD, value);
    }

    protected int glGetMaxLod(GlTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL12I.GL_TEXTURE_MAX_LOD);
    }


    protected void glSetMaxLevel(GlTexParamTarget target, int level) {
        Jpize.GL15.glTexParameteri(target.value, GL12I.GL_TEXTURE_MAX_LEVEL, level);
    }

    protected int glGetMaxLevel(GlTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL12I.GL_TEXTURE_MAX_LEVEL);
    }


    protected void glSetSqizzleR(GlTexParamTarget target, int value) {
        Jpize.GL15.glTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_R, value);
    }

    protected int glGetSqizzleR(GlTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_R);
    }


    protected void glSetSqizzleG(GlTexParamTarget target, int value) {
        Jpize.GL15.glTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_G, value);
    }

    protected int glGetSqizzleG(GlTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_G);
    }


    protected void glSetSqizzleB(GlTexParamTarget target, int value) {
        Jpize.GL15.glTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_B, value);
    }

    protected int glGetSqizzleB(GlTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_B);
    }


    protected void glSetSqizzleA(GlTexParamTarget target, int value) {
        Jpize.GL15.glTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_A, value);
    }

    protected int glGetSqizzleA(GlTexParamTarget target) {
        return Jpize.GL15.glGetTexParameteri(target.value, GL33I.GL_TEXTURE_SWIZZLE_A);
    }


    protected void glSetWrapS(GlTexParamTarget target, GlWrap wrap) {
        Jpize.GL15.glTexParameteri(target.value, GL11I.GL_TEXTURE_WRAP_S, wrap.value);
    }

    protected void glSetWrapT(GlTexParamTarget target, GlWrap wrap) {
        Jpize.GL15.glTexParameteri(target.value, GL11I.GL_TEXTURE_WRAP_T, wrap.value);
    }

    protected void glSetWrapR(GlTexParamTarget target, GlWrap wrap) {
        Jpize.GL15.glTexParameteri(target.value, GL12I.GL_TEXTURE_WRAP_R, wrap.value);
    }

    protected GlWrap glGetWrapS(GlTexParamTarget target) {
        return GlWrap.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL11I.GL_TEXTURE_WRAP_S));
    }

    protected GlWrap glGetWrapT(GlTexParamTarget target) {
        return GlWrap.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL11I.GL_TEXTURE_WRAP_T));
    }

    protected GlWrap glGetWrapR(GlTexParamTarget target) {
        return GlWrap.byValue(Jpize.GL15.glGetTexParameteri(target.value, GL12I.GL_TEXTURE_WRAP_R));
    }


    protected void glSetBorderColor(GlTexParamTarget target, float... color) {
        Jpize.GL15.glTexParameterfv(target.value, GL11I.GL_TEXTURE_BORDER_COLOR, color);
    }

    protected float[] glGetBorderColor(GlTexParamTarget target) {
        final float[] value = new float[4];
        Jpize.GL15.glGetTexParameterfv(target.value, GL11I.GL_TEXTURE_BORDER_COLOR, value);
        return value;
    }


    protected void glSetSwizzle(GlTexParamTarget target, float... color) {
        Jpize.GL15.glTexParameterfv(target.value, GL33I.GL_TEXTURE_SWIZZLE_RGBA, color);
    }

    protected float[] glGetSwizzle(GlTexParamTarget target) {
        final float[] value = new float[4];
        Jpize.GL15.glGetTexParameterfv(target.value, GL33I.GL_TEXTURE_SWIZZLE_RGBA, value);
        return value;
    }


    protected void glSetMaxAnisotropy(GlTexParamTarget target, float levels) {
        levels = Math.min(levels, Gl.getMaxAnisotropy());
        Jpize.GL15.glTexParameterf(target.value, GL46I.GL_TEXTURE_MAX_ANISOTROPY, levels);
    }

    protected float glGetMaxAnisotropy(GlTexParamTarget target) {
        return Jpize.GL15.glGetTexParameterf(target.value, GL46I.GL_TEXTURE_MAX_ANISOTROPY);
    }

}
