package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.context.GlfwContext;
import jpize.lwjgl.glfw.context.GlfwContextManager;
import jpize.context.callback.*;
import jpize.context.input.Action;
import jpize.context.input.Key;
import jpize.context.input.MouseBtn;
import jpize.lwjgl.glfw.input.GlfwMods;
import jpize.lwjgl.glfw.input.GlfwKey;
import jpize.lwjgl.glfw.input.GlfwMouseBtn;
import jpize.lwjgl.glfw.window.GlfwWindow;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwCallbacks extends AbstractCallbacks {

    private final long windowID;

    private Callback callbackCursorPos, callbackCursorEnter, callbackMouseButton;
    private Callback callbackScroll, callbackIMEStatus, callbackCharMods, callbackKey;
    private Callback callbackChar, callbackPreedit, callbackPreeditCandidate, callbackDrop;
    private Callback callbackClose, callbackContentScale, callbackFocus, callbackIconify;
    private Callback callbackMaximize, callbackPos, callbackRefresh, callbackWindowResize, callbackFramebufferResize;

    public GlfwCallbacks(GlfwWindow window) {
        this.windowID = window.getID();
    }


    private void makeContextCurrent() {
        final GlfwContext context = GlfwContextManager.instance().getContext(windowID);
        if(context != null)
            context.makeCurrent();
    }


    @Override
    public void addIMEStatus(Runnable callback) {
        super.addIMEStatus(callback);

        if(callbackIMEStatus != null) return;
        callbackIMEStatus = glfwSetIMEStatusCallback(windowID, (windowID) -> {
            this.makeContextCurrent();
            super.invokeIMEStatus();
        });
    }

    @Override
    public void addCloseContext(Runnable callback) {
        super.addCloseContext(callback);

        if(callbackClose != null) return;
        callbackClose = glfwSetWindowCloseCallback(windowID, (ID) -> {
            this.makeContextCurrent();
            super.invokeCloseContext();
        });
    }

    @Override
    public void addContentScale(ContentScaleCallback callback) {
        super.addContentScale(callback);

        if(callbackContentScale != null) return;
        callbackContentScale = glfwSetWindowContentScaleCallback(windowID, (ID, scaleX, scaleY) -> {
            this.makeContextCurrent();
            super.invokeContentScale(scaleX, scaleY);
        });
    }

    @Override
    public void addWindowFocus(WindowFocusCallback callback) {
        super.addWindowFocus(callback);

        if(callbackFocus != null) return;
        callbackFocus = glfwSetWindowFocusCallback(windowID, (ID, focused) -> {
            this.makeContextCurrent();
            super.invokeWindowFocus(focused);
        });
    }

    @Override
    public void addWindowIconify(WindowIconifyCallback callback) {
        super.addWindowIconify(callback);

        if(callbackIconify != null) return;
        callbackIconify = glfwSetWindowIconifyCallback(windowID, (ID, iconified) -> {
            this.makeContextCurrent();
            super.invokeWindowIconify(iconified);
        });
    }

    @Override
    public void addWindowMaximize(WindowMaximizeCallback callback) {
        super.addWindowMaximize(callback);

        if(callbackMaximize != null) return;
        callbackMaximize = glfwSetWindowMaximizeCallback(windowID, (ID, maximized) -> {
            this.makeContextCurrent();
            super.invokeWindowMaximize(maximized);
        });
    }

    @Override
    public void addWindowPos(WindowPosCallback callback) {
        super.addWindowPos(callback);

        if(callbackPos != null) return;
        callbackPos = glfwSetWindowPosCallback(windowID, (ID, x, y) -> {
            this.makeContextCurrent();
            super.invokeWindowPos(x, y);
        });
    }

    @Override
    public void addWindowRefresh(Runnable callback) {
        super.addWindowRefresh(callback);

        if(callbackRefresh != null) return;
        callbackRefresh = glfwSetWindowRefreshCallback(windowID, (ID) -> {
            this.makeContextCurrent();
            super.invokeWindowRefresh();
        });
    }

    @Override
    public void addWindowResize(ResizeCallback callback) {
        super.addResize(callback);

        if(callbackWindowResize != null) return;
        callbackWindowResize = glfwSetWindowSizeCallback(windowID, (ID, width, height) -> {
            this.makeContextCurrent();
            super.invokeResize(width, height);
        });
    }

    @Override
    public void addResize(ResizeCallback callback) {
        super.addResize(callback);

        if(callbackFramebufferResize != null) return;
        callbackFramebufferResize = glfwSetFramebufferSizeCallback(windowID, (ID, width, height) -> {
            this.makeContextCurrent();
            super.invokeResize(width, height);
        });
    }

    @Override
    public void addCursorPos(CursorPosCallback callback) {
        super.addCursorPos(callback);

        if(callbackCursorPos != null) return;
        callbackCursorPos = glfwSetCursorPosCallback(windowID, (windowID, x, y) -> {
            this.makeContextCurrent();
            super.invokeCursorPos(0, (float) x, (float) y);
        });
    }

    @Override
    public void addCursorEnter(CursorEnterCallback callback) {
        super.addCursorEnter(callback);

        if(callbackCursorEnter != null) return;
        callbackCursorEnter = glfwSetCursorEnterCallback(windowID, (windowID, entered) -> {
            this.makeContextCurrent();
            super.invokeCursorEnter(0, entered);
        });
    }

    @Override
    public void addMouseButton(MouseButtonCallback callback) {
        super.addMouseButton(callback);

        if(callbackMouseButton != null) return;
        callbackMouseButton = glfwSetMouseButtonCallback(windowID, (windowID, rawButton, rawAction, rawMods) -> {
            this.makeContextCurrent();
            final MouseBtn button = GlfwMouseBtn.byGlfwValue(rawButton);
            final Action action = ((rawAction == GLFW_PRESS) ? Action.DOWN : Action.UP);
            final GlfwMods mods = new GlfwMods(rawMods);
            super.invokeMouseButton(0, button, action, mods);
        });
    }

    @Override
    public void addScroll(ScrollCallback callback) {
        super.addScroll(callback);

        if(callbackScroll != null) return;
        callbackScroll = glfwSetScrollCallback(windowID, (windowID, offsetX, offsetY) -> {
            this.makeContextCurrent();
            super.invokeScroll((float) offsetX, (float) offsetY);
        });
    }

    @Override
    public void addCharMods(CharModsCallback callback) {
        super.addCharMods(callback);

        if(callbackCharMods != null) return;
        callbackCharMods = glfwSetCharModsCallback(windowID, (windowID, codepoint, rawMods) -> {
            this.makeContextCurrent();
            final GlfwMods mods = new GlfwMods(rawMods);
            super.invokeCharMods((char) codepoint, mods);
        });
    }

    @Override
    public void addKey(KeyCallback callback) {
        super.addKey(callback);

        if(callbackKey != null) return;
        callbackKey = glfwSetKeyCallback(windowID, (windowID, rawKey, scancode, rawAction, rawMods) -> {
            this.makeContextCurrent();
            final Key key = GlfwKey.byGlfwValue(rawKey);
            if(key == null)
                return;
            final Action action = switch(rawAction) {
                case GLFW_PRESS, GLFW_REPEAT -> Action.PRESSED;
                default -> Action.UP;
            };
            final GlfwMods mods = new GlfwMods(rawMods);
            super.invokeKey(key, scancode, action, mods);
        });
    }

    @Override
    public void addChar(CharCallback callback) {
        super.addChar(callback);

        if(callbackChar != null) return;
        callbackChar = glfwSetCharCallback(windowID, (windowID, codepoint) -> {
            this.makeContextCurrent();
            super.invokeChar((char) codepoint);
        });
    }

    @Override
    public void addPreedit(PreeditCallback callback) { //! ???
        super.addPreedit(callback);

        if(callbackPreedit != null) return;
        callbackPreedit = glfwSetPreeditCallback(windowID, (windowID, preeditCount, preeditStringPointer, blockCount, blockSizesPointer, focusedBlock, caret) -> {
            this.makeContextCurrent();
            super.invokePreedit(preeditCount, preeditStringPointer, blockCount, blockSizesPointer, focusedBlock, caret);
        });
    }

    @Override
    public void addPreeditCandidate(PreeditCandidateCallback callback) { //! ???
        super.addPreeditCandidate(callback);

        if(callbackPreeditCandidate != null) return;
        callbackPreeditCandidate = glfwSetPreeditCandidateCallback(windowID, (windowID, candidatesCount, selectedIndex, pageStart, pageSize) -> {
            this.makeContextCurrent();
            super.invokePreeditCandidate(candidatesCount, selectedIndex, pageStart, pageSize);
        });
    }

    @Override
    public void addDrop(DropCallback callback) {
        super.addDrop(callback);

        if(callbackDrop != null) return;
        callbackDrop = glfwSetDropCallback(windowID, (windowID, capacity, address) -> {
            this.makeContextCurrent();
            final PointerBuffer pointerBuf = MemoryUtil.memPointerBuffer(address, capacity);

            final String[] files = new String[capacity];
            for(int i = 0; i < capacity; i++)
                files[i] = MemoryUtil.memUTF8(pointerBuf.get(i));

            super.invokeDrop(files);
        });
    }

}
