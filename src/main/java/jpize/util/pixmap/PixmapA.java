package jpize.util.pixmap;

import jpize.gl.texture.GlInternalFormat;
import jpize.util.math.Maths;

import java.nio.ByteBuffer;

public class PixmapA extends Pixmap {

    public PixmapA(ByteBuffer buffer, int width, int height) {
        super(buffer, width, height, 1);
    }

    public PixmapA(PixmapA pixmap) {
        this(pixmap.buffer, pixmap.width, pixmap.height);
    }

    public PixmapA(int width, int height) {
        super(width, height, 1);
    }


    public float getPixelValue(int x, int y) {
        return (buffer.get(getChannelIndex(x, y)) & 0xFF) / 255F;
    }

    public void setPixel(int x, int y, double value) {
        if(!super.isOutOfBounds(x, y))
            buffer.put(getChannelIndex(x, y), Maths.toByteRange(value));
    }

    public void setPixel(int x, int y, int value) {
        if(!super.isOutOfBounds(x, y))
            buffer.put(getChannelIndex(x, y), (byte) (value & 0xFF));
    }


    public PixmapRGBA toPixmapRGBA(float r, float g, float b) {
        final PixmapRGBA pixmap = new PixmapRGBA(width, height);

        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++){
                final float a = this.getPixelValue(x, y);
                pixmap.setPixel(x, y, r, g, b, a);
            }

        return pixmap;
    }

    public PixmapRGBA toPixmapRGBA() {
        return this.toPixmapRGBA(1F, 1F, 1F);
    }


    @Override
    public GlInternalFormat getFormat() {
        return GlInternalFormat.ALPHA8;
    }

    @Override
    public Pixmap copy() {
        return new PixmapA(this);
    }

}
