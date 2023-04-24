package com.electro.helbelectro.Component;

public class Battery extends BasicElectronicComponent {
    private int percentage;

    public Battery() {
    }

    public void afficher(){
        System.out.println( this.getInfo() + " : Battery");
    }

}
