package com.example.helbelectro;
public class ComponentMotor extends Component{
    // attribut neccesaire a la creation du composant :
    protected static String power;
    public ComponentMotor(String power) {
            ComponentMotor.power = power;
        }
    public ComponentMotor() {
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


