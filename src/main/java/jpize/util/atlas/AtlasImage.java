package jpize.util.atlas;

import jpize.util.pixmap.PixmapRGBA;

class AtlasImage<T> {

    public final PixmapRGBA pixmap;
    public final T identifier; // indexing for regions
    public final int halfPerimeter;

    public AtlasImage(PixmapRGBA pixmap, T identifier) {
        this.pixmap = pixmap;
        this.identifier = identifier;
        halfPerimeter = pixmap.getWidth() + pixmap.getHeight();
    }

}