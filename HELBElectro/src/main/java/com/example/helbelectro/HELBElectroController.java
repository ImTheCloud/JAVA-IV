package com.example.helbelectro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HELBElectroController {
    private static HELBElectroController instance = null;
    public static List<Object> productObjectList = new ArrayList<>();
    public static List<Product> productObjectListSorted = new ArrayList<>();
    public static ObservableList<Object> componentObjectList = FXCollections.observableArrayList();

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
        }else{
            // Créer le composant
            switch (componentName) {
                case "Batterie" -> {
                    String load = values[2];
                    ComponentBattery battery = new ComponentBattery(load);
                    componentObjectList.add(battery);
                    //componentNames.add("Batterie");//C-Type-1
                    System.out.println("Component Batterie : " + load);
                }
                case "Capteur" -> {
                    String range = values[2];
                    String color = values[3];
                    ComponentSensor sensor = new ComponentSensor(range, color);
                    componentObjectList.add(sensor);
                    //componentNames.add("Capteur");
                    System.out.println("Component Capteur : " + range + ", " + color);
                }
                case "Moteur" -> {
                    String power = values[2];
                    ComponentMotor motor = new ComponentMotor(power);
                    componentObjectList.add(motor);
                    // componentNames.add("Moteur");
                    System.out.println("Component Moteur : " + power);
                }
            }

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
               // int manufacturingDuration = product.getManufacturingDuration();
                //System.out.println("Attente de " + manufacturingDuration + " secondes avant de fabriquer " + product.getClass().getSimpleName());
                // Utilisation de Timeline pour la pause

//                try {
//                    Thread.sleep(manufacturingDuration*1000);
                productObjectList.add(product);
                System.out.println(product.getClass().getSimpleName() + " créé ");
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }

                // supp des composants utilisés
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