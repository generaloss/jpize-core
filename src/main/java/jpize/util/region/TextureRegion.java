package jpize.util.region;

import jpize.gl.texture.Texture2D;

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
        super.set(region.u1, region.v1, region.u2, region.v2);
        return this.setTexture(region.getTexture());
    }

    public TextureRegion set(int x, int y, int width, int height) {
        super.set(x, y, width, height, texture.getWidth(), texture.getHeight());
        return this;
    }

    public TextureRegion set(Texture2D texture, int x, int y, int width, int height) {
        this.set(x, y, width, height);
        return this.setTexture(texture);
    }

    public TextureRegion set(Texture2D texture, double u1, double v1, double u2, double v2) {
        this.set(u1, v1, u2, v2);
        return this.setTexture(texture);
    }

    public TextureRegion set(Texture2D texture, Region region) {
        this.set(region);
        return this.setTexture(texture);
    }


    public TextureRegion setSubregion(double x, double y, double width, double height) {
        this.setSubregion(x, y, width, height, texture.getWidth(), texture.getHeight());
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
