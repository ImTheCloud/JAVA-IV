package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductBattery extends Product {
    public ProductBattery() {
        super("C", "#00BCD4", "P1",3,5);
    }



    public List<String> getComponentListNecessary() {
        List<String> componentListName = new ArrayList<>();
        componentListName.add("ComponentBattery");
        return componentListName;
    }

}
