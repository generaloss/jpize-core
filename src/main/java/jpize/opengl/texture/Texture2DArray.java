package jpize.opengl.texture;

import jpize.opengl.glenum.GlCompareFunc;
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
        this.setImages(pixmaps);
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
        this.bind();
        super.glActiveTexture(active);
        return this;
    }

    public Texture2DArray generateMipmap() {
        this.bind();
        super.glGenerateMipmap(TARGET);
        return this;
    }


    public Texture2DArray generateMipmap(int baseLevel, int maxLevel) {
        this.bind();
        super.glSetBaseLevel(PARAM_TARGET, baseLevel);
        super.glSetMaxLevel(PARAM_TARGET, maxLevel);
        super.glGenerateMipmap(TARGET);
        return this;
    }


    // image


    public Texture2DArray setImages(Pixmap... pixmaps) {
        this.bind();
        for(int offsetZ = 0; offsetZ < pixmaps.length; offsetZ++){
            final Pixmap pixmap = pixmaps[offsetZ];
            super.glSetSubImage3D(GlTexImg3DTarget.TEXTURE_2D_ARRAY, 0, pixmap.getWidth(), pixmap.getHeight(), 1, 0, 0, offsetZ, pixmap);
        }
        return this;
    }


    // params


    public Texture2DArray setDepthStencilTextureMode(GlDepthStencilMode mode) {
        this.bind();
        super.glSetDepthStencilTextureMode(PARAM_TARGET, mode);
        return this;
    }

    public GlDepthStencilMode getDepthStencilTextureMode() {
        this.bind();
        return glGetDepthStencilTextureMode(PARAM_TARGET);
    }


    public Texture2DArray setBaseLevel(int level) {
        this.bind();
        super.glSetBaseLevel(PARAM_TARGET, level);
        return this;
    }

    public int getBaseLevel() {
        this.bind();
        return glGetBaseLevel(PARAM_TARGET);
    }


    public Texture2DArray setCompareFunc(GlCompareFunc value) {
        this.bind();
        super.glSetCompareFunc(PARAM_TARGET, value);
        return this;
    }

    public GlCompareFunc getCompareFunc() {
        this.bind();
        return glGetCompareFunc(PARAM_TARGET);
    }


    public Texture2DArray setCompareMode(GlCompareMode value) {
        this.bind();
        super.glSetCompareMode(PARAM_TARGET, value);
        return this;
    }

    public GlCompareMode getCompareMode() {
        this.bind();
        return glGetCompareMode(PARAM_TARGET);
    }


    public Texture2DArray setLodBias(float lodBias) {
        this.bind();
        super.glSetLodBias(PARAM_TARGET, lodBias);
        return this;
    }

    public float getLodBias() {
        this.bind();
        return glGetMaxAnisotropy(PARAM_TARGET);
    }


    public Texture2DArray setMinFilter(GlFilter filter) {
        this.bind();
        super.glSetMinFilter(PARAM_TARGET, filter);
        return this;
    }

    public Texture2DArray setMagFilter(GlFilter filter) {
        this.bind();
        super.glSetMagFilter(PARAM_TARGET, filter);
        return this;
    }

    public Texture2DArray setFilters(GlFilter min, GlFilter mag) {
        this.bind();
        super.glSetMinFilter(PARAM_TARGET, min);
        super.glSetMagFilter(PARAM_TARGET, mag);
        return this;
    }

    public Texture2DArray setFilters(GlFilter minAndMag) {
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


    public Texture2DArray setMinLod(int value) {
        this.bind();
        super.glSetMinLod(PARAM_TARGET, value);
        return this;
    }

    public int getMinLod() {
        this.bind();
        return glGetMinLod(PARAM_TARGET);
    }


    public Texture2DArray setMaxLod(int value) {
        this.bind();
        super.glSetMaxLod(PARAM_TARGET, value);
        return this;
    }

    public int getMaxLod() {
        this.bind();
        return glGetMaxLod(PARAM_TARGET);
    }


    public Texture2DArray setMaxLevel(int level) {
        this.bind();
        super.glSetMaxLevel(PARAM_TARGET, level);
        return this;
    }

    public int getMaxLevel() {
        this.bind();
        return glGetMaxLevel(PARAM_TARGET);
    }


    public Texture2DArray setSqizzleR(int value) {
        this.bind();
        super.glSetSqizzleR(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleR() {
        this.bind();
        return glGetSqizzleR(PARAM_TARGET);
    }


    public Texture2DArray setSqizzleG(int value) {
        this.bind();
        super.glSetSqizzleG(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleG() {
        this.bind();
        return glGetSqizzleG(PARAM_TARGET);
    }


    public Texture2DArray setSqizzleB(int value) {
        this.bind();
        super.glSetSqizzleB(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleB() {
        this.bind();
        return glGetSqizzleB(PARAM_TARGET);
    }


    public Texture2DArray setSqizzleA(int value) {
        this.bind();
        super.glSetSqizzleA(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleA() {
        this.bind();
        return glGetSqizzleA(PARAM_TARGET);
    }


    public Texture2DArray setWrapS(GlWrap wrap) {
        this.bind();
        super.glSetWrapS(PARAM_TARGET, wrap);
        return this;
    }

    public Texture2DArray setWrapT(GlWrap wrap) {
        this.bind();
        super.glSetWrapT(PARAM_TARGET, wrap);
        return this;
    }

    public Texture2DArray setWrapR(GlWrap wrap) {
        this.bind();
        super.glSetWrapR(PARAM_TARGET, wrap);
        return this;
    }

    public Texture2DArray setWrap(GlWrap s, GlWrap t) {
        this.bind();
        super.glSetWrapS(PARAM_TARGET, s);
        super.glSetWrapT(PARAM_TARGET, t);
        return this;
    }

    public Texture2DArray setWrap(GlWrap s, GlWrap t, GlWrap r) {
        this.bind();
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


    public Texture2DArray setBorderColor(float... color) {
        this.bind();
        super.glSetBorderColor(PARAM_TARGET, color);
        return this;
    }

    public float[] getBorderColor() {
        this.bind();
        return glGetBorderColor(PARAM_TARGET);
    }


    public Texture2DArray setSwizzle(float... color) {
        this.bind();
        super.glSetSwizzle(PARAM_TARGET, color);
        return this;
    }

    public float[] getSwizzle() {
        this.bind();
        return glGetSwizzle(PARAM_TARGET);
    }


    public Texture2DArray setMaxAnisotropy(float levels) {
        this.bind();
        super.glSetMaxAnisotropy(PARAM_TARGET, levels);
        return this;
    }

    public float getMaxAnisotropy() {
        this.bind();
        return glGetMaxAnisotropy(PARAM_TARGET);
    }


    // level params


    public int getWidth(GlTexLevelTarget target, int level) {
        this.bind();
        return super.glGetWidth(target, level);
    }

    public int getHeight(GlTexLevelTarget target, int level) {
        this.bind();
        return super.glGetHeight(target, level);
    }

    public int getDepth(GlTexLevelTarget target, int level) {
        this.bind();
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
        this.bind();
        return super.glGetInternalFormat(target, level);
    }

    public GlInternalFormat getInternalFormat(GlTexLevelTarget target) {
        return getInternalFormat(target, 0);
    }


    public int getRedSize(GlTexLevelTarget target, int level) {
        this.bind();
        return super.glGetRedSize(target, level);
    }

    public int getRedSize(GlTexLevelTarget target) {
        return getRedSize(target, 0);
    }

    public int getGreenSize(GlTexLevelTarget target, int level) {
        this.bind();
        return super.glGetGreenSize(target, level);
    }

    public int getGreenSize(GlTexLevelTarget target) {
        return getGreenSize(target, 0);
    }

    public int getBlueSize(GlTexLevelTarget target, int level) {
        this.bind();
        return super.glGetBlueSize(target, level);
    }

    public int getBlueSize(GlTexLevelTarget target) {
        return getBlueSize(target, 0);
    }

    public int getAlphaSize(GlTexLevelTarget target, int level) {
        this.bind();
        return super.glGetAlphaSize(target, level);
    }

    public int getAlphaSize(GlTexLevelTarget target) {
        return getAlphaSize(target, 0);
    }


    public int getDepthSize(GlTexLevelTarget target, int level) {
        this.bind();
        return super.glGetDepthSize(target, level);
    }

    public int getDepthSize(GlTexLevelTarget target) {
        return getDepthSize(target, 0);
    }

    public int getCompressedImageSize(GlTexLevelTarget target, int level) {
        this.bind();
        return super.glGetCompressedImageSize(target, level);
    }

    public int getCompressedImageSize(GlTexLevelTarget target) {
        return getCompressedImageSize(target, 0);
    }

    public boolean isCompressed(GlTexLevelTarget target, int level) {
        this.bind();
        return super.glIsCompressed(target, level);
    }

    public boolean isCompressed(GlTexLevelTarget target) {
        return isCompressed(target, 0);
    }

    public int getBufferOffset(GlTexLevelTarget target, int level) {
        this.bind();
        return super.glGetBufferOffset(target, level);
    }

    public int getBufferOffset(GlTexLevelTarget target) {
        return this.getBufferOffset(target, 0);
    }
    
}
