package jpize.util.font;

import jpize.util.region.Region;
import jpize.util.math.vector.Vec2f;

public class Glyph {

    public final int code;
    public final Vec2f offset;
    public final Vec2f size;
    public final Region region;
    public final float advanceX;
    public int pageID;

    public Glyph(int code, float offsetX, float offsetY, float width, float height, Region region, float advanceX, int pageID) {
        this.code = code;
        this.region = region;
        this.offset = new Vec2f(offsetX, offsetY);
        this.size = new Vec2f(width, height);
        this.advanceX = advanceX;
        this.pageID = pageID;
    }

}