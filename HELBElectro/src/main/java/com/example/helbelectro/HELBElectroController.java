package com.example.helbelectro;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.helbelectro.HELBElectroView.numberButton;

public class HELBElectroController implements Optimization {
    private static HELBElectroController instance;
    private static final Timeline timelineChoiceOpti = new Timeline();


    // Constructeur privé pour empêcher l'instanciation directe
    HELBElectroController() {
    }

    // Méthode statique pour obtenir l'instance unique du singleton
    public static HELBElectroController getInstance() {
        if (instance == null) {
            instance = new HELBElectroController();
        }
        return instance;
    }

    // Autres méthodes de la classe
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
         productObjectListSorted.forEach(System.out::println);

        timeline.play();
    }

    @Override
    public void onOptiChoiceSelected(String selectedItem) {
        switch (selectedItem) {
            case "Time":
                getSortedProductListByTime();
                startTimeline();
                break;
            case "Cost":
                getSortedProductListByPrice();
                startTimeline();
                break;
            case "Score":
                getSortedProductListByScore();
                startTimeline();
                break;
            case "Diverse":
                getSortedProductListByDiverse();
                startTimeline();
                break;
        }
    }

    private static void startTimeline() {
        timelineChoiceOpti.stop();
        timelineChoiceOpti.getKeyFrames().clear();
        timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            createProduct();
            setButtonProduct();
        }));
        timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
        timelineChoiceOpti.play();
    }

    public static void setButtonProduct() {
        int index = 0;
        int compteur = 0;
        HELBElectroView.productButtonList = new ArrayList<>(); // Création de la liste de boutons

        for (Node node : HELBElectroView.areaProduct.getChildren()) {
            if (node instanceof Button setButton) {
                if (index >= productObjectList.size()) {
                    break;
                }

                Product product = (Product) productObjectList.get(index);
                setButton.setUserData(product);
                setButton.setText(product.getnameForP());
                setButton.setStyle("-fx-background-color: " + product.getColor() + ";");

                HELBElectroView.productButtonList.add(setButton); // Ajout du bouton à la liste

                index++;
                compteur++;
                if (compteur == numberButton) {
                    inializeAlertForAreaProductFull();
                    break;
                }
            }
        }
    }

    private static void inializeAlertForAreaProductFull() {
        System.out.println("stop production");
         stopTimeline();
        // runlater psq ya le thread java fx en cours
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entrepôt des produits");
            alert.setHeaderText(null);
            alert.setContentText("L'entrepôt est complet, veuillez le vider !");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                productObjectList.clear();
                HELBElectroView.clearProductLabels();
                System.out.println("Entrepôt vidé !");
            }
        });
    }

    private static void stopTimeline() {
        timelineChoiceOpti.stop();
        timelineChoiceOpti.getKeyFrames().clear();
        timelineChoiceOpti.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {}));
        timelineChoiceOpti.setCycleCount(Animation.INDEFINITE);
        timelineChoiceOpti.play();
    }

}