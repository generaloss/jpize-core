package jpize.util.font;

import jpize.util.region.Region;
import jpize.util.math.vector.Vec2f;

import java.util.HashMap;
import java.util.Map;

public class GlyphInfo {

    private final int code;
    private final Vec2f offset;
    private final Vec2f size;
    private Region region;
    private float advanceX;
    private int pageID;
    private final Map<Integer, Integer> kernings; // (code_next) => advance

    public GlyphInfo(int code) { //, float offsetX, float offsetY, float width, float height, Region region, float advanceX, int pageID
        this.code = code;
        this.offset = new Vec2f();
        this.size = new Vec2f();
        this.kernings = new HashMap<>();
    }

    public int getCode() {
        return code;
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


    public Map<Integer, Integer> kernings() {
        return kernings;
    }

    public int getKerning(int charCodeB) {
        return kernings.get(charCodeB);
    }


    @Override
    public String toString() {
        return String.valueOf((char) code);
    }

}