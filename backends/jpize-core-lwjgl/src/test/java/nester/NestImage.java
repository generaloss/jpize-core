package nester;

import jpize.util.pixmap.PixmapRGBA;

class NestImage<T> {

    public final PixmapRGBA pixmap;
    public final T identifier; // indexing for regions
    public final int halfPerimeter;

    public NestImage(PixmapRGBA pixmap, T identifier) {
        this.pixmap = pixmap;
        this.identifier = identifier;
        halfPerimeter = pixmap.getWidth() + pixmap.getHeight();
    }

}