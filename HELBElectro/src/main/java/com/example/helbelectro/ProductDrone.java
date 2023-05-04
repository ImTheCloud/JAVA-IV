package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductDrone extends Product {
    public ProductDrone() {
        super("E", 12, 60);
    }

    @Override
    public List<Object> getComponentList() {
        List<Object> componentList = new ArrayList<>();
        for (Object component : Factory.componentObjectList) {
            if (component instanceof ComponentBattery && component instanceof ComponentSensor && component instanceof ComponentMotor) {
                componentList.add(component);
            }
        }
        return componentList;
    }

}
