package jpize.app;

import jpize.io.IWindow;
import jpize.opengl.gl.Gl;
import jpize.glfw.window.GlfwWindow;
import jpize.util.time.DeltaTimeCounter;
import jpize.util.time.PerSecondCounter;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

public class Context {

    private final IWindow window;
    private final SyncExecutor syncExecutor;
    private final PerSecondCounter fpsCounter;
    private final DeltaTimeCounter deltaTimeCounter;

    private JpizeApplication app;
    private boolean disableRender;

    public Context(IWindow window) {
        this.window = window;
        this.makeCurrent();
        this.syncExecutor = new SyncExecutor();
        this.fpsCounter = new PerSecondCounter();
        this.deltaTimeCounter = new DeltaTimeCounter();
        ContextManager.instance().contextToInit(this);
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


    public void makeCurrent() {
        ContextManager.instance().makeContextCurrent(window);
    }


    protected void init() {
        this.makeCurrent();
        window.getCallbacks().addWindowSizeCallback(this::resize);
        if(app != null)
            app.init();
        window.show();
        // reset delta time counter after init
        fpsCounter.reset();
        deltaTimeCounter.reset();
    }

    private void resize(GlfwWindow window, int width, int height) {
        this.makeCurrent();
        Gl.viewport(width, height);
        if(app != null) app.resize(width, height);
    }

    protected void loop() {
        this.makeCurrent();

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

    private void exit() {
        // context is current (calls in loop)
        window.hide();

        // free resources
        if(app != null) app.dispose();
        window.dispose();
        ContextManager.instance().unregister(this);
    }

    public void close() {
        window.setShouldClose(true);
    }

}
