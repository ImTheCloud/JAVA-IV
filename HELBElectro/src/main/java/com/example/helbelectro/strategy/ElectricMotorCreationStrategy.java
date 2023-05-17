package com.example.helbelectro.strategy;


import com.example.helbelectro.conponent.Component;
import com.example.helbelectro.conponent.ComponentElectricMotor;
import com.example.helbelectro.strategy.ComponentCreationStrategy;

public class ElectricMotorCreationStrategy implements ComponentCreationStrategy {
    @Override
    public Component createComponent(String[] values) {
        String power = values[2];
        return new ComponentElectricMotor(power);
    }
}
