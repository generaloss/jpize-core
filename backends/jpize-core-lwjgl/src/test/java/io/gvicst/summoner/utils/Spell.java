package io.gvicst.summoner.utils;

import io.gvicst.summoner.config.GameParameters;
import jpize.context.Jpize;
import jpize.context.input.Key;

public class Spell {

    private final int id;
    private boolean is_unlocked, active;
    private float call_up, call_down, duration;
    private Key hotkey;
    private final String name, description;
    private final int cost;
    private final GameParameters params;

    public Spell(GameParameters params, int id, String name, String description, Key hotkey, float call_up, float duration, int cost){
        this.params = params;
        this.id = id;
        this.name = name;
        this.description = description;
        this.is_unlocked = false;
        this.active = false;
        this.hotkey = hotkey;
        this.call_down = 0;
        this.call_up = call_up;
        this.duration = duration;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUnlocked() {
        return is_unlocked;
    }

    public boolean isActive() {
        return active;
    }

    public float getCall_up() {
        return call_up;
    }

    public float getCall_down() {
        return call_down;
    }

    public float getDuration() {
        return (params.SPELLS_DURATION * 0.2f + 1) * duration;
    }

    public Key getHotkey() {
        return hotkey;
    }

    public int getCost() {
        return cost;
    }

    public void setUnlocked(boolean is_unlocked) {
        this.is_unlocked = is_unlocked;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCall_up(float call_up) {
        this.call_up = call_up;
    }

    public void setCall_down(float call_down) {
        this.call_down = call_down;
    }

    public void updateCall_down() {
        this.call_down -= Jpize.getDeltaTime();
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setHotkey(Key hotkey) {
        this.hotkey = hotkey;
    }
}
