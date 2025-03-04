package jpize.app;

import jpize.io.input.ICallbacks;
import jpize.io.input.AbstractInput;
import jpize.io.IWindow;
import jpize.opengl.gl.*;

public class Jpize {

    public static Context context;
    public static IWindow window;
    public static AbstractInput input;
    public static ICallbacks callbacks;
    public static IGL11 GL11;
    public static IGL12 GL12;
    public static IGL13 GL13;
    public static IGL14 GL14;
    public static IGL15 GL15;
    public static IGL20 GL20;
    public static IGL21 GL21;
    public static IGL30 GL30;
    public static IGL31 GL31;
    public static IGL32 GL32;
    public static IGL33 GL33;
    public static IGL40 GL40;
    public static IGL41 GL41;
    public static IGL42 GL42;
    public static IGL43 GL43;
    public static IGL44 GL44;
    public static IGL45 GL45;
    public static IGL46 GL46;


    public int getWidth() {
        return window.getWidth();
    }

    public int getHeight() {
        return window.getHeight();
    }

    public float getHalfWidth() {
        return (getWidth() * 0.5F);
    }

    public float getHalfHeight() {
        return (getHeight() * 0.5F);
    }

    public float getAspectRatio() {
        return window.getAspectRatio();
    }


    public float getX() {
        return input.getCursorX();
    }

    public float getY() {
        return input.getCursorY();
    }

    public float getScroll() {
        return input.getScrollY();
    }


    public int getFPS() {
        return context.getFPS();
    }

    public float getDeltaTime() {
        return context.getDeltaTime();
    }


    public SyncExecutor syncExecutor() {
        return context.getSyncExecutor();
    }


    public static void run() {
        ContextManager.instance().run();
    }

    public static void exit() {
        ContextManager.instance().closeAll();
    }

    public static void exitOthers() {
        ContextManager.instance().closeAllThatNotCurrent();
    }

}
