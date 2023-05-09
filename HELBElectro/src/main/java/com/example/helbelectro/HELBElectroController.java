package com.example.helbelectro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class HELBElectroController {
    private static HELBElectroController instance = null;
    public static List<Object> productObjectList = new ArrayList<>();
    public static List<Product> productObjectListSorted = new ArrayList<>();
    public static ObservableList<Object> componentObjectList = FXCollections.observableArrayList();
    private static Timeline timeline = new Timeline();


    public static HELBElectroController getInstance() {
        if (instance == null) {
            instance = new HELBElectroController();
        }
        return instance;
    }

    public void createComponent(String componentName, String[] values) {
        // Vérifier si le nombre maximal de labels a été atteint
        if (componentObjectList.size() >= HELBElectroView.number_lb_component) {
            System.out.println("Nombre maximal de composant atteint");
        } else {
            // Créer le composant en utilisant la Factory
            Factory factory = new Factory();
            Component component = factory.createComponent(componentName, values);
            componentObjectList.add(component);
            System.out.println("Component " + componentName + " créé");
        }
    }
    
    public static void createProduct() {
            for (Product product : productObjectListSorted) {
                boolean hasAllComponents = false;
                for (Object componentName : product.getComponentListNecessary()) {
                    boolean hasComponent = false;
                    for (Object component : componentObjectList) {
                        if (component.getClass().getSimpleName().equals(componentName.getClass().getSimpleName())) {
//                        System.out.println(component.getClass().getSimpleName());
//                        System.out.println(componentName.getClass().getSimpleName()+"******");
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

    public static void addProductList() {
        productObjectListSorted.add(new ProductBattery());
        productObjectListSorted.add(new ProductSensor());
        productObjectListSorted.add(new ProductMotor());
        productObjectListSorted.add(new ProductCar());
        productObjectListSorted.add(new ProductAlarm());
        productObjectListSorted.add(new ProductDrone());
        productObjectListSorted.add(new ProductRobot());
    }
    public static void getSortedProductListByTime() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getManufacturingDuration));
        System.out.println("Product list sorted by time :");
        for (Product product : productObjectListSorted) {
            System.out.println(product.getClass().getSimpleName() +
                    " time : " + product.getManufacturingDuration());
        }
        System.out.println("\n");
    }
    public static void getSortedProductListByScore() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getEcoScore));
        System.out.println("Product list sorted by score :");

        for (Product product : productObjectListSorted) {
            System.out.println(product.getClass().getSimpleName() +
                    ", score : " + product.getEcoScore());
        }
    }
    public static void getSortedProductListByPrice() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getSellingPrice));
        Collections.reverse(productObjectListSorted);
        System.out.println("Product list sorted by Price :");
        for (Product product : productObjectListSorted) {
            System.out.println(product.getClass().getSimpleName() +
                    ", Price : " + product.getSellingPrice());
        }
    }

}