package jpize.util.pixmap;

import jpize.util.Disposable;
import jpize.gl.texture.GlInternalFormat;
import jpize.util.math.vector.Vec2i;
import org.lwjgl.system.MemoryUtil;

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
        this(MemoryUtil.memCalloc(width * height * channels), width, height, channels);
    }

    public ByteBuffer getBuffer() {
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

    public boolean outOfBounds(int x, int y) {
        return x < 0 || y < 0 || x >= width || y >= height;
    }

    public int getIndex(int x, int y, int channel) {
        return (y * width + x) * channels + channel;
    }

    public int getIndex(int x, int y) {
        return (y * width + x) * channels;
    }


    public void resize(int width, int height) {
        if(!Vec2i.equals(width, height, this.width, this.height)){
            this.width = width;
            this.height = height;
            dispose();
            buffer = MemoryUtil.memCalloc(getBufferSize());
        }
    }

    public void set(Pixmap pixmap) {
        if(pixmap == null || pixmap.channels != channels)
            return;
        // resize
        resize(pixmap.width, pixmap.height);
        // copy pixels
        for(int i = 0; i < buffer.limit(); i++)
            buffer.put(i, pixmap.buffer.get(i));
    }


    public abstract GlInternalFormat getFormat();

    public abstract Pixmap copy();

    @Override
    public void dispose() {
        MemoryUtil.memFree(buffer);
    }

}
