package com.example.helbelectro;
import java.util.ArrayList;
import java.util.List;

public class ProductRobot extends Product {
    // Attribut pour la creation du produit avec les attribut de leur composant néccesaire a leur creation
    private String power;
    private String colorComponent;
    private String range;
    public ProductRobot(String power,String colorComponent, String range) {
        super("B", "#BBAE2A", "P6",6,40,"Robot suiveur");
        this.colorComponent = colorComponent;
        this.range = range;
        this.power = power;
    }
    public ProductRobot(){
        //constructeur vide pour la liste des produit trié par optimisation
    }
    //List de composant neccesaire avec les constructeur vide des composants
    //Override car on réimplemente la list de la class mere Product
    @Override
    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentSensor());
        componentList.add(new ComponentMotor());
        return componentList;
    }
}

