package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductDrone extends Product {
    public ProductDrone() {
        super("E", "#767676", "P7",12,60);

    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        for (Object component : Factory.componentObjectList) {
            if (component instanceof ComponentBattery && component instanceof ComponentSensor && component instanceof ComponentMotor) {
                componentList.add(component);
            }
        }
        return componentList;
    }

}
