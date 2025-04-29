package io.gvicst.summoner.utils;

import io.gvicst.summoner.main.Main;
import jpize.util.screen.AbstractScreen;

public class IScreen extends AbstractScreen<String> {

    public final Main main;

    public IScreen(Main context) {
        this.main = context;
    }

}
