package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductAlarm extends Product {
    public ProductAlarm() {
        super("C", "#A25846", "P4",4,20,"Alarme de sécurité");
    }


    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentBattery());
        componentList.add(new ComponentSensor());

        return componentList;
    }

}
