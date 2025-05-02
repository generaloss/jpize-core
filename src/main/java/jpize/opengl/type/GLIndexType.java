package jpize.opengl.type;

public enum GLIndexType {

    UNSIGNED_BYTE  (GLType.UNSIGNED_BYTE),
    UNSIGNED_SHORT (GLType.UNSIGNED_SHORT),
    UNSIGNED_INT   (GLType.UNSIGNED_INT);

    public final GLType type;

    GLIndexType(GLType type){
        this.type = type;
    }

}