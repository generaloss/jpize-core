package jpize.context.callback;

public interface PreeditCallback {

    void invoke(int preeditCount, long preeditStringPointer, int blockCount, long blockSizesPointer, int focusedBlock, int caret);

}
