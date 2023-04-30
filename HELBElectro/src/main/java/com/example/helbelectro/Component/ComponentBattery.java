package com.example.helbelectro.Component;


public class ComponentBattery {
    private String niveauDeCharge;

    public ComponentBattery(String niveauDeCharge) {
        this.niveauDeCharge = niveauDeCharge;
    }

    public String getNiveauDeCharge() {
        return niveauDeCharge;
    }

    public double fournirEnergie() {
        // Code pour fournir de l'énergie et la retourner en tant que double
        return 0;
    }

}

