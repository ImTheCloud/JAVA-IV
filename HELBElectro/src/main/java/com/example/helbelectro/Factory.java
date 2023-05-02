package com.example.helbelectro;

public class Factory {
    private static Factory instance = null;
    private double sellingPrice;
    private int ecoScoren,manufacturingDuration;
    private Factory() {

    }
    // design pattern : singleton
    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public Object createComponent(String componentName, String[] values) {
        if (componentName.equals("Batterie")) {
            String load = values[2];
            System.out.println("Batterie created");
            return new ComponentBattery(load);
        } else if (componentName.equals("Capteur")) {
            String range = values[2];
            String color = values[3];
            System.out.println("Capteur created");
            return new ComponentSensor(range, color);
        } else if (componentName.equals("Moteur")) {
            String power = values[2];
            System.out.println("Moteur created");
            return new ComponentMotor(power);
        }
        return null;
    }
}
