package com.example.helbelectro;

public class ElectricMotor extends BasicElectronicComponent {

    private int power;

    public ElectricMotor() {
    }

    public void afficher(){
        System.out.println( this.getInfo() + " : ElectricMotor");
    }

}
