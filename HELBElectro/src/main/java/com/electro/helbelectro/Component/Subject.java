package com.electro.helbelectro.Component;

public interface Subject {

    void attach(Observer o);
    void detach(Observer o);
    void Notify();

}


