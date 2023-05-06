package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public class ProductBattery extends Product {
    public ProductBattery() {
        super("C", "#00BCD4", "P1",3,5);
    }



    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        for (Object component : Factory.componentObjectList) {
            if (component instanceof ComponentBattery) {
                componentList.add(component);
            }
        }

        return componentList;
    }

}
