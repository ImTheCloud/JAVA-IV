package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductDrone extends Product {
    private String load;
    private String power;
    private String colorComponent;
    private String range;
    List<Object> componentList;
    public ProductDrone(String power,String colorComponent, String range,String load) {
        super("E", "#767676", "P7",12,60,"Drone de surveillance");
        this.load = load;
        this.colorComponent = colorComponent;
        this.range = range;
        this.power = power;
    }

    @Override
    public List<Object> getComponentListNecessary() {
     componentList = new ArrayList<>();
        componentList.add(new ComponentBattery());
        componentList.add(new ComponentSensor());
        componentList.add(new ComponentMotor());
        return componentList;
    }
}
