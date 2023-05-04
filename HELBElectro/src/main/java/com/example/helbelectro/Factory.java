package com.example.helbelectro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Factory {
    private static Factory instance = null;
    private static List<Object> componentObjectList = new ArrayList<>();
    public static List<String> componentNames = new ArrayList<>();
    private static List<Product> productObjectList = new ArrayList<>();


    private Factory() {
    }
    // design pattern : singleton
    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
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

    public static long getOptiTime() {
        long startTime = System.currentTimeMillis();

        // Parcours de la liste des composants
        for (Object component : componentObjectList) {
            if (component instanceof ComponentBattery) {
                productObjectList.add(new ProductBattery());
                    componentObjectList.remove(component);
            } else if (component instanceof ComponentSensor) {
                // Création du produit Sensor s'il n'existe pas déjà
                productObjectList.add(new ProductSensor());
                    componentObjectList.remove(component);

            } else if (component instanceof ComponentMotor) {
                // Création du produit Motor s'il n'existe pas déjà
                productObjectList.add(new ProductMotor());
                    componentObjectList.remove(component);
            }
        }
        System.out.println("liste des produit :"+productObjectList);
        System.out.println("liste des composant :"+componentObjectList);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }


    public static List<Product> addProductList() {
        productObjectList.add(new ProductBattery());
        productObjectList.add(new ProductSensor());
        productObjectList.add(new ProductMotor());
        productObjectList.add(new ProductCar());
        productObjectList.add(new ProductAlarm());
        productObjectList.add(new ProductDrone());
        productObjectList.add(new ProductRobot());
        return productObjectList;
    }
    public static List<Product> getSortedProductListByTime() {
        addProductList();
        productObjectList.sort(Comparator.comparing(Product::getManufacturingDuration));
        for (Product product : productObjectList) {
            System.out.println(product.getClass().getSimpleName() +
                    " fabrication : " + product.getManufacturingDuration());
        }
        return productObjectList;
    }
    public static List<Product> getSortedProductListByScore() {
        addProductList();
        productObjectList.sort(Comparator.comparing(Product::getEcoScore));
        for (Product product : productObjectList) {
            System.out.println(product.getClass().getSimpleName() +
                    ", score : " + product.getEcoScore());
        }
        return productObjectList;
    }
    public static List<Product> getSortedProductListByPrice() {
        addProductList();
        productObjectList.sort(Comparator.comparing(Product::getSellingPrice));
        for (Product product : productObjectList) {
            System.out.println(product.getClass().getSimpleName() +
                    ", Prix : " + product.getSellingPrice());
        }
        return productObjectList;
    }


}
