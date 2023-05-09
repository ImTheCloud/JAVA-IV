package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductDrone extends Product {
    protected String load;
    protected String power;
    protected String color;
    protected String range;
    List<Object> componentList;
    public ProductDrone(String power,String color, String range,String load) {
        super("E", "#767676", "P7",12,60,"Drone de surveillance");
        this.load = load;
        this.color = color;
        this.range = range;
        this.power = power;
    }
    public List<Object> getComponentListNecessary() {
     componentList = new ArrayList<>();
        componentList.add(new ComponentBattery());
        componentList.add(new ComponentSensor());
        componentList.add(new ComponentMotor());
        return componentList;
    }
}
