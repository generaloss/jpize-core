package jpize.context.input;

import java.nio.ByteBuffer;

public interface IAllocator {

    ByteBuffer memCalloc(int capacity);

    void memFree(ByteBuffer buffer);

}