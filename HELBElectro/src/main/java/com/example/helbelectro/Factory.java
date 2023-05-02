package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class Factory {
    private static Factory instance = null;
    private double sellingPrice;
    private int ecoScore,manufacturingDuration;
    private List<Object> componentObjectList = new ArrayList<>();
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
            ComponentBattery battery = new ComponentBattery(load);
            return battery;
        } else if (componentName.equals("Capteur")) {
            String range = values[2];
            String color = values[3];
            ComponentSensor sensor = new ComponentSensor(range, color);
            componentObjectList.add(sensor);
            return sensor;
        } else if (componentName.equals("Moteur")) {
            String power = values[2];
            ComponentMotor motor = new ComponentMotor(power);
            componentObjectList.add(motor);
            return motor;
        }
        return null;
    }
}
