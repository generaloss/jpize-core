package jpize.util.font.glyph;

import jpize.gl.texture.Texture2D;
import jpize.util.Disposable;

import java.util.HashMap;
import java.util.Map;

public class GlyphPages implements Disposable {

    private final Map<Integer, Texture2D> pages;

    public GlyphPages() {
        this.pages = new HashMap<>();
    }

    public void add(int pageID, Texture2D texture) {
        pages.put(pageID, texture);
    }

    public Texture2D get(int pageID) {
        return pages.get(pageID);
    }

    public boolean has(int pageID) {
        return pages.containsKey(pageID);
    }

    @Override
    public void dispose() {
        for(Texture2D page: pages.values())
            page.dispose();
    }

}
