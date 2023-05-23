package com.example.helbelectro.product;
import com.example.helbelectro.component.ComponentBattery;

import java.util.ArrayList;
import java.util.List;

public class ProductBattery extends Product {
    // attribut pour la creation du produit avec les attribut de leur composant néccesaire a leur creation
    private String load;
    public ProductBattery(String load) {
        super("C", "#00BCD4", "P1", 3, 5, "Batterie");
        this.load = load;
    }
    //list de composant neccesaire avec les constructeur vide des composants
    //override car on réimplemente la list de la class mere Product
    @Override
    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentBattery());
        return componentList;
    }
}
