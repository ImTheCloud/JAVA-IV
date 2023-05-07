package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductDrone extends Product {
    List<Object> componentList;
    public ProductDrone() {
        super("E", "#767676", "P7",12,60);

    }


    public List<Object> getComponentListNecessary() {
     componentList = new ArrayList<>();
        for (Object component : Factory.componentObjectList) {
            if (component instanceof ComponentBattery && component instanceof ComponentSensor && component instanceof ComponentMotor) {
                componentList.add(component);
            }
        }
        System.out.println(componentList);
        return componentList;
    }

}
