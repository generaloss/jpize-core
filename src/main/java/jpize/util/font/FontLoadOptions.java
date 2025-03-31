package jpize.util.font;

public class FontLoadOptions {

    private int size = 64;

    public int getSize() {
        return size;
    }

    /** Ignored is FNT fonts */
    public FontLoadOptions size(int size) {
        this.size = size;
        return this;
    }


    private boolean linearFilter = true;

    public boolean isLinearFilter() {
        return linearFilter;
    }

    public FontLoadOptions filter(boolean linear) {
        this.linearFilter = linear;
        return this;
    }


    private Charset charset = Charset.DEFAULT;

    public Charset getCharset() {
        return charset;
    }

    /** Ignored is FNT fonts */
    public FontLoadOptions charset(Charset charset) {
        this.charset = charset;
        return this;
    }


    private int borderWidth;

    public int getBorderWidth() {
        return borderWidth;
    }

    /** Ignored is FNT fonts */
    public FontLoadOptions borderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }


    private boolean borderStraight;

    public boolean isBorderStraight() {
        return borderStraight;
    }

    /** Ignored is FNT fonts */
    public FontLoadOptions borderStraight(boolean borderStraight) {
        this.borderStraight = borderStraight;
        return this;
    }

    /** Ignored is FNT fonts */
    public FontLoadOptions border(int width, boolean straight) {
        this.borderWidth = width;
        this.borderStraight = straight;
        return this;
    }

}
