package jpize.context;

import jpize.context.input.IAllocator;
import jpize.context.callback.AbstractCallbacks;
import jpize.context.input.AbstractInput;
import jpize.opengl.gl.*;

public class Jpize {

    public static IContextManager contextManager;
    public static IAllocator allocator;
    public static Context context;
    public static IWindow window;
    public static AbstractInput input;
    public static AbstractCallbacks callbacks;
    public static GLI11 GL11;
    public static GLI12 GL12;
    public static GLI13 GL13;
    public static GLI14 GL14;
    public static GLI15 GL15;
    public static GLI20 GL20;
    public static GLI21 GL21;
    public static GLI30 GL30;
    public static GLI31 GL31;
    public static GLI32 GL32;
    public static GLI33 GL33;
    public static GLI40 GL40;
    public static GLI41 GL41;
    public static GLI42 GL42;
    public static GLI43 GL43;
    public static GLI44 GL44;
    public static GLI45 GL45;
    public static GLI46 GL46;


    public static int getWidth() {
        return window.getWidth();
    }

    public static int getHeight() {
        return window.getHeight();
    }

    public static float getHalfWidth() {
        return (getWidth() * 0.5F);
    }

    public static float getHalfHeight() {
        return (getHeight() * 0.5F);
    }

    public static float getAspectRatio() {
        return window.getAspectRatio();
    }


    public static float getX(int cursorIndex) {
        return input.getCursorX(cursorIndex);
    }

    public static float getX() {
        return getX(0);
    }

    public static float getY(int cursorIndex) {
        return input.getCursorY(cursorIndex);
    }

    public static float getY() {
        return getY(0);
    }

    public static float getNativeY(int cursorIndex) {
        return input.getCursorNativeY(cursorIndex);
    }

    public static float getNativeY() {
        return getNativeY(0);
    }


    public static float getScroll() {
        return input.getScrollY();
    }


    public static int getFPS() {
        return context.getFPS();
    }

    public static float getDeltaTime() {
        return context.getDeltaTime();
    }


    public static SyncExecutor syncExecutor() {
        return context.getSyncExecutor();
    }


    public static <T extends IContextBuilder> T create(T builder, int width, int height, String title) {
        return builder;
    }

    public static <T extends IContextBuilder> T create(T builder) {
        return builder;
    }


    public static void run() {
        contextManager.run();
    }

    public static void exit() {
        contextManager.closeAll();
    }

    public static void exitOthers() {
        contextManager.closeAllThatNotCurrent();
    }

}
