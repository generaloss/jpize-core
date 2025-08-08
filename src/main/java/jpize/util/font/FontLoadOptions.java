package jpize.util.font;

import generaloss.resourceflow.resource.Resource;

public class FontLoadOptions {

    private int size = 64;

    public int getSize() {
        return size;
    }

    /** 64 set by default. Ignored in FNT fonts */
    public FontLoadOptions size(int size) {
        this.size = size;
        return this;
    }


    private boolean linearFilter = true;

    public boolean isLinearFilter() {
        return linearFilter;
    }

    /** True set by default */
    public FontLoadOptions linearFilter(boolean linearFilter) {
        this.linearFilter = linearFilter;
        return this;
    }


    private Charset charset = Charset.DEFAULT;

    public Charset getCharset() {
        return charset;
    }

    /** Ignored in FNT fonts */
    public FontLoadOptions charset(Charset charset) {
        this.charset = charset;
        return this;
    }


    private float borderWidth;

    public float getBorderWidth() {
        return borderWidth;
    }

    /** Ignored in FNT fonts */
    public FontLoadOptions borderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }


    private boolean borderStraight;

    public boolean isBorderStraight() {
        return borderStraight;
    }

    /** Ignored in FNT fonts */
    public FontLoadOptions borderStraight(boolean borderStraight) {
        this.borderStraight = borderStraight;
        return this;
    }

    /** Ignored in FNT fonts */
    public FontLoadOptions border(float width, boolean straight) {
        this.borderWidth = width;
        this.borderStraight = straight;
        return this;
    }


    private Resource attachment;

    public Resource getAttachment() {
        return attachment;
    }

    /** Ignored in FNT fonts */
    public FontLoadOptions attachment(Resource resource) {
        this.attachment = resource;
        return this;
    }

    /** Ignored in FNT fonts */
    public FontLoadOptions attachment(String internalResource) {
        return this.attachment(Resource.internal(internalResource));
    }


    private FontHinting hinting = FontHinting.LIGHT;

    public FontHinting getHinting() {
        return hinting;
    }

    /** FontHinting.LIGHT set by default. Ignored in FNT fonts */
    public FontLoadOptions hinting(FontHinting hinting) {
        this.hinting = hinting;
        return this;
    }

}
