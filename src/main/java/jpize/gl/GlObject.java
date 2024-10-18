package jpize.gl;

import jpize.util.Disposable;

public abstract class GlObject implements Disposable {
    
    protected final int ID;
    
    public GlObject(int ID) {
        this.ID = ID;
    }

    public final int getID() {
        return ID;
    }

    @Override
    public int hashCode() {
        return ID;
    }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(object == null || getClass() != object.getClass())
            return false;
        final GlObject glObject = (GlObject) object;
        return ID == glObject.ID;
    }

}
