package io.gvicst.summoner.utils;

public class Familiar {

    private final String name;
    private boolean is_unlocked;
    private final int id, cost;
    private int level = 1;

    public Familiar(int id, String name, int cost){
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public Familiar(int id, String name, int cost, boolean is_unlocked){
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.is_unlocked = is_unlocked;
    }

    public String getName() {
        return name;
    }

    public boolean isUnlocked() {
        return is_unlocked;
    }

    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public float getAttackBonus(){
        return (float)Math.floor(((1+(id/5f))+1.02f*(level-1))*100)/100f;
    }

    public int getCost(){
        return is_unlocked?(int)(cost/10f*(level+1)):cost;
    }

    public void setUnlocked(boolean is_unlocked) {
        this.is_unlocked = is_unlocked;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
