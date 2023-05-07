package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductAlarm extends Product {
    public ProductAlarm() {
        super("C", "#A25846", "P4",4,20);
    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        for (Object component : Factory.componentObjectList) {
            if (component instanceof ComponentBattery && component instanceof ComponentSensor) {
                componentList.add(component);
            }
        }
        return componentList;
    }

}
