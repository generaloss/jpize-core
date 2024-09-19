package jpize.gl.buffer;

import jpize.gl.GlObject;
import jpize.gl.texture.GlInternalFormat;
import jpize.gl.type.GlType;

import java.nio.*;

import static org.lwjgl.opengl.GL43.*;

public class GlBuffer extends GlObject {

    protected final GlBufTarget target;
    private GlBufUsage defaultUsage;

    public GlBuffer(GlBufTarget target) {
        super(glGenBuffers());
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
        allocateData(bytes, defaultUsage);
    }

    public void allocateData(int bytes) {
        allocateData(bytes, defaultUsage);
    }

    public void setData(float... data) {
        setData(data, defaultUsage);
    }

    public void setData(double... data) {
        setData(data, defaultUsage);
    }

    public void setData(int... data) {
        setData(data, defaultUsage);
    }

    public void setData(short... data) {
        setData(data, defaultUsage);
    }

    public void setData(IntBuffer data) {
        setData(data, defaultUsage);
    }

    public void setData(ByteBuffer data) {
        setData(data, defaultUsage);
    }

    public void setData(FloatBuffer data) {
        setData(data, defaultUsage);
    }

    public void setData(ShortBuffer data) {
        setData(data, defaultUsage);
    }

    public void setData(DoubleBuffer data) {
        setData(data, defaultUsage);
    }


    private GlBufferMapping createMapping(ByteBuffer mapBuffer) {
        return new GlBufferMapping(this, mapBuffer);
    }

    public GlBufferMapping mapRange(long offset, long length, GlBufAccess access) {
        bind();
        final ByteBuffer mapBuffer = glMapBufferRange(target.value, offset, length, access.value);
        return createMapping(mapBuffer);
    }

    public GlBufferMapping map(GlBufAccess access) {
        bind();
        final ByteBuffer mapBuffer = glMapBuffer(target.value, access.value);
        return createMapping(mapBuffer);
    }

    public void unmap() {
        bind();
        glUnmapBuffer(target.value);
    }


    public int getParameter(GlBufParam parameterName) {
        bind();
        return glGetBufferParameteri(target.value, parameterName.value);
    }

    public void getParameter(GlBufParam parameterName, int[] params) {
        bind();
        glGetBufferParameteriv(target.value, parameterName.value, params);
    }

    public void getParameter(GlBufParam parameterName, IntBuffer params) {
        bind();
        glGetBufferParameteriv(target.value, parameterName.value, params);
    }


    public int getSize() {
        return getParameter(GlBufParam.BUFFER_SIZE);
    }

    public void copySubData(GlBuffer buffer, long readOffset, long writeOffset, long size) {
        glBindBuffer(GL_COPY_READ_BUFFER, ID);
        glBindBuffer(GL_COPY_WRITE_BUFFER, buffer.ID);
        glCopyBufferSubData(GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, readOffset, writeOffset, size);
    }


    public void allocateData(long bytes, GlBufUsage usage) {
        bind();
        glBufferData(target.value, bytes, usage.value);
    }

    public void allocateData(int bytes, GlBufUsage usage) {
        bind();
        glBufferData(target.value, bytes, usage.value);
    }

    public void setData(int[] data, GlBufUsage usage) {
        bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(long[] data, GlBufUsage usage) {
        bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(float[] data, GlBufUsage usage) {
        bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(short[] data, GlBufUsage usage) {
        bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(double[] data, GlBufUsage usage) {
        bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(IntBuffer data, GlBufUsage usage) {
        bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(ByteBuffer data, GlBufUsage usage) {
        bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(LongBuffer data, GlBufUsage usage) {
        bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(FloatBuffer data, GlBufUsage usage) {
        bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(ShortBuffer data, GlBufUsage usage) {
        bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(DoubleBuffer data, GlBufUsage usage) {
        bind();
        glBufferData(target.value, data, usage.value);
    }


    public void setSubData(long offsetBytes, int[] data) {
        bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, long[] data) {
        bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, float[] data) {
        bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, short[] data) {
        bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, double[] data) {
        bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, IntBuffer data) {
        bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, ByteBuffer data) {
        bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, LongBuffer data) {
        bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, FloatBuffer data) {
        bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, ShortBuffer data) {
        bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, DoubleBuffer data) {
        bind();
        glBufferSubData(target.value, offsetBytes, data);
    }


    public void getSubData(long offsetBytes, int[] data) {
        bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, long[] data) {
        bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, float[] data) {
        bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, short[] data) {
        bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, double[] data) {
        bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, IntBuffer data) {
        bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, ByteBuffer data) {
        bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, LongBuffer data) {
        bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, FloatBuffer data) {
        bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, ShortBuffer data) {
        bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, DoubleBuffer data) {
        bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }


    public void clearData(GlInternalFormat format, GlType type, int... data) {
        bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, float... data) {
        bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, short... data) {
        bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, IntBuffer data) {
        bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, ByteBuffer data) {
        bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, FloatBuffer data) {
        bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, ShortBuffer data) {
        bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }


    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, int[] data) {
        bind();
        glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, float[] data) {
        bind();
        glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, short[] data) {
        bind();
        glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, IntBuffer data) {
        bind();
        glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, ByteBuffer data) {
        bind();
        glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, FloatBuffer data) {
        bind();
        glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, ShortBuffer data) {
        bind();
        glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }


    public void bind() {
        glBindBuffer(target.value, ID);
    }

    public void bind(int index) {
        glBindBufferBase(target.value, index, ID);
    }

    public void bind(int index, long offset, long size) {
        glBindBufferRange(target.value, index, ID, offset, size);
    }

    public void unbind() {
        glBindBuffer(target.value, 0);
    }

    public static void unbindAll() {
        for(GlBufTarget target: GlBufTarget.values())
            glBindBuffer(target.value, 0);
    }

    @Override
    public void dispose() {
        glDeleteBuffers(ID);
    }

}
