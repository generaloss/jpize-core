package jpize.context;

import jpize.context.callback.AbstractCallbacks;
import jpize.context.input.AbstractInput;
import jpize.opengl.gl.*;

public class Jpize {

    public static volatile Context context;
    public static IWindow window;
    public static AbstractInput input;
    public static volatile AbstractCallbacks callbacks;
    public static GL11I GL11;
    public static GL12I GL12;
    public static GL13I GL13;
    public static GL14I GL14;
    public static GL15I GL15;
    public static GL20I GL20;
    public static GL21I GL21;
    public static GL30I GL30;
    public static GL31I GL31;
    public static GL32I GL32;
    public static GL33I GL33;
    public static GL40I GL40;
    public static GL41I GL41;
    public static GL42I GL42;
    public static GL43I GL43;
    public static GL44I GL44;
    public static GL45I GL45;
    public static GL46I GL46;


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
    
    
    public static void exit() {
        context.close();
    }

}
