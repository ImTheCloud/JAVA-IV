package com.example.helbelectro;

public abstract class Factory {

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


    public static void creationOfCOmponent() {

    }
}


