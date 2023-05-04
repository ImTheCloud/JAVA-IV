package com.example.helbelectro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product {
    private String ecoScore;
    private int manufacturingDuration, sellingPrice;

    public Product(String ecoScore, int manufacturingDuration, int sellingPrice) {
        this.ecoScore = ecoScore;
        this.manufacturingDuration = manufacturingDuration;
        this.sellingPrice = sellingPrice;
    }

    public List<Object> getComponentList() {
        List<Object> componentList = new ArrayList<>();
        return componentList;
    }

    // Getter and Setter
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
