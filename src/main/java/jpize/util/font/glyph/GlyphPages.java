package jpize.util.font.glyph;

import jpize.gl.texture.GlTexture2D;
import jpize.util.Disposable;

import java.util.HashMap;
import java.util.Map;

public class GlyphPages implements Disposable {

    private final Map<Integer, GlTexture2D> pages;

    public GlyphPages() {
        this.pages = new HashMap<>();
    }

    public void add(int pageID, GlTexture2D texture) {
        pages.put(pageID, texture);
    }

    public GlTexture2D get(int pageID) {
        return pages.get(pageID);
    }

    public boolean has(int pageID) {
        return pages.containsKey(pageID);
    }

    @Override
    public void dispose() {
        for(GlTexture2D page: pages.values())
            page.dispose();
    }

}
