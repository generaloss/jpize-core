package jpize.util.font;

import generaloss.freetype.freetype.*;
import generaloss.freetype.glyph.*;
import generaloss.freetype.image.FTBitmap;
import generaloss.freetype.image.FTPixelMode;
import generaloss.freetype.stroke.FTStroker;
import generaloss.freetype.stroke.FTStrokerLineCap;
import generaloss.freetype.stroke.FTStrokerLineJoin;
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

    private static PixmapRGBA createPixmap(FTBitmap bitmap) {
        final int width = (int) bitmap.getWidth();
        final int height = (int) bitmap.getRows();
        final PixmapRGBA pixmap = new PixmapRGBA(width, height);

        final ByteBuffer sourceBuf = bitmap.getBuffer();
        final FTPixelMode pixelMode = bitmap.getPixelMode();
        final int rowBytes = Math.abs(bitmap.getPitch());

        if(pixelMode != FTPixelMode.GRAY)
            throw new IllegalStateException("TTFFontLoader: FTPixelMode not supported: " + pixelMode);

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                final int index = (x + rowBytes * y);
                final int sourceColor = (sourceBuf.get(index) & 0xFF);
                final int color = (0xFFFFFF00 | sourceColor);
                pixmap.setPixelRGBA(x, y, color);
            }
        }

        return pixmap;
    }

    public static Font load(Font font, Resource resource, FontLoadOptions options) {
        // clear font
        font.pages().clear();
        font.glyphs().clear();

        // init lib
        final FTLibrary library = new FTLibrary();

        // ft_face
        final FTFace face = library.newMemoryFace(resource.readBytes(), 0);
        face.setPixelSizes(0, options.getSize());

        // metrics
        final FTSizeMetrics fontMetrics = face.getSize().getMetrics();
        final float ascender = fontMetrics.getAscender();
        final float descender = fontMetrics.getDescender();
        final float height = fontMetrics.getHeight();
        font.setHeight(height);

        // add missing char
        final Charset charset = options.getCharset().copy();
        charset.add(UNKNOWN_CHARACTER);

        // page atlas
        final TextureAtlas<Integer> pageAtlas = new TextureAtlas<>();
        pageAtlas.setPadding(1);

        // stroker
        final FTStroker stroker = library.newStroker();

        final int borderWidth = 16;// options.getBorderWidth();
        if(borderWidth != 0) {
            final boolean borderStraight = options.isBorderStraight();
            final FTStrokerLineCap linecap = (borderStraight ? FTStrokerLineCap.BUTT : FTStrokerLineCap.ROUND);
            final FTStrokerLineJoin linejoin = (borderStraight ? FTStrokerLineJoin.MITER_FIXED : FTStrokerLineJoin.ROUND);

            stroker.set(Math.abs(borderWidth) / 2048F, linecap, linejoin, 0F);
        }

        // glyphs
        for(Character c: charset) {
            final long glyphIndex = face.getCharIndex(c);
            face.loadChar(c);

            // load glyph image into the slot (erase previous one)
            face.loadGlyph(glyphIndex);

            // glyph slot
            final FTGlyphSlot slot = face.getGlyph();
            FTGlyph glyph = slot.getGlyph();
            // border
            if(borderWidth != 0)
                glyph = glyph.strokeBorder(stroker, borderWidth < 0, true);
            // bitmap
            final FTBitmapGlyph bitmapGlyph = glyph.toBitmap(FTRenderMode.NORMAL, null, true);

            // pixmap
            final FTBitmap bitmap = bitmapGlyph.getBitmap();

            final PixmapRGBA pixmap = createPixmap(bitmap);
            final int bitmapWidth = pixmap.getWidth();
            final int bitmapHeight = pixmap.getHeight();

            // glyph info
            final FTGlyphMetrics metrics = slot.getMetrics();
            final int charcode = (int) c;
            final float offsetX = slot.getBitmapLeft();
            final float offsetY = (slot.getBitmapTop() - bitmapHeight - ascender + height);
            final float advanceX = (metrics.getHoriAdvance() + borderWidth);

            final GlyphInfo glyphInfo = new GlyphInfo(charcode)
                .setSize(bitmapWidth, bitmapHeight)
                .setOffset(offsetX, offsetY)
                .setAdvanceX(advanceX);

            font.glyphs().put(charcode, glyphInfo);
            pageAtlas.put(charcode, pixmap);
        }

        stroker.done();

        // create page texture
        final float glyphSize = (height + 1F + borderWidth);
        final int pageSize = Maths.nextPow2((int) Math.sqrt(glyphSize * glyphSize * charset.size()));
        pageAtlas.build(pageSize, pageSize);

        final Texture2D pageTexture = pageAtlas.getTexture();
        final GLFilter pageFilter = (options.isLinearFilter() ? GLFilter.LINEAR : GLFilter.NEAREST);
        pageTexture.setFilters(pageFilter);

        font.pages().put(0, pageTexture);

        // set glyphs regions
        for(Character c: charset) {
            final int code = (int) c;

            final GlyphInfo glyphInfo = font.glyphs().get(code);
            if(glyphInfo == null)
                continue;

            final Region region = pageAtlas.getRegion(code);
            if(region == null)
                continue;

            glyphInfo.setRegion(region);
        }

        // dispose
        pageAtlas.getPixmap().dispose();
        face.done();
        library.done();

        return font;
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
