package com.example.helbelectro.product.strategyProduct;

import com.example.helbelectro.component.ComponentMotionSensor;
import com.example.helbelectro.product.Product;
import com.example.helbelectro.product.ProductMotionSensor;

public class ProductMotionSensorCreationStrategy implements ProductCreationStrategy {
    @Override
    public Product createProduct() {
        return new ProductMotionSensor(ComponentMotionSensor.getRange(), ComponentMotionSensor.getColorSensor());
    }
}
