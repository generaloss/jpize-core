package jpize.util.font;

import jpize.gl.texture.TextureUtils;
import jpize.util.math.vector.Vec2f;
import jpize.util.region.Region;
import jpize.gl.texture.Texture2D;

public class GlyphSprite {

    private final Region region;
    private final Texture2D page;

    private final Vec2f position;
    private final Vec2f size;

    private final float offsetY;

    public GlyphSprite(FontData fontData, GlyphInfo glyph, Vec2f cursor, Vec2f scale) {
        this.region = glyph.getRegion();
        this.page = fontData.pages().get(glyph.getPageID());

        this.position = cursor.copy().add(glyph.getOffset()).mul(scale);
        this.size = glyph.getSize().copy().mul(scale);

        this.offsetY  = (glyph.getOffset().y * scale.y);
    }

    public GlyphSprite(FontData fontData, Vec2f cursor, Vec2f scale) {
        this.region = new Region();
        this.page = TextureUtils.whiteTexture();

        this.position = cursor.copy().add(0F).mul(scale);
        this.size = new Vec2f(fontData.getHeight() * 0.5F, fontData.getHeight()).mul(0.9F).mul(scale);

        this.offsetY  = 0F;
    }


    public Region getRegion() {
        return region;
    }

    public Texture2D getPage() {
        return page;
    }
    

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return size.x;
    }

    public float getHeight() {
        return size.y;
    }

    public float getOffsetY() {
        return offsetY;
    }

}
