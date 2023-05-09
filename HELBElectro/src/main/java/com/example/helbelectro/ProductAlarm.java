package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductAlarm extends Product {
    protected String load;
    protected String color;
    protected String range;

    public ProductAlarm(String load,String color, String range) {
        super("C", "#A25846", "P4",4,20,"Alarme de sécurité");
        this.load = load;
        this.color = color;
        this.range = range;
    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentBattery());
        componentList.add(new ComponentSensor());

        return componentList;
    }

}
