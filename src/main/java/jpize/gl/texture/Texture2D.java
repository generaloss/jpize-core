package jpize.gl.texture;

import jpize.gl.glenum.GlCompareFunc;
import jpize.gl.type.GlType;
import jpize.util.pixmap.PixmapIO;
import jpize.util.math.vector.Vec2i;
import jpize.util.res.Resource;
import jpize.util.pixmap.Pixmap;

import java.nio.ByteBuffer;

public class Texture2D extends GlTexture {

    public static GlTexTarget TARGET = GlTexTarget.TEXTURE_2D;
    public static GlTexParamTarget PARAM_TARGET = GlTexParamTarget.TEXTURE_2D;
    public static GlTexLevelTarget LEVEL_TARGET = GlTexLevelTarget.TEXTURE_2D;

    public Texture2D() {
        super();
        this.setFilters(GlFilter.NEAREST);
    }

    public Texture2D(int width, int height) {
        this();
        this.setDefaultImage(width, height);
    }

    public Texture2D(Pixmap pixmap) {
        this();
        this.setImage(pixmap);
    }

    public Texture2D(String filepath) {
        this(PixmapIO.load(filepath));
    }

    public Texture2D(Resource res) {
        this(PixmapIO.load(res));
    }


    @Override
    public void dispose() {
        super.dispose();
    }

    public Texture2D bind() {
        super.glBind(PARAM_TARGET);
        return this;
    }

    public static void unbind() {
        GlTexture.glUnbind(PARAM_TARGET);
    }


    // tex func


    public Texture2D active(int active) {
        this.bind();
        super.glActiveTexture(active);
        return this;
    }

    public Texture2D generateMipmap() {
        this.bind();
        super.glGenerateMipmap(TARGET);
        return this;
    }


    public Texture2D generateMipmap(int baseLevel, int maxLevel) {
        this.bind();
        super.glSetBaseLevel(PARAM_TARGET, baseLevel);
        super.glSetMaxLevel(PARAM_TARGET, maxLevel);
        super.glGenerateMipmap(TARGET);
        return this;
    }


    // image


    public Texture2D setImage(int level, Pixmap pixmap) {
        this.bind();
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_2D, 0, pixmap);
        return this;
    }

    public Texture2D setImage(Pixmap pixmap) {
        return setImage(0, pixmap);
    }

    public Texture2D setImage(String internalPath) {
        final Pixmap pixmap = PixmapIO.load(internalPath);
        this.setImage(pixmap);
        pixmap.dispose();
        return this;
    }


    public Texture2D setImage(int width, int height, int level, GlInternalFormat format, GlType type, ByteBuffer pixels) {
        this.bind();
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_2D, 0, width, height, format, type, pixels);
        return this;
    }

    public Texture2D setDefaultImage(int width, int height, int level, ByteBuffer pixels) {
        return setImage(width, height, level, GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, pixels);
    }

    public Texture2D setDefaultImage(int width, int height, ByteBuffer pixels) {
        return setDefaultImage(width, height, 0, pixels);
    }

    public Texture2D setImage(int width, int height, int level, GlInternalFormat format, GlType type) {
        return setImage(width, height, level, format, type, null);
    }

    public Texture2D setDefaultImage(int width, int height, int level) {
        return setImage(width, height, level, GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE);
    }

    public Texture2D setDefaultImage(int width, int height) {
        return setDefaultImage(width, height, 0);
    }


    // params


    public Texture2D setDepthStencilTextureMode(GlDepthStencilMode mode) {
        this.bind();
        super.glSetDepthStencilTextureMode(PARAM_TARGET, mode);
        return this;
    }

    public GlDepthStencilMode getDepthStencilTextureMode() {
        this.bind();
        return glGetDepthStencilTextureMode(PARAM_TARGET);
    }


    public Texture2D setBaseLevel(int level) {
        this.bind();
        super.glSetBaseLevel(PARAM_TARGET, level);
        return this;
    }

    public int getBaseLevel() {
        this.bind();
        return glGetBaseLevel(PARAM_TARGET);
    }


    public Texture2D setCompareFunc(GlCompareFunc value) {
        this.bind();
        super.glSetCompareFunc(PARAM_TARGET, value);
        return this;
    }

    public GlCompareFunc getCompareFunc() {
        this.bind();
        return glGetCompareFunc(PARAM_TARGET);
    }


    public Texture2D setCompareMode(GlCompareMode value) {
        this.bind();
        super.glSetCompareMode(PARAM_TARGET, value);
        return this;
    }

    public GlCompareMode getCompareMode() {
        this.bind();
        return glGetCompareMode(PARAM_TARGET);
    }


    public Texture2D setLodBias(float lodBias) {
        this.bind();
        super.glSetLodBias(PARAM_TARGET, lodBias);
        return this;
    }

    public float getLodBias() {
        this.bind();
        return glGetMaxAnisotropy(PARAM_TARGET);
    }


    public Texture2D setMinFilter(GlFilter filter) {
        this.bind();
        super.glSetMinFilter(PARAM_TARGET, filter);
        return this;
    }

    public Texture2D setMagFilter(GlFilter filter) {
        this.bind();
        super.glSetMagFilter(PARAM_TARGET, filter);
        return this;
    }

    public Texture2D setFilters(GlFilter min, GlFilter mag) {
        this.bind();
        super.glSetMinFilter(PARAM_TARGET, min);
        super.glSetMagFilter(PARAM_TARGET, mag);
        return this;
    }

    public Texture2D setFilters(GlFilter minAndMag) {
        return setFilters(minAndMag, minAndMag);
    }


    public GlFilter getMinFilter() {
        this.bind();
        return glGetMinFilter(PARAM_TARGET);
    }

    public GlFilter getMagFilter() {
        this.bind();
        return glGetMagFilter(PARAM_TARGET);
    }


    public Texture2D setMinLod(int value) {
        this.bind();
        super.glSetMinLod(PARAM_TARGET, value);
        return this;
    }

    public int getMinLod() {
        this.bind();
        return glGetMinLod(PARAM_TARGET);
    }


    public Texture2D setMaxLod(int value) {
        this.bind();
        super.glSetMaxLod(PARAM_TARGET, value);
        return this;
    }

    public int getMaxLod() {
        this.bind();
        return glGetMaxLod(PARAM_TARGET);
    }


    public Texture2D setMaxLevel(int level) {
        this.bind();
        super.glSetMaxLevel(PARAM_TARGET, level);
        return this;
    }

    public int getMaxLevel() {
        this.bind();
        return glGetMaxLevel(PARAM_TARGET);
    }


    public Texture2D setSqizzleR(int value) {
        this.bind();
        super.glSetSqizzleR(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleR() {
        this.bind();
        return glGetSqizzleR(PARAM_TARGET);
    }


    public Texture2D setSqizzleG(int value) {
        this.bind();
        super.glSetSqizzleG(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleG() {
        this.bind();
        return glGetSqizzleG(PARAM_TARGET);
    }


    public Texture2D setSqizzleB(int value) {
        this.bind();
        super.glSetSqizzleB(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleB() {
        this.bind();
        return glGetSqizzleB(PARAM_TARGET);
    }


    public Texture2D setSqizzleA(int value) {
        this.bind();
        super.glSetSqizzleA(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleA() {
        this.bind();
        return glGetSqizzleA(PARAM_TARGET);
    }


    public Texture2D setWrapS(GlWrap wrap) {
        this.bind();
        super.glSetWrapS(PARAM_TARGET, wrap);
        return this;
    }

    public Texture2D setWrapT(GlWrap wrap) {
        this.bind();
        super.glSetWrapT(PARAM_TARGET, wrap);
        return this;
    }

    public Texture2D setWrapR(GlWrap wrap) {
        this.bind();
        super.glSetWrapR(PARAM_TARGET, wrap);
        return this;
    }

    public Texture2D setWrap(GlWrap s, GlWrap t) {
        this.bind();
        super.glSetWrapS(PARAM_TARGET, s);
        super.glSetWrapT(PARAM_TARGET, t);
        return this;
    }

    public Texture2D setWrap(GlWrap s, GlWrap t, GlWrap r) {
        this.bind();
        super.glSetWrapS(PARAM_TARGET, s);
        super.glSetWrapT(PARAM_TARGET, t);
        super.glSetWrapT(PARAM_TARGET, r);
        return this;
    }

    public Texture2D setWrapST(GlWrap wrap) {
        return setWrap(wrap, wrap);
    }

    public Texture2D setWrapSTR(GlWrap wrap) {
        return setWrap(wrap, wrap, wrap);
    }


    public GlWrap getWrapS() {
        this.bind();
        return glGetWrapS(PARAM_TARGET);
    }

    public GlWrap getWrapT() {
        this.bind();
        return glGetWrapT(PARAM_TARGET);
    }

    public GlWrap getWrapR() {
        this.bind();
        return glGetWrapR(PARAM_TARGET);
    }


    public Texture2D setBorderColor(float... color) {
        this.bind();
        super.glSetBorderColor(PARAM_TARGET, color);
        return this;
    }

    public float[] getBorderColor() {
        this.bind();
        return glGetBorderColor(PARAM_TARGET);
    }


    public Texture2D setSwizzle(float... color) {
        this.bind();
        super.glSetSwizzle(PARAM_TARGET, color);
        return this;
    }

    public float[] getSwizzle() {
        this.bind();
        return glGetSwizzle(PARAM_TARGET);
    }


    public Texture2D setMaxAnisotropy(float levels) {
        this.bind();
        super.glSetMaxAnisotropy(PARAM_TARGET, levels);
        return this;
    }

    public float getMaxAnisotropy() {
        this.bind();
        return glGetMaxAnisotropy(PARAM_TARGET);
    }


    // level params


    public int getWidth(int level) {
        this.bind();
        return super.glGetWidth(LEVEL_TARGET, level);
    }

    public int getHeight(int level) {
        this.bind();
        return super.glGetHeight(LEVEL_TARGET, level);
    }

    public int getWidth() {
        return getWidth(0);
    }

    public int getHeight() {
        return getHeight(0);
    }

    public float aspect() {
        this.bind();
        final int width = super.glGetWidth(LEVEL_TARGET, 0);
        final int height = super.glGetHeight(LEVEL_TARGET, 0);
        return Vec2i.aspect(width, height);
    }


    public GlInternalFormat getInternalFormat(int level) {
        this.bind();
        return super.glGetInternalFormat(LEVEL_TARGET, level);
    }

    public GlInternalFormat getInternalFormat() {
        return getInternalFormat(0);
    }


    public int getRedSize(int level) {
        this.bind();
        return super.glGetRedSize(LEVEL_TARGET, level);
    }

    public int getRedSize() {
        return getRedSize(0);
    }

    public int getGreenSize(int level) {
        this.bind();
        return super.glGetGreenSize(LEVEL_TARGET, level);
    }

    public int getGreenSize() {
        return getGreenSize(0);
    }

    public int getBlueSize(int level) {
        this.bind();
        return super.glGetBlueSize(LEVEL_TARGET, level);
    }

    public int getBlueSize() {
        return getBlueSize(0);
    }

    public int getAlphaSize(int level) {
        this.bind();
        return super.glGetAlphaSize(LEVEL_TARGET, level);
    }

    public int getAlphaSize() {
        return getAlphaSize(0);
    }


    public int getDepthSize(int level) {
        this.bind();
        return super.glGetDepthSize(LEVEL_TARGET, level);
    }

    public int getDepthSize() {
        return getDepthSize(0);
    }

    public int getCompressedImageSize(int level) {
        this.bind();
        return super.glGetCompressedImageSize(LEVEL_TARGET, level);
    }

    public int getCompressedImageSize() {
        return getCompressedImageSize(0);
    }

    public boolean isCompressed(int level) {
        this.bind();
        return super.glIsCompressed(LEVEL_TARGET, level);
    }

    public boolean isCompressed() {
        return isCompressed(0);
    }

    public int getBufferOffset(int level) {
        this.bind();
        return super.glGetBufferOffset(LEVEL_TARGET, level);
    }

    public int getBufferOffset() {
        return getBufferOffset(0);
    }

}