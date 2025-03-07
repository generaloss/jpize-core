package jpize.io.input;

import jpize.io.IWindow;
import java.util.BitSet;

public class InputMonitor {

    private final BitSet keysDown, keysRelease;
    private final BitSet btnsDown, btnsRelease;
    private float scrollX, scrollY;

    public InputMonitor(IWindow window) {
        this.keysDown = new BitSet(Key.values().length);
        this.keysRelease = new BitSet(Key.values().length);
        this.btnsDown = new BitSet(MouseBtn.values().length);
        this.btnsRelease = new BitSet(MouseBtn.values().length);

        window.getCallbacks().addKey(this::onKey);
        window.getCallbacks().addMouseButton(this::onButton);
        window.getCallbacks().addScroll(this::onScroll);
    }
    
    private void onKey(Key key, int scancode, Action action, Mods mods) {
        if(action.isDown()) keysDown.set(key.ordinal());
        else if(action.isRelease()) keysRelease.set(key.ordinal());
    }

    private void onButton(MouseBtn button, Action action, Mods mods) {
        if(action.isDown()) btnsDown.set(button.ordinal());
        else if(action.isRelease()) btnsRelease.set(button.ordinal());
    }

    private void onScroll(float scrollX, float scrollY) {
        this.scrollX += scrollX;
        this.scrollY += scrollY;
    }

    public void clear() {
        keysDown.clear();
        keysRelease.clear();
        btnsDown.clear();
        btnsRelease.clear();
        scrollX = 0;
        scrollY = 0;
    }
    
    public boolean isKeyDown(Key key) {
        return keysDown.get(key.ordinal());
    }
    
    public boolean isKeyUp(Key key) {
        return keysRelease.get(key.ordinal());
    }

    public boolean isMouseButtonDown(MouseBtn button) {
        return btnsDown.get(button.ordinal());
    }

    public boolean isMouseButtonUp(MouseBtn button) {
        return btnsRelease.get(button.ordinal());
    }

    public float getScrollX() {
        return scrollX;
    }

    public float getScrollY() {
        return scrollY;
    }

}
