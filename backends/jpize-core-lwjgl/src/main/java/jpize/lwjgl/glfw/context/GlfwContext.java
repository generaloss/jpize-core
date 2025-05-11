package jpize.lwjgl.glfw.context;

import jpize.context.Context;
import jpize.context.Jpize;
import jpize.context.callback.AbstractCallbacks;
import jpize.context.input.AbstractInput;
import jpize.lwjgl.glfw.callback.GlfwCallbacks;
import jpize.lwjgl.glfw.input.GlfwInput;
import jpize.lwjgl.glfw.window.GlfwWindow;
import jpize.lwjgl.opengl.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

public class GlfwContext extends Context {

    private final GlfwWindow window;
    private final GlfwInput input;
    private final GlfwCallbacks callbacks;
    private final GLCapabilities glCapabilities;

    public GlfwContext(GlfwWindow window) {
        this.window = window;
        this.callbacks = new GlfwCallbacks(window);
        this.input = new GlfwInput(this); // uses callbacks in constructor

        this.makeCurrent();
        this.glCapabilities = GL.createCapabilities();

        GlfwContextManager.instance().contextToInit(this);
        callbacks.addRender(window::swapBuffers);
    }

    @Override
    public GlfwWindow getWindow() {
        return window;
    }

    @Override
    public AbstractInput getInput() {
        return input;
    }

    @Override
    public AbstractCallbacks getCallbacks() {
        return callbacks;
    }

    public GLCapabilities getGLCapabilities() {
        return glCapabilities;
    }

    @Override
    public void onInit() {
        this.makeCurrent();
        super.onInit();
        window.show();
    }

    @Override
    public void onResize(int width, int height) {
        this.makeCurrent();
        super.onResize(width, height);
    }

    @Override
    public void onUpdate() {
        this.makeCurrent();
        if(window.shouldClose())
            super.close();
        super.onUpdate();
        input.getInputMonitor().clear();
    }

    @Override
    public void onClose() {
        // (context is current (calls in loop))
        window.hide();
        GlfwContextManager.instance().unregister(this);
        super.onClose();
    }


    public void makeCurrent() {
        GlfwContextManager.instance().makeContextCurrent(window);
        Jpize.context = this;
        Jpize.window = window;
        Jpize.input = input;
        Jpize.callbacks = callbacks;
        Jpize.GL11 = LwjglGL11.INSTANCE;
        Jpize.GL12 = LwjglGL12.INSTANCE;
        Jpize.GL13 = LwjglGL13.INSTANCE;
        Jpize.GL14 = LwjglGL14.INSTANCE;
        Jpize.GL15 = LwjglGL15.INSTANCE;
        Jpize.GL20 = LwjglGL20.INSTANCE;
        Jpize.GL21 = LwjglGL21.INSTANCE;
        Jpize.GL30 = LwjglGL30.INSTANCE;
        Jpize.GL31 = LwjglGL31.INSTANCE;
        Jpize.GL32 = LwjglGL32.INSTANCE;
        Jpize.GL33 = LwjglGL33.INSTANCE;
        Jpize.GL40 = LwjglGL40.INSTANCE;
        Jpize.GL41 = LwjglGL41.INSTANCE;
        Jpize.GL42 = LwjglGL42.INSTANCE;
        Jpize.GL43 = LwjglGL43.INSTANCE;
        Jpize.GL44 = LwjglGL44.INSTANCE;
        Jpize.GL45 = LwjglGL45.INSTANCE;
        Jpize.GL46 = LwjglGL46.INSTANCE;
    }

}
