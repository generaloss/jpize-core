package jpize.util.region;

import jpize.gl.texture.Texture2D;
import jpize.util.math.vector.Vec2d;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec2i;

public class Region {

    protected float u1, v1, u2, v2;

    public Region(Region region) {
        this.set(region);
    }
    
    public Region(float u1, float v1, float u2, float v2) {
        this.set(u1, v1, u2, v2);
    }

    public Region(double u1, double v1, double u2, double v2) {
        this.set(u1, v1, u2, v2);
    }

    public Region() {
        this.u2 = 1F;
        this.v2 = 1F;
    }

    public Region(double x, double y, double width, double height, double totalWidth, double totalHeight) {
        this.set(x, y, width, height, totalWidth, totalHeight);
    }


    public Region set(Region region) {
        return this.set(region.u1, region.v1, region.u2, region.v2);
    }

    public Region set(float u1, float v1, float u2, float v2) {
        this.u1 = u1;
        this.v1 = v1;
        this.u2 = u2;
        this.v2 = v2;
        return this;
    }

    public Region set(double u1, double v1, double u2, double v2) {
        return this.set((float) u1, (float) v1, (float) u2, (float) v2);
    }

    public Region reset() {
        return this.set(0F, 0F, 1F, 1F);
    }

    public Region set(double x, double y, double width, double height, double totalWidth, double totalHeight) {
        return this.set(
            x / totalWidth,
            y / totalHeight,
            (x + width)  / totalWidth,
            (y + height) / totalHeight
        );
    }


    public Region setSubregion(double u1, double v1, double u2, double v2) {
        final float width = this.getWidth();
        final float height = this.getHeight();
        return this.set(
            this.u1 + (u1 * width ),
            this.v1 + (v1 * height),
            this.u1 + (u2 * width ),
            this.v1 + (v2 * height)
        );
    }

    public Region setSubregion(Region region) {
        return this.setSubregion(region.u1, region.v1, region.u2, region.v2);
    }

    public Region setSubregionPx(double x, double y, double width, double height, double totalWidth, double totalHeight) {
        return this.setSubregion(
            x / totalWidth,
            y / totalHeight,
            (x + width)  / totalWidth,
            (y + height) / totalHeight
        );
    }

    public Region setSubregionPx(double x, double y, double width, double height, Vec2i size) {
        return this.setSubregionPx(x, y, width, height, size.x, size.y);
    }

    public Region setSubregionPx(double x, double y, double width, double height, Vec2f size) {
        return this.setSubregionPx(x, y, width, height, size.x, size.y);
    }

    public Region setSubregionPx(double x, double y, double width, double height, Vec2d size) {
        return this.setSubregionPx(x, y, width, height, size.x, size.y);
    }


    public Region extrude(float x, float y) {
        u2 = u1 + (this.getWidth()  * x);
        v2 = v1 + (this.getHeight() * y);
        return this;
    }

    public Region translate(float x, float y) {
        x *= this.getWidth();
        y *= this.getHeight();
        u1 += x;
        v1 += y;
        u2 += x;
        v2 += y;
        return this;
    }


    public float u1() {
        return u1;
    }

    public float v1() {
        return v1;
    }

    public float u2() {
        return u2;
    }

    public float v2() {
        return v2;
    }

    public float getWidth() {
        return (u2 - u1);
    }
    
    public float getHeight() {
        return (v2 - v1);
    }
    
    
    public float aspect() {
        return this.getWidth() / this.getHeight();
    }
    
    public float getWidthPx(Texture2D texture) {
        return this.getWidth() * texture.getWidth();
    }
    
    public float getHeightPx(Texture2D texture) {
        return this.getHeight() * texture.getHeight();
    }

    
    public Region copy() {
        return new Region(this);
    }

    @Override
    public String toString() {
        return "[" + u1 + ", " + v1 + "]; [" + u2 + ", " + v2 + "]";
    }

}
