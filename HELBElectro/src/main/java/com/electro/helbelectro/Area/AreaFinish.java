package com.electro.helbelectro.Area;

import com.electro.helbelectro.Component.Observer;
import com.electro.helbelectro.Component.Subject;
import java.util.ArrayList;

public class AreaFinish implements Subject {
    public AreaFinish()
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
