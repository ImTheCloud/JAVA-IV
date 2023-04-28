package com.example.helbelectro.Component;

import com.example.helbelectro.Factory.FactoryProduct;

public class ComponentElectricMotor extends FactoryProduct {

    private int power;


    public ComponentElectricMotor(ComponentBattery battery, ComponentElectricMotor electricMotor, ComponentMotionSensor motionSensor) {
        super(battery, electricMotor, motionSensor);
    }
}
