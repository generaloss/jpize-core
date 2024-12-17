package jpize.util.pixmap;

import jpize.gl.texture.GlInternalFormat;
import jpize.util.color.Color;
import jpize.util.color.AbstractColor;

import java.nio.ByteBuffer;
import java.util.function.BiConsumer;

public class PixmapRGBA extends Pixmap {

    private boolean blending;

    private Color dst_tryBlend_color1;
    private Color dst_tryBlend_dst;
    private final Color tmp_colorArg = new Color();

    public PixmapRGBA(ByteBuffer buffer, int width, int height) {
        super(buffer, width, height, 4);
    }

    public PixmapRGBA(PixmapRGBA pixmap) {
        this(pixmap.buffer, pixmap.width, pixmap.height);
    }

    public PixmapRGBA(int width, int height) {
        super(width, height, 4);
    }


    public PixmapRGBA enableBlending() {
        this.blending = true;
        if(dst_tryBlend_color1 == null){
            dst_tryBlend_color1 = new Color();
            dst_tryBlend_dst = new Color();
        }
        return this;
    }

    public PixmapRGBA disableBlending() {
        this.blending = false;
        return this;
    }

    public PixmapRGBA setBlending(boolean blending) {
        this.blending = blending;
        return this;
    }

    protected AbstractColor tryBlend(AbstractColor color, int x, int y) {
        if(!blending)
            return color;

        this.getPixel(dst_tryBlend_color1, x, y);
        Color.blend(dst_tryBlend_dst, dst_tryBlend_color1, color);
        return dst_tryBlend_dst;
    }


    public PixmapRGBA setPixel(int x, int y, AbstractColor color) {
        if(super.isOutOfBounds(x, y))
            return this;

        final int posIndex = this.getPositionIndex(x, y);
        final AbstractColor blendedColor = this.tryBlend(color, x, y);

        buffer.put(this.getChannelIndex(posIndex, 0), (byte) ((int) (blendedColor.getRed()   * 255) & 0xFF));
        buffer.put(this.getChannelIndex(posIndex, 1), (byte) ((int) (blendedColor.getGreen() * 255) & 0xFF));
        buffer.put(this.getChannelIndex(posIndex, 2), (byte) ((int) (blendedColor.getBlue()  * 255) & 0xFF));
        buffer.put(this.getChannelIndex(posIndex, 3), (byte) ((int) (blendedColor.getAlpha() * 255) & 0xFF));
        return this;
    }

    public PixmapRGBA setPixel(int x, int y, float red, float green, float blue, float alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.setPixel(x, y, tmp_colorArg);
    }

    public PixmapRGBA setPixel(int x, int y, double red, double green, double blue, double alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.setPixel(x, y, tmp_colorArg);
    }

    public PixmapRGBA setPixeli(int x, int y, int red, int green, int blue, int alpha) {
        tmp_colorArg.seti(red, green, blue, alpha);
        return this.setPixel(x, y, tmp_colorArg);
    }

    public PixmapRGBA setPixel(int x, int y, float red, float green, float blue) {
        tmp_colorArg.set(red, green, blue);
        return this.setPixel(x, y, tmp_colorArg);
    }

    public PixmapRGBA setPixel(int x, int y, double red, double green, double blue) {
        tmp_colorArg.set(red, green, blue);
        return this.setPixel(x, y, tmp_colorArg);
    }

    public PixmapRGBA setPixeli(int x, int y, int red, int green, int blue) {
        tmp_colorArg.seti(red, green, blue);
        return this.setPixel(x, y, tmp_colorArg);
    }

    public PixmapRGBA setPixelRGB(int x, int y, int color) {
        tmp_colorArg.setRGB(color);
        return this.setPixel(x, y, tmp_colorArg);
    }

    public PixmapRGBA setPixelRGBA(int x, int y, int color) {
        tmp_colorArg.setRGBA(color);
        return this.setPixel(x, y, tmp_colorArg);
    }

    public PixmapRGBA setPixelARGB(int x, int y, int color) {
        tmp_colorArg.setARGB(color);
        return this.setPixel(x, y, tmp_colorArg);
    }


    public PixmapRGBA getPixel(Color dst, int x, int y) {
        final int posIndex = this.getPositionIndex(x, y);
        dst.seti(
                (buffer.get(this.getChannelIndex(posIndex, 0)) & 0xFF),
                (buffer.get(this.getChannelIndex(posIndex, 1)) & 0xFF),
                (buffer.get(this.getChannelIndex(posIndex, 2)) & 0xFF),
                (buffer.get(this.getChannelIndex(posIndex, 3)) & 0xFF)
        );
        return this;
    }

    public int getPixelRGBA(int x, int y) {
        final int posIndex = this.getPositionIndex(x, y);
        final int r = buffer.get(this.getChannelIndex(posIndex, 0)) & 0xFF;
        final int g = buffer.get(this.getChannelIndex(posIndex, 1)) & 0xFF;
        final int b = buffer.get(this.getChannelIndex(posIndex, 2)) & 0xFF;
        final int a = buffer.get(this.getChannelIndex(posIndex, 3)) & 0xFF;
        return (r << 24 | g << 16 | b << 8 | a);
    }
    
    public int getPixelARGB(int x, int y) {
        final int posIndex = this.getPositionIndex(x, y);
        final int r = buffer.get(this.getChannelIndex(posIndex, 0)) & 0xFF;
        final int g = buffer.get(this.getChannelIndex(posIndex, 1)) & 0xFF;
        final int b = buffer.get(this.getChannelIndex(posIndex, 2)) & 0xFF;
        final int a = buffer.get(this.getChannelIndex(posIndex, 3)) & 0xFF;
        return (a << 24 | r << 16 | g << 8 | b);
    }
    
    public int getPixelABGR(int x, int y) {
        final int posIndex = this.getPositionIndex(x, y);
        final int r = buffer.get(this.getChannelIndex(posIndex, 0)) & 0xFF;
        final int g = buffer.get(this.getChannelIndex(posIndex, 1)) & 0xFF;
        final int b = buffer.get(this.getChannelIndex(posIndex, 2)) & 0xFF;
        final int a = buffer.get(this.getChannelIndex(posIndex, 3)) & 0xFF;
        return (a << 24 | b << 16 | g << 8 | r);
    }


    public PixmapRGBA clear(AbstractColor color) {
        for(int i = 0; i < buffer.capacity(); i += 4){
            buffer.put(i,     (byte) (color.getRed()   * 255));
            buffer.put(i + 1, (byte) (color.getGreen() * 255));
            buffer.put(i + 2, (byte) (color.getBlue()  * 255));
            buffer.put(i + 3, (byte) (color.getAlpha() * 255));
        }
        return this;
    }

    public PixmapRGBA clear(float red, float green, float blue, float alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.clear(tmp_colorArg);
    }

    public PixmapRGBA clear(double red, double green, double blue, double alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.clear(tmp_colorArg);
    }

    public PixmapRGBA cleari(int red, int green, int blue, int alpha) {
        tmp_colorArg.seti(red, green, blue, alpha);
        return this.clear(tmp_colorArg);
    }

    public PixmapRGBA clear(float red, float green, float blue) {
        tmp_colorArg.set(red, green, blue);
        return this.clear(tmp_colorArg);
    }

    public PixmapRGBA clear(double red, double green, double blue) {
        tmp_colorArg.set(red, green, blue);
        return this.clear(tmp_colorArg);
    }

    public PixmapRGBA cleari(int red, int green, int blue) {
        tmp_colorArg.seti(red, green, blue);
        return this.clear(tmp_colorArg);
    }

    public PixmapRGBA clearRGB(int color) {
        tmp_colorArg.setRGB(color);
        return this.clear(tmp_colorArg);
    }

    public PixmapRGBA clearRGBA(int color) {
        tmp_colorArg.setRGBA(color);
        return this.clear(tmp_colorArg);
    }

    public PixmapRGBA clearARGB(int color) {
        tmp_colorArg.setARGB(color);
        return this.clear(tmp_colorArg);
    }


    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, AbstractColor color) {
        beginX = Math.max(0, beginX);
        beginY = Math.max(0, beginY);
        endX = Math.min(endX, width  - 1);
        endY = Math.min(endY, height - 1);

        for(int x = beginX; x <= endX; x++)
            for(int y = beginY; y <= endY; y++)
                this.setPixel(x, y, color);
        return this;
    }

    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, float red, float green, float blue, float alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.fill(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, double red, double green, double blue, double alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.fill(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, int red, int green, int blue, int alpha) {
        tmp_colorArg.seti(red, green, blue, alpha);
        return this.fill(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, float red, float green, float blue) {
        tmp_colorArg.set(red, green, blue);
        return this.fill(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, double red, double green, double blue) {
        tmp_colorArg.set(red, green, blue);
        return this.fill(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, int red, int green, int blue) {
        tmp_colorArg.seti(red, green, blue);
        return this.fill(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA fillRGB(int beginX, int beginY, int endX, int endY, int color) {
        tmp_colorArg.setRGB(color);
        return this.fill(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA fillRGBA(int beginX, int beginY, int endX, int endY, int color) {
        tmp_colorArg.setRGBA(color);
        return this.fill(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA fillARGB(int beginX, int beginY, int endX, int endY, int color) {
        tmp_colorArg.setARGB(color);
        return this.fill(beginX, beginY, endX, endY, tmp_colorArg);
    }


    /*public int samplePixel(double x, double y) {
        return this.getPixelABGR((int) (x * width), (int) (y * height));
    }

    public PixmapRGBA samplePixel(Color dst, double x, double y) {
        return this.getPixel(dst, (int) (x * width), (int) (y * height));
    }*/


    // public PixmapRGBA drawLine(int beginX, int beginY, int endX, int endY, double r, double g, double b, double a) {
    //     final Vec2d vec = new Vec2d(endX - beginX, endY - beginY);
    //     final double angle = Math.atan2(vec.y, vec.x);
    //     final float offsetX = Mathc.cos(angle);
    //     final float offsetY = Mathc.sin(angle);

    //     float x = beginX;
    //     float y = beginY;
    //     float length = 0;

    //     while(length < vec.len()){
    //         this.setPixel(Maths.round(x), Maths.round(y), r, g, b, a);

    //         x += offsetX;
    //         y += offsetY;
    //         length++;
    //     }
    //     return this;
    // }

    // public PixmapRGBA drawDottedLine(int beginX, int beginY, int endX, int endY, double lineLength, double r, double g, double b, double a) {
    //     final Vec2d vec = new Vec2d(endX - beginX, endY - beginY);
    //     final double angle = Math.atan2(vec.y, vec.x);
    //     final float offsetX = Mathc.cos(angle);
    //     final float offsetY = Mathc.sin(angle);

    //     float x = beginX;
    //     float y = beginY;
    //     float length = 0;

    //     while(length < vec.len()){
    //         if(Math.sin(length / lineLength) >= 0)
    //             this.setPixel(Maths.round(x), Maths.round(y), r, g, b, a);

    //         x += offsetX;
    //         y += offsetY;
    //         length++;
    //     }
    //     return this;
    // }

    // public PixmapRGBA drawPixmap(PixmapRGBA pixmap) {
    //     if(pixmap == null)
    //         return this;
    //     final float iEnd = Math.min(pixmap.width, width);
    //     final float jEnd = Math.min(pixmap.height, height);

    //     for(int i = 0; i < iEnd; i++)
    //         for(int j = 0; j < jEnd; j++)
    //             this.setPixel(i, j, pixmap.getPixelColor(tmp_color_2, i, j));
    //     return this;
    // }

    // public PixmapRGBA drawPixmap(PixmapRGBA pixmap, int x, int y) {
    //     if(pixmap == null)
    //         return this;
    //     float iEnd = (iEnd = x + pixmap.width) > width ? width : iEnd;
    //     float jEnd = (jEnd = y + pixmap.height) > height ? height : jEnd;

    //     for(int i = Math.max(0, x); i < iEnd; i++){
    //         for(int j = Math.max(0, y); j < jEnd; j++){
    //             final int px = Maths.round(i - x);
    //             final int py = Maths.round(j - y);

    //             this.setPixel(i, j, pixmap.getPixelColor(px, py));
    //         }
    //     }
    //     return this;
    // }

    // public PixmapRGBA drawPixmap(PixmapRGBA pixmap, double scaleX, double scaleY) {
    //     if(pixmap == null || scaleX <= 0 || scaleY <= 0)
    //         return this;

    //     final double widthScaled  = (pixmap.width  * scaleX);
    //     final double heightScaled = (pixmap.height * scaleY);
    //     final double iEnd = (widthScaled  > width  ? width  : widthScaled);
    //     final double jEnd = (heightScaled > height ? height : heightScaled);

    //     for(int i = 0; i < iEnd; i++){
    //         for(int j = 0; j < jEnd; j++){
    //             final int px = (int) (i / scaleX);
    //             final int py = (int) (j / scaleY);

    //             this.setPixel(i, j, pixmap.getPixelColor(px, py));
    //         }
    //     }
    //     return this;
    // }

    // public PixmapRGBA drawPixmap(PixmapRGBA pixmap, double scale) {
    //     return drawPixmap(pixmap, scale, scale);
    // }

    // public PixmapRGBA drawPixmap(PixmapRGBA pixmap, int x, int y, double scaleX, double scaleY) {
    //     if(pixmap == null || scaleX <= 0 || scaleY <= 0)
    //         return this;

    //     final double widthScaled  = (x + pixmap.width  * scaleX);
    //     final double heightScaled = (y + pixmap.height * scaleY);
    //     final double iEnd = (widthScaled  > width  ? width  : widthScaled);
    //     final double jEnd = (heightScaled > height ? height : heightScaled);

    //     for(int i = Math.max(0, x); i < iEnd; i++){
    //         for(int j = Math.max(0, y); j < jEnd; j++){
    //             final int px = (int) ((i - x) / scaleX);
    //             final int py = (int) ((j - y) / scaleY);

    //             this.setPixel(i, j, pixmap.getPixelColor(px, py));
    //         }
    //     }
    //     return this;
    // }

    // public PixmapRGBA drawPixmap(PixmapRGBA pixmap, int x, int y, double scale) {
    //     return this.drawPixmap(pixmap, x, y, scale, scale);
    // }

    // public PixmapRGBA colorize(double r, double g, double b) {
    //     final Color color = new Color();
    //     for(int x = 0; x < width; x++){
    //         for(int y = 0; y < height; y++){
    //             this.getPixelColor(x, y, color);
    //             color.set(
    //                 (color.red   * 0.2126 + r) / 2,
    //                 (color.green * 0.7152 + g) / 2,
    //                 (color.blue  * 0.0722 + b) / 2
    //             );

    //             this.setPixel(x, y, color);
    //         }
    //     }
    //     return this;
    // }


    // public PixmapRGBA getMipmapped() {
    //     final PixmapRGBA pixmap = new PixmapRGBA(width / 2, height / 2);
    
    //     for(int x = 0; x < pixmap.width; x++)
    //         for(int y = 0; y < pixmap.height; y++)
    //             pixmap.setPixel(x, y, 1, 0, 0, 1F);

    //     return pixmap;
    // }


    // public ByteBuffer getAlphaMultipliedBuffer() {
    //     final ByteBuffer buffer = this.buffer.duplicate();

    //     for(int i = 0; i < buffer.capacity(); i += 4){
    //         final float alpha = (buffer.get(i + 3) & 0xFF) / 255F;

    //         buffer.put(i    , (byte) ((int) ((buffer.get(i    ) & 0xFF) * alpha) & 0xFF));
    //         buffer.put(i + 1, (byte) ((int) ((buffer.get(i + 1) & 0xFF) * alpha) & 0xFF));
    //         buffer.put(i + 2, (byte) ((int) ((buffer.get(i + 2) & 0xFF) * alpha) & 0xFF));
    //     }

    //     return buffer;
    // }


    public PixmapRGBA forEach(BiConsumer<Integer, Integer> biConsumer) {
        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++)
                biConsumer.accept(x, y);
        return this;
    }


    @Override
    public GlInternalFormat getFormat() {
        return GlInternalFormat.RGBA8;
    }

    @Override
    public PixmapRGBA copy() {
        return new PixmapRGBA(this);
    }

}
