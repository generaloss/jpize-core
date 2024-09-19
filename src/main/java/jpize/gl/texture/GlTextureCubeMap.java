package jpize.gl.texture;

import jpize.gl.glenum.GlCompareFunc;
import jpize.gl.type.GlType;
import jpize.util.pixmap.Pixmap;
import jpize.util.pixmap.PixmapIO;

public class GlTextureCubeMap extends GlTexture {

    public static GlTexTarget TARGET = GlTexTarget.TEXTURE_CUBE_MAP;
    public static GlTexParamTarget PARAM_TARGET = GlTexParamTarget.TEXTURE_CUBE_MAP;

    public GlTextureCubeMap() {
        super();
        setFilters(GlFilter.LINEAR);
    }

    public GlTextureCubeMap(String positive_x, String negative_x, String positive_y, String negative_y, String positive_z, String negative_z) {
        this();
        setDefaultImages(positive_x, negative_x, positive_y, negative_y, positive_z, negative_z);
    }

    public GlTextureCubeMap(Pixmap positive_x, Pixmap negative_x, Pixmap positive_y, Pixmap negative_y, Pixmap positive_z, Pixmap negative_z) {
        this();
        setDefaultImages(positive_x, negative_x, positive_y, negative_y, positive_z, negative_z);
    }


    @Override
    public void dispose() {
        super.dispose();
    }

    public GlTextureCubeMap bind() {
        super.glBind(PARAM_TARGET);
        return this;
    }

    public static void unbind() {
        GlTexture.glUnbind(PARAM_TARGET);
    }


    // tex func


    public GlTextureCubeMap active(int active) {
        bind();
        super.glActiveTexture(active);
        return this;
    }

    public GlTextureCubeMap generateMipmap() {
        bind();
        super.glGenerateMipmap(TARGET);
        return this;
    }


    public GlTextureCubeMap generateMipmap(int baseLevel, int maxLevel) {
        bind();
        super.glSetBaseLevel(PARAM_TARGET, baseLevel);
        super.glSetMaxLevel(PARAM_TARGET, maxLevel);
        super.glGenerateMipmap(TARGET);
        return this;
    }


    // image


    public void setDefaultImages(Pixmap positive_x, Pixmap negative_x, Pixmap positive_y, Pixmap negative_y, Pixmap positive_z, Pixmap negative_z) {
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_CUBE_MAP_POSITIVE_X, 0, positive_x.getWidth(), positive_x.getHeight(), GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, positive_x.getBuffer());
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_X, 0, negative_x.getWidth(), negative_x.getHeight(), GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, negative_x.getBuffer());
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_CUBE_MAP_POSITIVE_Y, 0, positive_y.getWidth(), positive_y.getHeight(), GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, positive_y.getBuffer());
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_Y, 0, negative_y.getWidth(), negative_y.getHeight(), GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, negative_y.getBuffer());
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_CUBE_MAP_POSITIVE_Z, 0, positive_z.getWidth(), positive_z.getHeight(), GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, positive_z.getBuffer());
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_Z, 0, negative_z.getWidth(), negative_z.getHeight(), GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, negative_z.getBuffer());
    }

    public void setDefaultImages(String positive_x, String negative_x, String positive_y, String negative_y, String positive_z, String negative_z) {
        this.setDefaultImages(
            PixmapIO.load(positive_x), PixmapIO.load(negative_x),
            PixmapIO.load(positive_y), PixmapIO.load(negative_y),
            PixmapIO.load(positive_z), PixmapIO.load(negative_z)
        );
    }


    // params


    public GlTextureCubeMap setDepthStencilTextureMode(GlDepthStencilMode mode) {
        bind();
        super.glSetDepthStencilTextureMode(PARAM_TARGET, mode);
        return this;
    }

    public GlDepthStencilMode getDepthStencilTextureMode() {
        bind();
        return glGetDepthStencilTextureMode(PARAM_TARGET);
    }


    public GlTextureCubeMap setBaseLevel(int level) {
        bind();
        super.glSetBaseLevel(PARAM_TARGET, level);
        return this;
    }

    public int getBaseLevel() {
        bind();
        return glGetBaseLevel(PARAM_TARGET);
    }


    public GlTextureCubeMap setCompareFunc(GlCompareFunc value) {
        bind();
        super.glSetCompareFunc(PARAM_TARGET, value);
        return this;
    }

    public GlCompareFunc getCompareFunc() {
        bind();
        return glGetCompareFunc(PARAM_TARGET);
    }


    public GlTextureCubeMap setCompareMode(GlCompareMode value) {
        bind();
        super.glSetCompareMode(PARAM_TARGET, value);
        return this;
    }

    public GlCompareMode getCompareMode() {
        bind();
        return glGetCompareMode(PARAM_TARGET);
    }


    public GlTextureCubeMap setLodBias(float lodBias) {
        bind();
        super.glSetLodBias(PARAM_TARGET, lodBias);
        return this;
    }

    public float getLodBias() {
        bind();
        return glGetMaxAnisotropy(PARAM_TARGET);
    }


    public GlTextureCubeMap setMinFilter(GlFilter filter) {
        bind();
        super.glSetMinFilter(PARAM_TARGET, filter);
        return this;
    }

    public GlTextureCubeMap setMagFilter(GlFilter filter) {
        bind();
        super.glSetMagFilter(PARAM_TARGET, filter);
        return this;
    }

    public GlTextureCubeMap setFilters(GlFilter min, GlFilter mag) {
        bind();
        super.glSetMinFilter(PARAM_TARGET, min);
        super.glSetMagFilter(PARAM_TARGET, mag);
        return this;
    }

    public GlTextureCubeMap setFilters(GlFilter minAndMag) {
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


    public GlTextureCubeMap setMinLod(int value) {
        bind();
        super.glSetMinLod(PARAM_TARGET, value);
        return this;
    }

    public int getMinLod() {
        bind();
        return glGetMinLod(PARAM_TARGET);
    }


    public GlTextureCubeMap setMaxLod(int value) {
        bind();
        super.glSetMaxLod(PARAM_TARGET, value);
        return this;
    }

    public int getMaxLod() {
        bind();
        return glGetMaxLod(PARAM_TARGET);
    }


    public GlTextureCubeMap setMaxLevel(int level) {
        bind();
        super.glSetMaxLevel(PARAM_TARGET, level);
        return this;
    }

    public int getMaxLevel() {
        bind();
        return glGetMaxLevel(PARAM_TARGET);
    }


    public GlTextureCubeMap setSqizzleR(int value) {
        bind();
        super.glSetSqizzleR(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleR() {
        bind();
        return glGetSqizzleR(PARAM_TARGET);
    }


    public GlTextureCubeMap setSqizzleG(int value) {
        bind();
        super.glSetSqizzleG(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleG() {
        bind();
        return glGetSqizzleG(PARAM_TARGET);
    }


    public GlTextureCubeMap setSqizzleB(int value) {
        bind();
        super.glSetSqizzleB(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleB() {
        bind();
        return glGetSqizzleB(PARAM_TARGET);
    }


    public GlTextureCubeMap setSqizzleA(int value) {
        bind();
        super.glSetSqizzleA(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleA() {
        bind();
        return glGetSqizzleA(PARAM_TARGET);
    }


    public GlTextureCubeMap setWrapS(GlWrap wrap) {
        bind();
        super.glSetWrapS(PARAM_TARGET, wrap);
        return this;
    }

    public GlTextureCubeMap setWrapT(GlWrap wrap) {
        bind();
        super.glSetWrapT(PARAM_TARGET, wrap);
        return this;
    }

    public GlTextureCubeMap setWrapR(GlWrap wrap) {
        bind();
        super.glSetWrapR(PARAM_TARGET, wrap);
        return this;
    }

    public GlTextureCubeMap setWrap(GlWrap s, GlWrap t) {
        bind();
        super.glSetWrapS(PARAM_TARGET, s);
        super.glSetWrapT(PARAM_TARGET, t);
        return this;
    }

    public GlTextureCubeMap setWrap(GlWrap s, GlWrap t, GlWrap r) {
        bind();
        super.glSetWrapS(PARAM_TARGET, s);
        super.glSetWrapT(PARAM_TARGET, t);
        super.glSetWrapT(PARAM_TARGET, r);
        return this;
    }

    public GlTextureCubeMap setWrapST(GlWrap wrap) {
        return setWrap(wrap, wrap);
    }

    public GlTextureCubeMap setWrapSTR(GlWrap wrap) {
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


    public GlTextureCubeMap setBorderColor(float... color) {
        bind();
        super.glSetBorderColor(PARAM_TARGET, color);
        return this;
    }

    public float[] getBorderColor() {
        bind();
        return glGetBorderColor(PARAM_TARGET);
    }


    public GlTextureCubeMap setSwizzle(float... color) {
        bind();
        super.glSetSwizzle(PARAM_TARGET, color);
        return this;
    }

    public float[] getSwizzle() {
        bind();
        return glGetSwizzle(PARAM_TARGET);
    }


    public GlTextureCubeMap setMaxAnisotropy(float levels) {
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
