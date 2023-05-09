package com.example.helbelectro;
public class ComponentSensor extends Component {
    protected String range;
    protected String colorSensor;

        public ComponentSensor(String range, String colorSensor) {
            this.range = range;
            this.colorSensor = colorSensor;
        }
    public ComponentSensor() {

    }
    public String getColorSensor() {
        return colorSensor;
    }
    public String getRange() {
            return range;
        }

    public String getName() {
        return "C-Type-2";
    }

    public String getColor() {
        return "#4CAF50";
    }
    }

