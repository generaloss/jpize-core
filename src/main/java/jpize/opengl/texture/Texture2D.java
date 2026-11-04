package jpize.opengl.texture;

import jpize.opengl.glenum.GLCompareFunc;
import jpize.opengl.type.GLType;
import jpize.util.pixmap.PixmapIO;
import generaloss.spatialmath.vector.Vec2i;
import generaloss.resourceflow.resource.Resource;
import jpize.util.pixmap.Pixmap;

import java.nio.ByteBuffer;

public class Texture2D extends GLTexture {

    public static final GLTextureTarget TARGET = GLTextureTarget.TEXTURE_2D;
    public static final GLTexParamTarget PARAM_TARGET = GLTexParamTarget.TEXTURE_2D;
    public static final GLTexLevelTarget LEVEL_TARGET = GLTexLevelTarget.TEXTURE_2D;

    public Texture2D() {
        super();
        this.setFilters(GLFilter.NEAREST);
    }

    public Texture2D(int ID) {
        super(ID);
    }

    public Texture2D(int width, int height) {
        this();
        this.setDefaultImage(width, height);
    }

    public Texture2D(Pixmap pixmap) {
        this();
        this.setImage(pixmap);
    }

    public Texture2D(Resource res) {
        this(PixmapIO.load(res));
    }

    public Texture2D(String internalPath) {
        this(PixmapIO.load(internalPath));
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
        GLTexture.glUnbind(PARAM_TARGET);
    }


    // tex func


    public Texture2D active(int active) {
        GLTexture.glActiveTexture(active);
        this.bind();
        return this;
    }

    public static void resetActive() {
        GLTexture.glActiveTexture(0);
    }

    public Texture2D generateMipmap() {
        this.bind();
        GLTexture.glGenerateMipmap(TARGET);
        return this;
    }


    public Texture2D generateMipmap(int baseLevel, int maxLevel) {
        this.bind();
        GLTexture.glSetBaseLevel(PARAM_TARGET, baseLevel);
        GLTexture.glSetMaxLevel(PARAM_TARGET, maxLevel);
        GLTexture.glGenerateMipmap(TARGET);
        return this;
    }


    // image

    public Texture2D setImage(int width, int height, int level, GLInternalFormat format, GLType type, ByteBuffer pixels) {
        this.bind();
        GLTexture.glSetImage2D(GLTexImage2DTarget.TEXTURE_2D, level, width, height, format, type, pixels);
        return this;
    }

    public Texture2D setImage(int level, Pixmap pixmap) {
        this.bind();
        GLTexture.glSetImage2D(GLTexImage2DTarget.TEXTURE_2D, level, pixmap);
        return this;
    }

    public Texture2D setImage(Pixmap pixmap) {
        return this.setImage(0, pixmap);
    }

    public Texture2D setImage(int level, Resource res) {
        final Pixmap pixmap = PixmapIO.load(res);
        this.setImage(pixmap);
        pixmap.dispose();
        return this;
    }

    public Texture2D setImage(Resource res) {
        return this.setImage(0, res);
    }

    public Texture2D setImage(int level, String internalPath) {
        return this.setImage(level, Resource.internal(internalPath));
    }

    public Texture2D setImage(String internalPath) {
        return this.setImage(0, internalPath);
    }


    public Texture2D setDefaultImage(int width, int height, int level, ByteBuffer pixels) {
        return this.setImage(width, height, level, GLInternalFormat.RGBA8, GLType.UNSIGNED_BYTE, pixels);
    }

    public Texture2D setDefaultImage(int width, int height, ByteBuffer pixels) {
        return this.setDefaultImage(width, height, 0, pixels);
    }

    public Texture2D setImage(int width, int height, int level, GLInternalFormat format, GLType type) {
        return this.setImage(width, height, level, format, type, null);
    }

    public Texture2D setDefaultImage(int width, int height, int level) {
        return this.setImage(width, height, level, GLInternalFormat.RGBA8, GLType.UNSIGNED_BYTE);
    }

    public Texture2D setDefaultImage(int width, int height) {
        return this.setDefaultImage(width, height, 0);
    }


    public Texture2D getImage(int level, Pixmap pixmap) {
        this.bind();
        final int width = this.getWidth(level);
        final int height = this.getHeight(level);
        GLTexture.glGetImage(TARGET, level, width, height, pixmap);
        return this;
    }

    public Texture2D getImage(Pixmap pixmap) {
        return this.getImage(0, pixmap);
    }

    public Texture2D getSubImage(int level, int x, int y, int width, int height, Pixmap pixmap) {
        super.glGetSubImage(level, x, y, width, height, pixmap);
        return this;
    }

    public Texture2D getSubImage(int x, int y, int width, int height, Pixmap pixmap) {
        return this.getSubImage(0, x, y, width, height, pixmap);
    }

    // params


    public Texture2D setDepthStencilTextureMode(GLDepthStencilMode mode) {
        this.bind();
        GLTexture.glSetDepthStencilTextureMode(PARAM_TARGET, mode);
        return this;
    }

    public GLDepthStencilMode getDepthStencilTextureMode() {
        this.bind();
        return GLTexture.glGetDepthStencilTextureMode(PARAM_TARGET);
    }


    public Texture2D setBaseLevel(int level) {
        this.bind();
        GLTexture.glSetBaseLevel(PARAM_TARGET, level);
        return this;
    }

    public int getBaseLevel() {
        this.bind();
        return GLTexture.glGetBaseLevel(PARAM_TARGET);
    }


    public Texture2D setCompareFunc(GLCompareFunc value) {
        this.bind();
        GLTexture.glSetCompareFunc(PARAM_TARGET, value);
        return this;
    }

    public GLCompareFunc getCompareFunc() {
        this.bind();
        return GLTexture.glGetCompareFunc(PARAM_TARGET);
    }


    public Texture2D setCompareMode(GLCompareMode value) {
        this.bind();
        GLTexture.glSetCompareMode(PARAM_TARGET, value);
        return this;
    }

    public GLCompareMode getCompareMode() {
        this.bind();
        return GLTexture.glGetCompareMode(PARAM_TARGET);
    }


    public Texture2D setLodBias(float lodBias) {
        this.bind();
        GLTexture.glSetLodBias(PARAM_TARGET, lodBias);
        return this;
    }

    public float getLodBias() {
        this.bind();
        return GLTexture.glGetMaxAnisotropy(PARAM_TARGET);
    }


    public Texture2D setMinFilter(GLFilter filter) {
        this.bind();
        GLTexture.glSetMinFilter(PARAM_TARGET, filter);
        return this;
    }

    public Texture2D setMagFilter(GLFilter filter) {
        this.bind();
        GLTexture.glSetMagFilter(PARAM_TARGET, filter);
        return this;
    }

    public Texture2D setFilters(GLFilter min, GLFilter mag) {
        this.bind();
        GLTexture.glSetMinFilter(PARAM_TARGET, min);
        GLTexture.glSetMagFilter(PARAM_TARGET, mag);
        return this;
    }

    public Texture2D setFilters(GLFilter minAndMag) {
        return this.setFilters(minAndMag, minAndMag);
    }


    public GLFilter getMinFilter() {
        this.bind();
        return GLTexture.glGetMinFilter(PARAM_TARGET);
    }

    public GLFilter getMagFilter() {
        this.bind();
        return GLTexture.glGetMagFilter(PARAM_TARGET);
    }


    public Texture2D setMinLod(int value) {
        this.bind();
        GLTexture.glSetMinLod(PARAM_TARGET, value);
        return this;
    }

    public int getMinLod() {
        this.bind();
        return GLTexture.glGetMinLod(PARAM_TARGET);
    }


    public Texture2D setMaxLod(int value) {
        this.bind();
        GLTexture.glSetMaxLod(PARAM_TARGET, value);
        return this;
    }

    public int getMaxLod() {
        this.bind();
        return GLTexture.glGetMaxLod(PARAM_TARGET);
    }


    public Texture2D setMaxLevel(int level) {
        this.bind();
        GLTexture.glSetMaxLevel(PARAM_TARGET, level);
        return this;
    }

    public int getMaxLevel() {
        this.bind();
        return GLTexture.glGetMaxLevel(PARAM_TARGET);
    }


    public Texture2D setSqizzleR(int value) {
        this.bind();
        GLTexture.glSetSqizzleR(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleR() {
        this.bind();
        return GLTexture.glGetSqizzleR(PARAM_TARGET);
    }


    public Texture2D setSqizzleG(int value) {
        this.bind();
        GLTexture.glSetSqizzleG(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleG() {
        this.bind();
        return GLTexture.glGetSqizzleG(PARAM_TARGET);
    }


    public Texture2D setSqizzleB(int value) {
        this.bind();
        GLTexture.glSetSqizzleB(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleB() {
        this.bind();
        return GLTexture.glGetSqizzleB(PARAM_TARGET);
    }


    public Texture2D setSqizzleA(int value) {
        this.bind();
        GLTexture.glSetSqizzleA(PARAM_TARGET, value);
        return this;
    }

    public int getSqizzleA() {
        this.bind();
        return GLTexture.glGetSqizzleA(PARAM_TARGET);
    }


    public Texture2D setWrapS(GLWrap wrap) {
        this.bind();
        GLTexture.glSetWrapS(PARAM_TARGET, wrap);
        return this;
    }

    public Texture2D setWrapT(GLWrap wrap) {
        this.bind();
        GLTexture.glSetWrapT(PARAM_TARGET, wrap);
        return this;
    }

    public Texture2D setWrapR(GLWrap wrap) {
        this.bind();
        GLTexture.glSetWrapR(PARAM_TARGET, wrap);
        return this;
    }

    public Texture2D setWrap(GLWrap s, GLWrap t) {
        this.bind();
        GLTexture.glSetWrapS(PARAM_TARGET, s);
        GLTexture.glSetWrapT(PARAM_TARGET, t);
        return this;
    }

    public Texture2D setWrap(GLWrap s, GLWrap t, GLWrap r) {
        this.bind();
        GLTexture.glSetWrapS(PARAM_TARGET, s);
        GLTexture.glSetWrapT(PARAM_TARGET, t);
        GLTexture.glSetWrapT(PARAM_TARGET, r);
        return this;
    }

    public Texture2D setWrapST(GLWrap wrap) {
        return this.setWrap(wrap, wrap);
    }

    public Texture2D setWrapSTR(GLWrap wrap) {
        return this.setWrap(wrap, wrap, wrap);
    }


    public GLWrap getWrapS() {
        this.bind();
        return GLTexture.glGetWrapS(PARAM_TARGET);
    }

    public GLWrap getWrapT() {
        this.bind();
        return GLTexture.glGetWrapT(PARAM_TARGET);
    }

    public GLWrap getWrapR() {
        this.bind();
        return GLTexture.glGetWrapR(PARAM_TARGET);
    }


    public Texture2D setBorderColor(float... color) {
        this.bind();
        GLTexture.glSetBorderColor(PARAM_TARGET, color);
        return this;
    }

    public float[] getBorderColor() {
        this.bind();
        return GLTexture.glGetBorderColor(PARAM_TARGET);
    }


    public Texture2D setSwizzle(float... color) {
        this.bind();
        GLTexture.glSetSwizzle(PARAM_TARGET, color);
        return this;
    }

    public float[] getSwizzle() {
        this.bind();
        return GLTexture.glGetSwizzle(PARAM_TARGET);
    }


    public Texture2D setMaxAnisotropy(float levels) {
        this.bind();
        GLTexture.glSetMaxAnisotropy(PARAM_TARGET, levels);
        return this;
    }

    public float getMaxAnisotropy() {
        this.bind();
        return GLTexture.glGetMaxAnisotropy(PARAM_TARGET);
    }


    // level params


    public int getWidth(int level) {
        this.bind();
        return GLTexture.glGetWidth(LEVEL_TARGET, level);
    }

    public int getHeight(int level) {
        this.bind();
        return GLTexture.glGetHeight(LEVEL_TARGET, level);
    }

    public int getWidth() {
        return this.getWidth(0);
    }

    public int getHeight() {
        return this.getHeight(0);
    }

    public float aspect() {
        this.bind();
        final int width = GLTexture.glGetWidth(LEVEL_TARGET, 0);
        final int height = GLTexture.glGetHeight(LEVEL_TARGET, 0);
        return Vec2i.aspect(width, height);
    }


    public GLInternalFormat getInternalFormat(int level) {
        this.bind();
        return GLTexture.glGetInternalFormat(LEVEL_TARGET, level);
    }

    public GLInternalFormat getInternalFormat() {
        return this.getInternalFormat(0);
    }


    public int getRedSize(int level) {
        this.bind();
        return GLTexture.glGetRedSize(LEVEL_TARGET, level);
    }

    public int getRedSize() {
        return this.getRedSize(0);
    }

    public int getGreenSize(int level) {
        this.bind();
        return GLTexture.glGetGreenSize(LEVEL_TARGET, level);
    }

    public int getGreenSize() {
        return this.getGreenSize(0);
    }

    public int getBlueSize(int level) {
        this.bind();
        return GLTexture.glGetBlueSize(LEVEL_TARGET, level);
    }

    public int getBlueSize() {
        return this.getBlueSize(0);
    }

    public int getAlphaSize(int level) {
        this.bind();
        return GLTexture.glGetAlphaSize(LEVEL_TARGET, level);
    }

    public int getAlphaSize() {
        return this.getAlphaSize(0);
    }


    public int getDepthSize(int level) {
        this.bind();
        return GLTexture.glGetDepthSize(LEVEL_TARGET, level);
    }

    public int getDepthSize() {
        return this.getDepthSize(0);
    }

    public int getCompressedImageSize(int level) {
        this.bind();
        return GLTexture.glGetCompressedImageSize(LEVEL_TARGET, level);
    }

    public int getCompressedImageSize() {
        return this.getCompressedImageSize(0);
    }

    public boolean isCompressed(int level) {
        this.bind();
        return GLTexture.glIsCompressed(LEVEL_TARGET, level);
    }

    public boolean isCompressed() {
        return this.isCompressed(0);
    }

    public int getBufferOffset(int level) {
        this.bind();
        return GLTexture.glGetBufferOffset(LEVEL_TARGET, level);
    }

    public int getBufferOffset() {
        return this.getBufferOffset(0);
    }

}