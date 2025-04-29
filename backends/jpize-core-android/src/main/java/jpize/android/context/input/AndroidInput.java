package jpize.android.context.input;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.content.res.Configuration;
import jpize.android.context.JpizeAndroidContext;
import jpize.context.Jpize;
import jpize.context.input.*;
import jpize.util.math.geometry.Recti;
import jpize.util.math.vector.Vec2f;
import java.nio.IntBuffer;

public class AndroidInput extends AbstractInput {

    private final Activity activity;
    private final TouchMonitor touchMonitor;

    public AndroidInput(JpizeAndroidContext context, Activity activity) {
        super(context);
        this.activity = activity;
        this.touchMonitor = new TouchMonitor(context);
    }

    public TouchMonitor getTouchMonitor() {
        return touchMonitor;
    }


    @Override
    public int getKeyScancode(Key key) {
        return AndroidKey.getScancode(key);
    }

    @Override
    public String getKeyName(Key key) {
        return AndroidKey.getKeyName(key);
    }

    @Override
    public int getMaxMousesCount() {
        final ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo config = activityManager.getDeviceConfigurationInfo();
        return (config.reqTouchScreen == Configuration.TOUCHSCREEN_FINGER ? 10 : 2);
    }

    @Override
    public String getClipboardString() {
        return "";
    }

    @Override
    public void setClipboardString(CharSequence string) { }

    @Override
    public CursorMode getCursorMode() {
        return CursorMode.NORMAL;
    }

    @Override
    public boolean getStickyKeys() {
        return false;
    }

    @Override
    public boolean getStickyMouseButtons() {
        return false;
    }

    @Override
    public boolean getLockKeyMods() {
        return false;
    }

    @Override
    public boolean getRawMouseMotion() {
        return false;
    }

    @Override
    public boolean isRawMouseMotionSupported() {
        return false;
    }

    @Override
    public void setCursorMode(CursorMode value) { }

    @Override
    public void setStickyKeys(boolean value) { }

    @Override
    public void setStickyMouseButtons(boolean value) { }

    @Override
    public void setLockKeyMods(boolean value) { }

    @Override
    public void setRawMouseMotion(boolean value) { }

    @Override
    public Vec2f getCursorNativePos(Vec2f dst, int cursorIndex) {
        return dst.set(touchMonitor.getCursorPos(cursorIndex));
    }

    @Override
    public Vec2f getCursorPos(Vec2f dst, int cursorIndex) {
        final Vec2f nativePos = touchMonitor.getCursorPos(cursorIndex);
        final float y = (Jpize.getHeight() - nativePos.y);
        return dst.set(nativePos.x, y);
    }

    @Override
    public float getCursorX(int cursorIndex) {
        return touchMonitor.getCursorPos(cursorIndex).getX();
    }

    @Override
    public float getCursorNativeY(int cursorIndex) {
        return touchMonitor.getCursorPos(cursorIndex).getY();
    }

    @Override
    public void setCursorPos(double x, double y) { }

    @Override
    public Recti getPreeditCursorRectangle() {
        return new Recti(0, 0, 0, 0);
    }

    @Override
    public void setPreeditCursorRectangle(int x, int y, int width, int height) { }

    @Override
    public IntBuffer getPreeditCandidate(int index) {
        return IntBuffer.allocate(0);
    }

    @Override
    public void resetPreeditText() { }

}