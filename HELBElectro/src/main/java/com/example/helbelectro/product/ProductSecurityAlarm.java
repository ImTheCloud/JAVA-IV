package com.example.helbelectro.product;
import com.example.helbelectro.component.ComponentBattery;
import com.example.helbelectro.component.ComponentMotionSensor;

import java.util.ArrayList;
import java.util.List;

public class ProductSecurityAlarm extends Product {
    // attribut pour la creation du produit avec les attribut de leur composant néccesaire a leur creation
    private String load;
    private String colorComponent;
    private String range;

    public ProductSecurityAlarm(String load, String colorComponent, String range) {
        super("C", "#A25846", "P4",4,20,"Alarme de sécurité");
        this.load = load;
        this.colorComponent = colorComponent;
        this.range = range;
    }
    //list de composant neccesaire avec les constructeur vide des composants
    //override car on réimplemente la list de la class mere Product
    @Override
    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        componentList.add(new ComponentBattery());
        componentList.add(new ComponentMotionSensor());
        return componentList;
    }
}
