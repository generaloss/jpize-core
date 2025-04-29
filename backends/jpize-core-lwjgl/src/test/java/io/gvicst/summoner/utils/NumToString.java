package io.gvicst.summoner.utils;

public class NumToString {

    public static String convert(float num){
        if(num/1000000000000f>=1)
            return Math.round(num/10000000000f)/100f+"t";
        if(num/1000000000f>=1)
            return Math.round(num/10000000f)/100f+"b";
        if(num/1000000f>=1)
            return Math.round(num/10000f)/100f+"m";
        if(num/1000f>=1)
            return Math.round(num/10f)/100f+"k";
        return (int)num+"";
    }

}
