package com.example.helbelectro.component;

public class ComponentMotionSensor extends Component {
    protected static String range;
    protected static String colorSensor;

    public ComponentMotionSensor(String range, String colorSensor) {
        super("C-Type-2", "#4CAF50");
        ComponentMotionSensor.range = range;
        ComponentMotionSensor.colorSensor = colorSensor;
    }
    // constructeur vide car besoin pour la liste des composant necesaire a la cretion d'un produit
    public ComponentMotionSensor() {
        super("", "");
    }

    public static String getColorSensor() {
        return colorSensor;
    }

    public static String getRange() {
        return range;
    }

    @Override
    public String getAttribute() {
        return getRange() + ", " + getColorSensor();
    }
}


