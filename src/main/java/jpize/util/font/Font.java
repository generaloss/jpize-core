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
    private boolean italic;
    private FontRenderOptions renderOptions;

    protected Font(float height, float ascent, float descent, Map<Integer, Texture2D> pages, Map<Integer, Glyph> glyphs, boolean italic) {
        super(height, ascent, descent);
        this.pages = pages;
        this.glyphs = glyphs;
        this.italic = italic;
        this.renderOptions = new FontRenderOptions();
    }

    public Map<Integer, Texture2D> getPages() {
        return pages;
    }

    public Map<Integer, Glyph> getGlyphs() {
        return glyphs;
    }

    public boolean isItalic() {
        return italic;
    }

    public Font setItalic(boolean italic) {
        this.italic = italic;
        return this;
    }

    public FontRenderOptions getRenderOptions() {
        return renderOptions;
    }

    public Font setRenderOptions(FontRenderOptions renderOptions) {
        this.renderOptions = renderOptions;
        return this;
    }


    public float getHeightScaled() {
        return (super.height * renderOptions.scale().y);
    }

    public float getAscentScaled() {
        return (super.ascent * renderOptions.scale().y);
    }

    public float getDescentScaled() {
        return (super.descent * renderOptions.scale().y);
    }

    public float getLineAdvance() {
        return (super.height + renderOptions.getLineGap());
    }

    public float getLineAdvanceScaled() {
        return (this.getLineAdvance() * renderOptions.scale().y);
    }


    public void drawText(TextureBatch batch, String text, float x, float y) {
        TextRenderer.render(this, renderOptions, batch, text, x, y);
    }

    public void drawText(String text, float x, float y) {
        TextRenderer.render(this, renderOptions, text, x, y);
    }

    public Iterable<GlyphSprite> iterable(String text) {
        final GlyphIterator iterator = new GlyphIterator(this, renderOptions, text);
        return () -> iterator;
    }


    public Vec2f getTextBounds(String text) {
        float width = 0;
        float height = 0;

        for(GlyphSprite glyph: this.iterable(text)){
            final float glyphMaxX = glyph.getX() + ((char) glyph.getCode() == ' ' ? glyph.getAdvanceX() : glyph.getWidth());
            final float glyphMaxY = glyph.getOffsetY() + glyph.getHeight() + glyph.getLineY() * this.getLineAdvanceScaled();

            width = Math.max(width, glyphMaxX);
            height = Math.max(height, glyphMaxY);
        }

        return new Vec2f(width, height);
    }

    public Vec2f getTextBoundsWithAdvance(String text) {
        float width = 0;
        float height = 0;

        final Iterable<GlyphSprite> iterable = this.iterable(text);
        for(GlyphSprite glyph: iterable){
            final float glyphMaxX = glyph.getX() + ((char) glyph.getCode() == ' ' ? glyph.getAdvanceX() : glyph.getWidth());
            final float glyphMaxY = (glyph.getLineY() + 1) * this.getLineAdvanceScaled();

            width = Math.max(width, glyphMaxX);
            height = Math.max(height, glyphMaxY);
        }

        final float cursorX = ((GlyphIterator) iterable.iterator()).getCursorX() * renderOptions.scale().x;
        return new Vec2f(Math.max(cursorX, width), height);
    }

    public float getTextWidth(String text) {
        float width = 0;
        for(GlyphSprite glyph: this.iterable(text)){
            final float glyphMaxX = glyph.getX() + ((char) glyph.getCode() == ' ' ? glyph.getAdvanceX() : glyph.getWidth());
            width = Math.max(width, glyphMaxX);
        }
        return width;
    }

    public float getTextWidthWithAdvance(String text) {
        float width = 0;
        final Iterable<GlyphSprite> iterable = this.iterable(text);

        for(GlyphSprite glyph: iterable){
            final float glyphMaxX = glyph.getX() + ((char) glyph.getCode() == ' ' ? glyph.getAdvanceX() : glyph.getWidth());
            width = Math.max(width, glyphMaxX);
        }

        final float cursorX = ((GlyphIterator) iterable.iterator()).getCursorX() * renderOptions.scale().x;
        return Math.max(cursorX, width);
    }

    public float getTextHeight(String text) {
        float height = 0;
        for(GlyphSprite glyph: this.iterable(text)){
            final float glyphMaxY = Math.abs(glyph.getY() + super.descent + glyph.getHeight()) - super.descent;
            height = Math.max(height, glyphMaxY);
        }
        return height;
    }


    @Override
    public void dispose() {
        for(Texture2D page: pages.values())
            page.dispose();
    }

}