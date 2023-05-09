package com.example.helbelectro;
public class ComponentSensor extends Component {
    protected static String range;
    protected static String colorSensor;

        public ComponentSensor(String range, String colorSensor) {
            this.range = range;
            this.colorSensor = colorSensor;
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

