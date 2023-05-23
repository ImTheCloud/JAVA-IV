package com.example.helbelectro.component;

public class ComponentBattery extends Component {
    protected static String load;

    public ComponentBattery(String load) {
        super("C-Type-1", "#00BCD4");
        ComponentBattery.load = load;
    }

    public ComponentBattery() {
        super("C-Type-1", "#00BCD4");
    }

    public static String getLoad() {
        return load;
    }

    @Override
    public String getAttribute() {
        return getLoad();
    }
}
