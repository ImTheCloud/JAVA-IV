package com.example.helbelectro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;

import java.util.*;

public class Factory {
    private static Factory instance = null;
    public static List<Object> productObjectList = new ArrayList<>();
    public static List<Product> productObjectListSorted = new ArrayList<>();
    private static Timeline timeline = new Timeline();



    private Factory() {
    }
    // design pattern : singleton
    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    static ObservableList<Object> componentObjectList = FXCollections.observableArrayList();

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
                //componentNames.add("Batterie");//C-Type-1
                System.out.println("Component Batterie : "+load);
                return battery;
            } else if (componentName.equals("Capteur")) {
                String range = values[2];
                String color = values[3];
                ComponentSensor sensor = new ComponentSensor(range, color);
                componentObjectList.add(sensor);
                //componentNames.add("Capteur");
                System.out.println("Component Capteur : "+range+", "+color);

                return sensor;
            } else if (componentName.equals("Moteur")) {
                String power = values[2];
                ComponentMotor motor = new ComponentMotor(power);
                componentObjectList.add(motor);
                // componentNames.add("Moteur");
                System.out.println("Component Moteur : "+power);

                return motor;
            }

        }
        return null;
    }



    public static void createProduct() {
        for (Product product : productObjectListSorted) {
            boolean hasAllComponents = false;
            for (Object componentName : product.getComponentListNecessary()) {
                boolean hasComponent = false;
                for (Object component : componentObjectList) {
                    if (component.getClass().getSimpleName().equals(componentName.getClass().getSimpleName())) {
                        System.out.println(component.getClass().getSimpleName());
                        System.out.println(componentName.getClass().getSimpleName()+"******");
                        hasComponent = true;
                        break;
                    }
                }
                if (!hasComponent) {
                    hasAllComponents = false;
                    break;
                } else {
                    hasAllComponents = true;
                }
            }


            if (hasAllComponents) {
                int manufacturingDuration = product.getManufacturingDuration();
                System.out.println("Attente de " + manufacturingDuration + " secondes avant de fabriquer " + product.getClass().getSimpleName());
                // Utilisation de Timeline pour la pause
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(manufacturingDuration), e -> {
                    try {
                        Product newProduct = product.getClass().newInstance();
                        productObjectList.add(newProduct);
                        System.out.println(newProduct.getClass().getSimpleName() + " créé ");
                        // Suppression des composants utilisés
                        for (Object componentName : product.getComponentListNecessary()) {
                            Object componentToRemove = null;
                            for (Object component : componentObjectList) {
                                if (component.getClass().getSimpleName().equals(componentName.getClass().getSimpleName())) {
                                    componentToRemove = component;
                                    break;
                                }
                            }
                            if (componentToRemove != null) {
                                componentObjectList.remove(componentToRemove);
                            }
                        }
                    } catch (InstantiationException | IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                }));
                timeline.play();
            } else {
//                System.out.println("Impossible de créer le produit " + product.getClass().getSimpleName() +
//                        ", certains composants sont manquants.");
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
        System.out.println("Product list sorted by time :");
        for (Product product : productObjectListSorted) {
            System.out.println(product.getClass().getSimpleName() +
                    " time : " + product.getManufacturingDuration());
        }
        System.out.println("\n");
        return productObjectListSorted;
    }
    public static List<Product> getSortedProductListByScore() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getEcoScore));
        System.out.println("Product list sorted by score :");

        for (Product product : productObjectListSorted) {
            System.out.println(product.getClass().getSimpleName() +
                    ", score : " + product.getEcoScore());
        }
        return productObjectListSorted;
    }
    public static List<Product> getSortedProductListByPrice() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getSellingPrice));
        Collections.reverse(productObjectListSorted);
        System.out.println("Product list sorted by Price :");
        for (Product product : productObjectListSorted) {
            System.out.println(product.getClass().getSimpleName() +
                    ", Price : " + product.getSellingPrice());
        }
        return productObjectListSorted;
    }


}
