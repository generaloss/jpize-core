package jpize.util.pixmap;

import jpize.app.Jpize;
import jpize.util.Disposable;
import jpize.opengl.texture.GlInternalFormat;
import jpize.util.math.Maths;
import jpize.util.math.vector.Vec2i;

import java.nio.ByteBuffer;

public abstract class Pixmap implements Disposable {

    protected ByteBuffer buffer;
    protected int width;
    protected int height;
    protected int channels;

    public Pixmap(ByteBuffer buffer, int width, int height, int channels) {
        this.buffer = buffer;
        this.width = width;
        this.height = height;
        this.channels = channels;
    }

    public Pixmap(int width, int height, int channels) {
        this(Jpize.allocator.memCalloc(width * height * channels), width, height, channels);
    }

    public ByteBuffer buffer() {
        return buffer;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBufferSize() {
        return width * height * channels;
    }


    public void clear() {
        for(int i = 0; i < buffer.capacity(); i++)
            buffer.put(i, (byte) 0);
    }

    public void clearChannel(int channel, double value) {
        for(int i = 0; i < buffer.capacity(); i += channels)
            buffer.put(i + channel, (byte) (value * 255));
    }

    public boolean isOutOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= width || y >= height);
    }


    public int getPositionIndex(int x, int y) {
        return (y * width + x);
    }

    public int getChannelIndex(int positionIndex, int channel) {
        return (positionIndex * channels + channel);
    }

    public int getIndex(int x, int y, int channel) {
        final int positionIndex = this.getPositionIndex(x, y);
        return this.getChannelIndex(positionIndex, channel);
    }


    public void resize(int width, int height) {
        if(Vec2i.notEquals(width, height, this.width, this.height)){
            this.width = width;
            this.height = height;
            Jpize.allocator.memFree(buffer);
            buffer = Jpize.allocator.memCalloc(this.getBufferSize());
        }
    }

    public void set(Pixmap pixmap) {
        if(pixmap == null || pixmap.channels != channels)
            return;
        // resize
        this.resize(pixmap.width, pixmap.height);
        // copy pixels
        for(int i = 0; i < buffer.limit(); i++)
            buffer.put(i, pixmap.buffer.get(i));
    }


    public int getPixelChannel(int x, int y, int channel) {
        if(this.isOutOfBounds(x, y))
            return 0;
        return buffer.get(this.getIndex(x, y, channel)) & 0xFF;
    }

    public float getPixelChannelValue(int x, int y, int channel) {
        return this.getPixelChannel(x, y, channel) / 255F;
    }


    public void setPixelChannel(int x, int y, int channel, int value) {
        if(this.isOutOfBounds(x, y))
            return;
        buffer.put(this.getIndex(x, y, channel), (byte) (value & 0xFF));
    }

    public void setPixelChannelValue(int x, int y, int channel, double value) {
        this.setPixelChannel(x, y, channel, Maths.round(value * 255));
    }


    public abstract GlInternalFormat getFormat();

    public abstract Pixmap copy();

    @Override
    public void dispose() {
        Jpize.allocator.memFree(buffer);
    }

}
