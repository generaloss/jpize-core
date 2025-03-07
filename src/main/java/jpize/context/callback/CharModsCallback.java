package jpize.context.callback;

import jpize.context.input.Mods;

public interface CharModsCallback {

    void invoke(char codepoint, Mods mods);

}
