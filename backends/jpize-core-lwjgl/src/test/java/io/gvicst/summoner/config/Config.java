package io.gvicst.summoner.config;

import resourceflow.resource.FileResource;
import resourceflow.resource.Resource;

import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public class Config {

    private final TreeMap<String, String> table;

    public Config() {
        this.table = new TreeMap<>();
    }

    public TreeMap<String, String> getTable() {
        return table;
    }


    public Config load(Resource res) {
        table.clear();
        try {
            final String[] lines = res.readLines();

            for (String line : lines) {
                final String[] parts = line.split(":");
                if (parts.length != 2)
                    continue;

                final String key = parts[0].trim();
                if (key.isEmpty())
                    continue;

                final String value = parts[1].trim();
                if (value.isEmpty())
                    continue;

                table.put(key, value);
            }
        }catch(Exception e){
            System.out.println("Err: " + e);
        }

        return this;
    }

    public Config save(FileResource res) {
        final PrintWriter writer = res.writer();

        for(Map.Entry<String, String> entry: table.entrySet()){
            final String key = entry.getKey();
            final String value = entry.getValue();
            writer.println(key + ": " + value);
        }

        writer.close();
        return this;
    }


    public Config put(String key, String value) {
        table.put(key, value);
        return this;
    }

    public Config putIfAbsent(String key, String value) {
        table.putIfAbsent(key, value);
        return this;
    }

    public Config put(String key, byte value) {
        table.put(key, String.valueOf(value));
        return this;
    }

    public Config putIfAbsent(String key, byte value) {
        table.putIfAbsent(key, String.valueOf(value));
        return this;
    }

    public Config put(String key, short value) {
        table.put(key, String.valueOf(value));
        return this;
    }

    public Config putIfAbsent(String key, short value) {
        table.putIfAbsent(key, String.valueOf(value));
        return this;
    }

    public Config put(String key, int value) {
        table.put(key, String.valueOf(value));
        return this;
    }

    public Config putIfAbsent(String key, int value) {
        table.putIfAbsent(key, String.valueOf(value));
        return this;
    }

    public Config put(String key, long value) {
        table.put(key, String.valueOf(value));
        return this;
    }

    public Config putIfAbsent(String key, long value) {
        table.putIfAbsent(key, String.valueOf(value));
        return this;
    }

    public Config put(String key, float value) {
        table.put(key, String.valueOf(value));
        return this;
    }

    public Config putIfAbsent(String key, float value) {
        table.putIfAbsent(key, String.valueOf(value));
        return this;
    }

    public Config put(String key, double value) {
        table.put(key, String.valueOf(value));
        return this;
    }

    public Config putIfAbsent(String key, double value) {
        table.putIfAbsent(key, String.valueOf(value));
        return this;
    }

    public Config put(String key, boolean value) {
        table.put(key, String.valueOf(value));
        return this;
    }

    public Config putIfAbsent(String key, boolean value) {
        table.putIfAbsent(key, String.valueOf(value));
        return this;
    }


    public String getString(String key) {
        return table.get(key);
    }

    public String getString(String key, String defaultValue) {
        return table.getOrDefault(key, defaultValue);
    }

    public byte getByte(String key) {
        try{
            return Byte.parseByte(table.get(key));
        }catch(NumberFormatException ignored) { }
        return 0;
    }

    public byte getByte(String key, byte defaultValue) {
        try{
            return Byte.parseByte(table.get(key));
        }catch(NumberFormatException ignored) { }
        return defaultValue;
    }

    public short getShort(String key) {
        try{
            return Short.parseShort(table.get(key));
        }catch(NumberFormatException ignored) { }
        return 0;
    }

    public short getShort(String key, short defaultValue) {
        try{
            return Short.parseShort(table.get(key));
        }catch(NumberFormatException ignored) { }
        return defaultValue;
    }

    public int getInt(String key) {
        try{
            return Integer.parseInt(table.get(key));
        }catch(NumberFormatException ignored) { }
        return 0;
    }

    public int getInt(String key, int defaultValue) {
        try{
            return Integer.parseInt(table.get(key));
        }catch(NumberFormatException ignored) { }
        return defaultValue;
    }

    public long getLong(String key) {
        try{
            return Long.parseLong(table.get(key));
        }catch(NumberFormatException ignored) { }
        return 0L;
    }

    public long getLong(String key, long defaultValue) {
        try{
            return Long.parseLong(table.get(key));
        }catch(NumberFormatException ignored) { }
        return defaultValue;
    }

    public float getFloat(String key) {
        try{
            return Float.parseFloat(table.get(key));
        }catch(NumberFormatException ignored) { }
        return 0F;
    }

    public float getFloat(String key, float defaultValue) {
        try{
            return Float.parseFloat(table.get(key));
        }catch(NumberFormatException ignored) { }
        return defaultValue;
    }

    public double getDouble(String key) {
        try{
            return Double.parseDouble(table.get(key));
        }catch(NumberFormatException ignored) { }
        return 0D;
    }

    public double getDouble(String key, double defaultValue) {
        try{
            return Double.parseDouble(table.get(key));
        }catch(NumberFormatException ignored) { }
        return defaultValue;
    }

    public boolean getBool(String key) {
        return Boolean.parseBoolean(table.get(key));
    }

    public boolean getBool(String key, boolean defaultValue) {
        final String value = table.get(key);
        if(value == null)
            return defaultValue;
        return Boolean.parseBoolean(value);
    }

}
