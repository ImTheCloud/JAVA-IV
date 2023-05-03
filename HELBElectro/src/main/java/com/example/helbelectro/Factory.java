package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class Factory {
    private static Factory instance = null;

    private static List<Object> componentObjectList = new ArrayList<>();
    public static List<String> componentNames = new ArrayList<>();

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
            componentObjectList.add(battery);
            componentNames.add("C-Type-1");
            return battery;
        } else if (componentName.equals("Capteur")) {
            String range = values[2];
            String color = values[3];
            ComponentSensor sensor = new ComponentSensor(range, color);
            componentObjectList.add(sensor);
            componentNames.add("C-Type-2");
            return sensor;
        } else if (componentName.equals("Moteur")) {
            String power = values[2];
            ComponentMotor motor = new ComponentMotor(power);
            componentObjectList.add(motor);
            componentNames.add("C-Type-3");
            return motor;
        }
        return null;
    }

    public static List<Product> getOptiTime() {
        List<Product> productList = new ArrayList<>();
        int minDuration = Integer.MAX_VALUE;

        // recuperer le temps de produit le plus petit
        for (Product product : new Product[]{new ProductBattery(), new ProductSensor(), new ProductMotor(),
                new ProductCar(), new ProductAlarm(), new ProductDrone(), new ProductRobot()}) {
            if (product.getManufacturingDuration() < minDuration) {
                minDuration = product.getManufacturingDuration();
            }
        }


        for (Object component : componentObjectList) {
            if (component instanceof ComponentBattery) {
                ProductBattery batteryProduct = new ProductBattery();
                if (batteryProduct.getManufacturingDuration() <= minDuration) {
                    productList.add(batteryProduct);
                    componentObjectList.remove(component);
                }
            } else if (component instanceof ComponentSensor) {
                ProductSensor sensorProduct = new ProductSensor();
                if (sensorProduct.getManufacturingDuration() <= minDuration) {
                    productList.add(sensorProduct);
                    componentObjectList.remove(component);
                }
            } else if (component instanceof ComponentMotor) {
                ProductMotor motorProduct = new ProductMotor();
                if (motorProduct.getManufacturingDuration() <= minDuration) {
                    productList.add(motorProduct);
                    componentObjectList.remove(component);
                }
            } else if (component instanceof ComponentBattery && component instanceof ComponentSensor) {
                ProductAlarm alarmProduct = new ProductAlarm();
                productList.add(alarmProduct);
            }

        }
        return productList;
    }



}
