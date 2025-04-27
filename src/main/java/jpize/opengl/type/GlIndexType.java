package jpize.opengl.type;

public enum GlIndexType {

    UNSIGNED_BYTE  (GlType.UNSIGNED_BYTE),
    UNSIGNED_SHORT (GlType.UNSIGNED_SHORT),
    UNSIGNED_INT   (GlType.UNSIGNED_INT);

    public final GlType type;

    GlIndexType(GlType type){
        this.type = type;
    }

}