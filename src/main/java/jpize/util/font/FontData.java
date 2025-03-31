package jpize.util.font;

import jpize.opengl.texture.Texture2D;
import jpize.util.Disposable;

import java.util.HashMap;
import java.util.Map;

public class FontData implements Disposable {

    private final Map<Integer, Texture2D> pages;  // pageID => texture
    private final Map<Integer, GlyphInfo> glyphs; // code   => glyph

    private float height;
    private float ascent;
    private float descent;

    public FontData() {
        this.pages = new HashMap<>();
        this.glyphs = new HashMap<>();
    }

    public Map<Integer, Texture2D> pages() {
        return pages;
    }

    public Map<Integer, GlyphInfo> glyphs() {
        return glyphs;
    }


    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getAscent() {
        return ascent;
    }

    public void setAscent(float ascent) {
        this.ascent = ascent;
    }

    public float getDescent() {
        return descent;
    }

    public void setDescent(float descent) {
        this.descent = descent;
    }


    @Override
    public void dispose() {
        for(Texture2D page: pages.values())
            page.dispose();
    }

}
