package jpize.app;

import jpize.io.IWindow;
import jpize.opengl.gl.Gl;
import jpize.util.time.DeltaTimeCounter;
import jpize.util.time.PerSecondCounter;

public abstract class Context {

    protected final IWindow window;
    private final SyncExecutor syncExecutor;
    private final PerSecondCounter fpsCounter;
    private final DeltaTimeCounter deltaTimeCounter;

    private JpizeApplication app;
    private boolean disableRender;

    public Context(IWindow window) {
        this.window = window;
        this.syncExecutor = new SyncExecutor();
        this.fpsCounter = new PerSecondCounter();
        this.deltaTimeCounter = new DeltaTimeCounter();
    }

    public IWindow getWindow() {
        return window;
    }

    public SyncExecutor getSyncExecutor() {
        return syncExecutor;
    }


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
        window.getCallbacks().addWindowSize(this::resize);
        if(app != null)
            app.init();
        window.show();
        // reset delta time counter after init
        fpsCounter.reset();
        deltaTimeCounter.reset();
    }

    protected void resize(int width, int height) {
        Gl.viewport(width, height);
        if(app != null) app.resize(width, height);
    }

    protected void loop() {
        // close window
        if(window.shouldClose()){
            exit();
            return;
        }

        // update
        if(app != null) app.update();
        // execute tasks in context thread
        syncExecutor.sync();
        // render
        if(app != null && !disableRender){
            app.render();
            window.swapBuffers();
        }
        // clear down/release keys
        window.getInput()
            .getInputMonitor().clear();

        // update fps & delta time counters
        fpsCounter.update();
        deltaTimeCounter.update();
    }

    protected void exit() {
        // context is current (calls in loop)
        window.hide();
        // free resources
        if(app != null) app.dispose();
        window.dispose();
    }

    public void close() {
        window.setShouldClose(true);
    }

}
