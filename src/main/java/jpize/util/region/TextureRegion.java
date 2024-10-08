package jpize.util.region;

import jpize.gl.texture.Texture2D;
import jpize.util.math.vector.Vec2d;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec2i;

public class TextureRegion extends Region {
    
    private Texture2D texture;

    public TextureRegion(TextureRegion textureRegion) {
        this.set(textureRegion);
    }

    public TextureRegion(Texture2D texture) {
        super();
        this.setTexture(texture);
    }

    public TextureRegion(Texture2D texture, int x, int y, int width, int height)  {
        this.set(texture, x, y, width, height);
    }

    public TextureRegion(Texture2D texture, double u1, double v1, double u2, double v2) {
        this.set(texture, u1, v1, u2, v2);
    }

    public TextureRegion(Texture2D texture, Region region) {
        this.set(texture, region);
    }


    public TextureRegion set(TextureRegion region) {
        this.setTexture(region.getTexture());
        super.set(region.u1, region.v1, region.u2, region.v2);
        return this;
    }

    public TextureRegion set(int x, int y, int width, int height) {
        super.set(x, y, width, height, texture.getWidth(), texture.getHeight());
        return this;
    }

    public TextureRegion set(Texture2D texture, int x, int y, int width, int height) {
        this.setTexture(texture);
        this.set(x, y, width, height);
        return this;
    }

    public TextureRegion set(Texture2D texture, double u1, double v1, double u2, double v2) {
        this.setTexture(texture);
        this.set(u1, v1, u2, v2);
        return this;
    }

    public TextureRegion set(Texture2D texture, Region region) {
        this.setTexture(texture);
        this.set(region);
        return this;
    }


    public TextureRegion setSubregion(double u1, double v1, double u2, double v2) {
        super.setSubregion(u1, v1, u2, v2);
        return this;
    }

    public TextureRegion setSubregion(Region region) {
        super.setSubregion(region);
        return this;
    }

    public TextureRegion setSubregionPx(double x, double y, double width, double height, double totalWidth, double totalHeight) {
        super.setSubregionPx(x, y, width, height, totalWidth, totalHeight);
        return this;
    }

    public Region setSubregionPx(double x, double y, double width, double height, Vec2i size) {
        super.setSubregionPx(x, y, width, height, size);
        return this;
    }

    public Region setSubregionPx(double x, double y, double width, double height, Vec2f size) {
        super.setSubregionPx(x, y, width, height, size);
        return this;
    }

    public Region setSubregionPx(double x, double y, double width, double height, Vec2d size) {
        super.setSubregionPx(x, y, width, height, size);
        return this;
    }

    public TextureRegion setSubregionPx(double x, double y, double width, double height) {
        super.setSubregionPx(x, y, width, height, texture.getWidth(), texture.getHeight());
        return this;
    }


    public TextureRegion extrude(float x, float y) {
        super.extrude(x, y);
        return this;
    }

    public TextureRegion translate(float x, float y) {
        super.translate(x, y);
        return this;
    }


    public TextureRegion setTexture(Texture2D texture) {
        this.texture = texture;
        return this;
    }

    public Texture2D getTexture() {
        return texture;
    }

    
    public float getPixelWidth() {
        return getWidthPx(texture);
    }

    public float getPixelHeight() {
        return getHeightPx(texture);
    }


    public TextureRegion copy() {
        return new TextureRegion(this);
    }

}
