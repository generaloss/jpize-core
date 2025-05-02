package jpize.opengl.buffer;

import java.nio.ByteBuffer;

public class GLBufferMapping {

    private final GLBuffer glBuffer;
    private final ByteBuffer mapBuffer;

    public GLBufferMapping(GLBuffer glBuffer, ByteBuffer mapBuffer) {
        this.glBuffer = glBuffer;
        this.mapBuffer = mapBuffer;
    }

    public GLBuffer getGlBuffer() {
        return glBuffer;
    }

    public ByteBuffer getMemoryBuffer() {
        return mapBuffer;
    }


    /// LU: public void write(int offset, ByteBuffer buffer) {
    /// LU:     final long scrAddress = MemoryUtil.memAddress(buffer);
    /// LU:     final long dstAddress = MemoryUtil.memAddress(mapBuffer, offset);
    /// LU:     MemoryUtil.memCopy(scrAddress, dstAddress, buffer.remaining());
    /// LU: }


    public void unmap() {
        glBuffer.unmap();
    }

}
