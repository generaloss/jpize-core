package jpize.lwjgl.glfw.input;

import jpize.io.input.*;
import jpize.lwjgl.glfw.Glfw;
import jpize.lwjgl.glfw.cursor.GlfwCursor;
import jpize.lwjgl.glfw.window.GlfwWindow;
import jpize.util.math.vector.Vec2f;
import org.lwjgl.system.MemoryUtil;

import java.awt.*;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwInput extends AbstractInput {

    private final long windowID;

    public GlfwInput(GlfwWindow window) {
        super(window);
        this.windowID = window.getID();
    }


    @Override
    public Action getKey(Key key) {
        final int glfwKeyValue = GlfwKey.getGlfwValue(key);
        final int glfwAction = glfwGetKey(windowID, glfwKeyValue);
        return GlfwAction.byGlfwValue(glfwAction);
    }
    @Override
    public int getKeyScancode(Key key) {
        return GlfwKey.getScancode(key);
    }

    @Override
    public Action getMouseButton(MouseBtn button) {
        final int glfwButtonValue = GlfwMouseBtn.getGlfwValue(button);
        final int glfwAction = glfwGetMouseButton(windowID, glfwButtonValue);
        return GlfwAction.byGlfwValue(glfwAction);
    }


    @Override
    public String getClipboardString() {
        return glfwGetClipboardString(windowID);
    }

    @Override
    public void setClipboardString(CharSequence string) {
        glfwSetClipboardString(windowID, string);
    }


    public void setCursor(GlfwCursor cursor) {
        final long cursorID = (cursor == null) ? 0L : cursor.getID();
        glfwSetCursor(windowID, cursorID);
    }


    public int getInputMode(GlfwInputMode mode) {
        return glfwGetInputMode(windowID, mode.value);
    }

    @Override
    public CursorMode getCursorMode() {
        return GlfwCursorMode.byGlfwValue(this.getInputMode(GlfwInputMode.CURSOR));
    }

    @Override
    public boolean getStickyKeys() {
        return (this.getInputMode(GlfwInputMode.STICKY_KEYS) == Glfw.TRUE);
    }

    @Override
    public boolean getStickyMouseButtons() {
        return (this.getInputMode(GlfwInputMode.STICKY_MOUSE_BUTTONS) == Glfw.TRUE);
    }

    @Override
    public boolean getLockKeyMods() {
        return (this.getInputMode(GlfwInputMode.LOCK_KEY_MODS) == Glfw.TRUE);
    }

    @Override
    public boolean getRawMouseMotion() {
        return (this.getInputMode(GlfwInputMode.RAW_MOUSE_MOTION) == Glfw.TRUE);
    }

    @Override
    public boolean isRawMouseMotionSupported() {
        return glfwRawMouseMotionSupported();
    }


    public void setInputMode(GlfwInputMode mode, int value) {
        glfwSetInputMode(windowID, mode.value, value);
    }

    @Override
    public void setCursorMode(CursorMode value) {
        final int modeGlfwValue = GlfwCursorMode.getGlfwValue(value);
        this.setInputMode(GlfwInputMode.CURSOR, modeGlfwValue);
    }

    @Override
    public void setStickyKeys(boolean value) {
        final int intValue = (value ? Glfw.TRUE : Glfw.FALSE);
        this.setInputMode(GlfwInputMode.STICKY_KEYS, intValue);
    }

    @Override
    public void setStickyMouseButtons(boolean value) {
        final int intValue = (value ? Glfw.TRUE : Glfw.FALSE);
        this.setInputMode(GlfwInputMode.STICKY_MOUSE_BUTTONS, intValue);
    }

    @Override
    public void setLockKeyMods(boolean value) {
        final int intValue = (value ? Glfw.TRUE : Glfw.FALSE);
        this.setInputMode(GlfwInputMode.LOCK_KEY_MODS, intValue);
    }

    @Override
    public void setRawMouseMotion(boolean value) {
        final int intValue = (value ? Glfw.TRUE : Glfw.FALSE);
        this.setInputMode(GlfwInputMode.RAW_MOUSE_MOTION, intValue);
    }


    @Override
    public Vec2f getCursorNativePos(Vec2f dst) {
        final DoubleBuffer xBuf = MemoryUtil.memAllocDouble(1);
        final DoubleBuffer yBuf = MemoryUtil.memAllocDouble(1);
        glfwGetCursorPos(windowID, xBuf, yBuf);
        dst.set(xBuf.get(), yBuf.get());
        MemoryUtil.memFree(xBuf);
        MemoryUtil.memFree(yBuf);
        return dst;
    }

    @Override
    public Vec2f getCursorPos(Vec2f dst) {
        this.getCursorNativePos(dst);
        dst.y = (window.getHeight() - dst.y);
        return dst;
    }

    @Override
    public float getCursorX() {
        final DoubleBuffer buffer = MemoryUtil.memAllocDouble(1);
        glfwGetCursorPos(windowID, buffer, null);
        final float value = (float) buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    @Override
    public float getCursorNativeY() {
        final DoubleBuffer buffer = MemoryUtil.memAllocDouble(1);
        glfwGetCursorPos(windowID, null, buffer);
        final float value = (float) buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    @Override
    public void setCursorPos(double x, double y) {
        glfwSetCursorPos(windowID, x, y);
    }

    @Override
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

    @Override
    public void setPreeditCursorRectangle(int x, int y, int width, int height) {
        glfwSetPreeditCursorRectangle(windowID, x, y, width, height);
    }

    @Override
    public IntBuffer getPreeditCandidate(int index) {
        return glfwGetPreeditCandidate(windowID, index);
    }

    @Override
    public void resetPreeditText() {
        glfwResetPreeditText(windowID);
    }

}
