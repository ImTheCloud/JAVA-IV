package com.example.helbelectro;

import java.awt.*;

public class ComponentBattery extends Component {
    private String load;

    public ComponentBattery(String load) {
        this.load = load;
    }

    public String getName() {
        return "-C-Type-1";
    }

    public String getColor() {
        return "#00BCD4";
    }

    public String getLoad() {
        return load;
    }
}

