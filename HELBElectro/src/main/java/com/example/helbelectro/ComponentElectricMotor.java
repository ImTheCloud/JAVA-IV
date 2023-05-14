package com.example.helbelectro;
public class ComponentElectricMotor implements Component{
    // attribut neccesaire a la creation du composant
    protected static String power;
    public ComponentElectricMotor(String power) {
            ComponentElectricMotor.power = power;
        }
    public ComponentElectricMotor() {
        // constructeur vide car besoin pour la liste des composant necesaire a la cretion d'un produit
    }
    public static String getPower() {
            return power;
        }
    public String getName() {
        return "C-Type-3";
    }
    public String getColor() {
        return "#B111BB";
    }
    }


