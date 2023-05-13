package com.example.helbelectro;
import java.util.ArrayList;
import java.util.List;

    public class ProductSensor extends Product {
        // attribut pour la creation du produit avec les attribut de leur composant néccesaire a leur creation
        private String colorComponent;
        private String range;
        public ProductSensor(String colorComponent, String range) {
            super("B", "#4CAF50", "P2", 3, 10, "Capteur de mouvement");
            this.colorComponent = colorComponent;
            this.range = range;
        }
        //list de composant neccesaire avec les constructeur vide des composants
        //override car on réimplemente la list de la class mere Product
        @Override
        public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentSensor());
        return componentList;
    }
}
