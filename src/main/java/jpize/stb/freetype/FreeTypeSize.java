package jpize.stb.freetype;

public class FreeTypeSize {

    private final long address;

    FreeTypeSize(long address) {
        this.address = address;
    }

    private static native long getMetrics(long address);

    public FreeTypeSizeMetrics getMetrics() {
        return new FreeTypeSizeMetrics(getMetrics(address));
    }

}