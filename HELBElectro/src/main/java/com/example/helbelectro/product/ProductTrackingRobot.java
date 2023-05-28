package com.example.helbelectro.product;
import com.example.helbelectro.component.ComponentElectricMotor;
import com.example.helbelectro.component.ComponentMotionSensor;

import java.util.ArrayList;
import java.util.List;

public class ProductTrackingRobot extends Product {
    // attribut pour la creation du produit avec les attribut de leur composant néccesaire a leur creation
    private String power;
    private String colorComponent;
    private String range;
    public ProductTrackingRobot(String power, String colorComponent, String range) {
        super("B", "#BBAE2A", "P6",6,40);
        this.colorComponent = colorComponent;
        this.range = range;
        this.power = power;
    }
    //list de composant neccesaire avec les constructeur vide des composants
    //override car on réimplemente la list de la class mere Product
    @Override
    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentMotionSensor());
        componentList.add(new ComponentElectricMotor());
        return componentList;
    }
}

