package jpize.app;

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
        ContextManager.run();
    }


    public static GlfwWindow window() {
        return GlfwWindow.getCurrentContext();
    }

    public static GlfwInput input() {
        return window().getInput();
    }

    public static GlfwCallbacks callbacks() {
        return window().getCallbacks();
    }

    public static Context context() {
        return ContextManager.getCurrentContext();
    }


    public static int getWidth() {
        return window().getWidth();
    }

    public static int getHeight() {
        return window().getHeight();
    }

    public static float getX() {
        return input().getCursorX();
    }

    public static float getY() {
        return input().getCursorY();
    }


    public static int getFPS() {
        return ContextManager.getFPS();
    }

    public static float getDT() {
        return ContextManager.getDeltaTime();
    }


    public static SyncExecutor syncExecutor() {
        return context().getSyncExecutor();
    }


    public static void exit() {
        ContextManager.closeAll();
    }

    public static void exitOthers() {
        ContextManager.closeAllThatNotCurrent();
    }

}
