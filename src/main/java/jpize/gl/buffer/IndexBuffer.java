package jpize.gl.buffer;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

public class IndexBuffer extends GlIndexBuffer {

    private GlBufUsage defaultUsage;
    private int indexCount;
    
    public IndexBuffer() {
        this.defaultUsage = GlBufUsage.STATIC_DRAW;
    }


    @Override
    public void setDefaultUsage(GlBufUsage defaultUsage) {
        this.defaultUsage = defaultUsage;
    }

    public int getIndexCount() {
        return indexCount;
    }


    public void setData(long size) {
        super.allocateData(size, defaultUsage);
    }

    @Override
    public void setData(int... data) {
        super.setData(data, defaultUsage);
        indexCount = data.length;
    }

    public void setData(long... data) {
        super.setData(data, defaultUsage);
        indexCount = data.length;
    }

    @Override
    public void setData(short... data) {
        super.setData(data, defaultUsage);
        indexCount = data.length;
    }


    @Override
    public void setData(IntBuffer data) {
        super.setData(data, defaultUsage);
        indexCount = data.limit();
    }

    @Override
    public void setData(ByteBuffer data) {
        super.setData(data, defaultUsage);
        indexCount = data.limit();
    }

    public void setData(LongBuffer data) {
        super.setData(data, defaultUsage);
        indexCount = data.limit();
    }

    @Override
    public void setData(ShortBuffer data) {
        super.setData(data, defaultUsage);
        indexCount = data.limit();
    }

}