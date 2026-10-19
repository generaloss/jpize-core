
package jpize.context.input;

import jpize.context.Context;

import java.util.BitSet;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InputMonitor {

    public static final int KEYS_COUNT = Key.values().length;
    public static final int BUTTONS_COUNT = MouseBtn.values().length;

    private final Context context;

    private BitSet keysDown, keysPressed, keysUp;
    private BitSet btnsDown, btnsPressed, btnsUp;
    private int[] btnsPressedMousesCount;

    private float scrollX, scrollY;

    private final ConcurrentLinkedQueue<InputEvent> eventQueue;

    private interface InputEvent {}

    private record KeyEvent(Key key, int scancode, Action action, Mods mods) implements InputEvent {}
    private record MouseButtonEvent(int cursorIndex, MouseBtn button, Action action, Mods mods) implements InputEvent {}
    private record ScrollEvent(float x, float y) implements InputEvent {}


    public InputMonitor(Context context) {
        this.context = context;
        this.eventQueue = new ConcurrentLinkedQueue<>();
        context.getCallbacks().addInit(this::onInit);
    }

    private void onInit() {
        keysDown = new BitSet(KEYS_COUNT);
        keysPressed = new BitSet(KEYS_COUNT);
        keysUp = new BitSet(KEYS_COUNT);

        final int btnsSize = BUTTONS_COUNT * context.getInput().getMaxMousesCount();

        btnsDown = new BitSet(btnsSize);
        btnsPressed = new BitSet(btnsSize);
        btnsUp = new BitSet(btnsSize);

        btnsPressedMousesCount = new int[BUTTONS_COUNT];

        context.getCallbacks().addKey(this::onKey);
        context.getCallbacks().addMouseButton(this::onMouseButton);
        context.getCallbacks().addScroll(this::onScroll);
    }


    private void onKey(Key key, int scancode, Action action, Mods mods) {
        eventQueue.add(new KeyEvent(key, scancode, action, mods));
    }

    private void onMouseButton(int cursorIndex, MouseBtn button, Action action, Mods mods) {
        eventQueue.add(
            new MouseButtonEvent(cursorIndex, button, action, mods)
        );
    }

    private void onScroll(float scrollX, float scrollY) {
        eventQueue.add(new ScrollEvent(scrollX, scrollY));
    }


    public void update() {
        while (!eventQueue.isEmpty()) {
            final InputEvent event = eventQueue.poll();

            if (event instanceof KeyEvent e) {
                this.applyKey(e);
            } else if (event instanceof MouseButtonEvent e) {
                this.applyMouseButton(e);
            } else if (event instanceof ScrollEvent e) {
                scrollX += e.x();
                scrollY += e.y();
            }
        }
    }


    private void applyKey(KeyEvent event) {
        final int index = event.key().ordinal();

        if (event.action().isDown()) {
            keysDown.set(index);
            keysPressed.set(index, true);
        }

        if (event.action().isUp()) {
            keysUp.set(index);
            keysPressed.set(index, false);
        }
    }


    private void applyMouseButton(MouseButtonEvent event) {
        final MouseBtn button = event.button();
        final int index = getButtonIndex(event.cursorIndex(), button);
        final boolean wasPressed = btnsPressed.get(index);

        if (event.action().isDown() && !wasPressed) {
            btnsDown.set(index);
            btnsPressed.set(index, true);
            btnsPressedMousesCount[button.ordinal()]++;
        } else if (event.action().isUp() && wasPressed) {
            btnsUp.set(index);
            btnsPressed.set(index, false);
            btnsPressedMousesCount[button.ordinal()]--;
        }
    }


    private int getButtonIndex(int cursorIndex, MouseBtn button) {
        return button.ordinal() + cursorIndex * BUTTONS_COUNT;
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


    public boolean isMouseButtonDown(int cursorIndex, MouseBtn button) {
        return btnsDown.get(getButtonIndex(cursorIndex, button));
    }

    public boolean isMouseButtonPressed(int cursorIndex, MouseBtn button) {
        return btnsPressed.get(getButtonIndex(cursorIndex, button));
    }

    public boolean isMouseButtonUp(int cursorIndex, MouseBtn button) {
        return btnsUp.get(getButtonIndex(cursorIndex, button));
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
