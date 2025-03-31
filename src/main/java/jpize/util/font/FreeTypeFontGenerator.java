package jpize.util.font;

import generaloss.freetype.*;
import generaloss.freetype.bitmap.FTBitmap;
import generaloss.freetype.face.*;
import generaloss.freetype.glyph.*;
import generaloss.freetype.stroker.FTStroker;
import generaloss.freetype.stroker.FTStrokerLinecap;
import generaloss.freetype.stroker.FTStrokerLinejoin;
import jpize.opengl.texture.GlFilter;
import jpize.util.Disposable;
import jpize.util.atlas.TextureAtlas;
import jpize.util.color.AbstractColor;
import jpize.util.color.Color;
import jpize.util.math.Maths;
import jpize.util.pixmap.Pixmap;
import jpize.util.pixmap.PixmapRGBA;
import jpize.util.region.TextureRegion;
import jpize.util.res.Resource;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class FreeTypeFontGenerator {
/*
    static public final String DEFAULT_CHARS = "\u0000ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890\"!`?'.,;:()[]{}<>|/@\\^$€-%+=#_&~*\u0080\u0081\u0082\u0083\u0084\u0085\u0086\u0087\u0088\u0089\u008A\u008B\u008C\u008D\u008E\u008F\u0090\u0091\u0092\u0093\u0094\u0095\u0096\u0097\u0098\u0099\u009A\u009B\u009C\u009D\u009E\u009F\u00A0\u00A1\u00A2\u00A3\u00A4\u00A5\u00A6\u00A7\u00A8\u00A9\u00AA\u00AB\u00AC\u00AD\u00AE\u00AF\u00B0\u00B1\u00B2\u00B3\u00B4\u00B5\u00B6\u00B7\u00B8\u00B9\u00BA\u00BB\u00BC\u00BD\u00BE\u00BF\u00C0\u00C1\u00C2\u00C3\u00C4\u00C5\u00C6\u00C7\u00C8\u00C9\u00CA\u00CB\u00CC\u00CD\u00CE\u00CF\u00D0\u00D1\u00D2\u00D3\u00D4\u00D5\u00D6\u00D7\u00D8\u00D9\u00DA\u00DB\u00DC\u00DD\u00DE\u00DF\u00E0\u00E1\u00E2\u00E3\u00E4\u00E5\u00E6\u00E7\u00E8\u00E9\u00EA\u00EB\u00EC\u00ED\u00EE\u00EF\u00F0\u00F1\u00F2\u00F3\u00F4\u00F5\u00F6\u00F7\u00F8\u00F9\u00FA\u00FB\u00FC\u00FD\u00FE\u00FF";

    static public final int NO_MAXIMUM = -1; // A hint to scale the texture as needed, without capping it at any maximum size
    static private int maxTextureSize = 1024;

    final FTLibrary library;
    final FTFace face;
    final String name;
    boolean bitmapped = false;
    private int pixelWidth, pixelHeight;

    public FreeTypeFontGenerator(Resource fontFile) {
        this(fontFile, 0);
    }

    public FreeTypeFontGenerator(Resource fontFile, int faceIndex) {
        name = fontFile.simpleName();
        library = FTLibrary.init();
        face = library.newMemoryFace(fontFile, faceIndex);
        if(this.checkForBitmapFont())
            return;
        this.setPixelSizes(0, 15);
    }

    private FTLoadFlags getLoadingFlags(Hinting hinting) {
        final FTLoadFlags flags = new FTLoadFlags();
        switch(hinting) {
            case None -> flags.set(FTLoad.NO_HINTING);
            case Slight -> flags.set(FTLoadTarget.LIGHT);
            case Medium -> flags.set(FTLoadTarget.NORMAL);
            case Full -> flags.set(FTLoadTarget.MONO);
            case AutoSlight -> flags.set(FTLoad.FORCE_AUTOHINT).set(FTLoadTarget.LIGHT);
            case AutoMedium -> flags.set(FTLoad.FORCE_AUTOHINT).set(FTLoadTarget.NORMAL);
            case AutoFull -> flags.set(FTLoad.FORCE_AUTOHINT).set(FTLoadTarget.MONO);
        }
        return flags;
    }

    private boolean loadChar(int charCode) {
        final FTLoadFlags flags = new FTLoadFlags().set(FTLoad.DEFAULT).set(FTLoad.FORCE_AUTOHINT);
        return this.loadChar(charCode, flags);
    }

    private boolean loadChar(int charCode, FTLoadFlags flags) {
        return face.loadChar(charCode, flags);
    }

    private boolean checkForBitmapFont() {
        final FTFaceFlags faceFlags = face.getFaceFlags();

        if(faceFlags.has(FTFaceFlag.FIXED_SIZES) && faceFlags.has(FTFaceFlag.HORIZONTAL)) {
            if(this.loadChar(' ')) {
                final FTGlyphSlot slot = face.getGlyph();
                if(slot.getFormat() == FTGlyphFormat.BITMAP)
                    bitmapped = true;
            }
        }
        return bitmapped;
    }

    public Font generateFont(FreeTypeFontParameter parameter) {
        return this.generateFont(parameter, new FreeTypeBitmapFontData());
    }

    public Font generateFont(FreeTypeFontParameter parameter, FreeTypeBitmapFontData data) {
        boolean updateTextureRegions = (data.regions == null && parameter.packer != null);
        if(updateTextureRegions)
            data.regions = new ArrayList<>();

        this.generateData(parameter, data);

        if(updateTextureRegions) {
            parameter.packer.updateTextureRegions(data.regions, parameter.minFilter, parameter.magFilter, parameter.genMipMaps);
        }
        if(data.regions.isEmpty())
            throw new RuntimeException("Unable to create a font with no texture regions.");

        final Font font = new Font(); // data

        return font;
    }

    public int scaleForPixelHeight(int height) { // Uses ascender and descender of font to calculate real height that makes all glyphs to fit in given pixel size. Source: <a href="http://nothings.org/stb/stb_truetype.h">...</a> / stbtt_ScaleForPixelHeight
        this.setPixelSizes(0, height);
        final FTSizeMetrics fontMetrics = face.getSize().getMetrics();
        final int ascent = fontMetrics.getAscender();
        final int descent = fontMetrics.getDescender();
        return height * height / (ascent - descent);
    }

    public int scaleForPixelWidth(int width, int numChars) { // Uses max advance, ascender and descender of font to calculate real height that makes any n glyphs to fit in given pixel width.
        final FTSizeMetrics fontMetrics = face.getSize().getMetrics();
        final int advance = fontMetrics.getMaxAdvance();
        final int ascent = fontMetrics.getAscender();
        final int descent = fontMetrics.getDescender();
        final int unscaledHeight = ascent - descent;
        final int height = unscaledHeight * width / (advance * numChars);
        this.setPixelSizes(0, height);
        return height;
    }


    public int scaleToFitSquare(int width, int height, int numChars) { // Uses max advance, ascender and descender of font to calculate real height that makes any n glyphs to fit in given pixel width and height.
        return Math.min(scaleForPixelHeight(height), scaleForPixelWidth(width, numChars));
    }

    public static class GlyphAndBitmap {

        public GlyphInfo glyph;
        public FTBitmap bitmap;

    }

    public GlyphAndBitmap generateGlyphAndBitmap(int charCode, int size) { // Returns null if glyph was not found in the font. If there is nothing to render, for example with various space characters, then {@link GlyphAndBitmap#bitmap} will be null.
        this.setPixelSizes(0, size);

        final FTSizeMetrics fontMetrics = face.getSize().getMetrics();
        final int baseline = fontMetrics.getAscender();

        // Check if character exists in this font.
        // 0 means 'undefined character code'
        if(face.getCharIndex(charCode) == 0)
            return null;

        // Try to load character
        if(!this.loadChar(charCode))
            throw new RuntimeException("Unable to load character!");

        // Try to render to bitmap
        FTBitmap bitmap = null;

        final FTGlyphSlot slot = face.getGlyph();
        if(bitmapped || slot.renderGlyph(FTRenderMode.NORMAL))
            bitmap = slot.getBitmap();

        final FTGlyphMetrics metrics = slot.getMetrics();

        final GlyphInfo glyph = new GlyphInfo(charCode);
        if(bitmap != null)
            glyph.size().set(bitmap.getWidth(), bitmap.getRows());

        glyph.setAdvanceX(metrics.getHoriAdvance());

        glyph.offset().set(
            slot.getBitmapLeft(),
            -(glyph.size().y - slot.getBitmapTop()) - baseline
        );

        final GlyphAndBitmap result = new GlyphAndBitmap();
        result.glyph = glyph;
        result.bitmap = bitmap;
        return result;
    }

    //
    // Generates a new {@link FreeTypeBitmapFontData} instance, expert usage only. Throws a GdxRuntimeException if something went wrong.
    //
    // @param size the size in pixels
    //
    public FreeTypeBitmapFontData generateData(int size) {
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        return generateData(parameter);
    }

    public FreeTypeBitmapFontData generateData(FreeTypeFontParameter parameter) {
        return generateData(parameter, new FreeTypeBitmapFontData());
    }

    void setPixelSizes(int pixelWidth, int pixelHeight) {
        this.pixelWidth = pixelWidth;
        this.pixelHeight = pixelHeight;
        if(!bitmapped && !face.setPixelSizes(pixelWidth, pixelHeight))
            throw new RuntimeException("Couldn't set size for font");
    }

    public FreeTypeBitmapFontData generateData(FreeTypeFontParameter parameter, FreeTypeBitmapFontData data) {
        // data.name = name + "-" + parameter.size;
        char[] characters = parameter.characters.toCharArray();
        int charactersLength = characters.length;
        boolean incremental = parameter.incremental;
        final FTLoadFlags flags = this.getLoadingFlags(parameter.hinting);

        this.setPixelSizes(0, parameter.size);

        // set general font data
        final FTSizeMetrics fontMetrics = face.getSize().getMetrics();
        final int ascent = fontMetrics.getAscender();
        final int descent = fontMetrics.getDescender();
        int lineHeight = fontMetrics.getHeight();
        final float baseLine = ascent;

        // if bitmapped
        if(bitmapped && (lineHeight == 0)) {
            for(int c = 32; c < (32 + face.getNumGlyphs()); c++) {
                if(this.loadChar(c, flags)) {
                    final int glyphMetricsHeight = face.getGlyph().getMetrics().getHeight();
                    lineHeight = Math.max(glyphMetricsHeight, lineHeight);
                }
            }
        }
        lineHeight += parameter.spaceY;

        // determine space width
        if(this.loadChar(' ', flags) || this.loadChar('l', flags)) {
            data.spaceXadvance = face.getGlyph().getMetrics().getHoriAdvance();
        }else{
            data.spaceXadvance = face.getMaxAdvanceWidth(); // possibly very wrong.
        }

        // determine x-height
        // for(char xChar: data.xChars) {
        //     if(!this.loadChar(xChar, flags))
        //         continue;
        //     data.xHeight = face.getGlyph().getMetrics().getHeight();
        //     break;
        // }
        // if(data.xHeight == 0)
        //     throw new RuntimeException("No x-height character found in font");

        // determine cap height
        // for(char capChar: data.capChars) {
        //     if(!this.loadChar(capChar, flags))
        //         continue;
        //     data.capHeight = face.getGlyph().getMetrics().getHeight() + Math.abs(parameter.shadowOffsetY);
        //     break;
        // }
        // if(!bitmapped && data.capHeight == 1)
        //     throw new RuntimeException("No cap character found in font");

        // ascent -= data.capHeight;
        // data.down = -data.lineHeight;

        boolean ownsAtlas = false;

        TextureAtlas<Integer> packer = parameter.packer;

        if(packer == null) {
            // Create a packer.
            int size = Maths.nextPow2((int) Math.sqrt(lineHeight * lineHeight * charactersLength));
            if(maxTextureSize > 0)
                size = Math.min(size, maxTextureSize);
            ownsAtlas = true;
            packer = new TextureAtlas<>(size, size, Format.RGBA8888, 1, false, packStrategy);
            packer.setTransparentColor(parameter.color);
            packer.getTransparentColor().a = 0;
            if(parameter.borderWidth > 0) {
                packer.setTransparentColor(parameter.borderColor);
                packer.getTransparentColor().a = 0;
            }
        }

        if(incremental)
            data.glyphs = new ArrayList<>(charactersLength + 32);

        FTStroker stroker = null;
        if(parameter.borderWidth > 0) {
            stroker = library.strokerNew();
            stroker.set(
                (int) (parameter.borderWidth * 64F),
                (parameter.borderStraight ? FTStrokerLinecap.BUTT : FTStrokerLinecap.ROUND),
                (parameter.borderStraight ? FTStrokerLinejoin.MITER_FIXED : FTStrokerLinejoin.ROUND),
                0
            );
        }

        // Create glyphs largest height first for best packing.
        final int[] heights = new int[charactersLength];
        for(int i = 0; i < charactersLength; i++) {
            final char c = characters[i];

            int height = this.loadChar(c, flags) ? face.getGlyph().getMetrics().getHeight() : 0;
            heights[i] = height;

            if(c == '\0') {
                final GlyphInfo missingGlyph = createGlyph('\0', data, parameter, stroker, baseLine, packer);
                if(missingGlyph != null && missingGlyph.size().x != 0 && missingGlyph.size().y != 0) {
                    data.setGlyph('\0', missingGlyph);
                    data.missingGlyph = missingGlyph;
                    if(incremental)
                        data.glyphs.add(missingGlyph);
                }
            }
        }
        int heightsCount = heights.length;
        while(heightsCount > 0) {
            int best = 0, maxHeight = heights[0];
            for(int i = 1; i < heightsCount; i++) {
                int height = heights[i];
                if(height > maxHeight) {
                    maxHeight = height;
                    best = i;
                }
            }

            char c = characters[best];
            if(data.getGlyph(c) == null) {
                final GlyphInfo glyph = createGlyph(c, data, parameter, stroker, baseLine, packer);
                if(glyph != null) {
                    data.setGlyph(c, glyph);
                    if(incremental)
                        data.glyphs.add(glyph);
                }
            }

            heightsCount--;
            heights[best] = heights[heightsCount];
            char tmpChar = characters[best];
            characters[best] = characters[heightsCount];
            characters[heightsCount] = tmpChar;
        }

        if(stroker != null && !incremental)
            stroker.done();

        if(incremental) {
            data.generator = this;
            data.parameter = parameter;
            data.stroker = stroker;
            data.packer = packer;
        }

        // Generate kerning.
        parameter.kerning &= face.hasKerning();
        if(parameter.kerning) {
            for(int i = 0; i < charactersLength; i++) {
                char firstChar = characters[i];
                final GlyphInfo first = data.getGlyph(firstChar);
                if(first == null)
                    continue;
                int firstIndex = face.getCharIndex(firstChar);
                for(int ii = i; ii < charactersLength; ii++) {
                    char secondChar = characters[ii];
                    final GlyphInfo second = data.getGlyph(secondChar);
                    if(second == null)
                        continue;
                    int secondIndex = face.getCharIndex(secondChar);

                    int kerning = face.getKerning(firstIndex, secondIndex, FTKerningMode.DEFAULT);
                    if(kerning != 0)
                        first.kernings().put((int) secondChar, FreeType.FTPos_toInt(kerning));

                    kerning = face.getKerning(secondIndex, firstIndex, FTKerningMode.DEFAULT);
                    if(kerning != 0)
                        second.kernings().put((int) firstChar, FreeType.FTPos_toInt(kerning));
                }
            }
        }

        // Generate texture regions.
        if(ownsAtlas) {
            data.regions = new Array();
            packer.updateTextureRegions(data.regions, parameter.minFilter, parameter.magFilter, parameter.genMipMaps);
        }

        // Set space glyph.
        Glyph spaceGlyph = data.getGlyph(' ');
        if(spaceGlyph == null) {
            spaceGlyph = new Glyph();
            spaceGlyph.xadvance = (int) data.spaceXadvance + parameter.spaceX;
            spaceGlyph.id = (int) ' ';
            data.setGlyph(' ', spaceGlyph);
        }
        if(spaceGlyph.width == 0)
            spaceGlyph.width = (int) (spaceGlyph.xadvance + data.padRight);

        return data;
    }

    //
    // @return null if glyph was not found.
    //
    protected GlyphInfo createGlyph(char c, FreeTypeBitmapFontData data, FreeTypeFontParameter parameter, FTStroker stroker, float baseLine, TextureAtlas<Integer> packer) {

        boolean missing = (face.getCharIndex(c) == 0 && c != 0);
        if(missing)
            return null;

        if(!loadChar(c, getLoadingFlags(parameter.hinting)))
            return null;

        FTGlyphSlot slot = face.getGlyph();
        FTGlyph mainGlyph = slot.getGlyph();
        try {
            mainGlyph.toBitmap(parameter.mono ? FTRenderMode.MONO : FTRenderMode.NORMAL);
        } catch(RuntimeException e) {
            mainGlyph.done();
            System.err.println("Couldn't render char: " + c);
            return null;
        }
        FTBitmap mainBitmap = mainGlyph.getBitmap();
        PixmapRGBA mainPixmap = mainBitmap.getPixmap(Format.RGBA8888, parameter.color, parameter.gamma);

        if(mainBitmap.getWidth() != 0 && mainBitmap.getRows() != 0) {
            int offsetX = 0, offsetY = 0;
            if(parameter.borderWidth > 0) {
                // execute stroker; this generates a glyph "extended" along the outline
                int top = mainGlyph.getTop(), left = mainGlyph.getLeft();
                FTGlyph borderGlyph = slot.getGlyph();
                borderGlyph.strokeBorder(stroker, false);
                borderGlyph.toBitmap(parameter.mono ? FTRenderMode.MONO : FTRenderMode.NORMAL);
                offsetX = left - borderGlyph.getLeft();
                offsetY = -(top - borderGlyph.getTop());

                // Render border (pixmap is bigger than main).
                FTBitmap borderBitmap = borderGlyph.getBitmap();
                Pixmap borderPixmap = borderBitmap.getPixmap(Format.RGBA8888, parameter.borderColor,
                                                             parameter.borderGamma
                );

                // Draw main glyph on top of border.
                for(int i = 0, n = parameter.renderCount; i < n; i++) {
                    borderPixmap.drawPixmap(mainPixmap, offsetX, offsetY);
                }

                mainPixmap.dispose();
                mainGlyph.done();
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
                Pixmap shadowPixmap = new Pixmap(new Pixmap(shadowW, shadowH, Pixmap.Format.toGdx2DPixmapFormat(mainPixmap.getFormat())));
                shadowPixmap.setColor(packer.getTransparentColor());
                shadowPixmap.fill();

                Color shadowColor = parameter.shadowColor;
                float a = shadowColor.alpha;
                if(a != 0) {
                    byte r = (byte) (shadowColor.red * 255), g = (byte) (shadowColor.green * 255), b = (byte) (shadowColor.blue 255);
                    ByteBuffer mainPixels = mainPixmap.buffer();
                    ByteBuffer shadowPixels = shadowPixmap.buffer();
                    for(int y = 0; y < mainH; y++) {
                        int shadowRow = shadowW * (y + shadowOffsetY) + shadowOffsetX;
                        for(int x = 0; x < mainW; x++) {
                            int mainPixel = (mainW * y + x) * 4;
                            byte mainA = mainPixels.get(mainPixel + 3);
                            if(mainA == 0)
                                continue;
                            int shadowPixel = (shadowRow + x) * 4;
                            shadowPixels.put(shadowPixel, r);
                            shadowPixels.put(shadowPixel + 1, g);
                            shadowPixels.put(shadowPixel + 2, b);
                            shadowPixels.put(shadowPixel + 3, (byte) ((mainA & 0xff) * a));
                        }
                    }
                }

                // Draw main glyph (with any border) on top of shadow.
                for(int i = 0, n = parameter.renderCount; i < n; i++) {
                    shadowPixmap.drawPixmap(mainPixmap, Math.max(-parameter.shadowOffsetX, 0),
                                            Math.max(-parameter.shadowOffsetY, 0)
                    );
                }
                mainPixmap.dispose();
                mainPixmap = shadowPixmap;
            } else if(parameter.borderWidth == 0) {
                // No shadow and no border, draw glyph additional times.
                for(int i = 0, n = parameter.renderCount - 1; i < n; i++) {
                    mainPixmap.drawPixmap(mainPixmap, 0, 0);
                }
            }

            if(parameter.padTop > 0 || parameter.padLeft > 0 || parameter.padBottom > 0 || parameter.padRight > 0) {
                Pixmap padPixmap = new Pixmap(mainPixmap.getWidth() + parameter.padLeft + parameter.padRight,
                                              mainPixmap.getHeight() + parameter.padTop + parameter.padBottom,
                                              mainPixmap.getFormat()
                );
                padPixmap.setBlending(Blending.None);
                padPixmap.drawPixmap(mainPixmap, parameter.padLeft, parameter.padTop);
                mainPixmap.dispose();
                mainPixmap = padPixmap;
            }
        }

        FTGlyphMetrics metrics = slot.getMetrics();
        GlyphInfo glyph = new GlyphInfo(c)
                .setSize(mainPixmap.getWidth(), mainPixmap.getHeight())
                .setOffset(mainGlyph.getLeft(), -(glyph.size().y - mainGlyph.getTop()) - (int) baseLine)
                .setAdvanceX(metrics.getHoriAdvance() + (int) parameter.borderWidth + parameter.spaceX);

        if(bitmapped) {
            mainPixmap.setColor(Color.CLEAR);
            mainPixmap.fill();
            ByteBuffer buf = mainBitmap.getBuffer();
            int whiteIntBits = Color.WHITE.getHexInteger();
            int clearIntBits = Color.CLEAR.getHexInteger();
            for(int h = 0; h < glyph.size().y; h++) {
                int idx = h * mainBitmap.getPitch();
                for(int w = 0; w < (glyph.size().x + glyph.offset().x); w++) {
                    int bit = (buf.get(idx + (w / 8)) >>> (7 - (w % 8))) & 1;
                    mainPixmap.setPixel(w, h, ((bit == 1) ? whiteIntBits : clearIntBits));
                }
            }
        }

        PixmapPackerRectangle rect = packer.pack(mainPixmap);
        glyph.page = packer.getPages().indexOf(rect.page, true);
        glyph.srcX = rect.getX();
        glyph.srcY = rect.getY();

        // If a page was added, create a new texture region for the incrementally added glyph.
        if(parameter.incremental && data.regions != null && data.regions.size <= glyph.page) {
            packer.updateTextureRegions(data.regions, parameter.minFilter, parameter.magFilter, parameter.genMipMaps);
        }

        mainPixmap.dispose();
        mainGlyph.done();

        return glyph;
    }

    //
    // check the font glyph exists for single UTF-32 code point
    //
    public boolean hasGlyph(int charCode) {
        // 0 stand for undefined character code
        return face.getCharIndex(charCode) != 0;
    }

    public String toString() {
        return name;
    }

    //
    // Cleans up all resources of the generator. Call this if you no longer use the generator.
    //
    @Override
    public void dispose() {
        face.done();
        library.done();
    }

    //
    // Sets the maximum size that will be used when generating texture atlases for glyphs with <tt>generateData()</tt>. The
    // default is 1024. By specifying {@link #NO_MAXIMUM}, the texture atlas will scale as needed.
    // <p>
    // The power-of-two square texture size will be capped to the given <tt>texSize</tt>. It's recommended that a power-of-two
    // value be used here.
    // <p>
    // Multiple pages may be used to fit all the generated glyphs. You can query the resulting number of pages by calling
    // <tt>bitmapFont.getRegions().length</tt> or <tt>freeTypeBitmapFontData.getTextureRegions().length</tt>.
    // <p>
    // If PixmapPacker is specified when calling generateData, this parameter is ignored.
    //
    // @param texSize the maximum texture size for one page of glyphs
    //
    public static void setMaxTextureSize(int texSize) {
        maxTextureSize = texSize;
    }

    //
    // Returns the maximum texture size that will be used by generateData() when creating a texture atlas for the glyphs.
    //
    // @return the power-of-two max texture size
    //
    public static int getMaxTextureSize() {
        return maxTextureSize;
    }

     //
     // {@link BitmapFontData} used for fonts generated via the {@link FreeTypeFontGenerator}. The texture storing the glyphs is
     // held in memory, thus the {@link #getImagePaths()} and {@link #getFontFile()} methods will return null.
     //
     // @author mzechner
     // @author Nathan Sweet
     //
    static public class FreeTypeBitmapFontData implements Disposable {

        public List<TextureRegion> regions;

        // Fields for incremental glyph generation.
        FreeTypeFontGenerator generator;
        FreeTypeFontParameter parameter;
        FTStroker stroker;
        TextureAtlas<Integer> packer;
        List<GlyphInfo> glyphs;
        private boolean dirty;
        int spaceXadvance;

        public GlyphInfo getGlyph(char ch) {
            generator.setPixelSizes(0, parameter.size);
            float baseline = (ascent + capHeight) / scaleY;
            GlyphInfo glyph = generator.createGlyph(ch, this, parameter, stroker, baseline, packer);
            if(glyph == null)
                return missingGlyph;

            setGlyphRegion(glyph, regions.get(glyph.page));
            setGlyph(ch, glyph);

            glyphs.add(glyph);
            dirty = true;

            final FTFace face = generator.face;
            if(parameter.kerning) {
                final int glyphIndex = face.getCharIndex(ch);
                for(GlyphInfo other: glyphs) {
                    final int otherIndex = face.getCharIndex(other.getCode());

                    final int kerningA = face.getKerning(glyphIndex, otherIndex, FTKerningMode.DEFAULT);
                    if(kerningA != 0)
                        glyph.kernings().put(other.getCode(), kerningA);

                    final int kerningB = face.getKerning(otherIndex, glyphIndex, FTKerningMode.DEFAULT);
                    if(kerningB != 0)
                        other.kernings().put((int) ch, kerningB);
                }
            }
            return null;
        }

        public void getGlyphs(GlyphRun run, CharSequence str, int start, int end, GlyphInfo lastGlyph) {
            if(packer != null) {
                packer.setPackToTexture(true); // All glyphs added after this are packed directly to the texture.
            }
            super.getGlyphs(run, str, start, end, lastGlyph);
            if(dirty) {
                dirty = false;
                packer.updateTextureRegions(regions, parameter.minFilter, parameter.magFilter, parameter.genMipMaps);
            }
        }

        @Override
        public void dispose() {
            if(stroker != null)
                stroker.done();
            if(packer != null)
                packer.dispose();
        }

    }


    public enum Hinting { // Font smoothing algorithm.
        None, // Disable hinting. Generated glyphs will look blurry.
        Slight, // Light hinting with fuzzy edges, but close to the original shape
        Medium, // Average hinting
        Full, // Strong hinting with crisp edges at the expense of shape fidelity
        AutoSlight, // Light hinting with fuzzy edges, but close to the original shape. Uses the FreeType auto-hinter.
        AutoMedium, // Average hinting. Uses the FreeType auto-hinter.
        AutoFull, // Strong hinting with crisp edges at the expense of shape fidelity. Uses the FreeType auto-hinter.
    }

    public static class FreeTypeFontParameter {
        public int size = 16;
        public boolean mono; // If true, font smoothing is disabled.
        public Hinting hinting = Hinting.AutoMedium; // Strength of hinting
        public AbstractColor color = Color.WHITE; // Foreground color (required for non-black borders)
        public float gamma = 1.8f; // Glyph gamma. Values > 1 reduce antialiasing.
        public int renderCount = 2; // Number of times to render the glyph. Useful with a shadow or border, so it doesn't show through the glyph.
        public float borderWidth = 0; // Border width in pixels, 0 to disable
        public AbstractColor borderColor = Color.BLACK; // Border color; only used if borderWidth > 0
        public boolean borderStraight = false; // true for straight (mitered), false for rounded borders
        public float borderGamma = 1.8f; // Values < 1 increase the border size.
        public int shadowOffsetX = 0; // Offset of text shadow on X axis in pixels, 0 to disable
        public int shadowOffsetY = 0; // Offset of text shadow on Y axis in pixels, 0 to disable
        public Color shadowColor = new Color(0, 0, 0, 0.75f); // Shadow color; only used if shadowOffset > 0. If alpha component is 0, no shadow is drawn but characters are still offset by shadowOffset.
        public int spaceX, spaceY; // Pixels to add to glyph spacing when text is rendered. Can be negative.
        public int padTop, padLeft, padBottom, padRight; // Pixels to add to the glyph in the texture. Cannot be negative.
        public String characters = DEFAULT_CHARS; // The characters the font should contain. If '\0' is not included then missingGlyph is not set.
        public boolean kerning = true; // Whether the font should include kerning
        public TextureAtlas<Integer> packer = null; // The optional PixmapPacker to use for packing multiple fonts into a single texture.
        public boolean genMipMaps = false;
        public GlFilter minFilter = GlFilter.NEAREST;
        public GlFilter magFilter = GlFilter.NEAREST;
        // When true, glyphs are rendered on the fly to the font's glyph page textures as they are needed. The
        // FreeTypeFontGenerator must not be disposed until the font is no longer needed. The FreeTypeBitmapFontData must be
        // disposed (separately from the generator) when the font is no longer needed. The FreeTypeFontParameter should not be
        // modified after creating a font. If a PixmapPacker is not specified, the font glyph page textures will use
        // {@link FreeTypeFontGenerator#getMaxTextureSize()}.
        public boolean incremental;
    }
*/
}