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
        componentList.add(new ComponentBattery());
        componentList.add(new ComponentSensor());
        componentList.add(new ComponentMotor());
        return componentList;
    }

}
