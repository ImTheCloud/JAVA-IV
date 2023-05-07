package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductSensor extends Product {
    public ProductSensor() {
        super("B", "#4CAF50", "P2",3,10);

    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        for (Object component : Factory.componentObjectList) {
            if (component instanceof ComponentSensor) {
                componentList.add(component);
            }
        }

        return componentList;
    }
}