package jpize.opengl.vertex;

import jpize.opengl.type.GLType;

public class GLVertAttr {

    private final int count;
    private final GLType type;
    private final boolean normalized;

    public GLVertAttr(int count, GLType type, boolean normalized) {
        this.count = count;
        this.type = type;
        this.normalized = normalized;
    }
    
    public GLVertAttr(int count, GLType type) {
        this(count, type, false);
    }


    public int getCount() {
        return count;
    }

    public GLType getType() {
        return type;
    }

    public boolean isNormalized() {
        return normalized;
    }

}
