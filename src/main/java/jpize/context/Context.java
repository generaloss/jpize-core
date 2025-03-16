package jpize.context;

import jpize.context.callback.AbstractCallbacks;
import jpize.context.input.AbstractInput;
import jpize.util.time.DeltaTimeCounter;
import jpize.util.time.PerSecondCounter;

public abstract class Context {

    private final SyncExecutor syncExecutor;
    private final PerSecondCounter fpsCounter;
    private final DeltaTimeCounter deltaTimeCounter;

    private JpizeApplication app;
    private boolean disableRender;
    private boolean exitRequest;

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


    protected void init() {
        if(app != null)
            app.init();
        this.getCallbacks().invokeInit();
        // reset delta time counter after init
        fpsCounter.reset();
        deltaTimeCounter.reset();
    }

    protected void resize(int width, int height) {
        Jpize.GL11.glViewport(0, 0, width, height);
        if(app != null)
            app.resize(width, height);
        this.getCallbacks().invokeResize(width, height);
    }

    protected void loop() {
        // close
        if(exitRequest){
            this.exit();
            return;
        }
        // update
        if(app != null)
            app.update();
        this.getCallbacks().invokeUpdate();
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

    protected void exit() {
        // (context is current (calls in loop))
        this.getCallbacks().invokeExit();
        // free resources
        if(app != null)
            app.dispose();
    }

    public void close() {
        exitRequest = true;
    }

}
