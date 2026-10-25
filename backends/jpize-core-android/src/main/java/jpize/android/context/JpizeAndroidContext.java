package jpize.android.context;

import jpize.android.context.input.AndroidJpizeInput;
import jpize.context.Context;
import jpize.context.IWindow;
import jpize.context.callback.AbstractJpizeCallbacks;
import jpize.context.input.AbstractJpizeInput;

public class JpizeAndroidContext extends Context {

    private final AndroidWindow window;
    private final AndroidJpizeCallbacks callbacks;
    private final AndroidJpizeInput input;

    public JpizeAndroidContext(JpizeAndroidActivity activity) {
        this.window = new AndroidWindow(activity);
        this.callbacks = new AndroidJpizeCallbacks();
        this.input = new AndroidJpizeInput(this, activity);
    }


    @Override
    public IWindow getWindow() {
        return window;
    }

    @Override
    public AbstractJpizeCallbacks getCallbacks() {
        return callbacks;
    }

    @Override
    public AbstractJpizeInput getInput() {
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
        input.getTextInput().dispose();
    }

}
