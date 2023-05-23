package com.example.helbelectro.product.strategyProduct;

import com.example.helbelectro.component.ComponentElectricMotor;
import com.example.helbelectro.product.Product;
import com.example.helbelectro.product.ProductElectricMotor;

public class ProductElectricMotorCreationStrategy implements ProductCreationStrategy {
    @Override
    public Product createProduct() {
        return new ProductElectricMotor(ComponentElectricMotor.getPower());
    }
}
