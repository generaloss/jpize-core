package jpize.util.font;

import generaloss.freetype.FreeType;
import generaloss.freetype.FreeTypeBitmap;
import jpize.context.Jpize;
import jpize.opengl.texture.GlFilter;
import jpize.opengl.texture.Texture2D;
import jpize.util.color.AbstractColor;
import jpize.util.color.Color;
import jpize.util.math.Mathc;
import jpize.util.pixmap.Pixmap;
import jpize.util.pixmap.PixmapAlpha;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.region.Region;
import jpize.util.res.Resource;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Map;

class TTFFontLoader {

    private static FreeType freetype;

    private static Pixmap createPixmap(FreeTypeBitmap bitmap, AbstractColor color, float gamma) {
        final int width = bitmap.getWidth();
        final int rows = bitmap.getRows();
        final int pixelMode = bitmap.getPixelMode();
        final int rowBytes = Math.abs(bitmap.getPitch());
        final ByteBuffer buffer = bitmap.getBuffer();

        if(color == Color.WHITE && pixelMode == FreeType.FT_PIXEL_MODE_GRAY && rowBytes == width && gamma == 1F)
            return new PixmapAlpha(buffer, width, rows);

        final PixmapRGBA pixmap = new PixmapRGBA(width, rows);
        final int rgba = color.getHexInteger();
        final byte[] srcRow = new byte[rowBytes];
        final int[] dstRow = new int[width];

        final IntBuffer dst = pixmap.buffer().asIntBuffer();
        if(pixelMode == FreeType.FT_PIXEL_MODE_MONO) {
            // use the specified color for each set bit
            for(int y = 0; y < rows; y++) {
                buffer.get(srcRow);
                for(int i = 0, x = 0; x < width; i++, x += 8) {
                    byte b = srcRow[i];
                    for(int j = 0, n = Math.min(8, width - x); j < n; j++) {
                        if((b & (1 << (7 - j))) != 0) {
                            dstRow[x + j] = rgba;
                        } else {
                            dstRow[x + j] = 0;
                        }
                    }
                }
                dst.put(dstRow);
            }
        } else {
            // use the specified color for RGB, blend the FreeType bitmap with alpha
            final int rgb = (rgba & 0xffffff00);
            final int a = (rgba & 0xff);
            for(int y = 0; y < rows; y++) {
                buffer.get(srcRow);
                for(int x = 0; x < width; x++) {
                    // zero raised to any power is always zero
                    // 255 (= one) raised to any power is always one
                    // we only need Math.pow() when alpha is NOT zero and NOT one
                    final int alpha = srcRow[x] & 0xff;
                    if(alpha == 0) {
                        dstRow[x] = rgb;
                    } else if(alpha == 255) {
                        dstRow[x] = (rgb | a);
                    } else {
                        dstRow[x] = (rgb | (int) (a * Mathc.pow(alpha / 255f, gamma))); // inverse gamma
                    }
                }
                dst.put(dstRow);
            }
        }
        return pixmap;
    }


    public static Font load(Font font, Resource resource, int size, boolean linearFilter, Charset charset) {
        if(freetype == null) {
            freetype = FreeType.init();
            Jpize.callbacks.addExit(freetype::done);
        }

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
        return font;
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

}
