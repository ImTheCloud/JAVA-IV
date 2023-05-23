package com.example.helbelectro.product.strategyProduct;

import com.example.helbelectro.conponent.ComponentBattery;
import com.example.helbelectro.product.Product;
import com.example.helbelectro.product.ProductBattery;

public class ProductBatteryCreationStrategy implements ProductCreationStrategy {
    @Override
    public Product createProduct() {
        return new ProductBattery(ComponentBattery.getLoad());
    }
}
