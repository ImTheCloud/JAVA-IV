package com.example.helbelectro;


import java.util.ArrayList;
import java.util.List;

public class ProductMotor extends Product {
    public ProductMotor() {
        super("A", 3, 15);
    }

    @Override
    public List<Object> getComponentList() {
        List<Object> componentList = new ArrayList<>();
        for (Object component : Factory.componentObjectList) {
            if (component instanceof ComponentMotor) {
                componentList.add(component);
            }
        }
        return componentList;
    }
}

