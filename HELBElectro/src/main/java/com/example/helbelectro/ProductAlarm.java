package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductAlarm extends Product {
    public ProductAlarm() {
        super("C", "#A25846", "P4",4,20);
    }


    public List<String> getComponentListNecessary() {
        List<String> componentListName = new ArrayList<>();

        componentListName.add("ComponentBattery");
        componentListName.add("ComponentSensor");

        return componentListName;
    }

}
