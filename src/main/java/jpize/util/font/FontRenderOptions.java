package jpize.util.font;

import jpize.util.color.Color;
import jpize.util.math.vector.Vec2f;

public class FontRenderOptions {
    
    private final Vec2f scale = new Vec2f(1F);
    
    public Vec2f scale() {
        return scale;
    }


    private final Color color = new Color();
    
    public Color color() {
        return color;
    }


    private float rotation;
    
    public float getRotation() {
        return rotation;
    }
    
    public FontRenderOptions setRotation(float rotation) {
        this.rotation = rotation;
        return this;
    }


    private final Vec2f rotationOrigin = new Vec2f(0.5F);
    
    public Vec2f rotationOrigin() {
        return rotationOrigin;
    }


    private float italicAngle = 15F;

    public float getItalicAngle() {
        return italicAngle;
    }

    public FontRenderOptions setItalicAngle(float italicAngle) {
        this.italicAngle = italicAngle;
        return this;
    }


    private float lineGap;

    public float getLineGap() {
        return lineGap;
    }

    public float getGapScaled() {
        return lineGap * scale.y;
    }

    public FontRenderOptions setLineGap(float lineGap) {
        this.lineGap = lineGap;
        return this;
    }


    private final Vec2f advanceFactor = new Vec2f(1F);

    public Vec2f advanceFactor() {
        return advanceFactor;
    }


    private float wrapWidth = -1F;

    public float getWrapWidth() {
        return wrapWidth;
    }

    public FontRenderOptions setWrapWidth(float wrapWidth) {
        this.wrapWidth = wrapWidth;
        return this;
    }


    private boolean cullLinesEnabled;
    private float cullLinesBottomY;
    private float cullLinesTopY;

    public boolean isCullLinesEnabled() {
        return cullLinesEnabled;
    }

    public float getCullLinesBottomY() {
        return cullLinesBottomY;
    }

    public float getCullLinesTopY() {
        return cullLinesTopY;
    }

    public FontRenderOptions enableCullLines(float bottomY, float topY) {
        this.cullLinesEnabled = true;
        this.cullLinesBottomY = bottomY;
        this.cullLinesTopY = topY;
        return this;
    }

    public FontRenderOptions disableCullLines() {
        this.cullLinesEnabled = false;
        return this;
    }


    private boolean invLineWrap;

    public boolean isInvLineWrap() {
        return invLineWrap;
    }

    public int getLineWrapSign() {
        return invLineWrap ? -1 : 1;
    }

    public FontRenderOptions setInvLineWrap(boolean invLineWrap) {
        this.invLineWrap = invLineWrap;
        return this;
    }


}