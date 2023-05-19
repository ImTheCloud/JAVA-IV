package com.example.helbelectro.product.strategyProduct;

import com.example.helbelectro.conponent.ComponentMotionSensor;
import com.example.helbelectro.product.Product;
import com.example.helbelectro.product.ProductMotionSensor;
import com.example.helbelectro.product.strategyProduct.ProductCreationStrategy;

public class ProductMotionSensorCreationStrategy implements ProductCreationStrategy {
    @Override
    public Product createProduct() {
        return new ProductMotionSensor(ComponentMotionSensor.getRange(), ComponentMotionSensor.getColorSensor());
    }
}
