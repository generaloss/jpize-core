package jpize.util.font;

import spatialmath.vector.Vec2f;
import jpize.util.region.Region;
import jpize.opengl.texture.Texture2D;

public class GlyphSprite {

    private final Region region;
    private final Texture2D page;

    private final Vec2f position;
    private final Vec2f size;

    private final float offsetY;

    public GlyphSprite(FontData fontData, GlyphInfo glyph, Vec2f position, Vec2f scale) {
        this.region = glyph.region();
        this.page = fontData.pages().get(glyph.getPageID());
        this.position = position.copy().add(glyph.offset()).mul(scale);
        this.size = glyph.size().copy().mul(scale);
        this.offsetY = (glyph.offset().y * scale.y);
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
