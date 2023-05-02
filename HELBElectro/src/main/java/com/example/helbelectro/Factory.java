package com.example.helbelectro;

public class Factory {

    private ComponentBattery battery;
    private ComponentMotor electricMotor;
    private ComponentSensor motionSensor;
    private double sellingPrice;
    private int ecoScore;
    private int manufacturingDuration;

    public Factory(ComponentBattery battery, ComponentMotor electricMotor, ComponentSensor motionSensor) {
        this.battery = battery;
        this.electricMotor = electricMotor;
        this.motionSensor = motionSensor;
    }

    public static Object createComponent(String componentName, String[] values) {
        if (componentName.equals("Batterie")) {
            String load = values[2];
            return new ComponentBattery(load);
        } else if (componentName.equals("Capteur")) {
            String range = values[2];
            String color = values[3];
            return new ComponentSensor(range, color);
        } else if (componentName.equals("Moteur")) {
            String power = values[2];
            return new ComponentMotor(power);
        }
        return null;
    }


}


