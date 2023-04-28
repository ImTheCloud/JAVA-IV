package com.example.helbelectro.Component;

import com.example.helbelectro.Factory.FactoryProduct;

public class ComponentBattery extends FactoryProduct {
    private int percentage;

    public ComponentBattery(ComponentBattery battery, ComponentElectricMotor electricMotor, ComponentMotionSensor motionSensor) {
        super(battery, electricMotor, motionSensor);
    }



}
