package jpize.util.font;

import jpize.util.Utils;
import jpize.util.math.Mathc;
import jpize.util.res.*;
import jpize.gl.texture.GlFilter;
import jpize.util.region.Region;
import jpize.gl.texture.Texture2D;
import jpize.util.pixmap.PixmapA;
import jpize.util.io.FastReader;
import org.lwjgl.stb.STBTTAlignedQuad;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.file.Path;

import static org.lwjgl.stb.STBTruetype.*;

class FontLoader {

    private static String getValue(String token) {
        return token.split("=")[1];
    }


    public static Font loadDefault(Font font, int size, Charset charset, boolean linearFilter) {
        return FontLoader.loadTrueType(font, "/font/droidsans.ttf", size, charset, linearFilter);
    }

    public static Font loadDefault(Font font) {
        return loadDefault(font, 64, Charset.DEFAULT_ENG_RUS, true);
    }

    public static Font loadDefaultBold(Font font, int size, Charset charset, boolean linearFilter) {
        return FontLoader.loadTrueType(font, "/font/droidsans-bold.ttf", size, charset, linearFilter);
    }

    public static Font loadDefaultBold(Font font) {
        return loadDefaultBold(font, 64, Charset.DEFAULT_ENG_RUS, true);
    }


    public static Font loadFnt(Font font, Resource resource, boolean linearFilter) {
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
                    if(resource.isInternal()){
                        pageResource = Resource.internal(pageRelativePath);
                    }else if(resource.isExternal()){
                        pageResource = Resource.external(pageRelativePath);
                    }else if(resource instanceof ZipResource zipRes){
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

                    font.glyphs().put(code, new Glyph(
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
            }
        }
        return font;
    }

    public static Font loadFnt(Font font, String internalPath, boolean linearFilter) {
        return loadFnt(font, Resource.internal(internalPath), linearFilter);
    }


    public static Font loadTrueType(Font font, Resource resource, int size, Charset charset, boolean linearFilter) {
        // clear font
        font.pages().clear();
        font.glyphs().clear();

        // pixmap
        final int quadSize = size * Mathc.ceil(Math.sqrt(charset.size())) * 2;
        final int bitmapWidth =  quadSize;
        final int bitmapHeight = quadSize;

        final PixmapA pixmap = new PixmapA(bitmapWidth, bitmapHeight);
        final ByteBuffer fontFileData = resource.readByteBuffer();
        final STBTTBakedChar.Buffer charData = STBTTBakedChar.malloc(charset.maxChar() + 1);
        stbtt_BakeFontBitmap(fontFileData, size, pixmap.getBuffer(), bitmapWidth, bitmapHeight, charset.minChar(), charData);

        // texture
        final Texture2D texture = new Texture2D()
            .setFilters(linearFilter ? GlFilter.LINEAR : GlFilter.NEAREST)
            .setImage(pixmap.toPixmapRGBA());

        pixmap.dispose();

        font.pages().put(0, texture);

        // stb
        try(final MemoryStack stack = MemoryStack.stackPush()){
            // creating font
            final STBTTFontinfo fontInfo = STBTTFontinfo.create();
            stbtt_InitFont(fontInfo, fontFileData);

            // getting ascent & descent
            final IntBuffer ascentBuffer = stack.mallocInt(1);
            final IntBuffer descentBuffer = stack.mallocInt(1);

            stbtt_GetFontVMetrics(fontInfo, ascentBuffer, descentBuffer, null);

            final float pixelScale = stbtt_ScaleForPixelHeight(fontInfo, size);
            font.setAscent(ascentBuffer.get() * pixelScale);
            font.setDescent(descentBuffer.get() * pixelScale);

            // getting ascent
            final STBTTAlignedQuad quad = STBTTAlignedQuad.malloc(stack);

            for(int i = 0; i < charset.size(); i++){
                final int code = charset.get(i);

                // getting advanceX
                final FloatBuffer advanceXBuffer = stack.floats(0);
                final FloatBuffer advanceYBuffer = stack.floats(0);
                stbtt_GetBakedQuad(charData, bitmapWidth, bitmapHeight, code - charset.minChar(), advanceXBuffer, advanceYBuffer, quad, false);
                final float advanceX = advanceXBuffer.get();

                // calculating glyph region on the texture & glyph width and height
                final Region regionOnTexture = new Region(quad.s0(), quad.t0(), quad.s1(), quad.t1());
                float glyphHeight = quad.y1() - quad.y0();
                float glyphWidth = quad.x1() - quad.x0();

                // adding glyph to the font
                font.glyphs().put(code, new Glyph(
                    code,

                    quad.x0(),
                    -(quad.y0() + glyphHeight + font.getDescent()),
                    glyphWidth,
                    glyphHeight,

                    regionOnTexture,
                    advanceX,
                    0
                ));
            }
        }
        return font;
    }

    public static Font loadTrueType(Font font, String internalPath, int size, Charset charset, boolean linearFilter) {
        return loadTrueType(font, Resource.internal(internalPath), size, charset, linearFilter);
    }

    public static Font loadTrueType(Font font, Resource resource, int size, boolean linearFilter) {
        return loadTrueType(font, resource, size, Charset.DEFAULT, linearFilter);
    }

    public static Font loadTrueType(Font font, String internalPath, int size, boolean linearFilter) {
        return loadTrueType(font, internalPath, size, Charset.DEFAULT, linearFilter);
    }

}
