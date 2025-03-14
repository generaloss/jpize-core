package jpize.util.font;

import jpize.opengl.texture.GlFilter;
import jpize.opengl.texture.Texture2D;
import jpize.util.math.Mathc;
import jpize.util.pixmap.PixmapAlpha;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.region.Region;
import jpize.util.res.Resource;

import java.nio.ByteBuffer;
import java.util.Map;

class TTFFontLoader {

    public static Font loadDefault(Font font, int size, boolean linearFilter, Charset charset) {
        return load(font, "/font/droidsans.ttf", size, linearFilter, charset);
    }

    public static Font loadDefault(Font font, int size, boolean linearFilter) {
        return loadDefault(font, size, linearFilter, Charset.DEFAULT_ENG_RUS);
    }

    public static Font loadDefault(Font font, int size) {
        return loadDefault(font, size, true);
    }

    public static Font loadDefault(Font font) {
        return loadDefault(font, 64);
    }

    public static Font loadDefaultBold(Font font, int size, boolean linearFilter, Charset charset) {
        return load(font, "/font/droidsans-bold.ttf", size, linearFilter, charset);
    }

    public static Font loadDefaultBold(Font font, int size, boolean linearFilter) {
        return loadDefaultBold(font, size, linearFilter, Charset.DEFAULT_ENG_RUS);
    }

    public static Font loadDefaultBold(Font font, int size) {
        return loadDefaultBold(font, size, true);
    }

    public static Font loadDefaultBold(Font font) {
        return loadDefaultBold(font, 64);
    }


    public static Font load(Font font, Resource resource, int size, boolean linearFilter, Charset charset) {
        // clear font
        font.pages().clear();
        font.glyphs().clear();
        font.setHeight(size);

        // // pixmap
        // final int bitmapSize = (size * Mathc.ceil(Math.sqrt(charset.size())) * 2);

        // final PixmapAlpha pixmapAlpha = new PixmapAlpha(bitmapSize, bitmapSize);
        // final ByteBuffer fontFileData = resource.readByteBuffer();
        // final STBTTBakedChar.Buffer charData = STBTTBakedChar.malloc(charset.maxChar() + 1);
        // STBTruetype.stbtt_BakeFontBitmap(fontFileData, size, pixmapAlpha.buffer(), bitmapSize, bitmapSize, charset.minChar(), charData);

        // final PixmapRGBA pixmapRgba = new PixmapRGBA(bitmapSize, bitmapSize);
        // pixmapRgba.clearRGB(0xFFFFFF);
        // pixmapRgba.setAlphaChannel(pixmapAlpha);

        // // texture
        // final Texture2D texture = new Texture2D()
        //     .setFilters(linearFilter ? GlFilter.LINEAR : GlFilter.NEAREST)
        //     .setImage(pixmapRgba);

        // pixmapAlpha.dispose();

        // font.pages().put(0, texture);

        // // stb
        // try(final MemoryStack stack = MemoryStack.stackPush()){
        //     // creating font
        //     final STBTTFontinfo info = STBTTFontinfo.create();
        //     STBTruetype.stbtt_InitFont(info, fontFileData);

        //     // getting ascent & descent
        //     final IntBuffer ascentBuffer = stack.mallocInt(1);
        //     final IntBuffer descentBuffer = stack.mallocInt(1);

        //     STBTruetype.stbtt_GetFontVMetrics(info, ascentBuffer, descentBuffer, null);

        //     final float pixelScale = STBTruetype.stbtt_ScaleForPixelHeight(info, size);
        //     font.setAscent(ascentBuffer.get() * pixelScale);
        //     font.setDescent(descentBuffer.get() * pixelScale);

        //     // getting ascent
        //     final STBTTAlignedQuad quad = STBTTAlignedQuad.malloc(stack);

        //     for(int i = 0; i < charset.size(); i++){
        //         final int code = charset.get(i);

        //         // getting advanceX
        //         final FloatBuffer advanceBufferX = stack.floats(0);
        //         final FloatBuffer advanceBufferY = stack.floats(0);
        //         STBTruetype.stbtt_GetBakedQuad(charData, bitmapSize, bitmapSize, code - charset.minChar(), advanceBufferX, advanceBufferY, quad, false);
        //         final float advanceX = advanceBufferX.get();

        //         // glyph texture region & glyph size
        //         final Region regionOnTexture = new Region(quad.s0(), quad.t0(), quad.s1(), quad.t1());
        //         float glyphHeight = (quad.y1() - quad.y0());
        //         float glyphWidth  = (quad.x1() - quad.x0());

        //         // adding glyph to the font
        //         font.glyphs().put(code, new GlyphInfo(
        //             code,

        //             quad.x0(),
        //             -(quad.y0() + glyphHeight + font.getDescent()),
        //             glyphWidth,
        //             glyphHeight,

        //             regionOnTexture,
        //             advanceX,
        //             0
        //         ));
        //     }

        //     // kernings
        //     final int kerningsLength = STBTruetype.stbtt_GetKerningTableLength(info);
        //     final STBTTKerningentry.Buffer kerningsBuffer = STBTTKerningentry.malloc(kerningsLength, stack);
        //     STBTruetype.stbtt_GetKerningTable(info, kerningsBuffer);

        //     for(STBTTKerningentry kerningEntry: kerningsBuffer){
        //         final int code_0 = kerningsBuffer.glyph1();
        //         final int code_1 = kerningsBuffer.glyph2();
        //         final int advance = kerningsBuffer.advance();

        //         // add kerning entry
        //         final Map<Integer, Integer> kerning = font.kernings().getOrDefault(code_0, new HashMap<>());
        //         kerning.put(code_1, advance);
        //         font.kernings().put(code_0, kerning);
        //     }
        // }
        // return font;
        return null; //TODO: Load TTF
    }

    public static Font load(Font font, String internalPath, int size, boolean linearFilter, Charset charset) {
        return load(font, Resource.internal(internalPath), size, linearFilter, charset);
    }

    public static Font load(Font font, Resource resource, int size, boolean linearFilter) {
        return load(font, resource, size, linearFilter, Charset.DEFAULT);
    }

    public static Font load(Font font, String internalPath, int size, boolean linearFilter) {
        return load(font, internalPath, size, linearFilter, Charset.DEFAULT);
    }

}
