package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductRobot extends Product {
    public ProductRobot() {
        super("B", "#BBAE2A", "P6",6,40);

    }



    public List<String> getComponentListNecessary() {
        List<String> componentListName = new ArrayList<>();
        componentListName.add("ComponentSensor");
        componentListName.add("ComponentMotor");
        return componentListName;
    }
}

