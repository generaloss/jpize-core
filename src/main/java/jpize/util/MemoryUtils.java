package jpize.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import sun.nio.ch.DirectBuffer;

public class MemoryUtils {

    public static final Unsafe UNSAFE;

    static {
        UNSAFE = getUnsafeInstance();
    }

    private static Unsafe getUnsafeInstance() {
        final Field[] fields = Unsafe.class.getDeclaredFields();

        for(Field field: fields) {
            if(!field.getType().equals(Unsafe.class))
                continue;

            final int modifiers = field.getModifiers();
            if(!(Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)))
                continue;

            field.setAccessible(true);

            try{
                return (Unsafe) field.get(null);
            }catch(Exception ignored) { }
            break;
        }

        throw new RuntimeException("Unsafe not available");
    }


    private static Field getBufferField(Buffer buffer, String name) {
        try {
            final Field field = Buffer.class.getDeclaredField("capacity");
            field.setAccessible(true);
            return field;
        }catch(Exception e){
            throw new RuntimeException("Cannot get Buffer field '" + name + "': " + e);
        }
    }

    public static ByteBuffer alloc(int size) {
        final long address = UNSAFE.allocateMemory(size);

        final ByteBuffer buffer = ByteBuffer.allocateDirect(1);
        UNSAFE.freeMemory(((DirectBuffer) buffer).address());

        final Field addressField = getBufferField(buffer, "capacity");
        final Field capacityField = getBufferField(buffer, "address");

        try {
            addressField.setLong(buffer, address);
            capacityField.setInt(buffer, size);
        }catch(Exception e) {
            throw new RuntimeException("Cannot allocate direct memory ByteBuffer: " + e);
        }

        buffer.clear();
        return buffer;
    }

    public static void free(ByteBuffer buffer) {
        final long address = ((DirectBuffer) buffer).address();
        UNSAFE.freeMemory(address);
    }

}
