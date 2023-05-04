package com.example.helbelectro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Factory {
    private static Factory instance = null;
    private static List<Object> componentObjectList = new ArrayList<>();
    public static List<String> componentNames = new ArrayList<>();
    private static List<Product> productObjectListSortedBy = new ArrayList<>();
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
    public static List<Product> getOPtiTime() {
        Iterator<Object> iterator = componentObjectList.iterator();
        while (iterator.hasNext()) {
            Object component = iterator.next();
            if (component instanceof ComponentBattery) {
                ProductBattery batteryProduct = new ProductBattery();
                productObjectList.add(batteryProduct);
                iterator.remove();
            } else if (component instanceof ComponentSensor) {
                ProductSensor sensorProduct = new ProductSensor();
                productObjectList.add(sensorProduct);
                iterator.remove();
            } else if (component instanceof ComponentMotor) {
                ProductMotor motorProduct = new ProductMotor();
                productObjectList.add(motorProduct);
                iterator.remove();
            }
        }

        // Afficher les produits et les composants restants dans les listes
        System.out.println("liste des produit : "+productObjectList);
        System.out.println("liste des composant : "+componentObjectList);
        return productObjectList;
    }


    public static List<Product> addProductList() {
        productObjectListSortedBy.add(new ProductBattery());
        productObjectListSortedBy.add(new ProductSensor());
        productObjectListSortedBy.add(new ProductMotor());
        productObjectListSortedBy.add(new ProductCar());
        productObjectListSortedBy.add(new ProductAlarm());
        productObjectListSortedBy.add(new ProductDrone());
        productObjectListSortedBy.add(new ProductRobot());
        return productObjectListSortedBy;
    }
    public static List<Product> getSortedProductListByTime() {
        addProductList();
        productObjectListSortedBy.sort(Comparator.comparing(Product::getManufacturingDuration));
        for (Product product : productObjectListSortedBy) {
            System.out.println(product.getClass().getSimpleName() +
                    " fabrication : " + product.getManufacturingDuration());
        }
        return productObjectListSortedBy;
    }
    public static List<Product> getSortedProductListByScore() {
        addProductList();
        productObjectListSortedBy.sort(Comparator.comparing(Product::getEcoScore));
        for (Product product : productObjectListSortedBy) {
            System.out.println(product.getClass().getSimpleName() +
                    ", score : " + product.getEcoScore());
        }
        return productObjectListSortedBy;
    }
    public static List<Product> getSortedProductListByPrice() {
        addProductList();
        productObjectListSortedBy.sort(Comparator.comparing(Product::getSellingPrice));
        for (Product product : productObjectListSortedBy) {
            System.out.println(product.getClass().getSimpleName() +
                    ", Prix : " + product.getSellingPrice());
        }
        return productObjectListSortedBy;
    }


}
