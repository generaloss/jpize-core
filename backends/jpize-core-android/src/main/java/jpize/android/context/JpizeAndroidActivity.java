package jpize.android.context;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.FrameLayout;
import generaloss.resourceflow.resource.InternalResource;
import jpize.android.context.input.AndroidKeyHandler;
import jpize.android.context.input.AndroidTouchHandler;
import jpize.android.resources.AndroidAssetsSource;
import jpize.context.Jpize;

public class JpizeAndroidActivity extends Activity {

    private FrameLayout frameLayout;
    private AndroidGLSurfaceView glSurfaceView;
    private AndroidTouchHandler touchHandler;
    private AndroidKeyHandler keyHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.frameLayout = new FrameLayout(this);

        // create context
        final JpizeAndroidContext context = new JpizeAndroidContext(this);
        final AndroidJpizeCallbacks callbacks = (AndroidJpizeCallbacks) context.getCallbacks();
        final AndroidJpizeFiles files = new AndroidJpizeFiles(super.getApplicationContext());

        Jpize.context = context;
        Jpize.window = context.getWindow();
        Jpize.input = context.getInput();
        Jpize.callbacks = callbacks;
        Jpize.files = files;

        // use android asset manager
        InternalResource.registerSource(
            new AndroidAssetsSource(super.getAssets())
        );

        // input
        this.touchHandler = new AndroidTouchHandler(callbacks, context.getInput().getMaxMousesCount());
        this.keyHandler = new AndroidKeyHandler(callbacks);

        // surface
        this.glSurfaceView = new AndroidGLSurfaceView(this, context);
        this.glSurfaceView.setFocusable(true);
        this.glSurfaceView.setFocusableInTouchMode(true);
        this.glSurfaceView.setPreserveEGLContextOnPause(true);
        this.glSurfaceView.requestFocus();
        this.glSurfaceView.setOnTouchListener((view, event) -> touchHandler.onTouchEvent(event));
        this.frameLayout.addView(glSurfaceView);

        // set view
        super.setContentView(frameLayout);
        this.hideSystemUI();
    }

    private void hideSystemUI() {
        final WindowInsetsController controller = this.getWindow().getInsetsController();
        if(controller == null)
            return;

        controller.hide(WindowInsets.Type.navigationBars() | WindowInsets.Type.statusBars());
        controller.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
    }

    public FrameLayout getFrameLayout() {
        return frameLayout;
    }

    public AndroidGLSurfaceView getGLSurfaceView() {
        return glSurfaceView;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(keyHandler.onKeyEvent(event)) {
            if(event.getAction() == KeyEvent.ACTION_DOWN) {
                keyHandler.onKeyTyped(event);
                keyHandler.onKeyTypedWithMods(event);
            }
            return true;
        }

        return super.dispatchKeyEvent(event);
    }

    public void onJpizeInitialized() { }

}
