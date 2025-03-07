package jpize.context.callback;

import jpize.context.input.Action;
import jpize.context.input.Mods;
import jpize.context.input.MouseBtn;

public interface MouseButtonCallback {

    void invoke(MouseBtn button, Action action, Mods mods);

}
