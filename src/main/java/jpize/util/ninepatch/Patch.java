package jpize.util.ninepatch;

import jpize.util.region.TextureRegion;

class Patch {

    public final TextureRegion region;
    public final boolean stretchableX, stretchableY;
    public float width, height;

    public Patch(TextureRegion region, boolean stretchableX, boolean stretchableY) {
        this.region = region;
        this.stretchableX = stretchableX;
        this.stretchableY = stretchableY;
    }

}