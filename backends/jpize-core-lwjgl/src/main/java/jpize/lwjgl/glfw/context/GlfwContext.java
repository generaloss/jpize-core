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
        Jpize.GL11 = GL11;
        Jpize.GL12 = GL12;
        Jpize.GL13 = GL13;
        Jpize.GL14 = GL14;
        Jpize.GL15 = GL15;
        Jpize.GL20 = GL20;
        Jpize.GL21 = GL21;
        Jpize.GL30 = GL30;
        Jpize.GL31 = GL31;
        Jpize.GL32 = GL32;
        Jpize.GL33 = GL33;
        Jpize.GL40 = GL40;
        Jpize.GL41 = GL41;
        Jpize.GL42 = GL42;
        Jpize.GL43 = GL43;
        Jpize.GL44 = GL44;
        Jpize.GL45 = GL45;
        Jpize.GL46 = GL46;
    }

    private static final LwjglGL11 GL11 = LwjglGL11.INSTANCE;
    private static final LwjglGL12 GL12 = LwjglGL12.INSTANCE;
    private static final LwjglGL13 GL13 = LwjglGL13.INSTANCE;
    private static final LwjglGL14 GL14 = LwjglGL14.INSTANCE;
    private static final LwjglGL15 GL15 = LwjglGL15.INSTANCE;
    private static final LwjglGL20 GL20 = LwjglGL20.INSTANCE;
    private static final LwjglGL21 GL21 = LwjglGL21.INSTANCE;
    private static final LwjglGL30 GL30 = LwjglGL30.INSTANCE;
    private static final LwjglGL31 GL31 = LwjglGL31.INSTANCE;
    private static final LwjglGL32 GL32 = LwjglGL32.INSTANCE;
    private static final LwjglGL33 GL33 = LwjglGL33.INSTANCE;
    private static final LwjglGL40 GL40 = LwjglGL40.INSTANCE;
    private static final LwjglGL41 GL41 = LwjglGL41.INSTANCE;
    private static final LwjglGL42 GL42 = LwjglGL42.INSTANCE;
    private static final LwjglGL43 GL43 = LwjglGL43.INSTANCE;
    private static final LwjglGL44 GL44 = LwjglGL44.INSTANCE;
    private static final LwjglGL45 GL45 = LwjglGL45.INSTANCE;
    private static final LwjglGL46 GL46 = LwjglGL46.INSTANCE;

}
