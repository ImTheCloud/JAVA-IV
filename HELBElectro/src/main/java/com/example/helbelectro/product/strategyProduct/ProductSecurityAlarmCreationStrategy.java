package com.example.helbelectro.product.strategyProduct;

import com.example.helbelectro.component.ComponentBattery;
import com.example.helbelectro.component.ComponentMotionSensor;
import com.example.helbelectro.product.Product;
import com.example.helbelectro.product.ProductSecurityAlarm;

public class ProductSecurityAlarmCreationStrategy implements ProductCreationStrategy {
    @Override
    public Product createProduct() {
        return new ProductSecurityAlarm(
                ComponentBattery.getLoad(),
                ComponentMotionSensor.getColorSensor(),
                ComponentMotionSensor.getRange()
        );
    }
}
