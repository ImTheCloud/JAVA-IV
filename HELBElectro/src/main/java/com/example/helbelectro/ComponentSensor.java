package com.example.helbelectro;
public class ComponentSensor extends Component {
    // attribut neccesaire a la creation du composant :
    protected static String range;
    protected static String colorSensor;
    public ComponentSensor(String range, String colorSensor) {
        ComponentSensor.range = range;
        ComponentSensor.colorSensor = colorSensor;
    }
    public ComponentSensor() {
        // constructeur vide car besoin pour la liste des composant necesaire a la cretion d'un produit
    }
    public static String getColorSensor() {
        return colorSensor;
    }
    public static String getRange() {
            return range;
        }
    public String getName() {
        return "C-Type-2";
    }
    public String getColor() {
        return "#4CAF50";
    }
    }

