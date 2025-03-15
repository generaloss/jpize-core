package jpize.context.input;

import jpize.context.IWindow;
import jpize.util.math.geometry.Intersector;
import jpize.util.math.geometry.Recti;
import jpize.util.math.vector.Vec2f;

import java.nio.IntBuffer;

public abstract class AbstractInput {

    protected final IWindow window;

    public AbstractInput(IWindow window) {
        this.window = window;
    }


    public abstract Action getKey(Key key);

    public abstract int getKeyScancode(Key key);

    public abstract String getKeyName(Key key);


    public abstract boolean isKeyDown(Key key);

    public abstract boolean isKeyPressed(Key key);

    public abstract boolean isKeyUp(Key key);


    public abstract Action getMouseButton(int index, MouseBtn button);

    public Action getMouseButton(MouseBtn button) {
        return this.getMouseButton(0, button);
    }


    public abstract boolean isButtonDown(int index, MouseBtn button);

    public boolean isButtonDown(MouseBtn button) {
        return this.isButtonDown(0, button);
    }

    public abstract boolean isButtonPressed(int index, MouseBtn button);

    public boolean isButtonPressed(MouseBtn button) {
        return this.isButtonPressed(0, button);
    }

    public abstract boolean isButtonUp(int index, MouseBtn button);

    public boolean isButtonUp(MouseBtn button) {
        return this.isButtonUp(0, button);
    }


    public abstract float getScrollX();

    public abstract float getScrollY();


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


    public abstract Recti getPreeditCursorRectangle();

    public abstract void setPreeditCursorRectangle(int x, int y, int width, int height);

    public abstract IntBuffer getPreeditCandidate(int index);

    public abstract void resetPreeditText();

}
