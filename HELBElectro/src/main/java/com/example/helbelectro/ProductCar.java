package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductCar extends Product {
    public ProductCar() {
        super("B", "#A7632D", "P5",5,30);

    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentBattery());
        componentList.add(new ComponentMotor());
        return componentList;
    }

}