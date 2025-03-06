package jpize.lwjgl.opengl;

import jpize.opengl.gl.GLI15;
import org.lwjgl.opengl.GL15;
import java.nio.*;

public class LwjglGL15 extends LwjglGL14 implements GLI15 {

    @Override
    public void glBindBuffer(int target, int buffer) {
        GL15.glBindBuffer(target, buffer);
    }

    @Override
    public void nglDeleteBuffers(int n, long buffers) {
        GL15.nglDeleteBuffers(n, buffers);
    }

    @Override
    public void glDeleteBuffers(IntBuffer buffers) {
        GL15.glDeleteBuffers(buffers);
    }

    @Override
    public void glDeleteBuffers(int buffer) {
        GL15.glDeleteBuffers(buffer);
    }

    @Override
    public void nglGenBuffers(int n, long buffers) {
        GL15.nglGenBuffers(n, buffers);
    }

    @Override
    public void glGenBuffers(IntBuffer buffers) {
        GL15.glGenBuffers(buffers);
    }

    @Override
    public int glGenBuffers() {
        return GL15.glGenBuffers();
    }

    @Override
    public boolean glIsBuffer(int buffer) {
        return GL15.glIsBuffer(buffer);
    }

    @Override
    public void nglBufferData(int target, long size, long data, int usage) {
        GL15.nglBufferData(target, size, data, usage);
    }

    @Override
    public void glBufferData(int target, long size, int usage) {
        GL15.glBufferData(target, size, usage);
    }

    @Override
    public void glBufferData(int target, ByteBuffer data, int usage) {
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void glBufferData(int target, ShortBuffer data, int usage) {
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void glBufferData(int target, IntBuffer data, int usage) {
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void glBufferData(int target, LongBuffer data, int usage) {
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void glBufferData(int target, FloatBuffer data, int usage) {
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void glBufferData(int target, DoubleBuffer data, int usage) {
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void nglBufferSubData(int target, long offset, long size, long data) {
        GL15.nglBufferSubData(target, offset, size, data);
    }

    @Override
    public void glBufferSubData(int target, long offset, ByteBuffer data) {
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void glBufferSubData(int target, long offset, ShortBuffer data) {
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void glBufferSubData(int target, long offset, IntBuffer data) {
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void glBufferSubData(int target, long offset, LongBuffer data) {
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void glBufferSubData(int target, long offset, FloatBuffer data) {
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void glBufferSubData(int target, long offset, DoubleBuffer data) {
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void nglGetBufferSubData(int target, long offset, long size, long data) {
        GL15.nglGetBufferSubData(target, offset, size, data);
    }

    @Override
    public void glGetBufferSubData(int target, long offset, ByteBuffer data) {
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void glGetBufferSubData(int target, long offset, ShortBuffer data) {
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void glGetBufferSubData(int target, long offset, IntBuffer data) {
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void glGetBufferSubData(int target, long offset, LongBuffer data) {
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void glGetBufferSubData(int target, long offset, FloatBuffer data) {
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void glGetBufferSubData(int target, long offset, DoubleBuffer data) {
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public long nglMapBuffer(int target, int access) {
        return GL15.nglMapBuffer(target, access);
    }

    @Override
    public ByteBuffer glMapBuffer(int target, int access) {
        return GL15.glMapBuffer(target, access);
    }

    @Override
    public ByteBuffer glMapBuffer(int target, int access, ByteBuffer old_buffer) {
        return GL15.glMapBuffer(target, access, old_buffer);
    }

    @Override
    public ByteBuffer glMapBuffer(int target, int access, long length, ByteBuffer old_buffer) {
        return GL15.glMapBuffer(target, access, length, old_buffer);
    }

    @Override
    public boolean glUnmapBuffer(int target) {
        return GL15.glUnmapBuffer(target);
    }

    @Override
    public void nglGetBufferParameteriv(int target, int pname, long params) {
        GL15.nglGetBufferParameteriv(target, pname, params);
    }

    @Override
    public void glGetBufferParameteriv(int target, int pname, IntBuffer params) {
        GL15.glGetBufferParameteriv(target, pname, params);
    }

    @Override
    public int glGetBufferParameteri(int target, int pname) {
        return GL15.glGetBufferParameteri(target, pname);
    }

    @Override
    public void nglGetBufferPointerv(int target, int pname, long params) {
        GL15.nglGetBufferPointerv(target, pname, params);
    }

    @Override
    public long glGetBufferPointer(int target, int pname) {
        return GL15.glGetBufferPointer(target, pname);
    }

    @Override
    public void nglGenQueries(int n, long ids) {
        GL15.nglGenQueries(n, ids);
    }

    @Override
    public void glGenQueries(IntBuffer ids) {
        GL15.glGenQueries(ids);
    }

    @Override
    public int glGenQueries() {
        return GL15.glGenQueries();
    }

    @Override
    public void nglDeleteQueries(int n, long ids) {
        GL15.nglDeleteQueries(n, ids);
    }

    @Override
    public void glDeleteQueries(IntBuffer ids) {
        GL15.glDeleteQueries(ids);
    }

    @Override
    public void glDeleteQueries(int id) {
        GL15.glDeleteQueries(id);
    }

    @Override
    public boolean glIsQuery(int id) {
        return GL15.glIsQuery(id);
    }

    @Override
    public void glBeginQuery(int target, int id) {
        GL15.glBeginQuery(target, id);
    }

    @Override
    public void glEndQuery(int target) {
        GL15.glEndQuery(target);
    }

    @Override
    public void nglGetQueryiv(int target, int pname, long params) {
        GL15.nglGetQueryiv(target, pname, params);
    }

    @Override
    public void glGetQueryiv(int target, int pname, IntBuffer params) {
        GL15.glGetQueryiv(target, pname, params);
    }

    @Override
    public int glGetQueryi(int target, int pname) {
        return GL15.glGetQueryi(target, pname);
    }

    @Override
    public void nglGetQueryObjectiv(int id, int pname, long params) {
        GL15.nglGetQueryObjectiv(id, pname, params);
    }

    @Override
    public void glGetQueryObjectiv(int id, int pname, IntBuffer params) {
        GL15.glGetQueryObjectiv(id, pname, params);
    }

    @Override
    public void glGetQueryObjectiv(int id, int pname, long params) {
        GL15.glGetQueryObjectiv(id, pname, params);
    }

    @Override
    public int glGetQueryObjecti(int id, int pname) {
        return GL15.glGetQueryObjecti(id, pname);
    }

    @Override
    public void nglGetQueryObjectuiv(int id, int pname, long params) {
        GL15.nglGetQueryObjectuiv(id, pname, params);
    }

    @Override
    public void glGetQueryObjectuiv(int id, int pname, IntBuffer params) {
        GL15.glGetQueryObjectuiv(id, pname, params);
    }

    @Override
    public void glGetQueryObjectuiv(int id, int pname, long params) {
        GL15.glGetQueryObjectuiv(id, pname, params);
    }

    @Override
    public int glGetQueryObjectui(int id, int pname) {
        return GL15.glGetQueryObjectui(id, pname);
    }

    @Override
    public void glDeleteBuffers(int[] buffers) {
        GL15.glDeleteBuffers(buffers);
    }

    @Override
    public void glGenBuffers(int[] buffers) {
        GL15.glGenBuffers(buffers);
    }

    @Override
    public void glBufferData(int target, short[] data, int usage) {
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void glBufferData(int target, int[] data, int usage) {
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void glBufferData(int target, long[] data, int usage) {
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void glBufferData(int target, float[] data, int usage) {
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void glBufferData(int target, double[] data, int usage) {
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void glBufferSubData(int target, long offset, short[] data) {
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void glBufferSubData(int target, long offset, int[] data) {
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void glBufferSubData(int target, long offset, long[] data) {
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void glBufferSubData(int target, long offset, float[] data) {
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void glBufferSubData(int target, long offset, double[] data) {
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void glGetBufferSubData(int target, long offset, short[] data) {
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void glGetBufferSubData(int target, long offset, int[] data) {
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void glGetBufferSubData(int target, long offset, long[] data) {
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void glGetBufferSubData(int target, long offset, float[] data) {
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void glGetBufferSubData(int target, long offset, double[] data) {
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void glGetBufferParameteriv(int target, int pname, int[] params) {
        GL15.glGetBufferParameteriv(target, pname, params);
    }

    @Override
    public void glGenQueries(int[] ids) {
        GL15.glGenQueries(ids);
    }

    @Override
    public void glDeleteQueries(int[] ids) {
        GL15.glDeleteQueries(ids);
    }

    @Override
    public void glGetQueryiv(int target, int pname, int[] params) {
        GL15.glGetQueryiv(target, pname, params);
    }

    @Override
    public void glGetQueryObjectiv(int id, int pname, int[] params) {
        GL15.glGetQueryObjectiv(id, pname, params);
    }

    @Override
    public void glGetQueryObjectuiv(int id, int pname, int[] params) {
        GL15.glGetQueryObjectuiv(id, pname, params);
    }

}