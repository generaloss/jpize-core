package jpize.util.pixmap;

import jpize.gl.texture.GlInternalFormat;
import jpize.util.color.Color;
import jpize.util.math.Mathc;
import jpize.util.math.Maths;
import jpize.util.math.vector.Vec2d;

import java.nio.ByteBuffer;
import java.util.function.BiConsumer;

public class PixmapRGBA extends Pixmap {

    private boolean blending;
    private Color tmp_color_1;
    private Color tmp_color_2;

    // CONSTRUCTOR
    public PixmapRGBA(ByteBuffer buffer, int width, int height) {
        super(buffer, width, height, 4);
    }

    public PixmapRGBA(PixmapRGBA pixmap) {
        this(pixmap.buffer, pixmap.width, pixmap.height);
    }

    public PixmapRGBA(int width, int height) {
        super(width, height, 4);
    }


    // SET PIXEL
    public PixmapRGBA setPixel(int x, int y, int color) {
        if(super.outOfBounds(x, y))
            return this;

        final Color blendColor = Color.blend(
            this.getPixelColor(x, y),
            new Color(color >> 24 & 0xFF, color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF)
        );
        buffer.put(this.getIndex(x, y, 0), (byte) ((color >> 24) & 0xFF));
        buffer.put(this.getIndex(x, y, 1), (byte) ((color >> 16) & 0xFF));
        buffer.put(this.getIndex(x, y, 2), (byte) ((color >> 8) & 0xFF));
        buffer.put(this.getIndex(x, y, 3), (byte) ((color) & 0xFF));
        return this;
    }

    public PixmapRGBA setPixel(int x, int y, Color color) {
        if(super.outOfBounds(x, y))
            return this;

        final Color blendColor = this.blend(this.getPixelColor(tmp_color_1, x, y), color);
        buffer.put(this.getIndex(x, y, 0), (byte) ((int) (blendColor.red   * 255) & 0xFF));
        buffer.put(this.getIndex(x, y, 1), (byte) ((int) (blendColor.green * 255) & 0xFF));
        buffer.put(this.getIndex(x, y, 2), (byte) ((int) (blendColor.blue  * 255) & 0xFF));
        buffer.put(this.getIndex(x, y, 3), (byte) ((int) (blendColor.alpha * 255) & 0xFF));
        return this;
    }

    public PixmapRGBA setPixel(int x, int y, double r, double g, double b, double a) {
        if(super.outOfBounds(x, y))
            return this;

        final Color blendColor = this.blend(this.getPixelColor(x, y), new Color(r, g, b, a));
        buffer.put(this.getIndex(x, y, 0), (byte) ((int) (blendColor.red   * 255) & 0xFF));
        buffer.put(this.getIndex(x, y, 1), (byte) ((int) (blendColor.green * 255) & 0xFF));
        buffer.put(this.getIndex(x, y, 2), (byte) ((int) (blendColor.blue  * 255) & 0xFF));
        buffer.put(this.getIndex(x, y, 3), (byte) ((int) (blendColor.alpha * 255) & 0xFF));
        return this;
    }

    public PixmapRGBA setPixel(int x, int y, int r, int g, int b, int a) {
        if(super.outOfBounds(x, y))
            return this;

        final Color blendColor = this.blend(this.getPixelColor(x, y), new Color(r, g, b, a));
        buffer.put(this.getIndex(x, y, 0), (byte) (r & 0xFF));
        buffer.put(this.getIndex(x, y, 1), (byte) (g & 0xFF));
        buffer.put(this.getIndex(x, y, 2), (byte) (b & 0xFF));
        buffer.put(this.getIndex(x, y, 3), (byte) (a & 0xFF));
        return this;
    }

    public PixmapRGBA setPixel(int x, int y, double r, double g, double b) {
        if(super.outOfBounds(x, y))
            return this;

        final Color blendColor = this.blend(this.getPixelColor(x, y), new Color(r, g, b));
        buffer.put(this.getIndex(x, y, 0), (byte) ((int) (blendColor.red   * 255) & 0xFF));
        buffer.put(this.getIndex(x, y, 1), (byte) ((int) (blendColor.green * 255) & 0xFF));
        buffer.put(this.getIndex(x, y, 2), (byte) ((int) (blendColor.blue  * 255) & 0xFF));
        buffer.put((byte) 255);
        return this;
    }

    public PixmapRGBA setPixel(int x, int y, int r, int g, int b) {
        if(super.outOfBounds(x, y))
            return this;

        final Color blendColor = this.blend(this.getPixelColor(x, y), new Color(r, g, b));
        buffer.put(this.getIndex(x, y, 0), (byte) (r & 0xFF));
        buffer.put(this.getIndex(x, y, 1), (byte) (g & 0xFF));
        buffer.put(this.getIndex(x, y, 2), (byte) (b & 0xFF));
        buffer.put((byte) 255);
        return this;
    }

    // BLENDING
    public PixmapRGBA enableBlending(boolean blending) {
        this.blending = blending;
        return this;
    }

    private Color blend(Color color1, Color color2){
        if(!blending)
            return new Color(color2);

        color1.set(
            color1.red   * (1 - color2.alpha) + color2.red   * (color2.alpha),
            color1.green * (1 - color2.alpha) + color2.green * (color2.alpha),
            color1.blue  * (1 - color2.alpha) + color2.blue  * (color2.alpha),
            Math.max(color1.alpha, color2.alpha)
        );

        return color1;
    }

    private Color blend(Color color1, double r2, double g2, double b2, double a2) {
        if(!blending)
            return new Color(r2, g2, b2, a2);

        color1.set(
            color1.red   * (1 - a2) + r2 * (a2),
            color1.green * (1 - a2) + g2 * (a2),
            color1.blue  * (1 - a2) + b2 * (a2),
            Math.max(color1.alpha, a2)
        );

        return color1;
    }

    private Color blend(double r1, double g1, double b1, double a1, double r2, double g2, double b2, double a2) {
        if(!blending)
            return new Color(r2, g2, b2, a2);

        return new Color(
            r1 * (1 - a2) + r2 * (a2),
            g1 * (1 - a2) + g2 * (a2),
            b1 * (1 - a2) + b2 * (a2),
            Math.max(a1, a2)
        );
    }

    // GET PIXEL
    public int getPixelABGR(int x, int y) {
        final int r = buffer.get(this.getIndex(x, y, 0)) & 0xFF;
        final int g = buffer.get(this.getIndex(x, y, 1)) & 0xFF;
        final int b = buffer.get(this.getIndex(x, y, 2)) & 0xFF;
        final int a = buffer.get(this.getIndex(x, y, 3)) & 0xFF;
        return (a | b << 8 | g << 16 | r << 24);
    }
    
    public int getPixelBGRA(int x, int y) {
        final int r = buffer.get(this.getIndex(x, y, 0)) & 0xFF;
        final int g = buffer.get(this.getIndex(x, y, 1)) & 0xFF;
        final int b = buffer.get(this.getIndex(x, y, 2)) & 0xFF;
        final int a = buffer.get(this.getIndex(x, y, 3)) & 0xFF;
        return (b | g << 8 | r << 16 | a << 24);
    }
    
    public int getPixelRGBA(int x, int y) {
        final int r = buffer.get(this.getIndex(x, y, 0)) & 0xFF;
        final int g = buffer.get(this.getIndex(x, y, 1)) & 0xFF;
        final int b = buffer.get(this.getIndex(x, y, 2)) & 0xFF;
        final int a = buffer.get(this.getIndex(x, y, 3)) & 0xFF;
        return (r | g << 8 | b << 16 | a << 24);
    }

    public Color getPixelColor(Color dst, int x, int y) {
        return dst.seti(
            (buffer.get(this.getIndex(x, y, 0)) & 0xFF),
            (buffer.get(this.getIndex(x, y, 1)) & 0xFF),
            (buffer.get(this.getIndex(x, y, 2)) & 0xFF),
            (buffer.get(this.getIndex(x, y, 3)) & 0xFF)
        );
    }

    public PixmapRGBA getPixelColor(int x, int y, Color color) {
        color.set(
            (buffer.get(this.getIndex(x, y, 0)) & 0xFF) / 255F,
            (buffer.get(this.getIndex(x, y, 1)) & 0xFF) / 255F,
            (buffer.get(this.getIndex(x, y, 2)) & 0xFF) / 255F,
            (buffer.get(this.getIndex(x, y, 3)) & 0xFF) / 255F
        );
        return this;
    }

    // SAMPLE PIXEL
    public int samplePixel(double x, double y) {
        return this.getPixelABGR((int) (x * width), (int) (y * height));
    }

    public Color samplePixelColor(Color dst, double x, double y) {
        return this.getPixelColor(dst, (int) (x * width), (int) (y * height));
    }

    // FILL
    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, int color) {
        final float iEnd = Math.min(endX + 1, width);
        final float jEnd = Math.min(endY + 1, height);

        for(int i = Math.max(0, beginX); i < iEnd; i++)
            for(int j = Math.max(0, beginY); j < jEnd; j++)
                this.setPixel(i, j, color);
        return this;
    }

    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, Color color) {
        final float iEnd = Math.min(endX + 1, width);
        final float jEnd = Math.min(endY + 1, height);

        for(int i = Math.max(0, beginX); i < iEnd; i++)
            for(int j = Math.max(0, beginY); j < jEnd; j++)
                this.setPixel(i, j, color);
        return this;
    }

    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, double r, double g, double b, double a) {
        float iEnd = Math.min(endX + 1, width);
        float jEnd = Math.min(endY + 1, height);

        for(int i = Math.max(0, beginX); i < iEnd; i++)
            for(int j = Math.max(0, beginY); j < jEnd; j++)
                this.setPixel(i, j, r, g, b, a);
        return this;
    }

    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, int r, int g, int b, int a) {
        final float iEnd = Math.min(endX + 1, width);
        final float jEnd = Math.min(endY + 1, height);

        for(int i = Math.max(0, beginX); i < iEnd; i++)
            for(int j = Math.max(0, beginY); j < jEnd; j++)
                this.setPixel(i, j, r, g, b, a);
        return this;
    }

    // DRAW LINE
    public PixmapRGBA drawLine(int beginX, int beginY, int endX, int endY, double r, double g, double b, double a) {
        final Vec2d vec = new Vec2d(endX - beginX, endY - beginY);
        final double angle = Math.atan2(vec.y, vec.x);
        final float offsetX = Mathc.cos(angle);
        final float offsetY = Mathc.sin(angle);

        float x = beginX;
        float y = beginY;
        float length = 0;

        while(length < vec.len()){
            this.setPixel(Maths.round(x), Maths.round(y), r, g, b, a);

            x += offsetX;
            y += offsetY;
            length++;
        }
        return this;
    }

    // DRAW DOTTED LINE
    public PixmapRGBA drawDottedLine(int beginX, int beginY, int endX, int endY, double lineLength, double r, double g, double b, double a) {
        final Vec2d vec = new Vec2d(endX - beginX, endY - beginY);
        final double angle = Math.atan2(vec.y, vec.x);
        final float offsetX = Mathc.cos(angle);
        final float offsetY = Mathc.sin(angle);

        float x = beginX;
        float y = beginY;
        float length = 0;

        while(length < vec.len()){
            if(Math.sin(length / lineLength) >= 0)
                this.setPixel(Maths.round(x), Maths.round(y), r, g, b, a);

            x += offsetX;
            y += offsetY;
            length++;
        }
        return this;
    }

    // DRAW PIXMAP
    public PixmapRGBA drawPixmap(PixmapRGBA pixmap) {
        if(pixmap == null)
            return this;
        final float iEnd = Math.min(pixmap.width, width);
        final float jEnd = Math.min(pixmap.height, height);

        for(int i = 0; i < iEnd; i++)
            for(int j = 0; j < jEnd; j++)
                this.setPixel(i, j, pixmap.getPixelColor(tmp_color_2, i, j));
        return this;
    }

    public PixmapRGBA drawPixmap(PixmapRGBA pixmap, int x, int y) {
        if(pixmap == null)
            return this;
        float iEnd = (iEnd = x + pixmap.width) > width ? width : iEnd;
        float jEnd = (jEnd = y + pixmap.height) > height ? height : jEnd;

        for(int i = Math.max(0, x); i < iEnd; i++){
            for(int j = Math.max(0, y); j < jEnd; j++){
                final int px = Maths.round(i - x);
                final int py = Maths.round(j - y);

                this.setPixel(i, j, pixmap.getPixelColor(px, py));
            }
        }
        return this;
    }

    public PixmapRGBA drawPixmap(PixmapRGBA pixmap, double scaleX, double scaleY) {
        if(pixmap == null || scaleX <= 0 || scaleY <= 0)
            return this;

        final double widthScaled  = (pixmap.width  * scaleX);
        final double heightScaled = (pixmap.height * scaleY);
        final double iEnd = (widthScaled  > width  ? width  : widthScaled);
        final double jEnd = (heightScaled > height ? height : heightScaled);

        for(int i = 0; i < iEnd; i++){
            for(int j = 0; j < jEnd; j++){
                final int px = (int) (i / scaleX);
                final int py = (int) (j / scaleY);

                this.setPixel(i, j, pixmap.getPixelColor(px, py));
            }
        }
        return this;
    }

    public PixmapRGBA drawPixmap(PixmapRGBA pixmap, double scale) {
        return drawPixmap(pixmap, scale, scale);
    }

    public PixmapRGBA drawPixmap(PixmapRGBA pixmap, int x, int y, double scaleX, double scaleY) {
        if(pixmap == null || scaleX <= 0 || scaleY <= 0)
            return this;

        final double widthScaled  = (x + pixmap.width  * scaleX);
        final double heightScaled = (y + pixmap.height * scaleY);
        final double iEnd = (widthScaled  > width  ? width  : widthScaled);
        final double jEnd = (heightScaled > height ? height : heightScaled);

        for(int i = Math.max(0, x); i < iEnd; i++){
            for(int j = Math.max(0, y); j < jEnd; j++){
                final int px = (int) ((i - x) / scaleX);
                final int py = (int) ((j - y) / scaleY);

                this.setPixel(i, j, pixmap.getPixelColor(px, py));
            }
        }
        return this;
    }

    public PixmapRGBA drawPixmap(PixmapRGBA pixmap, int x, int y, double scale) {
        return this.drawPixmap(pixmap, x, y, scale, scale);
    }

    // UTILS
    public PixmapRGBA colorize(double r, double g, double b) {
        final Color color = new Color();
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                this.getPixelColor(x, y, color);
                color.set(
                    (color.red   * 0.2126 + r) / 2,
                    (color.green * 0.7152 + g) / 2,
                    (color.blue  * 0.0722 + b) / 2
                );

                this.setPixel(x, y, color);
            }
        }
        return this;
    }

    // CLEAR
    public PixmapRGBA clear(double r, double g, double b, double a) {
        for(int i = 0; i < buffer.capacity(); i += 4){
            buffer.put(i,     (byte) (r * 255));
            buffer.put(i + 1, (byte) (g * 255));
            buffer.put(i + 2, (byte) (b * 255));
            buffer.put(i + 3, (byte) (a * 255));
        }
        return this;
    }

    public PixmapRGBA clear(int r, int g, int b, int a) {
        for(int i = 0; i < buffer.capacity(); i += 4){
            buffer.put(i,     (byte) r);
            buffer.put(i + 1, (byte) g);
            buffer.put(i + 2, (byte) b);
            buffer.put(i + 3, (byte) a);
        }
        return this;
    }

    public PixmapRGBA clear(Color color) {
        for(int i = 0; i < buffer.capacity(); i += 4){
            buffer.put(i,     (byte) (color.red   * 255));
            buffer.put(i + 1, (byte) (color.green * 255));
            buffer.put(i + 2, (byte) (color.blue  * 255));
            buffer.put(i + 3, (byte) (color.alpha * 255));
        }
        return this;
    }
    
    // MIPMAPPED
    public PixmapRGBA getMipmapped() {
        final PixmapRGBA pixmap = new PixmapRGBA(width / 2, height / 2);
    
        for(int x = 0; x < pixmap.width; x++)
            for(int y = 0; y < pixmap.height; y++)
                pixmap.setPixel(x, y, 1, 0, 0, 1F);

        return pixmap;
    }

    // BUFFER
    public ByteBuffer getAlphaMultipliedBuffer() {
        final ByteBuffer buffer = this.buffer.duplicate();
        
        for(int i = 0; i < buffer.capacity(); i += 4){
            float alpha = (buffer.get(i + 3) & 0xFF) / 255F;
            
            buffer.put(i    , (byte) ((int) ((buffer.get(i    ) & 0xFF) * alpha) & 0xFF));
            buffer.put(i + 1, (byte) ((int) ((buffer.get(i + 1) & 0xFF) * alpha) & 0xFF));
            buffer.put(i + 2, (byte) ((int) ((buffer.get(i + 2) & 0xFF) * alpha) & 0xFF));
        }
        
        return buffer;
    }

    // FOREACH

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
