package jpize.lwjgl.app;

import jpize.io.IAllocator;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

public class LwjglAllocator implements IAllocator {

    @Override
    public ByteBuffer memCalloc(int capacity) {
        return MemoryUtil.memCalloc(capacity);
    }

    @Override
    public void memFree(ByteBuffer buffer) {
        MemoryUtil.memFree(buffer);
    }

}
