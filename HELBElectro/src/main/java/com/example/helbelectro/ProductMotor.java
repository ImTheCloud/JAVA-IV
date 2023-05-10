package com.example.helbelectro;
import java.util.ArrayList;
import java.util.List;

public class ProductMotor extends Product {
    // Attribut pour la creation du produit avec les attribut de leur composant néccesaire a leur creation
    private String power;
    public ProductMotor(String power) {
        super("A", "#B111BB", "P3",3,15,"Moteur éléctrique");
        this.power = power;
    }
    //List de composant neccesaire avec les constructeur vide des composants
    //Override car on réimplemente la list de la class mere Product
    @Override
    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();

        componentList.add(new ComponentMotor());
        return componentList;
    }
}

