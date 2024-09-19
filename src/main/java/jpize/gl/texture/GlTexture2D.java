package jpize.gl.texture;

import jpize.gl.glenum.GlCompareFunc;
import jpize.gl.type.GlType;
import jpize.util.pixmap.PixmapIO;
import jpize.util.math.vector.Vec2i;
import jpize.util.res.Resource;
import jpize.util.pixmap.Pixmap;

import java.nio.ByteBuffer;

public class GlTexture2D extends GlTexture {

    public static GlTexTarget TARGET = GlTexTarget.TEXTURE_2D;
    public static GlTexParamTarget PARAM_TARGET = GlTexParamTarget.TEXTURE_2D;
    public static GlTexLevelTarget LEVEL_TARGET = GlTexLevelTarget.TEXTURE_2D;

    public GlTexture2D() {
        super();
        this.setFilters(GlFilter.NEAREST);
    }

    public GlTexture2D(int width, int height) {
        this();
        this.setDefaultImage(width, height);
    }

    public GlTexture2D(Pixmap pixmap) {
        this();
        this.setImage(pixmap);
    }

    public GlTexture2D(String filepath) {
        this(PixmapIO.load(filepath));
    }

    public GlTexture2D(Resource res) {
        this(PixmapIO.load(res));
    }


    @Override
    public void dispose() {
        super.dispose();
    }

    public GlTexture2D bind() {
        super.glBind(PARAM_TARGET);
        return this;
    }

    public static void unbind() {
        GlTexture.glUnbind(PARAM_TARGET);
    }


    // tex func


    public GlTexture2D active(int active) {
        bind();
        super.glActiveTexture(active);
        return this;
    }

    public GlTexture2D generateMipmap() {
        bind();
        super.glGenerateMipmap(TARGET);
        return this;
    }


    public GlTexture2D generateMipmap(int baseLevel, int maxLevel) {
        bind();
        super.glSetBaseLevel(PARAM_TARGET, baseLevel);
        super.glSetMaxLevel(PARAM_TARGET, maxLevel);
        super.glGenerateMipmap(TARGET);
        return this;
    }


    // image


    public GlTexture2D setImage(int level, Pixmap pixmap) {
        bind();
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_2D, 0, pixmap);
        return this;
    }

    public GlTexture2D setImage(Pixmap pixmap) {
        return setImage(0, pixmap);
    }

    public GlTexture2D setImage(String internalPath) {
        return setImage(PixmapIO.load(internalPath));
    }


    public GlTexture2D setImage(int width, int height, int level, GlInternalFormat format, GlType type, ByteBuffer pixels) {
        bind();
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_2D, 0, width, height, format, type, pixels);
        return this;
    }

    public GlTexture2D setDefaultImage(int width, int height, int level, ByteBuffer pixels) {
        return setImage(width, height, level, GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, pixels);
    }

    public GlTexture2D setDefaultImage(int width, int height, ByteBuffer pixels) {
        return setDefaultImage(width, height, 0, pixels);
    }

    public GlTexture2D setImage(int width, int height, int level, GlInternalFormat format, GlType type) {
        return setImage(width, height, level, format, type, null);
    }

    public GlTexture2D setDefaultImage(int width, int height, int level) {
        return setImage(width, height, level, GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE);
    }

    public GlTexture2D setDefaultImage(int width, int height) {
        return setDefaultImage(width, height, 0);
    }


    // params


    public GlTexture2D setDepthStencilTextureMode(GlDepthStencilMode mode) {
        bind();
        super.glSetDepthStencilTextureMode(PARAM_TARGET, mode);
        return this;
    }

    public GlDepthStencilMode getDepthStencilTextureMode() {
        bind();
        return glGetDepthStencilTextureMode(PARAM_TARGET);
    }


    public GlTexture2D setBaseLevel(int level) {
        bind();
        super.glSetBaseLevel(PARAM_TARGET, level);
        return this;
    }

    public int getBaseLevel() {
        bind();
        return glGetBaseLevel(PARAM_TARGET);
    }


    public GlTexture2D setCompareFunc(GlCompareFunc value) {
        bind();
        super.glSetCompareFunc(PARAM_TARGET, value);
        return this;
    }

    public GlCompareFunc getCompareFunc() {
        bind();
        return glGetCompareFunc(PARAM_TARGET);
    }


    public GlTexture2D setCompareMode(GlCompareMode value) {
        bind();
        super.glSetCompareMode(PARAM_TARGET, value);
        return this;
    }

    public GlCompareMode getCompareMode() {
        bind();
        return glGetCompareMode(PARAM_TARGET);
    }


    public GlTexture2D setLodBias(float lodBias) {
        bind();
        super.glSetLodBias(PARAM_TARGET, lodBias);
        return this;
    }

    public float getLodBias() {
        bind();
        return glGetMaxAnisotropy(PARAM_TARGET);
    }


    public GlTexture2D setMinFilter(GlFilter filter) {
        bind();
        super.glSetMinFilter(PARAM_TARGET, filter);
        return this;
    }

    public GlTexture2D setMagFilter(GlFilter filter) {
        bind();
        super.glSetMagFilter(PARAM_TARGET, filter);
        return this;
    }

    public GlTexture2D setFilters(GlFilter min, GlFilter mag) {
        bind();
        super.glSetMinFilter(PARAM_TARGET, min);
        super.glSetMagFilter(PARAM_TARGET, mag);
        return this;
    }

    public GlTexture2D setFilters(GlFilter minAndMag) {
        return setFilters(minAndMag, minAndMag);
    }


    public GlFilter getMinFilter() {
        bind();
        return glGetMinFilter(PARAM_TARGET);
    }

    public GlFilter getMagFilter() {
        bind();
        return glGetMagFilter(PARAM_TARGET);
    }


    public GlTexture2D setMinLod(int value) {
        bind();
        super.glSetMinLod(PARAM_TARGET, value);
        return this;
    }

    public int getMinLod() {
        bind();
        return glGetMinLod(PARAM_TARGET);
    }


    public GlTexture2D setMaxLod(int value) {
        bind();
        super.glSetMaxLod(PARAM_TARGET, value);
        return this;
    }

    public int getMaxLod() {
        bind();
        return glGetMaxLod(PARAM_TARGET);
    }


    public GlTexture2D setMaxLevel(int level) {
        bind();
        super.glSetMaxLevel(PARAM_TARGET, level);
        return this;
    }

    public int getMaxLevel() {
        bind();
        return glGetMaxLevel(PARAM_TARGET);
    }


    public GlTexture2D setSqizzleR(int value) {
        bind();
        super.glSetSqizzleR(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleR() {
        bind();
        return glGetSqizzleR(PARAM_TARGET);
    }


    public GlTexture2D setSqizzleG(int value) {
        bind();
        super.glSetSqizzleG(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleG() {
        bind();
        return glGetSqizzleG(PARAM_TARGET);
    }


    public GlTexture2D setSqizzleB(int value) {
        bind();
        super.glSetSqizzleB(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleB() {
        bind();
        return glGetSqizzleB(PARAM_TARGET);
    }


    public GlTexture2D setSqizzleA(int value) {
        bind();
        super.glSetSqizzleA(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleA() {
        bind();
        return glGetSqizzleA(PARAM_TARGET);
    }


    public GlTexture2D setWrapS(GlWrap wrap) {
        bind();
        super.glSetWrapS(PARAM_TARGET, wrap);
        return this;
    }

    public GlTexture2D setWrapT(GlWrap wrap) {
        bind();
        super.glSetWrapT(PARAM_TARGET, wrap);
        return this;
    }

    public GlTexture2D setWrapR(GlWrap wrap) {
        bind();
        super.glSetWrapR(PARAM_TARGET, wrap);
        return this;
    }

    public GlTexture2D setWrap(GlWrap s, GlWrap t) {
        bind();
        super.glSetWrapS(PARAM_TARGET, s);
        super.glSetWrapT(PARAM_TARGET, t);
        return this;
    }

    public GlTexture2D setWrap(GlWrap s, GlWrap t, GlWrap r) {
        bind();
        super.glSetWrapS(PARAM_TARGET, s);
        super.glSetWrapT(PARAM_TARGET, t);
        super.glSetWrapT(PARAM_TARGET, r);
        return this;
    }

    public GlTexture2D setWrapST(GlWrap wrap) {
        return setWrap(wrap, wrap);
    }

    public GlTexture2D setWrapSTR(GlWrap wrap) {
        return setWrap(wrap, wrap, wrap);
    }


    public GlWrap getWrapS() {
        bind();
        return glGetWrapS(PARAM_TARGET);
    }

    public GlWrap getWrapT() {
        bind();
        return glGetWrapT(PARAM_TARGET);
    }

    public GlWrap getWrapR() {
        bind();
        return glGetWrapR(PARAM_TARGET);
    }


    public GlTexture2D setBorderColor(float... color) {
        bind();
        super.glSetBorderColor(PARAM_TARGET, color);
        return this;
    }

    public float[] getBorderColor() {
        bind();
        return glGetBorderColor(PARAM_TARGET);
    }


    public GlTexture2D setSwizzle(float... color) {
        bind();
        super.glSetSwizzle(PARAM_TARGET, color);
        return this;
    }

    public float[] getSwizzle() {
        bind();
        return glGetSwizzle(PARAM_TARGET);
    }


    public GlTexture2D setMaxAnisotropy(float levels) {
        bind();
        super.glSetMaxAnisotropy(PARAM_TARGET, levels);
        return this;
    }

    public float getMaxAnisotropy() {
        bind();
        return glGetMaxAnisotropy(PARAM_TARGET);
    }


    // level params


    public int getWidth(int level) {
        bind();
        return super.glGetWidth(LEVEL_TARGET, level);
    }

    public int getHeight(int level) {
        bind();
        return super.glGetHeight(LEVEL_TARGET, level);
    }

    public int getWidth() {
        return getWidth(0);
    }

    public int getHeight() {
        return getHeight(0);
    }

    public float aspect() {
        bind();
        final int width = super.glGetWidth(LEVEL_TARGET, 0);
        final int height = super.glGetHeight(LEVEL_TARGET, 0);
        return Vec2i.aspect(width, height);
    }


    public GlInternalFormat getInternalFormat(int level) {
        bind();
        return super.glGetInternalFormat(LEVEL_TARGET, level);
    }

    public GlInternalFormat getInternalFormat() {
        return getInternalFormat(0);
    }


    public int getRedSize(int level) {
        bind();
        return super.glGetRedSize(LEVEL_TARGET, level);
    }

    public int getRedSize() {
        return getRedSize(0);
    }

    public int getGreenSize(int level) {
        bind();
        return super.glGetGreenSize(LEVEL_TARGET, level);
    }

    public int getGreenSize() {
        return getGreenSize(0);
    }

    public int getBlueSize(int level) {
        bind();
        return super.glGetBlueSize(LEVEL_TARGET, level);
    }

    public int getBlueSize() {
        return getBlueSize(0);
    }

    public int getAlphaSize(int level) {
        bind();
        return super.glGetAlphaSize(LEVEL_TARGET, level);
    }

    public int getAlphaSize() {
        return getAlphaSize(0);
    }


    public int getDepthSize(int level) {
        bind();
        return super.glGetDepthSize(LEVEL_TARGET, level);
    }

    public int getDepthSize() {
        return getDepthSize(0);
    }

    public int getCompressedImageSize(int level) {
        bind();
        return super.glGetCompressedImageSize(LEVEL_TARGET, level);
    }

    public int getCompressedImageSize() {
        return getCompressedImageSize(0);
    }

    public boolean isCompressed(int level) {
        bind();
        return super.glIsCompressed(LEVEL_TARGET, level);
    }

    public boolean isCompressed() {
        return isCompressed(0);
    }

    public int getBufferOffset(int level) {
        bind();
        return super.glGetBufferOffset(LEVEL_TARGET, level);
    }

    public int getBufferOffset() {
        return getBufferOffset(0);
    }

}