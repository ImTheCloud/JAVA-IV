package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public abstract class Product {

    protected String name;
    protected int manufacturingDuration, sellingPrice;
    protected String ecoScore;
    protected String color;

    public Product(String ecoScore, String color, String name, int manufacturingDuration, int sellingPrice) {
        this.ecoScore = ecoScore;
        this.color = color;
        this.name = name;
        this.manufacturingDuration = manufacturingDuration;
        this.sellingPrice = sellingPrice;
    }

    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        return componentList;
    }
    public String getName() {
        return name;
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
