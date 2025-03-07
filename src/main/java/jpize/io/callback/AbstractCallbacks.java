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


    public void addExitCallback(ExitCallback callback) {
        exit.add(callback);
    }

    public void removeExitClose(ExitCallback callback) {
        exit.remove(callback);
    }


    public void addContentScale(ContentScaleCallback callback) {
        contentScale.add(callback);
    }

    public void removeContentScale(ContentScaleCallback callback) {
        contentScale.remove(callback);
    }


    public void addWindowFocus(WindowFocusCallback callback) {
        windowFocus.add(callback);
    }

    public void removeWindowFocus(WindowFocusCallback callback) {
        windowFocus.remove(callback);
    }


    public void addWindowIconify(WindowIconifyCallback callback) {
        windowIconify.add(callback);
    }

    public void removeWindowIconify(WindowIconifyCallback callback) {
        windowIconify.remove(callback);
    }


    public void addWindowMaximize(WindowMaximizeCallback callback) {
        windowMaximize.add(callback);
    }

    public void removeWindowMaximize(WindowMaximizeCallback callback) {
        windowMaximize.remove(callback);
    }


    public void addWindowPos(WindowPosCallback callback) {
        windowPosition.add(callback);
    }

    public void removeWindowPos(WindowPosCallback callback) {
        windowPosition.remove(callback);
    }


    public void addWindowRefresh(WindowRefreshCallback callback) {
        windowRefresh.add(callback);
    }

    public void removeWindowRefresh(WindowRefreshCallback callback) {
        windowRefresh.remove(callback);
    }


    public void addWindowSize(WindowSizeCallback callback) {
        windowSize.add(callback);
    }

    public void removeWindowSize(WindowSizeCallback callback) {
        windowSize.remove(callback);
    }


    public void addFramebufferSize(FramebufferSizeCallback callback) {
        framebufferSize.add(callback);
    }

    public void removeFramebufferSize(FramebufferSizeCallback callback) {
        framebufferSize.remove(callback);
    }


    public void addCursorPos(CursorPosCallback callback) {
        cursorPosition.add(callback);
    }

    public void removeCursorPos(CursorPosCallback callback) {
        cursorPosition.remove(callback);
    }


    public void addCursorEnter(CursorEnterCallback callback) {
        cursorEnter.add(callback);
    }

    public void removeCursorEnter(CursorEnterCallback callback) {
        cursorEnter.remove(callback);
    }


    public void addMouseButton(MouseButtonCallback callback) {
        mouseButton.add(callback);
    }

    public void removeMouseButton(MouseButtonCallback callback) {
        mouseButton.remove(callback);
    }


    public void addScroll(ScrollCallback callback) {
        scroll.add(callback);
    }

    public void removeScroll(ScrollCallback callback) {
        scroll.remove(callback);
    }


    public void addCharMods(CharModsCallback callback) {
        charMods.add(callback);
    }

    public void removeCharMods(CharModsCallback callback) {
        charMods.remove(callback);
    }


    public void addKey(KeyCallback callback) {
        key.add(callback);
    }

    public void removeKey(KeyCallback callback) {
        key.remove(callback);
    }


    public void addChar(CharCallback callback) {
        character.add(callback);
    }

    public void removeChar(CharCallback callback) {
        character.remove(callback);
    }


    public void addPreedit(PreeditCallback callback) { //! ???
        preedit.add(callback);
    }

    public void removePreedit(PreeditCallback callback) { //! ???
        preedit.remove(callback);
    }


    public void addPreeditCandidate(PreeditCandidateCallback callback) { //! ???
        preeditCandidate.add(callback);
    }

    public void removePreeditCandidate(PreeditCandidateCallback callback) { //! ???
        preeditCandidate.remove(callback);
    }


    public void addDrop(DropCallback callback) {
        drop.add(callback);
    }

    public void removeDrop(DropCallback callback) {
        drop.remove(callback);
    }

}
