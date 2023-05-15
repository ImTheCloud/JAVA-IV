package com.example.helbelectro.conponent;

public class ComponentBattery implements Component {
    // attribut neccesaire a la creation du composant
    protected static String load;
    public ComponentBattery(String load) {
        ComponentBattery.load = load;
    }
    public ComponentBattery() {
        // constructeur vide car besoin pour la liste des composant necesaire a la cretion d'un produit
    }
    public String getName() {
        return "C-Type-1";
    }
    public String getColor() {
        return "#00BCD4";
    }
    public static String getLoad() {
        return load;
    }
}

