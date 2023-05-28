package com.example.helbelectro.product;
import java.util.ArrayList;
import java.util.List;

public abstract class Product {
    // attribut pour la creation de chaque produit
    protected String nameForP;
    protected int manufacturingDuration;
    protected int sellingPrice;
    protected String ecoScore;
    protected String color;
    protected Product(String ecoScore, String color, String nameForP, int manufacturingDuration, int sellingPrice) {
        this.ecoScore = ecoScore;
        this.color = color;
        this.nameForP = nameForP;
        this.manufacturingDuration = manufacturingDuration;
        this.sellingPrice = sellingPrice;

    }
    // list de composant neccesaire avec les constructeur vide des composants
    public List<Object> getComponentListNecessary() {
        return new ArrayList<>();
    }
    // Getter
    public String getnameForP() {
        return nameForP;
    }
    public String getColor() {
        return color;
    }
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
