package jpize.util.font;

import jpize.util.camera.Camera3D;
import jpize.util.mesh.TextureBatch;
import jpize.util.math.vector.Vec2f;
import jpize.util.res.Resource;

public class Font extends FontData {

    private FontRenderOptions options;

    private boolean italic;

    public Font() {
        this.options = new FontRenderOptions();
    }

    public FontRenderOptions getOptions() {
        return options;
    }

    public Font setOptions(FontRenderOptions options) {
        this.options = options;
        return this;
    }


    public boolean isItalic() {
        return italic;
    }

    public Font setItalic(boolean italic) {
        this.italic = italic;
        return this;
    }


    public float getHeightScaled() {
        return (super.getHeight() * options.scale().y);
    }

    public float getAscentScaled() {
        return (super.getAscent() * options.scale().y);
    }

    public float getDescentScaled() {
        return (super.getDescent() * options.scale().y);
    }

    public float getLineAdvance() {
        return (super.getHeight() + options.getLineGap());
    }

    public float getNewLineAdvance() {
        return (super.getHeight() + options.getNewLineGap());
    }

    public float getLineAdvanceScaled() {
        return (this.getLineAdvance() * options.scale().y);
    }

    public float getNewLineAdvanceScaled() {
        return (this.getNewLineAdvance() * options.scale().y);
    }


    public Font loadDefault(int size, boolean linearFilter, Charset charset) {
        return FontLoader.loadDefault(this, size, linearFilter, charset);
    }

    public Font loadDefault(int size, boolean linearFilter) {
        return FontLoader.loadDefault(this, size, linearFilter);
    }

    public Font loadDefault(int size) {
        return FontLoader.loadDefault(this, size);
    }

    public Font loadDefault() {
        return FontLoader.loadDefault(this);
    }

    public Font loadDefaultBold(int size, boolean linearFilter, Charset charset) {
        return FontLoader.loadDefaultBold(this, size, linearFilter, charset);
    }

    public Font loadDefaultBold(int size, boolean linearFilter) {
        return FontLoader.loadDefaultBold(this, size, linearFilter);
    }

    public Font loadDefaultBold(int size) {
        return FontLoader.loadDefaultBold(this, size);
    }

    public Font loadDefaultBold() {
        return FontLoader.loadDefaultBold(this);
    }

    public Font loadFNT(Resource resource, boolean linearFilter) {
        return FontLoader.loadFNT(this, resource, linearFilter);
    }

    public Font loadFNT(String internalPath, boolean linearFilter) {
        return FontLoader.loadFNT(this, internalPath, linearFilter);
    }

    public Font loadTTF(Resource resource, int size, boolean linearFilter, Charset charset) {
        return FontLoader.loadTTF(this, resource, size, linearFilter, charset);
    }

    public Font loadTTF(String internalPath, int size, boolean linearFilter, Charset charset) {
        return FontLoader.loadTTF(this, internalPath, size, linearFilter, charset);
    }

    public Font loadTTF(Resource resource, int size, boolean linearFilter) {
        return FontLoader.loadTTF(this, resource, size, linearFilter);
    }

    public Font loadTTF(String internalPath, int size, boolean linearFilter) {
        return FontLoader.loadTTF(this, internalPath, size, linearFilter);
    }



    public GlyphIterator drawText(TextureBatch batch, String text, float x, float y) {
        return TextRenderer.render(this, batch, text, x, y);
    }

    public GlyphIterator drawText(String text, float x, float y) {
        return TextRenderer.render(this, text, x, y);
    }

    public GlyphIterator drawText(Camera3D camera, String text, float x, float y, float z) {
        return TextRenderer.render(this, camera, text, x, y, z);
    }


    public GlyphIterator iterator(String text, FontRenderOptions options) {
        return new GlyphIterator(this, options, text);
    }

    public GlyphIterator iterator(String text) {
        return this.iterator(text, options);
    }

    public GlyphIterable iterable(String text, FontRenderOptions options) {
        return new GlyphIterable(this.iterator(text, options));
    }

    public GlyphIterable iterable(String text) {
        return this.iterable(text, options);
    }


    public Vec2f getTextBounds(String text) {
        float width = 0;
        float height = 0;

        for(GlyphSprite glyph: this.iterable(text)){
            final float glyphMaxX = glyph.getX() + ((char) glyph.getCode() == ' ' ? glyph.getAdvanceX() : glyph.getWidth());
            final float glyphMaxY = glyph.getOffsetY() + glyph.getHeight() + glyph.getLineIndex() * this.getLineAdvanceScaled();

            width = Math.max(width, glyphMaxX);
            height = Math.max(height, glyphMaxY);
        }

        return new Vec2f(width, height);
    }

    public Vec2f getTextBoundsWithKerning(String text) {
        float width = 0;
        float height = 0;

        final GlyphIterable iterable = this.iterable(text);
        for(GlyphSprite glyph: iterable){
            final float glyphMaxX = glyph.getX() + ((char) glyph.getCode() == ' ' ? glyph.getAdvanceX() : glyph.getWidth());
            final float glyphMaxY = (glyph.getLineIndex() + 1) * this.getLineAdvanceScaled();

            width = Math.max(width, glyphMaxX);
            height = Math.max(height, glyphMaxY);
        }

        final float cursorX = iterable.iterator().cursor().x * options.scale().x;
        return new Vec2f(Math.max(cursorX, width), height);
    }

    public float getTextWidth(String text) {
        final GlyphIterable iterable = this.iterable(text);
        final GlyphIterator iterator = iterable.iterator();

        float width = 0;
        for(GlyphSprite glyph: iterable)
            width = Math.max(width, iterator.cursor().x + iterator.nextAdvanceX());

        return (width * options.scale().x);
    }

    public float getTextWidthCursor(String text) {
        final GlyphIterable iterable = this.iterable(text);
        final GlyphIterator iterator = iterable.iterator();

        float width = 0;
        for(GlyphSprite glyph: iterable)
            width = Math.max(width, iterator.cursor().x);

        return (width * options.scale().x);
    }

    public float getTextWidthWithKerning(String text) {
        final GlyphIterable iterable = this.iterable(text);
        final GlyphIterator iterator = iterable.iterator();

        float width = 0;
        for(GlyphSprite glyph: iterable)
            width = Math.max(width, iterator.cursor().x + iterator.advanced().x);

        return (width * options.scale().x);
    }

    public float getTextHeight(String text) {
        final GlyphIterable iterable = this.iterable(text);
        final GlyphIterator iterator = iterable.iterator();

        for(GlyphSprite glyph: iterable)
            iterator.nextLine();

        return ((iterator.cursor().y + this.getHeightScaled()) * options.scale().x);
    }


    @Override
    public void dispose() {
        super.dispose();
    }

}