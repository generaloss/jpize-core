package jpize.util.pixmap;

import jpize.opengl.texture.GLInternalFormat;
import jpize.opengl.texture.Texture2D;
import jpize.util.color.Color;
import jpize.util.color.AbstractColor;
import jpize.util.math.Maths;
import jpize.util.math.vector.Vec2f;

import java.nio.ByteBuffer;
import java.util.function.BiConsumer;

public class PixmapRGBA extends Pixmap {

    private boolean blending;

    private Color tmp_blendColor1;
    private Color tmp_blendDst;
    private Color tmp_colorizeColor;
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

    public PixmapRGBA(Texture2D texture) {
        super(texture.getWidth(), texture.getHeight(), 4);
        texture.getImage(0, this);
    }


    public PixmapRGBA enableBlending() {
        this.blending = true;
        if(tmp_blendColor1 == null){
            tmp_blendColor1 = new Color();
            tmp_blendDst = new Color();
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

    protected AbstractColor tryToBlend(AbstractColor color, int x, int y) {
        if(!blending || color.getAlpha() == 1F)
            return color;

        this.getPixel(tmp_blendColor1, x, y);
        Color.blend(tmp_blendDst, tmp_blendColor1, color);
        return tmp_blendDst;
    }


    public PixmapRGBA getPixel(Color dst, int x, int y) {
        final int posIndex = super.getPositionIndex(x, y);
        dst.seti(
            (buffer.get(super.getChannelIndex(posIndex, 0)) & 0xFF),
            (buffer.get(super.getChannelIndex(posIndex, 1)) & 0xFF),
            (buffer.get(super.getChannelIndex(posIndex, 2)) & 0xFF),
            (buffer.get(super.getChannelIndex(posIndex, 3)) & 0xFF)
        );
        return this;
    }

    public int getPixelRGBA(int x, int y) {
        final int posIndex = super.getPositionIndex(x, y);
        final int r = buffer.get(super.getChannelIndex(posIndex, 0)) & 0xFF;
        final int g = buffer.get(super.getChannelIndex(posIndex, 1)) & 0xFF;
        final int b = buffer.get(super.getChannelIndex(posIndex, 2)) & 0xFF;
        final int a = buffer.get(super.getChannelIndex(posIndex, 3)) & 0xFF;
        return (r << 24 | g << 16 | b << 8 | a);
    }

    public int getPixelARGB(int x, int y) {
        final int posIndex = super.getPositionIndex(x, y);
        final int r = buffer.get(super.getChannelIndex(posIndex, 0)) & 0xFF;
        final int g = buffer.get(super.getChannelIndex(posIndex, 1)) & 0xFF;
        final int b = buffer.get(super.getChannelIndex(posIndex, 2)) & 0xFF;
        final int a = buffer.get(super.getChannelIndex(posIndex, 3)) & 0xFF;
        return (a << 24 | r << 16 | g << 8 | b);
    }

    public int getPixelABGR(int x, int y) {
        final int posIndex = super.getPositionIndex(x, y);
        final int r = buffer.get(super.getChannelIndex(posIndex, 0)) & 0xFF;
        final int g = buffer.get(super.getChannelIndex(posIndex, 1)) & 0xFF;
        final int b = buffer.get(super.getChannelIndex(posIndex, 2)) & 0xFF;
        final int a = buffer.get(super.getChannelIndex(posIndex, 3)) & 0xFF;
        return (a << 24 | b << 16 | g << 8 | r);
    }


    public PixmapRGBA setPixel(int x, int y, AbstractColor color) {
        if(super.isOutOfBounds(x, y))
            return this;

        final int posIndex = super.getPositionIndex(x, y);
        final AbstractColor blendedColor = this.tryToBlend(color, x, y);

        buffer.put(super.getChannelIndex(posIndex, 0), (byte) (Maths.round(blendedColor.getRed()   * 255) & 0xFF));
        buffer.put(super.getChannelIndex(posIndex, 1), (byte) (Maths.round(blendedColor.getGreen() * 255) & 0xFF));
        buffer.put(super.getChannelIndex(posIndex, 2), (byte) (Maths.round(blendedColor.getBlue()  * 255) & 0xFF));
        buffer.put(super.getChannelIndex(posIndex, 3), (byte) (Maths.round(blendedColor.getAlpha() * 255) & 0xFF));
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


    public PixmapRGBA setPixelRed(int x, int y, int value) {
        super.setPixelChannel(x, y, 0, value);
        return this;
    }

    public PixmapRGBA setPixelRedValue(int x, int y, double value) {
        this.setPixelChannelValue(x, y, 0, value);
        return this;
    }

    public PixmapRGBA setPixelGreen(int x, int y, int value) {
        super.setPixelChannel(x, y, 1, value);
        return this;
    }

    public PixmapRGBA setPixelGreenValue(int x, int y, double value) {
        this.setPixelChannelValue(x, y, 1, value);
        return this;
    }

    public PixmapRGBA setPixelBlue(int x, int y, int value) {
        super.setPixelChannel(x, y, 2, value);
        return this;
    }

    public PixmapRGBA setPixelBlueValue(int x, int y, double value) {
        this.setPixelChannelValue(x, y, 2, value);
        return this;
    }

    public PixmapRGBA setPixelAlpha(int x, int y, int value) {
        super.setPixelChannel(x, y, 3, value);
        return this;
    }

    public PixmapRGBA setPixelAlphaValue(int x, int y, double value) {
        this.setPixelChannelValue(x, y, 3, value);
        return this;
    }


    public PixmapRGBA clear(AbstractColor color) {
        for(int i = 0; i < buffer.capacity(); i += 4){
            buffer.put(i,     (byte) Maths.round(color.getRed()   * 255));
            buffer.put(i + 1, (byte) Maths.round(color.getGreen() * 255));
            buffer.put(i + 2, (byte) Maths.round(color.getBlue()  * 255));
            buffer.put(i + 3, (byte) Maths.round(color.getAlpha() * 255));
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
        final int x1 = Math.max(Math.min(beginX, endX), 0);
        final int y1 = Math.max(Math.min(beginY, endY), 0);
        final int x2 = Math.min(Math.max(beginX, endX), width  - 1);
        final int y2 = Math.min(Math.max(beginY, endY), height - 1);

        for(int x = x1; x <= x2; x++)
            for(int y = y1; y <= y2; y++)
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


    public PixmapRGBA samplePixel(Color dst, double x, double y) {
        return this.getPixel(dst, (int) (x * width), (int) (y * height));
    }

    public int samplePixelRGBA(double x, double y) {
        return this.getPixelRGBA((int) (x * width), (int) (y * height));
    }

    public int samplePixelARGB(double x, double y) {
        return this.getPixelARGB((int) (x * width), (int) (y * height));
    }

    public int samplePixelABGR(double x, double y) {
        return this.getPixelABGR((int) (x * width), (int) (y * height));
    }


    public PixmapRGBA drawLine(int beginX, int beginY, int endX, int endY, AbstractColor color) {
        final int dx = (endX - beginX);
        final int dy = (endY - beginY);

        final float steps = Math.max(Math.abs(dx), Math.abs(dy));
        final float increaseX = (dx / steps);
        final float increaseY = (dy / steps);

        float x = beginX;
        float y = beginY;

        for(int i = 0; i <= steps; i++) {
            this.setPixel(Maths.round(x), Maths.round(y), color);

            x += increaseX;
            y += increaseY;
        }
        return this;
    }

    public PixmapRGBA drawLine(int beginX, int beginY, int endX, int endY, float red, float green, float blue, float alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.drawLine(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA drawLine(int beginX, int beginY, int endX, int endY, double red, double green, double blue, double alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.drawLine(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA drawLinei(int beginX, int beginY, int endX, int endY, int red, int green, int blue, int alpha) {
        tmp_colorArg.seti(red, green, blue, alpha);
        return this.drawLine(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA drawLine(int beginX, int beginY, int endX, int endY, float red, float green, float blue) {
        tmp_colorArg.set(red, green, blue);
        return this.drawLine(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA drawLine(int beginX, int beginY, int endX, int endY, double red, double green, double blue) {
        tmp_colorArg.set(red, green, blue);
        return this.drawLine(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA drawLinei(int beginX, int beginY, int endX, int endY, int red, int green, int blue) {
        tmp_colorArg.seti(red, green, blue);
        return this.drawLine(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA drawLineRGB(int beginX, int beginY, int endX, int endY, int color) {
        tmp_colorArg.setRGB(color);
        return this.drawLine(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA drawLineRGBA(int beginX, int beginY, int endX, int endY, int color) {
        tmp_colorArg.setRGBA(color);
        return this.drawLine(beginX, beginY, endX, endY, tmp_colorArg);
    }

    public PixmapRGBA drawLineARGB(int beginX, int beginY, int endX, int endY, int color) {
        tmp_colorArg.setARGB(color);
        return this.drawLine(beginX, beginY, endX, endY, tmp_colorArg);
    }


    public PixmapRGBA drawLinePath(AbstractColor color, int... pointsXY) {
        for(int i = 0; i < pointsXY.length; i += 2){
            final int x1 = pointsXY[i];
            final int y1 = pointsXY[i + 1];

            final int j = (i + 2) % pointsXY.length;
            final int x2 = pointsXY[j];
            final int y2 = pointsXY[j + 1];

            this.drawLine(x1, y1, x2, y2, color);
        }
        return this;
    }

    public PixmapRGBA drawLinePath(float red, float green, float blue, float alpha, int... pointsXY) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.drawLinePath(tmp_colorArg, pointsXY);
    }

    public PixmapRGBA drawLinePath(double red, double green, double blue, double alpha, int... pointsXY) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.drawLinePath(tmp_colorArg, pointsXY);
    }

    public PixmapRGBA drawLinePathi(int red, int green, int blue, int alpha, int... pointsXY) {
        tmp_colorArg.seti(red, green, blue, alpha);
        return this.drawLinePath(tmp_colorArg, pointsXY);
    }

    public PixmapRGBA drawLinePath(float red, float green, float blue, int... pointsXY) {
        tmp_colorArg.set(red, green, blue);
        return this.drawLinePath(tmp_colorArg, pointsXY);
    }

    public PixmapRGBA drawLinePath(double red, double green, double blue, int... pointsXY) {
        tmp_colorArg.set(red, green, blue);
        return this.drawLinePath(tmp_colorArg, pointsXY);
    }

    public PixmapRGBA drawLinePathi(int red, int green, int blue, int... pointsXY) {
        tmp_colorArg.seti(red, green, blue);
        return this.drawLinePath(tmp_colorArg, pointsXY);
    }

    public PixmapRGBA drawLinePathRGB(int color, int... pointsXY) {
        tmp_colorArg.setRGB(color);
        return this.drawLinePath(tmp_colorArg, pointsXY);
    }

    public PixmapRGBA drawLinePathRGBA(int color, int... pointsXY) {
        tmp_colorArg.setRGBA(color);
        return this.drawLinePath(tmp_colorArg, pointsXY);
    }

    public PixmapRGBA drawLinePathARGB(int color, int... pointsXY) {
        tmp_colorArg.setARGB(color);
        return this.drawLinePath(tmp_colorArg, pointsXY);
    }


    public PixmapRGBA drawDottedLine(int beginX, int beginY, int endX, int endY, double lineLength, AbstractColor color) {
        final int dx = (endX - beginX);
        final int dy = (endY - beginY);

        final float steps = Math.max(Math.abs(dx), Math.abs(dy));
        final float increaseX = (dx / steps);
        final float increaseY = (dy / steps);
        final float increaseLength = Vec2f.len(increaseX, increaseY);

        float x = beginX;
        float y = beginY;
        float length = 0;

        for(int i = 0; i <= steps; i++) {
            if(length % lineLength < lineLength * 0.5F)
                this.setPixel(Maths.round(x), Maths.round(y), color);

            x += increaseX;
            y += increaseY;
            length += increaseLength;
        }
        return this;
    }

    public PixmapRGBA drawDottedLine(int beginX, int beginY, int endX, int endY, double lineLength, float red, float green, float blue, float alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.drawDottedLine(beginX, beginY, endX, endY, lineLength, tmp_colorArg);
    }

    public PixmapRGBA drawDottedLine(int beginX, int beginY, int endX, int endY, double lineLength, double red, double green, double blue, double alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.drawDottedLine(beginX, beginY, endX, endY, lineLength, tmp_colorArg);
    }

    public PixmapRGBA drawDottedLinei(int beginX, int beginY, int endX, int endY, double lineLength, int red, int green, int blue, int alpha) {
        tmp_colorArg.seti(red, green, blue, alpha);
        return this.drawDottedLine(beginX, beginY, endX, endY, lineLength, tmp_colorArg);
    }

    public PixmapRGBA drawDottedLine(int beginX, int beginY, int endX, int endY, double lineLength, float red, float green, float blue) {
        tmp_colorArg.set(red, green, blue);
        return this.drawDottedLine(beginX, beginY, endX, endY, lineLength, tmp_colorArg);
    }

    public PixmapRGBA drawDottedLine(int beginX, int beginY, int endX, int endY, double lineLength, double red, double green, double blue) {
        tmp_colorArg.set(red, green, blue);
        return this.drawDottedLine(beginX, beginY, endX, endY, lineLength, tmp_colorArg);
    }

    public PixmapRGBA drawDottedLinei(int beginX, int beginY, int endX, int endY, double lineLength, int red, int green, int blue) {
        tmp_colorArg.seti(red, green, blue);
        return this.drawDottedLine(beginX, beginY, endX, endY, lineLength, tmp_colorArg);
    }

    public PixmapRGBA drawDottedLineRGB(int beginX, int beginY, int endX, int endY, double lineLength, int color) {
        tmp_colorArg.setRGB(color);
        return this.drawDottedLine(beginX, beginY, endX, endY, lineLength, tmp_colorArg);
    }

    public PixmapRGBA drawDottedLineRGBA(int beginX, int beginY, int endX, int endY, double lineLength, int color) {
        tmp_colorArg.setRGBA(color);
        return this.drawDottedLine(beginX, beginY, endX, endY, lineLength, tmp_colorArg);
    }

    public PixmapRGBA drawDottedLineARGB(int beginX, int beginY, int endX, int endY, double lineLength, int color) {
        tmp_colorArg.setARGB(color);
        return this.drawDottedLine(beginX, beginY, endX, endY, lineLength, tmp_colorArg);
    }


    public PixmapRGBA drawDottedLinePath(AbstractColor color, double lineLength, int... pointsXY) {
        for(int i = 0; i < pointsXY.length; i += 2){
            final int x1 = pointsXY[i];
            final int y1 = pointsXY[i + 1];

            final int j = (i + 2) % pointsXY.length;
            final int x2 = pointsXY[j];
            final int y2 = pointsXY[j + 1];

            this.drawDottedLine(x1, y1, x2, y2, lineLength, color);
        }
        return this;
    }

    public PixmapRGBA drawDottedLinePath(float red, float green, float blue, float alpha, double lineLength, int... pointsXY) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.drawDottedLinePath(tmp_colorArg, lineLength, pointsXY);
    }

    public PixmapRGBA drawDottedLinePath(double red, double green, double blue, double alpha, double lineLength, int... pointsXY) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.drawDottedLinePath(tmp_colorArg, lineLength, pointsXY);
    }

    public PixmapRGBA drawDottedLinePathi(int red, int green, int blue, int alpha, double lineLength, int... pointsXY) {
        tmp_colorArg.seti(red, green, blue, alpha);
        return this.drawDottedLinePath(tmp_colorArg, lineLength, pointsXY);
    }

    public PixmapRGBA drawDottedLinePath(float red, float green, float blue, double lineLength, int... pointsXY) {
        tmp_colorArg.set(red, green, blue);
        return this.drawDottedLinePath(tmp_colorArg, lineLength, pointsXY);
    }

    public PixmapRGBA drawDottedLinePath(double red, double green, double blue, double lineLength, int... pointsXY) {
        tmp_colorArg.set(red, green, blue);
        return this.drawDottedLinePath(tmp_colorArg, lineLength, pointsXY);
    }

    public PixmapRGBA drawDottedLinePathi(int red, int green, int blue, double lineLength, int... pointsXY) {
        tmp_colorArg.seti(red, green, blue);
        return this.drawDottedLinePath(tmp_colorArg, lineLength, pointsXY);
    }

    public PixmapRGBA drawDottedLinePathRGB(int color, double lineLength, int... pointsXY) {
        tmp_colorArg.setRGB(color);
        return this.drawDottedLinePath(tmp_colorArg, lineLength, pointsXY);
    }

    public PixmapRGBA drawDottedLinePathRGBA(int color, double lineLength, int... pointsXY) {
        tmp_colorArg.setRGBA(color);
        return this.drawDottedLinePath(tmp_colorArg, lineLength, pointsXY);
    }

    public PixmapRGBA drawDottedLinePathARGB(int color, double lineLength, int... pointsXY) {
        tmp_colorArg.setARGB(color);
        return this.drawDottedLinePath(tmp_colorArg, lineLength, pointsXY);
    }


    private void drawCircleOctants(int centerX, int centerY, int x, int y, AbstractColor color) {
        this.setPixel(centerX + x, centerY + y, color);
        this.setPixel(centerX + x, centerY - y, color);
        this.setPixel(centerX - x, centerY - y, color);
        this.setPixel(centerX - x, centerY + y, color);
        this.setPixel(centerX + y, centerY + x, color);
        this.setPixel(centerX + y, centerY - x, color);
        this.setPixel(centerX - y, centerY - x, color);
        this.setPixel(centerX - y, centerY + x, color);
    }

    public PixmapRGBA drawCircle(int centerX, int centerY, int radius, AbstractColor color) {
        int x = 0;
        int y = radius;
        int d = (3 - 2 * radius);
        this.drawCircleOctants(centerX, centerY, x, y, color);

        while(y >= x){
            if(d > 0){
                y--;
                d += (x - y) * 4 + 10;
            }else{
                d += (x * 4 + 6);
            }
            x++;
            this.drawCircleOctants(centerX, centerY, x, y, color);
        }
        return this;
    }

    public PixmapRGBA drawCircle(int centerX, int centerY, int radius, float red, float green, float blue, float alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.drawCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA drawCircle(int centerX, int centerY, int radius, double red, double green, double blue, double alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.drawCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA drawCirclei(int centerX, int centerY, int radius, int red, int green, int blue, int alpha) {
        tmp_colorArg.seti(red, green, blue, alpha);
        return this.drawCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA drawCircle(int centerX, int centerY, int radius, float red, float green, float blue) {
        tmp_colorArg.set(red, green, blue);
        return this.drawCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA drawCircle(int centerX, int centerY, int radius, double red, double green, double blue) {
        tmp_colorArg.set(red, green, blue);
        return this.drawCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA drawCirclei(int centerX, int centerY, int radius, int red, int green, int blue) {
        tmp_colorArg.seti(red, green, blue);
        return this.drawCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA drawCircleRGB(int centerX, int centerY, int radius, int color) {
        tmp_colorArg.setRGB(color);
        return this.drawCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA drawCircleRGBA(int centerX, int centerY, int radius, int color) {
        tmp_colorArg.setRGBA(color);
        return this.drawCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA drawCircleARGB(int centerX, int centerY, int radius, int color) {
        tmp_colorArg.setARGB(color);
        return this.drawCircle(centerX, centerY, radius, tmp_colorArg);
    }


    private void fillCircleOctants(int centerX, int centerY, int x, int y, AbstractColor color) {
        int max_i = (centerX + x);
        int arg_y = (centerY + y);
        for(int i = centerX - x; i <= max_i; i++)
            this.setPixel(i, arg_y, color);

        arg_y = (centerY - y);
        for(int i = centerX - x; i <= max_i; i++)
            this.setPixel(i, arg_y, color);

        max_i = (centerX + y);
        arg_y = (centerY + x);
        for(int i = centerX - y; i <= max_i; i++)
            this.setPixel(i, arg_y, color);

        arg_y = (centerY - x);
        for(int i = centerX - y; i <= max_i; i++)
            this.setPixel(i, arg_y, color);
    }

    public PixmapRGBA fillCircle(int centerX, int centerY, int radius, AbstractColor color) {
        int x = 0;
        int y = radius;
        int d = (3 - 2 * radius);
        this.fillCircleOctants(centerX, centerY, x, y, color);

        while(y >= x){
            if(d > 0){
                y--;
                d += (x - y) * 4 + 10;
            }else{
                d += (x * 4 + 6);
            }
            x++;
            this.fillCircleOctants(centerX, centerY, x, y, color);
        }
        return this;
    }

    public PixmapRGBA fillCircle(int centerX, int centerY, int radius, float red, float green, float blue, float alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.fillCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA fillCircle(int centerX, int centerY, int radius, double red, double green, double blue, double alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.fillCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA fillCirclei(int centerX, int centerY, int radius, int red, int green, int blue, int alpha) {
        tmp_colorArg.seti(red, green, blue, alpha);
        return this.fillCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA fillCircle(int centerX, int centerY, int radius, float red, float green, float blue) {
        tmp_colorArg.set(red, green, blue);
        return this.fillCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA fillCircle(int centerX, int centerY, int radius, double red, double green, double blue) {
        tmp_colorArg.set(red, green, blue);
        return this.fillCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA fillCirclei(int centerX, int centerY, int radius, int red, int green, int blue) {
        tmp_colorArg.seti(red, green, blue);
        return this.fillCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA fillCircleRGB(int centerX, int centerY, int radius, int color) {
        tmp_colorArg.setRGB(color);
        return this.fillCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA fillCircleRGBA(int centerX, int centerY, int radius, int color) {
        tmp_colorArg.setRGBA(color);
        return this.fillCircle(centerX, centerY, radius, tmp_colorArg);
    }

    public PixmapRGBA fillCircleARGB(int centerX, int centerY, int radius, int color) {
        tmp_colorArg.setARGB(color);
        return this.fillCircle(centerX, centerY, radius, tmp_colorArg);
    }


    public PixmapRGBA drawPixmap(PixmapRGBA pixmap, int offsetX, int offsetY, double scaleX, double scaleY) {
        if(pixmap == null)
            return this;

        final int pixmapWidth  = (int) (pixmap.width  * scaleX + offsetX);
        final int pixmapHeight = (int) (pixmap.height * scaleY + offsetY);

        final int x1 = Math.max(offsetX, 0);
        final int y1 = Math.max(offsetY, 0);
        final int x2 = Math.min(pixmapWidth , width );
        final int y2 = Math.min(pixmapHeight, height);

        if(blending || scaleX != 1D) {
            // average implementation
            for(int x = x1; x < x2; x++){
                for(int y = y1; y < y2; y++){
                    final int px = (int) ((x - offsetX) / scaleX);
                    final int py = (int) ((y - offsetY) / scaleY);

                    pixmap.getPixel(tmp_colorArg, px, py);
                    this.setPixel(x, y, tmp_colorArg);
                }
            }
            return this;
        }

        // faster implementation
        final int sliceLength = ((x2 - offsetX) * super.channels);
        for(int y = y1; y < y2; y++){
            final int sliceIndex = pixmap.getIndex(0, Maths.round((y - offsetY) / scaleY), 0);
            final ByteBuffer slice = pixmap.buffer().slice(sliceIndex, sliceLength);

            final int index = super.getIndex(x1, y, 0);
            buffer.put(index, slice, 0, sliceLength);
        }
        return this;
    }

    public PixmapRGBA drawPixmap(PixmapRGBA pixmap, int offsetX, int offsetY, double scale) {
        return this.drawPixmap(pixmap, offsetX, offsetY, scale, scale);
    }

    public PixmapRGBA drawPixmap(PixmapRGBA pixmap, int offsetX, int offsetY) {
        return this.drawPixmap(pixmap, offsetX, offsetY, 1D, 1D);
    }

    public PixmapRGBA drawPixmap(PixmapRGBA pixmap, double scaleX, double scaleY) {
        return this.drawPixmap(pixmap, 0, 0, scaleX, scaleY);
    }

    public PixmapRGBA drawPixmap(PixmapRGBA pixmap, double scale) {
        return this.drawPixmap(pixmap, 0, 0, scale, scale);
    }

    public PixmapRGBA drawPixmap(PixmapRGBA pixmap) {
        return this.drawPixmap(pixmap, 0, 0, 1D, 1D);
    }


    public PixmapRGBA colorize(AbstractColor color) {
        if(tmp_colorizeColor == null)
            tmp_colorizeColor = new Color();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){

                this.getPixel(tmp_colorizeColor, x, y);
                final float brightness = (
                    tmp_colorizeColor.red   * 0.2126F +
                        tmp_colorizeColor.green * 0.7152F +
                        tmp_colorizeColor.blue  * 0.0722F
                );
                tmp_colorizeColor.set(
                    brightness * color.getRed(),
                    brightness * color.getGreen(),
                    brightness * color.getBlue()
                );
                this.setPixel(x, y, tmp_colorizeColor);
            }
        }
        return this;
    }

    public PixmapRGBA colorize(float red, float green, float blue, float alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.colorize(tmp_colorArg);
    }

    public PixmapRGBA colorize(double red, double green, double blue, double alpha) {
        tmp_colorArg.set(red, green, blue, alpha);
        return this.colorize(tmp_colorArg);
    }

    public PixmapRGBA colorizei(int red, int green, int blue, int alpha) {
        tmp_colorArg.seti(red, green, blue, alpha);
        return this.colorize(tmp_colorArg);
    }

    public PixmapRGBA colorize(float red, float green, float blue) {
        tmp_colorArg.set(red, green, blue);
        return this.colorize(tmp_colorArg);
    }

    public PixmapRGBA colorize(double red, double green, double blue) {
        tmp_colorArg.set(red, green, blue);
        return this.colorize(tmp_colorArg);
    }

    public PixmapRGBA colorizei(int red, int green, int blue) {
        tmp_colorArg.seti(red, green, blue);
        return this.colorize(tmp_colorArg);
    }

    public PixmapRGBA colorizeRGB(int color) {
        tmp_colorArg.setRGB(color);
        return this.colorize(tmp_colorArg);
    }

    public PixmapRGBA colorizeRGBA(int color) {
        tmp_colorArg.setRGBA(color);
        return this.colorize(tmp_colorArg);
    }

    public PixmapRGBA colorizeARGB(int color) {
        tmp_colorArg.setARGB(color);
        return this.colorize(tmp_colorArg);
    }


    public PixmapRGBA setAlphaChannel(PixmapAlpha alphaPixmap) {
        final int minWidth = Math.min(alphaPixmap.getWidth(), width);
        final int minHeight = Math.min(alphaPixmap.getHeight(), height);

        for(int x = 0; x < minWidth; x++) {
            for(int y = 0; y < minHeight; y++) {
                final int alpha = alphaPixmap.getPixel(x, y);
                this.setPixelAlpha(x, y, alpha);
            }
        }
        return this;
    }


    public PixmapRGBA forEach(BiConsumer<Integer, Integer> biConsumer) {
        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++)
                biConsumer.accept(x, y);
        return this;
    }


    @Override
    public GLInternalFormat getFormat() {
        return GLInternalFormat.RGBA8;
    }

    @Override
    public PixmapRGBA copy() {
        return new PixmapRGBA(this);
    }

}
