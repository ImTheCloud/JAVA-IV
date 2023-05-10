package com.example.helbelectro;
import java.util.ArrayList;
import java.util.List;

public class ProductCar extends Product {
    // Attribut pour la creation du produit avec les attribut de leur composant néccesaire a leur creation
    private String load;
    private String power;
    public ProductCar(String power,String load) {
        super("B", "#A7632D", "P5",5,30,"Voiture télécommandée");
        this.load = load;
        this.power = power;
    }
    public ProductCar(){
        //constructeur vide pour la liste des produit trié par optimisation
    }
    //List de composant neccesaire avec les constructeur vide des composants
    //Override car on réimplemente la list de la class mere Product
    @Override
    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentBattery());
        componentList.add(new ComponentMotor());
        return componentList;
    }
}