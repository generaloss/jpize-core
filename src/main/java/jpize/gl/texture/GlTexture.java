package jpize.gl.texture;

import jpize.gl.Gl;
import jpize.gl.GlObject;
import jpize.gl.glenum.GlCompareFunc;
import jpize.gl.type.GlType;
import jpize.util.pixmap.Pixmap;
import org.lwjgl.opengl.GL46;

import java.nio.*;

import static org.lwjgl.opengl.GL46.*;

public abstract class GlTexture extends GlObject {

    public static void unbindAll() {
        for(GlTexParamTarget target: GlTexParamTarget.values())
            GL46.glBindTexture(target.value, 0);
    }


    public GlTexture(int ID) {
        super(ID);
    }

    public GlTexture() {
        this(GL46.glGenTextures());
    }


    @Override
    public void dispose() {
        GL46.glDeleteTextures(ID);
    }

    protected void glBind(GlTexParamTarget target) {
        GL46.glBindTexture(target.value, ID);
    }

    protected static void glUnbind(GlTexParamTarget target) {
        GL46.glBindTexture(target.value, 0);
    }



    protected void glActiveTexture(int active) {
        GL46.glActiveTexture(GL_TEXTURE0 + active);
    }


    protected void glGenerateMipmap(GlTexTarget target) {
        GL46.glGenerateMipmap(target.value);
    }



    protected void glSetImage1D(GlTexImg1DTarget target, int level, int width, GlInternalFormat format, GlType type, ByteBuffer pixels) {
        GL46.glTexImage1D(target.value, level, format.value, width, 0, format.base.value, type.value, pixels);
    }


    protected void glSetImage2D(GlTexImg2DTarget target, int level, int width, int height, GlInternalFormat format, GlType type, ByteBuffer pixels) {
        GL46.glTexImage2D(target.value, level, format.value, width, height, 0, format.base.value, type.value, pixels);
    }

    protected void glSetImage2D(GlTexImg2DTarget target, int level, Pixmap pixmap) {
        this.glSetImage2D(target, level, pixmap.getWidth(), pixmap.getHeight(), pixmap.getFormat(), GlType.UNSIGNED_BYTE, pixmap.buffer());
    }


    protected void glSetImage3D(GlTexImg3DTarget target, int level, int width, int height, int depth, GlInternalFormat format, GlType type, ByteBuffer pixels) {
        GL46.glTexImage3D(target.value, level, format.value, width, height, depth, 0, format.base.value, type.value, pixels);
    }


    protected void glSetSubImage1D(GlTexImg1DTarget target, int level, int width, int height, int offsetX, GlBaseFormat format, GlType type, ByteBuffer pixels) {
        GL46.glTexSubImage1D(target.value, level, offsetX, width, format.value, type.value, pixels);
    }


    protected void glSetSubImage2D(GlTexImg2DTarget target, int level, int width, int height, int offsetX, int offsetY, GlBaseFormat format, GlType type, ByteBuffer pixels) {
        GL46.glTexSubImage2D(target.value, level, offsetX, offsetY, width, height, format.value, type.value, pixels);
    }


    protected void glSetSubImage3D(GlTexImg3DTarget target, int level, int width, int height, int depth, int offsetX, int offsetY, int offsetZ, GlBaseFormat format, GlType type, ByteBuffer pixels) {
        GL46.glTexSubImage3D(target.value, level, offsetX, offsetY, offsetZ, width, height, depth, format.value, type.value, pixels);
    }

    protected void glSetSubImage3D(GlTexImg3DTarget target, int level, int width, int height, int depth, int offsetX, int offsetY, int offsetZ, Pixmap pixmap) {
        final ByteBuffer pixels = (pixmap == null ? null : pixmap.buffer());
        final GlBaseFormat format = (pixmap == null ? GlBaseFormat.RGBA : pixmap.getFormat().base);
        this.glSetSubImage3D(target, level, width, height, depth, offsetX, offsetY, offsetZ, format, GlType.UNSIGNED_BYTE, pixels);
    }


    protected void glGetImage(GlTexTarget target, int level, GlBaseFormat format, GlType type, ByteBuffer pixels) {
        GL46.glGetTexImage(target.value, level, format.value, type.value, pixels);
    }

    protected void glGetImage(GlTexTarget target, int level, int width, int height, Pixmap pixmap) {
        pixmap.resize(width, height);
        this.glGetImage(target, level, pixmap.getFormat().base, GlType.UNSIGNED_BYTE, pixmap.buffer());
    }


    protected void glGetSubImage(int level, int x, int y, int z, int width, int height, int depth, GlBaseFormat format, GlType type, ByteBuffer pixels) {
        GL46.glGetTextureSubImage(ID, level, x, y, z, width, height, depth, format.value, type.value, pixels);
    }

    protected void glGetSubImage(int level, int x, int y, int width, int height, Pixmap pixmap) {
        pixmap.resize(width, height);
        this.glGetSubImage(level, x, y, 0, width, height, 1, pixmap.getFormat().base, GlType.UNSIGNED_BYTE, pixmap.buffer());
    }


    protected int glGetWidth(GlTexLevelTarget target, int level) {
        return GL46.glGetTexLevelParameteri(target.value, level, GL_TEXTURE_WIDTH);
    }

    protected int glGetHeight(GlTexLevelTarget target, int level) {
        return GL46.glGetTexLevelParameteri(target.value, level, GL_TEXTURE_HEIGHT);
    }

    protected int glGetDepth(GlTexLevelTarget target, int level) {
        return GL46.glGetTexLevelParameteri(target.value, level, GL_TEXTURE_DEPTH);
    }


    protected GlInternalFormat glGetInternalFormat(GlTexLevelTarget target, int level) {
        return GlInternalFormat.byValue(GL46.glGetTexLevelParameteri(target.value, level, GL_TEXTURE_INTERNAL_FORMAT));
    }


    protected int glGetRedSize(GlTexLevelTarget target, int level) {
        return GL46.glGetTexLevelParameteri(target.value, level, GL_TEXTURE_RED_SIZE);
    }

    protected int glGetGreenSize(GlTexLevelTarget target, int level) {
        return GL46.glGetTexLevelParameteri(target.value, level, GL_TEXTURE_GREEN_SIZE);
    }

    protected int glGetBlueSize(GlTexLevelTarget target, int level) {
        return GL46.glGetTexLevelParameteri(target.value, level, GL_TEXTURE_BLUE_SIZE);
    }

    protected int glGetAlphaSize(GlTexLevelTarget target, int level) {
        return GL46.glGetTexLevelParameteri(target.value, level, GL_TEXTURE_ALPHA_SIZE);
    }


    protected int glGetDepthSize(GlTexLevelTarget target, int level) {
        return GL46.glGetTexLevelParameteri(target.value, level, GL_TEXTURE_DEPTH_SIZE);
    }

    protected int glGetCompressedImageSize(GlTexLevelTarget target, int level) {
        return GL46.glGetTexLevelParameteri(target.value, level, GL_TEXTURE_COMPRESSED_IMAGE_SIZE);
    }

    protected boolean glIsCompressed(GlTexLevelTarget target, int level) {
        return GL46.glGetTexLevelParameteri(target.value, level, GL_TEXTURE_COMPRESSED) == GL_TRUE;
    }

    protected int glGetBufferOffset(GlTexLevelTarget target, int level) {
        return GL46.glGetTexLevelParameteri(target.value, level, GL_TEXTURE_BUFFER_OFFSET);
    }



    protected void glSetDepthStencilTextureMode(GlTexParamTarget target, GlDepthStencilMode mode) {
        GL46.glTexParameteri(target.value, GL_DEPTH_STENCIL_TEXTURE_MODE, mode.value);
    }

    protected GlDepthStencilMode glGetDepthStencilTextureMode(GlTexParamTarget target) {
        return GlDepthStencilMode.byValue(GL46.glGetTexParameteri(target.value, GL_DEPTH_STENCIL_TEXTURE_MODE));
    }


    protected void glSetBaseLevel(GlTexParamTarget target, int level) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_BASE_LEVEL, level);
    }

    protected int glGetBaseLevel(GlTexParamTarget target) {
        return GL46.glGetTexParameteri(target.value, GL_TEXTURE_BASE_LEVEL);
    }


    protected void glSetCompareFunc(GlTexParamTarget target, GlCompareFunc value) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_COMPARE_FUNC, value.value);
    }

    protected GlCompareFunc glGetCompareFunc(GlTexParamTarget target) {
        return GlCompareFunc.byValue(GL46.glGetTexParameteri(target.value, GL_TEXTURE_COMPARE_FUNC));
    }


    protected void glSetCompareMode(GlTexParamTarget target, GlCompareMode value) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_COMPARE_MODE, value.value);
    }

    protected GlCompareMode glGetCompareMode(GlTexParamTarget target) {
        return GlCompareMode.byValue(GL46.glGetTexParameteri(target.value, GL_TEXTURE_COMPARE_MODE));
    }


    protected void glSetLodBias(GlTexParamTarget target, float lodBias) {
        lodBias = Math.min(lodBias, Gl.getMaxTextureLodBias());
        GL46.glTexParameterf(target.value, GL_TEXTURE_LOD_BIAS, lodBias);
    }

    protected float glGetLodBias(GlTexParamTarget target) {
        return GL46.glGetTexParameterf(target.value, GL_TEXTURE_LOD_BIAS);
    }


    protected void glSetMinFilter(GlTexParamTarget target, GlFilter filter) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_MIN_FILTER, filter.value);
    }

    protected void glSetMagFilter(GlTexParamTarget target, GlFilter filter) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_MAG_FILTER, filter.value);
    }

    protected GlFilter glGetMinFilter(GlTexParamTarget target) {
        return GlFilter.byValue(GL46.glGetTexParameteri(target.value, GL_TEXTURE_MIN_FILTER));
    }

    protected GlFilter glGetMagFilter(GlTexParamTarget target) {
        return GlFilter.byValue(GL46.glGetTexParameteri(target.value, GL_TEXTURE_MAG_FILTER));
    }


    protected void glSetMinLod(GlTexParamTarget target, int value) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_MIN_LOD, value);
    }

    protected int glGetMinLod(GlTexParamTarget target) {
        return GL46.glGetTexParameteri(target.value, GL_TEXTURE_MIN_LOD);
    }


    protected void glSetMaxLod(GlTexParamTarget target, int value) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_MAX_LOD, value);
    }

    protected int glGetMaxLod(GlTexParamTarget target) {
        return GL46.glGetTexParameteri(target.value, GL_TEXTURE_MAX_LOD);
    }


    protected void glSetMaxLevel(GlTexParamTarget target, int level) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_MAX_LEVEL, level);
    }

    protected int glGetMaxLevel(GlTexParamTarget target) {
        return GL46.glGetTexParameteri(target.value, GL_TEXTURE_MAX_LEVEL);
    }


    protected void glSetSqizzleR(GlTexParamTarget target, int value) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_SWIZZLE_R, value);
    }

    protected int glGetSqizzleR(GlTexParamTarget target) {
        return GL46.glGetTexParameteri(target.value, GL_TEXTURE_SWIZZLE_R);
    }


    protected void glSetSqizzleG(GlTexParamTarget target, int value) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_SWIZZLE_G, value);
    }

    protected int glGetSqizzleG(GlTexParamTarget target) {
        return GL46.glGetTexParameteri(target.value, GL_TEXTURE_SWIZZLE_G);
    }


    protected void glSetSqizzleB(GlTexParamTarget target, int value) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_SWIZZLE_B, value);
    }

    protected int glGetSqizzleB(GlTexParamTarget target) {
        return GL46.glGetTexParameteri(target.value, GL_TEXTURE_SWIZZLE_B);
    }


    protected void glSetSqizzleA(GlTexParamTarget target, int value) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_SWIZZLE_A, value);
    }

    protected int glGetSqizzleA(GlTexParamTarget target) {
        return GL46.glGetTexParameteri(target.value, GL_TEXTURE_SWIZZLE_A);
    }


    protected void glSetWrapS(GlTexParamTarget target, GlWrap wrap) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_WRAP_S, wrap.value);
    }

    protected void glSetWrapT(GlTexParamTarget target, GlWrap wrap) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_WRAP_T, wrap.value);
    }

    protected void glSetWrapR(GlTexParamTarget target, GlWrap wrap) {
        GL46.glTexParameteri(target.value, GL_TEXTURE_WRAP_R, wrap.value);
    }

    protected GlWrap glGetWrapS(GlTexParamTarget target) {
        return GlWrap.byValue(GL46.glGetTexParameteri(target.value, GL_TEXTURE_WRAP_S));
    }

    protected GlWrap glGetWrapT(GlTexParamTarget target) {
        return GlWrap.byValue(GL46.glGetTexParameteri(target.value, GL_TEXTURE_WRAP_T));
    }

    protected GlWrap glGetWrapR(GlTexParamTarget target) {
        return GlWrap.byValue(GL46.glGetTexParameteri(target.value, GL_TEXTURE_WRAP_R));
    }


    protected void glSetBorderColor(GlTexParamTarget target, float... color) {
        GL46.glTexParameterfv(target.value, GL_TEXTURE_BORDER_COLOR, color);
    }

    protected float[] glGetBorderColor(GlTexParamTarget target) {
        final float[] value = new float[4];
        GL46.glGetTexParameterfv(target.value, GL_TEXTURE_BORDER_COLOR, value);
        return value;
    }


    protected void glSetSwizzle(GlTexParamTarget target, float... color) {
        GL46.glTexParameterfv(target.value, GL_TEXTURE_SWIZZLE_RGBA, color);
    }

    protected float[] glGetSwizzle(GlTexParamTarget target) {
        final float[] value = new float[4];
        GL46.glGetTexParameterfv(target.value, GL_TEXTURE_SWIZZLE_RGBA, value);
        return value;
    }


    protected void glSetMaxAnisotropy(GlTexParamTarget target, float levels) {
        levels = Math.min(levels, Gl.getMaxAnisotropy());
        GL46.glTexParameterf(target.value, GL_TEXTURE_MAX_ANISOTROPY, levels);
    }

    protected float glGetMaxAnisotropy(GlTexParamTarget target) {
        return GL46.glGetTexParameterf(target.value, GL_TEXTURE_MAX_ANISOTROPY);
    }

}
