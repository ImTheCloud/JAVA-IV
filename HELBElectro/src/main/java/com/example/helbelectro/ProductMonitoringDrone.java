package com.example.helbelectro;
import java.util.ArrayList;
import java.util.List;

public class ProductMonitoringDrone extends Product {
    // attribut pour la creation du produit avec les attribut de leur composant néccesaire a leur creation
    private String load;
    private String power;
    private String colorComponent;
    private String range;
    List<Object> componentList;
    public ProductMonitoringDrone(String power, String colorComponent, String range, String load) {
        super("E", "#767676", "P7",12,60,"Drone de surveillance");
        this.load = load;
        this.colorComponent = colorComponent;
        this.range = range;
        this.power = power;
    }
    //list de composant neccesaire avec les constructeur vide des composants
    //override car on réimplemente la list de la class mere Product
    @Override
    public List<Object> getComponentListNecessary() {
     componentList = new ArrayList<>();
        componentList.add(new ComponentBattery());
        componentList.add(new ComponentMotionSensor());
        componentList.add(new ComponentElectricMotor());
        return componentList;
    }
}
