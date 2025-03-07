package jpize.util.font;

import jpize.util.region.Region;
import jpize.util.math.vector.Vec2f;

public class GlyphInfo {

    private final int code;
    private final Vec2f offset;
    private final Vec2f size;
    private final Region region;
    private final float advanceX;
    private final int pageID;

    public GlyphInfo(int code, float offsetX, float offsetY, float width, float height, Region region, float advanceX, int pageID) {
        this.code = code;
        this.offset = new Vec2f(offsetX, offsetY);
        this.size = new Vec2f(width, height);
        this.region = region;
        this.advanceX = advanceX;
        this.pageID = pageID;
    }

    public int getCode() {
        return code;
    }

    public Vec2f getOffset() {
        return offset;
    }

    public Vec2f getSize() {
        return size;
    }

    public Region getRegion() {
        return region;
    }

    public float getAdvanceX() {
        return advanceX;
    }

    public int getPageID() {
        return pageID;
    }


    @Override
    public String toString() {
        return String.valueOf((char) code);
    }

}