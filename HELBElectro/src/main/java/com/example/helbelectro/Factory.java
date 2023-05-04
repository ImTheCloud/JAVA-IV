package com.example.helbelectro;

import java.util.*;

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

    public static List<Product> getOptiTime() {
        Timer timer = new Timer();
        Iterator<Object> iterator = componentObjectList.iterator();
        int numProducts = 1;
        while (iterator.hasNext()) {
            Object component = iterator.next();
            if (component instanceof ComponentBattery) {
                createAndScheduleProduct(new ProductBattery(), timer, iterator, numProducts);
            } else if (component instanceof ComponentSensor) {
                createAndScheduleProduct(new ProductSensor(), timer, iterator, numProducts);
            } else if (component instanceof ComponentMotor) {
                createAndScheduleProduct(new ProductMotor(), timer, iterator, numProducts);
            }
            numProducts++;
        }

        // Afficher les produits et les composants restants dans les listes
        //System.out.println("liste des produit : " + productObjectList);
        //System.out.println("liste des composant : " + componentObjectList);
        return productObjectList;
    }

    private static void createAndScheduleProduct(Product product, Timer timer, Iterator<Object> iterator, int numProducts) {
        productObjectList.add(product);
        iterator.remove();
        long manufacturingDuration = product.getManufacturingDuration() * 1000;
        long delay = manufacturingDuration * numProducts;
        System.out.println("Temps restant avant la création de " + product.getClass().getSimpleName() + " : " + delay/1000 + " secondes.");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(product.getClass().getSimpleName() + " a été fabriqué");
            }
        }, delay);
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
        System.out.println("\n");
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
