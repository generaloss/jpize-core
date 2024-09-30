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

class ContextManager {

    private static final Map<Long, Context> CONTEXTS = new ConcurrentHashMap<>();
    private static final Queue<Context> TO_INIT = new ConcurrentLinkedQueue<>();

    protected static Context getContext(long windowID) {
        return CONTEXTS.get(windowID);
    }

    protected static Context getContext(GlfwWindow window) {
        return CONTEXTS.get(window.getID());
    }

    protected static Context getCurrentContext() {
        return getContext(GlfwWindow.getCurrentContext());
    }


    protected static void contextToInit(Context context) {
        TO_INIT.add(context);
    }

    private static void register(Context context) {
        CONTEXTS.put(context.getWindow().getID(), context);
    }

    protected static void unregister(Context context) {
        CONTEXTS.remove(context.getWindow().getID());
    }


    private static volatile boolean INIT = false;

    protected static synchronized void init() {
        if(INIT) return;
        INIT = true;
        Glfw.init();
        Glfw.swapInterval(1);
    }

    protected static void run() {
        initContexts();
        startLoop();
        terminate();
    }

    private static void terminate() {
        if(!INIT) return;
        Glfw.terminate();
        ReflectUtils.invokeStaticMethod(TextureUtils.class, "dispose");
        ReflectUtils.invokeStaticMethod(ScreenQuad.class, "dispose");
        ReflectUtils.invokeStaticMethod(ScreenQuadShader.class, "dispose");
        ReflectUtils.invokeStaticMethod(BaseShader.class, "disposeShaders");
        INIT = false;
    }


    private static final UpsCounter FPS_COUNTER = new UpsCounter();
    private static final DeltaTimeCounter DELTA_TIME_COUNTER = new DeltaTimeCounter();

    protected static int getFPS() {
        return FPS_COUNTER.get();
    }

    protected static float getDeltaTime() {
        return DELTA_TIME_COUNTER.get();
    }


    private static void startLoop() {
        while(!Thread.interrupted()) {
            initContexts();
            if(CONTEXTS.isEmpty())
                return;

            Glfw.pollEvents();
            for(Context context: CONTEXTS.values())
                context.loop();

            FPS_COUNTER.update();
            DELTA_TIME_COUNTER.update();
        }
    }

    private static void initContexts() {
        while(!TO_INIT.isEmpty()){
            final Context context = TO_INIT.poll();
            register(context);
            context.init();
        }
    }


    protected static void closeAll() {
        for(Context context: CONTEXTS.values())
            context.close();
    }

    protected static void closeAllThatNotCurrent() {
        final Context current = getCurrentContext();
        for(Context context: CONTEXTS.values())
            if(context != current)
                context.close();
    }

}
