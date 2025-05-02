package jpize.util.pixmap;

import jpize.opengl.texture.GLInternalFormat;

import java.nio.ByteBuffer;

public class PixmapAlpha extends Pixmap {

    public PixmapAlpha(ByteBuffer buffer, int width, int height) {
        super(buffer, width, height, 1);
    }

    public PixmapAlpha(PixmapAlpha pixmap) {
        this(pixmap.buffer, pixmap.width, pixmap.height);
    }

    public PixmapAlpha(int width, int height) {
        super(width, height, 1);
    }


    public int getPixel(int x, int y) {
        return super.getPixelChannel(x, y, 0);
    }

    public float getPixelValue(int x, int y) {
        return super.getPixelChannelValue(x, y, 0);
    }

    
    public void setPixel(int x, int y, int value) {
        super.setPixelChannel(x, y, 0, value);
    }

    public void setPixelValue(int x, int y, double value) {
        super.setPixelChannelValue(x, y, 0, value);
    }


    @Override
    public GLInternalFormat getFormat() {
        return GLInternalFormat.ALPHA8;
    }

    @Override
    public Pixmap copy() {
        return new PixmapAlpha(this);
    }

}
