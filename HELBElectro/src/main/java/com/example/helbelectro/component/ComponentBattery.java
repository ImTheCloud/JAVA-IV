package com.example.helbelectro.component;

public class ComponentBattery extends Component {
    protected static String load;

    public ComponentBattery(String load) {
        super("C-Type-1", "#00BCD4");
        ComponentBattery.load = load;
    }

    // constructeur vide car besoin pour la liste des composant necesaire a la cretion d'un produit
    public ComponentBattery() {
        super("", "");
    }

    public static String getLoad() {
        return load;
    }

}
