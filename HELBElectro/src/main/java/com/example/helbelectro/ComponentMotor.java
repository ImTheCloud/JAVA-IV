package com.example.helbelectro;
public class ComponentMotor extends Component{
        protected static String power;
        public ComponentMotor(String power) {
            this.power = power;
        }
    public ComponentMotor() {

    }
    public static String getPower() {
            return power;
        }

    public String getName() {
        return "C-Type-3";
    }

    public String getColor() {
        return "#B111BB";
    }
    }


