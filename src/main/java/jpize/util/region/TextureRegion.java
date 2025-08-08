package jpize.util.region;

import jpize.opengl.texture.Texture2D;
import generaloss.spatialmath.vector.Vec2d;
import generaloss.spatialmath.vector.Vec2f;
import generaloss.spatialmath.vector.Vec2i;

public class TextureRegion extends Region {
    
    private Texture2D texture;

    public TextureRegion() { }

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

    public TextureRegion setSubregionInPixels(double x, double y, double width, double height, double totalWidth, double totalHeight) {
        super.setSubregionInPixels(x, y, width, height, totalWidth, totalHeight);
        return this;
    }

    public Region setSubregionInPixels(double x, double y, double width, double height, Vec2i size) {
        super.setSubregionInPixels(x, y, width, height, size);
        return this;
    }

    public Region setSubregionInPixels(double x, double y, double width, double height, Vec2f size) {
        super.setSubregionInPixels(x, y, width, height, size);
        return this;
    }

    public Region setSubregionInPixels(double x, double y, double width, double height, Vec2d size) {
        super.setSubregionInPixels(x, y, width, height, size);
        return this;
    }

    public TextureRegion setSubregionInPixels(double x, double y, double width, double height) {
        super.setSubregionInPixels(x, y, width, height, texture.getWidth(), texture.getHeight());
        return this;
    }


    public TextureRegion extrude(float x, float y) {
        super.extrude(x, y);
        return this;
    }

    public TextureRegion extrude(int x, int y) {
        return this.extrude(
            (float) x / texture.getWidth(),
            (float) y / texture.getHeight()
        );
    }

    public TextureRegion translate(float x, float y) {
        super.translate(x, y);
        return this;
    }

    public TextureRegion translate(int x, int y) {
        return this.translate(
            (float) x / texture.getWidth(),
            (float) y / texture.getHeight()
        );
    }


    public TextureRegion setTexture(Texture2D texture) {
        this.texture = texture;
        return this;
    }

    public Texture2D getTexture() {
        return texture;
    }


    public float getPixelX() {
        return super.getPixelX(texture);
    }

    public float getPixelY() {
        return super.getPixelY(texture);
    }

    public float getPixelWidth() {
        return super.getPixelWidth(texture);
    }

    public float getPixelHeight() {
        return super.getPixelHeight(texture);
    }


    public TextureRegion copy() {
        return new TextureRegion(this);
    }

}
