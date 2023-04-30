package com.example.helbelectro.Factory;

import com.example.helbelectro.Component.ComponentBattery;
import com.example.helbelectro.Component.ComponentMotor;
import com.example.helbelectro.Component.ComponentSensor;

public abstract class FactoryProduct {

    private ComponentBattery battery;
    private ComponentMotor electricMotor;
    private ComponentSensor motionSensor;
    private double sellingPrice;
    private int ecoScore;
    private int manufacturingDuration;

    public FactoryProduct(ComponentBattery battery, ComponentMotor electricMotor, ComponentSensor motionSensor) {
        this.battery = battery;
        this.electricMotor = electricMotor;
        this.motionSensor = motionSensor;
    }
}


