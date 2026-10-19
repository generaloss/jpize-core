package jpize.opengl.buffer;

import jpize.context.Jpize;
import jpize.opengl.GLObject;
import jpize.opengl.gl.GL32I;
import jpize.opengl.texture.GLBaseFormat;
import jpize.opengl.texture.GLInternalFormat;
import jpize.opengl.type.GLType;

import java.nio.*;

public class GLBuffer extends GLObject {

    protected final GLBufferTarget target;
    private GLBufferUsage defaultUsage;
    private StoredType typeCache;
    private long sizeBytesCache;

    public GLBuffer(GLBufferTarget target) {
        super(Jpize.GL15.glGenBuffers());
        this.target = target;
        this.defaultUsage = GLBufferUsage.STATIC_DRAW;
    }


    public StoredType getType() { // nullable
        return typeCache;
    }


    public GLBufferUsage getDefaultUsage() {
        return defaultUsage;
    }

    public void setDefaultUsage(GLBufferUsage defaultUsage) {
        this.defaultUsage = defaultUsage;
    }


    public void allocateData(long bytes) {
        this.allocateData(bytes, defaultUsage);
    }

    public void allocateData(int bytes) {
        this.allocateData(bytes, defaultUsage);
    }

    public void setData(float... data) {
        this.setData(data, defaultUsage);
    }

    public void setData(double... data) {
        this.setData(data, defaultUsage);
    }

    public void setData(int... data) {
        this.setData(data, defaultUsage);
    }

    public void setData(short... data) {
        this.setData(data, defaultUsage);
    }

    public void setData(IntBuffer data) {
        this.setData(data, defaultUsage);
    }

    public void setData(ByteBuffer data) {
        this.setData(data, defaultUsage);
    }

    public void setData(FloatBuffer data) {
        this.setData(data, defaultUsage);
    }

    public void setData(ShortBuffer data) {
        this.setData(data, defaultUsage);
    }

    public void setData(DoubleBuffer data) {
        this.setData(data, defaultUsage);
    }


    private GLBufferMapping createMapping(ByteBuffer mapBuffer) {
        return new GLBufferMapping(this, mapBuffer);
    }

    public GLBufferMapping mapRange(long offset, long length, GLBufferAccess access) {
        this.bind();
        final ByteBuffer mapBuffer = Jpize.GL30.glMapBufferRange(target.value, offset, length, access.value);
        return createMapping(mapBuffer);
    }

    public GLBufferMapping map(GLBufferAccess access) {
        this.bind();
        final ByteBuffer mapBuffer = Jpize.GL15.glMapBuffer(target.value, access.value);
        return createMapping(mapBuffer);
    }

    public void unmap() {
        this.bind();
        Jpize.GL15.glUnmapBuffer(target.value);
    }


    public int getParameter(GLBufferParam parameterName) {
        this.bind();
        return Jpize.GL15.glGetBufferParameteri(target.value, parameterName.value);
    }

    public void getParameter(GLBufferParam parameterName, int[] params) {
        this.bind();
        Jpize.GL15.glGetBufferParameteriv(target.value, parameterName.value, params);
    }

    public void getParameter(GLBufferParam parameterName, IntBuffer params) {
        this.bind();
        Jpize.GL15.glGetBufferParameteriv(target.value, parameterName.value, params);
    }


    public int getSizeBytes() {
        return this.getParameter(GLBufferParam.SIZE);
    }

    public boolean isEmpty() {
        return (this.getSizeBytes() == 0);
    }

    public long getSizeElements() {
        if(typeCache == null)
            return 0;

        return (sizeBytesCache / typeCache.bytes);
    }


    public void copySubData(GLBuffer buffer, long readOffset, long writeOffset, long size) {
        this.bind(GLBufferTarget.COPY_READ);
        buffer.bind(GLBufferTarget.COPY_WRITE);
        Jpize.GL31.glCopyBufferSubData(GL32I.GL_COPY_READ_BUFFER, GL32I.GL_COPY_WRITE_BUFFER, readOffset, writeOffset, size);
    }


    public void allocateData(long sizeBytes, GLBufferUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, sizeBytes, usage.value);

        typeCache = null;
        sizeBytesCache = sizeBytes;
    }

    public void allocateData(int sizeBytes, GLBufferUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, sizeBytes, usage.value);

        typeCache = null;
        sizeBytesCache = sizeBytes;
    }

    public void setData(int[] data, GLBufferUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);

        typeCache = StoredType.INT;
        sizeBytesCache = (long) data.length * typeCache.bytes;
    }

    public void setData(long[] data, GLBufferUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);

        typeCache = StoredType.LONG;
        sizeBytesCache = (long) data.length * typeCache.bytes;
    }

    public void setData(float[] data, GLBufferUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);

        typeCache = StoredType.FLOAT;
        sizeBytesCache = (long) data.length * typeCache.bytes;
    }

    public void setData(short[] data, GLBufferUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);

        typeCache = StoredType.SHORT;
        sizeBytesCache = (long) data.length * typeCache.bytes;
    }

    public void setData(double[] data, GLBufferUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);

        typeCache = StoredType.DOUBLE;
        sizeBytesCache = (long) data.length * typeCache.bytes;
    }

    public void setData(IntBuffer data, GLBufferUsage usage) {
        final long remaining = data.remaining();

        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);

        typeCache = StoredType.INT;
        sizeBytesCache = remaining * typeCache.bytes;
    }

    public void setData(ByteBuffer data, GLBufferUsage usage) {
        final long remaining = data.remaining();

        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);

        typeCache = StoredType.BYTE;
        sizeBytesCache = remaining * typeCache.bytes;
    }

    public void setData(LongBuffer data, GLBufferUsage usage) {
        final long remaining = data.remaining();

        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);

        typeCache = StoredType.LONG;
        sizeBytesCache = remaining * typeCache.bytes;
    }

    public void setData(FloatBuffer data, GLBufferUsage usage) {
        final long remaining = data.remaining();

        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);

        typeCache = StoredType.FLOAT;
        sizeBytesCache = remaining * typeCache.bytes;
    }

    public void setData(ShortBuffer data, GLBufferUsage usage) {
        final long remaining = data.remaining();

        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);

        typeCache = StoredType.SHORT;
        sizeBytesCache = remaining * typeCache.bytes;
    }

    public void setData(DoubleBuffer data, GLBufferUsage usage) {
        final long remaining = data.remaining();

        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);

        typeCache = StoredType.DOUBLE;
        sizeBytesCache = remaining * typeCache.bytes;
    }


    private void checkSubData(long dataSizeBytes, long offsetBytes) {
        if (offsetBytes < 0 || dataSizeBytes > sizeBytesCache - offsetBytes) {
            throw new IndexOutOfBoundsException(
                "SubData range exceeds buffer size: offset=" + offsetBytes
                    + ", size=" + dataSizeBytes
                    + ", bufferSize=" + sizeBytesCache
            );
        }
    }


    public void setSubData(long offsetBytes, int... data) {
        if (typeCache == null)
            typeCache = StoredType.INT;
        else if (StoredType.INT != typeCache)
            throw new IllegalStateException("Buffer type mismatch: expected " + typeCache + ", got INT");

        final long dataSizeBytes = (long) data.length * Integer.BYTES;
        this.checkSubData(dataSizeBytes, offsetBytes);

        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, long... data) {
        if (typeCache == null)
            typeCache = StoredType.LONG;
        else if (StoredType.LONG != typeCache)
            throw new IllegalStateException("Buffer type mismatch: expected " + typeCache + ", got LONG");

        final long dataSizeBytes = (long) data.length * Long.BYTES;
        this.checkSubData(dataSizeBytes, offsetBytes);

        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, float... data) {
        if (typeCache == null)
            typeCache = StoredType.FLOAT;
        else if (StoredType.FLOAT != typeCache)
            throw new IllegalStateException("Buffer type mismatch: expected " + typeCache + ", got FLOAT");

        final long dataSizeBytes = (long) data.length * Float.BYTES;
        this.checkSubData(dataSizeBytes, offsetBytes);

        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, short... data) {
        if (typeCache == null)
            typeCache = StoredType.SHORT;
        else if (StoredType.SHORT != typeCache)
            throw new IllegalStateException("Buffer type mismatch: expected " + typeCache + ", got SHORT");

        final long dataSizeBytes = (long) data.length * Short.BYTES;
        this.checkSubData(dataSizeBytes, offsetBytes);

        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, double... data) {
        if (typeCache == null)
            typeCache = StoredType.DOUBLE;
        else if (StoredType.DOUBLE != typeCache)
            throw new IllegalStateException("Buffer type mismatch: expected " + typeCache + ", got DOUBLE");

        final long dataSizeBytes = (long) data.length * Double.BYTES;
        this.checkSubData(dataSizeBytes, offsetBytes);

        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, IntBuffer data) {
        if (typeCache == null)
            typeCache = StoredType.INT;
        else if (StoredType.INT != typeCache)
            throw new IllegalStateException("Buffer type mismatch: expected " + typeCache + ", got INT");

        final long dataSizeBytes = (long) data.remaining() * Integer.BYTES;
        this.checkSubData(dataSizeBytes, offsetBytes);

        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, ByteBuffer data) {
        if (typeCache == null)
            typeCache = StoredType.BYTE;
        else if (StoredType.BYTE != typeCache)
            throw new IllegalStateException("Buffer type mismatch: expected " + typeCache + ", got BYTE");

        this.checkSubData(data.remaining(), offsetBytes);

        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, LongBuffer data) {
        if (typeCache == null)
            typeCache = StoredType.LONG;
        else if (StoredType.LONG != typeCache)
            throw new IllegalStateException("Buffer type mismatch: expected " + typeCache + ", got LONG");

        final long dataSizeBytes = (long) data.remaining() * Long.BYTES;
        this.checkSubData(dataSizeBytes, offsetBytes);

        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, FloatBuffer data) {
        if (typeCache == null)
            typeCache = StoredType.FLOAT;
        else if (StoredType.FLOAT != typeCache)
            throw new IllegalStateException("Buffer type mismatch: expected " + typeCache + ", got FLOAT");

        final long dataSizeBytes = (long) data.remaining() * Float.BYTES;
        this.checkSubData(dataSizeBytes, offsetBytes);

        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, ShortBuffer data) {
        if (typeCache == null)
            typeCache = StoredType.SHORT;
        else if (StoredType.SHORT != typeCache)
            throw new IllegalStateException("Buffer type mismatch: expected " + typeCache + ", got SHORT");

        final long dataSizeBytes = (long) data.remaining() * Short.BYTES;
        this.checkSubData(dataSizeBytes, offsetBytes);

        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, DoubleBuffer data) {
        if (typeCache == null)
            typeCache = StoredType.DOUBLE;
        else if (StoredType.DOUBLE != typeCache)
            throw new IllegalStateException("Buffer type mismatch: expected " + typeCache + ", got DOUBLE");

        final long dataSizeBytes = (long) data.remaining() * Double.BYTES;
        this.checkSubData(dataSizeBytes, offsetBytes);

        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
    }


    public void getSubData(long offsetBytes, int... data) {
        this.bind();
        Jpize.GL15.glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, long... data) {
        this.bind();
        Jpize.GL15.glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, float... data) {
        this.bind();
        Jpize.GL15.glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, short... data) {
        this.bind();
        Jpize.GL15.glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, double... data) {
        this.bind();
        Jpize.GL15.glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, IntBuffer data) {
        this.bind();
        Jpize.GL15.glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, ByteBuffer data) {
        this.bind();
        Jpize.GL15.glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, LongBuffer data) {
        this.bind();
        Jpize.GL15.glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, FloatBuffer data) {
        this.bind();
        Jpize.GL15.glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, ShortBuffer data) {
        this.bind();
        Jpize.GL15.glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, DoubleBuffer data) {
        this.bind();
        Jpize.GL15.glGetBufferSubData(target.value, offsetBytes, data);
    }


    public void clearData(GLInternalFormat internalFormat, GLBaseFormat format, GLType type, int... data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, internalFormat.value, format.value, type.value, data);
    }

    public void clearData(GLInternalFormat internalFormat, GLBaseFormat format, GLType type, float... data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, internalFormat.value, format.value, type.value, data);
    }

    public void clearData(GLInternalFormat internalFormat, GLBaseFormat format, GLType type, short... data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, internalFormat.value, format.value, type.value, data);
    }

    public void clearData(GLInternalFormat internalFormat, GLBaseFormat format, GLType type, IntBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, internalFormat.value, format.value, type.value, data);
    }

    public void clearData(GLInternalFormat internalFormat, GLBaseFormat format, GLType type, ByteBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, internalFormat.value, format.value, type.value, data);
    }

    public void clearData(GLInternalFormat internalFormat, GLBaseFormat format, GLType type, FloatBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, internalFormat.value, format.value, type.value, data);
    }

    public void clearData(GLInternalFormat internalFormat, GLBaseFormat format, GLType type, ShortBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, internalFormat.value, format.value, type.value, data);
    }


    public void clearSubData(GLInternalFormat format, GLType type, long offset, long size, int... data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GLInternalFormat format, GLType type, long offset, long size, float... data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GLInternalFormat format, GLType type, long offset, long size, short... data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GLInternalFormat format, GLType type, long offset, long size, IntBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GLInternalFormat format, GLType type, long offset, long size, ByteBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GLInternalFormat format, GLType type, long offset, long size, FloatBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GLInternalFormat format, GLType type, long offset, long size, ShortBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }


    public void bind(GLBufferTarget target) {
        Jpize.GL15.glBindBuffer(target.value, ID);
    }

    public void bind() {
        this.bind(target);
    }

    public void bind(int index) {
        Jpize.GL30.glBindBufferBase(target.value, index, ID);
    }

    public void bind(int index, long offset, long size) {
        Jpize.GL30.glBindBufferRange(target.value, index, ID, offset, size);
    }

    public void unbind() {
        Jpize.GL30.glBindBuffer(target.value, 0);
    }

    public static void unbindAll() {
        for(GLBufferTarget target: GLBufferTarget.values())
            Jpize.GL15.glBindBuffer(target.value, 0);
    }

    @Override
    public void dispose() {
        Jpize.GL15.glDeleteBuffers(ID);
    }

}