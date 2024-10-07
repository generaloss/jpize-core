package jpize.util.region;

import jpize.gl.texture.Texture2D;

public class TextureRegion extends Region {
    
    private Texture2D texture;

    public TextureRegion(TextureRegion textureRegion) {
        this.set(textureRegion);
    }

    public TextureRegion(TextureRegion textureRegion1, int x, int y, int width, int height) {
        this.set(textureRegion1, x, y, width, height);
    }

    public TextureRegion(TextureRegion textureRegion1, double u1, double v1, double u2, double v2) {
        this.set(textureRegion1, u1, v1, u2, v2);
    }

    public TextureRegion(TextureRegion textureRegion1, Region region2) {
        this.set(textureRegion1, region2);
    }

    public TextureRegion(Region region1, TextureRegion textureRegion2) {
        this.set(region1, textureRegion2);
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


    public void set(TextureRegion region) {
        this.setTexture(region.getTexture());
        super.set(region.u1, region.v1, region.u2, region.v2);
    }

    public void set(TextureRegion textureRegion1, int x, int y, int width, int height) {
        this.setTexture(textureRegion1.getTexture());
        super.set(textureRegion1, x, y, width, height, texture.getWidth(), texture.getHeight());
    }

    public void set(TextureRegion textureRegion1, double u1, double v1, double u2, double v2) {
        this.setTexture(textureRegion1.getTexture());
        super.set(textureRegion1, u1, v1, u2, v2);
    }

    public void set(TextureRegion textureRegion1, Region region2) {
        this.setTexture(textureRegion1.getTexture());
        super.set(textureRegion1, region2);
    }

    public void set(Region region1, TextureRegion textureRegion2) {
        this.setTexture(textureRegion2.getTexture());
        super.set(region1, textureRegion2);
    }

    public void set(int x, int y, int width, int height) {
        super.set(x, y, width, height, texture.getWidth(), texture.getHeight());
    }

    public void set(Texture2D texture, int x, int y, int width, int height) {
        this.setTexture(texture);
        this.set(x, y, width, height);
    }

    public void set(Texture2D texture, double u1, double v1, double u2, double v2) {
        this.setTexture(texture);
        this.set(u1, v1, u2, v2);
    }

    public void set(Texture2D texture, Region region) {
        this.setTexture(texture);
        this.set(region);
    }


    public void setTexture(Texture2D texture) {
        this.texture = texture;
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
