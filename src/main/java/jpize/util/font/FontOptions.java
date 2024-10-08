package jpize.util.font;

import jpize.util.color.Color;
import jpize.util.math.vector.Vec2f;

public class FontOptions {

    private final Font font;

    public float scale;
    public float rotation;
    public boolean italic;
    public Color color;

    public float lineGaps;
    public float italicAngle;
    public double wrapThreesholdWidth;
    public boolean invLineWrap;
    public final Vec2f rotateOrigin;
    public final Vec2f advanceFactor;

    public FontOptions(Font font) {
        this.font = font;

        this.scale = 1;
        this.color = new Color();
        this.italicAngle = 15;
        this.wrapThreesholdWidth = -1;
        this.rotateOrigin = new Vec2f(0.5);
        this.advanceFactor = new Vec2f(1);
    }

    public Font getFont() {
        return font;
    }

    public float getAdvance() {
        return font.getHeight() + lineGaps;
    }

    public float getAdvanceScaled() {
        return getAdvance() * scale;
    }

    public float getGapsScaled() {
        return lineGaps * scale;
    }

    public float getDescentScaled() {
        return font.getDescent() * scale;
    }

    public float getLineHeightScaled() {
        return font.getHeight() * scale;
    }

    public float getItalicAngle() {
        if(italic)
            return italicAngle;
        return 0;
    }

    public int getLineWrapSign() {
        return invLineWrap ? -1 : 1;
    }

}
