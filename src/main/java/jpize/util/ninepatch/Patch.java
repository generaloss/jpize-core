package jpize.util.ninepatch;

import jpize.util.region.TextureRegion;

class Patch {

    public final TextureRegion region;
    public final boolean stretchableX;
    public final boolean stretchableY;
    public float x, y, width, height;
    public boolean top, left, bottom, right;

    public Patch(TextureRegion region, boolean stretchableX, boolean stretchableY) {
        this.region = region;
        this.stretchableX = stretchableX;
        this.stretchableY = stretchableY;
    }

}