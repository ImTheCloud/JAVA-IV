package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductDrone extends Product {
    public ProductDrone() {
        super("E", "#767676", "P7",12,60);

    }


    public List<String> getComponentListNecessary() {
        List<String> componentListName = new ArrayList<>();
        componentListName.add("ComponentBattery");
        componentListName.add("ComponentSensor");
        componentListName.add("ComponentMotor");
        return componentListName;
    }

}
