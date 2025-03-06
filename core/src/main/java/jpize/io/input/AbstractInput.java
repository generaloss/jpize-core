package jpize.io.input;

import jpize.io.IWindow;
import jpize.util.math.geometry.Intersector;
import jpize.util.math.vector.Vec2f;

import java.awt.*;
import java.nio.IntBuffer;

public abstract class AbstractInput {

    protected final IWindow window;
    private final InputMonitor inputMonitor;

    public AbstractInput(IWindow window) {
        this.window = window;
        this.inputMonitor = new InputMonitor(window);
    }

    public InputMonitor getInputMonitor() {
        return inputMonitor;
    }


    public abstract Action getKey(Key key);

    public abstract int getKeyScancode(Key key);


    public boolean isKeyDown(Key key) {
        return inputMonitor.isKeyDown(key);
    }

    public boolean isKeyPressed(Key key) {
        return this.getKey(key).isPressed();
    }

    public boolean isKeyUp(Key key) {
        return inputMonitor.isKeyUp(key);
    }


    public abstract Action getMouseButton(MouseBtn button);

    public boolean isButtonDown(MouseBtn button) {
        return inputMonitor.isMouseButtonDown(button);
    }

    public boolean isButtonPressed(MouseBtn button) {
        return this.getMouseButton(button).isPressed();
    }

    public boolean isButtonUp(MouseBtn button) {
        return inputMonitor.isMouseButtonUp(button);
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



    public abstract Vec2f getCursorNativePos(Vec2f dst);

    public abstract Vec2f getCursorPos(Vec2f dst);

    public abstract float getCursorX();

    public abstract float getCursorNativeY();

    public float getCursorY() {
        return (window.getHeight() - this.getCursorNativeY());
    }

    public abstract void setCursorPos(double x, double y);


    public boolean isCursorInRect(double x, double y, double width, double height) {
        return Intersector.isPointInRect(this.getCursorX(), this.getCursorY(), x, y, width, height);
    }

    public boolean isCursorOnRect(double x, double y, double width, double height) {
        return Intersector.isPointOnRect(this.getCursorX(), this.getCursorY(), x, y, width, height);
    }


    public abstract Rectangle getPreeditCursorRectangle();

    public abstract void setPreeditCursorRectangle(int x, int y, int width, int height);

    public abstract IntBuffer getPreeditCandidate(int index);

    public abstract void resetPreeditText();

}
