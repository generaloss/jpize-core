package jpize.opengl;

import java.nio.*;

public interface IGL44 extends IGL43 {

    int GL_MAX_VERTEX_ATTRIB_STRIDE = 33509;
    int GL_PRIMITIVE_RESTART_FOR_PATCHES_SUPPORTED = 33313;
    int GL_TEXTURE_BUFFER_BINDING = 35882;
    int GL_MAP_PERSISTENT_BIT = 64;
    int GL_MAP_COHERENT_BIT = 128;
    int GL_DYNAMIC_STORAGE_BIT = 256;
    int GL_CLIENT_STORAGE_BIT = 512;
    int GL_BUFFER_IMMUTABLE_STORAGE = 33311;
    int GL_BUFFER_STORAGE_FLAGS = 33312;
    int GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT = 16384;
    int GL_CLEAR_TEXTURE = 37733;
    int GL_LOCATION_COMPONENT = 37706;
    int GL_TRANSFORM_FEEDBACK_BUFFER_INDEX = 37707;
    int GL_TRANSFORM_FEEDBACK_BUFFER_STRIDE = 37708;
    int GL_QUERY_RESULT_NO_WAIT = 37268;
    int GL_QUERY_BUFFER = 37266;
    int GL_QUERY_BUFFER_BINDING = 37267;
    int GL_QUERY_BUFFER_BARRIER_BIT = 32768;
    int GL_MIRROR_CLAMP_TO_EDGE = 34627;

    void nglBufferStorage(int target, long size, long data, int flags);
    void glBufferStorage(int target, long size, int flags);
    void glBufferStorage(int target, ByteBuffer data, int flags);
    void glBufferStorage(int target, ShortBuffer data, int flags);
    void glBufferStorage(int target, IntBuffer data, int flags);
    void glBufferStorage(int target, FloatBuffer data, int flags);
    void glBufferStorage(int target, DoubleBuffer data, int flags);
    void nglClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long data);
    void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer data);
    void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer data);
    void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer data);
    void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer data);
    void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer data);
    void nglClearTexImage(int texture, int level, int format, int type, long data);
    void glClearTexImage(int texture, int level, int format, int type, ByteBuffer data);
    void glClearTexImage(int texture, int level, int format, int type, ShortBuffer data);
    void glClearTexImage(int texture, int level, int format, int type, IntBuffer data);
    void glClearTexImage(int texture, int level, int format, int type, FloatBuffer data);
    void glClearTexImage(int texture, int level, int format, int type, DoubleBuffer data);
    void nglBindBuffersBase(int target, int first, int count, long buffers);
    void glBindBuffersBase(int target, int first, IntBuffer buffers);
    void nglBindBuffersRange(int target, int first, int count, long buffers, long offsets, long sizes);
    void glBindBuffersRange(int target, int first, IntBuffer buffers, PointerBuffer offsets, PointerBuffer sizes);
    void nglBindTextures(int first, int count, long textures);
    void glBindTextures(int first, IntBuffer textures);
    void nglBindSamplers(int first, int count, long samplers);
    void glBindSamplers(int first, IntBuffer samplers);
    void nglBindImageTextures(int first, int count, long textures);
    void glBindImageTextures(int first, IntBuffer textures);
    void nglBindVertexBuffers(int first, int count, long buffers, long offsets, long strides);
    void glBindVertexBuffers(int first, IntBuffer buffers, PointerBuffer offsets, IntBuffer strides);
    void glBufferStorage(int target, short[] data, int flags);
    void glBufferStorage(int target, int[] data, int flags);
    void glBufferStorage(int target, float[] data, int flags);
    void glBufferStorage(int target, double[] data, int flags);
    void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, short [] data);
    void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int [] data);
    void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, float [] data);
    void glClearTexSubImage(int texture, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, double [] data);
    void glClearTexImage(int texture, int level, int format, int type, short [] data);
    void glClearTexImage(int texture, int level, int format, int type, int [] data);
    void glClearTexImage(int texture, int level, int format, int type, float [] data);
    void glClearTexImage(int texture, int level, int format, int type, double [] data);
    void glBindBuffersBase(int target, int first, int [] buffers);
    void glBindBuffersRange(int target, int first, int [] buffers, PointerBuffer offsets, PointerBuffer sizes);
    void glBindTextures(int first, int [] textures);
    void glBindSamplers(int first, int [] samplers);
    void glBindImageTextures(int first, int [] textures);
    void glBindVertexBuffers(int first, int [] buffers, PointerBuffer offsets, int [] strides);

}
