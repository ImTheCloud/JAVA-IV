package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public abstract class Product {

    protected String nameForP;
    protected String nameForScene;
    protected int manufacturingDuration, sellingPrice;
    protected String ecoScore;
    protected String color;
    protected String load;
    protected String power;
    protected String range;
    protected String colorComponent;


    public Product(String ecoScore, String color, String nameForP,
                   int manufacturingDuration, int sellingPrice,String nameForScene) {
        this.ecoScore = ecoScore;
        this.color = color;
        this.nameForP = nameForP;
        this.manufacturingDuration = manufacturingDuration;
        this.sellingPrice = sellingPrice;
        this.nameForScene = nameForScene;

        //  String load,String power, String range, String colorComponent
//        this.load = load;
//        this.power = power;
//        this.range = range;
//        this.colorComponent = colorComponent;

    }

    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        return componentList;
    }
    public String getnameForP() {
        return nameForP;
    }
    public String getnameForScene() {
        return nameForScene;
    }

    public String getColor() {
        return color;
    }


    // Getter and Setter
    public int getSellingPrice() {
        return sellingPrice;
    }

    public String getEcoScore() {
        return ecoScore;
    }

    public int getManufacturingDuration() {
        return manufacturingDuration;
    }

}
