package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductCar extends Product {
    public ProductCar() {
        super("B", "#A7632D", "P5",5,30);

    }


    public List<String> getComponentListNecessary() {
        List<String> componentListName = new ArrayList<>();

        componentListName.add("ComponentBattery");
        componentListName.add("ComponentMotor");

        return componentListName;
    }

}