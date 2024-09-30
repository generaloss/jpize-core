package jpize.gl.texture;

import jpize.gl.glenum.GlCompareFunc;
import jpize.util.pixmap.Pixmap;

public class Texture2DArray extends GlTexture {

    public static GlTexTarget TARGET = GlTexTarget.TEXTURE_2D_ARRAY;
    public static GlTexParamTarget PARAM_TARGET = GlTexParamTarget.TEXTURE_2D_ARRAY;
    public static GlTexLevelTarget LEVEL_TARGET = GlTexLevelTarget.TEXTURE_2D_ARRAY;

    public Texture2DArray() {
        super();
    }

    public Texture2DArray(Pixmap... pixmaps) {
        this();
        setImages(pixmaps);
    }


    @Override
    public void dispose() {
        super.dispose();
    }

    public Texture2DArray bind() {
        super.glBind(PARAM_TARGET);
        return this;
    }

    public static void unbind() {
        GlTexture.glUnbind(PARAM_TARGET);
    }


    // tex func


    public Texture2DArray active(int active) {
        bind();
        super.glActiveTexture(active);
        return this;
    }

    public Texture2DArray generateMipmap() {
        bind();
        super.glGenerateMipmap(TARGET);
        return this;
    }


    public Texture2DArray generateMipmap(int baseLevel, int maxLevel) {
        bind();
        super.glSetBaseLevel(PARAM_TARGET, baseLevel);
        super.glSetMaxLevel(PARAM_TARGET, maxLevel);
        super.glGenerateMipmap(TARGET);
        return this;
    }


    // image


    public Texture2DArray setImages(Pixmap... pixmaps) {
        bind();
        for(int offsetZ = 0; offsetZ < pixmaps.length; offsetZ++){
            final Pixmap pixmap = pixmaps[offsetZ];
            super.glSetSubImage3D(GlTexImg3DTarget.TEXTURE_2D_ARRAY, 0, pixmap.getWidth(), pixmap.getHeight(), 1, 0, 0, offsetZ, pixmap);
        }
        return this;
    }


    // params


    public Texture2DArray setDepthStencilTextureMode(GlDepthStencilMode mode) {
        bind();
        super.glSetDepthStencilTextureMode(PARAM_TARGET, mode);
        return this;
    }

    public GlDepthStencilMode getDepthStencilTextureMode() {
        bind();
        return glGetDepthStencilTextureMode(PARAM_TARGET);
    }


    public Texture2DArray setBaseLevel(int level) {
        bind();
        super.glSetBaseLevel(PARAM_TARGET, level);
        return this;
    }

    public int getBaseLevel() {
        bind();
        return glGetBaseLevel(PARAM_TARGET);
    }


    public Texture2DArray setCompareFunc(GlCompareFunc value) {
        bind();
        super.glSetCompareFunc(PARAM_TARGET, value);
        return this;
    }

    public GlCompareFunc getCompareFunc() {
        bind();
        return glGetCompareFunc(PARAM_TARGET);
    }


    public Texture2DArray setCompareMode(GlCompareMode value) {
        bind();
        super.glSetCompareMode(PARAM_TARGET, value);
        return this;
    }

    public GlCompareMode getCompareMode() {
        bind();
        return glGetCompareMode(PARAM_TARGET);
    }


    public Texture2DArray setLodBias(float lodBias) {
        bind();
        super.glSetLodBias(PARAM_TARGET, lodBias);
        return this;
    }

    public float getLodBias() {
        bind();
        return glGetMaxAnisotropy(PARAM_TARGET);
    }


    public Texture2DArray setMinFilter(GlFilter filter) {
        bind();
        super.glSetMinFilter(PARAM_TARGET, filter);
        return this;
    }

    public Texture2DArray setMagFilter(GlFilter filter) {
        bind();
        super.glSetMagFilter(PARAM_TARGET, filter);
        return this;
    }

    public Texture2DArray setFilters(GlFilter min, GlFilter mag) {
        bind();
        super.glSetMinFilter(PARAM_TARGET, min);
        super.glSetMagFilter(PARAM_TARGET, mag);
        return this;
    }

    public Texture2DArray setFilters(GlFilter minAndMag) {
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


    public Texture2DArray setMinLod(int value) {
        bind();
        super.glSetMinLod(PARAM_TARGET, value);
        return this;
    }

    public int getMinLod() {
        bind();
        return glGetMinLod(PARAM_TARGET);
    }


    public Texture2DArray setMaxLod(int value) {
        bind();
        super.glSetMaxLod(PARAM_TARGET, value);
        return this;
    }

    public int getMaxLod() {
        bind();
        return glGetMaxLod(PARAM_TARGET);
    }


    public Texture2DArray setMaxLevel(int level) {
        bind();
        super.glSetMaxLevel(PARAM_TARGET, level);
        return this;
    }

    public int getMaxLevel() {
        bind();
        return glGetMaxLevel(PARAM_TARGET);
    }


    public Texture2DArray setSqizzleR(int value) {
        bind();
        super.glSetSqizzleR(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleR() {
        bind();
        return glGetSqizzleR(PARAM_TARGET);
    }


    public Texture2DArray setSqizzleG(int value) {
        bind();
        super.glSetSqizzleG(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleG() {
        bind();
        return glGetSqizzleG(PARAM_TARGET);
    }


    public Texture2DArray setSqizzleB(int value) {
        bind();
        super.glSetSqizzleB(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleB() {
        bind();
        return glGetSqizzleB(PARAM_TARGET);
    }


    public Texture2DArray setSqizzleA(int value) {
        bind();
        super.glSetSqizzleA(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleA() {
        bind();
        return glGetSqizzleA(PARAM_TARGET);
    }


    public Texture2DArray setWrapS(GlWrap wrap) {
        bind();
        super.glSetWrapS(PARAM_TARGET, wrap);
        return this;
    }

    public Texture2DArray setWrapT(GlWrap wrap) {
        bind();
        super.glSetWrapT(PARAM_TARGET, wrap);
        return this;
    }

    public Texture2DArray setWrapR(GlWrap wrap) {
        bind();
        super.glSetWrapR(PARAM_TARGET, wrap);
        return this;
    }

    public Texture2DArray setWrap(GlWrap s, GlWrap t) {
        bind();
        super.glSetWrapS(PARAM_TARGET, s);
        super.glSetWrapT(PARAM_TARGET, t);
        return this;
    }

    public Texture2DArray setWrap(GlWrap s, GlWrap t, GlWrap r) {
        bind();
        super.glSetWrapS(PARAM_TARGET, s);
        super.glSetWrapT(PARAM_TARGET, t);
        super.glSetWrapT(PARAM_TARGET, r);
        return this;
    }

    public Texture2DArray setWrapST(GlWrap wrap) {
        return setWrap(wrap, wrap);
    }

    public Texture2DArray setWrapSTR(GlWrap wrap) {
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


    public Texture2DArray setBorderColor(float... color) {
        bind();
        super.glSetBorderColor(PARAM_TARGET, color);
        return this;
    }

    public float[] getBorderColor() {
        bind();
        return glGetBorderColor(PARAM_TARGET);
    }


    public Texture2DArray setSwizzle(float... color) {
        bind();
        super.glSetSwizzle(PARAM_TARGET, color);
        return this;
    }

    public float[] getSwizzle() {
        bind();
        return glGetSwizzle(PARAM_TARGET);
    }


    public Texture2DArray setMaxAnisotropy(float levels) {
        bind();
        super.glSetMaxAnisotropy(PARAM_TARGET, levels);
        return this;
    }

    public float getMaxAnisotropy() {
        bind();
        return glGetMaxAnisotropy(PARAM_TARGET);
    }


    // level params


    public int getWidth(GlTexLevelTarget target, int level) {
        bind();
        return super.glGetWidth(target, level);
    }

    public int getHeight(GlTexLevelTarget target, int level) {
        bind();
        return super.glGetHeight(target, level);
    }

    public int getDepth(GlTexLevelTarget target, int level) {
        bind();
        return super.glGetDepth(target, level);
    }

    public int getWidth(GlTexLevelTarget target) {
        return getWidth(target, 0);
    }

    public int getHeight(GlTexLevelTarget target) {
        return getHeight(target, 0);
    }

    public int getDepth(GlTexLevelTarget target) {
        return getDepth(target, 0);
    }


    public GlInternalFormat getInternalFormat(GlTexLevelTarget target, int level) {
        bind();
        return super.glGetInternalFormat(target, level);
    }

    public GlInternalFormat getInternalFormat(GlTexLevelTarget target) {
        return getInternalFormat(target, 0);
    }


    public int getRedSize(GlTexLevelTarget target, int level) {
        bind();
        return super.glGetRedSize(target, level);
    }

    public int getRedSize(GlTexLevelTarget target) {
        return getRedSize(target, 0);
    }

    public int getGreenSize(GlTexLevelTarget target, int level) {
        bind();
        return super.glGetGreenSize(target, level);
    }

    public int getGreenSize(GlTexLevelTarget target) {
        return getGreenSize(target, 0);
    }

    public int getBlueSize(GlTexLevelTarget target, int level) {
        bind();
        return super.glGetBlueSize(target, level);
    }

    public int getBlueSize(GlTexLevelTarget target) {
        return getBlueSize(target, 0);
    }

    public int getAlphaSize(GlTexLevelTarget target, int level) {
        bind();
        return super.glGetAlphaSize(target, level);
    }

    public int getAlphaSize(GlTexLevelTarget target) {
        return getAlphaSize(target, 0);
    }


    public int getDepthSize(GlTexLevelTarget target, int level) {
        bind();
        return super.glGetDepthSize(target, level);
    }

    public int getDepthSize(GlTexLevelTarget target) {
        return getDepthSize(target, 0);
    }

    public int getCompressedImageSize(GlTexLevelTarget target, int level) {
        bind();
        return super.glGetCompressedImageSize(target, level);
    }

    public int getCompressedImageSize(GlTexLevelTarget target) {
        return getCompressedImageSize(target, 0);
    }

    public boolean isCompressed(GlTexLevelTarget target, int level) {
        bind();
        return super.glIsCompressed(target, level);
    }

    public boolean isCompressed(GlTexLevelTarget target) {
        return isCompressed(target, 0);
    }

    public int getBufferOffset(GlTexLevelTarget target, int level) {
        bind();
        return super.glGetBufferOffset(target, level);
    }

    public int getBufferOffset(GlTexLevelTarget target) {
        return getBufferOffset(target, 0);
    }
    
}
