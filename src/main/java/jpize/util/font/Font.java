package jpize.util.font;

import jpize.gl.texture.Texture2D;
import jpize.util.font.glyph.*;
import jpize.util.mesh.TextureBatch;
import jpize.util.math.vector.Vec2f;
import jpize.util.Disposable;

import java.util.Map;

public class Font extends FontInfo implements Disposable {

    private final Map<Integer, Texture2D> pages;
    private final Map<Integer, Glyph> glyphs;
    private final FontOptions options;

    protected Font(float height, float ascent, float descent, Map<Integer, Texture2D> pages, Map<Integer, Glyph> glyphs) {
        super(height, ascent, descent);
        this.pages = pages;
        this.glyphs = glyphs;
        this.options = new FontOptions(this);
    }


    public Map<Integer, Texture2D> getPages() {
        return pages;
    }

    public Map<Integer, Glyph> getGlyphs() {
        return glyphs;
    }

    public FontOptions options() {
        return options;
    }


    public float getScale() {
        return options.scale;
    }

    public void setScale(float scale) {
        options.scale = scale;
    }


    public Vec2f getBounds(String text) {
        float width = 0;
        float height = 0;

        for(GlyphSprite glyph: this.iterable(text)){
            final float glyphMaxX = glyph.getX() + ((char) glyph.getCode() == ' ' ? glyph.getAdvanceX() : glyph.getWidth());
            final float glyphMaxY = glyph.getOffsetY() + glyph.getHeight() + glyph.getLineY() * options.getAdvanceScaled();

            width = Math.max(width, glyphMaxX);
            height = Math.max(height, glyphMaxY);
        }

        return new Vec2f(width, height);
    }

    public Vec2f getMaxBounds(String text) {
        float width = 0;
        float height = 0;

        final Iterable<GlyphSprite> iterable = this.iterable(text);
        for(GlyphSprite glyph: iterable){
            final float glyphMaxX = glyph.getX() + ((char) glyph.getCode() == ' ' ? glyph.getAdvanceX() : glyph.getWidth());
            final float glyphMaxY = (glyph.getLineY() + 1) * options.getAdvanceScaled();

            width = Math.max(width, glyphMaxX);
            height = Math.max(height, glyphMaxY);
        }

        final GlyphIterator iterator = ((GlyphIterator) iterable.iterator());
        return new Vec2f(Math.max(iterator.getCursorX(), width), height);
    }

    public float getTextWidth(String text) {
        float width = 0;
        for(GlyphSprite glyph: this.iterable(text)){
            final float glyphMaxX = glyph.getX() + ((char) glyph.getCode() == ' ' ? glyph.getAdvanceX() : glyph.getWidth());
            width = Math.max(width, glyphMaxX);
        }
        return width;
    }

    public float getTextAdvance(String text) {
        float width = 0;
        final Iterable<GlyphSprite> iterable = this.iterable(text);

        for(GlyphSprite glyph: iterable){
            final float glyphMaxX = glyph.getX() + ((char) glyph.getCode() == ' ' ? glyph.getAdvanceX() : glyph.getWidth());
            width = Math.max(width, glyphMaxX);
        }

        final GlyphIterator iterator = ((GlyphIterator) iterable.iterator());
        return Math.max(iterator.getCursorX(), width);
    }

    public float getTextHeight(String text) {
        float height = 0;
        for(GlyphSprite glyph: this.iterable(text)){
            final float glyphMaxY = Math.abs(glyph.getY() + super.descent + glyph.getHeight()) - super.descent;
            height = Math.max(height, glyphMaxY);
        }
        return height;
    }


    public void drawText(TextureBatch batch, String text, float x, float y) {
        TextRenderer.render(this, batch, text, x, y);
    }

    public void drawText(String text, float x, float y) {
        TextRenderer.render(this, text, x, y);
    }

    public Iterable<GlyphSprite> iterable(String text) {
        final GlyphIterator iterator = new GlyphIterator(this, text);
        return () -> iterator;
    }


    @Override
    public void dispose() {
        for(Texture2D page: pages.values())
            page.dispose();
    }

}