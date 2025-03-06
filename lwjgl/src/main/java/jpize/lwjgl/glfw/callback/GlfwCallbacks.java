package jpize.lwjgl.glfw.callback;

import jpize.app.Context;
import jpize.app.ContextManager;
import jpize.io.callback.*;
import jpize.io.input.Action;
import jpize.io.input.Key;
import jpize.io.input.MouseBtn;
import jpize.lwjgl.glfw.input.GlfwAction;
import jpize.lwjgl.glfw.input.GlfwMods;
import jpize.lwjgl.glfw.input.GlfwKey;
import jpize.lwjgl.glfw.input.GlfwMouseBtn;
import jpize.lwjgl.glfw.window.GlfwWindow;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwCallbacks extends AbstractCallbacks {

    private final long windowID;

    private Callback callbackCursorPos, callbackCursorEnter, callbackMouseButton;
    private Callback callbackScroll, callbackIMEStatus, callbackCharMods, callbackKey;
    private Callback callbackChar, callbackPreedit, callbackPreeditCandidate, callbackDrop;
    private Callback callbackClose, callbackContentScale, callbackFocus, callbackIconify;
    private Callback callbackMaximize, callbackPos, callbackRefresh, callbackSize, callbackFramebufferSize;

    protected final List<GlfwIMEStatusCallback> imeStatus = new CopyOnWriteArrayList<>();

    public GlfwCallbacks(GlfwWindow window) {
        this.windowID = window.getID();
    }


    private void makeContextCurrent() {
        final Context context = ContextManager.instance().getContext(windowID);
        context.makeCurrent();
    }


    public void addIMEStatusCallback(GlfwIMEStatusCallback callback) {
        imeStatus.add(callback);

        if(callbackIMEStatus == null) return;
        callbackIMEStatus = glfwSetIMEStatusCallback(windowID, (windowID) -> {
            this.makeContextCurrent();
            imeStatus.forEach(GlfwIMEStatusCallback::invoke);
        });
    }

    public void removeIMEStatusCallback(GlfwIMEStatusCallback callback) {
        imeStatus.remove(callback);
    }

    @Override
    public void addWindowCloseCallback(ExitCallback callback) {
        super.addWindowCloseCallback(callback);

        if(callbackClose != null) return;
        callbackClose = glfwSetWindowCloseCallback(windowID, (ID) -> {
            this.makeContextCurrent();
            exit.forEach(ExitCallback::invoke);
        });
    }

    @Override
    public void addWindowContentScaleCallback(ContentScaleCallback callback) {
        super.addWindowContentScaleCallback(callback);

        if(callbackContentScale == null) return;
        callbackContentScale = glfwSetWindowContentScaleCallback(windowID, (ID, scaleX, scaleY) -> {
            this.makeContextCurrent();
            contentScale.forEach(c -> c.invoke(scaleX, scaleY));
        });
    }

    @Override
    public void addWindowFocusCallback(WindowFocusCallback callback) {
        super.addWindowFocusCallback(callback);

        if(callbackFocus == null) return;
        callbackFocus = glfwSetWindowFocusCallback(windowID, (ID, focused) -> {
            this.makeContextCurrent();
            windowFocus.forEach(c -> c.invoke(focused));
        });
    }

    @Override
    public void addWindowIconifyCallback(WindowIconifyCallback callback) {
        super.addWindowIconifyCallback(callback);

        if(callbackIconify == null) return;
        callbackIconify = glfwSetWindowIconifyCallback(windowID, (ID, iconified) -> {
            this.makeContextCurrent();
            windowIconify.forEach(c -> c.invoke(iconified));
        });
    }

    @Override
    public void addWindowMaximizeCallback(WindowMaximizeCallback callback) {
        super.addWindowMaximizeCallback(callback);

        if(callbackMaximize == null) return;
        callbackMaximize = glfwSetWindowMaximizeCallback(windowID, (ID, maximized) -> {
            this.makeContextCurrent();
            windowMaximize.forEach(c -> c.invoke(maximized));
        });
    }

    @Override
    public void addWindowPosCallback(WindowPosCallback callback) {
        super.addWindowPosCallback(callback);

        if(callbackPos == null) return;
        callbackPos = glfwSetWindowPosCallback(windowID, (ID, x, y) -> {
            this.makeContextCurrent();
            windowPosition.forEach(c -> c.invoke(x, y));
        });
    }

    @Override
    public void addWindowRefreshCallback(WindowRefreshCallback callback) {
        super.addWindowRefreshCallback(callback);

        if(callbackRefresh == null) return;
        callbackRefresh = glfwSetWindowRefreshCallback(windowID, (ID) -> {
            this.makeContextCurrent();
            windowRefresh.forEach(WindowRefreshCallback::invoke);
        });
    }

    @Override
    public void addWindowSizeCallback(WindowSizeCallback callback) {
        super.addWindowSizeCallback(callback);

        if(callbackSize == null) return;
        callbackSize = glfwSetWindowSizeCallback(windowID, (ID, width, height) -> {
            this.makeContextCurrent();
            windowSize.forEach(c -> c.invoke(width, height));
        });
    }

    @Override
    public void addFramebufferSizeCallback(FramebufferSizeCallback callback) {
        super.addFramebufferSizeCallback(callback);

        if(callbackFramebufferSize == null) return;
        callbackFramebufferSize = glfwSetFramebufferSizeCallback(windowID, (ID, width, height) -> {
            this.makeContextCurrent();
            framebufferSize.forEach(c -> c.invoke(width, height));
        });
    }

    @Override
    public void addCursorPosCallback(CursorPosCallback callback) {
        super.addCursorPosCallback(callback);

        if(callbackCursorPos == null) return;
        callbackCursorPos = glfwSetCursorPosCallback(windowID, (windowID, x, y) -> {
            this.makeContextCurrent();
            cursorPosition.forEach(c -> c.invoke((float) x, (float) y));
        });
    }

    @Override
    public void addCursorEnterCallback(CursorEnterCallback callback) {
        super.addCursorEnterCallback(callback);

        if(callbackCursorEnter == null) return;
        callbackCursorEnter = glfwSetCursorEnterCallback(windowID, (windowID, entered) -> {
            this.makeContextCurrent();
            cursorEnter.forEach(c -> c.invoke(entered));
        });
    }

    @Override
    public void addMouseButtonCallback(MouseButtonCallback callback) {
        super.addMouseButtonCallback(callback);

        if(callbackMouseButton == null) return;
        callbackMouseButton = glfwSetMouseButtonCallback(windowID, (windowID, rawButton, rawAction, rawMods) -> {
            this.makeContextCurrent();
            final MouseBtn button = GlfwMouseBtn.byGlfwValue(rawButton);
            final Action action = GlfwAction.byGlfwValue(rawAction);
            final GlfwMods mods = new GlfwMods(rawMods);
            mouseButton.forEach(c -> c.invoke(button, action, mods));
        });
    }

    @Override
    public void addScrollCallback(ScrollCallback callback) {
        super.addScrollCallback(callback);

        if(callbackScroll == null) return;
        callbackScroll = glfwSetScrollCallback(windowID, (windowID, offsetX, offsetY) -> {
            this.makeContextCurrent();
            scroll.forEach(c -> c.invoke((float) offsetX, (float) offsetY));
        });
    }

    @Override
    public void addCharModsCallback(CharModsCallback callback) {
        super.addCharModsCallback(callback);

        if(callbackCharMods == null) return;
        callbackCharMods = glfwSetCharModsCallback(windowID, (windowID, codepoint, rawMods) -> {
            this.makeContextCurrent();
            final GlfwMods mods = new GlfwMods(rawMods);
            charMods.forEach(c -> c.invoke((char) codepoint, mods));
        });
    }

    @Override
    public void addKeyCallback(KeyCallback callback) {
        super.addKeyCallback(callback);

        if(callbackKey == null) return;
        callbackKey = glfwSetKeyCallback(windowID, (windowID, rawKey, scancode, rawAction, rawMods) -> {
            this.makeContextCurrent();
            final Key key = GlfwKey.byGlfwValue(rawKey);
            if(key == null)
                return;
            final Action action = GlfwAction.byGlfwValue(rawAction);
            final GlfwMods mods = new GlfwMods(rawMods);
            this.key.forEach(c -> c.invoke(key, scancode, action, mods));
        });
    }

    @Override
    public void addCharCallback(CharCallback callback) {
        super.addCharCallback(callback);

        if(callbackChar == null) return;
        callbackChar = glfwSetCharCallback(windowID, (windowID, codepoint) -> {
            this.makeContextCurrent();
            character.forEach(c -> c.invoke((char) codepoint));
        });
    }

    @Override
    public void addPreeditCallback(PreeditCallback callback) { //! ???
        super.addPreeditCallback(callback);

        if(callbackPreedit == null) return;
        callbackPreedit = glfwSetPreeditCallback(windowID, (windowID, preeditCount, preeditStringPointer, blockCount, blockSizesPointer, focusedBlock, caret) -> {
            this.makeContextCurrent();
            preedit.forEach(c -> c.invoke(
                preeditCount, preeditStringPointer, blockCount, blockSizesPointer, focusedBlock, caret
            ));
        });
    }

    @Override
    public void addPreeditCandidateCallback(PreeditCandidateCallback callback) { //! ???
        super.addPreeditCandidateCallback(callback);

        if(callbackPreeditCandidate == null) return;
        callbackPreeditCandidate = glfwSetPreeditCandidateCallback(windowID, (windowID, candidatesCount, selectedIndex, pageStart, pageSize) -> {
            this.makeContextCurrent();
            preeditCandidate.forEach(c -> c.invoke(candidatesCount, selectedIndex, pageStart, pageSize));
        });
    }

    @Override
    public void addDropCallback(DropCallback callback) {
        super.addDropCallback(callback);

        if(callbackDrop == null) return;
        callbackDrop = glfwSetDropCallback(windowID, (windowID, capacity, address) -> {
            this.makeContextCurrent();
            final PointerBuffer pointerBuf = MemoryUtil.memPointerBuffer(address, capacity);

            final String[] files = new String[capacity];
            for(int i = 0; i < capacity; i++)
                files[i] = MemoryUtil.memUTF8(pointerBuf.get(i));

            drop.forEach(c -> c.invoke(files));
        });
    }

}
