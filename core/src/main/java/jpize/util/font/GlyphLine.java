package jpize.util.font;

public class GlyphLine {

    private final String text;
    private final boolean newLine;
    private final float advanceY;

    public GlyphLine(String text, boolean newLine, float advanceY) {
        this.text = text;
        this.newLine = newLine;
        this.advanceY = advanceY;
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

    public boolean isNewLine() {
        return newLine;
    }

    public float getAdvanceY() {
        return advanceY;
    }

    public int size() {
        return text.length();
    }

    @Override
    public String toString() {
        return text;
    }

}
