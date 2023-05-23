package com.example.helbelectro.product;
import com.example.helbelectro.component.ComponentBattery;
import com.example.helbelectro.component.ComponentElectricMotor;

import java.util.ArrayList;
import java.util.List;

public class ProductRemoteCar extends Product {
    // attribut pour la creation du produit avec les attribut de leur composant néccesaire a leur creation
    private String load;
    private String power;
    public ProductRemoteCar(String power, String load) {
        super("B", "#A7632D", "P5",5,30,"Voiture télécommandée");
        this.load = load;
        this.power = power;
    }
    //list de composant neccesaire avec les constructeur vide des composants
    //override car on réimplemente la list de la class mere Product
    @Override
    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentBattery());
        componentList.add(new ComponentElectricMotor());
        return componentList;
    }
}