package com.example.helbelectro.component;

public abstract class Component {
    protected String name;
    protected String color;

    public Component(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public abstract String getAttribute();

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
