package jpize.opengl.buffer;

import jpize.opengl.type.GLIndexType;
import jpize.opengl.type.GLType;

public enum StoredType {

    BYTE   (1, GLType.BYTE,   GLIndexType.UNSIGNED_BYTE),
    SHORT  (2, GLType.SHORT,  GLIndexType.UNSIGNED_SHORT),
    INT    (4, GLType.INT,    GLIndexType.UNSIGNED_INT),
    LONG   (8, null,          null),
    FLOAT  (4, GLType.FLOAT,  null),
    DOUBLE (8, GLType.DOUBLE, null);

    public final int bytes;
    public final GLType type; // nullable
    public final GLIndexType indexType; // nullable

    StoredType(int bytes, GLType type, GLIndexType indexType) {
        this.bytes = bytes;
        this.type = type;
        this.indexType = indexType;
    }

}
