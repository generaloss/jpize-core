package jpize.util.font.glyph;

import jpize.util.font.Font;
import jpize.util.font.FontRenderOptions;
import jpize.util.math.vector.Vec2f;

import java.util.Iterator;

public class GlyphIterator implements Iterator<GlyphSprite> {

    private final Font font;
    private final FontRenderOptions renderOptions;
    private final CharSequence text;

    private int position;
    private int lineY;
    private float cursorX;
    private float cursorY;
    private float prevIncX;
    private float prevIncY;

    public GlyphIterator(Font font, FontRenderOptions renderOptions, CharSequence text) {
        this.font = font;
        this.renderOptions = renderOptions;
        this.text = this.removeUnknownGlyphs(text);
        this.cursorY = -(renderOptions.isInvLineWrap() ? font.getLineAdvance() : 0);
    }

    @Override
    public void remove() {
        cursorX -= prevIncX;
        cursorY -= prevIncY;
    }


    @Override
    public boolean hasNext() {
        return position < text.length();
    }

    @Override
    public GlyphSprite next() {
        prevIncX = 0;
        prevIncY = 0;

        final Glyph glyph = this.findNextGlyph();

        // Scale
        final Vec2f scale = renderOptions.scale();

        // Last '\n' for correct bounds
        if(glyph == null)
            return new GlyphSprite(cursorY, font.getLineAdvance(), scale, lineY);

        final float cursorXAdvance = glyph.advanceX * renderOptions.advanceFactor().x;

        // Wrap line (max width)
        final double maxWidth = renderOptions.getWrapWidth() / scale.x;
        if(maxWidth >= 0 && cursorX + cursorXAdvance > maxWidth){
            cursorX = 0;
            prevIncY = renderOptions.getLineWrapSign() * font.getLineAdvance() * renderOptions.advanceFactor().y;
            cursorY += prevIncY;
            lineY++;
        }

        // Create sprite
        final GlyphSprite sprite = new GlyphSprite(font, glyph, cursorX, cursorY, scale, lineY);

        // Advance cursor :|
        prevIncX = cursorXAdvance;
        cursorX += prevIncX;
        position++;

        return sprite;
    }

    private Glyph findNextGlyph() {
        int code = -1;

        while(hasNext()){
            code = text.charAt(position);
            if(code != '\n')
                break;

            // Wrap line
            cursorX = 0;
            cursorY += renderOptions.getLineWrapSign() * font.getLineAdvance() * renderOptions.advanceFactor().y;
            lineY++;

            position++;
        }

        return font.getGlyphs().get(code);
    }

    private CharSequence removeUnknownGlyphs(CharSequence str) {
        if(str == null || str.isEmpty())
            return "";

        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            final int code = Character.codePointAt(str, i);
            if(code == '\n' || font.getGlyphs().containsKey(code))
                builder.append((char) code);
        }
        return builder.toString();
    }


    public void skipLine() {
        lineY++;
        cursorX = 0;

        // Skip characters
        while(hasNext()){
            if(text.charAt(position) == '\n')
                break;
            position++;
        }
    }


    public Font getFont() {
        return font;
    }

    public CharSequence getText() {
        return text;
    }

    public int getPosition() {
        return position;
    }

    public float getCursorX() {
        return cursorX;
    }

    public float getCursorY() {
        return cursorY;
    }

    public int getLineY() {
        return lineY;
    }

}
