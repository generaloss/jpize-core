package jpize.android.context;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import jpize.android.opengl.*;
import jpize.context.Jpize;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class AndroidGLSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer {

    private final JpizeAndroidContext context;

    public AndroidGLSurfaceView(Activity activity, JpizeAndroidContext context) {
        super(activity);
        this.context = context;

        super.setEGLContextClientVersion(3);
        super.setRenderer(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        Jpize.GL11 = AndroidGL11.INSTANCE;
        Jpize.GL12 = AndroidGL12.INSTANCE;
        Jpize.GL13 = AndroidGL13.INSTANCE;
        Jpize.GL14 = AndroidGL14.INSTANCE;
        Jpize.GL15 = AndroidGL15.INSTANCE;
        Jpize.GL20 = AndroidGL20.INSTANCE;
        Jpize.GL21 = AndroidGL21.INSTANCE;
        Jpize.GL30 = AndroidGL30.INSTANCE;
        Jpize.GL31 = AndroidGL31.INSTANCE;
        Jpize.GL32 = AndroidGL32.INSTANCE;
        Jpize.GL33 = AndroidGL33.INSTANCE;
        Jpize.GL40 = AndroidGL40.INSTANCE;
        Jpize.GL41 = AndroidGL41.INSTANCE;
        Jpize.GL42 = AndroidGL42.INSTANCE;
        Jpize.GL43 = AndroidGL43.INSTANCE;
        Jpize.GL44 = AndroidGL44.INSTANCE;
        Jpize.GL45 = AndroidGL45.INSTANCE;
        Jpize.GL46 = AndroidGL46.INSTANCE;
        context.onInit();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        context.getCallbacks().invokeResize(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        context.onUpdate();
    }

}
