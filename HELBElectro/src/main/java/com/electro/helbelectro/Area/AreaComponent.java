package com.electro.helbelectro.Area;

import com.electro.helbelectro.Component.Observer;
import java.util.ArrayList;

public class AreaComponent {

    private int numberLocation;

    public AreaComponent()
    {
        observers = new ArrayList<Observer>();
    }

    private ArrayList<Observer> observers;

    public void attach(Observer o){
        observers.add(o);
    }

    public void detach(Observer o){
        observers.remove(o);
    }

    public void Notify(){
        for(int i = 0 ; i < observers.size() ; i++){
            observers.get(i).update(this);
        }
    }

    // Method to deliver component
    public void deliverComponent() {
        System.out.println("Component delivered.");
    }

    // Method to store component
    public void storeComponent() {
        System.out.println("Component stored.");
    }

    // Method to manufacture component
    public void manufactureComponent() {
        System.out.println("Component manufactured.");
    }
}
