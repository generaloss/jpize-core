package io.gvicst.summoner.utils;

import java.util.HashMap;
import java.util.Map;

public class ArgsMap {

    private final Map<String, String> map;

    public ArgsMap(String... args) {
        this.map = new HashMap<>();
        final int length = (args.length % 2 == 0) ? args.length : (args.length - 1);

        for(int i = 0; i < length; i += 2){
            final String key = args[i];
            if(key.length() > 2)
                this.map.put(key.substring(2), args[i + 1]);
        }
    }

    public Map<String, String> getMap() {
        return map;
    }


    public String getString(String key, String defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        try{
            if(map.containsKey(key)){
                return Integer.parseInt(map.get(key));
            }
        }catch(NumberFormatException ignored){
        }
        return defaultValue;
    }

}
