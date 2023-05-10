package com.example.helbelectro;
public class ComponentSensor implements Component {
    protected static String range;
    protected static String colorSensor;

        public ComponentSensor(String range, String colorSensor) {
            ComponentSensor.range = range;
            ComponentSensor.colorSensor = colorSensor;
        }

    public ComponentSensor() {

    }

    public static String getColorSensor() {
        return colorSensor;
    }
    public static String getRange() {
            return range;
        }

    public String getName() {
        return "C-Type-2";
    }

    public String getColor() {
        return "#4CAF50";
    }
    }

