package jpize.android.context;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import jpize.android.opengl.*;
import jpize.context.Jpize;
import jpize.opengl.gl.GL;
import jpize.opengl.glenum.GLCompareFunc;
import jpize.opengl.glenum.GLTarget;
import jpize.opengl.texture.GLBlendFactor;

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
        Jpize.GL11 = new AndroidGL11();
        Jpize.GL12 = new AndroidGL12();
        Jpize.GL13 = new AndroidGL13();
        Jpize.GL14 = new AndroidGL14();
        Jpize.GL15 = new AndroidGL15();
        Jpize.GL20 = new AndroidGL20();
        Jpize.GL21 = new AndroidGL21();
        Jpize.GL30 = new AndroidGL30();
        Jpize.GL31 = new AndroidGL31();
        Jpize.GL32 = new AndroidGL32();
        Jpize.GL33 = new AndroidGL33();
        Jpize.GL40 = new AndroidGL40();
        Jpize.GL41 = new AndroidGL41();
        Jpize.GL42 = new AndroidGL42();
        Jpize.GL43 = new AndroidGL43();
        Jpize.GL44 = new AndroidGL44();
        Jpize.GL45 = new AndroidGL45();
        Jpize.GL46 = new AndroidGL46();

        // default blending options, enable cullface
        GL.enable(GLTarget.BLEND, GLTarget.CULL_FACE);
        GL.blendFunc(GLBlendFactor.SRC_ALPHA, GLBlendFactor.ONE_MINUS_SRC_ALPHA);
        // opengl left-handled coordinate system options
        GL.depthFunc(GLCompareFunc.GEQUAL);
        GL.clearDepth(0);

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
