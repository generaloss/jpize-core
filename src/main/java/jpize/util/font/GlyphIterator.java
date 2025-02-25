package jpize.util.font;

import jpize.util.array.StringList;
import jpize.util.math.vector.Vec2f;
import java.util.Iterator;

public class GlyphIterator implements Iterator<GlyphSprite> {

    // font
    private final Font font;
    private final FontRenderOptions options;
    // data
    private final StringList lines;
    private int size;
    // iterator state
    private String currentLine;
    private final Vec2f cursor;
    private final Vec2f prevIncrease;
    private int charIndex;
    private int lineCharIndex;
    private int lineIndex;

    public GlyphIterator(Font font, StringList lines) {
        this.font = font;
        this.options = font.getRenderOptions();
        this.lines = lines;
        this.cursor = new Vec2f();
        this.prevIncrease = new Vec2f();
        this.resetcursorY();
    }

    public GlyphIterator(Font font, CharSequence text) {
        this(font, new StringList(""));
        this.extractLines(text);
    }

    private void extractLines(CharSequence text) {
        final float lineBreakingWidth = (options.getLineBreakingWidth() / options.scale().x);
        Glyph glyph;

        while(charIndex < text.length()) {
            // add char to line
            final int code = text.charAt(charIndex++);
            if(code == '\n') {
                // add line
                lines.add("");
                lineIndex++;
                continue;
            }
            lines.elementAdd(lineIndex, (char) code);
            size++;

            // line breaking with textarea max width
            if(lineBreakingWidth >= 0F){
                // glyph
                glyph = font.glyphs().get(code);
                if(glyph == null)
                    continue;

                prevIncrease.x = (glyph.advanceX * options.advanceFactor().x);

                if((cursor.x + prevIncrease.x) >= lineBreakingWidth) {
                    lines.add("");
                    lineIndex++;
                    cursor.x = 0;

                    prevIncrease.y = (options.getLineWrapSign() * font.getLineAdvance() * options.advanceFactor().y);
                    cursor.y += prevIncrease.y;
                    continue;
                }

                cursor.x += prevIncrease.x;
            }
        }

        this.reset();
    }

    
    public Font font() {
        return font;
    }

    public StringList lines() {
        return lines;
    }

    public int size() {
        return size;
    }

    public String getCurrentLine() {
        return currentLine;
    }

    public Vec2f cursor() {
        return cursor;
    }

    public Vec2f prevIncrease() {
        return prevIncrease;
    }
    
    public int getCharIndex() {
        return charIndex;
    }

    public int getLineCharIndex() {
        return charIndex;
    }
    
    public int getLineIndex() {
        return lineIndex;
    }


    public void reset() {
        charIndex = 0;
        lineCharIndex = 0;
        lineIndex = 0;
        prevIncrease.zero();
        currentLine = lines.getFirst();
        cursor.x = 0;
        this.resetcursorY();
    }

    private void resetcursorY() {
        cursor.y = -(options.isInvLineWrap() ? font.getLineAdvance() : 0);
    }


    @Override
    public void remove() {
        cursor.sub(prevIncrease);
    }

    public boolean hasNextLine() {
        return (lineIndex < lines.size());
    }

    public void skipLine() {
        cursor.x = 0F;
        if(lineCharIndex != 0)
            cursor.y += (options.getLineWrapSign() * font.getNewLineAdvance() * options.advanceFactor().y);

        charIndex += (currentLine.length() - lineCharIndex);
        lineCharIndex = 0;
        lineIndex++;
        if(this.hasNextLine()){
            currentLine = lines.get(lineIndex);
        }else{
            currentLine = null;
        }
    }

    @Override
    public boolean hasNext() {
        return (charIndex < size);
    }

    @Override
    public GlyphSprite next() {
        prevIncrease.zero();

        final Glyph glyph = this.findNextGlyph();
        if(glyph == null) {
            // create last glyph '\n' to correct bounds
            return new GlyphSprite(cursor.y, font.getLineAdvance(), options.scale(), lineIndex);
        }

        prevIncrease.x = (glyph.advanceX * options.advanceFactor().x);

        // create sprite
        final GlyphSprite sprite = new GlyphSprite(font, glyph, cursor.x, cursor.y, options.scale(), lineIndex);

        // advance cursor
        cursor.x += prevIncrease.x;
        return sprite;
    }
    
    private Glyph findNextGlyph() {
        Glyph glyph = null;

        while(glyph == null && this.hasNext()){
            while(lineCharIndex >= currentLine.length()){
                // next line
                currentLine = lines.get(++lineIndex);
                lineCharIndex = 0;
                cursor.x = 0F;
                cursor.y += (options.getLineWrapSign() * font.getNewLineAdvance() * options.advanceFactor().y);
            }

            final int code = currentLine.charAt(lineCharIndex++);
            glyph = font.glyphs().get(code);
            charIndex++;
        }

        return glyph;
    }

}
