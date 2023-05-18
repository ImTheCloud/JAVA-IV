package com.example.helbelectro.conponent;

public interface ComponentCreationStrategy {
    Component createComponent(String[] values);
}

class BatteryCreationStrategy implements ComponentCreationStrategy {
    @Override
    public Component createComponent(String[] values) {
        String load = values[2];
        return new ComponentBattery(load);
    }
}


class ElectricMotorCreationStrategy implements ComponentCreationStrategy {
    @Override
    public Component createComponent(String[] values) {
        String power = values[2];
        return new ComponentElectricMotor(power);
    }
}
class MotionSensorCreationStrategy implements ComponentCreationStrategy {
    @Override
    public Component createComponent(String[] values) {
        String range = values[2];
        String color = values[3];
        return new ComponentMotionSensor(range, color);
    }
}
