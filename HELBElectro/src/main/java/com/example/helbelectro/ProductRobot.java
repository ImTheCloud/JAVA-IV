package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductRobot extends Product {
    protected String power;
    protected String color;
    protected String range;
    public ProductRobot(String power,String color, String range) {
        super("B", "#BBAE2A", "P6",6,40,"Robot suiveur");
        this.color = color;
        this.range = range;
        this.power = power;
    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentSensor());
        componentList.add(new ComponentMotor());
        return componentList;
    }
}

