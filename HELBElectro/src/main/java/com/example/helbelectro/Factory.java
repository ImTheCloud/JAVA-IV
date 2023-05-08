package com.example.helbelectro;

public class Factory {
    private static Factory instance = null;
    private Factory() {
    }
    // design pattern : singleton
    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }
}
