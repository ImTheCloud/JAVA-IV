package com.example.helbelectro;

public class ComponentBattery implements Component {
    protected static String load;
    public ComponentBattery(String load) {
        ComponentBattery.load = load;
    }
    public ComponentBattery() {
    }
    public String getName() {
        return "C-Type-1";
    }
    public String getColor() {
        return "#00BCD4";
    }
    public static String getLoad() {
        return load;
    }
}

