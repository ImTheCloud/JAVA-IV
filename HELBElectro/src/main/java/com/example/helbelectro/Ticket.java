package com.example.helbelectro;

import java.util.Date;

public class Ticket {

    private Date date;
    private String productType;
    private double price;
    private int ecoScore;
    private int charge;
    private int power;

    // Constructor
    public Ticket(Date date, String productType, double price, int ecoScore, int charge, int power) {
        this.date = date;
        this.productType = productType;
        this.price = price;
        this.ecoScore = ecoScore;
        this.charge = charge;
        this.power = power;
    }



}
