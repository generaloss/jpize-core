package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.app.GlfwContext;
import jpize.lwjgl.app.GlfwContextManager;
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
        final GlfwContext context = GlfwContextManager.instance().getContext(windowID);
        context.makeCurrent();
    }


    public void addIMEStatusCallback(GlfwIMEStatusCallback callback) {
        imeStatus.add(callback);

        if(callbackIMEStatus != null) return;
        callbackIMEStatus = glfwSetIMEStatusCallback(windowID, (windowID) -> {
            this.makeContextCurrent();
            imeStatus.forEach(GlfwIMEStatusCallback::invoke);
        });
    }

    public void removeIMEStatusCallback(GlfwIMEStatusCallback callback) {
        imeStatus.remove(callback);
    }

    @Override
    public void addExitCallback(ExitCallback callback) {
        super.addExitCallback(callback);

        if(callbackClose != null) return;
        callbackClose = glfwSetWindowCloseCallback(windowID, (ID) -> {
            this.makeContextCurrent();
            exit.forEach(ExitCallback::invoke);
        });
    }

    @Override
    public void addContentScale(ContentScaleCallback callback) {
        super.addContentScale(callback);

        if(callbackContentScale != null) return;
        callbackContentScale = glfwSetWindowContentScaleCallback(windowID, (ID, scaleX, scaleY) -> {
            this.makeContextCurrent();
            contentScale.forEach(c -> c.invoke(scaleX, scaleY));
        });
    }

    @Override
    public void addWindowFocus(WindowFocusCallback callback) {
        super.addWindowFocus(callback);

        if(callbackFocus != null) return;
        callbackFocus = glfwSetWindowFocusCallback(windowID, (ID, focused) -> {
            this.makeContextCurrent();
            windowFocus.forEach(c -> c.invoke(focused));
        });
    }

    @Override
    public void addWindowIconify(WindowIconifyCallback callback) {
        super.addWindowIconify(callback);

        if(callbackIconify != null) return;
        callbackIconify = glfwSetWindowIconifyCallback(windowID, (ID, iconified) -> {
            this.makeContextCurrent();
            windowIconify.forEach(c -> c.invoke(iconified));
        });
    }

    @Override
    public void addWindowMaximize(WindowMaximizeCallback callback) {
        super.addWindowMaximize(callback);

        if(callbackMaximize != null) return;
        callbackMaximize = glfwSetWindowMaximizeCallback(windowID, (ID, maximized) -> {
            this.makeContextCurrent();
            windowMaximize.forEach(c -> c.invoke(maximized));
        });
    }

    @Override
    public void addWindowPos(WindowPosCallback callback) {
        super.addWindowPos(callback);

        if(callbackPos != null) return;
        callbackPos = glfwSetWindowPosCallback(windowID, (ID, x, y) -> {
            this.makeContextCurrent();
            windowPosition.forEach(c -> c.invoke(x, y));
        });
    }

    @Override
    public void addWindowRefresh(WindowRefreshCallback callback) {
        super.addWindowRefresh(callback);

        if(callbackRefresh != null) return;
        callbackRefresh = glfwSetWindowRefreshCallback(windowID, (ID) -> {
            this.makeContextCurrent();
            windowRefresh.forEach(WindowRefreshCallback::invoke);
        });
    }

    @Override
    public void addWindowSize(WindowSizeCallback callback) {
        super.addWindowSize(callback);

        if(callbackSize != null) return;
        callbackSize = glfwSetWindowSizeCallback(windowID, (ID, width, height) -> {
            this.makeContextCurrent();
            windowSize.forEach(c -> c.invoke(width, height));
        });
    }

    @Override
    public void addFramebufferSize(FramebufferSizeCallback callback) {
        super.addFramebufferSize(callback);

        if(callbackFramebufferSize != null) return;
        callbackFramebufferSize = glfwSetFramebufferSizeCallback(windowID, (ID, width, height) -> {
            this.makeContextCurrent();
            framebufferSize.forEach(c -> c.invoke(width, height));
        });
    }

    @Override
    public void addCursorPos(CursorPosCallback callback) {
        super.addCursorPos(callback);

        if(callbackCursorPos != null) return;
        callbackCursorPos = glfwSetCursorPosCallback(windowID, (windowID, x, y) -> {
            this.makeContextCurrent();
            cursorPosition.forEach(c -> c.invoke((float) x, (float) y));
        });
    }

    @Override
    public void addCursorEnter(CursorEnterCallback callback) {
        super.addCursorEnter(callback);

        if(callbackCursorEnter != null) return;
        callbackCursorEnter = glfwSetCursorEnterCallback(windowID, (windowID, entered) -> {
            this.makeContextCurrent();
            cursorEnter.forEach(c -> c.invoke(entered));
        });
    }

    @Override
    public void addMouseButton(MouseButtonCallback callback) {
        super.addMouseButton(callback);

        if(callbackMouseButton != null) return;
        callbackMouseButton = glfwSetMouseButtonCallback(windowID, (windowID, rawButton, rawAction, rawMods) -> {
            this.makeContextCurrent();
            final MouseBtn button = GlfwMouseBtn.byGlfwValue(rawButton);
            final Action action = GlfwAction.byGlfwValue(rawAction);
            final GlfwMods mods = new GlfwMods(rawMods);
            mouseButton.forEach(c -> c.invoke(button, action, mods));
        });
    }

    @Override
    public void addScroll(ScrollCallback callback) {
        super.addScroll(callback);

        if(callbackScroll != null) return;
        callbackScroll = glfwSetScrollCallback(windowID, (windowID, offsetX, offsetY) -> {
            this.makeContextCurrent();
            scroll.forEach(c -> c.invoke((float) offsetX, (float) offsetY));
        });
    }

    @Override
    public void addCharMods(CharModsCallback callback) {
        super.addCharMods(callback);

        if(callbackCharMods != null) return;
        callbackCharMods = glfwSetCharModsCallback(windowID, (windowID, codepoint, rawMods) -> {
            this.makeContextCurrent();
            final GlfwMods mods = new GlfwMods(rawMods);
            charMods.forEach(c -> c.invoke((char) codepoint, mods));
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
            final Action action = GlfwAction.byGlfwValue(rawAction);
            final GlfwMods mods = new GlfwMods(rawMods);
            this.key.forEach(c -> c.invoke(key, scancode, action, mods));
        });
    }

    @Override
    public void addChar(CharCallback callback) {
        super.addChar(callback);

        if(callbackChar != null) return;
        callbackChar = glfwSetCharCallback(windowID, (windowID, codepoint) -> {
            this.makeContextCurrent();
            character.forEach(c -> c.invoke((char) codepoint));
        });
    }

    @Override
    public void addPreedit(PreeditCallback callback) { //! ???
        super.addPreedit(callback);

        if(callbackPreedit != null) return;
        callbackPreedit = glfwSetPreeditCallback(windowID, (windowID, preeditCount, preeditStringPointer, blockCount, blockSizesPointer, focusedBlock, caret) -> {
            this.makeContextCurrent();
            preedit.forEach(c -> c.invoke(
                preeditCount, preeditStringPointer, blockCount, blockSizesPointer, focusedBlock, caret
            ));
        });
    }

    @Override
    public void addPreeditCandidate(PreeditCandidateCallback callback) { //! ???
        super.addPreeditCandidate(callback);

        if(callbackPreeditCandidate != null) return;
        callbackPreeditCandidate = glfwSetPreeditCandidateCallback(windowID, (windowID, candidatesCount, selectedIndex, pageStart, pageSize) -> {
            this.makeContextCurrent();
            preeditCandidate.forEach(c -> c.invoke(candidatesCount, selectedIndex, pageStart, pageSize));
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

            drop.forEach(c -> c.invoke(files));
        });
    }

}
