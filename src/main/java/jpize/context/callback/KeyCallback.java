package jpize.context.callback;

import jpize.context.input.Action;
import jpize.context.input.Key;
import jpize.context.input.Mods;

public interface KeyCallback {

    void invoke(Key key, int scancode, Action action, Mods mods);

}
