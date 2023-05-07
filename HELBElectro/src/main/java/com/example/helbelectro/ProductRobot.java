package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductRobot extends Product {
    public ProductRobot() {
        super("B", "#BBAE2A", "P6",6,40);

    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentSensor());
        componentList.add(new ComponentMotor());
        return componentList;
    }
}

