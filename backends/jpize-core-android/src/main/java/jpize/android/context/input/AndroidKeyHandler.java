package jpize.android.context.input;

import android.util.Log;
import android.view.KeyEvent;
import jpize.android.context.AndroidCallbacks;
import jpize.context.input.Action;
import jpize.context.input.Key;
import jpize.context.input.Mods;

public final class AndroidKeyHandler {

    private final AndroidCallbacks callbacks;

    public AndroidKeyHandler(AndroidCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    public boolean onKeyEvent(KeyEvent event) {
        final int keycode = event.getKeyCode();

        final Key key = AndroidKey.byAndroidKeycode(keycode);
        if(key == null)
            return false;

        final int rawAction = event.getAction();

        final Action action = switch(rawAction) {
            case KeyEvent.ACTION_DOWN -> (event.getRepeatCount() > 0) ? Action.PRESSED : Action.DOWN;
            case KeyEvent.ACTION_UP -> Action.UP;
            default -> null;
        };

        if(action == null)
            return false;

        final Mods mods = new AndroidMods(event.getMetaState());

        callbacks.invokeKey(key, event.getScanCode(), action, mods);
        return true;
    }

    public boolean onKeyTyped(KeyEvent event) {
        final int unicode = event.getUnicodeChar();

        if (unicode == 0)
            return false;

        callbacks.invokeChar((char) unicode);
        return true;
    }

    public boolean onKeyTypedWithMods(KeyEvent event) {
        final int unicode = event.getUnicodeChar();

        if (unicode == 0)
            return false;

        final Mods mods = new AndroidMods(event.getMetaState());

        callbacks.invokeCharMods((char) unicode, mods);
        return true;
    }
}