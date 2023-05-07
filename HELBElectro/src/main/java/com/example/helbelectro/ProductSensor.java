package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductSensor extends Product {
    public ProductSensor() {
        super("B", "#4CAF50", "P2",3,10);

    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentSensor());
        return componentList;
    }
}