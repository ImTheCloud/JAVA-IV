package com.example.helbelectro.product;
import com.example.helbelectro.component.ComponentElectricMotor;

import java.util.ArrayList;
import java.util.List;

public class ProductElectricMotor extends Product {
    // attribut pour la creation du produit avec les attribut de leur composant néccesaire a leur creation
    private String power;
    public ProductElectricMotor(String power) {
        super("A", "#B111BB", "P3",3,15);
        this.power = power;
    }
    //list de composant neccesaire avec les constructeur vide des composants
    //override car on réimplemente la list de la class mere Product
    @Override
    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentElectricMotor());
        return componentList;
    }
}

