package jpize.util.font;

import jpize.util.math.vector.Vec2f;

public class GlyphLine {

    private final String text;
    private final boolean breaked;
    private final float advanceY;
    private final Vec2f bounds;

    public GlyphLine(String text, boolean breaked, float advanceY) {
        this.text = text;
        this.breaked = breaked;
        this.advanceY = advanceY;
        this.bounds = new Vec2f();
    }

    public String getText() {
        return text;
    }

    public char charAt(int index) {
        return text.charAt(index);
    }

    public boolean isEmpty() {
        return text.isEmpty();
    }

    public boolean isBreaked() {
        return breaked;
    }

    public float getAdvanceY() {
        return advanceY;
    }

    public Vec2f bounds() {
        return bounds;
    }

    public int size() {
        return text.length();
    }

}
