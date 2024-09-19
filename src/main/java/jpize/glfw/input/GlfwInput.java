package jpize.glfw.input;

import jpize.glfw.Glfw;
import jpize.glfw.cursor.GlfwCursor;
import jpize.glfw.window.GlfwWindow;
import jpize.util.math.vector.Vec2f;
import org.lwjgl.system.MemoryUtil;
import java.nio.DoubleBuffer;

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
        return this.getKey(key).isPress();
    }

    public boolean isKeyUp(Key key) {
        return inputMonitor.isKeyUp(key);
    }
    

    public GlfwAction getMouseButton(MouseBtn button) {
        return GlfwAction.byValue(glfwGetMouseButton(windowID, button.value));
    }

    public boolean isButtonDown(MouseBtn button) {
        return inputMonitor.isMouseButtonDown(button);
    }

    public boolean isButtonPressed(MouseBtn button) {
        return this.getMouseButton(button).isPress();
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
        return GlfwCursorMode.valueOf(getInputMode(GlfwMode.CURSOR));
    }

    public boolean getInputModeStickyKeys() {
        return getInputMode(GlfwMode.STICKY_KEYS) == Glfw.TRUE;
    }

    public boolean getInputModeStickyMouseButtons() {
        return getInputMode(GlfwMode.STICKY_MOUSE_BUTTONS) == Glfw.TRUE;
    }

    public boolean getInputModeLockKeyMods() {
        return getInputMode(GlfwMode.LOCK_KEY_MODS) == Glfw.TRUE;
    }

    public boolean getInputModeRawMouseMotion() {
        return getInputMode(GlfwMode.RAW_MOUSE_MOTION) == Glfw.TRUE;
    }


    public void setInputMode(GlfwMode mode, int value) {
        glfwSetInputMode(windowID, mode.value, value);
    }

    public void setInputModeCursor(GlfwCursorMode value) {
        setInputMode(GlfwMode.CURSOR, value.value);
    }

    public void setInputModeStickyKeys(boolean value) {
        setInputMode(GlfwMode.STICKY_KEYS, value ? Glfw.TRUE : Glfw.FALSE);
    }

    public void setInputModeStickyMouseButtons(boolean value) {
        setInputMode(GlfwMode.STICKY_MOUSE_BUTTONS, value ? Glfw.TRUE : Glfw.FALSE);
    }

    public void setInputModeLockKeyMods(boolean value) {
        setInputMode(GlfwMode.LOCK_KEY_MODS, value ? Glfw.TRUE : Glfw.FALSE);
    }

    public void setInputModeRawMouseMotion(boolean value) {
        setInputMode(GlfwMode.RAW_MOUSE_MOTION, value ? Glfw.TRUE : Glfw.FALSE);
    }


    public Vec2f getCursorPos() {
        final DoubleBuffer xBuf = MemoryUtil.memAllocDouble(1);
        final DoubleBuffer yBuf = MemoryUtil.memAllocDouble(1);
        glfwGetCursorPos(windowID, xBuf, yBuf);
        final Vec2f value = new Vec2f(xBuf.get(), yBuf.get());
        MemoryUtil.memFree(xBuf);
        MemoryUtil.memFree(yBuf);
        return value;
    }

    public float getCursorX() {
        final DoubleBuffer buffer = MemoryUtil.memAllocDouble(1);
        glfwGetCursorPos(windowID, buffer, null);
        final float value = (float) buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    public float getCursorY() {
        final DoubleBuffer buffer = MemoryUtil.memAllocDouble(1);
        glfwGetCursorPos(windowID, null, buffer);
        final float value = (float) buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    public void setCursorPos(double x, double y) {
        glfwSetCursorPos(windowID, x, y);
    }


    //!LWJGL3.3.4 public Rectangle getPreeditCursorRectangle() {
    //!LWJGL3.3.4     final IntBuffer xBuf = MemoryUtil.memAllocInt(1);
    //!LWJGL3.3.4     final IntBuffer yBuf = MemoryUtil.memAllocInt(1);
    //!LWJGL3.3.4     final IntBuffer widthBuf = MemoryUtil.memAllocInt(1);
    //!LWJGL3.3.4     final IntBuffer heightBuf = MemoryUtil.memAllocInt(1);

    //!LWJGL3.3.4     glfwGetPreeditCursorRectangle(windowID, xBuf, yBuf, widthBuf, heightBuf);
    //!LWJGL3.3.4     final Rectangle value = new Rectangle(xBuf.get(), yBuf.get(), widthBuf.get(), heightBuf.get());

    //!LWJGL3.3.4     MemoryUtil.memFree(xBuf);
    //!LWJGL3.3.4     MemoryUtil.memFree(yBuf);
    //!LWJGL3.3.4     MemoryUtil.memFree(widthBuf);
    //!LWJGL3.3.4     MemoryUtil.memFree(heightBuf);
    //!LWJGL3.3.4     return value;
    //!LWJGL3.3.4 }

    //!LWJGL3.3.4 public void setPreeditCursorRectangle(int x, int y, int width, int height) {
    //!LWJGL3.3.4     glfwSetPreeditCursorRectangle(windowID, x, y, width, height);
    //!LWJGL3.3.4 }

    //!LWJGL3.3.4 public IntBuffer getPreeditCandidate(int index) {
    //!LWJGL3.3.4     return glfwGetPreeditCandidate(windowID, index);
    //!LWJGL3.3.4 }

    //!LWJGL3.3.4 public void resetPreeditText() {
    //!LWJGL3.3.4     glfwResetPreeditText(windowID);
    //!LWJGL3.3.4 }


    private final Vec2f prevCursorPos = new Vec2f();

    public Vec2f getCursorRelPos() {
        final Vec2f value = getCursorPos().sub(prevCursorPos);
        prevCursorPos.set(getCursorPos());
        return value;
    }


    public static GlfwInput getCurrentInput() {
        return GlfwWindow.getCurrentContext().getInput();
    }

}
