package jpize.util.font;

import java.util.HashMap;
import java.util.Map;

public class GlyphInfoMap {

    private final Map<Long, GlyphInfo> map;

    public GlyphInfoMap() {
        this.map = new HashMap<>();
    }

    public int size() {
        return map.size();
    }


    public GlyphInfo get(long codepoint) {
        return map.get(codepoint);
    }

    public GlyphInfo get(int charcode, int variationSelector) {
        final long codepoint = GlyphInfo.getCodePoint(charcode, variationSelector);
        return this.get(codepoint);
    }

    public GlyphInfo get(int charcode) {
        return this.get(charcode, 0);
    }


    public GlyphInfo getOrDefault(long key, GlyphInfo defaultGlyphInfo) {
        return map.getOrDefault(key, defaultGlyphInfo);
    }

    public GlyphInfo getOrDefault(int charcode, int variationSelector, GlyphInfo defaultGlyphInfo) {
        final long codepoint = GlyphInfo.getCodePoint(charcode, variationSelector);
        return this.getOrDefault(codepoint, defaultGlyphInfo);
    }

    public GlyphInfo getOrDefault(int charcode, GlyphInfo defaultGlyphInfo) {
        return this.getOrDefault(charcode, 0, defaultGlyphInfo);
    }


    public GlyphInfo put(long codepoint, GlyphInfo glyphInfo) {
        return map.put(codepoint, glyphInfo);
    }

    public void put(int charcode, int variationSelector, GlyphInfo glyphInfo) {
        final long codepoint = GlyphInfo.getCodePoint(charcode, variationSelector);
        this.put(codepoint, glyphInfo);
    }

    public void put(int charcode, GlyphInfo glyphInfo) {
        this.put(charcode, 0, glyphInfo);
    }


    public void clear() {
        map.clear();
    }

}
