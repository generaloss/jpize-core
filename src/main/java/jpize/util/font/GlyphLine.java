package jpize.util.font;

public class GlyphLine {

    private final long[] codepoints;
    private final boolean newLine;
    private final float advanceY;

    public GlyphLine(long[] codepoints, boolean newLine, float advanceY) {
        this.codepoints = codepoints;
        this.newLine = newLine;
        this.advanceY = advanceY;
    }

    public long[] getCodepoints() {
        return codepoints;
    }

    public long codepointAt(int index) {
        return codepoints[index];
    }

    public boolean isEmpty() {
        return (codepoints.length == 0);
    }

    public boolean isNewLine() {
        return newLine;
    }

    public float getAdvanceY() {
        return advanceY;
    }

    public int size() {
        return codepoints.length;
    }

}
