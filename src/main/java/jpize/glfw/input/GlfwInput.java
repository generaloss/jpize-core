package jpize.glfw.input;

import jpize.glfw.Glfw;
import jpize.glfw.cursor.GlfwCursor;
import jpize.glfw.window.GlfwWindow;
import jpize.util.math.geometry.Intersector;
import jpize.util.math.vector.Vec2f;
import org.lwjgl.system.MemoryUtil;

import java.awt.*;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwInput {

    private final GlfwWindow window;
    private final long windowID;
    private final InputMonitor inputMonitor;

    public GlfwInput(GlfwWindow window) {
        this.window = window;
        this.windowID = window.getID();
        this.inputMonitor = new InputMonitor(window);
    }

    public InputMonitor getInputMonitor() {
        return inputMonitor;
    }
    
    
    public GlfwAction getKey(Key key) {
        return GlfwAction.byValue(glfwGetKey(windowID, key.value));
    }
    
    public boolean isKeyDown(Key key) {
        return inputMonitor.isKeyDown(key);
    }
    
    public boolean isKeyPressed(Key key) {
        return this.getKey(key).isPressed();
    }

    public boolean isKeyUp(Key key) {
        return inputMonitor.isKeyUp(key);
    }

    public float getScrollX() {
        return inputMonitor.getScrollX();
    }

    public float getScrollY() {
        return inputMonitor.getScrollY();
    }
    

    public GlfwAction getMouseButton(MouseBtn button) {
        return GlfwAction.byValue(glfwGetMouseButton(windowID, button.value));
    }

    public boolean isButtonDown(MouseBtn button) {
        return inputMonitor.isMouseButtonDown(button);
    }

    public boolean isButtonPressed(MouseBtn button) {
        return this.getMouseButton(button).isPressed();
    }

    public boolean isButtonUp(MouseBtn button) {
        return inputMonitor.isMouseButtonUp(button);
    }


    public String getClipboardString() {
        return glfwGetClipboardString(windowID);
    }

    public void setClipboardString(CharSequence string) {
        glfwSetClipboardString(windowID, string);
    }


    public void setCursor(GlfwCursor cursor) {
        final long cursorID = (cursor == null) ? 0L : cursor.getID();
        glfwSetCursor(windowID, cursorID);
    }


    public int getInputMode(GlfwMode mode) {
        return glfwGetInputMode(windowID, mode.value);
    }

    public GlfwCursorMode getInputModeCursor() {
        return GlfwCursorMode.valueOf(this.getInputMode(GlfwMode.CURSOR));
    }

    public boolean getInputModeStickyKeys() {
        return this.getInputMode(GlfwMode.STICKY_KEYS) == Glfw.TRUE;
    }

    public boolean getInputModeStickyMouseButtons() {
        return this.getInputMode(GlfwMode.STICKY_MOUSE_BUTTONS) == Glfw.TRUE;
    }

    public boolean getInputModeLockKeyMods() {
        return this.getInputMode(GlfwMode.LOCK_KEY_MODS) == Glfw.TRUE;
    }

    public boolean getInputModeRawMouseMotion() {
        return this.getInputMode(GlfwMode.RAW_MOUSE_MOTION) == Glfw.TRUE;
    }


    public void setInputMode(GlfwMode mode, int value) {
        glfwSetInputMode(windowID, mode.value, value);
    }

    public void setInputModeCursor(GlfwCursorMode value) {
        this.setInputMode(GlfwMode.CURSOR, value.value);
    }

    public void setInputModeStickyKeys(boolean value) {
        this.setInputMode(GlfwMode.STICKY_KEYS, value ? Glfw.TRUE : Glfw.FALSE);
    }

    public void setInputModeStickyMouseButtons(boolean value) {
        this.setInputMode(GlfwMode.STICKY_MOUSE_BUTTONS, value ? Glfw.TRUE : Glfw.FALSE);
    }

    public void setInputModeLockKeyMods(boolean value) {
        this.setInputMode(GlfwMode.LOCK_KEY_MODS, value ? Glfw.TRUE : Glfw.FALSE);
    }

    public void setInputModeRawMouseMotion(boolean value) {
        this.setInputMode(GlfwMode.RAW_MOUSE_MOTION, value ? Glfw.TRUE : Glfw.FALSE);
    }


    public Vec2f getCursorNativePos(Vec2f dst) {
        final DoubleBuffer xBuf = MemoryUtil.memAllocDouble(1);
        final DoubleBuffer yBuf = MemoryUtil.memAllocDouble(1);
        glfwGetCursorPos(windowID, xBuf, yBuf);
        dst.set(xBuf.get(), yBuf.get());
        MemoryUtil.memFree(xBuf);
        MemoryUtil.memFree(yBuf);
        return dst;
    }

    public Vec2f getCursorPos(Vec2f dst) {
        this.getCursorNativePos(dst);
        dst.y = (window.getHeight() - dst.y);
        return dst;
    }

    public float getCursorX() {
        final DoubleBuffer buffer = MemoryUtil.memAllocDouble(1);
        glfwGetCursorPos(windowID, buffer, null);
        final float value = (float) buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    public float getCursorNativeY() {
        final DoubleBuffer buffer = MemoryUtil.memAllocDouble(1);
        glfwGetCursorPos(windowID, null, buffer);
        final float value = (float) buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    public float getCursorY() {
        return (window.getHeight() - this.getCursorNativeY());
    }

    public void setCursorPos(double x, double y) {
        glfwSetCursorPos(windowID, x, y);
    }


    public boolean isCursorInRect(double x, double y, double width, double height) {
        return Intersector.isPointInRect(this.getCursorX(), this.getCursorY(), x, y, width, height);
    }

    public boolean isCursorNativeInRect(double x, double y, double width, double height) {
        return Intersector.isPointInRect(this.getCursorX(), this.getCursorNativeY(), x, y, width, height);
    }


    public Rectangle getPreeditCursorRectangle() {
        final IntBuffer xBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer yBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer widthBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer heightBuf = MemoryUtil.memAllocInt(1);

        glfwGetPreeditCursorRectangle(windowID, xBuf, yBuf, widthBuf, heightBuf);
        final Rectangle value = new Rectangle(xBuf.get(), yBuf.get(), widthBuf.get(), heightBuf.get());

        MemoryUtil.memFree(xBuf);
        MemoryUtil.memFree(yBuf);
        MemoryUtil.memFree(widthBuf);
        MemoryUtil.memFree(heightBuf);
        return value;
    }

    public void setPreeditCursorRectangle(int x, int y, int width, int height) {
        glfwSetPreeditCursorRectangle(windowID, x, y, width, height);
    }

    public IntBuffer getPreeditCandidate(int index) {
        return glfwGetPreeditCandidate(windowID, index);
    }

    public void resetPreeditText() {
        glfwResetPreeditText(windowID);
    }


    public static GlfwInput getCurrentInput() {
        return GlfwWindow.getCurrentContext().getInput();
    }

}
