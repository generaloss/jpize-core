package jpize.app;

import jpize.gl.GL20;
import jpize.gl.GL30;
import jpize.gl.GL31;
import jpize.gl.GL32;
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
    
    
    public static GL20 gl20() {
        return null;
    }

    public static GL30 gl30() {
        return null;
    }

    public static GL31 gl31() {
        return null;
    }

    public static GL32 gl() {
        return null;
    }


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
