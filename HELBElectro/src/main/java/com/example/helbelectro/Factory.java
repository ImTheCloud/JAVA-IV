package com.example.helbelectro;

public class Factory {
    private static Factory instance = null;
    Factory() {
    }
    // design pattern : singleton
    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public Component createComponent(String componentName, String[] values) {
        switch (componentName) {
            case "Batterie":
                String load = values[2];
                return new ComponentBattery(load);
            case "Capteur":
                String range = values[2];
                String color = values[3];
                return new ComponentSensor(range, color);
            case "Moteur":
                String power = values[2];
                return new ComponentMotor(power);
            default:
                throw new IllegalArgumentException("Composant non reconnu : " + componentName);
        }
    }
}
