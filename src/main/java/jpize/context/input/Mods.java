package jpize.context.input;

public abstract class Mods {

    protected final int bits;

    public Mods(int bits) {
        this.bits = bits;
    }

    public int getBits() {
        return bits;
    }

    public abstract boolean hasShift();

    public abstract boolean hasCtrl();

    public abstract boolean hasAlt();

    public abstract boolean hasSuper();

    public abstract boolean hasCapsLock();

    public abstract boolean hasNumLock();

}
