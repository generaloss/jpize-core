package jpize.lwjgl.glfw.input;

import jpize.context.Jpize;
import jpize.context.input.*;
import jpize.lwjgl.context.GlfwContext;
import jpize.lwjgl.glfw.Glfw;
import jpize.lwjgl.glfw.cursor.GlfwCursor;
import jpize.util.math.geometry.Recti;
import jpize.util.math.vector.Vec2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

public class GlfwInput extends AbstractInput {

    private final long windowID;

    public GlfwInput(GlfwContext context) {
        super(context);
        this.windowID = context.getWindow().getID();
    }


    @Override
    public int getKeyScancode(Key key) {
        return GlfwKey.getScancode(key);
    }

    @Override
    public String getKeyName(Key key) {
        return GlfwKey.getKeyName(key);
    }

    @Override
    public int getMaxMousesCount() {
        return 1;
    }


    @Override
    public String getClipboardString() {
        return GLFW.glfwGetClipboardString(windowID);
    }

    @Override
    public void setClipboardString(CharSequence string) {
        GLFW.glfwSetClipboardString(windowID, string);
    }


    public void setCursor(GlfwCursor cursor) {
        final long cursorID = (cursor == null) ? 0L : cursor.getID();
        GLFW.glfwSetCursor(windowID, cursorID);
    }


    public int getInputMode(GlfwInputMode mode) {
        return GLFW.glfwGetInputMode(windowID, mode.value);
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
        return GLFW.glfwRawMouseMotionSupported();
    }


    public void setInputMode(GlfwInputMode mode, int value) {
        GLFW.glfwSetInputMode(windowID, mode.value, value);
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
    public Vec2f getCursorNativePos(Vec2f dst, int mouseIndex) {
        if(mouseIndex != 0)
            return dst.set(0F, Jpize.getHeight());
        final DoubleBuffer xBuf = MemoryUtil.memAllocDouble(1);
        final DoubleBuffer yBuf = MemoryUtil.memAllocDouble(1);
        GLFW.glfwGetCursorPos(windowID, xBuf, yBuf);
        dst.set(xBuf.get(), yBuf.get());
        MemoryUtil.memFree(xBuf);
        MemoryUtil.memFree(yBuf);
        return dst;
    }

    @Override
    public Vec2f getCursorPos(Vec2f dst, int mouseIndex) {
        if(mouseIndex != 0)
            return dst.set(0F);
        this.getCursorNativePos(dst);
        dst.y = (Jpize.getHeight() - dst.y);
        return dst;
    }

    @Override
    public float getCursorX(int mouseIndex) {
        if(mouseIndex != 0)
            return 0F;
        final DoubleBuffer buffer = MemoryUtil.memAllocDouble(1);
        GLFW.glfwGetCursorPos(windowID, buffer, null);
        final float value = (float) buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    @Override
    public float getCursorNativeY(int mouseIndex) {
        if(mouseIndex != 0)
            return Jpize.getHeight();
        final DoubleBuffer buffer = MemoryUtil.memAllocDouble(1);
        GLFW.glfwGetCursorPos(windowID, null, buffer);
        final float value = (float) buffer.get();
        MemoryUtil.memFree(buffer);
        return value;
    }

    @Override
    public void setCursorPos(double x, double y) {
        GLFW.glfwSetCursorPos(windowID, x, y);
    }

    @Override
    public Recti getPreeditCursorRectangle() {
        final IntBuffer xBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer yBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer widthBuf = MemoryUtil.memAllocInt(1);
        final IntBuffer heightBuf = MemoryUtil.memAllocInt(1);

        GLFW.glfwGetPreeditCursorRectangle(windowID, xBuf, yBuf, widthBuf, heightBuf);
        final Recti value = new Recti(xBuf.get(), yBuf.get(), widthBuf.get(), heightBuf.get());

        MemoryUtil.memFree(xBuf);
        MemoryUtil.memFree(yBuf);
        MemoryUtil.memFree(widthBuf);
        MemoryUtil.memFree(heightBuf);
        return value;
    }

    @Override
    public void setPreeditCursorRectangle(int x, int y, int width, int height) {
        GLFW.glfwSetPreeditCursorRectangle(windowID, x, y, width, height);
    }

    @Override
    public IntBuffer getPreeditCandidate(int index) {
        return GLFW.glfwGetPreeditCandidate(windowID, index);
    }

    @Override
    public void resetPreeditText() {
        GLFW.glfwResetPreeditText(windowID);
    }

}
