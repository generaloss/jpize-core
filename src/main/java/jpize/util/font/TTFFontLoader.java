package jpize.util.font;

import generaloss.freetype.*;
import generaloss.freetype.bitmap.*;
import generaloss.freetype.face.*;
import generaloss.freetype.glyph.*;
import generaloss.freetype.stroker.FTStroker;
import generaloss.freetype.stroker.FTStrokerLinecap;
import generaloss.freetype.stroker.FTStrokerLinejoin;
import jpize.opengl.texture.GlFilter;
import jpize.opengl.texture.Texture2D;
import jpize.util.atlas.TextureAtlas;
import jpize.util.math.Maths;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.region.Region;
import jpize.util.res.Resource;

import java.nio.ByteBuffer;

class TTFFontLoader {

    private static PixmapRGBA createPixmap(FTBitmap bitmap) {
        final int width = bitmap.getWidth();
        final int height = bitmap.getRows();
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
        final FTLibrary freetype = FTLibrary.init();

        // ft_face
        final FTFace face = freetype.newMemoryFace(resource.readBytes(), 0);
        if(!face.setPixelSizes(0, options.getSize()))
            throw new RuntimeException(FTLibrary.getLastError().toString());

        // metrics
        final FTSizeMetrics fontMetrics = face.getSize().getMetrics();
        final int ascender = fontMetrics.getAscender();
        final int descender = fontMetrics.getDescender();
        final int height = fontMetrics.getHeight();
        font.setHeight(height);

        // add missing char
        final Charset charset = options.getCharset().copy();
        charset.add('\0');

        // page atlas
        final TextureAtlas<Integer> pageAtlas = new TextureAtlas<>();
        pageAtlas.setPadding(1);

        // stroker
        final FTStroker stroker = freetype.strokerNew();

        final int borderWidth = options.getBorderWidth();
        if(borderWidth != 0) {
            final boolean borderStraight = options.isBorderStraight();
            final FTStrokerLinecap linecap = (borderStraight ? FTStrokerLinecap.BUTT : FTStrokerLinecap.ROUND);
            final FTStrokerLinejoin linejoin = (borderStraight ? FTStrokerLinejoin.MITER_FIXED : FTStrokerLinejoin.ROUND);

            stroker.set(Math.abs(borderWidth) * 64, linecap, linejoin, 0);
        }

        // glyphs
        for(Character c: charset) {
            if(!face.loadChar(c))
                continue;

            // load glyph image into the slot (erase previous one)
            final int glyphIndex = face.getCharIndex(c);
            if(!face.loadGlyph(glyphIndex))
                continue;

            // glyph slot
            final FTGlyphSlot glyphSlot = face.getGlyph();
            final FTGlyph glyph = glyphSlot.getGlyph();
            // border
            if(borderWidth != 0)
                glyph.strokeBorder(stroker, borderWidth < 0);
            // bitmap
            glyph.toBitmap(FTRenderMode.NORMAL);

            // pixmap
            final FTBitmap bitmap = glyph.getBitmap();
            final PixmapRGBA pixmap = createPixmap(bitmap);
            final int bitmapWidth = pixmap.getWidth();
            final int bitmapHeight = pixmap.getHeight();

            // glyph info
            final FTGlyphMetrics metrics = glyphSlot.getMetrics();
            final int code = (int) c;
            final int offsetX = glyphSlot.getBitmapLeft();
            final int offsetY = (glyphSlot.getBitmapTop() - bitmapHeight - ascender + height);
            final int advanceX = (metrics.getHoriAdvance() + borderWidth);

            final GlyphInfo glyphInfo = new GlyphInfo(code)
                .setSize(bitmapWidth, bitmapHeight)
                .setOffset(offsetX, offsetY)
                .setAdvanceX(advanceX);

            font.glyphs().put(code, glyphInfo);
            pageAtlas.put(code, pixmap);
        }

        // create page texture
        final int glyphSize = (height + 1 + borderWidth);
        final int pageSize = Maths.nextPow2((int) Math.sqrt(glyphSize * glyphSize * charset.size()));
        pageAtlas.build(pageSize, pageSize);

        final Texture2D pageTexture = pageAtlas.getTexture();
        final GlFilter pageFilter = (options.isLinearFilter() ? GlFilter.LINEAR : GlFilter.NEAREST);
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
        freetype.done();

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
