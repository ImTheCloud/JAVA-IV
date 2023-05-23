package com.example.helbelectro.component;

public class ComponentElectricMotor extends Component {
    protected static String power;

    public ComponentElectricMotor(String power) {
        super("C-Type-3", "#B111BB");
        ComponentElectricMotor.power = power;
    }
    // constructeur vide car besoin pour la liste des composant necesaire a la cretion d'un produit
    public ComponentElectricMotor() {
        super("", "");
    }

    public static String getPower() {
        return power;
    }

    @Override
    public String getAttribute() {
        return getPower();
    }
}
