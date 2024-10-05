package jpize.util.font.glyph;

import jpize.util.font.Font;
import java.util.Iterator;

public class GlyphIterator implements Iterator<GlyphSprite> {

    private final Font font;
    private final CharSequence text;
    private final int size;

    private int cursor;
    private int lineY;
    private float cursorX;
    private float cursorY;

    public GlyphIterator(Font font, CharSequence text) {
        this.font = font;
        this.text = this.textWithoutNullGlyphs(text);
        this.size = this.text.length();

        this.cursorY = -(font.options().invLineWrap ? font.options().getAdvance() : 0);
    }


    private float prevIncX;
    private float prevIncY;

    @Override
    public void remove() {
        cursorX -= prevIncX;
        cursorY -= prevIncY;
    }


    @Override
    public boolean hasNext() {
        return cursor < size;
    }

    @Override
    public GlyphSprite next() {
        prevIncX = 0;
        prevIncY = 0;

        final Glyph glyph = this.findNextGlyph();

        // Scale
        final float scale = font.options().scale;

        // Last '\n' for correct bounds
        if(glyph == null)
            return new GlyphSprite(cursorY, font.options().getAdvance(), scale, lineY);

        final float cursorXAdvance = glyph.advanceX * font.options().advanceFactor.x;

        // Wrap line (area width)
        final double maxWidth = font.options().wrapThreesholdWidth / scale;
        if(maxWidth >= 0 && cursorX + cursorXAdvance > maxWidth){
            cursorX = 0;
            prevIncY = font.options().getLineWrapSign() * font.options().getAdvance() * font.options().advanceFactor.y;
            cursorY += prevIncY;
            lineY++;
        }

        // Create sprite
        final GlyphSprite sprite = new GlyphSprite(font, glyph, cursorX, cursorY, scale, lineY);

        // Advance cursor :|
        prevIncX = cursorXAdvance;
        cursorX += prevIncX;
        cursor++;

        return sprite;
    }

    private Glyph findNextGlyph() {
        int code = -1;

        while(hasNext()){
            code = text.charAt(cursor);
            if(code != '\n')
                break;

            // Wrap line
            cursorX = 0;
            cursorY += font.options().getLineWrapSign() * font.options().getAdvance() * font.options().advanceFactor.y;
            lineY++;

            cursor++;
        }

        return font.getGlyphs().get(code);
    }

    private CharSequence textWithoutNullGlyphs(CharSequence text) {
        if(text == null || text.isEmpty())
            return "";

        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < text.length(); i++){
            final int code = Character.codePointAt(text, i);
            if(code == '\n' || font.getGlyphs().containsKey(code))
                builder.append((char) code);
        }
        return builder.toString();
    }

}
