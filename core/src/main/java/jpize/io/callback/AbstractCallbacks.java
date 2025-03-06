package jpize.io.callback;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractCallbacks {

    protected final List<CursorPosCallback> cursorPosition = new CopyOnWriteArrayList<>();
    protected final List<CursorEnterCallback> cursorEnter = new CopyOnWriteArrayList<>();
    protected final List<MouseButtonCallback> mouseButton = new CopyOnWriteArrayList<>();
    protected final List<ScrollCallback> scroll = new CopyOnWriteArrayList<>();
    protected final List<CharModsCallback> charMods = new CopyOnWriteArrayList<>();
    protected final List<KeyCallback> key = new CopyOnWriteArrayList<>();
    protected final List<CharCallback> character = new CopyOnWriteArrayList<>();
    protected final List<PreeditCallback> preedit = new CopyOnWriteArrayList<>();
    protected final List<PreeditCandidateCallback> preeditCandidate = new CopyOnWriteArrayList<>();
    protected final List<DropCallback> drop = new CopyOnWriteArrayList<>();
    protected final List<ExitCallback> exit = new CopyOnWriteArrayList<>();
    protected final List<ContentScaleCallback> contentScale = new CopyOnWriteArrayList<>();
    protected final List<WindowFocusCallback> windowFocus = new CopyOnWriteArrayList<>();
    protected final List<WindowIconifyCallback> windowIconify = new CopyOnWriteArrayList<>();
    protected final List<WindowMaximizeCallback> windowMaximize = new CopyOnWriteArrayList<>();
    protected final List<WindowPosCallback> windowPosition = new CopyOnWriteArrayList<>();
    protected final List<WindowRefreshCallback> windowRefresh = new CopyOnWriteArrayList<>();
    protected final List<WindowSizeCallback> windowSize = new CopyOnWriteArrayList<>();
    protected final List<FramebufferSizeCallback> framebufferSize = new CopyOnWriteArrayList<>();


    public void addWindowCloseCallback(ExitCallback callback) {
        exit.add(callback);
    }

    public void removeWindowCloseCallback(ExitCallback callback) {
        exit.remove(callback);
    }


    public void addWindowContentScaleCallback(ContentScaleCallback callback) {
        contentScale.add(callback);
    }

    public void removeWindowContentScaleCallback(ContentScaleCallback callback) {
        contentScale.remove(callback);
    }


    public void addWindowFocusCallback(WindowFocusCallback callback) {
        windowFocus.add(callback);
    }

    public void removeWindowFocusCallback(WindowFocusCallback callback) {
        windowFocus.remove(callback);
    }


    public void addWindowIconifyCallback(WindowIconifyCallback callback) {
        windowIconify.add(callback);
    }

    public void removeWindowIconifyCallback(WindowIconifyCallback callback) {
        windowIconify.remove(callback);
    }


    public void addWindowMaximizeCallback(WindowMaximizeCallback callback) {
        windowMaximize.add(callback);
    }

    public void removeWindowMaximizeCallback(WindowMaximizeCallback callback) {
        windowMaximize.remove(callback);
    }


    public void addWindowPosCallback(WindowPosCallback callback) {
        windowPosition.add(callback);
    }

    public void removeWindowPosCallback(WindowPosCallback callback) {
        windowPosition.remove(callback);
    }


    public void addWindowRefreshCallback(WindowRefreshCallback callback) {
        windowRefresh.add(callback);
    }

    public void removeWindowRefreshCallback(WindowRefreshCallback callback) {
        windowRefresh.remove(callback);
    }


    public void addWindowSizeCallback(WindowSizeCallback callback) {
        windowSize.add(callback);
    }

    public void removeWindowSizeCallback(WindowSizeCallback callback) {
        windowSize.remove(callback);
    }


    public void addFramebufferSizeCallback(FramebufferSizeCallback callback) {
        framebufferSize.add(callback);
    }

    public void removeFramebufferSizeCallback(FramebufferSizeCallback callback) {
        framebufferSize.remove(callback);
    }


    public void addCursorPosCallback(CursorPosCallback callback) {
        cursorPosition.add(callback);
    }

    public void removeCursorPosCallback(CursorPosCallback callback) {
        cursorPosition.remove(callback);
    }


    public void addCursorEnterCallback(CursorEnterCallback callback) {
        cursorEnter.add(callback);
    }

    public void removeCursorEnterCallback(CursorEnterCallback callback) {
        cursorEnter.remove(callback);
    }


    public void addMouseButtonCallback(MouseButtonCallback callback) {
        mouseButton.add(callback);
    }

    public void removeMouseButtonCallback(MouseButtonCallback callback) {
        mouseButton.remove(callback);
    }


    public void addScrollCallback(ScrollCallback callback) {
        scroll.add(callback);
    }

    public void removeScrollCallback(ScrollCallback callback) {
        scroll.remove(callback);
    }


    public void addCharModsCallback(CharModsCallback callback) {
        charMods.add(callback);
    }

    public void removeCharModsCallback(CharModsCallback callback) {
        charMods.remove(callback);
    }


    public void addKeyCallback(KeyCallback callback) {
        key.add(callback);
    }

    public void removeKeyCallback(KeyCallback callback) {
        key.remove(callback);
    }


    public void addCharCallback(CharCallback callback) {
        character.add(callback);
    }

    public void removeCharCallback(CharCallback callback) {
        character.remove(callback);
    }


    public void addPreeditCallback(PreeditCallback callback) { //! ???
        preedit.add(callback);
    }

    public void removePreeditCallback(PreeditCallback callback) { //! ???
        preedit.remove(callback);
    }


    public void addPreeditCandidateCallback(PreeditCandidateCallback callback) { //! ???
        preeditCandidate.add(callback);
    }

    public void removePreeditCandidateCallback(PreeditCandidateCallback callback) { //! ???
        preeditCandidate.remove(callback);
    }


    public void addDropCallback(DropCallback callback) {
        drop.add(callback);
    }

    public void removeDropCallback(DropCallback callback) {
        drop.remove(callback);
    }

}
