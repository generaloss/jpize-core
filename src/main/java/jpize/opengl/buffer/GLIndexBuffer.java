package jpize.opengl.buffer;

import jpize.opengl.type.GLIndexType;

public class GLIndexBuffer extends GLBuffer {

    public GLIndexBuffer() {
        super(GLBufferTarget.ELEMENT);
    }

    public GLIndexType getIndexType() { // nullable
        final StoredType type = super.getType();
        if(type == null)
            return null;
        return type.indexType;
    }

}
