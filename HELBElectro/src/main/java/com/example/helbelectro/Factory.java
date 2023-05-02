package com.example.helbelectro;
public class Factory {

    private static Factory instance = null;
    private ComponentBattery battery;
    private ComponentMotor electricMotor;
    private ComponentSensor motionSensor;
    private double sellingPrice;
    private int ecoScore;
    private int manufacturingDuration;
    private Factory(ComponentBattery battery, ComponentMotor electricMotor, ComponentSensor motionSensor) {
        this.battery = battery;
        this.electricMotor = electricMotor;
        this.motionSensor = motionSensor;
    }

    //  design patern : singleton
    public static Factory getInstance(ComponentBattery battery, ComponentMotor electricMotor, ComponentSensor motionSensor) {
        if (instance == null) {
            instance = new Factory(battery, electricMotor, motionSensor);
        }
        return instance;
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
