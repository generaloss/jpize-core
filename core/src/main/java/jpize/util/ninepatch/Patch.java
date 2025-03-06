package jpize.util.ninepatch;

import jpize.util.region.TextureRegion;

class Patch {

    private final TextureRegion region;
    private final float pixelWidth, pixelHeight;
    private final boolean dynamicX, dynamicY;
    private float drawWidth, drawHeight;

    public Patch(TextureRegion region, boolean dynamicX, boolean dynamicY) {
        this.region = region;
        this.pixelWidth = region.getPixelWidth();
        this.pixelHeight = region.getPixelHeight();
        this.dynamicX = dynamicX;
        this.dynamicY = dynamicY;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public float getPixelWidth() {
        return pixelWidth;
    }

    public float getPixelHeight() {
        return pixelHeight;
    }

    public boolean isDynamicX() {
        return dynamicX;
    }

    public boolean isDynamicY() {
        return dynamicY;
    }

    public float getDrawWidth() {
        return drawWidth;
    }

    public float getDrawHeight() {
        return drawHeight;
    }

    public void setDrawWidth(float drawWidth) {
        this.drawWidth = drawWidth;
    }

    public void setDrawHeight(float drawHeight) {
        this.drawHeight = drawHeight;
    }

}