package jpize.util.font;

import generaloss.freetype.*;
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
        final FTPixelMode pixelMode = bitmap.getPixelMode();
        final int rowBytes = Math.abs(bitmap.getPitch());
        final ByteBuffer buffer = bitmap.getBuffer();

        if(color == Color.WHITE && pixelMode == FTPixelMode.GRAY && rowBytes == width && gamma == 1F)
            return new PixmapAlpha(buffer, width, rows);

        final PixmapRGBA pixmap = new PixmapRGBA(width, rows);
        final int rgba = color.getHexInteger();
        final byte[] srcRow = new byte[rowBytes];
        final int[] dstRow = new int[width];

        final IntBuffer dst = pixmap.buffer().asIntBuffer();
        if(pixelMode == FTPixelMode.MONO) {
            // use the specified color for each set bit
            for(int y = 0; y < rows; y++) {
                buffer.get(srcRow);
                for(int i = 0, x = 0; x < width; i++, x += 8) {
                    byte b = srcRow[i];
                    for(int j = 0, n = Math.min(8, width - x); j < n; j++) {
                        if((b & (1 << (7 - j))) != 0) {
                            dstRow[x + j] = rgba;
                        }else{
                            dstRow[x + j] = 0;
                        }
                    }
                }
                dst.put(dstRow);
            }
        }else{
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
                    }else if(alpha == 255) {
                        dstRow[x] = (rgb | a);
                    }else{
                        dstRow[x] = (rgb | (int) (a * Mathc.pow(alpha / 255f, gamma))); // inverse gamma
                    }
                }
                dst.put(dstRow);
            }
        }
        return pixmap;
    }

    private static int toInt(int value) {
        return ((value + 63) & -64) >> 6;
    }


    protected GlyphInfo createGlyph(char c, FreeTypeFace face, FreeTypeStroker stroker, float baseLine, PixmapPacker packer) {
        final boolean missing = (c != 0 && face.getCharIndex(c) == 0);
        if(missing)
            return null;

        if(!loadChar.getLoadingFlags(parameter)) return null;

        final FreeTypeGlyphSlot slot = face.getGlyph();
        FreeTypeGlyph mainGlyph = slot.getGlyph();

        try {
            mainGlyph.toBitmap(parameter.mono ? FreeType.FT_RENDER_MODE_MONO : FreeType.FT_RENDER_MODE_NORMAL);
        }catch(RuntimeException e){
            mainGlyph.dispose();
            System.err.println("Couldn't render char: " + c);
            return null;
        }
        FreeTypeBitmap mainBitmap = mainGlyph.getBitmap();
        Pixmap mainPixmap = createPixmap(mainBitmap, parameter.color, parameter.gamma);

        if(mainBitmap.getWidth() != 0 && mainBitmap.getRows() != 0) {
            int offsetX = 0, offsetY = 0;
            if(parameter.borderWidth > 0) {
                // execute stroker; this generates a glyph "extended" along the outline
                int top = mainGlyph.getTop(), left = mainGlyph.getLeft();
                FreeTypeGlyph borderGlyph = slot.getGlyph();
                borderGlyph.strokeBorder(stroker, false);
                borderGlyph.toBitmap(parameter.mono ? FreeType.FT_RENDER_MODE_MONO : FreeType.FT_RENDER_MODE_NORMAL);
                offsetX = left - borderGlyph.getLeft();
                offsetY = -(top - borderGlyph.getTop());

                // Render border (pixmap is bigger than main).
                FreeTypeBitmap borderBitmap = borderGlyph.getBitmap();
                Pixmap borderPixmap = borderBitmap.getPixmap(Format.RGBA8888, parameter.borderColor,
                    parameter.borderGamma);

                // Draw main glyph on top of border.
                for(int i = 0, n = parameter.renderCount; i < n; i++)
                    borderPixmap.drawPixmap(mainPixmap, offsetX, offsetY);

                mainPixmap.dispose();
                mainGlyph.dispose();
                mainPixmap = borderPixmap;
                mainGlyph = borderGlyph;
            }

            if(parameter.shadowOffsetX != 0 || parameter.shadowOffsetY != 0) {
                int mainW = mainPixmap.getWidth(), mainH = mainPixmap.getHeight();
                int shadowOffsetX = Math.max(parameter.shadowOffsetX, 0), shadowOffsetY = Math.max(
                    parameter.shadowOffsetY, 0);
                int shadowW = mainW + Math.abs(parameter.shadowOffsetX), shadowH = mainH + Math.abs(
                    parameter.shadowOffsetY);
                // use the Gdx2DPixmap constructor to avoid filling the pixmap twice
                Pixmap shadowPixmap = new Pixmap(shadowW, shadowH);
                shadowPixmap.setColor(packer.getTransparentColor());
                shadowPixmap.fill();

                Color shadowColor = parameter.shadowColor;
                float a = shadowColor.a;
                if(a != 0) {
                    byte r = (byte) (shadowColor.r * 255), g = (byte) (shadowColor.g * 255), b = (byte) (shadowColor.b * 255);
                    ByteBuffer mainPixels = mainPixmap.getPixels();
                    ByteBuffer shadowPixels = shadowPixmap.getPixels();
                    for(int y = 0; y < mainH; y++) {
                        int shadowRow = shadowW * (y + shadowOffsetY) + shadowOffsetX;
                        for(int x = 0; x < mainW; x++) {
                            int mainPixel = (mainW * y + x) * 4;
                            byte mainA = mainPixels.get(mainPixel + 3);
                            if(mainA == 0) continue;
                            int shadowPixel = (shadowRow + x) * 4;
                            shadowPixels.put(shadowPixel, r);
                            shadowPixels.put(shadowPixel + 1, g);
                            shadowPixels.put(shadowPixel + 2, b);
                            shadowPixels.put(shadowPixel + 3, (byte) ((mainA & 0xff) * a));
                        }
                    }
                }

                // Draw main glyph (with any border) on top of shadow.
                for(int i = 0, n = parameter.renderCount; i < n; i++)
                    shadowPixmap.drawPixmap(mainPixmap, Math.max(-parameter.shadowOffsetX, 0),
                        Math.max(-parameter.shadowOffsetY, 0));
                mainPixmap.dispose();
                mainPixmap = shadowPixmap;
            }else if(parameter.borderWidth == 0) {
                // No shadow and no border, draw glyph additional times.
                for(int i = 0, n = parameter.renderCount - 1; i < n; i++)
                    mainPixmap.drawPixmap(mainPixmap, 0, 0);
            }

            if(parameter.padTop > 0 || parameter.padLeft > 0 || parameter.padBottom > 0 || parameter.padRight > 0) {
                Pixmap padPixmap = new Pixmap(mainPixmap.getWidth() + parameter.padLeft + parameter.padRight,
                    mainPixmap.getHeight() + parameter.padTop + parameter.padBottom, mainPixmap.getFormat());
                padPixmap.setBlending(Blending.None);
                padPixmap.drawPixmap(mainPixmap, parameter.padLeft, parameter.padTop);
                mainPixmap.dispose();
                mainPixmap = padPixmap;
            }
        }

        final int width = mainPixmap.getWidth();
        final int height = mainPixmap.getHeight();
        final int xoffset = mainGlyph.getLeft();
        final int yoffset;
        if(parameter.flip)
            yoffset = -mainGlyph.getTop() + (int) baseLine;
        else
            yoffset = -(glyph.height - mainGlyph.getTop()) - (int) baseLine;

        final int xadvance = metrics.getHoriAdvance() + (int) parameter.borderWidth + parameter.spaceX;

        FreeTypeGlyphMetrics metrics = slot.getMetrics();
        GlyphInfo glyph = new GlyphInfo(
            c,
            xoffset,
            yoffset,
            width,
            height,
            region,
            xadvance,
            0
        );


        if(bitmapped) {
            mainPixmap.setColor(Color.CLEAR);
            mainPixmap.fill();
            ByteBuffer buf = mainBitmap.getBuffer();
            int whiteIntBits = Color.WHITE.toIntBits();
            int clearIntBits = Color.CLEAR.toIntBits();
            for(int h = 0; h < glyph.height; h++) {
                int idx = h * mainBitmap.getPitch();
                for(int w = 0; w < (glyph.width + glyph.xoffset); w++) {
                    int bit = (buf.get(idx + (w / 8)) >>> (7 - (w % 8))) & 1;
                    mainPixmap.drawPixel(w, h, ((bit == 1) ? whiteIntBits : clearIntBits));
                }
            }
        }

        PixmapPackerRectangle rect = packer.pack(mainPixmap);
        glyph.page = packer.getPages().indexOf(rect.page, true);
        glyph.srcX = rect.getX();
        glyph.srcY = rect.getY();

        // If a page was added, create a new texture region for the incrementally added glyph.
        if(parameter.incremental && data.regions != null && data.regions.size <= glyph.page)
            packer.updateTextureRegions(data.regions, parameter.minFilter, parameter.magFilter, parameter.genMipMaps);

        mainPixmap.dispose();
        mainGlyph.dispose();

        return glyph;
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

        final FreeTypeFace face = freetype.newFace(resource, 0);
        face.setPixelSizes(0, size);

        final FreeTypeSizeMetrics sizeMetrics = face.getSize().getMetrics();
        final int ascender = toInt(sizeMetrics.getAscender());
        final int descender = toInt(sizeMetrics.getDescender());
        final int height = toInt(sizeMetrics.getHeight());

        final FreeTypeBitmap bitmap = face. final Pixmap pixmap = createPixmap()

        for(int i = 0; i < CHARS.length(); i++) {
            if(!face.loadGlyph(face.getCharIndex(CHARS.charAt(i)), 0)) continue;
            if(!face.getGlyph().renderGlyph(FreeType.FT_RENDER_MODE_NORMAL)) continue;

            final FreeTypeBitmap bitmap = face.getGlyph().getBitmap();
            final FreeTypeGlyphMetrics glyphMetrics = face.getGlyph().getMetrics();

            System.out.println(toInt(glyphMetrics.getHoriBearingX()) + ", " + toInt(glyphMetrics.getHoriBearingY()));
            System.out.println(toInt(glyphMetrics.getWidth()) + ", " + toInt(glyphMetrics.getHeight()) + ", " + toInt(
                glyphMetrics.getHoriAdvance()));
            System.out.println(
                bitmap.getWidth() + ", " + bitmap.getRows() + ", " + bitmap.getPitch() + ", " + bitmap.getNumGray());

            for(int y = 0; y < bitmap.getRows(); y++) {
                for(int x = 0; x < bitmap.getWidth(); x++)
                    System.out.print(bitmap.getBuffer().get(x + bitmap.getPitch() * y) != 0 ? "X" : " ");
                System.out.println();
            }
        }

        face.done();
        library.done();

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
