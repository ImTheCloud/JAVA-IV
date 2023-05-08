package com.example.helbelectro;

import java.awt.*;

public class ComponentSensor extends Component {
        protected String range;
        protected String color;

        public ComponentSensor(String range, String color) {
            this.range = range;
            this.color = color;
        }

    public ComponentSensor() {

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

