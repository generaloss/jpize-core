package jpize.io.callback;

import jpize.io.input.Mods;

public interface CharModsCallback {

    void invoke(char codepoint, Mods mods);

}
