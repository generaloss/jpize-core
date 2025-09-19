package jpize.util;

import java.nio.ByteBuffer;

public class MemoryUtils {

    public static ByteBuffer alloc(int size) {
        return BufferUtils.createByteBuffer(size);
    }

    public static void free(ByteBuffer buffer) {
        // TODO
    }

}
