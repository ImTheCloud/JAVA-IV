package com.example.helbelectro.product.strategyProduct;

import com.example.helbelectro.conponent.ComponentElectricMotor;
import com.example.helbelectro.conponent.ComponentMotionSensor;
import com.example.helbelectro.product.Product;
import com.example.helbelectro.product.ProductTrackingRobot;

public class ProductTrackingRobotCreationStrategy implements ProductCreationStrategy {
    @Override
    public Product createProduct() {
        return new ProductTrackingRobot(
                ComponentElectricMotor.getPower(),
                ComponentMotionSensor.getColorSensor(),
                ComponentMotionSensor.getRange()
        );
    }
}
