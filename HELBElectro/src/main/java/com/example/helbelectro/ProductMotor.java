package com.example.helbelectro;


import java.util.ArrayList;
import java.util.List;

public class ProductMotor extends Product {
    public ProductMotor() {
        super("A", "#B111BB", "P3",3,15);

    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();

        componentList.add(new ComponentMotor());
        return componentList;
    }
}

