package com.example.helbelectro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Factory {
    private static Factory instance = null;

    private static List<Object> componentObjectList = new ArrayList<>();
    public static List<String> componentNames = new ArrayList<>();

    private Factory() {

    }
    // design pattern : singleton
    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }
    public static List<Product> getSortedProductList() {
        List<Product> productList = new ArrayList<>();

        productList.add(new ProductBattery());
        productList.add(new ProductSensor());
        productList.add(new ProductMotor());
        productList.add(new ProductCar());
        productList.add(new ProductAlarm());
        productList.add(new ProductDrone());
        productList.add(new ProductRobot());

        productList.sort(Comparator.comparing(Product::getSellingPrice)
                .thenComparing(Product::getEcoScore)
                .thenComparing(Product::getManufacturingDuration));

        for (Product product : productList) {
            System.out.println(product.getClass().getSimpleName() +
                    ", score : " + product.getEcoScore() +
                    ", fabrication : " + product.getManufacturingDuration() +
                    ", Prix : " + product.getSellingPrice());
        }
        return productList;
    }

    public Object createComponent(String componentName, String[] values) {
        if (componentName.equals("Batterie")) {
            String load = values[2];
            ComponentBattery battery = new ComponentBattery(load);
            componentObjectList.add(battery);
            componentNames.add("C-Type-1");
            return battery;
        } else if (componentName.equals("Capteur")) {
            String range = values[2];
            String color = values[3];
            ComponentSensor sensor = new ComponentSensor(range, color);
            componentObjectList.add(sensor);
            componentNames.add("C-Type-2");
            return sensor;
        } else if (componentName.equals("Moteur")) {
            String power = values[2];
            ComponentMotor motor = new ComponentMotor(power);
            componentObjectList.add(motor);
            componentNames.add("C-Type-3");
            return motor;
        }
        return null;
    }

    public static List<Product> getOptiTime() {
        List<Product> productList = new ArrayList<>();
        int minDuration = Integer.MAX_VALUE;

        // récupérer le temps de production le plus petit
        for (Product product : new Product[]{new ProductBattery(), new ProductSensor(), new ProductMotor(),
                new ProductCar(), new ProductAlarm(), new ProductDrone(), new ProductRobot()}) {
            if (product.getManufacturingDuration() < minDuration) {
                minDuration = product.getManufacturingDuration();
            }
        }

        // créer les produits en parcourant la liste triée et en vérifiant le temps de production
        for (Product product : getSortedProductList()) {
            try{
              //  Thread.sleep(minDuration*1000);
              //  System.out.println(minDuration*1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (product.getManufacturingDuration() <= minDuration) {
                productList.add(product);
                minDuration = product.getManufacturingDuration();
            }
        }
        System.out.println(productList);
        return productList;
    }




}
