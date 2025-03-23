package jpize.stb.freetype;

import jpize.util.color.AbstractColor;
import jpize.util.color.Color;
import jpize.util.pixmap.Pixmap;
import jpize.util.pixmap.PixmapAlpha;
import jpize.util.pixmap.PixmapRGBA;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class FreeTypeBitmap {

    private final long address;

    protected FreeTypeBitmap(long address) {
        this.address = address;
    }

    private static native int getRows(long bitmap);

    public int getRows() {
        return getRows(address);
    }


    private static native int getWidth(long bitmap);

    public int getWidth() {
        return getWidth(address);
    }


    private static native int getPitch(long bitmap);

    public int getPitch() {
        return getPitch(address);
    }


    private static native ByteBuffer getBuffer(long bitmap);

    public ByteBuffer getBuffer() {
        if(this.getRows() == 0)
            return ByteBuffer.allocateDirect(1);
        return getBuffer(address);
    }


    private static native int getNumGray(long bitmap);

    public int getNumGray() {
        return getNumGray(address);
    }


    private static native int getPixelMode(long bitmap);

    public int getPixelMode() {
        return getPixelMode(address);
    }


    public Pixmap getPixmap(AbstractColor color, float gamma) {
        final int width = getWidth();
        final int rows = getRows();
        final int pixelMode = getPixelMode();
        final int rowBytes = Math.abs(getPitch());
        final ByteBuffer buffer = getBuffer();

        if(color == Color.WHITE && pixelMode == FreeType.FT_PIXEL_MODE_GRAY && rowBytes == width && gamma == 1F)
            return new PixmapAlpha(buffer, width, rows);

        final PixmapRGBA pixmap = new PixmapRGBA(width, rows);
        final int rgba = color.getHexInteger();
        final byte[] srcRow = new byte[rowBytes];
        final int[] dstRow = new int[width];

        final IntBuffer dst = pixmap.buffer().asIntBuffer();
        if(pixelMode == FreeType.FT_PIXEL_MODE_MONO) {
            // use the specified color for each set bit
            for(int y = 0; y < rows; y++) {
                buffer.get(srcRow);
                for(int i = 0, x = 0; x < width; i++, x += 8) {
                    byte b = srcRow[i];
                    for(int j = 0, n = Math.min(8, width - x); j < n; j++) {
                        if((b & (1 << (7 - j))) != 0) {
                            dstRow[x + j] = rgba;
                        } else {
                            dstRow[x + j] = 0;
                        }
                    }
                }
                dst.put(dstRow);
            }
        } else {
            // use the specified color for RGB, blend the FreeType bitmap with alpha
            final int rgb = (rgba & 0xffffff00);
            final int a = (rgba & 0xff);
            for(int y = 0; y < rows; y++) {
                buffer.get(srcRow);
                for(int x = 0; x < width; x++) {
                    // zero raised to any power is always zero
                    // 255 (= one) raised to any power is always one
                    // we only need Math.pow() when alpha is NOT zero and NOT one
                    final int alpha = srcRow[x] & 0xff;
                    if(alpha == 0) {
                        dstRow[x] = rgb;
                    } else if(alpha == 255) {
                        dstRow[x] = rgb | a;
                    } else {
                        dstRow[x] = rgb | (int) (a * (float) Math.pow(alpha / 255f, gamma)); // inverse gamma
                    }
                }
                dst.put(dstRow);
            }
        }
        return pixmap;
    }

}