package com.example.helbelectro.strategy;

import com.example.helbelectro.conponent.Component;
import com.example.helbelectro.conponent.ComponentMotionSensor;
import com.example.helbelectro.strategy.ComponentCreationStrategy;

public class MotionSensorCreationStrategy implements ComponentCreationStrategy {
    @Override
    public Component createComponent(String[] values) {
        String range = values[2];
        String color = values[3];
        return new ComponentMotionSensor(range, color);
    }
}