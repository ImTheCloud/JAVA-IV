package com.electro.helbelectro.Component;

public class MotionSensor extends BasicElectronicComponent {

    private int metter;
    private String color;

    public MotionSensor() {
    }

    public void afficher(){
        System.out.println( this.getInfo() + " : MotionSensor");
    }
}
