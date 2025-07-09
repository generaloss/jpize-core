package jpize.util.font;

import generaloss.freetype.freetype.*;
import generaloss.freetype.glyph.*;
import generaloss.freetype.image.FTBitmap;
import generaloss.freetype.image.FTPixelMode;
import generaloss.freetype.stroke.FTStroker;
import generaloss.freetype.stroke.FTStrokerLineCap;
import generaloss.freetype.stroke.FTStrokerLineJoin;
import generaloss.freetype.types.FTVector;
import generaloss.freetype.types.PosType;
import jpize.opengl.texture.GLFilter;
import jpize.opengl.texture.Texture2D;
import jpize.util.atlas.TextureAtlas;
import jpize.util.math.Maths;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.region.Region;
import jpize.util.res.Resource;

import java.nio.ByteBuffer;

class FreeTypeFontLoader {

    private static final char UNKNOWN_CHARACTER = '\0';

    public static Font load(Font font, Resource resource, FontLoadOptions options) {
        // clear font
        font.dispose();

        final FTLibrary library = new FTLibrary();
        final FTFace face = library.newMemoryFace(resource.readByteBuffer(), 0);

        // flags
        final LoadFlags glyphLoadFlags = new LoadFlags(options.getHinting().loadFlags);
        glyphLoadFlags.set(FTLoad.COLOR).set(FTLoad.RENDER);

        final FaceFlags faceFlags = face.getFaceFlags();
        final boolean hasKerning = faceFlags.hasKerning();
        final boolean isFixedSizes = faceFlags.has(FTFaceFlag.FIXED_SIZES);

        if(isFixedSizes) {
            if(face.getNumFixedSizes() > 0)
                face.selectSize(0);
        }else{
            face.setPixelSizes(0L, options.getSize());
        }

        // attachment
        final Resource attachment = options.getAttachment();
        if(attachment != null) {
            final ByteBuffer attachmentBuffer = attachment.readByteBuffer();

            final FTOpenArgs parameters = new FTOpenArgs();
            parameters.setFlags(FTOpen.MEMORY);
            parameters.setMemoryBase(attachmentBuffer);

            face.attachStream(parameters);
            parameters.free();
        }

        // metrics
        final FTSizeMetrics sizeMetrics = face.getSize().getMetrics();
        final float ascender = sizeMetrics.getAscender();
        final float descender = sizeMetrics.getDescender();
        final float height = sizeMetrics.getHeight();
        font.setHeight(height);

        // add missing char
        final Charset charset = options.getCharset().copy();
        charset.add(UNKNOWN_CHARACTER);

        // page atlas
        final TextureAtlas<Long> pageGlyphAtlas = new TextureAtlas<>();
        pageGlyphAtlas.setPadding(1);

        // stroker
        final FTStroker stroker;

        final float borderWidth = options.getBorderWidth();
        if(borderWidth != 0F) {
            final boolean borderStraight = options.isBorderStraight();
            final FTStrokerLineCap linecap = (borderStraight ? FTStrokerLineCap.BUTT : FTStrokerLineCap.ROUND);
            final FTStrokerLineJoin linejoin = (borderStraight ? FTStrokerLineJoin.MITER_FIXED : FTStrokerLineJoin.ROUND);

            stroker = library.newStroker();
            stroker.set(Math.abs(borderWidth), linecap, linejoin, 0F);
        }else{
            stroker = null;
        }

        // glyphs
        charset.forEach((charcode, variationSelector) -> {
            final long glyphIndex;
            if(variationSelector != 0) {
                glyphIndex = face.getCharVariantIndex(charcode, variationSelector);
            }else{
                glyphIndex = face.getCharIndex(charcode);
            }

            final long codepoint = GlyphInfo.getCodePoint(charcode, variationSelector);

            face.loadGlyph(glyphIndex, glyphLoadFlags);

            // glyph slot
            final FTGlyphSlot slot = face.getGlyph();
            FTGlyph glyph = slot.getGlyph();
            // border
            if(stroker != null)
                glyph = glyph.strokeBorder(stroker, borderWidth < 0F, true);
            // bitmap
            final FTBitmapGlyph bitmapGlyph = glyph.toBitmap(FTRenderMode.NORMAL, null, true);

            // pixmap
            final FTBitmap bitmap = bitmapGlyph.getBitmap();

            final PixmapRGBA pixmap = createPixmap(bitmap);
            final int bitmapWidth = pixmap.getWidth();
            final int bitmapHeight = pixmap.getHeight();

            // glyph info
            final FTGlyphMetrics glyphMetrics = slot.getMetrics();
            final float offsetX = slot.getBitmapLeft();
            final float offsetY = (slot.getBitmapTop() - bitmapHeight - ascender + height);
            final float advanceX = (glyphMetrics.getHoriAdvance() + borderWidth);

            final GlyphInfo glyphInfo = new GlyphInfo(charcode, variationSelector)
                .setSize(bitmapWidth, bitmapHeight)
                .setOffset(offsetX, offsetY)
                .setAdvanceX(advanceX);

            // kerning
            if(hasKerning) {
                final FTVector kerningDst = new FTVector();
                charset.forEach((charcodeRight, variationSelectorRight) -> {
                    final long codepointRight = GlyphInfo.getCodePoint(charcodeRight, variationSelectorRight);

                    face.getKerning(charcode, charcodeRight, kerningDst);
                    final float x = kerningDst.getX(PosType.F26DOT6);
                    if(x == 0F)
                        return;

                    glyphInfo.kernings().put(codepointRight, (int) x);
                });
                kerningDst.free();
            }

            font.glyphs().put(codepoint, glyphInfo);
            pageGlyphAtlas.put(codepoint, pixmap);
        });

        if(stroker != null)
            stroker.done();

        // create page texture
        final float glyphSize = (height + 1F + borderWidth);
        final int pageSize = Maths.nextPow2((int) Math.sqrt(glyphSize * glyphSize * charset.size()));
        pageGlyphAtlas.build(pageSize, pageSize);

        final Texture2D pageTexture = pageGlyphAtlas.getTexture();
        final GLFilter pageFilter = (options.isLinearFilter() ? GLFilter.LINEAR : GLFilter.NEAREST);
        pageTexture.setFilters(pageFilter);

        font.pages().put(0, pageTexture);

        // set glyphs regions
        charset.forEach((charcode, variationSelector) -> {
            final long codepoint = GlyphInfo.getCodePoint(charcode, variationSelector);

            final GlyphInfo glyphInfo = font.glyphs().get(codepoint);
            if(glyphInfo == null)
                return;

            final Region region = pageGlyphAtlas.getRegion(codepoint);
            if(region == null)
                return;
            glyphInfo.setRegion(region);
        });

        // dispose
        pageGlyphAtlas.getPixmap().dispose();
        face.done();
        library.done();

        return font;
    }

    private static PixmapRGBA createPixmap(FTBitmap bitmap) {
        final int width = (int) bitmap.getWidth();
        final int height = (int) bitmap.getRows();
        final PixmapRGBA pixmap = new PixmapRGBA(width, height);

        final int rowBytes = Math.abs(bitmap.getPitch());
        final ByteBuffer sourceBuffer = bitmap.getBuffer();

        final FTPixelMode pixelMode = bitmap.getPixelMode();
        switch(pixelMode) {
            case GRAY -> fillPixmapGray(pixmap, sourceBuffer, width, height, rowBytes);
            case MONO -> fillPixmapMono(pixmap, sourceBuffer, width, height, rowBytes);
            case BGRA -> fillPixmapBGRA(pixmap, sourceBuffer, width, height, rowBytes);
            default -> throw new IllegalStateException("TTFFontLoader: FTPixelMode not supported: " + pixelMode);
        }

        return pixmap;
    }

    private static void fillPixmapGray(PixmapRGBA pixmap, ByteBuffer sourceBuffer, int width, int height, int rowBytes) {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                final int index = (x + rowBytes * y);
                final int sourceColor = (sourceBuffer.get(index) & 0xFF);
                final int color = (0xFFFFFF00 | sourceColor);
                pixmap.setPixelRGBA(x, y, color);
            }
        }
    }

    private static void fillPixmapMono(PixmapRGBA pixmap, ByteBuffer sourceBuffer, int width, int height, int rowBytes) {
        for(int y = 0; y < height; y++) {
            final int rowStart = (y * rowBytes);

            for(int x = 0; x < width; x++) {
                final int byteIndex = rowStart + (x >> 3);
                final int bitIndex = 7 - (x & 7);

                final byte b = sourceBuffer.get(byteIndex);
                final boolean bitSet = ((b >> bitIndex) & 1) != 0;

                final int color = (bitSet ? 0xFFFFFFFF : 0);

                pixmap.setPixelRGBA(x, y, color);
            }
        }
    }

    private static void fillPixmapBGRA(PixmapRGBA pixmap, ByteBuffer sourceBuffer, int width, int height, int rowBytes) {
        for(int y = 0; y < height; y++) {
            final int rowStart = (y * rowBytes);
            for(int x = 0; x < width; x++) {
                final int pixelOffset = (rowStart + x * 4);

                final int b = sourceBuffer.get(pixelOffset    ) & 0xFF;
                final int g = sourceBuffer.get(pixelOffset + 1) & 0xFF;
                final int r = sourceBuffer.get(pixelOffset + 2) & 0xFF;
                final int a = sourceBuffer.get(pixelOffset + 3) & 0xFF;

                final int color = (r << 24) | (g << 16) | (b << 8) | a;

                pixmap.setPixelRGBA(x, y, color);
            }
        }
    }


    public static Font load(Font font, String internalPath, FontLoadOptions options) {
        return load(font, Resource.internal(internalPath), options);
    }

    public static Font loadDefault(Font font, FontLoadOptions options) {
        return load(font, "/font/droidsans.ttf", options);
    }

    public static Font loadDefaultBold(Font font, FontLoadOptions options) {
        return load(font, "/font/droidsans-bold.ttf", options);
    }


    public static Font load(Font font, Resource resource, int size) {
        final FontLoadOptions options = new FontLoadOptions().size(size);
        return load(font, resource, options);
    }

    public static Font load(Font font, String internalPath, int size) {
        final FontLoadOptions options = new FontLoadOptions().size(size);
        return load(font, Resource.internal(internalPath), options);
    }

    public static Font loadDefault(Font font, int size) {
        final FontLoadOptions options = new FontLoadOptions().size(size);
        return load(font, "/font/droidsans.ttf", options);
    }

    public static Font loadDefault(Font font) {
        return loadDefault(font, 64);
    }

    public static Font loadDefaultBold(Font font, int size) {
        final FontLoadOptions options = new FontLoadOptions().size(size);
        return load(font, "/font/droidsans-bold.ttf", options);
    }

    public static Font loadDefaultBold(Font font) {
        return loadDefaultBold(font, 64);
    }

}