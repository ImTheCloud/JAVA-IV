package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductCar extends Product {
    protected String load;

    protected String power;
    public ProductCar(String power,String load) {
        super("B", "#A7632D", "P5",5,30,"Voiture télécommandée");
        this.load = load;
        this.power = power;
    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentBattery());
        componentList.add(new ComponentMotor());
        return componentList;
    }

}