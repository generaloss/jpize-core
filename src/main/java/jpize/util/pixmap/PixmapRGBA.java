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
        if(outOfBounds(x, y))
            return this;

        final Color blendColor = blend(
            getPixelColor(x, y),
            new Color(color >> 24 & 0xFF, color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF)
        );
        buffer.put(getIndex(x, y, 0), (byte) ((color >> 24) & 0xFF));
        buffer.put(getIndex(x, y, 1), (byte) ((color >> 16) & 0xFF));
        buffer.put(getIndex(x, y, 2), (byte) ((color >> 8) & 0xFF));
        buffer.put(getIndex(x, y, 3), (byte) ((color) & 0xFF));
        return this;
    }

    public PixmapRGBA setPixel(int x, int y, Color color) {
        if(outOfBounds(x, y))
            return this;

        final Color blendColor = blend(getPixelColor(x, y), color);
        buffer.put(getIndex(x, y, 0), (byte) ((int) (blendColor.r * 255) & 0xFF));
        buffer.put(getIndex(x, y, 1), (byte) ((int) (blendColor.g * 255) & 0xFF));
        buffer.put(getIndex(x, y, 2), (byte) ((int) (blendColor.b * 255) & 0xFF));
        buffer.put(getIndex(x, y, 3), (byte) ((int) (blendColor.a * 255) & 0xFF));
        return this;
    }

    public PixmapRGBA setPixel(int x, int y, double r, double g, double b, double a) {
        if(outOfBounds(x, y))
            return this;

        Color blendColor = blend(getPixelColor(x, y), new Color(r, g, b, a));
        buffer.put(getIndex(x, y, 0), (byte) ((int) (blendColor.r * 255) & 0xFF));
        buffer.put(getIndex(x, y, 1), (byte) ((int) (blendColor.g * 255) & 0xFF));
        buffer.put(getIndex(x, y, 2), (byte) ((int) (blendColor.b * 255) & 0xFF));
        buffer.put(getIndex(x, y, 3), (byte) ((int) (blendColor.a * 255) & 0xFF));
        return this;
    }

    public PixmapRGBA setPixel(int x, int y, int r, int g, int b, int a) {
        if(outOfBounds(x, y))
            return this;

        Color blendColor = blend(getPixelColor(x, y), new Color(r, g, b, a));
        buffer.put(getIndex(x, y, 0), (byte) (r & 0xFF));
        buffer.put(getIndex(x, y, 1), (byte) (g & 0xFF));
        buffer.put(getIndex(x, y, 2), (byte) (b & 0xFF));
        buffer.put(getIndex(x, y, 3), (byte) (a & 0xFF));
        return this;
    }

    public PixmapRGBA setPixel(int x, int y, double r, double g, double b) {
        if(outOfBounds(x, y))
            return this;

        Color blendColor = blend(getPixelColor(x, y), new Color(r, g, b));
        buffer.put(getIndex(x, y, 0), (byte) ((int) (blendColor.r * 255) & 0xFF));
        buffer.put(getIndex(x, y, 1), (byte) ((int) (blendColor.g * 255) & 0xFF));
        buffer.put(getIndex(x, y, 2), (byte) ((int) (blendColor.b * 255) & 0xFF));
        buffer.put((byte) 255);
        return this;
    }

    public PixmapRGBA setPixel(int x, int y, int r, int g, int b) {
        if(outOfBounds(x, y))
            return this;

        Color blendColor = blend(getPixelColor(x, y), new Color(r, g, b));
        buffer.put(getIndex(x, y, 0), (byte) (r & 0xFF));
        buffer.put(getIndex(x, y, 1), (byte) (g & 0xFF));
        buffer.put(getIndex(x, y, 2), (byte) (b & 0xFF));
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
            color1.r * (1 - color2.a) + color2.r * (color2.a),
            color1.g * (1 - color2.a) + color2.g * (color2.a),
            color1.b * (1 - color2.a) + color2.b * (color2.a),
            Math.max(color1.a, color2.a)
        );

        return color1;
    }

    private Color blend(Color color1, double r2, double g2, double b2, double a2) {
        if(!blending)
            return new Color(r2, g2, b2, a2);

        color1.set(
            color1.r * (1 - a2) + r2 * (a2),
            color1.g * (1 - a2) + g2 * (a2),
            color1.b * (1 - a2) + b2 * (a2),
            Math.max(color1.a, a2)
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
        int r = buffer.get(getIndex(x, y, 0)) & 0xFF;
        int g = buffer.get(getIndex(x, y, 1)) & 0xFF;
        int b = buffer.get(getIndex(x, y, 2)) & 0xFF;
        int a = buffer.get(getIndex(x, y, 3)) & 0xFF;
        return r << 24 | g << 16 | b << 8 | a;
    }
    
    public int getPixelBGRA(int x, int y) {
        int r = buffer.get(getIndex(x, y, 0)) & 0xFF;
        int g = buffer.get(getIndex(x, y, 1)) & 0xFF;
        int b = buffer.get(getIndex(x, y, 2)) & 0xFF;
        int a = buffer.get(getIndex(x, y, 3)) & 0xFF;
        return a << 24 | r << 16 | g << 8 | b;
    }
    
    public int getPixelRGBA(int x, int y) {
        int r = buffer.get(getIndex(x, y, 0)) & 0xFF;
        int g = buffer.get(getIndex(x, y, 1)) & 0xFF;
        int b = buffer.get(getIndex(x, y, 2)) & 0xFF;
        int a = buffer.get(getIndex(x, y, 3)) & 0xFF;
        return a << 24 | b << 16 | g << 8 | r;
    }

    public Color getPixelColor(int x, int y) {
        return new Color(
            (buffer.get(getIndex(x, y, 0)) & 0xFF) / 255F,
            (buffer.get(getIndex(x, y, 1)) & 0xFF) / 255F,
            (buffer.get(getIndex(x, y, 2)) & 0xFF) / 255F,
            (buffer.get(getIndex(x, y, 3)) & 0xFF) / 255F
        );
    }

    public PixmapRGBA getPixelColor(int x, int y, Color color) {
        color.set(
            (buffer.get(getIndex(x, y, 0)) & 0xFF) / 255F,
            (buffer.get(getIndex(x, y, 1)) & 0xFF) / 255F,
            (buffer.get(getIndex(x, y, 2)) & 0xFF) / 255F,
            (buffer.get(getIndex(x, y, 3)) & 0xFF) / 255F
        );
        return this;
    }

    // SAMPLE PIXEL
    public int samplePixel(double x, double y) {
        return getPixelABGR((int) (x * width), (int) (y * height));
    }

    public Color samplePixelColor(double x, double y) {
        return getPixelColor((int) (x * width), (int) (y * height));
    }

    // FILL
    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, int color) {
        float iEnd = Math.min(endX + 1, width);
        float jEnd = Math.min(endY + 1, height);

        for(int i = Math.max(0, beginX); i < iEnd; i++)
            for(int j = Math.max(0, beginY); j < jEnd; j++)
                setPixel(i, j, color);
        return this;
    }

    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, Color color) {
        float iEnd = Math.min(endX + 1, width);
        float jEnd = Math.min(endY + 1, height);

        for(int i = Math.max(0, beginX); i < iEnd; i++)
            for(int j = Math.max(0, beginY); j < jEnd; j++)
                setPixel(i, j, color);
        return this;
    }

    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, double r, double g, double b, double a) {
        float iEnd = Math.min(endX + 1, width);
        float jEnd = Math.min(endY + 1, height);

        for(int i = Math.max(0, beginX); i < iEnd; i++)
            for(int j = Math.max(0, beginY); j < jEnd; j++)
                setPixel(i, j, r, g, b, a);
        return this;
    }

    public PixmapRGBA fill(int beginX, int beginY, int endX, int endY, int r, int g, int b, int a) {
        float iEnd = Math.min(endX + 1, width);
        float jEnd = Math.min(endY + 1, height);

        for(int i = Math.max(0, beginX); i < iEnd; i++)
            for(int j = Math.max(0, beginY); j < jEnd; j++)
                setPixel(i, j, r, g, b, a);
        return this;
    }

    // DRAW LINE
    public PixmapRGBA drawLine(int beginX, int beginY, int endX, int endY, double r, double g, double b, double a) {
        Vec2d vec = new Vec2d(endX - beginX, endY - beginY);
        double angle = Math.atan2(vec.y, vec.x);
        float offsetX = Mathc.cos(angle);
        float offsetY = Mathc.sin(angle);

        float x = beginX;
        float y = beginY;
        float length = 0;

        while(length < vec.len()){
            setPixel(Maths.round(x), Maths.round(y), r, g, b, a);

            x += offsetX;
            y += offsetY;
            length++;
        }
        return this;
    }

    // DRAW DOTTED LINE
    public PixmapRGBA drawDottedLine(int beginX, int beginY, int endX, int endY, double lineLength, double r, double g, double b, double a) {
        Vec2d vec = new Vec2d(endX - beginX, endY - beginY);
        double angle = Math.atan2(vec.y, vec.x);
        float offsetX = Mathc.cos(angle);
        float offsetY = Mathc.sin(angle);

        float x = beginX;
        float y = beginY;
        float length = 0;

        while(length < vec.len()){
            if(Math.sin(length / lineLength) >= 0)
                setPixel(Maths.round(x), Maths.round(y), r, g, b, a);

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
                setPixel(i, j, pixmap.getPixelColor(i, j));
        return this;
    }

    public PixmapRGBA drawPixmap(PixmapRGBA pixmap, int x, int y) {
        if(pixmap == null)
            return this;
        float iEnd = (iEnd = x + pixmap.width) > width ? width : iEnd;
        float jEnd = (jEnd = y + pixmap.height) > height ? height : jEnd;

        for(int i = Math.max(0, x); i < iEnd; i++){
            for(int j = Math.max(0, y); j < jEnd; j++){
                int px = Maths.round(i - x);
                int py = Maths.round(j - y);

                setPixel(i, j, pixmap.getPixelColor(px, py));
            }
        }
        return this;
    }

    public PixmapRGBA drawPixmap(PixmapRGBA pixmap, double scaleX, double scaleY) {
        if(pixmap == null || scaleX <= 0 || scaleY <= 0)
            return this;
        double iEnd = (iEnd = pixmap.width * scaleX) > width ? width : iEnd;
        double jEnd = (jEnd = pixmap.height * scaleY) > height ? height : jEnd;

        for(int i = 0; i < iEnd; i++){
            for(int j = 0; j < jEnd; j++){
                int px = (int) (i / scaleX);
                int py = (int) (j / scaleY);

                setPixel(i, j, pixmap.getPixelColor(px, py));
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
        double iEnd = (iEnd = x + pixmap.width * scaleX) > width ? width : iEnd;
        double jEnd = (jEnd = y + pixmap.height * scaleY) > height ? height : jEnd;

        for(int i = Math.max(0, x); i < iEnd; i++){
            for(int j = Math.max(0, y); j < jEnd; j++){
                int px = (int) ((i - x) / scaleX);
                int py = (int) ((j - y) / scaleY);

                setPixel(i, j, pixmap.getPixelColor(px, py));
            }
        }
        return this;
    }

    public PixmapRGBA drawPixmap(PixmapRGBA pixmap, int x, int y, double scale) {
        return drawPixmap(pixmap, x, y, scale, scale);
    }

    // UTILS
    public PixmapRGBA colorize(double r, double g, double b) {
        final Color color = new Color();
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                getPixelColor(x, y, color);
                color.set(
                    (color.r * 0.2126 + r) / 2,
                    (color.g * 0.7152 + g) / 2,
                    (color.b * 0.0722 + b) / 2
                );

                setPixel(x, y, color);
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
            buffer.put(i,     (byte) (color.r * 255));
            buffer.put(i + 1, (byte) (color.g * 255));
            buffer.put(i + 2, (byte) (color.b * 255));
            buffer.put(i + 3, (byte) (color.a * 255));
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
