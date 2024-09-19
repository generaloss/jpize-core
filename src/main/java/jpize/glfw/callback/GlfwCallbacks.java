package jpize.glfw.callback;

import jpize.glfw.input.GlfwAction;
import jpize.glfw.input.GlfwMods;
import jpize.glfw.input.Key;
import jpize.glfw.input.MouseBtn;
import jpize.glfw.window.GlfwWindow;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwCallbacks {

    private final GlfwWindow window;
    private final long windowID;

    private Callback callbackCursorPos, callbackCursorEnter, callbackMouseButton;
    private Callback callbackScroll, callbackIMEStatus, callbackCharMods, callbackKey;
    private Callback callbackChar, callbackPreedit, callbackPreeditCandidate, callbackDrop;
    private Callback callbackClose, callbackContentScale, callbackFocus, callbackIconify;
    private Callback callbackMaximize, callbackPos, callbackRefresh, callbackSize, callbackFramebufferSize;

    private final List<GlfwCursorPosCallback> callbacksCursorPos = new CopyOnWriteArrayList<>();
    private final List<GlfwCursorEnterCallback> callbacksCursorEnter = new CopyOnWriteArrayList<>();
    private final List<GlfwMouseButtonCallback> callbacksMouseButton = new CopyOnWriteArrayList<>();
    private final List<GlfwScrollCallback> callbacksScroll = new CopyOnWriteArrayList<>();
    private final List<GlfwIMEStatusCallback> callbacksIMEStatus = new CopyOnWriteArrayList<>();
    private final List<GlfwCharModsCallback> callbacksCharMods = new CopyOnWriteArrayList<>();
    private final List<GlfwKeyCallback> callbacksKey = new CopyOnWriteArrayList<>();
    private final List<GlfwCharCallback> callbacksChar = new CopyOnWriteArrayList<>();
    private final List<GlfwPreeditCallback> callbacksPreedit = new CopyOnWriteArrayList<>();
    private final List<GlfwPreeditCandidateCallback> callbacksPreeditCandidate = new CopyOnWriteArrayList<>();
    private final List<GlfwDropCallback> callbacksDrop = new CopyOnWriteArrayList<>();
    private final List<GlfwWindowCloseCallback> callbacksClose = new CopyOnWriteArrayList<>();
    private final List<GlfwWindowContentScaleCallback> callbacksContentScale = new CopyOnWriteArrayList<>();
    private final List<GlfwWindowFocusCallback> callbacksFocus = new CopyOnWriteArrayList<>();
    private final List<GlfwWindowIconifyCallback> callbacksIconify = new CopyOnWriteArrayList<>();
    private final List<GlfwWindowMaximizeCallback> callbacksMaximize = new CopyOnWriteArrayList<>();
    private final List<GlfwWindowPosCallback> callbacksPos = new CopyOnWriteArrayList<>();
    private final List<GlfwWindowRefreshCallback> callbacksRefresh = new CopyOnWriteArrayList<>();
    private final List<GlfwWindowSizeCallback> callbacksSize = new CopyOnWriteArrayList<>();
    private final List<GlfwFramebufferSizeCallback> callbacksFramebufferSize = new CopyOnWriteArrayList<>();

    public GlfwCallbacks(GlfwWindow window) {
        this.window = window;
        this.windowID = window.getID();
    }


    public void addWindowCloseCallback(GlfwWindowCloseCallback callback) {
        callbacksClose.add(callback);
        if(callbackClose == null){
            callbackClose = glfwSetWindowCloseCallback(windowID, (ID) -> {
                window.makeContextCurrent();
                callbacksClose.forEach(c -> c.invoke(window));
            });
        }
    }

    public void removeWindowCloseCallback(GlfwWindowCloseCallback callback) {
        callbacksClose.remove(callback);
    }


    public void addWindowContentScaleCallback(GlfwWindowContentScaleCallback callback) {
        callbacksContentScale.add(callback);
        if(callbackContentScale == null){
            callbackContentScale = glfwSetWindowContentScaleCallback(windowID, (ID, scaleX, scaleY) -> {
                window.makeContextCurrent();
                callbacksContentScale.forEach(c -> c.invoke(window, scaleX, scaleY));
            });
        }
    }

    public void removeWindowContentScaleCallback(GlfwWindowContentScaleCallback callback) {
        callbacksContentScale.remove(callback);
    }


    public void addWindowFocusCallback(GlfwWindowFocusCallback callback) {
        callbacksFocus.add(callback);
        if(callbackFocus == null){
            callbackFocus = glfwSetWindowFocusCallback(windowID, (ID, focused) -> {
                window.makeContextCurrent();
                callbacksFocus.forEach(c -> c.invoke(window, focused));
            });
        }
    }

    public void removeWindowFocusCallback(GlfwWindowFocusCallback callback) {
        callbacksFocus.remove(callback);
    }


    public void addWindowIconifyCallback(GlfwWindowIconifyCallback callback) {
        callbacksIconify.add(callback);
        if(callbackIconify == null){
            callbackIconify = glfwSetWindowIconifyCallback(windowID, (ID, iconified) -> {
                window.makeContextCurrent();
                callbacksIconify.forEach(c -> c.invoke(window, iconified));
            });
        }
    }

    public void removeWindowIconifyCallback(GlfwWindowIconifyCallback callback) {
        callbacksIconify.remove(callback);
    }


    public void addWindowMaximizeCallback(GlfwWindowMaximizeCallback callback) {
        callbacksMaximize.add(callback);
        if(callbackMaximize == null){
            callbackMaximize = glfwSetWindowMaximizeCallback(windowID, (ID, maximized) -> {
                window.makeContextCurrent();
                callbacksMaximize.forEach(c -> c.invoke(window, maximized));
            });
        }
    }

    public void removeWindowMaximizeCallback(GlfwWindowMaximizeCallback callback) {
        callbacksMaximize.remove(callback);
    }


    public void addWindowPosCallback(GlfwWindowPosCallback callback) {
        System.out.println(this + " " + windowID);
        callbacksPos.add(callback);
        if(callbackPos == null){
            callbackPos = glfwSetWindowPosCallback(windowID, (ID, x, y) -> {
                window.makeContextCurrent();
                callbacksPos.forEach(c -> c.invoke(window, x, y));
            });
        }
    }

    public void removeWindowPosCallback(GlfwWindowPosCallback callback) {
        callbacksPos.remove(callback);
    }


    public void addWindowRefreshCallback(GlfwWindowRefreshCallback callback) {
        callbacksRefresh.add(callback);
        if(callbackRefresh == null){
            callbackRefresh = glfwSetWindowRefreshCallback(windowID, (ID) -> {
                window.makeContextCurrent();
                callbacksRefresh.forEach(c -> c.invoke(window));
            });
        }
    }

    public void removeWindowRefreshCallback(GlfwWindowRefreshCallback callback) {
        callbacksRefresh.remove(callback);
    }


    public void addWindowSizeCallback(GlfwWindowSizeCallback callback) {
        callbacksSize.add(callback);
        if(callbackSize == null){
            callbackSize = glfwSetWindowSizeCallback(windowID, (ID, width, height) -> {
                window.makeContextCurrent();
                callbacksSize.forEach(c -> c.invoke(window, width, height));
            });
        }
    }

    public void removeWindowSizeCallback(GlfwWindowSizeCallback callback) {
        callbacksSize.remove(callback);
    }


    public void addFramebufferSizeCallback(GlfwFramebufferSizeCallback callback) {
        callbacksFramebufferSize.add(callback);
        if(callbackFramebufferSize == null){
            callbackFramebufferSize = glfwSetFramebufferSizeCallback(windowID, (ID, width, height) -> {
                window.makeContextCurrent();
                callbacksFramebufferSize.forEach(c -> c.invoke(window, width, height));
            });
        }
    }

    public void removeFramebufferSizeCallback(GlfwFramebufferSizeCallback callback) {
        callbacksFramebufferSize.remove(callback);
    }


    public void addCursorPosCallback(GlfwCursorPosCallback callback) {
        callbacksCursorPos.add(callback);
        if(callbackCursorPos == null){
            callbackCursorPos = glfwSetCursorPosCallback(windowID, (windowID, x, y) -> {
                window.makeContextCurrent();
                callbacksCursorPos.forEach(c -> c.invoke(window, (float) x, (float) y));
            });
        }
    }

    public void removeCursorPosCallback(GlfwCursorPosCallback callback) {
        callbacksCursorPos.remove(callback);
    }


    public void addCursorEnterCallback(GlfwCursorEnterCallback callback) {
        callbacksCursorEnter.add(callback);
        if(callbackCursorEnter == null){
            callbackCursorEnter = glfwSetCursorEnterCallback(windowID, (windowID, entered) -> {
                window.makeContextCurrent();
                callbacksCursorEnter.forEach(c -> c.invoke(window, entered));
            });
        }
    }

    public void removeCursorEnterCallback(GlfwCursorEnterCallback callback) {
        callbacksCursorEnter.remove(callback);
    }


    public void addMouseButtonCallback(GlfwMouseButtonCallback callback) {
        callbacksMouseButton.add(callback);
        if(callbackMouseButton == null){
            callbackMouseButton = glfwSetMouseButtonCallback(windowID, (windowID, rawButton, rawAction, rawMods) -> {
                window.makeContextCurrent();
                final MouseBtn button = MouseBtn.byValue(rawButton);
                final GlfwAction action = GlfwAction.byValue(rawAction);
                final GlfwMods mods = new GlfwMods(rawMods);
                callbacksMouseButton.forEach(c -> c.invoke(window, button, action, mods));
            });
        }
    }

    public void removeMouseButtonCallback(GlfwMouseButtonCallback callback) {
        callbacksMouseButton.remove(callback);
    }


    public void addScrollCallback(GlfwScrollCallback callback) {
        callbacksScroll.add(callback);
        if(callbackScroll == null){
            callbackScroll = glfwSetScrollCallback(windowID, (windowID, offsetX, offsetY) -> {
                window.makeContextCurrent();
                callbacksScroll.forEach(c -> c.invoke(window, (float) offsetX, (float) offsetY));
            });
        }
    }

    public void removeScrollCallback(GlfwScrollCallback callback) {
        callbacksScroll.remove(callback);
    }


    public void addIMEStatusCallback(GlfwIMEStatusCallback callback) {
        callbacksIMEStatus.add(callback);
        if(callbackIMEStatus == null){
            callbackIMEStatus = glfwSetIMEStatusCallback(windowID, (windowID) -> {
                window.makeContextCurrent();
                callbacksIMEStatus.forEach(c -> c.invoke(window));
            });
        }
    }

    public void removeIMEStatusCallback(GlfwIMEStatusCallback callback) {
        callbacksIMEStatus.remove(callback);
    }


    public void addCharModsCallback(GlfwCharModsCallback callback) {
        callbacksCharMods.add(callback);
        if(callbackCharMods == null){
            callbackCharMods = glfwSetCharModsCallback(windowID, (windowID, codepoint, rawMods) -> {
                window.makeContextCurrent();
                final GlfwMods mods = new GlfwMods(rawMods);
                callbacksCharMods.forEach(c -> c.invoke(window, (char) codepoint, mods));
            });
        }
    }

    public void removeCharModsCallback(GlfwCharModsCallback callback) {
        callbacksCharMods.remove(callback);
    }


    public void addKeyCallback(GlfwKeyCallback callback) {
        callbacksKey.add(callback);
        if(callbackKey == null){
            callbackKey = glfwSetKeyCallback(windowID, (windowID, rawKey, scancode, rawAction, rawMods) -> {
                window.makeContextCurrent();
                final Key key = Key.byValue(rawKey);
                final GlfwAction action = GlfwAction.byValue(rawAction);
                final GlfwMods mods = new GlfwMods(rawMods);
                callbacksKey.forEach(c -> c.invoke(window, key, scancode, action, mods));
            });
        }
    }

    public void removeKeyCallback(GlfwKeyCallback callback) {
        callbacksKey.remove(callback);
    }


    public void addCharCallback(GlfwCharCallback callback) {
        callbacksChar.add(callback);
        if(callbackChar == null){
            callbackChar = glfwSetCharCallback(windowID, (windowID, codepoint) -> {
                window.makeContextCurrent();
                callbacksChar.forEach(c -> c.invoke(window, (char) codepoint));
            });
        }
    }

    public void removeCharCallback(GlfwCharCallback callback) {
        callbacksChar.remove(callback);
    }


    public void addPreeditCallback(GlfwPreeditCallback callback) { //! ???
        callbacksPreedit.add(callback);
        if(callbackPreedit == null){
            callbackPreedit = glfwSetPreeditCallback(windowID, (windowID, preeditCount, preeditStringPointer, blockCount, blockSizesPointer, focusedBlock, caret) -> {
                window.makeContextCurrent();
                callbacksPreedit.forEach(c -> c.invoke(window, preeditCount, preeditStringPointer, blockCount, blockSizesPointer, focusedBlock, caret));
            });
        }
    }

    public void removePreeditCallback(GlfwPreeditCallback callback) { //! ???
        callbacksPreedit.remove(callback);
    }


    public void addPreeditCandidateCallback(GlfwPreeditCandidateCallback callback) { //! ???
        callbacksPreeditCandidate.add(callback);
        if(callbackPreeditCandidate == null){
            callbackPreeditCandidate = glfwSetPreeditCandidateCallback(windowID, (windowID, candidatesCount, selectedIndex, pageStart, pageSize) -> {
                window.makeContextCurrent();
                callbacksPreeditCandidate.forEach(c -> c.invoke(window, candidatesCount, selectedIndex, pageStart, pageSize));
            });
        }
    }

    public void removePreeditCandidateCallback(GlfwPreeditCandidateCallback callback) { //! ???
        callbacksPreeditCandidate.remove(callback);
    }


    public void addDropCallback(GlfwDropCallback callback) {
        callbacksDrop.add(callback);
        if(callbackDrop == null){
            callbackDrop = glfwSetDropCallback(windowID, (windowID, capacity, address) -> {
                window.makeContextCurrent();
                final PointerBuffer pointerBuf = MemoryUtil.memPointerBuffer(address, capacity);

                final String[] files = new String[capacity];
                for(int i = 0; i < capacity; i++)
                    files[i] = MemoryUtil.memUTF8(pointerBuf.get(i)); //! MemoryUtil.memByteBufferNT1(pointerBuf.get(i))

                callbacksDrop.forEach(c -> c.invoke(window, files));
            });
        }
    }

    public void removeDropCallback(GlfwDropCallback callback) {
        callbacksDrop.remove(callback);
    }

}
