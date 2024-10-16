package jpize.app;

import jpize.gl.Gl;
import jpize.glfw.window.GlfwWindow;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

public class Context {

    private final GlfwWindow window;
    private final GLCapabilities capabilities;
    private final SyncExecutor syncExecutor;

    private JpizeApplication app;
    private boolean disableRender;

    protected Context(GlfwWindow window) {
        this.window = window;
        this.makeCurrent();
        this.capabilities = GL.createCapabilities();
        this.syncExecutor = new SyncExecutor();
        ContextManager.instance().contextToInit(this);
    }

    public GlfwWindow getWindow() {
        return window;
    }

    public GLCapabilities getGLCapabilities() {
        return capabilities;
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


    public void makeCurrent() {
        ContextManager.instance().makeContextCurrent(window);
    }


    protected void init() {
        this.makeCurrent();
        window.getCallbacks().addWindowSizeCallback(this::resize);
        if(app != null)
            app.init();
        window.show();
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
    }

    private void exit() {
        // context is current (calls in loop)
        window.hide();

        // free resources
        if(app != null) app.dispose();
        window.destroy();
        ContextManager.instance().unregister(this);
    }

    public void close() {
        window.setShouldClose(true);
    }

}
