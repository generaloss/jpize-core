package jpize.context.input;

import jpize.context.Context;
import jpize.context.Jpize;
import jpize.util.math.geometry.Intersector;
import jpize.util.math.geometry.Recti;
import jpize.util.math.vector.Vec2f;

import java.nio.IntBuffer;

public abstract class AbstractInput {

    private final InputMonitor inputMonitor;

    public AbstractInput(Context context) {
        this.inputMonitor = new InputMonitor(context);
    }

    public InputMonitor getInputMonitor() {
        return inputMonitor;
    }


    public abstract int getKeyScancode(Key key);

    public abstract String getKeyName(Key key);


    public Action getKey(Key key) {
        if(inputMonitor.isKeyDown(key))
            return Action.DOWN;
        if(inputMonitor.isKeyPressed(key))
            return Action.PRESSED;
        if(inputMonitor.isKeyUp(key))
            return Action.UP;
        return Action.NOT_PRESSED;
    }

    public boolean isKeyDown(Key key) {
        return inputMonitor.isKeyDown(key);
    }

    public boolean isKeyPressed(Key key) {
        return inputMonitor.isKeyPressed(key);
    }

    public boolean isKeyUp(Key key) {
        return inputMonitor.isKeyUp(key);
    }


    public abstract int getMaxMousesCount();

    public int getMouseButtonPressedCount(MouseBtn button) {
        return inputMonitor.getPressedCount(button);
    }

    public Action getMouseButton(int cursorIndex, MouseBtn button) {
        if(inputMonitor.isMouseButtonDown(cursorIndex, button))
            return Action.DOWN;
        if(inputMonitor.isMouseButtonPressed(cursorIndex, button))
            return Action.PRESSED;
        if(inputMonitor.isMouseButtonUp(cursorIndex, button))
            return Action.UP;
        return Action.NOT_PRESSED;
    }

    public boolean isButtonDown(int cursorIndex, MouseBtn button) {
        return inputMonitor.isMouseButtonDown(cursorIndex, button);
    }

    public boolean isButtonPressed(int cursorIndex, MouseBtn button) {
        return inputMonitor.isMouseButtonPressed(cursorIndex, button);
    }

    public boolean isButtonUp(int cursorIndex, MouseBtn button) {
        return inputMonitor.isMouseButtonUp(cursorIndex, button);
    }


    public Action getMouseButton(MouseBtn button) {
        return this.getMouseButton(0, button);
    }

    public boolean isButtonDown(MouseBtn button) {
        return this.isButtonDown(0, button);
    }

    public boolean isButtonPressed(MouseBtn button) {
        return this.isButtonPressed(0, button);
    }

    public boolean isButtonUp(MouseBtn button) {
        return this.isButtonUp(0, button);
    }


    public float getScrollX() {
        return inputMonitor.getScrollX();
    }

    public float getScrollY() {
        return inputMonitor.getScrollY();
    }


    public abstract String getClipboardString();

    public abstract void setClipboardString(CharSequence string);


    public abstract CursorMode getCursorMode();

    public abstract boolean getStickyKeys();

    public abstract boolean getStickyMouseButtons();

    public abstract boolean getLockKeyMods();

    public abstract boolean getRawMouseMotion();

    public abstract boolean isRawMouseMotionSupported();


    public abstract void setCursorMode(CursorMode value);

    public abstract void setStickyKeys(boolean value);

    public abstract void setStickyMouseButtons(boolean value);

    public abstract void setLockKeyMods(boolean value);

    public abstract void setRawMouseMotion(boolean value);



    public abstract Vec2f getCursorNativePos(Vec2f dst, int cursorIndex);

    public Vec2f getCursorNativePos(Vec2f dst) {
        return this.getCursorNativePos(dst, 0);
    }

    public abstract Vec2f getCursorPos(Vec2f dst, int cursorIndex);

    public Vec2f getCursorPos(Vec2f dst) {
        return this.getCursorPos(dst, 0);
    }

    public abstract float getCursorX(int cursorIndex);

    public float getCursorX() {
        return this.getCursorX(0);
    }

    public abstract float getCursorNativeY(int cursorIndex);

    public float getCursorNativeY() {
        return this.getCursorNativeY(0);
    }

    public float getCursorY(int cursorIndex) {
        return (Jpize.getHeight() - this.getCursorNativeY(cursorIndex));
    }

    public float getCursorY() {
        return this.getCursorY(0);
    }

    public abstract void setCursorPos(double x, double y);


    public boolean isCursorInRect(double x, double y, double width, double height) {
        return Intersector.isPointInRect(this.getCursorX(), this.getCursorY(), x, y, width, height);
    }

    public boolean isCursorOnRect(double x, double y, double width, double height) {
        return Intersector.isPointOnRect(this.getCursorX(), this.getCursorY(), x, y, width, height);
    }


    public abstract Recti getPreeditCursorRectangle();

    public abstract void setPreeditCursorRectangle(int x, int y, int width, int height);

    public abstract IntBuffer getPreeditCandidate(int index);

    public abstract void resetPreeditText();

}
