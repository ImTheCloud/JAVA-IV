package com.example.helbelectro.Factory;

import com.example.helbelectro.Component.ComponentBattery;
import com.example.helbelectro.Component.ComponentElectricMotor;
import com.example.helbelectro.Component.ComponentMotionSensor;

public abstract class FactoryProduct {

    private ComponentBattery battery;
    private ComponentElectricMotor electricMotor;
    private ComponentMotionSensor motionSensor;
    private double sellingPrice;
    private int ecoScore;
    private int manufacturingDuration;

    public FactoryProduct(ComponentBattery battery, ComponentElectricMotor electricMotor, ComponentMotionSensor motionSensor) {
        this.battery = battery;
        this.electricMotor = electricMotor;
        this.motionSensor = motionSensor;
    }
}


