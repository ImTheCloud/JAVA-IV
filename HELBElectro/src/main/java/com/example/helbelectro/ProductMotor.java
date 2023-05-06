package com.example.helbelectro;


import java.util.ArrayList;
import java.util.List;

public class ProductMotor extends Product {
    public ProductMotor() {
        super("A", "#624064", "P3",3,15);

    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        for (Object component : Factory.componentObjectList) {
            if (component instanceof ComponentMotor) {
                componentList.add(component);
            }
        }
        return componentList;
    }
}

