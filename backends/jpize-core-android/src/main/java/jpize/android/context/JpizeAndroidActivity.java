package jpize.android.context;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import jpize.android.context.input.AndroidKey;
import jpize.android.context.input.AndroidKeyHandler;
import jpize.android.context.input.AndroidMods;
import jpize.android.context.input.AndroidTouchHandler;
import jpize.context.Jpize;
import jpize.context.input.Action;
import jpize.context.input.Key;
import jpize.context.input.Mods;

public class JpizeAndroidActivity extends Activity {

    private AndroidGLSurfaceView glSurfaceView;
    private AndroidTouchHandler touchHandler;
    private AndroidKeyHandler keyHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create context
        final JpizeAndroidContext context = new JpizeAndroidContext(this);

        final AndroidCallbacks callbacks = (AndroidCallbacks) context.getCallbacks();

        Jpize.context = context;
        Jpize.window = context.getWindow();
        Jpize.input = context.getInput();
        Jpize.callbacks = callbacks;

        // set view
        this.glSurfaceView = new AndroidGLSurfaceView(this, context);

        this.glSurfaceView.setFocusable(true);
        this.glSurfaceView.setFocusableInTouchMode(true);
        this.glSurfaceView.setPreserveEGLContextOnPause(true);
        this.glSurfaceView.requestFocus();

        this.touchHandler = new AndroidTouchHandler(callbacks, context.getInput().getMaxMousesCount());
        this.keyHandler = new AndroidKeyHandler(callbacks);

        this.glSurfaceView.setOnTouchListener((view, event) -> this.touchHandler.onTouchEvent(event));

        super.setContentView(this.glSurfaceView);
    }

    public AndroidGLSurfaceView getGLSurfaceView() {
        return glSurfaceView;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("JPIZE", "dispatch: action=" + event.getAction()
                           + ", repeat=" + event.getRepeatCount()
                           + ", downTime=" + event.getDownTime()
                           + ", eventTime=" + event.getEventTime()
                           + ", deviceId=" + event.getDeviceId()
                           + ", source=" + event.getSource());

        if (keyHandler.onKeyEvent(event)) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                keyHandler.onKeyTyped(event);
                keyHandler.onKeyTypedWithMods(event);
            }
            return true;
        }

        return super.dispatchKeyEvent(event);
    }

}
