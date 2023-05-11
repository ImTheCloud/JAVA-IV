package com.example.helbelectro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class HELBElectroController {
     static List<Object> productObjectList = new ArrayList<>();
     static List<Product> productObjectListSorted = new ArrayList<>();
     static List<Object> componentObjectList = new ArrayList<>();
     static AtomicBoolean isBusy = new AtomicBoolean(false);

    public static void createComponent(String componentName, String[] values) {
        // Vérifier si le nombre maximal de labels a été atteint
        if (componentObjectList.size() >= HELBElectroView.numberLBComponent) {
            System.out.println("Nombre maximal de composant atteint");
        } else {
            // Créer le composant en utilisant la Factory
            Component component = Factory.getInstance().createComponent(componentName, values);
            componentObjectList.add(component);
            System.out.println("Component " + componentName + " créé");
        }
    }

    public static void createProduct() {
        if (isBusy.get()) {
            return;
        }
        for (Product product : productObjectListSorted) {
            boolean hasAllComponents = hasAllNecessaryComponents(product);
            if (hasAllComponents && !isBusy.get()) {
                int manufacturingDuration = product.getManufacturingDuration();
                System.out.println("Attente de " + manufacturingDuration + " secondes avant de fabriquer " + product.getClass().getSimpleName());
                isBusy.set(true);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(manufacturingDuration), e -> {
                    Product newProduct = Factory.getInstance().createNewProduct(product);

                    if (newProduct != null) {
                        productObjectList.add(newProduct);
                        System.out.println(newProduct.getClass().getSimpleName() + " créé ");
                        removeUsedComponents(product);
                    }

                    isBusy.set(false);
                }));
                timeline.play(); // lancer la timeline pour attendre la durée de fabrication du produit
                break; // arrêter la boucle pour ne fabriquer qu'un seul produit à la fois
            }
        }
    }

    private static void removeUsedComponents(Product product) {
        List<Object> componentNames = product.getComponentListNecessary();
        for (Object componentName : componentNames) {
            // Recherche le premier composant qui correspont dans la liste des composants
            Component componentToRemove = (Component) componentObjectList.stream()
                    .filter(component -> component.getClass().getSimpleName().equals(componentName.getClass().getSimpleName()))
                    .findFirst()
                    .orElse(null);

            if (componentToRemove != null) {
                componentObjectList.remove(componentToRemove);
            }
        }
    }



    private static boolean hasAllNecessaryComponents(Product product) {
        for (Object componentName : product.getComponentListNecessary()) {
            boolean hasComponent = componentObjectList.stream()
                    .anyMatch(component -> component.getClass().getSimpleName().equals(componentName.getClass().getSimpleName()));
            if (!hasComponent) {
                return false;
            }
        }
        return true;
    }
        public static void addProductList() {
        productObjectListSorted.add(new ProductBattery(""));
        productObjectListSorted.add(new ProductSensor("",""));
        productObjectListSorted.add(new ProductMotor(""));
        productObjectListSorted.add(new ProductCar("",""));
        productObjectListSorted.add(new ProductAlarm("","",""));
        productObjectListSorted.add(new ProductDrone("","","",""));
        productObjectListSorted.add(new ProductRobot("","",""));
    }
    public static void getSortedProductListByTime() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getManufacturingDuration));
       // productObjectListSorted.forEach(System.out::println);

    }
    public static void getSortedProductListByScore() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getEcoScore));
    }
    public static void getSortedProductListByPrice() {
        addProductList();
        productObjectListSorted.sort(Comparator.comparing(Product::getSellingPrice));
        Collections.reverse(productObjectListSorted);
    }

    public static void getSortedProductListByDiverse() {
        addProductList();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            productObjectListSorted.sort(Comparator.comparingInt(p -> Collections.frequency(productObjectList, p)));
            Collections.reverse(productObjectListSorted);
        }));
        timeline.play();
    }

    public static void clearListProduct() {
        productObjectList.clear();
    }

}