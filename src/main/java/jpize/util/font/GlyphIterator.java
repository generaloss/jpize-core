package jpize.util.font;

import jpize.util.math.vector.Vec2f;

import java.util.*;

public class GlyphIterator implements Iterator<GlyphSprite> {

    // links
    private final FontData fontData;
    private final FontRenderOptions options;
    // iteration data
    private int size;
    private final List<GlyphLine> lines;
    // state
    private final Vec2f cursor;
    private final Vec2f advance;
    private Map<Integer, Integer> kernelings;
    private int charIndex;
    private GlyphInfo glyph;
    private int lineCharIndex;
    private int lineIndex;
    private GlyphLine line;

    public GlyphIterator(FontData fontData, FontRenderOptions options, CharSequence text) {
        this.fontData = fontData;
        this.options = options;
        this.cursor = new Vec2f();
        this.advance = new Vec2f();
        // initialize lines
        this.lines = new ArrayList<>();
        this.makeLines(text);
        this.line = lines.get(0);
        if(!line.isEmpty())
            this.glyph = fontData.glyphs().get((int) line.charAt(0));
    }

    public FontData fontData() {
        return fontData;
    }

    public FontRenderOptions options() {
        return options;
    }

    public int size() {
        return size;
    }

    public List<GlyphLine> lines() {
        return lines;
    }


    public Vec2f cursor() {
        return cursor;
    }

    public Vec2f advance() {
        return advance;
    }


    public int charIndex() {
        return charIndex;
    }

    public GlyphInfo glyph() {
        return glyph;
    }

    public int lineCharIndex() {
        return lineCharIndex;
    }

    public int lineIndex() {
        return lineIndex;
    }

    public GlyphLine line() {
        return line;
    }


    private GlyphLine addLine(StringBuilder lineBuilder, boolean newLine) {
        // calculate advance y
        final float lineGap = (newLine ? options.getNewLineGapScaled() : options.getLineGapScaled());
        final float lineHeight = (fontData.getHeight() * options.advanceFactor().y);
        final float lineAdvance = (lineGap + lineHeight) * options.scale().y;

        // add line
        final GlyphLine line = new GlyphLine(lineBuilder.toString(), newLine, lineAdvance);
        lines.add(line);

        // reset 'line' state
        lineBuilder.delete(0, lineBuilder.length());
        kernelings = null;
        cursor.x = 0;

        return line;
    }

    private void makeLines(CharSequence text) {
        // setup
        final StringBuilder lineBuilder = new StringBuilder();
        final float breakLineMaxWidth = (options.getLineBreakingWidth() / options.scale().x);
        boolean lastLineIsNew = true;

        // iterate chars
        for(int i = 0; i < text.length(); i++){
            final int code = text.charAt(i);
            // new line
            if(code == '\n') {
                this.addLine(lineBuilder, true);
                continue;
            }

            // build line
            lineBuilder.append((char) code);
            size++;

            // break line
            if(breakLineMaxWidth >= 0F){
                lastLineIsNew = false;

                final GlyphInfo glyph = fontData.glyphs().get(code);
                if(glyph == null)
                    continue;

                // kerning
                final int kerning = (kernelings == null ? 0 : kernelings.getOrDefault(code, 0));
                kernelings = fontData.kernings().get(code);

                // calculate advance x
                advance.x = ((glyph.getAdvanceX() + kerning) * options.advanceFactor().x);

                // check current line width
                if(cursor.x != 0 && (cursor.x + advance.x) >= breakLineMaxWidth) {
                    final GlyphLine line = this.addLine(lineBuilder, false);
                    cursor.y += line.getAdvanceY();
                }
            }
        }
        this.addLine(lineBuilder, lastLineIsNew);

        // reset state
        advance.x = 0;
        cursor.y = 0;
    }


    public boolean hasNextLine() {
        return (lineIndex < lines.size());
    }

    @Override
    public boolean hasNext() {
        return (glyph != null);
    }

    @Override
    public GlyphSprite next() {
        float spriteY = cursor.y;
        if(options.isInvLineWrap())
            spriteY = -spriteY - (fontData.getHeight() + options.getNewLineGap()) * options.scale().y;

        final GlyphSprite sprite = new GlyphSprite(fontData, glyph, cursor.x, spriteY, options.scale(), lineIndex);
        // kerning
        final int code = glyph.getCode();
        final int kerning = (kernelings == null ? 0 : kernelings.getOrDefault(code, 0));
        kernelings = fontData.kernings().get(code);
        // advance x
        advance.x = (glyph.getAdvanceX() + kerning) * options.advanceFactor().x;
        cursor.x += advance.x;
        advance.y = 0;
        // next glyph
        this.nextGlyph();
        return sprite;
    }

    private void nextGlyph() {
        if(line == null)
            return;

        do{
            // increase next char index
            charIndex++;
            lineCharIndex++;
            // next line
            if(lineCharIndex == line.size()) {
                this.nextLine();

                if(line == null) {
                    glyph = null;
                    return;
                }
            }

            // next glyph
            final int code = line.charAt(lineCharIndex);
            glyph = fontData.glyphs().get(code);
        }while(glyph == null);
    }

    private void nextLine() {
        do{
            // next line index
            lineIndex++;
            if(lineIndex == lines.size()){
                line = null;
                return;
            }
            // next line
            line = lines.get(lineIndex);
            lineCharIndex = 0;
            // advance
            cursor.x = 0F;
            advance.y = line.getAdvanceY();
            cursor.y += advance.y;
        }while(line.isEmpty());
    }

    public void skipLine() {
        charIndex += (line.size() - lineCharIndex);
        this.nextLine();
    }

    @Override
    public void remove() {
        cursor.sub(advance);
    }

}
