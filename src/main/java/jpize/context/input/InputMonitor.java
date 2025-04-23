package jpize.context.input;

import jpize.context.Context;
import java.util.BitSet;

public class InputMonitor {

    public static final int KEYS_COUNT = (Key.values().length);
    public static final int BUTTONS_COUNT = (MouseBtn.values().length);

    private final Context context;
    private BitSet keysDown, keysPressed, keysUp;
    private BitSet btnsDown, btnsPressed, btnsUp;
    private int[] btnsPressedMousesCount;
    private float scrollX, scrollY;

    public InputMonitor(Context context) {
        this.context = context;
        context.getCallbacks().addInit(this::onInit);
    }

    private void onInit() {
        keysDown = new BitSet(KEYS_COUNT);
        keysPressed = new BitSet(KEYS_COUNT);
        keysUp = new BitSet(KEYS_COUNT);

        final int btnsSize = (BUTTONS_COUNT * context.getInput().getMaxMousesCount());
        btnsDown = new BitSet(btnsSize);
        btnsPressed = new BitSet(btnsSize);
        btnsUp = new BitSet(btnsSize);
        btnsPressedMousesCount = new int[BUTTONS_COUNT];

        context.getCallbacks().addKey(this::onKey);
        context.getCallbacks().addMouseButton(this::onMouseButton);
        context.getCallbacks().addScroll(this::onScroll);
    }


    private void onKey(Key key, int scancode, Action action, Mods mods) {
        final int index = key.ordinal();
        if(action.isPressed()) {
            keysDown.set(index);
            keysPressed.set(index, true);
        }else{
            keysUp.set(index);
            keysPressed.set(index, false);
        }
    }

    private void onMouseButton(int mouseIndex, MouseBtn button, Action action, Mods mods) {
        final int index = this.getButtonIndex(mouseIndex, button);
        if(action.isPressed()) {
            btnsDown.set(index);
            btnsPressed.set(index, true);
            btnsPressedMousesCount[button.ordinal()]++;
        }else{
            btnsUp.set(index);
            btnsPressed.set(index, false);
            btnsPressedMousesCount[button.ordinal()]--;
        }
    }

    private void onScroll(float scrollX, float scrollY) {
        this.scrollX += scrollX;
        this.scrollY += scrollY;
    }


    private int getButtonIndex(int mouseIndex, MouseBtn button) {
        return (button.ordinal() + mouseIndex * BUTTONS_COUNT);
    }

    public void clear() {
        keysDown.clear();
        keysUp.clear();
        btnsDown.clear();
        btnsUp.clear();
        scrollX = 0;
        scrollY = 0;
    }

    
    public boolean isKeyDown(Key key) {
        return keysDown.get(key.ordinal());
    }

    public boolean isKeyPressed(Key key) {
        return keysPressed.get(key.ordinal());
    }

    public boolean isKeyUp(Key key) {
        return keysUp.get(key.ordinal());
    }


    public boolean isMouseButtonDown(int mouseIndex, MouseBtn button) {
        final int index = this.getButtonIndex(mouseIndex, button);
        return btnsDown.get(index);
    }

    public boolean isMouseButtonPressed(int mouseIndex, MouseBtn button) {
        final int index = this.getButtonIndex(mouseIndex, button);
        return btnsPressed.get(index);
    }

    public boolean isMouseButtonUp(int mouseIndex, MouseBtn button) {
        final int index = this.getButtonIndex(mouseIndex, button);
        return btnsUp.get(index);
    }

    public int getPressedCount(MouseBtn button) {
        return btnsPressedMousesCount[button.ordinal()];
    }


    public float getScrollX() {
        return scrollX;
    }

    public float getScrollY() {
        return scrollY;
    }

}
