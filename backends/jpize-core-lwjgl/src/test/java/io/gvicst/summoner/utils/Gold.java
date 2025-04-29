package io.gvicst.summoner.utils;

import jpize.util.math.Mathc;

public class Gold {

    private int multiplier;
    private float amount;

    public void update(){
        if(amount*Mathc.pow(1000, multiplier)>=Mathc.pow(1000, multiplier+1)){
            multiplier++;
            amount/=1000f;
        }
        if(!(amount<=0&&multiplier<=0)&&amount*Mathc.pow(1000, multiplier)<Mathc.pow(1000, multiplier)){
            multiplier--;
            amount*=1000;
        }
    }


    public boolean isMoreThan(float amount, float multiplier){
        return this.amount*Mathc.pow(1000, this.multiplier) >= amount*Mathc.pow(1000, multiplier);
    }

    public float getAmount() {
        return amount;
    }

    public String getValue(){
        return Mathc.round(getAmount()*10)/10f+new String[]{"", "k", "m", "b", "t", "q", "Q", "s", "S", "o", "n", "d", "D"}[getMultiplier()];
    }

    public String valToString(float amount, int multiplier){
        float temp_amount = 0;
        while (amount!=temp_amount) {
            temp_amount = amount;
            if (amount * Mathc.pow(1000, multiplier) >= Mathc.pow(1000, multiplier + 1)) {
                multiplier++;
                amount /= 1000f;
            }
            if (!(amount <= 0 && multiplier <= 0) && amount * Mathc.pow(1000, multiplier) < Mathc.pow(1000, multiplier)) {
                multiplier--;
                amount *= 1000;
            }
        }
        return Mathc.round(amount*10)/10f+new String[]{"", "k", "m", "b", "t", "q", "Q", "s", "S", "o", "n", "d", "D"}[multiplier];
    }
    public String valToInt(float amount, int multiplier){
        float temp_amount = 0;
        while (amount!=temp_amount) {
            temp_amount = amount;
            if (amount * Mathc.pow(1000, multiplier) >= Mathc.pow(1000, multiplier + 1)) {
                multiplier++;
                amount /= 1000f;
            }
            if (!(amount <= 0 && multiplier <= 0) && amount * Mathc.pow(1000, multiplier) < Mathc.pow(1000, multiplier)) {
                multiplier--;
                amount *= 1000;
            }
        }
        return (int)amount+new String[]{"", "k", "m", "b", "t", "q", "Q", "s", "S", "o", "n", "d", "D"}[multiplier];
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void addAmount(float amount, float multiplier){
        float mul_diff = multiplier-this.multiplier;
        float temp_amount = amount * (Mathc.pow(1000, mul_diff));
        this.amount += temp_amount;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
