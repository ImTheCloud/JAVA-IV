package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductSensor extends Product {
    public ProductSensor() {
        super("B", 3, 10);
    }

    @Override
    public List<Object> getComponentList() {
        List<Object> componentList = new ArrayList<>();
        for (Object component : Factory.componentObjectList) {
            if (component instanceof ComponentSensor) {
                componentList.add(component);
            }
        }
        return componentList;
    }
}