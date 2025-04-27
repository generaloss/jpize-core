package jpize.opengl.vertex;

import jpize.opengl.type.GlType;

public class GlVertAttr {

    private final int count;
    private final GlType type;
    private final boolean normalized;

    public GlVertAttr(int count, GlType type, boolean normalized) {
        this.count = count;
        this.type = type;
        this.normalized = normalized;
    }
    
    public GlVertAttr(int count, GlType type) {
        this(count, type, false);
    }


    public int getCount() {
        return count;
    }

    public GlType getType() {
        return type;
    }

    public boolean isNormalized() {
        return normalized;
    }

}
