package jpize.opengl;

import resourceflow.Disposable;

public abstract class GLObject implements Disposable {
    
    protected final int ID;
    
    public GLObject(int ID) {
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
        final GLObject glObject = (GLObject) object;
        return ID == glObject.ID;
    }

}
