package jpize.io.callback;

import jpize.io.input.Action;
import jpize.io.input.Mods;
import jpize.io.input.MouseBtn;

public interface MouseButtonCallback {

    void invoke(MouseBtn button, Action action, Mods mods);

}
