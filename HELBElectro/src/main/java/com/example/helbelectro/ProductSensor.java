package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

    public class ProductSensor extends Product {
        private String colorComponent;
        private String range;
        public ProductSensor(String colorComponent, String range) {
            super("B", "#4CAF50", "P2", 3, 10, "Capteur de mouvement");
            this.colorComponent = colorComponent;
            this.range = range;
        }


        @Override
        public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentSensor());
        return componentList;
    }
}
