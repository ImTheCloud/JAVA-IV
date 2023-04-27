package com.example.helbelectro;

import com.example.helbelectro.Observer;
import com.example.helbelectro.Subject;
import java.util.ArrayList;

public class AreaProductFinish implements Subject {
    public AreaProductFinish()
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
}
