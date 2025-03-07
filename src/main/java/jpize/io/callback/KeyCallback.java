package jpize.io.callback;

import jpize.io.input.Action;
import jpize.io.input.Key;
import jpize.io.input.Mods;

public interface KeyCallback {

    void invoke(Key key, int scancode, Action action, Mods mods);

}
