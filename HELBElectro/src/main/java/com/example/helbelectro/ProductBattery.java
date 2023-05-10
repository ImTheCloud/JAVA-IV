package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductBattery extends Product {
    protected String load;

    public ProductBattery(String load) {
        super("C", "#00BCD4", "P1", 3, 5, "Batterie");
        this.load = load;
    }
    @Override
    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentBattery());
        return componentList;
    }

}
