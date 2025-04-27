package jpize.opengl.buffer;

import jpize.context.Jpize;
import jpize.opengl.GlObject;
import jpize.opengl.gl.GLI32;
import jpize.opengl.texture.GlInternalFormat;
import jpize.opengl.type.GlType;

import java.nio.*;

public class GlBuffer extends GlObject {

    protected final GlBufTarget target;
    private GlBufUsage defaultUsage;
    private int typeBytes;

    public GlBuffer(GlBufTarget target) {
        super(Jpize.GL15.glGenBuffers());
        this.target = target;
        this.defaultUsage = GlBufUsage.STATIC_DRAW;
    }


    public GlBufUsage getDefaultUsage() {
        return defaultUsage;
    }

    public void setDefaultUsage(GlBufUsage defaultUsage) {
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


    private GlBufferMapping createMapping(ByteBuffer mapBuffer) {
        return new GlBufferMapping(this, mapBuffer);
    }

    public GlBufferMapping mapRange(long offset, long length, GlBufAccess access) {
        this.bind();
        final ByteBuffer mapBuffer = Jpize.GL30.glMapBufferRange(target.value, offset, length, access.value);
        return createMapping(mapBuffer);
    }

    public GlBufferMapping map(GlBufAccess access) {
        this.bind();
        final ByteBuffer mapBuffer = Jpize.GL15.glMapBuffer(target.value, access.value);
        return createMapping(mapBuffer);
    }

    public void unmap() {
        this.bind();
        Jpize.GL15.glUnmapBuffer(target.value);
    }


    public int getParameter(GlBufParam parameterName) {
        this.bind();
        return Jpize.GL15.glGetBufferParameteri(target.value, parameterName.value);
    }

    public void getParameter(GlBufParam parameterName, int[] params) {
        this.bind();
        Jpize.GL15.glGetBufferParameteriv(target.value, parameterName.value, params);
    }

    public void getParameter(GlBufParam parameterName, IntBuffer params) {
        this.bind();
        Jpize.GL15.glGetBufferParameteriv(target.value, parameterName.value, params);
    }


    public int getSizeBytes() {
        return this.getParameter(GlBufParam.BUFFER_SIZE);
    }

    public int getSizeElements() {
        if(typeBytes == 0)
            return 0;

        final int sizeBytes = this.getSizeBytes();
        return (sizeBytes / typeBytes);
    }


    public void copySubData(GlBuffer buffer, long readOffset, long writeOffset, long size) {
        Jpize.GL15.glBindBuffer(GLI32.GL_COPY_READ_BUFFER, ID);
        Jpize.GL15.glBindBuffer(GLI32.GL_COPY_WRITE_BUFFER, buffer.ID);
        Jpize.GL31.glCopyBufferSubData(GLI32.GL_COPY_READ_BUFFER, GLI32.GL_COPY_WRITE_BUFFER, readOffset, writeOffset, size);
    }


    public void allocateData(long bytes, GlBufUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, bytes, usage.value);
        typeBytes = 0;
    }

    public void allocateData(int bytes, GlBufUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, bytes, usage.value);
        typeBytes = 0;
    }

    public void setData(int[] data, GlBufUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);
        typeBytes = Integer.BYTES;
    }

    public void setData(long[] data, GlBufUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);
        typeBytes = Long.BYTES;
    }

    public void setData(float[] data, GlBufUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);
        typeBytes = Float.BYTES;
    }

    public void setData(short[] data, GlBufUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);
        typeBytes = Short.BYTES;
    }

    public void setData(double[] data, GlBufUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);
        typeBytes = Double.BYTES;
    }

    public void setData(IntBuffer data, GlBufUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);
        typeBytes = Integer.BYTES;
    }

    public void setData(ByteBuffer data, GlBufUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);
        typeBytes = Byte.BYTES;
    }

    public void setData(LongBuffer data, GlBufUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);
        typeBytes = Long.BYTES;
    }

    public void setData(FloatBuffer data, GlBufUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);
        typeBytes = Float.BYTES;
    }

    public void setData(ShortBuffer data, GlBufUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);
        typeBytes = Short.BYTES;
    }

    public void setData(DoubleBuffer data, GlBufUsage usage) {
        this.bind();
        Jpize.GL15.glBufferData(target.value, data, usage.value);
        typeBytes = Double.BYTES;
    }


    public void setSubData(long offsetBytes, int... data) {
        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
        typeBytes = Integer.BYTES;
    }

    public void setSubData(long offsetBytes, long... data) {
        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
        typeBytes = Long.BYTES;
    }

    public void setSubData(long offsetBytes, float... data) {
        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
        typeBytes = Float.BYTES;
    }

    public void setSubData(long offsetBytes, short... data) {
        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
        typeBytes = Short.BYTES;
    }

    public void setSubData(long offsetBytes, double... data) {
        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
        typeBytes = Double.BYTES;
    }

    public void setSubData(long offsetBytes, IntBuffer data) {
        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
        typeBytes = Integer.BYTES;
    }

    public void setSubData(long offsetBytes, ByteBuffer data) {
        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
        typeBytes = Byte.BYTES;
    }

    public void setSubData(long offsetBytes, LongBuffer data) {
        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
        typeBytes = Long.BYTES;
    }

    public void setSubData(long offsetBytes, FloatBuffer data) {
        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
        typeBytes = Float.BYTES;
    }

    public void setSubData(long offsetBytes, ShortBuffer data) {
        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
        typeBytes = Short.BYTES;
    }

    public void setSubData(long offsetBytes, DoubleBuffer data) {
        this.bind();
        Jpize.GL15.glBufferSubData(target.value, offsetBytes, data);
        typeBytes = Double.BYTES;
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


    public void clearData(GlInternalFormat format, GlType type, int... data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, float... data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, short... data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, IntBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, ByteBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, FloatBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, ShortBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }


    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, int... data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, float... data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, short... data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, IntBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, ByteBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, FloatBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, ShortBuffer data) {
        this.bind();
        Jpize.GL43.glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }


    public void bind() {
        Jpize.GL15.glBindBuffer(target.value, ID);
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
        for(GlBufTarget target: GlBufTarget.values())
            Jpize.GL15.glBindBuffer(target.value, 0);
    }

    @Override
    public void dispose() {
        Jpize.GL15.glDeleteBuffers(ID);
    }

}
