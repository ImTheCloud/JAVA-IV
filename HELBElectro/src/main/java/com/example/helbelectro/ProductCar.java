package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductCar extends Product {
    public ProductCar() {
        super("B", 5, 30);
    }

    @Override
    public List<Object> getComponentList() {
        List<Object> componentList = new ArrayList<>();
        for (Object component : Factory.componentObjectList) {
            if (component instanceof ComponentBattery && component instanceof ComponentMotor) {
                componentList.add(component);
            }
        }
        return componentList;
    }

}