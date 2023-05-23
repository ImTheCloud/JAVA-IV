package com.example.helbelectro.component;


public class ComponentElectricMotor extends Component {
    protected static String power;

    public ComponentElectricMotor(String power) {
        super("C-Type-3", "#B111BB");
        ComponentElectricMotor.power = power;
    }

    public ComponentElectricMotor() {
        super("C-Type-3", "#B111BB");
    }

    public static String getPower() {
        return power;
    }

    @Override
    public String getAttribute() {
        return getPower();
    }
}
