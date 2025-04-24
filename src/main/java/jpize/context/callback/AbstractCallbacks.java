package jpize.context.callback;

import jpize.context.input.Action;
import jpize.context.input.Key;
import jpize.context.input.Mods;
import jpize.context.input.MouseBtn;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractCallbacks {

    protected final List<Runnable> init = new CopyOnWriteArrayList<>();
    protected final List<Runnable> render = new CopyOnWriteArrayList<>();
    protected final List<Runnable> update = new CopyOnWriteArrayList<>();
    protected final List<ResizeCallback> resize = new CopyOnWriteArrayList<>();
    protected final List<Runnable> exit = new CopyOnWriteArrayList<>();

    protected final List<ContentScaleCallback> contentScale = new CopyOnWriteArrayList<>();
    protected final List<WindowFocusCallback> windowFocus = new CopyOnWriteArrayList<>();
    protected final List<WindowIconifyCallback> windowIconify = new CopyOnWriteArrayList<>();
    protected final List<WindowMaximizeCallback> windowMaximize = new CopyOnWriteArrayList<>();
    protected final List<WindowPosCallback> windowPosition = new CopyOnWriteArrayList<>();
    protected final List<Runnable> windowRefresh = new CopyOnWriteArrayList<>();
    protected final List<ResizeCallback> windowResize = new CopyOnWriteArrayList<>();

    protected final List<CursorPosCallback> cursorPosition = new CopyOnWriteArrayList<>();
    protected final List<CursorEnterCallback> cursorEnter = new CopyOnWriteArrayList<>();
    protected final List<MouseButtonCallback> mouseButton = new CopyOnWriteArrayList<>();
    protected final List<ScrollCallback> scroll = new CopyOnWriteArrayList<>();
    protected final List<CharModsCallback> charMods = new CopyOnWriteArrayList<>();
    protected final List<KeyCallback> key = new CopyOnWriteArrayList<>();
    protected final List<CharCallback> character = new CopyOnWriteArrayList<>();
    protected final List<PreeditCallback> preedit = new CopyOnWriteArrayList<>();
    protected final List<PreeditCandidateCallback> preeditCandidate = new CopyOnWriteArrayList<>();
    protected final List<Runnable> imeStatus = new CopyOnWriteArrayList<>();
    protected final List<DropCallback> drop = new CopyOnWriteArrayList<>();


    public void addInit(Runnable callback) {
        init.add(callback);
    }

    public void removeInit(Runnable callback) {
        init.remove(callback);
    }

    public void invokeInit() {
        init.forEach(Runnable::run);
    }


    public void addUpdate(Runnable callback) {
        update.add(callback);
    }

    public void removeUpdate(Runnable callback) {
        update.remove(callback);
    }

    public void invokeUpdate() {
        update.forEach(Runnable::run);
    }


    public void addRender(Runnable callback) {
        render.add(callback);
    }

    public void removeRender(Runnable callback) {
        render.remove(callback);
    }

    public void invokeRender() {
        render.forEach(Runnable::run);
    }


    public void addResize(ResizeCallback callback) {
        resize.add(callback);
    }

    public void removeResize(ResizeCallback callback) {
        resize.remove(callback);
    }

    public void invokeResize(int width, int height) {
        resize.forEach(callback -> callback.invoke(width, height));
    }


    public void addExit(Runnable callback) {
        exit.add(callback);
    }

    public void removeExit(Runnable callback) {
        exit.remove(callback);
    }

    public void invokeExit() {
        exit.forEach(Runnable::run);
    }


    public void addContentScale(ContentScaleCallback callback) {
        contentScale.add(callback);
    }

    public void removeContentScale(ContentScaleCallback callback) {
        contentScale.remove(callback);
    }

    public void invokeContentScale(float scaleX, float scaleY) {
        contentScale.forEach(callback -> callback.invoke(scaleX, scaleY));
    }


    public void addWindowFocus(WindowFocusCallback callback) {
        windowFocus.add(callback);
    }

    public void removeWindowFocus(WindowFocusCallback callback) {
        windowFocus.remove(callback);
    }

    public void invokeWindowFocus(boolean focused) {
        windowFocus.forEach(callback -> callback.invoke(focused));
    }


    public void addWindowIconify(WindowIconifyCallback callback) {
        windowIconify.add(callback);
    }

    public void removeWindowIconify(WindowIconifyCallback callback) {
        windowIconify.remove(callback);
    }

    public void invokeWindowIconify(boolean iconified) {
        windowIconify.forEach(callback -> callback.invoke(iconified));
    }


    public void addWindowMaximize(WindowMaximizeCallback callback) {
        windowMaximize.add(callback);
    }

    public void removeWindowMaximize(WindowMaximizeCallback callback) {
        windowMaximize.remove(callback);
    }

    public void invokeWindowMaximize(boolean maximized) {
        windowMaximize.forEach(callback -> callback.invoke(maximized));
    }


    public void addWindowPos(WindowPosCallback callback) {
        windowPosition.add(callback);
    }

    public void removeWindowPos(WindowPosCallback callback) {
        windowPosition.remove(callback);
    }

    public void invokeWindowPos(int x, int y) {
        windowPosition.forEach(callback -> callback.invoke(x, y));
    }


    public void addWindowRefresh(Runnable callback) {
        windowRefresh.add(callback);
    }

    public void removeWindowRefresh(Runnable callback) {
        windowRefresh.remove(callback);
    }

    public void invokeWindowRefresh() {
        windowRefresh.forEach(Runnable::run);
    }


    public void addWindowResize(ResizeCallback callback) {
        windowResize.add(callback);
    }

    public void removeWindowResize(ResizeCallback callback) {
        windowResize.remove(callback);
    }

    public void invokeWindowResize(int width, int height) {
        windowResize.forEach(callback -> callback.invoke(width, height));
    }



    public void addCursorPos(CursorPosCallback callback) {
        cursorPosition.add(callback);
    }

    public void removeCursorPos(CursorPosCallback callback) {
        cursorPosition.remove(callback);
    }

    public void invokeCursorPos(int cursorIndex, float x, float y) {
        cursorPosition.forEach(callback -> callback.invoke(cursorIndex, x, y));
    }


    public void addCursorEnter(CursorEnterCallback callback) {
        cursorEnter.add(callback);
    }

    public void removeCursorEnter(CursorEnterCallback callback) {
        cursorEnter.remove(callback);
    }

    public void invokeCursorEnter(int cursorIndex, boolean entered) {
        cursorEnter.forEach(callback -> callback.invoke(cursorIndex, entered));
    }


    public void addMouseButton(MouseButtonCallback callback) {
        mouseButton.add(callback);
    }

    public void removeMouseButton(MouseButtonCallback callback) {
        mouseButton.remove(callback);
    }

    public void invokeMouseButton(int cursorIndex, MouseBtn button, Action action, Mods mods) {
        mouseButton.forEach(callback -> callback.invoke(cursorIndex, button, action, mods));
    }


    public void addScroll(ScrollCallback callback) {
        scroll.add(callback);
    }

    public void removeScroll(ScrollCallback callback) {
        scroll.remove(callback);
    }

    public void invokeScroll(float offsetX, float offsetY) {
        scroll.forEach(callback -> callback.invoke(offsetX, offsetY));
    }


    public void addCharMods(CharModsCallback callback) {
        charMods.add(callback);
    }

    public void removeCharMods(CharModsCallback callback) {
        charMods.remove(callback);
    }

    public void invokeCharMods(char codepoint, Mods mods) {
        charMods.forEach(callback -> callback.invoke(codepoint, mods));
    }


    public void addKey(KeyCallback callback) {
        key.add(callback);
    }

    public void removeKey(KeyCallback callback) {
        key.remove(callback);
    }

    public void invokeKey(Key key, int scancode, Action action, Mods mods) {
        this.key.forEach(callback -> callback.invoke(key, scancode, action, mods));
    }


    public void addChar(CharCallback callback) {
        character.add(callback);
    }

    public void removeChar(CharCallback callback) {
        character.remove(callback);
    }

    public void invokeChar(char codepoint) {
        character.forEach(callback -> callback.invoke(codepoint));
    }


    public void addPreedit(PreeditCallback callback) { //! ???
        preedit.add(callback);
    }

    public void removePreedit(PreeditCallback callback) { //! ???
        preedit.remove(callback);
    }

    public void invokePreedit(int preeditCount, long preeditStringPointer, int blockCount, long blockSizesPointer, int focusedBlock, int caret) {
        preedit.forEach(callback -> callback.invoke(preeditCount, preeditStringPointer, blockCount, blockSizesPointer, focusedBlock, caret));
    }


    public void addPreeditCandidate(PreeditCandidateCallback callback) { //! ???
        preeditCandidate.add(callback);
    }

    public void removePreeditCandidate(PreeditCandidateCallback callback) { //! ???
        preeditCandidate.remove(callback);
    }

    public void invokePreeditCandidate(int candidatesCount, int selectedIndex, int pageStart, int pageSize) {
        preeditCandidate.forEach(callback -> callback.invoke(candidatesCount, selectedIndex, pageStart, pageSize));
    }


    public void addIMEStatus(Runnable callback) { //! ???
        imeStatus.add(callback);
    }

    public void removeIMEStatus(Runnable callback) { //! ???
        imeStatus.remove(callback);
    }

    public void invokeIMEStatus() {
        imeStatus.forEach(Runnable::run);
    }


    public void addDrop(DropCallback callback) {
        drop.add(callback);
    }

    public void removeDrop(DropCallback callback) {
        drop.remove(callback);
    }

    public void invokeDrop(String[] files) {
        drop.forEach(callback -> callback.invoke(files));
    }

}
