package jpize.app;

import jpize.opengl.*;
import jpize.glfw.callback.GlfwCallbacks;
import jpize.glfw.input.GlfwInput;
import jpize.glfw.window.GlfwWindow;

public class Jpize {

    public static ContextBuilder create(String title, int width, int height) {
        return new ContextBuilder(title, width, height);
    }

    public static ContextBuilder create(int width, int height, String title) {
        return create(title, width, height);
    }

    public static void run() {
        ContextManager.instance().run();
    }
    
    
    public static IGL11 GL11 = null;
    public static IGL12 GL12 = null;
    public static IGL13 GL13 = null;
    public static IGL14 GL14 = null;
    public static IGL15 GL15 = null;
    public static IGL20 GL20 = null;
    public static IGL21 GL21 = null;
    public static IGL30 GL30 = null;
    public static IGL31 GL31 = null;
    public static IGL32 GL32 = null;
    public static IGL33 GL33 = null;
    public static IGL40 GL40 = null;
    public static IGL41 GL41 = null;
    public static IGL42 GL42 = null;
    public static IGL43 GL43 = null;
    public static IGL44 GL44 = null;
    public static IGL45 GL45 = null;
    public static IGL46 GL46 = null;


    public static GlfwWindow window() {
        final Context context = context();
        if(context == null)
            return null;
        return context.getWindow();
    }

    public static GlfwInput input() {
        final GlfwWindow window = window();
        if(window == null)
            return null;
        return window.getInput();
    }

    public static GlfwCallbacks callbacks() {
        final GlfwWindow window = window();
        if(window == null)
            return null;
        return window.getCallbacks();
    }

    public static Context context() {
        return ContextManager.instance().getCurrentContext();
    }


    public static int getWidth() {
        final GlfwWindow window = window();
        if(window == null)
            return 0;
        return window.getWidth();
    }

    public static int getHeight() {
        final GlfwWindow window = window();
        if(window == null)
            return 0;
        return window.getHeight();
    }

    public static float getHalfWidth() {
        return getWidth() * 0.5F;
    }

    public static float getHalfHeight() {
        return getHeight() * 0.5F;
    }

    public static float getAspectRatio() {
        final GlfwWindow window = window();
        if(window == null)
            return 1F;
        return window.getAspectRatio();
    }


    public static float getX() {
        final GlfwInput input = input();
        if(input == null)
            return 0;
        return input.getCursorX();
    }

    public static float getY() {
        final GlfwInput input = input();
        if(input == null)
            return 0;
        return input.getCursorY();
    }

    public static float getScroll() {
        final GlfwInput input = input();
        if(input == null)
            return 0;
        return input.getScrollY();
    }


    public static int getFPS() {
        return context().getFPS();
    }

    public static float getDeltaTime() {
        return context().getDeltaTime();
    }


    public static SyncExecutor syncExecutor() {
        final Context context = context();
        if(context == null)
            return null;
        return context.getSyncExecutor();
    }


    public static void exit() {
        ContextManager.instance().closeAll();
    }

    public static void exitOthers() {
        ContextManager.instance().closeAllThatNotCurrent();
    }

}
