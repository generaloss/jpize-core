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
    private final Vec2f offset;
    private final Vec2f cursor;
    private final Vec2f position;
    private final Vec2f bounds;
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
        this.offset = new Vec2f();
        this.cursor = new Vec2f();
        this.position = new Vec2f();
        this.bounds = new Vec2f();
        this.advanced = new Vec2f();
        // init
        this.lines = new ArrayList<>();
        this.kernings = new IntList();
        this.makeLines(text);
        this.line = lines.get(0);
        this.charIndex = -1;
        this.lineCharIndex = -1;
        if(options.isInvLineWrap())
            offset.y = -fontData.getHeight();
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


    public Vec2f offset() {
        return offset;
    }

    public Vec2f cursor() {
        return cursor;
    }

    public float nextCursorX() {
        if(this.hasNextLine() && lineCharIndex + 1 >= line.size())
            return 0F;
        return (cursor.x + nextAdvanceX);
    }

    public float nextCursorY() {
        return (cursor.y + this.nextAdvanceY());
    }


    public Vec2f position() {
        return position;
    }

    public Vec2f bounds() {
        return bounds;
    }


    public float nextAdvanceX() {
        return nextAdvanceX;
    }

    public float nextAdvanceY() {
        if(this.hasNextInLine() || !this.hasNextLine())
            return 0F;
        return line.getAdvanceY();
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

    public int code() {
        if(glyph == null)
            return -1;
        return glyph.getCode();
    }

    public char character() {
        return (char) this.code();
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


    private void addLine(StringBuilder lineBuilder, boolean newLine) {
        // calculate advance y
        final float lineGap = (newLine ? options.getNewLineGap() : options.getLineGap());
        final float lineHeight = (fontData.getHeight() * options.advanceFactor().y);
        final float lineAdvance = (lineGap + lineHeight);

        // add line
        lines.add(new GlyphLine(lineBuilder.toString(), newLine, lineAdvance));

        // reset 'line' state
        lineBuilder.delete(0, lineBuilder.length());
        cursor.x = 0;
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
                    this.addLine(lineBuilder, false);
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
    }


    public boolean hasNextLine() {
        return (lineIndex + 1 < lines.size());
    }

    public boolean hasNextInLine() {
        if(line == null)
            return false;
        return (lineCharIndex + 1 < line.size());
    }

    @Override
    public boolean hasNext() {
        return (line != null && charIndex + 1 < size);
    }

    @Override
    public GlyphSprite next() {
        // advance cursor
        advanced.zero();
        this.nextGlyph();
        cursor.add(advanced);
        // position
        position.set(cursor);
        position.y *= options.getLineWrapSign();
        position.add(offset);
        // bounds
        bounds.setMaxComps(
            cursor.x + nextAdvanceX,
            cursor.y + line.getAdvanceY()
        );
        // sprite
        if(glyph == null)
            return new GlyphSprite(fontData, position, options.scale());
        return new GlyphSprite(fontData, glyph, position, options.scale());
    }

    private void nextGlyph() {
        // increase next char index
        charIndex++;
        lineCharIndex++;
        // next line
        if(lineCharIndex >= line.size()) {
            this.nextNotEmptyLine();
            if(line == null)
                return;
        }
        // next glyph
        final int code = line.charAt(lineCharIndex);
        glyph = fontData.glyphs().get(code);
        // next advance X
        advanced.x = nextAdvanceX;
        nextAdvanceX = ((glyph != null) ?
            (glyph.getAdvanceX() + this.nextKerning()) : // normal glyph
            (fontData.getHeight() * 0.5F) // undefined glyph
        ) * options.advanceFactor().x;
    }

    public void nextNotEmptyLine() {
        do{
            this.nextLine();
        }while(line != null && line.isEmpty());
    }

    private void nextLine() {
        lineIndex++;
        // next char indices
        charIndex += (line.size() - lineCharIndex);
        lineCharIndex = 0;
        // advance
        nextAdvanceX = 0F;
        cursor.x = 0F;
        advanced.y += line.getAdvanceY();
        // next line
        line = null;
        if(lineIndex < lines.size())
            line = lines.get(lineIndex);
    }

    public void skipLine() { // skips only non-empty lines
        this.nextLine();
        charIndex -= 1;
        lineCharIndex -= 1;
    }

    public void hideLine() {
        this.skipLine();
        if(line != null)
            cursor.y += line.getAdvanceY();
    }


    @Override
    public void remove() {
        cursor.sub(advanced);
    }

}
