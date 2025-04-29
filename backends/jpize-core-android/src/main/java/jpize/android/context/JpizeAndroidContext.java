package jpize.android.context;

import android.app.Activity;
import jpize.android.context.input.AndroidInput;
import jpize.context.Context;
import jpize.context.IWindow;
import jpize.context.callback.AbstractCallbacks;
import jpize.context.input.AbstractInput;

public class JpizeAndroidContext extends Context {

    private final AndroidWindow window;
    private final AndroidCallbacks callbacks;
    private final AndroidInput input;

    public JpizeAndroidContext(Activity activity) {
        this.window = new AndroidWindow(activity);
        this.callbacks = new AndroidCallbacks();
        this.input = new AndroidInput(this, activity);
    }


    @Override
    public IWindow getWindow() {
        return window;
    }

    @Override
    public AbstractCallbacks getCallbacks() {
        return callbacks;
    }

    @Override
    public AbstractInput getInput() {
        return input;
    }


    @Override
    protected void onInit() {
        super.onInit();
    }

    @Override
    protected void onResize(int width, int height) {
        super.onResize(width, height);
    }

    @Override
    protected void onUpdate() {
        super.onUpdate();
    }

    @Override
    protected void onClose() {
        super.onClose();
    }

}
