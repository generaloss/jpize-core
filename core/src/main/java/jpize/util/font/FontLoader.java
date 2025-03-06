package jpize.util.font;

import jpize.util.Utils;
import jpize.util.math.Mathc;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.res.*;
import jpize.opengl.texture.GlFilter;
import jpize.util.region.Region;
import jpize.opengl.texture.Texture2D;
import jpize.util.pixmap.PixmapAlpha;
import jpize.util.io.FastReader;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

class FontLoader {

    private static String getValue(String token) {
        return token.split("=")[1];
    }


    public static Font loadDefault(Font font, int size, boolean linearFilter, Charset charset) {
        return FontLoader.loadTTF(font, "/font/droidsans.ttf", size, linearFilter, charset);
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
        return FontLoader.loadTTF(font, "/font/droidsans-bold.ttf", size, linearFilter, charset);
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


    public static Font loadFNT(Font font, Resource resource, boolean linearFilter) {
        // clear font
        font.pages().clear();
        font.glyphs().clear();

        // read
        final FastReader reader = resource.reader();

        while(reader.hasNext()){
            final String[] tokens = reader.nextLine().trim().split("\\s+");

            switch(tokens[0].toLowerCase()){
                case "info" -> font.setItalic(Integer.parseInt(getValue(tokens[4])) == 1);
                case "common" -> {
                    font.setHeight(Integer.parseInt(getValue(tokens[1])));
                    font.setAscent(Integer.parseInt(getValue(tokens[2])));
                    font.setDescent(font.getAscent() - font.getHeight());
                }
                case "page" -> {
                    final int pageID = Integer.parseInt(getValue(tokens[1]));
                    String pageRelativePath = getValue(tokens[2]).replace("\"", "");

                    // page path
                    final Path path = Path.of(resource.path());
                    if(path.getParent() != null){
                        final Path pagePath = Path.of(path.getParent() + "/" + pageRelativePath);
                        pageRelativePath = pagePath.normalize().toString();
                    }
                    pageRelativePath = Utils.osGeneralizePath(pageRelativePath);

                    // page resource
                    final Resource pageResource;
                    if(resource.isInternalRes()){
                        pageResource = Resource.internal(pageRelativePath);
                    }else if(resource.isFileRes()){
                        pageResource = Resource.file(pageRelativePath);
                    }else if(resource.isZipRes()){
                        final ZipResource zipRes = resource.asZipRes();
                        pageResource = Resource.zip(zipRes.file(), zipRes.file().getEntry(pageRelativePath));
                    }else{
                        throw new IllegalArgumentException("Unable to load FNT font from UrlResource.");
                    }

                    // page texture
                    final Texture2D texture = new Texture2D()
                        .setFilters(linearFilter ? GlFilter.LINEAR : GlFilter.NEAREST)
                        .setImage(pageResource);

                    font.pages().put(pageID, texture);
                }
                case "char" -> {
                    final int code = Integer.parseInt(getValue(tokens[1]));

                    final int page = Integer.parseInt(getValue(tokens[9]));
                    final Texture2D pageTexture = font.pages().get(page);

                    final float s0 = (float) Integer.parseInt(getValue(tokens[2])) / pageTexture.getWidth();
                    final float t0 = (float) Integer.parseInt(getValue(tokens[3])) / pageTexture.getHeight();
                    final float s1 = (float) Integer.parseInt(getValue(tokens[4])) / pageTexture.getWidth() + s0;
                    final float t1 = (float) Integer.parseInt(getValue(tokens[5])) / pageTexture.getHeight() + t0;

                    final int offsetX = Integer.parseInt(getValue(tokens[6]));
                    final int offsetY = Integer.parseInt(getValue(tokens[7]));
                    final int advanceX = Integer.parseInt(getValue(tokens[8]));

                    final Region regionOnTexture = new Region(s0, t0, s1, t1);
                    final float glyphHeight = regionOnTexture.getPixelHeight(font.pages().get(page));
                    final float glyphWidth = regionOnTexture.getPixelWidth(font.pages().get(page));

                    font.glyphs().put(code, new GlyphInfo(
                        code,

                        offsetX,
                        (font.getHeight() - offsetY - glyphHeight),
                        glyphWidth,
                        glyphHeight,

                        regionOnTexture,
                        advanceX,
                        page
                    ));
                }
                case "kerning" -> {
                    final int code_0 = Integer.parseInt(getValue(tokens[1]));
                    final int code_1 = Integer.parseInt(getValue(tokens[2]));
                    final int advance = Integer.parseInt(getValue(tokens[3]));

                    // add kerning entry
                    final Map<Integer, Integer> kerning = font.kernings().getOrDefault(code_0, new HashMap<>());
                    kerning.put(code_1, advance);
                    font.kernings().put(code_0, kerning);
                }
            }
        }
        return font;
    }

    public static Font loadFNT(Font font, String internalPath, boolean linearFilter) {
        return loadFNT(font, Resource.internal(internalPath), linearFilter);
    }


    public static Font loadTTF(Font font, Resource resource, int size, boolean linearFilter, Charset charset) {
        // // clear font
        // font.pages().clear();
        // font.glyphs().clear();
        // font.setHeight(size);

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

    public static Font loadTTF(Font font, String internalPath, int size, boolean linearFilter, Charset charset) {
        return loadTTF(font, Resource.internal(internalPath), size, linearFilter, charset);
    }

    public static Font loadTTF(Font font, Resource resource, int size, boolean linearFilter) {
        return loadTTF(font, resource, size, linearFilter, Charset.DEFAULT);
    }

    public static Font loadTTF(Font font, String internalPath, int size, boolean linearFilter) {
        return loadTTF(font, internalPath, size, linearFilter, Charset.DEFAULT);
    }

}
