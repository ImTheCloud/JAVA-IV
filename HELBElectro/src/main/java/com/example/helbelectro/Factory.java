package com.example.helbelectro;

import java.util.*;

public class Factory {
    private static Factory instance = null;
    static List<Object> componentObjectList = new ArrayList<>();
    private static List<Object> productObjectList = new ArrayList<>();

    public static List<String> componentNames = new ArrayList<>();
    private static List<Product> productObjectListSorted = new ArrayList<>();


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
        // Vérifier si le nombre maximal de labels a été atteint
        if (componentObjectList.size() >= Controller.number_lb_component) {
            System.out.println("Nombre maximal de composant atteint");
            return null;
        }else{
            // Créer le composant
            if (componentName.equals("Batterie")) {
                String load = values[2];
                ComponentBattery battery = new ComponentBattery(load);
                componentObjectList.add(battery);
                componentNames.add("Batterie");//C-Type-1
                System.out.println("Composant Batterie crée");
                return battery;
            } else if (componentName.equals("Capteur")) {
                String range = values[2];
                String color = values[3];
                ComponentSensor sensor = new ComponentSensor(range, color);
                componentObjectList.add(sensor);
                componentNames.add("Capteur");
                System.out.println("Composant Capteur crée");

                return sensor;
            } else if (componentName.equals("Moteur")) {
                String power = values[2];
                ComponentMotor motor = new ComponentMotor(power);
                componentObjectList.add(motor);
                componentNames.add("Moteur");
                System.out.println("Composant Moteur crée");

                return motor;
            }

        }



        return null;
    }



    public static void getOptiTime() {
        for (Product product : productObjectListSorted) {
            boolean validProduct = true;
            for (Object componentName : product.getComponentList()) {
                boolean hasComponent = false;
                for (Object component : componentObjectList) {
                    if (component.getClass().getSimpleName().equals(componentName)) {
                        hasComponent = true;
                        break;
                    }
                }
                if (!hasComponent) {
                    validProduct = false;
                    break;
                }
            }
            if (validProduct) {
                try {
                    Product newProduct = product.getClass().newInstance();
                    productObjectList.add(newProduct);
                    System.out.println(newProduct.getClass().getSimpleName() +
                            " créé avec succès.");
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Impossible de créer le produit " +
                        product.getClass().getSimpleName() + ", les composants nécessaires sont manquants.");
            }
        }
    }






    public static List<Product> addProductList() {
        productObjectListSorted.add(new ProductBattery());
        productObjectListSorted.add(new ProductSensor());
        productObjectListSorted.add(new ProductMotor());
        productObjectListSorted.add(new ProductCar());
        productObjectListSorted.add(new ProductAlarm());
        productObjectListSorted.add(new ProductDrone());
        productObjectListSorted.add(new ProductRobot());
        return productObjectListSorted;
    }
    public static List<Product> getSortedProductListByTime() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getManufacturingDuration));
        for (Product product : productObjectListSorted) {
            System.out.println(product.getClass().getSimpleName() +
                    " fabrication : " + product.getManufacturingDuration());
        }
        System.out.println("\n");
        return productObjectListSorted;
    }
    public static List<Product> getSortedProductListByScore() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getEcoScore));
        for (Product product : productObjectListSorted) {
            System.out.println(product.getClass().getSimpleName() +
                    ", score : " + product.getEcoScore());
        }
        return productObjectListSorted;
    }
    public static List<Product> getSortedProductListByPrice() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getSellingPrice));
        for (Product product : productObjectListSorted) {
            System.out.println(product.getClass().getSimpleName() +
                    ", Prix : " + product.getSellingPrice());
        }
        return productObjectListSorted;
    }


}
