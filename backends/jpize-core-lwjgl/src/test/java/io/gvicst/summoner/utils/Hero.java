package io.gvicst.summoner.utils;

import io.gvicst.summoner.config.GameParameters;

public class Hero {

    private final int id, cost;
    private int level;
    private boolean is_unlocked;
    private final String name;
    private final String param;
    private final GameParameters params;

    public Hero(GameParameters params, int id, String name, int cost, String param){
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.params = params;
        this.param = param;
    }

    public void setUnlocked(boolean is_unlocked) {
        this.is_unlocked = is_unlocked;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getParam(){
        return param + " x " + level;
    }

    public int getLevel() {
        return level;
    }

    public boolean isUnlocked() {
        return is_unlocked;
    }
}
