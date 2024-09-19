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
    private boolean skipRender;

    protected Context(GlfwWindow window) {
        window.makeContextCurrent();
        this.window = window;
        this.capabilities = GL.createCapabilities();
        this.syncExecutor = new SyncExecutor();
        ContextManager.contextToInit(this);
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

    public Context skipRender(boolean skipRender) {
        this.skipRender = skipRender;
        return this;
    }


    protected void init() {
        window.makeContextCurrent();
        window.getCallbacks().addWindowSizeCallback(this::resize);
        window.show();
        if(app != null){
            app.init();
            if(skipRender) window.swapBuffers();
        }
    }

    private void resize(GlfwWindow window, int width, int height) {
        window.makeContextCurrent();
        Gl.viewport(width, height);
        if(app != null) app.resize(width, height);
    }

    protected void loop() {
        window.makeContextCurrent();

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
        if(app != null && !skipRender){
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
        ContextManager.unregister(this);
    }

    public void close() {
        window.setShouldClose(true);
    }

}
