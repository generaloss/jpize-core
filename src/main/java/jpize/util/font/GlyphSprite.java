package jpize.util.font;

import jpize.util.math.vector.Vec2f;
import jpize.util.region.Region;
import jpize.gl.texture.Texture2D;

public class GlyphSprite {

    private final int code;
    private final Region region;
    private final Texture2D page;

    private final Vec2f position;
    private final Vec2f size;

    private final int lineIndex;
    private final boolean renderable;
    private final float advanceX;
    private final float offsetY;

    public GlyphSprite(FontData fontData, GlyphInfo glyph, float cursorX, float cursorY, Vec2f scale, int lineIndex) {
        this.code = glyph.getCode();
        this.region = glyph.getRegion();
        this.page = fontData.pages().get(glyph.getPageID());

        this.position = new Vec2f(cursorX, cursorY).add(glyph.getOffset()).mul(scale);
        this.size = glyph.getSize().copy().mul(scale);

        this.lineIndex = lineIndex;
        this.renderable = true;
        this.advanceX = (glyph.getAdvanceX() * scale.x);
        this.offsetY  = (glyph.getOffset().y * scale.y);
    }

    public GlyphSprite(float cursorY, float height, Vec2f scale, int lineIndex) {
        this.code = -1;
        this.region = null;
        this.page = null;

        this.position = new Vec2f(0, cursorY).mul(scale);
        this.size = new Vec2f(0, height).mul(scale);

        this.lineIndex = lineIndex;
        this.renderable = false;
        this.advanceX = 0;
        this.offsetY = 0;
    }


    public int getCode() {
        return code;
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


    public int getLineIndex() {
        return lineIndex;
    }

    public boolean isRenderable() {
        return renderable;
    }

    public float getAdvanceX() {
        return advanceX;
    }

    public float getOffsetY() {
        return offsetY;
    }


    @Override
    public String toString() {
        return String.valueOf((char) code);
    }

}
