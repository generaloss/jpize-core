package jpize.util.font;

import jpize.util.region.Region;
import spatialmath.vector.Vec2f;

import java.util.HashMap;
import java.util.Map;

public class GlyphInfo {

    private final int charcode;
    private final int variationSelector;
    private final long codepoint;
    private final Vec2f offset;
    private final Vec2f size;
    private Region region;
    private float advanceX;
    private int pageID;
    private final Map<Long, Integer> kernings; // (codepoint_right) => advance

    public GlyphInfo(int charcode, int variationSelector) {
        this.charcode = charcode;
        this.variationSelector = variationSelector;
        this.codepoint = getCodePoint(charcode, variationSelector);
        this.offset = new Vec2f();
        this.size = new Vec2f();
        this.kernings = new HashMap<>();
    }

    public GlyphInfo(int charcode) {
        this(charcode, 0);
    }

    public int getCharCode() {
        return charcode;
    }

    public int getVariationSelector() {
        return variationSelector;
    }

    public long getCodePoint() {
        return codepoint;
    }


    public Vec2f offset() {
        return offset;
    }

    public GlyphInfo setOffset(float x, float y) {
        offset.set(x, y);
        return this;
    }


    public Vec2f size() {
        return size;
    }

    public GlyphInfo setSize(float width, float height) {
        size.set(width, height);
        return this;
    }


    public Region region() {
        return region;
    }

    public GlyphInfo setRegion(Region region) {
        this.region = region;
        return this;
    }


    public float getAdvanceX() {
        return advanceX;
    }

    public GlyphInfo setAdvanceX(float advanceX) {
        this.advanceX = advanceX;
        return this;
    }


    public int getPageID() {
        return pageID;
    }

    public GlyphInfo setPageID(int pageID) {
        this.pageID = pageID;
        return this;
    }


    public Map<Long, Integer> kernings() {
        return kernings;
    }

    public int getKerning(long codepointB) {
        return kernings.get(codepointB);
    }


    @Override
    public String toString() {
        return "GlyphInfo{charcode=" + charcode + ", variationSelector=" + variationSelector + "}";
    }


    public static long getCodePoint(int charcode, int variationSelector) {
        return (charcode | ((long) variationSelector << Integer.SIZE));
    }

}