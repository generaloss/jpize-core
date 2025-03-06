package jpize.io;

import java.nio.ByteBuffer;

public interface IAllocator {

    ByteBuffer memCalloc(int capacity);

    void memFree(ByteBuffer buffer);

}
