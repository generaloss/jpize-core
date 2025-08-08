package jpize.context;

import jpize.context.callback.AbstractCallbacks;
import jpize.context.input.AbstractInput;
import jpize.opengl.gl.GL;
import jpize.opengl.glenum.GLCompareFunc;
import jpize.opengl.glenum.GLTarget;
import jpize.opengl.texture.GLBlendFactor;
import generaloss.chronokit.DeltaTimeCounter;
import generaloss.chronokit.PerSecondCounter;

public abstract class Context {

    private final SyncExecutor syncExecutor;
    private final PerSecondCounter fpsCounter;
    private final DeltaTimeCounter deltaTimeCounter;

    private JpizeApplication app;
    private boolean disableRender;
    private boolean closeRequest;

    public Context() {
        this.syncExecutor = new SyncExecutor();
        this.fpsCounter = new PerSecondCounter();
        this.deltaTimeCounter = new DeltaTimeCounter();
    }

    public SyncExecutor getSyncExecutor() {
        return syncExecutor;
    }

    public abstract IWindow getWindow();

    public abstract AbstractInput getInput();

    public abstract AbstractCallbacks getCallbacks();


    public Context setApp(JpizeApplication app) {
        this.app = app;
        return this;
    }

    public Context disableRender(boolean disable) {
        this.disableRender = disable;
        return this;
    }


    protected int getFPS() {
        return fpsCounter.get();
    }

    protected float getDeltaTime() {
        return deltaTimeCounter.get();
    }


    protected void onInit() {
        // default blending options, enable cullface
        GL.enable(GLTarget.BLEND, GLTarget.CULL_FACE);
        GL.blendFunc(GLBlendFactor.SRC_ALPHA, GLBlendFactor.ONE_MINUS_SRC_ALPHA);
        // opengl left-handled coordinate system options
        GL.depthFunc(GLCompareFunc.GEQUAL);
        GL.clearDepth(0);
        // callbacks
        this.getCallbacks().addResize(this::onResize);
        if(app != null)
            app.init();
        this.getCallbacks().invokeInit();
        // reset delta time counter after init
        fpsCounter.reset();
        deltaTimeCounter.reset();
    }

    protected void onResize(int width, int height) {
        Jpize.GL11.glViewport(0, 0, width, height);
        if(app != null)
            app.resize(width, height);
    }

    protected void onUpdate() {
        // close
        if(closeRequest){
            this.onClose();
            return;
        }
        // update
        this.getCallbacks().invokeUpdate();
        if(app != null)
            app.update();
        // execute tasks in context thread
        syncExecutor.sync();
        // render
        if(app != null && !disableRender){
            app.render();
            this.getCallbacks().invokeRender();
        }
        // update fps & delta time counters
        fpsCounter.update();
        deltaTimeCounter.update();
        this.getInput().getInputMonitor().clear();
    }

    protected void onClose() {
        // (context is current (calls in loop))
        this.getCallbacks().invokeCloseContext();
        // free resources
        if(app != null)
            app.dispose();
    }

    public void close() {
        closeRequest = true;
    }

}
