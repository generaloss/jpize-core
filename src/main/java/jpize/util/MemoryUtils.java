package jpize.util;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

public class MemoryUtils {

    // public static final Unsafe UNSAFE;

    // static {
    //     UNSAFE = getUnsafeInstance();
    // }

    // private static Unsafe getUnsafeInstance() {
    //     final Field[] fields = Unsafe.class.getDeclaredFields();

    //     for(Field field: fields) {
    //         if(!field.getType().equals(Unsafe.class))
    //             continue;

    //         final int modifiers = field.getModifiers();
    //         if(!(Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)))
    //             continue;

    //         field.setAccessible(true);

    //         try{
    //             return (Unsafe) field.get(null);
    //         }catch(Exception ignored) { }
    //         break;
    //     }

    //     throw new RuntimeException("Unsafe not available");
    // }


    // private static Field getBufferField(Buffer buffer, String name) {
    //     try {
    //         final Field field = Buffer.class.getDeclaredField("capacity");
    //         field.setAccessible(true);
    //         return field;
    //     }catch(Exception e){
    //         throw new RuntimeException("Cannot get Buffer field '" + name + "': " + e);
    //     }
    // }

    public static ByteBuffer alloc(int size) {
        // final long address = UNSAFE.allocateMemory(size);

        // final ByteBuffer buffer = ByteBuffer.allocateDirect(1);
        // UNSAFE.freeMemory(address(buffer));

        // final Field addressField = getBufferField(buffer, "capacity");
        // final Field capacityField = getBufferField(buffer, "address");

        // try {
        //     addressField.setLong(buffer, address);
        //     capacityField.setInt(buffer, size);
        // }catch(Exception e) {
        //     throw new RuntimeException("Cannot allocate direct memory ByteBuffer: " + e);
        // }

        // buffer.clear();
        // return buffer;
        return BufferUtils.createByteBuffer(size);
    }

    public static long address(ByteBuffer buffer) {
        final DirectBuffer directBuffer = ((DirectBuffer) buffer);
        return directBuffer.address();
    }

    public static void free(ByteBuffer buffer) {
        // final long address = address(buffer);
        // UNSAFE.freeMemory(address);
    }

}
