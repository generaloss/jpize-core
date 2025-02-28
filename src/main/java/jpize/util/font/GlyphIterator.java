package jpize.util.font;

import jpize.util.array.IntList;
import jpize.util.math.vector.Vec2f;

import java.util.*;

public class GlyphIterator implements Iterator<GlyphSprite> {

    // links
    private final FontData fontData;
    private final FontRenderOptions options;
    // iteration data
    private int size;
    private final List<GlyphLine> lines;
    private final IntList kernings;
    // state
    private final Vec2f cursor;
    private float nextAdvanceX;
    private final Vec2f advanced;
    private int charIndex;
    private GlyphInfo glyph;
    private int lineCharIndex;
    private int lineIndex;
    private GlyphLine line;

    public GlyphIterator(FontData fontData, FontRenderOptions options, CharSequence text) {
        this.fontData = fontData;
        this.options = options;
        this.cursor = new Vec2f();
        this.advanced = new Vec2f();
        // init
        this.lines = new ArrayList<>();
        this.kernings = new IntList();
        this.makeLines(text);
        this.line = lines.get(0);
        this.charIndex = -1;
        this.lineCharIndex = -1;

        float spriteY = cursor.y;
        if(options.isInvLineWrap())
            cursor.y -= fontData.getHeight();
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

    public IntList kernings() {
        return kernings;
    }

    public int kerning() {
        return kernings.get(charIndex);
    }

    public int nextKerning() {
        final int index = (charIndex + 1);
        if(index < kernings.size())
            return kernings.get(index);
        return 0;
    }


    public Vec2f cursor() {
        return cursor;
    }

    public float nextAdvanceX() {
        return nextAdvanceX;
    }

    public float nextAdvanceY() {
        return (line == null ? 0F : line.getAdvanceY());
    }

    public Vec2f advanced() {
        return advanced;
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
        final float lineGap = (newLine ? options.getNewLineGap() : options.getLineGap());
        final float lineHeight = (fontData.getHeight() * options.advanceFactor().y);
        final float lineAdvance = (lineGap + lineHeight) * options.getLineWrapSign();

        // add line
        final GlyphLine line = new GlyphLine(lineBuilder.toString(), newLine, lineAdvance);
        lines.add(line);

        // reset 'line' state
        lineBuilder.delete(0, lineBuilder.length());
        cursor.x = 0;

        return line;
    }

    private void makeLines(CharSequence text) {
        // setup
        final StringBuilder lineBuilder = new StringBuilder();
        final float breakLineMaxWidth = (options.getLineBreakingWidth() / options.scale().x);
        boolean lastLineIsNew = true;
        Map<Integer, Integer> kerningEntry = null;

        // iterate chars
        for(int i = 0; i < text.length(); i++){
            lastLineIsNew = true;
            final int code = text.charAt(i);
            // new line
            if(code == '\n') {
                this.addLine(lineBuilder, true);
                kerningEntry = null;
                continue;
            }

            // build kerning
            final int kerning = (kerningEntry != null ? kerningEntry.getOrDefault(code, 0) : 0);
            kerningEntry = fontData.kernings().get(code);
            kernings.add(kerning);

            // break line
            if(breakLineMaxWidth >= 0F){
                lastLineIsNew = false;

                glyph = fontData.glyphs().get(code);
                if(glyph == null)
                    continue;

                // advance x
                advanced.x = ((glyph.getAdvanceX() + kerning) * options.advanceFactor().x);

                // check current line width
                if(cursor.x != 0 && (cursor.x + advanced.x) >= breakLineMaxWidth) {
                    kerningEntry = null;
                    final GlyphLine line = this.addLine(lineBuilder, false);
                    cursor.y += line.getAdvanceY();
                }

                cursor.x += advanced.x;
            }

            // build line
            lineBuilder.append((char) code);
            size++;
        }
        this.addLine(lineBuilder, lastLineIsNew);

        // reset state
        advanced.x = 0;
        cursor.y = 0;
    }


    public boolean hasNextLine() {
        return (lineIndex + 1 < lines.size());
    }

    @Override
    public boolean hasNext() {
        System.out.println((charIndex + 1 + " < " + size));
        return (charIndex + 1 < size);
    }

    @Override
    public GlyphSprite next() {
        this.nextGlyph();
        cursor.add(advanced);
        return new GlyphSprite(fontData, glyph, cursor.x, cursor.y, options.scale(), lineIndex);
    }

    private void nextGlyph() {
        advanced.zero();
        do{
            // increase next char index
            charIndex++;
            lineCharIndex++;
            // next line
            if(lineCharIndex >= line.size()) {
                this.nextLine();
                if(line == null)
                    return;
            }
            // next glyph
            final int code = line.charAt(lineCharIndex);
            glyph = fontData.glyphs().get(code);
        }while(glyph == null);

        // next advance X
        advanced.x += nextAdvanceX;
        nextAdvanceX = (glyph.getAdvanceX() + this.nextKerning()) * options.advanceFactor().x;
    }

    public void nextLine() {
        do{
            // next line index
            lineIndex++;
            // next char indices
            charIndex += (line.size() - lineCharIndex);
            lineCharIndex = 0;
            // advance
            advanced.y += line.getAdvanceY();
            nextAdvanceX = 0F;
            cursor.x = 0F;
            // check bounds
            if(lineIndex >= lines.size()){
                line = null;
                glyph = null;
                return;
            }
            // next line
            line = lines.get(lineIndex);
        }while(line.isEmpty());
    }

    public void skipLine() {
        // next line index
        lineIndex++;
        // next char indices
        charIndex += (line.size() - lineCharIndex);
        lineCharIndex = 0;
        // advance
        advanced.y += line.getAdvanceY();
        nextAdvanceX = 0F;
        cursor.x = 0F;
        // check bounds
        if(lineIndex >= lines.size()){
            line = null;
            glyph = null;
            return;
        }
        // next line
        line = lines.get(lineIndex);
    }

    @Override
    public void remove() {
        cursor.sub(advanced);
    }

}
