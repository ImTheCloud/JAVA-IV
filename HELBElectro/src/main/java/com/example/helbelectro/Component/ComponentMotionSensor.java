package com.example.helbelectro.Component;

import com.example.helbelectro.Factory.FactoryProduct;

public class ComponentMotionSensor extends FactoryProduct {

    private int metter;
    private String color;

    public ComponentMotionSensor(ComponentBattery battery, ComponentElectricMotor electricMotor, ComponentMotionSensor motionSensor) {
        super(battery, electricMotor, motionSensor);
    }



}
