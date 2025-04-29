package jpize.lwjgl.context;

import jpize.context.IWindow;
import jpize.lwjgl.glfw.Glfw;
import jpize.lwjgl.glfw.init.GlfwPlatform;
import org.lwjgl.glfw.GLFW;

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


    public static void run() {
        final ContextManager manager = instance();
        manager.initContexts();
        manager.startLoop();
        GLFW.glfwTerminate();
    }

    public static void exit() {
        for(GlfwContext context: instance().contexts.values())
            context.close();
    }

    public static void closeAllOtherContexts() {
        final ContextManager manager = instance();
        final GlfwContext current = manager.getCurrentContext();

        for(GlfwContext context: manager.contexts.values())
            if(context != current)
                context.close();
    }


    private final Queue<GlfwContext> contextsToInit = new ConcurrentLinkedQueue<>();
    private final Map<Long, GlfwContext> contexts = new ConcurrentHashMap<>();
    private long currentContextID;

    private ContextManager() {
        // waiting for wayland fix in lwjgl
        if(System.getProperty("os.name").equals("Linux"))
            Glfw.glfwInitHintPlatform(GlfwPlatform.X11);
        // init glfw
        if(!GLFW.glfwInit())
            throw new RuntimeException("GLFW init failed.");
        Glfw.enableVSync(true);
    }


    public GlfwContext getContext(long windowID) {
        return contexts.getOrDefault(windowID,
            contextsToInit.stream()
                .filter(context -> windowID == context.getWindow().getID())
                .findFirst()
                .orElse(null)
        );
    }

    public GlfwContext getContext(IWindow window) {
        if(window == null)
            return null;
        return this.getContext(window.getID());
    }

    public GlfwContext getCurrentContext() {
        return this.getContext(currentContextID);
    }

    public void makeContextCurrent(IWindow window) {
        if(window == null){
            currentContextID = 0L;
            return;
        }
        currentContextID = window.getID();
        window.makeContextCurrent();
    }

    protected void contextToInit(GlfwContext context) {
        contextsToInit.add(context);
    }

    protected void unregister(GlfwContext context) {
        contexts.remove(context.getWindow().getID());
    }


    private void initContexts() {
        while(!contextsToInit.isEmpty()){
            final GlfwContext context = contextsToInit.poll();
            contexts.put(context.getWindow().getID(), context);
            context.onInit();
        }
    }

    private void startLoop() {
        while(!Thread.interrupted()) {
            this.initContexts();
            if(contexts.isEmpty())
                return;

            GLFW.glfwPollEvents();
            for(GlfwContext context: contexts.values())
                context.onUpdate();
        }
    }

}
