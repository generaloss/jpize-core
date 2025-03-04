package jpize.opengl.buffer;

///LU: import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

public class GlBufferMapping {

    private final GlBuffer glBuffer;
    private final ByteBuffer mapBuffer;

    public GlBufferMapping(GlBuffer glBuffer, ByteBuffer mapBuffer) {
        this.glBuffer = glBuffer;
        this.mapBuffer = mapBuffer;
    }


    ///LU: public void write(int offset, ByteBuffer buffer) {
    ///LU:     final long scrAddress = MemoryUtil.memAddress(buffer);
    ///LU:     final long dstAddress = MemoryUtil.memAddress(mapBuffer, offset);
    ///LU:     MemoryUtil.memCopy(scrAddress, dstAddress, buffer.remaining());
    ///LU: }

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
