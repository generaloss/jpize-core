package jpize.opengl.vertex;

import jpize.opengl.type.GlType;

public class GlVertAttr {

    private final int count;
    private final GlType type;
    private final boolean normalize;

    public GlVertAttr(int count, GlType type, boolean normalize) {
        this.count = count;
        this.type = type;
        this.normalize = normalize;
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

    public boolean isNormalize() {
        return normalize;
    }

}
