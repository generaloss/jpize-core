package jpize.gl.texture;

import jpize.gl.glenum.GlCompareFunc;
import jpize.gl.type.GlType;
import jpize.util.pixmap.Pixmap;
import jpize.util.pixmap.PixmapIO;

public class TextureCubeMap extends GlTexture {

    public static GlTexTarget TARGET = GlTexTarget.TEXTURE_CUBE_MAP;
    public static GlTexParamTarget PARAM_TARGET = GlTexParamTarget.TEXTURE_CUBE_MAP;

    public TextureCubeMap() {
        super();
        this.setFilters(GlFilter.LINEAR);
    }

    public TextureCubeMap(Pixmap positiveX, Pixmap negativeX, Pixmap positiveY, Pixmap negativeY, Pixmap positiveZ, Pixmap negativeZ) {
        this();
        this.setDefaultImages(positiveX, negativeX, positiveY, negativeY, positiveZ, negativeZ);
    }

    public TextureCubeMap(String positiveX, String negativeX, String positiveY, String negativeY, String positiveZ, String negativeZ) {
        this();
        this.setDefaultImages(positiveX, negativeX, positiveY, negativeY, positiveZ, negativeZ);
    }


    @Override
    public void dispose() {
        super.dispose();
    }

    public TextureCubeMap bind() {
        super.glBind(PARAM_TARGET);
        return this;
    }

    public static void unbind() {
        GlTexture.glUnbind(PARAM_TARGET);
    }


    // tex func


    public TextureCubeMap active(int active) {
        this.bind();
        super.glActiveTexture(active);
        return this;
    }

    public TextureCubeMap generateMipmap() {
        this.bind();
        super.glGenerateMipmap(TARGET);
        return this;
    }


    public TextureCubeMap generateMipmap(int baseLevel, int maxLevel) {
        this.bind();
        super.glSetBaseLevel(PARAM_TARGET, baseLevel);
        super.glSetMaxLevel(PARAM_TARGET, maxLevel);
        super.glGenerateMipmap(TARGET);
        return this;
    }


    // image


    public void setDefaultImages(Pixmap positiveX, Pixmap negativeX, Pixmap positiveY, Pixmap negativeY, Pixmap positiveZ, Pixmap negativeZ) {
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_CUBE_MAP_POSITIVE_X, 0, positiveX.getWidth(), positiveX.getHeight(), GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, positiveX.getBuffer());
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_X, 0, negativeX.getWidth(), negativeX.getHeight(), GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, negativeX.getBuffer());
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_CUBE_MAP_POSITIVE_Y, 0, positiveY.getWidth(), positiveY.getHeight(), GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, positiveY.getBuffer());
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_Y, 0, negativeY.getWidth(), negativeY.getHeight(), GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, negativeY.getBuffer());
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_CUBE_MAP_POSITIVE_Z, 0, positiveZ.getWidth(), positiveZ.getHeight(), GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, positiveZ.getBuffer());
        super.glSetImage2D(GlTexImg2DTarget.TEXTURE_CUBE_MAP_NEGATIVE_Z, 0, negativeZ.getWidth(), negativeZ.getHeight(), GlInternalFormat.RGBA8, GlType.UNSIGNED_BYTE, negativeZ.getBuffer());
    }

    public void setDefaultImages(String positiveX, String negativeX, String positiveY, String negativeY, String positiveZ, String negativeZ) {
        this.setDefaultImages(
            PixmapIO.load(positiveX), PixmapIO.load(negativeX),
            PixmapIO.load(positiveY), PixmapIO.load(negativeY),
            PixmapIO.load(positiveZ), PixmapIO.load(negativeZ)
        );
    }


    // params


    public TextureCubeMap setDepthStencilTextureMode(GlDepthStencilMode mode) {
        this.bind();
        super.glSetDepthStencilTextureMode(PARAM_TARGET, mode);
        return this;
    }

    public GlDepthStencilMode getDepthStencilTextureMode() {
        this.bind();
        return glGetDepthStencilTextureMode(PARAM_TARGET);
    }


    public TextureCubeMap setBaseLevel(int level) {
        this.bind();
        super.glSetBaseLevel(PARAM_TARGET, level);
        return this;
    }

    public int getBaseLevel() {
        this.bind();
        return glGetBaseLevel(PARAM_TARGET);
    }


    public TextureCubeMap setCompareFunc(GlCompareFunc value) {
        this.bind();
        super.glSetCompareFunc(PARAM_TARGET, value);
        return this;
    }

    public GlCompareFunc getCompareFunc() {
        this.bind();
        return glGetCompareFunc(PARAM_TARGET);
    }


    public TextureCubeMap setCompareMode(GlCompareMode value) {
        this.bind();
        super.glSetCompareMode(PARAM_TARGET, value);
        return this;
    }

    public GlCompareMode getCompareMode() {
        this.bind();
        return glGetCompareMode(PARAM_TARGET);
    }


    public TextureCubeMap setLodBias(float lodBias) {
        this.bind();
        super.glSetLodBias(PARAM_TARGET, lodBias);
        return this;
    }

    public float getLodBias() {
        this.bind();
        return glGetMaxAnisotropy(PARAM_TARGET);
    }


    public TextureCubeMap setMinFilter(GlFilter filter) {
        this.bind();
        super.glSetMinFilter(PARAM_TARGET, filter);
        return this;
    }

    public TextureCubeMap setMagFilter(GlFilter filter) {
        this.bind();
        super.glSetMagFilter(PARAM_TARGET, filter);
        return this;
    }

    public TextureCubeMap setFilters(GlFilter min, GlFilter mag) {
        this.bind();
        super.glSetMinFilter(PARAM_TARGET, min);
        super.glSetMagFilter(PARAM_TARGET, mag);
        return this;
    }

    public TextureCubeMap setFilters(GlFilter minAndMag) {
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


    public TextureCubeMap setMinLod(int value) {
        this.bind();
        super.glSetMinLod(PARAM_TARGET, value);
        return this;
    }

    public int getMinLod() {
        this.bind();
        return glGetMinLod(PARAM_TARGET);
    }


    public TextureCubeMap setMaxLod(int value) {
        this.bind();
        super.glSetMaxLod(PARAM_TARGET, value);
        return this;
    }

    public int getMaxLod() {
        this.bind();
        return glGetMaxLod(PARAM_TARGET);
    }


    public TextureCubeMap setMaxLevel(int level) {
        this.bind();
        super.glSetMaxLevel(PARAM_TARGET, level);
        return this;
    }

    public int getMaxLevel() {
        this.bind();
        return glGetMaxLevel(PARAM_TARGET);
    }


    public TextureCubeMap setSqizzleR(int value) {
        this.bind();
        super.glSetSqizzleR(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleR() {
        this.bind();
        return glGetSqizzleR(PARAM_TARGET);
    }


    public TextureCubeMap setSqizzleG(int value) {
        this.bind();
        super.glSetSqizzleG(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleG() {
        this.bind();
        return glGetSqizzleG(PARAM_TARGET);
    }


    public TextureCubeMap setSqizzleB(int value) {
        this.bind();
        super.glSetSqizzleB(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleB() {
        this.bind();
        return glGetSqizzleB(PARAM_TARGET);
    }


    public TextureCubeMap setSqizzleA(int value) {
        this.bind();
        super.glSetSqizzleA(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleA() {
        this.bind();
        return glGetSqizzleA(PARAM_TARGET);
    }


    public TextureCubeMap setWrapS(GlWrap wrap) {
        this.bind();
        super.glSetWrapS(PARAM_TARGET, wrap);
        return this;
    }

    public TextureCubeMap setWrapT(GlWrap wrap) {
        this.bind();
        super.glSetWrapT(PARAM_TARGET, wrap);
        return this;
    }

    public TextureCubeMap setWrapR(GlWrap wrap) {
        this.bind();
        super.glSetWrapR(PARAM_TARGET, wrap);
        return this;
    }

    public TextureCubeMap setWrap(GlWrap s, GlWrap t) {
        this.bind();
        super.glSetWrapS(PARAM_TARGET, s);
        super.glSetWrapT(PARAM_TARGET, t);
        return this;
    }

    public TextureCubeMap setWrap(GlWrap s, GlWrap t, GlWrap r) {
        this.bind();
        super.glSetWrapS(PARAM_TARGET, s);
        super.glSetWrapT(PARAM_TARGET, t);
        super.glSetWrapT(PARAM_TARGET, r);
        return this;
    }

    public TextureCubeMap setWrapST(GlWrap wrap) {
        return setWrap(wrap, wrap);
    }

    public TextureCubeMap setWrapSTR(GlWrap wrap) {
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


    public TextureCubeMap setBorderColor(float... color) {
        this.bind();
        super.glSetBorderColor(PARAM_TARGET, color);
        return this;
    }

    public float[] getBorderColor() {
        this.bind();
        return glGetBorderColor(PARAM_TARGET);
    }


    public TextureCubeMap setSwizzle(float... color) {
        this.bind();
        super.glSetSwizzle(PARAM_TARGET, color);
        return this;
    }

    public float[] getSwizzle() {
        this.bind();
        return glGetSwizzle(PARAM_TARGET);
    }


    public TextureCubeMap setMaxAnisotropy(float levels) {
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
        return getBufferOffset(target, 0);
    }
    
}
