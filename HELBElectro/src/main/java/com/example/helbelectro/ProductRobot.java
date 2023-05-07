package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductRobot extends Product {
    public ProductRobot() {
        super("B", "#BBAE2A", "P6",6,40);

    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        for (Object component : Factory.componentObjectList) {
            if (component instanceof ComponentSensor && component instanceof ComponentMotor) {
                componentList.add(component);
            }
        }
        return componentList;
    }
}

