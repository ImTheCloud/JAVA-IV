package com.example.helbelectro;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    public static List<Product> getOptiTime() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Iterator<Object> iterator = componentObjectList.iterator();
        while (iterator.hasNext()) {
            Object component = iterator.next();
            if (component instanceof ComponentBattery) {
                createAndScheduleProduct(new ProductBattery(), scheduler, iterator);
            } else if (component instanceof ComponentSensor) {
                createAndScheduleProduct(new ProductSensor(), scheduler, iterator);
            } else if (component instanceof ComponentMotor) {
                createAndScheduleProduct(new ProductMotor(), scheduler, iterator);
            }
        }

        // Attendre que toutes les tâches soient terminées avant de continuer
        scheduler.shutdown();
        try {
            scheduler.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Afficher les produits et les composants restants dans les listes
        System.out.println("liste des produit : " + productObjectList);
        System.out.println("liste des composant : " + componentObjectList);
        return productObjectList;
    }

    private static void createAndScheduleProduct(Product product, ScheduledExecutorService scheduler, Iterator<Object> iterator) {
        long manufacturingDuration = product.getManufacturingDuration();
        System.out.println("Temps restant avant la création de " + product.getClass().getSimpleName() + " : " + manufacturingDuration + " secondes.");
        scheduler.schedule(() -> {
            productObjectList.add(product);
            iterator.remove();
            System.out.println(product.getClass().getSimpleName() + " a été fabriqué");
        }, manufacturingDuration, TimeUnit.SECONDS);
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
