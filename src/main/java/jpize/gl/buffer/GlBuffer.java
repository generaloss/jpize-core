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
        final ByteBuffer mapBuffer = glMapBufferRange(target.value, offset, length, access.value);
        return createMapping(mapBuffer);
    }

    public GlBufferMapping map(GlBufAccess access) {
        this.bind();
        final ByteBuffer mapBuffer = glMapBuffer(target.value, access.value);
        return createMapping(mapBuffer);
    }

    public void unmap() {
        this.bind();
        glUnmapBuffer(target.value);
    }


    public int getParameter(GlBufParam parameterName) {
        this.bind();
        return glGetBufferParameteri(target.value, parameterName.value);
    }

    public void getParameter(GlBufParam parameterName, int[] params) {
        this.bind();
        glGetBufferParameteriv(target.value, parameterName.value, params);
    }

    public void getParameter(GlBufParam parameterName, IntBuffer params) {
        this.bind();
        glGetBufferParameteriv(target.value, parameterName.value, params);
    }


    public int getSize() {
        return this.getParameter(GlBufParam.BUFFER_SIZE);
    }

    public void copySubData(GlBuffer buffer, long readOffset, long writeOffset, long size) {
        glBindBuffer(GL_COPY_READ_BUFFER, ID);
        glBindBuffer(GL_COPY_WRITE_BUFFER, buffer.ID);
        glCopyBufferSubData(GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, readOffset, writeOffset, size);
    }


    public void allocateData(long bytes, GlBufUsage usage) {
        this.bind();
        glBufferData(target.value, bytes, usage.value);
    }

    public void allocateData(int bytes, GlBufUsage usage) {
        this.bind();
        glBufferData(target.value, bytes, usage.value);
    }

    public void setData(int[] data, GlBufUsage usage) {
        this.bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(long[] data, GlBufUsage usage) {
        this.bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(float[] data, GlBufUsage usage) {
        this.bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(short[] data, GlBufUsage usage) {
        this.bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(double[] data, GlBufUsage usage) {
        this.bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(IntBuffer data, GlBufUsage usage) {
        this.bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(ByteBuffer data, GlBufUsage usage) {
        this.bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(LongBuffer data, GlBufUsage usage) {
        this.bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(FloatBuffer data, GlBufUsage usage) {
        this.bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(ShortBuffer data, GlBufUsage usage) {
        this.bind();
        glBufferData(target.value, data, usage.value);
    }

    public void setData(DoubleBuffer data, GlBufUsage usage) {
        this.bind();
        glBufferData(target.value, data, usage.value);
    }


    public void setSubData(long offsetBytes, int... data) {
        this.bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, long... data) {
        this.bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, float... data) {
        this.bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, short... data) {
        this.bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, double... data) {
        this.bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, IntBuffer data) {
        this.bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, ByteBuffer data) {
        this.bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, LongBuffer data) {
        this.bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, FloatBuffer data) {
        this.bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, ShortBuffer data) {
        this.bind();
        glBufferSubData(target.value, offsetBytes, data);
    }

    public void setSubData(long offsetBytes, DoubleBuffer data) {
        this.bind();
        glBufferSubData(target.value, offsetBytes, data);
    }


    public void getSubData(long offsetBytes, int... data) {
        this.bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, long... data) {
        this.bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, float... data) {
        this.bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, short... data) {
        this.bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, double... data) {
        this.bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, IntBuffer data) {
        this.bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, ByteBuffer data) {
        this.bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, LongBuffer data) {
        this.bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, FloatBuffer data) {
        this.bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, ShortBuffer data) {
        this.bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }

    public void getSubData(long offsetBytes, DoubleBuffer data) {
        this.bind();
        glGetBufferSubData(target.value, offsetBytes, data);
    }


    public void clearData(GlInternalFormat format, GlType type, int... data) {
        this.bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, float... data) {
        this.bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, short... data) {
        this.bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, IntBuffer data) {
        this.bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, ByteBuffer data) {
        this.bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, FloatBuffer data) {
        this.bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }

    public void clearData(GlInternalFormat format, GlType type, ShortBuffer data) {
        this.bind();
        glClearBufferData(target.value, format.value, format.base.value, type.value, data);
    }


    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, int... data) {
        this.bind();
        glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, float... data) {
        this.bind();
        glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, short... data) {
        this.bind();
        glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, IntBuffer data) {
        this.bind();
        glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, ByteBuffer data) {
        this.bind();
        glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, FloatBuffer data) {
        this.bind();
        glClearBufferSubData(target.value, format.value, offset, size, format.base.value, type.value, data);
    }

    public void clearSubData(GlInternalFormat format, GlType type, long offset, long size, ShortBuffer data) {
        this.bind();
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
