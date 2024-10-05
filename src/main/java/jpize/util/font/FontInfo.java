package jpize.util.font;

public class FontInfo {

    protected final float height;
    protected final float ascent;
    protected final float descent;

    public FontInfo(float height, float ascent, float descent) {
        this.height = height;
        this.ascent = ascent;
        this.descent = descent;
    }

    public float getHeight() {
        return height;
    }

    public float getAscent() {
        return ascent;
    }

    public float getDescent() {
        return descent;
    }

}
