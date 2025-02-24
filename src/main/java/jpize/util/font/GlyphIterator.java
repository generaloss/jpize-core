package jpize.util.font;

import java.util.Iterator;

public class GlyphIterator implements Iterator<GlyphSprite> {

    private final Font font;
    private final FontRenderOptions options;
    private final CharSequence text;

    private int position;
    private int lineY;
    private float cursorX;
    private float cursorY;
    private float prevIncreaseX;
    private float prevIncreaseY;

    public GlyphIterator(Font font, CharSequence text) {
        this.font = font;
        this.options = font.getRenderOptions();
        this.text = this.removeUnknownGlyphs(text);
        this.cursorY = -(options.isInvLineWrap() ? font.getLineAdvance() : 0);
    }

    @Override
    public void remove() {
        cursorX -= prevIncreaseX;
        cursorY -= prevIncreaseY;
    }


    @Override
    public boolean hasNext() {
        return (position < text.length());
    }

    @Override
    public GlyphSprite next() {
        prevIncreaseX = 0F;
        prevIncreaseY = 0F;

        final Glyph glyph = this.findNextGlyph();
        if(glyph == null) {
            // create last glyph '\n' to correct bounds
            return new GlyphSprite(cursorY, font.getLineAdvance(), options.scale(), lineY);
        }

        prevIncreaseX = (glyph.advanceX * options.advanceFactor().x);

        // wrap line (max width)
        final double maxWidth = (options.getWrapWidth() / options.scale().x);
        if(maxWidth >= 0F && cursorX + prevIncreaseX > maxWidth){
            cursorX = 0F;
            prevIncreaseY = (options.getLineWrapSign() * font.getLineAdvance() * options.advanceFactor().y);
            cursorY += prevIncreaseY;
            lineY++;
        }

        // create sprite
        final GlyphSprite sprite = new GlyphSprite(font, glyph, cursorX, cursorY, options.scale(), lineY);

        // advance cursor
        cursorX += prevIncreaseX;
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
            cursorX = 0F;
            cursorY += (options.getLineWrapSign() * font.getNewLineAdvance() * options.advanceFactor().y);
            lineY++;

            position++;
        }

        return font.glyphs().get(code);
    }

    private CharSequence removeUnknownGlyphs(CharSequence str) {
        if(str == null || str.isEmpty())
            return "";

        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            final int code = Character.codePointAt(str, i);
            if(code == '\n' || font.glyphs().containsKey(code))
                builder.append((char) code);
        }
        return builder.toString();
    }


    public void skipLine() {
        lineY++;
        cursorX = 0F;

        // Skip characters
        while(this.hasNext()){
            if(text.charAt(position) == '\n')
                break;

            position++;
        }
    }


    public Font font() {
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

    public void advance(float x, float y) {
        cursorX += x;
        cursorY += y;
    }

    public int getLineY() {
        return lineY;
    }

}
