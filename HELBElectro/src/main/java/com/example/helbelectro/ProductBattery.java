package com.example.helbelectro;
import java.util.ArrayList;
import java.util.List;

public class ProductBattery extends Product {
    // Attribut pour la creation du produit avec les attribut de leur composant néccesaire a leur creation
    private String load;

    public ProductBattery(String load) {
        super("C", "#00BCD4", "P1", 3, 5, "Batterie");
        this.load = load;
    }
    //List de composant neccesaire avec les constructeur vide des composants
    //Override car on réimplemente la list de la class mere Product
    @Override
    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentBattery());
        return componentList;
    }
}
