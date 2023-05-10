package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductRobot extends Product {
    private String power;
    private String colorComponent;
    private String range;
    public ProductRobot(String power,String colorComponent, String range) {
        super("B", "#BBAE2A", "P6",6,40,"Robot suiveur");
        this.colorComponent = colorComponent;
        this.range = range;
        this.power = power;
    }
    @Override
    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentSensor());
        componentList.add(new ComponentMotor());
        return componentList;
    }
}

