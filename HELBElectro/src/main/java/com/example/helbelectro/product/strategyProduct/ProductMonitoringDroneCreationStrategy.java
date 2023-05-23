package com.example.helbelectro.product.strategyProduct;

import com.example.helbelectro.component.ComponentBattery;
import com.example.helbelectro.component.ComponentElectricMotor;
import com.example.helbelectro.component.ComponentMotionSensor;
import com.example.helbelectro.product.Product;
import com.example.helbelectro.product.ProductMonitoringDrone;

public class ProductMonitoringDroneCreationStrategy implements ProductCreationStrategy {
    @Override
    public Product createProduct() {
        return new ProductMonitoringDrone(
                ComponentElectricMotor.getPower(),
                ComponentMotionSensor.getColorSensor(),
                ComponentMotionSensor.getRange(),
                ComponentBattery.getLoad()
        );
    }
}
