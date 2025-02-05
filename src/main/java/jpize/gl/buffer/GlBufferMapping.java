package jpize.gl.buffer;

import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

public class GlBufferMapping {

    private final GlBuffer glBuffer;
    private final ByteBuffer mapBuffer;

    public GlBufferMapping(GlBuffer glBuffer, ByteBuffer mapBuffer) {
        this.glBuffer = glBuffer;
        this.mapBuffer = mapBuffer;
    }


    public void write(int offset, ByteBuffer buffer) {
        final long scrAddress = MemoryUtil.memAddress(buffer);
        final long dstAddress = MemoryUtil.memAddress(mapBuffer, offset);
        MemoryUtil.memCopy(scrAddress, dstAddress, buffer.remaining());
    }

    public void unmap() {
        glBuffer.unmap();
    }


    public GlBuffer getGlBuffer() {
        return glBuffer;
    }

    public ByteBuffer getMemoryBuffer() {
        return mapBuffer;
    }

}
