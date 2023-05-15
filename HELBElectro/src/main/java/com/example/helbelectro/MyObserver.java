package com.example.helbelectro;

public class MyObserver implements Observer{
    @Override
    public void update() {
        System.out.println("Observateur notifié !");
    }
}
