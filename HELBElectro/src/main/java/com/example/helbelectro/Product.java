package com.example.helbelectro;

import java.util.ArrayList;
import java.util.List;

public abstract class Product {
    protected String ecoScore;
    protected String color;

    public Product(String ecoScore, String color, String name, int manufacturingDuration, int sellingPrice) {
        this.ecoScore = ecoScore;
        this.color = color;
        this.name = name;
        this.manufacturingDuration = manufacturingDuration;
        this.sellingPrice = sellingPrice;
    }

    protected String name;
    protected int manufacturingDuration, sellingPrice;

    public List<Object> getComponentListNecessary() {
        List<Object> componentList = new ArrayList<>();
        return componentList;
    }

    // Getter and Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getEcoScore() {
        return ecoScore;
    }

    public void setEcoScore(String ecoScore) {
        this.ecoScore = ecoScore;
    }

    public int getManufacturingDuration() {
        return manufacturingDuration;
    }

    public void setManufacturingDuration(int manufacturingDuration) {
        this.manufacturingDuration = manufacturingDuration;
    }


}
