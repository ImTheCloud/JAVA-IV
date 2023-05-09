package com.example.helbelectro;
import java.util.ArrayList;
import java.util.List;

public class ProductMotor extends Product {
    protected String power;

    public ProductMotor(String power) {
        super("A", "#B111BB", "P3",3,15,"Moteur éléctrique");
        this.power = power;
    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();

        componentList.add(new ComponentMotor());
        return componentList;
    }
}

