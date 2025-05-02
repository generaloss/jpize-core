package jpize.opengl.texture;

import jpize.opengl.glenum.GLCompareFunc;
import jpize.opengl.type.GLType;
import jpize.util.pixmap.Pixmap;
import jpize.util.pixmap.PixmapIO;

public class TextureCubemap extends GLTexture {

    public static GLTexTarget TARGET = GLTexTarget.TEXTURE_CUBE_MAP;
    public static GLTexParamTarget PARAM_TARGET = GLTexParamTarget.TEXTURE_CUBE_MAP;

    public TextureCubemap() {
        super();
        this.setFilters(GLFilter.LINEAR);
        this.setWrapSTR(GLWrap.CLAMP_TO_EDGE);
    }

    public TextureCubemap(Pixmap positiveX, Pixmap negativeX, Pixmap positiveY, Pixmap negativeY, Pixmap positiveZ, Pixmap negativeZ) {
        this();
        this.setDefaultImages(positiveX, negativeX, positiveY, negativeY, positiveZ, negativeZ);
    }

    public TextureCubemap(String positiveX, String negativeX, String positiveY, String negativeY, String positiveZ, String negativeZ) {
        this();
        this.setDefaultImages(positiveX, negativeX, positiveY, negativeY, positiveZ, negativeZ);
    }


    @Override
    public void dispose() {
        super.dispose();
    }

    public TextureCubemap bind() {
        super.glBind(PARAM_TARGET);
        return this;
    }

    public static void unbind() {
        GLTexture.glUnbind(PARAM_TARGET);
    }



    public TextureCubemap active(int active) {
        this.bind();
        super.glActiveTexture(active);
        return this;
    }

    public TextureCubemap generateMipmap() {
        this.bind();
        super.glGenerateMipmap(TARGET);
        return this;
    }


    public TextureCubemap generateMipmap(int baseLevel, int maxLevel) {
        this.bind();
        super.glSetBaseLevel(PARAM_TARGET, baseLevel);
        super.glSetMaxLevel(PARAM_TARGET, maxLevel);
        super.glGenerateMipmap(TARGET);
        return this;
    }



    public void setImage(GLCubemapTarget target, int level, Pixmap pixmap) {
        this.bind();
        super.glSetImage2D(target.imageTarget, level, pixmap.getWidth(), pixmap.getHeight(), pixmap.getFormat(), GLType.UNSIGNED_BYTE, pixmap.buffer());
    }

    public void setImage(GLCubemapTarget target, Pixmap pixmap) {
        this.setImage(target, 0, pixmap);
    }

    public void setDefaultImages(int level, Pixmap positiveX, Pixmap negativeX, Pixmap positiveY, Pixmap negativeY, Pixmap positiveZ, Pixmap negativeZ) {
        this.setImage(GLCubemapTarget.POSITIVE_X, level, positiveX);
        this.setImage(GLCubemapTarget.NEGATIVE_X, level, negativeX);
        this.setImage(GLCubemapTarget.POSITIVE_Y, level, positiveY);
        this.setImage(GLCubemapTarget.NEGATIVE_Y, level, negativeY);
        this.setImage(GLCubemapTarget.POSITIVE_Z, level, positiveZ);
        this.setImage(GLCubemapTarget.NEGATIVE_Z, level, negativeZ);
    }

    public void setDefaultImages(Pixmap positiveX, Pixmap negativeX, Pixmap positiveY, Pixmap negativeY, Pixmap positiveZ, Pixmap negativeZ) {
        this.setDefaultImages(0, positiveX, negativeX, positiveY, negativeY, positiveZ, negativeZ);
    }

    public void setDefaultImages(int level, Pixmap... pixmaps) {
        for(int i = 0; i < Math.min(GLCubemapTarget.values().length, pixmaps.length); i++)
            this.setImage(GLCubemapTarget.values()[i], level, pixmaps[i]);
    }

    public void setDefaultImages(Pixmap... pixmaps) {
        this.setDefaultImages(0, pixmaps);
    }


    public void setDefaultImages(int level, String positiveX, String negativeX, String positiveY, String negativeY, String positiveZ, String negativeZ) {
        final Pixmap[] pixmaps = {
            PixmapIO.load(positiveX), PixmapIO.load(negativeX),
            PixmapIO.load(positiveY), PixmapIO.load(negativeY),
            PixmapIO.load(positiveZ), PixmapIO.load(negativeZ),
        };

        this.setDefaultImages(pixmaps);

        for(Pixmap pixmap: pixmaps)
            pixmap.dispose();
    }

    public void setDefaultImages(String positiveX, String negativeX, String positiveY, String negativeY, String positiveZ, String negativeZ) {
        this.setDefaultImages(0, positiveX, negativeX, positiveY, negativeY, positiveZ, negativeZ);
    }



    public TextureCubemap setDepthStencilTextureMode(GLDepthStencilMode mode) {
        this.bind();
        super.glSetDepthStencilTextureMode(PARAM_TARGET, mode);
        return this;
    }

    public GLDepthStencilMode getDepthStencilTextureMode() {
        this.bind();
        return super.glGetDepthStencilTextureMode(PARAM_TARGET);
    }


    public TextureCubemap setBaseLevel(int level) {
        this.bind();
        super.glSetBaseLevel(PARAM_TARGET, level);
        return this;
    }

    public int getBaseLevel() {
        this.bind();
        return super.glGetBaseLevel(PARAM_TARGET);
    }


    public TextureCubemap setCompareFunc(GLCompareFunc value) {
        this.bind();
        super.glSetCompareFunc(PARAM_TARGET, value);
        return this;
    }

    public GLCompareFunc getCompareFunc() {
        this.bind();
        return super.glGetCompareFunc(PARAM_TARGET);
    }


    public TextureCubemap setCompareMode(GLCompareMode value) {
        this.bind();
        super.glSetCompareMode(PARAM_TARGET, value);
        return this;
    }

    public GLCompareMode getCompareMode() {
        this.bind();
        return super.glGetCompareMode(PARAM_TARGET);
    }


    public TextureCubemap setLodBias(float lodBias) {
        this.bind();
        super.glSetLodBias(PARAM_TARGET, lodBias);
        return this;
    }

    public float getLodBias() {
        this.bind();
        return super.glGetMaxAnisotropy(PARAM_TARGET);
    }


    public TextureCubemap setMinFilter(GLFilter filter) {
        this.bind();
        super.glSetMinFilter(PARAM_TARGET, filter);
        return this;
    }

    public TextureCubemap setMagFilter(GLFilter filter) {
        this.bind();
        super.glSetMagFilter(PARAM_TARGET, filter);
        return this;
    }

    public TextureCubemap setFilters(GLFilter min, GLFilter mag) {
        this.bind();
        super.glSetMinFilter(PARAM_TARGET, min);
        super.glSetMagFilter(PARAM_TARGET, mag);
        return this;
    }

    public TextureCubemap setFilters(GLFilter minAndMag) {
        return this.setFilters(minAndMag, minAndMag);
    }


    public GLFilter getMinFilter() {
        this.bind();
        return super.glGetMinFilter(PARAM_TARGET);
    }

    public GLFilter getMagFilter() {
        this.bind();
        return super.glGetMagFilter(PARAM_TARGET);
    }


    public TextureCubemap setMinLod(int value) {
        this.bind();
        super.glSetMinLod(PARAM_TARGET, value);
        return this;
    }

    public int getMinLod() {
        this.bind();
        return super.glGetMinLod(PARAM_TARGET);
    }


    public TextureCubemap setMaxLod(int value) {
        this.bind();
        super.glSetMaxLod(PARAM_TARGET, value);
        return this;
    }

    public int getMaxLod() {
        this.bind();
        return super.glGetMaxLod(PARAM_TARGET);
    }


    public TextureCubemap setMaxLevel(int level) {
        this.bind();
        super.glSetMaxLevel(PARAM_TARGET, level);
        return this;
    }

    public int getMaxLevel() {
        this.bind();
        return super.glGetMaxLevel(PARAM_TARGET);
    }


    public TextureCubemap setSqizzleR(int value) {
        this.bind();
        super.glSetSqizzleR(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleR() {
        this.bind();
        return super.glGetSqizzleR(PARAM_TARGET);
    }


    public TextureCubemap setSqizzleG(int value) {
        this.bind();
        super.glSetSqizzleG(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleG() {
        this.bind();
        return super.glGetSqizzleG(PARAM_TARGET);
    }


    public TextureCubemap setSqizzleB(int value) {
        this.bind();
        super.glSetSqizzleB(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleB() {
        this.bind();
        return super.glGetSqizzleB(PARAM_TARGET);
    }


    public TextureCubemap setSqizzleA(int value) {
        this.bind();
        super.glSetSqizzleA(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleA() {
        this.bind();
        return super.glGetSqizzleA(PARAM_TARGET);
    }


    public TextureCubemap setWrapS(GLWrap wrap) {
        this.bind();
        super.glSetWrapS(PARAM_TARGET, wrap);
        return this;
    }

    public TextureCubemap setWrapT(GLWrap wrap) {
        this.bind();
        super.glSetWrapT(PARAM_TARGET, wrap);
        return this;
    }

    public TextureCubemap setWrapR(GLWrap wrap) {
        this.bind();
        super.glSetWrapR(PARAM_TARGET, wrap);
        return this;
    }

    public TextureCubemap setWrap(GLWrap s, GLWrap t) {
        this.bind();
        super.glSetWrapS(PARAM_TARGET, s);
        super.glSetWrapT(PARAM_TARGET, t);
        return this;
    }

    public TextureCubemap setWrap(GLWrap s, GLWrap t, GLWrap r) {
        this.bind();
        super.glSetWrapS(PARAM_TARGET, s);
        super.glSetWrapT(PARAM_TARGET, t);
        super.glSetWrapT(PARAM_TARGET, r);
        return this;
    }

    public TextureCubemap setWrapST(GLWrap wrap) {
        return this.setWrap(wrap, wrap);
    }

    public TextureCubemap setWrapSTR(GLWrap wrap) {
        return this.setWrap(wrap, wrap, wrap);
    }


    public GLWrap getWrapS() {
        this.bind();
        return super.glGetWrapS(PARAM_TARGET);
    }

    public GLWrap getWrapT() {
        this.bind();
        return super.glGetWrapT(PARAM_TARGET);
    }

    public GLWrap getWrapR() {
        this.bind();
        return super.glGetWrapR(PARAM_TARGET);
    }


    public TextureCubemap setBorderColor(float... color) {
        this.bind();
        super.glSetBorderColor(PARAM_TARGET, color);
        return this;
    }

    public float[] getBorderColor() {
        this.bind();
        return super.glGetBorderColor(PARAM_TARGET);
    }


    public TextureCubemap setSwizzle(float... color) {
        this.bind();
        super.glSetSwizzle(PARAM_TARGET, color);
        return this;
    }

    public float[] getSwizzle() {
        this.bind();
        return super.glGetSwizzle(PARAM_TARGET);
    }


    public TextureCubemap setMaxAnisotropy(float levels) {
        this.bind();
        super.glSetMaxAnisotropy(PARAM_TARGET, levels);
        return this;
    }

    public float getMaxAnisotropy() {
        this.bind();
        return super.glGetMaxAnisotropy(PARAM_TARGET);
    }


    // level params

    public int getWidth(GLTexLevelTarget target, int level) {
        this.bind();
        return super.glGetWidth(target, level);
    }

    public int getHeight(GLTexLevelTarget target, int level) {
        this.bind();
        return super.glGetHeight(target, level);
    }

    public int getDepth(GLTexLevelTarget target, int level) {
        this.bind();
        return super.glGetDepth(target, level);
    }

    public int getWidth(GLTexLevelTarget target) {
        return this.getWidth(target, 0);
    }

    public int getHeight(GLTexLevelTarget target) {
        return this.getHeight(target, 0);
    }

    public int getDepth(GLTexLevelTarget target) {
        return this.getDepth(target, 0);
    }


    public GLInternalFormat getInternalFormat(GLTexLevelTarget target, int level) {
        this.bind();
        return super.glGetInternalFormat(target, level);
    }

    public GLInternalFormat getInternalFormat(GLTexLevelTarget target) {
        return this.getInternalFormat(target, 0);
    }


    public int getRedSize(GLTexLevelTarget target, int level) {
        this.bind();
        return super.glGetRedSize(target, level);
    }

    public int getRedSize(GLTexLevelTarget target) {
        return this.getRedSize(target, 0);
    }

    public int getGreenSize(GLTexLevelTarget target, int level) {
        this.bind();
        return super.glGetGreenSize(target, level);
    }

    public int getGreenSize(GLTexLevelTarget target) {
        return this.getGreenSize(target, 0);
    }

    public int getBlueSize(GLTexLevelTarget target, int level) {
        this.bind();
        return super.glGetBlueSize(target, level);
    }

    public int getBlueSize(GLTexLevelTarget target) {
        return this.getBlueSize(target, 0);
    }

    public int getAlphaSize(GLTexLevelTarget target, int level) {
        this.bind();
        return super.glGetAlphaSize(target, level);
    }

    public int getAlphaSize(GLTexLevelTarget target) {
        return this.getAlphaSize(target, 0);
    }


    public int getDepthSize(GLTexLevelTarget target, int level) {
        this.bind();
        return super.glGetDepthSize(target, level);
    }

    public int getDepthSize(GLTexLevelTarget target) {
        return this.getDepthSize(target, 0);
    }

    public int getCompressedImageSize(GLTexLevelTarget target, int level) {
        this.bind();
        return super.glGetCompressedImageSize(target, level);
    }

    public int getCompressedImageSize(GLTexLevelTarget target) {
        return this.getCompressedImageSize(target, 0);
    }

    public boolean isCompressed(GLTexLevelTarget target, int level) {
        this.bind();
        return super.glIsCompressed(target, level);
    }

    public boolean isCompressed(GLTexLevelTarget target) {
        return this.isCompressed(target, 0);
    }

    public int getBufferOffset(GLTexLevelTarget target, int level) {
        this.bind();
        return super.glGetBufferOffset(target, level);
    }

    public int getBufferOffset(GLTexLevelTarget target) {
        return this.getBufferOffset(target, 0);
    }
    
}
