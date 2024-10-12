package jpize.app;

import jpize.glfw.Glfw;
import jpize.glfw.window.GlfwWindow;
import jpize.util.shader.BaseShader;
import jpize.util.postprocess.ScreenQuad;
import jpize.util.postprocess.ScreenQuadShader;
import jpize.gl.texture.TextureUtils;
import jpize.util.ReflectUtils;
import jpize.util.time.DeltaTimeCounter;
import jpize.util.time.UpsCounter;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ContextManager {

    private static ContextManager INSTANCE;

    public static ContextManager instance() {
        if(INSTANCE == null)
            INSTANCE = new ContextManager();
        return INSTANCE;
    }


    private final Queue<Context> contextsToInit = new ConcurrentLinkedQueue<>();
    private final Map<Long, Context> contexts = new ConcurrentHashMap<>();
    private final UpsCounter fpsCounter = new UpsCounter();
    private final DeltaTimeCounter deltaTimeCounter = new DeltaTimeCounter();
    private long currentContextID;

    private ContextManager() {
        Glfw.init();
        Glfw.swapInterval(1);
    }


    public Context getContext(long windowID) {
        return contexts.get(windowID);
    }

    public Context getContext(GlfwWindow window) {
        if(window == null)
            return null;
        return this.getContext(window.getID());
    }

    public Context getCurrentContext() {
        return this.getContext(currentContextID);
    }

    public void makeContextCurrent(GlfwWindow window) {
        if(window == null){
            currentContextID = 0L;
            return;
        }
        currentContextID = window.getID();
        window.makeContextCurrent();
    }

    protected void contextToInit(Context context) {
        contextsToInit.add(context);
    }

    protected void unregister(Context context) {
        contexts.remove(context.getWindow().getID());
    }

    protected void closeAll() {
        for(Context context: contexts.values())
            context.close();
    }

    protected void closeAllThatNotCurrent() {
        final Context current = this.getCurrentContext();
        for(Context context: contexts.values())
            if(context != current)
                context.close();
    }


    protected int getFPS() {
        return fpsCounter.get();
    }

    protected float getDeltaTime() {
        return deltaTimeCounter.get();
    }


    public void run() {
        this.initContexts();
        this.startLoop();
        this.terminate();
    }

    private void initContexts() {
        while(!contextsToInit.isEmpty()){
            final Context context = contextsToInit.poll();
            contexts.put(context.getWindow().getID(), context);
            context.init();
        }
    }

    private void startLoop() {
        while(!Thread.interrupted()) {
            this.initContexts();
            if(contexts.isEmpty())
                return;

            Glfw.pollEvents();
            for(Context context: contexts.values())
                context.loop();

            fpsCounter.update();
            deltaTimeCounter.update();
        }
    }

    private void terminate() {
        Glfw.terminate();
        ReflectUtils.invokeStaticMethod(TextureUtils.class, "dispose");
        ReflectUtils.invokeStaticMethod(ScreenQuad.class, "dispose");
        ReflectUtils.invokeStaticMethod(ScreenQuadShader.class, "dispose");
        ReflectUtils.invokeStaticMethod(BaseShader.class, "disposeShaders");
    }

}
