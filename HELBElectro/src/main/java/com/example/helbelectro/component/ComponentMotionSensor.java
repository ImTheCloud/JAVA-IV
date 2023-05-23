package com.example.helbelectro.component;

public class ComponentMotionSensor extends Component {
    protected static String range;
    protected static String colorSensor;

    public ComponentMotionSensor(String range, String colorSensor) {
        super("C-Type-2", "#4CAF50");
        ComponentMotionSensor.range = range;
        ComponentMotionSensor.colorSensor = colorSensor;
    }

    public ComponentMotionSensor() {
        super("C-Type-2", "#4CAF50");
    }

    public static String getColorSensor() {
        return colorSensor;
    }

    public static String getRange() {
        return range;
    }

    @Override
    public String getAttribute() {
        return getRange() + ", " + getColorSensor();
    }
}
