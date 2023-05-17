package com.example.helbelectro.strategy;

import com.example.helbelectro.conponent.Component;
import com.example.helbelectro.conponent.ComponentBattery;
import com.example.helbelectro.strategy.ComponentCreationStrategy;

public class BatteryCreationStrategy implements ComponentCreationStrategy {
    @Override
    public Component createComponent(String[] values) {
        String load = values[2];
        return new ComponentBattery(load);
    }
}