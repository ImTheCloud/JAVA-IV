package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

    public class ProductSensor extends Product {
        protected String color;
        protected String range;
        public ProductSensor(String color, String range) {
            super("B", "#4CAF50", "P2", 3, 10, "Capteur de mouvement");
            this.color = color;
            this.range = range;
        }


        public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentSensor());
        return componentList;
    }
}
