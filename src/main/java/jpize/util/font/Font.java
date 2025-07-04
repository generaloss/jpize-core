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


    public Font loadFNT(Resource resource, boolean linearFilter) {
        return FNTFontLoader.load(this, resource, linearFilter);
    }

    public Font loadFNT(String internalPath, boolean linearFilter) {
        return FNTFontLoader.load(this, internalPath, linearFilter);
    }


    public Font loadFT(Resource resource, FontLoadOptions options) {
        return FreeTypeFontLoader.load(this, resource, options);
    }

    public Font loadFT(String internalPath, FontLoadOptions options) {
        return FreeTypeFontLoader.load(this, internalPath, options);
    }

    public Font loadFT(Resource resource, int size) {
        return FreeTypeFontLoader.load(this, resource, size);
    }

    public Font loadFT(String internalPath, int size) {
        return FreeTypeFontLoader.load(this, internalPath, size);
    }


    public Font loadDefault(FontLoadOptions options) {
        return FreeTypeFontLoader.loadDefault(this, options);
    }

    public Font loadDefault(int size) {
        return FreeTypeFontLoader.loadDefault(this, size);
    }

    public Font loadDefault() {
        return FreeTypeFontLoader.loadDefault(this);
    }


    public Font loadDefaultBold(FontLoadOptions options) {
        return FreeTypeFontLoader.loadDefaultBold(this, options);
    }

    public Font loadDefaultBold(int size) {
        return FreeTypeFontLoader.loadDefaultBold(this, size);
    }

    public Font loadDefaultBold() {
        return FreeTypeFontLoader.loadDefaultBold(this);
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
        final GlyphIterable iterable = this.iterable(text);
        for(GlyphSprite glyph: iterable);
        return iterable.iterator().bounds().mul(options.scale());
    }

    public float getTextWidth(String text) {
        final GlyphIterable iterable = this.iterable(text);
        for(GlyphSprite glyph: iterable);
        return iterable.iterator().bounds().x * options.scale().x;
    }

    public float getTextHeight(String text) {
        final GlyphIterable iterable = this.iterable(text);
        final GlyphIterator iterator = iterable.iterator();
        for(GlyphSprite glyph: iterable)
            iterator.nextNotEmptyLine();
        return (iterator.bounds().y * options.scale().y);
    }

    public float getTextMaxCursorX(String text) {
        final GlyphIterable iterable = this.iterable(text);
        final GlyphIterator iterator = iterable.iterator();
        float x = 0;
        for(GlyphSprite glyph: iterable)
            x = Math.max(x, iterator.cursor().x);
        return (x * options.scale().x);
    }


    @Override
    public void dispose() {
        super.dispose();
    }

}