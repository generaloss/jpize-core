package jpize.util.font;

import jpize.util.Utils;
import jpize.util.math.Mathc;
import jpize.util.res.Resource;
import jpize.gl.texture.GlFilter;
import jpize.util.font.glyph.Glyph;
import jpize.util.font.glyph.GlyphMap;
import jpize.util.font.glyph.GlyphPages;
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

public class FontLoader {

    public static Font loadDefault(int size, Charset charset) {
        return loadTrueType("/font/droidsans.ttf", size, charset);
    }

    public static Font loadDefault() {
        return loadDefault(64, Charset.DEFAULT_ENG_RUS);
    }

    public static Font loadDefaultBold(int size, Charset charset) {
        return loadTrueType("/font/droidsans-bold.ttf", size, charset);
    }

    public static Font loadDefaultBold() {
        return loadDefaultBold(64, Charset.DEFAULT_ENG_RUS);
    }


    public static Font loadFnt(Resource resource) {
        final GlyphPages pages = new GlyphPages();
        final GlyphMap glyphs = new GlyphMap();

        int height = 0;
        int ascent = 0;
        int descent = 0;

        boolean italic = false;

        final FastReader reader = resource.reader();

        while(reader.hasNext()){
            final String[] tokens = reader.nextLine().trim().split("\\s+");

            switch(tokens[0].toLowerCase()){
                case "info" -> italic = (Integer.parseInt(getValue(tokens[4])) == 1);
                case "common" -> {
                    height = Integer.parseInt(getValue(tokens[1]));
                    ascent = Integer.parseInt(getValue(tokens[2]));
                    descent = ascent - height;
                }
                case "page" -> {
                    final int pageID = Integer.parseInt(getValue(tokens[1]));

                    final Path path = Path.of(resource.path());
                    String relativeTexturePath = getValue(tokens[2]).replace("\"", "");

                    if(path.getParent() != null){
                        final String parentPath = Utils.osGeneralizePath(path.getParent().toString());
                        relativeTexturePath = Path.of(parentPath + "/" + relativeTexturePath).normalize().toString();
                    }

                    final Resource textureResource = Resource.internal(relativeTexturePath);
                    pages.add(pageID, new Texture2D(textureResource));
                }
                case "char" -> {
                    final int code = Integer.parseInt(getValue(tokens[1]));

                    final int page = Integer.parseInt(getValue(tokens[9]));
                    final Texture2D pageTexture = pages.get(page);

                    final float s0 = (float) Integer.parseInt(getValue(tokens[2])) / pageTexture.getWidth();
                    final float t0 = (float) Integer.parseInt(getValue(tokens[3])) / pageTexture.getHeight();
                    final float s1 = (float) Integer.parseInt(getValue(tokens[4])) / pageTexture.getWidth() + s0;
                    final float t1 = (float) Integer.parseInt(getValue(tokens[5])) / pageTexture.getHeight() + t0;

                    final int offsetX = Integer.parseInt(getValue(tokens[6]));
                    final int offsetY = Integer.parseInt(getValue(tokens[7]));
                    final int advanceX = Integer.parseInt(getValue(tokens[8]));

                    final Region regionOnTexture = new Region(s0, t0, s1, t1);
                    final float glyphHeight = regionOnTexture.getHeightPx(pages.get(page));
                    final float glyphWidth = regionOnTexture.getWidthPx(pages.get(page));

                    glyphs.add(new Glyph(
                        code,

                        offsetX,
                        height - offsetY - glyphHeight,
                        glyphWidth,
                        glyphHeight,

                        regionOnTexture,
                        advanceX,
                        page,
                        pages
                    ));
                }
            }
        }

        final FontInfo info = new FontInfo(height, ascent, descent);
        final Font font = new Font(info, pages, glyphs);
        font.options.italic = italic;

        return font;
    }

    public static Font loadFnt(String filepath) {
        return loadFnt(Resource.internal(filepath));
    }


    private static String getValue(String token) {
        return token.split("=")[1];
    }


    public static Font loadTrueType(Resource resource, int size, Charset charset) {
        final GlyphPages pages = new GlyphPages();
        final GlyphMap glyphs = new GlyphMap();

        float ascent;
        float descent;

        // Pixmap
        final int quadSize = size * Mathc.ceil(Math.sqrt(charset.size())) * 2;
        final int bitmapWidth =  quadSize;
        final int bitmapHeight = quadSize;

        final PixmapA pixmap = new PixmapA(bitmapWidth, bitmapHeight);
        final ByteBuffer fontFileData = resource.readByteBuffer();
        final STBTTBakedChar.Buffer charData = STBTTBakedChar.malloc(charset.maxChar() + 1);
        stbtt_BakeFontBitmap(fontFileData, size, pixmap.getBuffer(), bitmapWidth, bitmapHeight, charset.minChar(), charData);

        // Texture
        final Texture2D texture = new Texture2D()
            .setFilters(GlFilter.LINEAR)
            .setImage(pixmap.toPixmapRGBA());

        pages.add(0, texture);

        // STB
        try(final MemoryStack stack = MemoryStack.stackPush()){
            // Creating font
            final STBTTFontinfo fontInfo = STBTTFontinfo.create();
            stbtt_InitFont(fontInfo, fontFileData);

            // Getting ascent & descent
            final IntBuffer ascentBuffer = stack.mallocInt(1);
            final IntBuffer descentBuffer = stack.mallocInt(1);

            stbtt_GetFontVMetrics(fontInfo, ascentBuffer, descentBuffer, null);

            final float pixelScale = stbtt_ScaleForPixelHeight(fontInfo, size);
            ascent = ascentBuffer.get() * pixelScale;
            descent = descentBuffer.get() * pixelScale;

            // Getting ascent
            final STBTTAlignedQuad quad = STBTTAlignedQuad.malloc(stack);

            for(int i = 0; i < charset.size(); i++){
                final int code = charset.get(i);

                // Getting advanceX
                final FloatBuffer advanceXBuffer = stack.floats(0);
                final FloatBuffer advanceYBuffer = stack.floats(0);
                stbtt_GetBakedQuad(charData, bitmapWidth, bitmapHeight, code - charset.minChar(), advanceXBuffer, advanceYBuffer, quad, false);
                final float advanceX = advanceXBuffer.get();

                // Calculating glyph Region on the texture & glyph Width and Height
                final Region regionOnTexture = new Region(quad.s0(), quad.t0(), quad.s1(), quad.t1());
                float glyphHeight = quad.y1() - quad.y0();
                float glyphWidth = quad.x1() - quad.x0();

                // Adding Glyph to the font
                glyphs.add(new Glyph(
                    code,

                    quad.x0(),
                    -quad.y0() - glyphHeight - descent,
                    glyphWidth,
                    glyphHeight,

                    regionOnTexture,
                    advanceX,
                    0,
                    pages
                ));
            }
        }

        final FontInfo info = new FontInfo(size, ascent, descent);
        return new Font(info, pages, glyphs);
    }

    public static Font loadTrueType(String filepath, int size, Charset charset) {
        return loadTrueType(Resource.internal(filepath), size, charset);
    }

    public static Font loadTrueType(Resource resource, int size) {
        return loadTrueType(resource, size, Charset.DEFAULT);
    }

    public static Font loadTrueType(String filePath, int size) {
        return loadTrueType(filePath, size, Charset.DEFAULT);
    }

}
