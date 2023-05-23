package com.example.helbelectro.product.strategyProduct;

import com.example.helbelectro.component.ComponentBattery;
import com.example.helbelectro.component.ComponentElectricMotor;
import com.example.helbelectro.product.Product;
import com.example.helbelectro.product.ProductRemoteCar;

public class ProductRemoteCarCreationStrategy implements ProductCreationStrategy {
    @Override
    public Product createProduct() {
        return new ProductRemoteCar(
                ComponentElectricMotor.getPower(),
                ComponentBattery.getLoad()
        );
    }
}
